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
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
        ViewSwitcher.setScene(scene);
        ViewSwitcher.switchTo(View.MAINMENU);
        stage.setTitle("BouncyTower!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    @Override
    public void stop() throws Exception {
        super.stop();
        BouncyTowerController.shutdown();
    }
}