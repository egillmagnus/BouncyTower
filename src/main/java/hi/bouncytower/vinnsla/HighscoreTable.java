package hi.bouncytower.vinnsla;

import hi.bouncytower.vinnsla.HighscoreEntry;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Hjálparklasi fyrir vinnslu stigatöflunnar
 */
public class HighscoreTable implements Serializable {
    private List<HighscoreEntry> highscores;
    /**
     * Smiður fyrir HighscoreTable. Upphafstillir tóman lista af HighscoreEntry hlutum
     */
    public HighscoreTable(){
        highscores = new ArrayList<>();
    }
    /**
     * Bætir við nýju HighscoreEntry á lista. Skráir nafn leikmanns og stig
     * hanhs og raðar því síðan niður eftir fjölda stiga.
     *
     * @param playerName Nafn leikmanns sem á að bæta við skránna
     * @param score Stig leikmanns sem á að bæta við skránna
     */
    public void addHighscore(String playerName, int score){
        highscores.add(new HighscoreEntry(playerName, score));
        highscores.sort((e1, e2) -> Integer.compare(e2.getScore(), e1.getScore()));
    }

    /**
     * @return Listi af HighscoreEntry tilvikum sem inniheldur hæstu stig.
     */
    public List<HighscoreEntry> getHighscores(){
        return highscores;
    }
}
