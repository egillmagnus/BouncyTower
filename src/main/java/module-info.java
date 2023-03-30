module hi.bouncytower.bouncytower {
    requires javafx.controls;
    requires javafx.fxml;


    opens hi.bouncytower.bouncytower to javafx.fxml;
    exports hi.bouncytower.bouncytower;
}