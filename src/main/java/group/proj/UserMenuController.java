package group.proj;
import group.proj.Air_port.*;
import group.proj.User.Passenger;
import group.proj.User.UserFileManger;
import javafx.application.Platform;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class UserMenuController implements Initializable {

    private FXMLLoader fxmlLoader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private UserFileManger userManager;
    private FlightsManager flightsManager;
    @FXML
    private Label Flight1_buisn;

    @FXML
    private Label Flight1_date;

    @FXML
    private Label Flight1_date2;

    @FXML
    private Label Flight1_eco;

    @FXML
    private Label Flight1_first;

    @FXML
    private TextField Flight1_num;

    @FXML
    private Label Flight1_time;

    @FXML
    private Label Flight1_time2;

    @FXML
    private Label Flight2_buis;

    @FXML
    private Label Flight2_date;

    @FXML
    private Label Flight2_date2;

    @FXML
    private Label Flight2_eco;

    @FXML
    private Label Flight2_first;

    @FXML
    private TextField Flight2_num;

    @FXML
    private Label Flight2_time;

    @FXML
    private Label Flight2_time2;

    @FXML
    private BorderPane Compare_Menu;
    @FXML
    private BorderPane Home_menu;
    @FXML
    private BorderPane Search_Menu;
    @FXML
    private Button CM_Home_button;
    @FXML
    private Button CM_search_button;
    @FXML
    private Button HM_compare_button;
    @FXML
    private Button HM_search_button;
    @FXML
    private Button SM_Home_Button;
    @FXML
    private Button SM_compare_button;
    @FXML
    private Button History1;
    @FXML
    private Button History2;
    @FXML
    private VBox LayoutBox;
    @FXML
    private Button History3;
    @FXML
    private Button search_btn;
    @FXML
    private Label Compare_warn;
    @FXML
    private Button home_btn;
    @FXML
    private Button compare_btn;
    @FXML
    private BorderPane History;
    @FXML
    private TextField searchBar;
    @FXML
    private TableView<Flight> flightTableView;
    @FXML
    private TableColumn<Flight, Integer> flightIdColumn;
    @FXML
    private TableColumn<Flight, String> departureAirportColumn;
    @FXML
    private TableColumn<Flight, String> arrivalAirportColumn;
    @FXML
    private TableColumn<Flight, String> departureDateColumn;
    @FXML
    private TableColumn<Flight, String> arrivalDateColumn;
    @FXML
    private TableColumn<Flight, Double> p_buisness;
    @FXML
    private TableColumn<Flight, Double> p_economey;
    @FXML
    private TableColumn<Flight, Double> p_first;

    @FXML
    private TableColumn<Flight, Integer> availableSeatsColumn;
    private ObservableList<Flight> flightList = FXCollections.observableArrayList();
    private Passenger Currentuser;
    public void setUserManager(UserFileManger userManager , Passenger CurrentUser) {
        this.userManager = userManager;
        this.Currentuser=CurrentUser;
    }
    public void setFlightsManager(FlightsManager flightsManager) {
        this.flightsManager = flightsManager;
        if (flightsManager != null) {
            flightsManager.getFlights().forEach(flight -> {
                if (flight.isFlights_available()){
                flightList.add(flight);
                }
            });

        }
    }
    private boolean isInt(TextField field,String mesage) {
        try {
            int num = Integer.parseInt(field.getText());
            return true;
        }catch(NumberFormatException e){
            System.out.println("user enterd" + mesage);
            return false;
        }
    }
    //==============================================
    @FXML
    void clear(ActionEvent event) {

        Flight1_num.setText("");
        Flight1_buisn.setText("");
        Flight1_eco.setText("");
        Flight1_first.setText("");
        Flight1_date.setText("");
        Flight1_date2.setText("");
        Flight1_time.setText("");
        Flight1_time2.setText("");

        Flight2_num.setText("");
        Flight2_buis.setText("");
        Flight2_eco.setText("");
        Flight2_first.setText("");
        Flight2_date.setText("");
        Flight2_date2.setText("");
        Flight2_time.setText("");
        Flight2_time2.setText("");



    }
    @FXML
    void compare(ActionEvent event) {

        Compare_warn.setTextFill(Color.RED);
    AtomicReference<Flight> flight1 = new AtomicReference<>();
    AtomicReference<Flight> flight2 = new AtomicReference<>();
    if(Flight1_num.getText().isEmpty()){
        Compare_warn.setText("Please Enter Flight 1 Number");
        return;
    }
    if(Flight2_num.getText().isEmpty()){

            Compare_warn.setText("Please Enter Flight 2 Number");
            return;
    }
    if (!(isInt(Flight1_num,Flight1_num.getText()))) {

        Compare_warn.setText("Invalid Flight 1 Input");
        return;
    }
    if (!(isInt(Flight2_num,Flight2_num.getText()))) {

            Compare_warn.setText("Invalid Flight 2 Input");
            return;
    }

    int fli_1_id = Integer.parseInt(Flight1_num.getText());
    int fli_2_id = Integer.parseInt(Flight2_num.getText());
    AtomicBoolean found1 = new AtomicBoolean(false);
    AtomicBoolean found2 = new AtomicBoolean(false);
    flightsManager.getFlights().forEach(flight -> {
        if (fli_1_id == flight.getFlightId()){
            flight1.set(flight);
            found1.set(true);
        }
        if (fli_2_id == flight.getFlightId()){
            flight2.set(flight);
            found2.set(true);
        }
    });

    if (found1.get()){
        if (found2.get()){

                Flight1_time.setText(flight1.get().getDepartureTime());
                Flight1_time2.setText(flight1.get().getArrivalTime());
                Flight1_date.setText(flight1.get().getDepartureDate());
                Flight1_date2.setText(flight1.get().getArrivalDate());
                Flight1_first.setText(String.valueOf(flight1.get().getFirstclass_price()) + "$");
                Flight1_eco.setText(String.valueOf(flight1.get().getEco_price()) + "$");
                Flight1_buisn.setText(String.valueOf(flight1.get().getBusiness_price()) + "$");
                Flight2_time.setText(flight2.get().getDepartureTime());
                Flight2_time2.setText(flight2.get().getArrivalTime());
                Flight2_date.setText(flight2.get().getDepartureDate());
                Flight2_date2.setText(flight2.get().getArrivalDate());
                Flight2_first.setText(String.valueOf(flight2.get().getFirstclass_price()) + "$");
                Flight2_eco.setText(String.valueOf(flight2.get().getEco_price()) + "$");
                Flight2_buis.setText(String.valueOf(flight2.get().getBusiness_price()) + "$");
                Compare_warn.setText("");
        }else {
            Compare_warn.setText("Flight 2 Not found");
            return;
        }
    }else {
        Compare_warn.setText("Flight 1 Not found");
        return;
    }

    }
    @FXML
    void to_login_screen(ActionEvent event) throws IOException{

        fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = fxmlLoader.load();
        //=============================
        loginMenuControllers controller = fxmlLoader.getController();
        controller.setUserManager(userManager);
        controller.setFlightsManager(flightsManager);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    @FXML
    void to_booking_screen(ActionEvent event) throws IOException{

        fxmlLoader = new FXMLLoader(getClass().getResource("Bookingmenu.fxml"));
        root = fxmlLoader.load();
        //=============================
        BookingmenuController controller = fxmlLoader.getController();
        controller.setUserManager(userManager,Currentuser);
        controller.setFlightsManager(flightsManager);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    @FXML
    void to_profile(ActionEvent event) throws  IOException{

        fxmlLoader = new FXMLLoader(getClass().getResource("profileuser.fxml"));
        root = fxmlLoader.load();
        //=============================
        ProfileController controller = fxmlLoader.getController();
        controller.setUserManager(userManager,Currentuser);
        controller.setFlightsManager(flightsManager);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,401 , 620);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }
    @FXML
    void switches(ActionEvent event) {

        if (event.getSource() == CM_Home_button || event.getSource() == SM_Home_Button || event.getSource() == home_btn) {

            Home_menu.setVisible(true);
            Compare_Menu.setVisible(false);
            Search_Menu.setVisible(false);
            History.setVisible(false);

        }else if (event.getSource() == HM_compare_button || event.getSource() == SM_compare_button || event.getSource() == compare_btn) {
            Home_menu.setVisible(false);
            Compare_Menu.setVisible(true);
            Search_Menu.setVisible(false);
            History.setVisible(false);

        }else if (event.getSource() == HM_search_button || event.getSource() == CM_search_button || event.getSource() == search_btn){

            Home_menu.setVisible(false);
            Compare_Menu.setVisible(false);
            Search_Menu.setVisible(true);
            History.setVisible(false);

        }else if (event.getSource() == History1 || event.getSource() == History2 || event.getSource() == History3 ){

            History.setVisible(true);
            Home_menu.setVisible(false);
            Compare_Menu.setVisible(false);
            Search_Menu.setVisible(false);

        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Platform.runLater(
                () ->
                {
                    if (Currentuser.getBookings() != null){
                        Currentuser.getBookings().forEach(booking -> {

                            try {

                                FXMLLoader fxmlLoader1 = new FXMLLoader();
                                fxmlLoader1.setLocation(getClass().getResource( "Card.fxml"));
                                AnchorPane cardbox = fxmlLoader1.load();
                                CardController cardController = fxmlLoader1.getController();
                                cardController.setdata(booking);
                                LayoutBox.getChildren().add(cardbox);

                            } catch (IOException e) {

                                throw new RuntimeException(e);

                            }


                        });


                    }
                }
        );


        // Set up the columns using standard getter methods from the Flight class
        flightIdColumn.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getFlightId()));
        departureAirportColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getDepartureAirport().getCity()));
        arrivalAirportColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getArrivalAirport().getCity()));
        departureDateColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getDepartureDate()));
        arrivalDateColumn.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getArrivalDate()));
        p_first.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getFirstclass_price()));
        p_buisness.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getBusiness_price()));
        p_economey.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getEco_price()));

        // Create a FilteredList to filter the flights based on the search query
        FilteredList<Flight> filteredFlights = new FilteredList<>(flightList, flight -> true);

        // Bind the search bar to filter the flights dynamically
        searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredFlights.setPredicate(flight -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true; // If no text in search bar, show all flights
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return flight.getDepartureAirport().getCity().toLowerCase().contains(lowerCaseFilter) ||
                        flight.getArrivalAirport().getCity().toLowerCase().contains(lowerCaseFilter) ||
                        String.valueOf(flight.getFlightId()).contains(lowerCaseFilter);
            });
        });

        // Wrap the filtered list in a SortedList to make it sortable
        flightTableView.setItems(filteredFlights);
    }

}
