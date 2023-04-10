package hi.bouncytower.vidmot;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class HighscoreController implements Initializable {
    @FXML
    private ListView<String> highscoreListView;
    private static final String HIGHSCORE_FILE = "highscores.ser";
    private HighscoreTable highscoreTable;
    private ObservableList<String> highscoreItems = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("HighscoreController initialize() called");
        loadHighscoreTable();
        updateHighscoreListView();
        addHighscore("Test Player", 123);
    }

    private void loadHighscoreTable() {
        System.out.println("HighscoreController loadHighscoreTable() called");
        File highscoreFile = new File(HIGHSCORE_FILE);
        if (highscoreFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(highscoreFile))) {
                highscoreTable = (HighscoreTable) ois.readObject();
                if (highscoreTable == null) {
                    throw new InvalidObjectException("Deserialized highscoreTable is null");
                }
                System.out.println("Loaded highscoreTable from file: " + highscoreTable);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println("Error loading highscoreTable from file. Creating a new one.");
                highscoreTable = new HighscoreTable();
                populateDefaultHighscores();
            }
        } else {
            highscoreTable = new HighscoreTable();
            System.out.println("Created new highscoreTable: " + highscoreTable);
            populateDefaultHighscores();
        }
    }

    public void updateHighscoreListView() {
        List<HighscoreEntry> highscores = highscoreTable.getHighscores();
        System.out.println("Highscores: " + highscores);
        highscoreItems.clear();

        for (HighscoreEntry entry : highscores) {
            String item = String.format("%s: %d", entry.getPlayerName(), entry.getScore());
            highscoreItems.add(item);
        }
        highscoreListView.setItems(highscoreItems);
    }

    private void populateDefaultHighscores(){
        highscoreTable.addHighscore("King of the Tower",25000);
        highscoreTable.addHighscore("The Developer", 18000);
        highscoreTable.addHighscore("Average Joe", 10000);
        highscoreTable.addHighscore("The Developer's Dad", 7000);
        highscoreTable.addHighscore("Some Nerd", 1000);
        highscoreTable.addHighscore("The Worst Player", 10);
    }
    private void saveHighscoreTable() {
        File highscoreFile = new File(HIGHSCORE_FILE);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(highscoreFile))) {
            oos.writeObject(highscoreTable);
            System.out.println("HighscoreTable saved to file: " + highscoreFile.getAbsolutePath());
        } catch (IOException e) {
            System.err.println("Error saving highscoreTable to file.");
            e.printStackTrace();
        }
    }
    public void addHighscore(String playerName, int score) {
        highscoreTable.addHighscore(playerName, score);
        saveHighscoreTable();
        updateHighscoreListView();
    }
    public void onBackToMenuClick(){
        ViewSwitcher.switchTo(View.MAINMENU);
    }
    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            System.out.println("Vinstri");
            event.consume();
        } else if (event.getCode() == KeyCode.RIGHT) {
            System.out.println("Hægri");
            event.consume();
        }
        System.out.println("Key pressed");
    }
}
