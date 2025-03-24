module group.proj {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;
    requires com.google.gson;
    requires java.desktop;
    requires fontawesomefx;

    opens group.proj to javafx.fxml, com.google.gson;
    exports group.proj;
    exports group.proj.Air_port;
    opens group.proj.Air_port to com.google.gson, javafx.fxml;
    exports group.proj.Booking_Sys;
    opens group.proj.Booking_Sys to com.google.gson, javafx.fxml;
    exports group.proj.User;
    opens group.proj.User to com.google.gson, javafx.fxml;


}