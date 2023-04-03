package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BouncyTowerController {
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
    }
}