package hi.bouncytower.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public enum View {
    MAINMENU("mainMenu.fxml"),
    SETTINGS("settings.fxml"),
    CONTROLS("controls.fxml"),
    HIGHSCORES("highScores.fxml"),
    GAME("game.fxml");

    private String fileName;

    View(String fileName) {this.fileName = fileName;}


    public String getFileName(){return fileName;}

}
