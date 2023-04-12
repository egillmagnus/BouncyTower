package hi.bouncytower.vidmot;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class GameController{

    @FXML
    private Canvas canvas;
    private Bolti bolti;
    private List<Pallur> pallar = new ArrayList<>();

    private double gravity = 0.5;

    private GraphicsContext gc;
    public void initialize(){
        System.out.println("GameController initialize() called");
        bolti = new Bolti();
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        pallar.add(new Pallur(25,500, 650, 20 ));

        gameLoop();
    }

    private void gameLoop(){
        new AnimationTimer() {
            long lastUpdateTime = -1;

            @Override
            public void handle(long now) {

                if (lastUpdateTime == -1) {
                    lastUpdateTime = now;
                    return;
                }
                lastUpdateTime = now;

                bolti.update(canvas, gravity);

                for (Pallur pallur : pallar) {
                    if (boltiAPall(bolti, pallur)) {
                        bolti.updateBoltiAPall(pallur.getY());
                    }
                }

                teiknaBolta();
                teiknaPalla();



            }
        }.start();
    }

    private void teiknaBolta() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.fillOval(bolti.getCenterX() - bolti.getRadius(), bolti.getCenterY() - bolti.getRadius(), bolti.getRadius() * 2, bolti.getRadius() * 2);
        gc.drawImage(bolti.getImage(), bolti.getCenterX(), bolti.getCenterY(), bolti.getRadius(), bolti.getRadius());
    }

    public void teiknaPalla() {
        for (Pallur pallur : pallar) {
            pallur.draw(gc);
        }
    }

    public boolean boltiAPall(Bolti ball, Pallur pallur) {
        if(ball.getCenterX() > pallur.getX()-ball.getRadius() && ball.getCenterX() < pallur.getX()+pallur.getWidth()+ball.getRadius()) {
            if(ball.getCenterY() > pallur.getY()-ball.getRadius() && ball.getCenterY() < pallur.getY()+pallur.getHeight()+ball.getRadius()) {
                return true;
            }
        }
        return false;
    }

    @FXML
    public void handleKeyPress(KeyEvent event) {
        if (event.getCode() == KeyCode.LEFT) {
            bolti.moveLeft();
            event.consume();
        } else if (event.getCode() == KeyCode.RIGHT) {
            bolti.moveRight();
            event.consume();
        } else if (event.getCode() == KeyCode.UP) {
            bolti.jump();
        }
    }

}
