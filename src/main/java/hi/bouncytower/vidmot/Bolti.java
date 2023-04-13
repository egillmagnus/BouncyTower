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

    private double raunHaed;


    Bolti() {
        super(100, 0, 40);
        speedX = 0;
        speedY = 0;
        raunHaed = 0;
    }

    /**
     * Ef bolti er á palli
     */
    public void updateBoltiAPall(Pallur pallur, Canvas canvas) {
        raunHaed = pallur.getY() - radius;
        setCenterY(pallur.getRettY() - radius);
        speedY = 0;
        aPalli = true;
    }

    public double getHaed() {
        return raunHaed;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void update(Canvas canvas, double gravity) {
        speedY = Math.min(speedY, 25);
        double oldCenter = getCenterY();
        double maxHeadASkja = 150;
        if(oldCenter + speedY < maxHeadASkja) {
            setCenterY(maxHeadASkja);
        } else if(oldCenter + speedY > canvas.getHeight() - maxHeadASkja) {
            setCenterY(canvas.getHeight() - maxHeadASkja);
        }
        else {
            setCenterY(oldCenter + speedY);
        }
        raunHaed+=speedY;

        if (getCenterX() <=0 ) {
            setCenterX(0);
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


    public void jump() {
        setCenterY(getCenterY() - 1);
        raunHaed-=1;
        speedY = -14-(speedX*speedX)/3;
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


