package group.proj;
import group.proj.Air_port.FlightsManager;
import group.proj.User.Admin;
import group.proj.User.Passenger;
import group.proj.User.UserFileManger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.regex.Pattern;


public class loginMenuControllers implements Initializable {

    private UserFileManger userManager;
    private FlightsManager flightsManager;
    private FXMLLoader fxmlLoader;
    private Parent root;
    private Stage stage;
    private Scene scene;
    private Passenger Currentuser;
    private Admin Currentadmin;
    @FXML
    private AnchorPane login_form;
    @FXML
    private TextField signup_phone;
    @FXML
    private TextField signup_ssn;
    @FXML
    private TextField signup_age;
    @FXML
    private TextField signup_email;
    @FXML
    private Label warn;
    @FXML
    private TextField login_password;
    @FXML
    private Hyperlink login_registerhere;
    @FXML
    private CheckBox login_showpassword;
    @FXML
    private TextField login_username;
    @FXML
    private AnchorPane main_form;
    @FXML
    private AnchorPane signup_form;
    @FXML
    private TextField signup_password;
    @FXML
    private CheckBox signup_showpassword;
    @FXML
    private Button signup_signupbtn;
    @FXML
    private TextField signup_username;
    @FXML
    private Hyperlink signup_loginhere;
    @FXML
    private PasswordField login_passfield;
    @FXML
    private PasswordField signup_passfield;
    @FXML
    private Label welcomeText;
    @FXML
    private ChoiceBox<String> login_selectgender;
    @FXML
    private Label login_selectusergender;

//========================================

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

    public void switchform(ActionEvent actionEvent) {
        if (actionEvent.getSource() == login_registerhere) {

            login_form.setVisible(false);
            signup_form.setVisible(true);

        } else if (actionEvent.getSource() == signup_loginhere) {
            login_form.setVisible(true);
            signup_form.setVisible(false);
        }
    }
    public void show_password() {
        if (login_showpassword.isSelected()) {

            login_password.setText(login_passfield.getText());
            login_passfield.setText("");
            login_passfield.setVisible(false);
            login_password.setVisible(true);

        }
        else {

            login_passfield.setText(login_password.getText());
            login_password.setText("");
            login_passfield.setVisible(true);
            login_password.setVisible(false);
        }

        if (signup_showpassword.isSelected()){
            signup_password.setText(signup_passfield.getText());
            signup_passfield.setText("");
            signup_passfield.setVisible(false);
            signup_password.setVisible(true);
        }
        else {
            signup_passfield.setText(signup_password.getText());
            signup_password.setText("");
            signup_passfield.setVisible(true);
            signup_password.setVisible(false);
        }
    }
    public void setUserManager(UserFileManger userManager) {
        this.userManager = userManager;
    }
    public void setUserManager(UserFileManger userManager , Passenger CurrentUser) {
        this.userManager = userManager;
        this.Currentuser=CurrentUser;
    }
    public void setFlightsManager(FlightsManager flightsManager) {
        this.flightsManager = flightsManager;
    }
    public void showAlert() {

        Alert alert = new Alert(Alert.AlertType.NONE,"You Have Signed up successfully!",ButtonType.OK);
        alert.setTitle("Login successfully...");
        alert.setContentText("You Have Signed up successfully!");
        alert.showAndWait();

    }

    //========================================
    @FXML
    protected void onLoginButtonClick(ActionEvent event) {

        String userInput;
        String passwordInput;
        userInput = login_username.getText();
        System.out.println("Input from TextField: [" + userInput + "]");

        if (login_showpassword.isSelected()) {
             passwordInput = login_password.getText();
            System.out.println("Input from TextField: [" + passwordInput + "]");
        }
        else {

             passwordInput = login_passfield.getText();
            System.out.println("Input from TextField: [" + passwordInput + "]");
        }

        if (userInput.isEmpty()) {
            welcomeText.setText("Please enter your Passenger name.");
            return;
        }
        if (passwordInput.isEmpty()) {
            welcomeText.setText("Please enter your Password.");
            return;
        }
        AtomicBoolean usertmp= new AtomicBoolean(false);
        AtomicBoolean usertmp2 = new AtomicBoolean(false);
        AtomicBoolean admintmp= new AtomicBoolean(false);
        AtomicBoolean admintmp2 = new AtomicBoolean(false);

        //check for passenger
            userManager.passengers.forEach(passenger -> {
                if (passenger.getUsername().equals(userInput)){
                    usertmp.set(true);
                    if (passenger.getPassword().equals(passwordInput))
                    {
                        Currentuser = passenger;
                        usertmp2.set(true);
                    }
                }
            });

        //check for admins
            userManager.admins.forEach(admin -> {
                if (admin.getUsername().equals(userInput)){
                    admintmp.set(true);
                    if (admin.getPassword().equals(passwordInput))
                    {
                        Currentadmin = admin;
                        admintmp2.set(true);
                    }
                }
            });


        if (admintmp.get() || usertmp.get()) {
            if (admintmp2.get()){
            try {
                to_adminmenu(event);
            } catch (IOException e) {
                welcomeText.setText("Error loading menu.");
                e.printStackTrace();}

            }else if (usertmp2.get()){
                    try {
                        to_menu(event);
                    } catch (IOException e) {
                        welcomeText.setText("Error loading menu.");
                        e.printStackTrace();
                }

            }else
            {
                welcomeText.setText("Incorrect Password");
            }
        } else {
            welcomeText.setText("UserName Not found.");
        }

    }

