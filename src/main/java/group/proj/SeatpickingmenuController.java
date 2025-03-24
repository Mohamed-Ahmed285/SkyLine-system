package group.proj;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import group.proj.Air_port.FlightsManager;
import group.proj.Booking_Sys.Booking;
import group.proj.Booking_Sys.Payment;
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
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicInteger;

public class SeatpickingmenuController implements Initializable {

    private FXMLLoader fxmlLoader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private UserFileManger userManager;
    private FlightsManager flightsManager;
    private Passenger Currentuser;
    private Booking newbooking;
    private Payment newpayment;
    @FXML
    private ChoiceBox<String> Seat_pick;
    @FXML
    private Button Next_btn;
    @FXML
    private Label warning;
    @FXML
    private Label Selected_Seat;
    @FXML
    private CheckBox El;

    @FXML
    private CheckBox Insu;


    public void setUserManager(UserFileManger userManager , Passenger CurrentUser) {
        this.userManager = userManager;
        this.Currentuser=CurrentUser;
    }
    public void setBooking(Booking newbooking){
        this.newbooking = newbooking;

    }
    public void setFlightsManager(FlightsManager flightsManager) {
        this.flightsManager = flightsManager;
    }



    @FXML
    public void on_Next_btn(ActionEvent event) throws IOException{

        if (Seat_pick.getValue()==null){
                warning.setText("Please Select A seat");
        }else {


            newbooking.getBookedflight().getSeats().forEach(seat -> {
                if (Seat_pick.getValue().equals(seat.getSeatNumber())){
                    newbooking.setBookedSeat(seat);
                }
            });
            newpayment = new Payment(newbooking.getBookedSeat(),Currentuser,newbooking.getBookedflight());
            if (El.isSelected()){
                newpayment.addAdditionalService("Extra Luggage");
            }
            if (Insu.isSelected()){
                newpayment.addAdditionalService("Insurance");
            }
            to_payment(event);
        }
    }




    @FXML
    public void to_flight_booking(javafx.event.ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("Bookingmenu.fxml"));
        root = fxmlLoader.load();
        //=============================
        BookingmenuController controller = fxmlLoader.getController();
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
    public void to_payment(javafx.event.ActionEvent event) throws IOException {
            fxmlLoader = new FXMLLoader(getClass().getResource("Payment.fxml"));
            root = fxmlLoader.load();
            //=============================
            PaymentController controller = fxmlLoader.getController();
            controller.setUserManager(userManager,Currentuser);
            controller.setFlightsManager(flightsManager);
            controller.setBooking(newbooking);
            controller.setPayment(newpayment);
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.centerOnScreen();
            stage.show();
        }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Platform.runLater(()->{

            newbooking.getBookedflight().getSeats().forEach(seat -> {

                if (seat.isAvailable()) {

                    Seat_pick.getItems().add(seat.getSeatNumber());

                }

            });

        });

        Seat_pick.setOnAction(actionEvent -> {
            Selected_Seat.setText("");
            Selected_Seat.setTextFill(Color.color(0 ,0,0));
        });

    }
}
