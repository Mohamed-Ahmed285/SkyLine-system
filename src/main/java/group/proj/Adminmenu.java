package group.proj;

import group.proj.Air_port.Airport;
import group.proj.Air_port.Flight;
import group.proj.Air_port.FlightsManager;
import group.proj.User.Admin;
import group.proj.User.Passenger;
import group.proj.User.UserFileManger;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Adminmenu implements Initializable {

    private FXMLLoader fxmlLoader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private UserFileManger userManager;
    private FlightsManager flightsManager;
    private Admin Currentadmin;

    private boolean validTimeformat(String field){
        String pattern = "^([0-9]|0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$";
        Pattern compiledPattern = Pattern.compile(pattern);
        Matcher matcher = compiledPattern.matcher(field);
        return matcher.matches();
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
    private boolean isEmp(TextField field) {

        String input = field.getText();
        if (input.isEmpty()){
            return true;
        }
        return false;
    }
    String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
    String SSNregex = "^\\d{14}$";
    String Phoneregex = "^\\d{11}$";


    @FXML
    private Button Admins_btn;
    @FXML
    private Pane Airport_Menu;
    @FXML
    private TextField Admin_Password;
    @FXML
    private Button Airport_btn_more;
    @FXML
    private Button Admins_btn_more;
    @FXML
    private Button customers_btn_more;
    @FXML
    private Button flight_btn_more;
    @FXML
    private TextField Admin_username;
    @FXML
    private TextField Admin_Phonefield;
    @FXML
    private TextField Admin_emailfield;
    @FXML
    private TextField Admin_agefield;
    @FXML
    private ChoiceBox<String> Admin_genfield;
    @FXML
    private Label Admin_warn;
    @FXML
    private Label gen_label;


    @FXML
    private Button Airport_btn;

    @FXML
    private TextField arrivalAirport;

    @FXML
    ListView<Flight> flightListView;
    @FXML
    ListView<Airport> airportListView;

    @FXML
    private DatePicker arrivalDate;

    @FXML
    private TextField arrivalTime;
    @FXML
    private Label label1;

    @FXML
    private Label label2;

    @FXML
    private TextField departureAirport;

    @FXML
    private DatePicker departureDate;
    @FXML
    private TextField Admin_SSNfield;
    @FXML
    private TextField departureTime;
    @FXML
    private Button logout;
    @FXML
    private Pane Flights_Menu;
    @FXML
    private Pane customers_menu;
    @FXML
    private Pane Home_Menu;
    @FXML
    private Label admins_num;
    @FXML
    private Label flights_num;
    @FXML
    private Label users_num;
    @FXML
    private Label airports_num;
    @FXML
    private Button flight_btn;
    @FXML
    private Button  Dashborad_btn;
    @FXML
    private  Button customers_btn;
    private ObservableList<Flight> flightList;
    private ObservableList<Airport> AirportList;

    @FXML
    private Pane Admin_Menu;
    @FXML
    private TableView<Admin> adminTableView;
    @FXML
    private TableColumn<Admin, String> Admin_SSN;

    @FXML
    private TableColumn<Admin, Integer> Admin_age;

    @FXML
    private TableColumn<Admin, String> Admin_email;

    @FXML
    private TableColumn<Admin, String> Admin_gen;

    @FXML
    private TableColumn<Admin, Integer> Admin_id;

    @FXML
    private TableColumn<Admin, String> Admin_name;

    @FXML
    private TableColumn<Admin, String> Admin_phone;


    @FXML
    private TextField BuisnessClassPrice;

    @FXML
    private TextField FirstClassPrice;

    @FXML
    private TextField EconomeyClassPrice;

    @FXML
    private TextField searchBar;
    @FXML
    private TableView<Passenger> customerTableView;
    @FXML
    private TableColumn<Passenger, Integer> Pass_Id;
    @FXML
    private TableColumn<Passenger, String> Username;
    @FXML
    private TableColumn<Passenger, Integer> Pass;
    @FXML
    private TableColumn<Passenger, String> Gen;
    @FXML
    private TableColumn<Passenger, String> email;
    @FXML
    private TableColumn<Passenger, String> phone;
    @FXML
    private TableColumn<Passenger, Integer> age;
    @FXML
    private TableColumn<Passenger, String> ssn;

    @FXML
    private TextField Airport_cityfield;

    @FXML
    private TextField Airport_countryfield;

    @FXML
    private TextField Airport_namefield;

    private ObservableList<Passenger> passengers = FXCollections.observableArrayList();
    private ObservableList<Admin> admins  = FXCollections.observableArrayList();

    @FXML
    private Label Warn;
    @FXML
    private Label Warn1;
    @FXML
    private ChoiceBox<String> arr_arr_ch;

    @FXML
    private ChoiceBox<String> dep_arr_ch;



    // ListView to display flights
    private boolean isDouble(TextField field,String mesage) {
        try {
            double num = Double.parseDouble(field.getText());
            return true;
        }catch(NumberFormatException e){
            System.out.println("user enterd" + mesage);
            return false;
        }
    }
    public String capitalizeFirstLetter(String original) {
        if (original == null || original.length() == 0) {
            return original;
        }
        return original.substring(0, 1).toUpperCase() + original.substring(1);
    }
    public static String capitalizeWords(String input) {
        // split the input string into an array of words, stream the array
        // apply the capitalization transformation and join the words back
        return Arrays.stream(input.split("\\s"))
                .map(word -> Character.toTitleCase(word.charAt(0)) + word.substring(1))
                .collect(Collectors.joining(" "));
    }
    public void setUserManager(UserFileManger userManager) {
        this.userManager = userManager;
        if (userManager != null) {
            passengers.addAll(userManager.getPassengers());
            admins.addAll(userManager.getAdmins());
        }
    }
    public void setCurrentAdmin(Admin admin) {
        this.Currentadmin = admin;

    }
    public void setFlightsManager(FlightsManager flightsManager) {
        this.flightsManager = flightsManager;
        if (flightsManager != null){
            flightList = FXCollections.observableArrayList(flightsManager.getFlights());
            flightListView.setItems(flightList);
            AirportList = FXCollections.observableArrayList(flightsManager.getAirports());
            airportListView.setItems(AirportList);
        }
    }

    @FXML
    void to_login_screen(ActionEvent event) throws IOException {


        fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        root = fxmlLoader.load();
        //=============================
        loginMenuControllers controller = fxmlLoader.getController();
        controller.setUserManager(userManager);
        controller.setFlightsManager(flightsManager);
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();


    }
    @FXML
    void on_add_click(ActionEvent event) throws IOException{

         Warn.setTextFill(Color.RED);
         Warn.setText("");
         double f_price=0.0;
         double e_price=0.0;
         double b_price=0.0;
         String departureTime1 = departureTime.getText();
         String arrivalTime1 = arrivalTime.getText();

        if (departureDate.getValue().equals(LocalDate.now())){

        }else if (!(departureDate.getValue().isAfter(LocalDate.now() ))){
            Warn.setText("Change The Date of the Departure");
            return;
        }

        if (!(arrivalDate.getValue().equals(departureDate.getValue()))){
         if (!(arrivalDate.getValue().isAfter(departureDate.getValue()) )){
            Warn.setText("The Date of the Arrival has to be After the Departure");
            return;
        }
        }else if(departureTime.getText().equals(arrivalTime.getText())) {
            Warn.setText("The time Should be different");
            return;
        }
           if (dep_arr_ch.getValue()==null){
               Warn.setText("You Have to fill all of the fields");
               return;
           }
           if (arr_arr_ch.getValue()==null){
               Warn.setText("You Have to fill all of the fields");
               return;
           }
           if (arr_arr_ch.getValue().equals(dep_arr_ch.getValue())){
               Warn.setText("Change one of the airports");
               return;
           }

         String departureAirport1 = dep_arr_ch.getValue() ;
         String arrivalAirport1 = arr_arr_ch.getValue();
         String departureDate1 = String.valueOf(departureDate.getValue());
         String arrivalDate1 =String.valueOf(arrivalDate.getValue());



        if ( !arrivalDate1.isEmpty() && !departureAirport1.isEmpty() && !arrivalTime1.isEmpty() && !departureDate1.isEmpty() && !EconomeyClassPrice.getText().isEmpty() && !BuisnessClassPrice.getText().isEmpty() && !FirstClassPrice.getText().isEmpty() ) {

            AtomicReference<Airport> Arrival = new AtomicReference<>();
            AtomicReference<Airport> Dep = new AtomicReference<>();
            final boolean[] one = {false};
            final boolean[] two = {false};

            if (isDouble(FirstClassPrice,FirstClassPrice.getText())){

                f_price = Double.parseDouble(FirstClassPrice.getText());

            }else {
                Warn.setText("The Price should be Integer");
                return;
            }
            if (isDouble(BuisnessClassPrice,BuisnessClassPrice.getText())){

                b_price = Double.parseDouble(BuisnessClassPrice.getText());

            }else {
                Warn.setText("The Price should be Integer");
                return;
            }
            if (isDouble(EconomeyClassPrice,EconomeyClassPrice.getText())){

                e_price = Double.parseDouble(EconomeyClassPrice.getText());

            }else {
                Warn.setText("The Price should be Integer");
                return;
            }


            if (validTimeformat(departureTime1)){
                departureTime1 = departureTime.getText() ;
            }else{
                Warn.setText("Departure time format isn't Correct");
                return;
            }

            if (validTimeformat(arrivalTime1)){
                arrivalTime1 = arrivalTime.getText() ;
            }else{
                Warn.setText("Arrival time format isn't Correct");
                return;
            }
            flightsManager.getAirPorts().forEach(Airportsearch -> {
                if (Airportsearch.getAirPortName().equalsIgnoreCase(arrivalAirport1)){

                   Arrival.set(Airportsearch);
                     one[0] =true;
                }
            });
            flightsManager.getAirPorts().forEach(Airportsearch -> {
                if (Airportsearch.getAirPortName().equalsIgnoreCase(departureAirport1)){

                    Dep.set(Airportsearch);
                    two[0] = true;
                }
            });
            if (two[0]) {
                if (one[0]) {
                    Flight newflight = new Flight(flightsManager.getFlights(), Dep.get(), Arrival.get(), departureTime1, arrivalTime1, departureDate1, arrivalDate1,f_price,e_price,b_price);
                    flightsManager.addFlight(newflight);
                    System.out.println("flight added success");
                    Warn.setText("");
                    flightList.add(newflight);
                    dep_arr_ch.getSelectionModel().clearSelection();
                    arr_arr_ch.getSelectionModel().clearSelection();
                    departureTime.clear();
                    arrivalTime.clear();
                    FirstClassPrice.clear();
                    BuisnessClassPrice.clear();
                    EconomeyClassPrice.clear();
                    departureDate.getEditor().clear();
                    arrivalDate.getEditor().clear();
                } else {
                    System.out.println("airport not found");
                    Warn.setText("Arrival airport not found");
                    return;
                }
            } else {
                System.out.println("airport not found");
                Warn.setText("Departure airport not found");
                return;
            }
        }else {
            Warn.setText("You Have to fill all of the fields");
            return;
        }
    }

    @FXML
    void removeFlight(javafx.scene.input.MouseEvent event){
        Warn.setText("");
        int selectedID = flightListView.getSelectionModel().getSelectedIndex();
        flightsManager.getFlights().remove(selectedID);
        flightList.remove(selectedID);
    }

    @FXML
    void switches(ActionEvent event) {
        if (event.getSource() == flight_btn || event.getSource() == flight_btn_more ){

            Flights_Menu.setVisible(true);
            Home_Menu.setVisible(false);
            Airport_Menu.setVisible(false);
            customers_menu.setVisible(false);
            Admin_Menu.setVisible(false);

        }else if (event.getSource() == Dashborad_btn){

            Flights_Menu.setVisible(false);
            Home_Menu.setVisible(true);
            customers_menu.setVisible(false);
            Airport_Menu.setVisible(false);
            Admin_Menu.setVisible(false);


        }else if (event.getSource() == customers_btn  || event.getSource() == customers_btn_more){

            customers_menu.setVisible(true);
            Flights_Menu.setVisible(false);
            Home_Menu.setVisible(false);
            Airport_Menu.setVisible(false);
            Admin_Menu.setVisible(false);

        }else if (event.getSource() == Airport_btn || event.getSource() == Airport_btn_more){

            customers_menu.setVisible(false);
            Flights_Menu.setVisible(false);
            Home_Menu.setVisible(false);
            Airport_Menu.setVisible(true);
            Admin_Menu.setVisible(false);
        }else if (event.getSource() == Admins_btn || event.getSource() == Admins_btn_more){
            Admin_Menu.setVisible(true);
            customers_menu.setVisible(false);
            Flights_Menu.setVisible(false);
            Home_Menu.setVisible(false);
            Airport_Menu.setVisible(false);
        }
    }

    @FXML
    void to_profile(ActionEvent event) throws  IOException{

        fxmlLoader = new FXMLLoader(getClass().getResource("profileadmin.fxml"));
        root = fxmlLoader.load();
        //=============================
        ProfileAdmin controller = fxmlLoader.getController();
        controller.setUserManager(userManager,Currentadmin);
        controller.setFlightsManager(flightsManager);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,401 , 620);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();

    }

    @FXML
    void removeAirport(javafx.scene.input.MouseEvent event) {
        int selectedID = airportListView.getSelectionModel().getSelectedIndex();
        flightsManager.getAirPorts().remove(selectedID);
        AirportList.remove(selectedID);
    }

    @FXML
    void add_airport(ActionEvent event) {

        Warn1.setTextFill(Color.RED);
        String airportname = Airport_namefield.getText();
        String airportcity = Airport_cityfield.getText();
        String airportcountry = Airport_countryfield.getText();
        AtomicBoolean Exist = new AtomicBoolean(false);
        if (!airportname.isEmpty() && !airportcity.isEmpty() &&  !airportcountry.isEmpty()){

            flightsManager.getAirPorts().forEach(airport -> {
                if (airport.getAirPortName().equalsIgnoreCase(airportname)){
                    Exist.set(true);
                    Warn1.setText("This Airport Already Exists!");
                }
            });
           if (!Exist.get()){
               Airport airport = new Airport(flightsManager.getAirports(),capitalizeWords(airportname),capitalizeWords(airportcity) ,capitalizeWords(airportcountry));
               flightsManager.getAirPorts().add(airport);
               AirportList.add(airport);
               System.out.println("airport added success");
               Warn1.setText("");
               Airport_namefield.clear();
               Airport_cityfield.clear();
               Airport_countryfield.clear();
           }
        }else {
            Warn1.setText("You Have to fill all of the fields");
        }

    }

    @FXML
    void Add_admin(ActionEvent event) {

        Admin_warn.setTextFill(Color.RED);
        String userInput;
        String passwordInput;
        String Gender = "";
        String phone;
        int age;
        String ssn;
        String email;
        //-------------------------------------------

        userInput = Admin_username.getText();
        Gender = (String) Admin_genfield.getValue();
        passwordInput = Admin_Password.getText();
        email = Admin_emailfield.getText();
        phone = Admin_Phonefield.getText();
        ssn = Admin_SSNfield.getText();

        if (userInput.isEmpty()) {
            Admin_warn.setText("Please enter the Username.");
            return;
        }
        if (passwordInput.isEmpty()) {
            Admin_warn.setText("Please enter the Password.");
            return;

        }
        if (Gender == null) {
            Admin_warn.setText("Please enter the Gender.");
            return;
        }
        if (email.isEmpty()) {
            Admin_warn.setText("Please enter the Email.");
            return;
        }
        if (ssn.isEmpty()) {
            Admin_warn.setText("Please enter the SSN.");
            return;
        }
        if (phone.isEmpty()) {
            Admin_warn.setText("Please enter the Phone.");
            return;
        }
        if (isEmp(Admin_agefield)){
            Admin_warn.setText("please Enter the age ");
            return;
        }


        //=======================================================

        if (!(Pattern.matches(emailRegex, email))) {
            Admin_warn.setText("Invalid email address.");
            System.out.println("Invalid email address.");
            return;
            }
        if(!(Pattern.matches(Phoneregex, phone))) {
            Admin_warn.setText("Invalid Phone number. (Must be 11 digits long)");
            System.out.println("Invalid Phone number.");
            return;
        }
        if(!(Pattern.matches(SSNregex, ssn))) {
            Admin_warn.setText("Invalid SSN number. (Must be 14 digits long)");
            System.out.println("Invalid SSN number.");
            return;
        }
        if (isInt(Admin_agefield, Admin_agefield.getText())) {
            age = Integer.parseInt(Admin_agefield.getText());
            if (age<0){
                Admin_warn.setText("please the Age has to be a Positive number ");
                return;
            }
        }
        else {
            Admin_warn.setText("please the Age has to be a number ");
            return;
        }

        AtomicBoolean search = new AtomicBoolean(false);
        //check for passengers
        userManager.passengers.forEach(passenger -> {
            if (passenger.getUsername().equals(userInput)){
                search.set(true);
            }
        });
        //check for admins
        userManager.admins.forEach(admin -> {
            if (admin.getUsername().equals(userInput)){
                search.set(true);
            }
        });

        if (search.get()){
            Admin_warn.setText("This UserName Already Exists.");
            return;
        }else {

            Admin admin = new Admin(userInput,passwordInput ,age,Gender, phone,email , ssn ,userManager.getAdmins());
            userManager.addAdmin(admin);
            Admin_warn.setText("");
            adminTableView.getItems().add(admin);
            Admin_username.clear();
            Admin_Password.clear();
            Admin_Password.clear();
            Admin_emailfield.clear();
            Admin_Phonefield.clear();
            Admin_agefield.clear();
            Admin_SSNfield.clear();
            Admin_genfield.getSelectionModel().clearSelection();
            gen_label.setText("Gender");
            gen_label.setTextFill(Color.color(0.66274509803 ,0.66274509803,0.66274509803));
        }



    }
    @FXML
    public void remove_admin(javafx.scene.input.MouseEvent mouseEvent) {
        int selectedID = adminTableView.getSelectionModel().getSelectedIndex();
        userManager.getAdmins().remove(selectedID);
        adminTableView.getItems().remove(selectedID);
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        Admin_genfield.getItems().addAll("Male","Female");
        Admin_genfield.setOnAction(actionEvent -> {
            gen_label.setText("");
        });


            // Set up the columns using standard getter methods from the Flight class
            Pass_Id.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper<>(cellData.getValue().getPassngerId()));
            phone.setCellValueFactory(cellData ->
                    new ReadOnlyStringWrapper(cellData.getValue().getPhone()));
            Pass.setCellValueFactory(cellData ->
                    new ReadOnlyObjectWrapper(cellData.getValue().getNumOfBookings()));
            Username.setCellValueFactory(cellData ->
                    new ReadOnlyStringWrapper(cellData.getValue().getUsername()));
            ssn.setCellValueFactory(cellData ->
                    new ReadOnlyStringWrapper(cellData.getValue().getSsn()));
            email.setCellValueFactory(cellData ->
                    new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
            Gen.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getGender()));
            age.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getAge()));

        //===============================================================================

            Admin_id.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getAdminID()));
            Admin_phone.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getPhone()));
            Admin_email.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getEmail()));
            Admin_name.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getUsername()));
            Admin_gen.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getGender()));
            Admin_SSN.setCellValueFactory(cellData ->
                new ReadOnlyStringWrapper(cellData.getValue().getSsn()));
            Admin_age.setCellValueFactory(cellData ->
                new ReadOnlyObjectWrapper<>(cellData.getValue().getAge()));

            adminTableView.setItems(admins);


        // Create a FilteredList to filter the flights based on the search query
            FilteredList<Passenger> filteredcustomers = new FilteredList<>(passengers, customer -> true);
            // Bind the search bar to filter the flights dynamically
            searchBar.textProperty().addListener((observable, oldValue, newValue) -> {
                filteredcustomers.setPredicate(customer -> {
                    if (newValue == null || newValue.isEmpty()) {
                        return true; // If no text in search bar, show all users
                    }
                    String lowerCaseFilter = newValue.toLowerCase();
                    return customer.getUsername().toLowerCase().contains(lowerCaseFilter) ||
                            customer.getEmail().toLowerCase().contains(lowerCaseFilter) ||
                            String.valueOf(customer.getPassngerId()).contains(lowerCaseFilter) ||
                            String.valueOf(customer.getSsn()).contains(lowerCaseFilter)   ;
                });
            });
            // Wrap the filtered list in a SortedList to make it sortable
            customerTableView.setItems(filteredcustomers);



        Platform.runLater(() ->{

            flightsManager.getAirPorts().forEach(airport -> {


                    arr_arr_ch.getItems().add(airport.getAirPortName());
                    dep_arr_ch.getItems().add(airport.getAirPortName());



            });
            dep_arr_ch.setOnAction(actionEvent -> {
                label1.setText("");
                label1.setTextFill(Color.color(0 ,0,0));
            });
            arr_arr_ch.setOnAction(actionEvent -> {
                label2.setText("");
                label2.setTextFill(Color.color(0 ,0,0));
            });

                double frame=25;
            final Timeline[] adtimeline = {new Timeline()};// Use an array to allow modification inside the lambda
            final int[] i = {0};
            adtimeline[0].getKeyFrames().add(
                    new KeyFrame(Duration.millis(frame), event -> {
                        admins_num.setText(Integer.toString(i[0]));
                        if ( i[0] >= userManager.getnumeofadmins()) {
                            adtimeline[0].stop(); // Stop the animation once target is reached
                        }
                        i[0]++;
                    })
            );
            adtimeline[0].setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely until stopped
            adtimeline[0].play();

            final Timeline[] fltimeline = {new Timeline()};// Use an array to allow modification inside the lambda
            final int[] i1 = {0};
            fltimeline[0].getKeyFrames().add(
                    new KeyFrame(Duration.millis(frame), event -> {
                        flights_num.setText(Integer.toString(i1[0]));
                        if ( i1[0] >= flightsManager.getnumberofFlights()) {
                            fltimeline[0].stop(); // Stop the animation once target is reached
                        }
                        i1[0]++;
                    })
            );

            fltimeline[0].setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely until stopped
            fltimeline[0].play();

            final Timeline[] ustimeline = {new Timeline()};// Use an array to allow modification inside the lambda
            final int[] i2 = {0};
            ustimeline[0].getKeyFrames().add(
                    new KeyFrame(Duration.millis(frame), event -> {
                        users_num.setText(Integer.toString(i2[0]));
                        if ( i2[0] >= userManager.getnumeofusers()) {
                            ustimeline[0].stop(); // Stop the animation once target is reached
                        }
                        i2[0]++;
                    })
            );

            ustimeline[0].setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely until stopped
            ustimeline[0].play();

            final Timeline[] artimeline = {new Timeline()};// Use an array to allow modification inside the lambda
            final int[] i3 = {0};
            artimeline[0].getKeyFrames().add(
                    new KeyFrame(Duration.millis(frame), event -> {
                        airports_num.setText(Integer.toString(i3[0]));
                        if ( i3[0] >= flightsManager.getnumberofairports()) {
                            artimeline[0].stop(); // Stop the animation once target is reached
                        }
                        i3[0]++;
                    })
            );

            artimeline[0].setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely until stopped
            artimeline[0].play();


        });
    }


}
