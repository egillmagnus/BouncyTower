package hi.bouncytower.vidmot;

public enum View {
    MAINMENU("mainMenu.fxml"),
    SETTINGS("settings.fxml"),
    CONTROLS("controls.fxml"),
    HIGHSCORES("highScores.fxml");

    private String fileName;

    View(String fileName) {this.fileName = fileName;}

    public String getFileName(){return fileName;}

}
