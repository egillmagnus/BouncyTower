module hi.bouncytower.bouncytower {
    requires javafx.controls;
    requires javafx.fxml;


    opens hi.bouncytower.vidmot to javafx.fxml;
    exports hi.bouncytower.vidmot;
    exports hi.bouncytower.vinnsla;
    opens hi.bouncytower.vinnsla to javafx.fxml;
}