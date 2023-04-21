package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * BouncyTowerController stjórnar aðalvalmyndarviðmótinu í Bouncy Tower.
 * Klasinn sér um að hlaða myndum og stjórna viðmótsatviki.
 *
 * @author Sturla Freyr Magnússon
 * @author Egill Magnússon
 */
public class BouncyTowerController {

    @FXML
    private Canvas fxBackgroundCanvas;

    private Image bakgrunnur = new Image(getClass().getResource("/Images/main_background.png").toExternalForm());

    /**
     * Meðhöndlun á 'Play' takkanum.
     */
    public void onPlayButtonClick(){
        ViewSwitcher.switchTo(View.GAME);
    }
    /**
     * Meðhöndlun á 'HighScore' takkanum.
     */
    @FXML
    protected void onHighScoreButtonClick() {
        ViewSwitcher.switchTo(View.HIGHSCORES);
    }
    /**
     * Meðhöndlun á 'Controls' takkanum.
     */
    public void onControlsButtonClick(){
        ViewSwitcher.switchTo(View.CONTROLS);
    }
    /**
     * Meðhöndlun á 'Settings' takkanum.
     */
    public void onSettingsButtonClick(){
        ViewSwitcher.switchTo(View.SETTINGS);
    }
    /**
     * Upphafstillir aðalvalmyndarviðmót BouncyTower.
     * Myndin fyrir bakgrunninn hlaðin og teiknuð á skjáinn.
     */
    public void initialize(){
        GraphicsContext gc = fxBackgroundCanvas.getGraphicsContext2D();
        gc.drawImage(bakgrunnur,0, 0, 700, 800);
    }
}