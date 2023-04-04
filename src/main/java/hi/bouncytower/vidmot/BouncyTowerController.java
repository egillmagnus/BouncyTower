package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.scene.control.Label;


public class BouncyTowerController {

    public void onPlayButtonClick(){
        //TODO
    }
    @FXML
    protected void onHighScoreButtonClick() {
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
    public void initialize(){

    }
}