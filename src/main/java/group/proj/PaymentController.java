package group.proj;

import group.proj.Air_port.FlightsManager;
import group.proj.Booking_Sys.Booking;
import group.proj.Booking_Sys.Payment;
import group.proj.Booking_Sys.Ticket;
import group.proj.User.Passenger;
import group.proj.User.UserFileManger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PaymentController implements Initializable {

    private FXMLLoader fxmlLoader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private UserFileManger userManager;
    private FlightsManager flightsManager;
    private Passenger Currentuser;
    private Booking newbooking;
    private Payment newpayment;
    private Ticket tic;

    @FXML
    private TextField CVV_Field;
    @FXML
    private TextField CreditField;
    @FXML
    private TextField ExpirationField;

    @FXML
    private Label Total;

    @FXML
    private Label warning_text;

    @FXML
    private Label add_serv;

    @FXML
    private Label basefare;

    @FXML
    private Label taxes;

    public static boolean isValidThreeDigitInteger(String input) {
        // Regex pattern for a strict three-digit integer
        String pattern = "^-?[0-9]{3}$";

        // Check if the input matches the regex
        return input.matches(pattern);
    }
    private boolean isValidCreditCard(String cardNumber) {

        String sanitized = cardNumber.replaceAll("[ -]", "");

        // Credit card regex pattern
        String pattern = "[0-9]{16}$";
        return sanitized.matches(pattern);

    }
    private boolean isValidMMYY(String input) {
        // Regex pattern for MM/YY
        String pattern = "^(0[1-9]|1[0-2])/\\d{2}$";
        // Compile and match
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(input);
        return matcher.matches();
    }
    public void setUserManager(UserFileManger userManager , Passenger CurrentUser) {
        this.userManager = userManager;
        this.Currentuser=CurrentUser;
    }
    public void setFlightsManager(FlightsManager flightsManager) {
        this.flightsManager = flightsManager;
    }
    public void setBooking(Booking newbooking){
        this.newbooking = newbooking;
    }
    public void setPayment(Payment newpayment){
        this.newpayment= newpayment;
}
    @FXML
    public void to_seatPick(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("seatpickingmenu.fxml"));
        root = fxmlLoader.load();
        //=============================
        SeatpickingmenuController controller = fxmlLoader.getController();
        controller.setUserManager(userManager,Currentuser);
        controller.setFlightsManager(flightsManager);
        controller.setBooking(newbooking);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
    @FXML
    public void on_pay_click(ActionEvent event) throws  IOException{

        if (CreditField.getText().isEmpty()){
            warning_text.setText("Please Input your Credit Card");
            return;
        }
        if (CVV_Field.getText().isEmpty()){
                warning_text.setText("Please Input your CVV");
            return;
        }
        if (ExpirationField.getText().isEmpty()){
            warning_text.setText("Please Input your Expiration Date");
            return;
        }
        if (!isValidCreditCard(CreditField.getText())){
            warning_text.setText("Please Input A Valid CreditCard number");
            return;
        }
        if (!isValidThreeDigitInteger(CVV_Field.getText())){
            warning_text.setText("Please Input A Valid CVV");
            return;
        }
        if (!isValidMMYY(ExpirationField.getText())){
            warning_text.setText("Please Input A Valid Expiration Date");
            return;
        }

        tic = new Ticket(String.valueOf(newbooking.getBookedflight().getFlightId()),newpayment.getTotalCost(),String.valueOf(newbooking.getBookingID()) ,newbooking.getBookedSeat().getSeatNumber(),String.valueOf(newpayment.getPayment_id()));
        newbooking.setTicket(tic);
        newbooking.setPayment(newpayment);
        flightsManager.getFlights().forEach(flight -> {
            if (flight.getFlightId() == newbooking.getBookedflight().getFlightId()){
                flight.getSeats().forEach(seat -> {
                    if (seat.getSeatNumber().equals(newbooking.getBookedSeat().getSeatNumber())){
                        seat.setAvailable(false);
                    }
                });
                flight.bookaseat();
            }
        });
        newbooking.setBookingStatus("Confirmed");
        to_Ticketdata(event);
    }

    public void to_Ticketdata(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("Ticketdata.fxml"));
        root = fxmlLoader.load();
        //=============================
        Ticketdata controller = fxmlLoader.getController();
        controller.setUserManager(userManager,Currentuser);
        controller.setFlightsManager(flightsManager);
        controller.setBooking(newbooking);
        controller.setTicket(tic);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(()->{

            Total.setText(String.valueOf(newpayment.getTotalCost()));
            basefare.setText(String.valueOf(newpayment.getBaseFare()));
            taxes.setText(String.valueOf(newpayment.getTaxes()));
            add_serv.setText(String.valueOf(newpayment.calculateServiceCost()));



        });

    }
}
