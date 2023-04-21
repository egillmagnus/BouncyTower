package hi.bouncytower.vinnsla;

import javafx.scene.canvas.Canvas;
import javafx.scene.image.Image;
import javafx.scene.shape.Circle;

/**
 * Bolti klasinn er hluti af vinnslupakkanum og sér um að stjórna boltanum í BouncyTower.
 * Hann sér um hraða, stefnu, stærð og hreyfingu boltans. Klasinn inniheldur líka aðferðir til að
 * stýra boltanum til vinstri eða hægri, hoppa og hreyfa bakgrunninn miðað við boltann.
 *
 * @author Egill Magnússon
 */
public class Bolti extends Circle {
    private double x;
    private double y;
    private double speedX;
    private double speedY;
    private String imageFilePath = getClass().getResource("/Images/ball_stop.png").toExternalForm();

    private boolean moveBackground;

    private double radius = 40;
    private double maxSpeedX = 5;

    private boolean aPalli;

    private double raunHaed;

    /**
     * Smiður fyrir Bolti-klasann. Skilgreinir upphafshraða, staðsetningu
     * og radius fyrir boltann.
     */
    public Bolti() {
        super(100, 0, 40);
        speedX = 0;
        speedY = 0;
        raunHaed = 0;
    }

    /**
     * Setur bolta á pall og stoppar bolta frá því að detta niður
     *
     * @param pallur Pallur tilvik sem boltinn á að fylgja
     * @param canvas Canvas sem boltinn er teiknaður á
     */
    public void updateBoltiAPall(Pallur pallur, Canvas canvas) {
        raunHaed = pallur.getY() - radius;
        setCenterY(pallur.getRettY() - radius);
        speedY = 0;
        aPalli = true;
    }

    /**
     * Skilar raunhæð boltans.
     *
     * @return raunhæð boltans
     */
    public double getHaed() {
        return raunHaed;
    }

    /**
     * Sækir hraða boltans á Y-ás.
     *
     * @return hraði boltans Y-ás
     */
    public double getSpeedY() {
        return speedY;
    }

    /**
     * Uppfærir stöðu og hraða boltans reiknar hvar hann á að vera miðað við þyngdaraflið og skjástærð
     * teiknar boltann á nýjum stað.
     *
     * @param canvas skjárinn sem boltinn er teiknaður á
     * @param gravity þyngdaraflið sem áhrif hafa á boltann
     */
    public void update(Canvas canvas, double gravity) {
        speedY = Math.min(speedY, 25);
        double oldCenter = getCenterY();
        double maxHeadASkja = 150;
        moveBackground = false;
        if(oldCenter + speedY < maxHeadASkja) {
            setCenterY(maxHeadASkja);
            moveBackground = true;
        } else if(oldCenter + speedY > canvas.getHeight() - maxHeadASkja) {
            setCenterY(canvas.getHeight() - maxHeadASkja);
            moveBackground = true;
        }
        else {
            setCenterY(oldCenter + speedY);
        }
        raunHaed+=speedY;

        if (getCenterX() <=90 ) {
            setCenterX(90);
            speedX = -speedX;
        } else if (getCenterX() > canvas.getWidth() - getRadius()-90) {
            setCenterX(canvas.getWidth() - getRadius()-90);
            speedX = -speedX;
        }
        setCenterX(getCenterX() + speedX);
        speedY += gravity;
        aPalli = false;
    }

    /**
     * Skilar mynd af boltanum út frá slóð svo hægt sé að teikna hann á skjáinn.
     *
     * @return mynd af boltanum
     */
    public Image getImage() {
        return new Image(imageFilePath);
    }

    /**
     * Færir boltann til vinstri ef hraði hans er ekki nú þegar
     * á hámarki (-maxSpeedX).
     */
    public void moveLeft() {
        if(speedX - 1 >= -maxSpeedX) {
            if(speedX > 0 && speedX > 1.5) {
                speedX = Math.ceil(speedX)/2;
            } else{
                speedX -= 1;
            }
        }
    }

    /**
     * Skilar boolean gildi sem segir hvort bakgrunnur eigi að hreyfa sig
     *
     * @return 'true' ef bakgrunnurinn á að hreyfa sig, annars 'false'
     */
    public boolean moveBackground() {
        return moveBackground;
    }

    /**
     * Lætur boltann hoppa með því að breyta hraða hanns á Y-ás.
     */
    public void jump() {
        setCenterY(getCenterY() - 1);
        raunHaed-=1;
        speedY = -14-(speedX*speedX)/3;
    }

    /**
     * Færir boltann til hægri ef hraði hans er ekki nú þegar
     * á hámarki (maxSpeedX).
     */
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


