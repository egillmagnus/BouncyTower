package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * Controller sem stjórnar viðmóti fyrir senu sem sýnir stýringarnar í Bouncy Tower.
 * Skilgreinir hluti og atburði sem gerast í stýringasenunni.
 *
 * @author Sturla Freyr Magnússon
 */
public class ControlsController {
    @FXML
    GridPane contentGrid;
    /**
     * Upphafsstillingar fyrir Controls senu. Býr til efni sem skal sýna notanda
     */
    public void initialize() {
        HBox keyRow1 = createKeyRow("A", "←");
        Label actionDescription1 = createLabel("Færa boltann til vinstri.");

        HBox keyRow2 = createKeyRow("D", "→");
        Label actionDescription2 = createLabel("Færa boltann til hægri");

        HBox keyRow3 = createKeyRow("W", "↑");
        Label spaceBar = createKey("␣");
        spaceBar.setMinSize(90, 40);
        spaceBar.setMaxSize(90, 40);
        VBox keyRow3Wrapper = new VBox(5);
        keyRow3Wrapper.getChildren().addAll(keyRow3, spaceBar);
        Label actionDescription3 = createLabel("Láta boltann hoppa");

        HBox keyRow4 = createKeyRow("P");
        HBox keyRow4Wrapper = new HBox();
        keyRow4Wrapper.setAlignment(Pos.CENTER);
        keyRow4Wrapper.setMinWidth(90);
        keyRow4Wrapper.getChildren().add(keyRow4);

        Label actionDescription4 = createLabel("Pása leikinn");

        VBox keysAndDescriptions = new VBox(10);
        keysAndDescriptions.setAlignment(Pos.TOP_LEFT);
        keysAndDescriptions.getChildren().addAll(
                createRow(keyRow1, actionDescription1),
                createRow(keyRow2, actionDescription2),
                createRow(keyRow3Wrapper, actionDescription3),
                createRow(keyRow4Wrapper, actionDescription4)
        );

        contentGrid.add(keysAndDescriptions, 1, 1);
    }
    /**
     * Býr til HBox með tökkum út frá gefnum strengjum.
     *
     * @param keys Strengir sem lýsa tökkum sem á að bæta við í HBox.
     * @return HBox með tökkum út frá gefnum strengjum.
     */
    private HBox createKeyRow(String... keys) {
        HBox keyRow = new HBox(10);
        keyRow.setAlignment(Pos.CENTER);
        for (String keyText : keys) {
            Label key = createKey(keyText);
            keyRow.getChildren().add(key);
        }
        return keyRow;
    }

    /**
     * Hjálparfall sem býr til takka út frá gefnum streng.
     *
     * @param keyText Strengur sem lýsir takka.
     * @return Takki út frá gefnum streng.
     */
    private Label createKey(String keyText) {
        Label keyLabel = new Label(keyText);
        keyLabel.getStyleClass().add("key");
        keyLabel.setAlignment(Pos.CENTER);
        keyLabel.setMinSize(40, 40);
        keyLabel.setMaxSize(40, 40);
        return keyLabel;
    }

    /**
     * Hjálparfall til að setja upp label sem lýsir virkni takka
     *
     * @param labelText Strengur sem lýsir tökkum.
     * @return Label út frá gefnum streng.
     */
    private Label createLabel(String labelText) {
        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }

    /**
     * Býr til wrapper úr HBox með tökkum og lýsingu.
     *
     * @param keyRow Takkaröð til að bæta við HBox.
     * @param description Lýsing til að bæta við HBox.
     * @return HBox með takkaröð og lýsingu.
     */
    private HBox createRow(Node keyRow, Label description) {
        HBox row = new HBox(20);
        row.setAlignment(Pos.CENTER_LEFT); // Change this line
        row.getChildren().addAll(keyRow, description);
        return row;
    }

    /**
     * Atburður sem gerist þegar smellt er á "Til baka" takkann.
     * Skiptir yfir í aðalvalmyndina.
     */
    public void onBackToMenuClick(){
        ViewSwitcher.switchTo(View.MAINMENU);
    }
}
