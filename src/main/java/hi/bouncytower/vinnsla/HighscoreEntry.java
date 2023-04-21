package hi.bouncytower.vinnsla;

import java.io.Serializable;

/**
 * HighscoreEntry klasinn geymir upplýsingar um eina færslu í stigatöflu.
 * @author Sturla Freyr Magnússon
 */
public class HighscoreEntry implements Serializable {
    private String playerName;
    private int score;

    /**
     * Smiður fyrir HighscoreEntry klasann.
     *
     * @param playerName Nafn leikmanns.
     * @param score Stig leikmanns.
     */
    public HighscoreEntry(String playerName, int score){
        this.playerName = playerName;
        this.score = score;
    }

    /**
     * Skilar nafni leikmanns.
     *
     * @return Nafnið á leikmanninum.
     */
    public String getPlayerName(){
        return playerName;
    }

    /**
     * Skilar stigum leikmanns.
     *
     * @return Stigin sem leikmaður hefur náð.
     */
    public int getScore(){
        return score;
    }

    /**
     * Skilar hástigsfærslu hlutnum sem streng.
     *
     * @return Strengur sem sýnir nafn leikmanns og stig.
     */
    @Override
    public String toString() {
        return String.format("%s: %d", playerName, score);
    }
}
