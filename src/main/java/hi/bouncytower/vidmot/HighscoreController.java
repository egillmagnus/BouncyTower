package hi.bouncytower.vidmot;

import hi.bouncytower.vinnsla.Game;
import hi.bouncytower.vinnsla.HighscoreEntry;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.io.*;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

/**
 * HighscoreController stjórnar viðmóti fyrir stigatöflu senuna í Bouncy Tower.
 * Sýnir stigatöfluna, bætir við stigum og vistar stigatöfluna.
 *
 * @author Sturla Freyr Magnússon
 */
public class HighscoreController implements Initializable, ControllerWithModel {
    @FXML
    private ListView<String> highscoreListView;
    private static final String HIGHSCORE_FILE = "highscores.ser";
    private HighscoreTable highscoreTable;

    private Game model;
    private ObservableList<String> highscoreItems = FXCollections.observableArrayList();

    /**
     * Upphafsstillingar fyrir stigatöfluskjá.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("HighscoreController initialize() called");
        loadHighscoreTable();
        updateHighscoreListView();
    }

    /**
     * Hleður stigatöflu úr skrá.
     */
    private void loadHighscoreTable() {
        File highscoreFile = new File(HIGHSCORE_FILE);
        if (highscoreFile.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(highscoreFile))) {
                highscoreTable = (HighscoreTable) ois.readObject();
                if (highscoreTable == null) {
                    throw new InvalidObjectException("Deserialized highscoreTable is null");
                }
            } catch (IOException | ClassNotFoundException e) {
                highscoreTable = new HighscoreTable();
                populateDefaultHighscores();
            }
        } else {
            highscoreTable = new HighscoreTable();
            populateDefaultHighscores();
        }
    }

    /**
     * Uppfærir ListView með stigatöflu.
     */
    public void updateHighscoreListView() {
        List<HighscoreEntry> highscores = highscoreTable.getHighscores();
        highscoreItems.clear();

        for (HighscoreEntry entry : highscores) {
            String item = String.format("%s: %d", entry.getPlayerName(), entry.getScore());
            highscoreItems.add(item);
        }
        highscoreListView.setItems(highscoreItems);
    }

    /**
     * Býr til sjálfgefna stigatöflu.
     */
    private void populateDefaultHighscores(){
        highscoreTable.addHighscore("King of the Tower",25000);
        highscoreTable.addHighscore("The Developer", 18000);
        highscoreTable.addHighscore("Average Joe", 10000);
        highscoreTable.addHighscore("The Developer's Dad", 7000);
        highscoreTable.addHighscore("Some Nerd", 1000);
        highscoreTable.addHighscore("The Worst Player", 10);
    }

    /**
     * Vistar stigatöfluna í skrá.
     */
    private void saveHighscoreTable() {
        File highscoreFile = new File(HIGHSCORE_FILE);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(highscoreFile))) {
            oos.writeObject(highscoreTable);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Setur módelið fyrir stigatöfluskjáinn.
     * @param model Leikjarmódelið sem stigatöfluskjárinn vinnur með.
     */
    public void setModel(Game model) {
        this.model = model;
    }

    /**
     * Bætir nafni og stigum við stigatöfluna og vistar þau.
     */
    public void addHighscore() {
        highscoreTable.addHighscore(model.getPlayerName(), model.getScore());
        saveHighscoreTable();
        updateHighscoreListView();
    }

    /**
     * Atburður sem gerist þegar smellt er á "Til baka" takkann.
     * Skiptir yfir í aðalvalmyndina.
     */
    public void onBackToMenuClick(){
        ViewSwitcher.switchTo(View.MAINMENU);
    }
}