    @FXML
    protected void onSignButtonClick(ActionEvent event) {

        String userInput;
        String passwordInput;
        String Gender = "";
        String phone;
        int age;
        String ssn;
        String email;

//===============================================
        userInput = signup_username.getText();
        email = signup_email.getText();
        Gender = login_selectgender.getValue();
        phone = signup_phone.getText();
        ssn = signup_ssn.getText();

        //pass
        if (signup_showpassword.isSelected()) {
            passwordInput = signup_password.getText();
            System.out.println("Input from TextField: [" + passwordInput + "]");
        } else {
            passwordInput = signup_passfield.getText();
            System.out.println("Input from TextField: [" + passwordInput + "]");
        }
//=============================================
        if (userInput.isEmpty()) {
            warn.setText("Please enter your Username.");
            return;
        }
        if (passwordInput.isEmpty()) {
            warn.setText("Please enter your Password.");
            return;

        }
        if (Gender == null) {
            warn.setText("Please enter your Gender.");
            return;
        }
        if (email.isEmpty()) {
            warn.setText("Please enter your email.");
            return;
        }
        if (isEmp(signup_age)){
            warn.setText("please Enter ur age ");
            return;
        }
        if (isEmp(signup_phone)) {
            warn.setText("please Enter ur phone ");
            return;
        }
        if (isEmp(signup_ssn)){
            warn.setText("please Enter ur Ssn ");
            return;
        }

//===============================================

        if(!(Pattern.matches(emailRegex, email))) {
            warn.setText("Invalid email address.");
            System.out.println("Invalid email address.");
            return;
        }
        if(!(Pattern.matches(Phoneregex, phone))) {
            warn.setText("Invalid Phone number. (Must be 11 digits long)");
            System.out.println("Invalid Phone number.");
            return;
        }
        if(!(Pattern.matches(SSNregex, ssn))) {
            warn.setText("Invalid SSN number. (Must be 14 digits long)");
            System.out.println("Invalid SSN number.");
            return;
        }
        if (isInt(signup_age, signup_age.getText())) {
            age = Integer.parseInt(signup_age.getText());
            if (age<0){
                warn.setText("please the Age has to be a Positive number ");
                return;
            }
        }
        else {
            warn.setText("please the Age has to be a number ");
            return;
        }

//===============================================

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
            warn.setText("This UserName Already Exists.");
            return;
        }else{
            Currentuser = new Passenger(userInput,passwordInput ,age,Gender, phone,email , ssn ,userManager.getnumeofusers());
            userManager.addUser(Currentuser);
            signup_ssn.setText("");
            signup_phone.setText("");
            signup_age.setText("");
            signup_username.setText("");
            signup_passfield.setText("");
            signup_password.setText("");
            signup_password.setText("");
            signup_email.setText("");
            login_selectgender.getSelectionModel().clearSelection();
            login_selectusergender.setText("Gender");
            login_selectusergender.setTextFill(Color.color(0.66274509803 ,0.66274509803,0.66274509803));
            showAlert();
            login_form.setVisible(true);
            signup_form.setVisible(false);
        }

    }
    //=====================================


    public void to_menu(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("menu.fxml"));
        root = fxmlLoader.load();
        UserMenuController controller = fxmlLoader.getController();
        Font.loadFont(getClass().getResourceAsStream("/BalooTamma2-SemiBold.ttf"), 18);
        controller.setUserManager(userManager,Currentuser);
        controller.setFlightsManager(flightsManager);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1200 , 620);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    public void to_adminmenu(ActionEvent event) throws IOException {

        fxmlLoader = new FXMLLoader(getClass().getResource("AdminMenu.fxml"));
        root = fxmlLoader.load();
        Adminmenu adminmenucontroller = fxmlLoader.getController();
        Font.loadFont(getClass().getResourceAsStream("/BalooTamma2-SemiBold.ttf"), 18);
        adminmenucontroller.setUserManager(userManager);
        adminmenucontroller.setCurrentAdmin(Currentadmin);
        adminmenucontroller.setFlightsManager(flightsManager);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root,1200 , 620);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        login_selectgender.getItems().addAll("Male","Female");
        login_selectgender.setOnAction(actionEvent -> {
            String selected = login_selectgender.getValue();
            login_selectusergender.setText(selected);
            login_selectusergender.setTextFill(Color.color(0 ,0,0));
           });
    }
}