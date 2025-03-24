package group.proj;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

public class LoginMenu extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 330, 550);
        stage.setResizable(false);
        Image icon = new Image("icon.png");
        stage.setMinWidth(340);
        stage.setMinHeight(580);
        stage.setTitle("Flight Management system");
        stage.getIcons().add(icon);
        stage.setScene(scene);
        stage.show();

    }
}