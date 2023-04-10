package hi.bouncytower.vidmot;

import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
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
        bolti = new Bolti();
        gc = canvas.getGraphicsContext2D();
        pallar.add(new Pallur(50,60, 50, 10 ));
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

                bolti.updateNidur(canvas, gravity);

                for (Pallur pallur : pallar) {
                    if (boltiAPall(bolti, pallur)) {
                        bolti.updateBoltiAPall(pallur.getY());
                        lastUpdateTime = now;
                    }
                }

                teiknaBolta();



            }
        }.start();
    }

    private void teiknaBolta() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        gc.drawImage(bolti.getImage(), bolti.getCenterX(), bolti.getCenterY(), bolti.getRadius(), bolti.getRadius());
    }

    public boolean boltiAPall(Bolti ball, Pallur pallur) {
        if(ball.getCenterX() > pallur.getX()-ball.getRadius() && ball.getCenterX() < pallur.getX()+pallur.getWidth()+ball.getRadius()) {
            if(ball.getCenterY() > pallur.getY()-ball.getRadius()-2 && ball.getCenterY() < pallur.getY()+pallur.getHeight()+ball.getRadius()+2) {
                return true;
            }
        }
        return false;
    }
}
