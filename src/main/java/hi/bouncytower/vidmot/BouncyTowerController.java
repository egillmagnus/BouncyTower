package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class BouncyTowerController {
    @FXML
    private Label welcomeText;

    public void onPlayButtonClick(){
        //TODO
    }
    @FXML
    protected void onHighScoreButtonClick() {
        welcomeText.setText("Welcome to JavaFX Application!");
        ViewSwitcher.switchTo(View.HIGHSCORES);
    }
    public void onControlsButtonClick(){
        ViewSwitcher.switchTo(View.CONTROLS);
    }
    public void onSettingsButtonClick(){
        ViewSwitcher.switchTo(View.SETTINGS);
    }
    public void onBackToMenuClick(){
        ViewSwitcher.switchTo(View.MAINMENU);
    }
}