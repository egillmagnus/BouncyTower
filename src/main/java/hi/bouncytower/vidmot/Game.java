package hi.bouncytower.vidmot;

public class Game {

    private int score;


    private String playerName;

    public Game() {
        score = 0;
        playerName = "";
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
