package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class ControlsController {
    @FXML
    GridPane contentGrid;
    public void initialize() {
        HBox keyRow1 = createKeyRow("A", "←");
        Label actionDescription1 = createLabel("Færa boltann til vinstri.");

        HBox keyRow2 = createKeyRow("D", "→");
        Label actionDescription2 = createLabel("Færa boltann til hægri");

        HBox keyRow3 = createKeyRow("W", "↑");
        Label spaceBar = createKey("␣");
        spaceBar.setMinSize(80, 40);
        spaceBar.setMaxSize(80, 40);
        VBox keyRow3Wrapper = new VBox(5);
        keyRow3Wrapper.getChildren().addAll(keyRow3, spaceBar);

        Label actionDescription3 = createLabel("Láta boltann hoppa");

        VBox keysAndDescriptions = new VBox(10);
        keysAndDescriptions.setAlignment(Pos.TOP_LEFT);
        keysAndDescriptions.getChildren().addAll(
                createRow(keyRow1, actionDescription1),
                createRow(keyRow2, actionDescription2),
                createRow(keyRow3Wrapper, actionDescription3)
        );

        contentGrid.add(keysAndDescriptions, 1, 1);
    }

    private HBox createKeyRow(String... keys) {
        HBox keyRow = new HBox(10);
        keyRow.setAlignment(Pos.CENTER);
        for (String keyText : keys) {
            Label key = createKey(keyText);
            keyRow.getChildren().add(key);
        }
        return keyRow;
    }

    private Label createKey(String keyText) {
        Label keyLabel = new Label(keyText);
        keyLabel.getStyleClass().add("key");
        keyLabel.setAlignment(Pos.CENTER);
        keyLabel.setMinSize(40, 40);
        keyLabel.setMaxSize(40, 40);
        return keyLabel;
    }

    private Label createLabel(String labelText) {
        Label label = new Label(labelText);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }

    private HBox createRow(Node keyRow, Label description) {
        HBox row = new HBox(20);
        row.setAlignment(Pos.CENTER_LEFT); // Change this line
        row.getChildren().addAll(keyRow, description);
        return row;
    }

    public void onBackToMenuClick(){
        ViewSwitcher.switchTo(View.MAINMENU);
    }
}
