package hi.bouncytower.vidmot;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class HighscoreTable implements Serializable {
    private List<HighscoreEntry> highscores;
    public HighscoreTable(){
        highscores = new ArrayList<>();
    }
    public void addHighscore(String playerName, int score){
        highscores.add(new HighscoreEntry(playerName, score));
        highscores.sort((e1, e2) -> Integer.compare(e2.getScore(), e1.getScore()));
    }
    public List<HighscoreEntry> getHighscores(){
        return highscores;
    }
}
