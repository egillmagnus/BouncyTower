package hi.bouncytower.vidmot;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Bounds;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.io.IOException;

public class Pallur extends Rectangle {


    String litur = "#999999";

    double yHnit;

    /**
     * Basic smiður bara
     * @param x
     * @param y
     * @param width
     */
    public Pallur(double x, double y, double width, double thykkt) {
        super(x, y, width, thykkt);
    }

    /**
     * uppfærir staðsetningu á pallinum.
     * @param speed
     */
    public void move(double speed) {
        setY(getY() - speed);
    }

    /**
     * teiknar pallinn á GraphicsContext
     * @param gc
     */
    public void draw(GraphicsContext gc, double rhaed, double yhaed) {
        gc.setFill(Paint.valueOf(litur));
        yHnit = getY() - rhaed + yhaed ;
        gc.fillRect(getX(), yHnit, getWidth(), getHeight());
    }


    public double getRettY() {
        return yHnit;
    }


    /**
     * gettari á Bounds fyrir intersects í Controller
     * @return Bounds á pallinum.
     */
    public Bounds getBounds() {
        return getBoundsInLocal();
    }
}
