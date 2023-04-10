package hi.bouncytower.vidmot;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Bolti extends Circle {
    private double x;
    private double y;
    private double speedX;
    private double speedY;

    private String imageFilePath = "src/main/Images/ball_stop.png";


    private double radius = 20;
    private double maxSpeedX = 5;


    Bolti() {
        x = 0;
        y = 0;
        speedX = 0;
        speedY = 0;
    }

    /**
     * Ef bolti er á palli
     */
    public void updateBoltiAPall(double yHnitPalls) {
        setCenterX(getCenterX() + speedX);
    }

    public void updateNidur(Canvas canvas, double gravity) {
        speedY = Math.min(speedY, 25);
        setCenterY(getCenterY() + speedY);

        if (getCenterX() < getRadius()) {
            setCenterX(getRadius());
            speedX = -speedX;
        } else if (getCenterX() > canvas.getWidth() - getRadius()) {
            setCenterX(canvas.getWidth() - getRadius());
            speedX = -speedX;
        }
        setCenterX(getCenterX() + speedX);
        speedY += gravity;
    }

    /**
     * Setur y hraðann í 0
     */
    public void stopSpeedY(){
        speedY = 0;
    }

    public Image getImage() {
        return new Image(imageFilePath);
    }
}
