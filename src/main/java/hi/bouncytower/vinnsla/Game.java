package hi.bouncytower.vinnsla;

/**
 * Game klasinn heldur utan um stig og nafn leikmanns.
 *
 * @author Egill Magnússon
 */
public class Game {

    private int score;

    private String playerName;

    /**
     * Smiður fyrir Game klasann, núllstillir upphafsstig og leikmannsnafn.
     */
    public Game() {
        score = 0;
        playerName = "";
    }

    /**
     * Skilar stigum leikmanns.
     *
     * @return stigin sem leikmaður náði.
     */
    public int getScore() {
        return score;
    }

    /**
     * Vistar stig leikmanns.
     *
     * @param score stig sem eiga að vera geymd fyrir leikmann.
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Skilar nafni leikmanns.
     *
     * @return nafnið á leikmanninum.
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Vistar nafn leikmanns.
     *
     * @param playerName nafnið sem á að geyma fyrir leikmanninn.
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

}
