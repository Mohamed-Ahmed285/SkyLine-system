package group.proj;
import group.proj.Air_port.FlightsManager;
import group.proj.Booking_Sys.Booking;
import group.proj.User.Passenger;
import group.proj.User.UserFileManger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;

public class BookingmenuController {

    @FXML
    private Pane Flight_booking;
    @FXML
    private Pane Payment;

    private FXMLLoader fxmlLoader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private UserFileManger userManager;
    private FlightsManager flightsManager;
    private Passenger Currentuser;
    private Booking newbooking;

    @FXML
    private TextField flight_num;

    @FXML
    private TextField from_city;

    @FXML
    private TextField to_city;

    @FXML
    private Label warning_text;

    private boolean isInt(TextField field) {
        try {
            int num = Integer.parseInt(field.getText());
            return true;
        }catch(NumberFormatException e){
            return false;
        }
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



    //========================
    //to other menus
    @FXML
    public void to_menu(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        root = fxmlLoader.load();
        UserMenuController controller = fxmlLoader.getController();
        controller.setUserManager(userManager,Currentuser);
        controller.setFlightsManager(flightsManager);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1200 , 620);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    @FXML
    public void on_next_btn_click(ActionEvent event) throws IOException {

        AtomicBoolean truedata= new AtomicBoolean(false);
        int flightnum;
        String from_;
        String to_;
        if (isInt(flight_num)){
            flightnum = Integer.parseInt(flight_num.getText());
        }else {
            warning_text.setText("Please The Flight_Id has to be a Number");
            return;
        }
        from_ = from_city.getText();
        to_ = to_city.getText();
        if (from_.isEmpty()){
            warning_text.setText("Please Input the Departure City");
            return;
        }
        if (to_.isEmpty()){
            warning_text.setText("Please Input the Arrival City");
                return;
        }

        flightsManager.getFlights().forEach(flight -> {
                    if (flight.getFlightId() == flightnum) {
                        if (flight.getDepartureAirport().getCity().equalsIgnoreCase(from_)) {
                            if (flight.getArrivalAirport().getCity().equalsIgnoreCase(to_)) {
                                newbooking = new Booking(Currentuser.getNumOfBookings(),Currentuser.getUsername(), flight);
                                Currentuser.bookflight(newbooking);
                                truedata.set(true);
                            }
                        }
                    }
        });

        if (truedata.get()) {

            to_seatPick(event);

        }else{
            warning_text.setText("This Flight Doesn't Exist!");
        }

    }
    @FXML
    public void to_seatPick(ActionEvent event) throws IOException{

        fxmlLoader = new FXMLLoader(getClass().getResource("seatpickingmenu.fxml"));
        root = fxmlLoader.load();
        //=============================
        SeatpickingmenuController controller = fxmlLoader.getController();
        controller.setUserManager(userManager,Currentuser);
        controller.setFlightsManager(flightsManager);
        controller.setBooking(newbooking);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1200 , 620);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
}
