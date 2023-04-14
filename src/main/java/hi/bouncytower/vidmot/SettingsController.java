package hi.bouncytower.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class SettingsController implements Initializable {

    @FXML
    private ChoiceBox<String> veljaBolta;

    @FXML
    private ImageView redBoltiImage;

    @FXML
    private ImageView fotboltiImage;

    private String[] boltar = {"Rauður bolti", "Fótbolti"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        veljaBolta.getItems().addAll(boltar);
        veljaBolta.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            switch (newValue) {
                case "Rauður bolti":
                    redBoltiImage.setVisible(true);
                    fotboltiImage.setVisible(false);
                    break;
                case "Fótbolti":
                    fotboltiImage.setVisible(true);
                    redBoltiImage.setVisible(false);
                    break;
                default:
                    redBoltiImage.setVisible(false);
                    fotboltiImage.setVisible(false);
                    break;
            }
        });
    }
    @FXML
    void handleKeyPress(KeyEvent event) {

    }


    public void onBackToMenuClick(){
        ViewSwitcher.switchTo(View.MAINMENU);
    }

}
