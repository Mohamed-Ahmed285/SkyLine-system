package group.proj;
import group.proj.Air_port.Airport;
import group.proj.Air_port.Flight;
import group.proj.Air_port.FlightsManager;
import group.proj.User.UserFileManger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends LoginMenu {

    public static UserFileManger userManager;
    public static FlightsManager flightsManager;

    public void start(Stage stage) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Parent root = fxmlLoader.load();
        //===================================
        loginMenuControllers controller = fxmlLoader.getController();
        controller.setUserManager(userManager);
        controller.setFlightsManager(flightsManager);
        //==========================
        Scene scene = new Scene(root, 330, 550);
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.setMinWidth(340);
        stage.setMinHeight(580);
        stage.setTitle("SkyLine");
        stage.getIcons().add(icon);
        stage.centerOnScreen();
        stage.setScene(scene);
        stage.show();
        //==========================


    }
    public static void main(String[] args) {


        String userfilePath = "src/main/resources/files/users.json";
        String adminfilePath = "src/main/resources/files/admins.json";
        String flightsFilePath = "src/main/resources/files/flights.json";
        String airportsFilePath = "src/main/resources/files/airports.json";
        userManager = new UserFileManger(userfilePath,adminfilePath);
        flightsManager = new FlightsManager(flightsFilePath,airportsFilePath);
        userManager.loadUsers();
        userManager.loadAdmins();
        flightsManager.loadFlightsFromJson();
        flightsManager.loadAirPortsFromJson();

//        Airport myAirport1 = new Airport(7, "Sydney Kingsford Smith Airport","Sydney","Australia");
//        Airport myAirport2 = new Airport(8, "Beijing Capital International Airport","Beijing","China");
//        Flight myflight = new Flight(flightsManager.getnumberofFlights(),myAirport1,myAirport2,"08:00 AM","08:00 PM","2024-12-20","2024-12-20");
//        flightsManager.getFlights().add(myflight);

        launch(args);

//        flightsManager.getFlights().forEach(
//                flight -> {
//                    System.out.println(flight.getSeats().toArray().length);
//                    flight.getSeats().forEach(
//                            seat -> {
//                                System.out.println(seat.getId());
//                            }
//                    );
//                }
//        );


        flightsManager.saveFlights();
        flightsManager.saveAirports();
        userManager.saveUsers();
        userManager.saveAdmins();
    }

}