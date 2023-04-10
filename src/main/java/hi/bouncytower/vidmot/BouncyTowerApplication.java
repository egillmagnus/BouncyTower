package hi.bouncytower.vidmot;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class BouncyTowerApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(BouncyTowerApplication.class.getResource("mainMenu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 700, 800);
        scene.getStylesheets().add(getClass().getResource("/stylesheets/bouncytower.css").toExternalForm());
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MAINMENU);
        stage.setTitle("BouncyTower!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

}