package hi.bouncytower.vidmot;

/**
 * View tilgreinir fxml skrár fyrir hverja senu í Bouncy Tower.
 * Inniheldur heiti skrár fyrir hverja senu.
 *
 * @author Sturla Freyr Magnússon
 */
public enum View {
    MAINMENU("mainMenu.fxml"),
    SETTINGS("settings.fxml"),
    CONTROLS("controls.fxml"),
    HIGHSCORES("highScores.fxml"),
    GAME("game.fxml");

    private String fileName;

    /**
     * Smiður fyrir View.
     *
     * @param fileName Heiti fxml skrár fyrir viðmótið.
     */
    View(String fileName) {this.fileName = fileName;}

    /**
     * Sækir heiti fxml skrár fyrir viðmót.
     *
     * @return Heiti fxml skrár fyrir viðmót.
     */
    public String getFileName(){return fileName;}

}
