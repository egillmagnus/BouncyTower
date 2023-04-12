package hi.bouncytower.vidmot;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

public class Bolti extends Circle {
    private double x;
    private double y;
    private double speedX;
    private double speedY;

    //private String imageFilePath = "C:/Users/toegi/Documents/V23/HBV201G/Stóra verkefnit/BouncyTower/src/main/Images/ball_stop.png";
    private String imageFilePath = getClass().getResource("/Images/ball_stop.png").toExternalForm();


    private double radius = 40;
    private double maxSpeedX = 5;

    private boolean aPalli;


    Bolti() {
        super(100, 100, 40);
        speedX = 0;
        speedY = 0;
    }

    /**
     * Ef bolti er á palli
     */
    public void updateBoltiAPall(double yHnitPalls) {
        setCenterY(yHnitPalls-radius);
        aPalli = true;
    }

    public void update(Canvas canvas, double gravity) {
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
        aPalli = false;
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
    public void moveLeft() {
        if(speedX - 1 >= -maxSpeedX) {
            if(speedX > 0 && speedX > 1.5) {
                speedX = Math.ceil(speedX)/2;
            } else{
                speedX -= 1;
            }
        }
    }

    public void moveRight() {
        if(speedX + 1 <= maxSpeedX) {
            if(speedX < 0 && speedX < -1.5) {
                speedX = Math.ceil(speedX)/2;
            } else{
                speedX += 1;
            }
        }
    }
}


