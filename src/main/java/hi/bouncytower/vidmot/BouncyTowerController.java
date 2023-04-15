package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;


public class BouncyTowerController {

    @FXML
    private Canvas fxBackgroundCanvas;

    private Image bakgrunnur = new Image(getClass().getResource("/Images/main_background.png").toExternalForm());

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
        GraphicsContext gc = fxBackgroundCanvas.getGraphicsContext2D();
        gc.drawImage(bakgrunnur,0, 0, 700, 800);
    }
}