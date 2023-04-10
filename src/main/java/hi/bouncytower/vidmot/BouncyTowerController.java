package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class BouncyTowerController {

    public void onPlayButtonClick(){
        ViewSwitcher.switchTo(View.GAME);
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
    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            System.out.println("Vinstri");
            event.consume();
        } else if (event.getCode() == KeyCode.RIGHT) {
            System.out.println("Hægri");
            event.consume();
        }
        System.out.println("Key pressed");
    }
}