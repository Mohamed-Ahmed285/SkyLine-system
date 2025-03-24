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
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class Ticketdata implements Initializable {

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
    public void setTicket(Ticket tic){
        this.tic= tic;
    }


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
    private Label Payment_Id;
    @FXML
    private Label Ticket_num;
    @FXML
    private Label Total_Cost;
    @FXML
    private Label seat_num;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater( () -> {
            Payment_Id.setText(tic.getPaymentID());
            Ticket_num.setText(tic.getTicket_number());
            Total_Cost.setText(String.valueOf(tic.getFare()));
            seat_num.setText(tic.getSeatNumber());
        });
    }

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


}
