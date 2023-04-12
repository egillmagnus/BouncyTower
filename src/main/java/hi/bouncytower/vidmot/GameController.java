package hi.bouncytower.vidmot;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;

import java.lang.reflect.Array;
import java.util.*;

public class GameController{

    @FXML
    private Canvas canvas;
    private Bolti bolti;
    private List<Pallur> pallar = new ArrayList<>();

    private int fpalla;


    private boolean jumping = false;

    private double gravity = 0.5;

    private GraphicsContext gc;
    private static Map<KeyCode,Stefna> takkaMap = new HashMap<>();
    public void initialize(){
        System.out.println("GameController initialize() called");
        bolti = new Bolti();
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        pallar.add(new Pallur(25,500, 650, 20));
        fpalla = 1;

        canvas.sceneProperty().addListener(new ChangeListener<Scene>() {
            @Override
            public void changed(ObservableValue<? extends Scene> observableValue, Scene oldScene, Scene newScene) {
                if (newScene != null) {
                    orvatakkar(newScene);
                    gameLoop();
                }
            }
        });
    }
    public void orvatakkar(Scene s){
        takkaMap.put(KeyCode.LEFT, Stefna.VINSTRI);
        takkaMap.put(KeyCode.RIGHT, Stefna.HAEGRI);
        takkaMap.put(KeyCode.DOWN, Stefna.NIDUR);
        s.addEventFilter(KeyEvent.ANY,
                event -> {
                    handleKeyPress(event);
                });
        /*s.setOnKeyReleased(event ->{
            leikbord.setStefna(takkaMap.get(KeyCode.DOWN));
        });*/
    };

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
                boolean apallinuna = false;
                if(bolti.getSpeedY()>=0) {
                    for (Pallur pallur : pallar) {
                        if (boltiAPall(bolti, pallur)) {
                            bolti.updateBoltiAPall(pallur, canvas);
                            apallinuna = true;
                            if(jumping){
                                bolti.jump();
                                jumping = false;
                            }
                        }
                    }
                }
                if(!apallinuna) {
                    bolti.update(canvas, gravity);
                }


                System.out.println(bolti.getCenterY()+", "+bolti.getHaed());
                addPallar();

                gc.setFill(Color.WHITE);
                gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());

                teiknaPalla();
                teiknaBolta();


            }
        }.start();
    }

    private void addPallar() {
        if(-fpalla*100 < bolti.getHaed() - bolti.getCenterY() + 800) {
            System.out.println("Palli bætt við");
            fpalla++;
            Random random = new Random();
            double platformX = random.nextDouble() * (canvas.getWidth() - 100);
            pallar.add(new Pallur(platformX, (-fpalla*100)+600, Math.max(75, random.nextDouble() * 250 ) , 20 ));
        }
    }

    private void teiknaBolta() {
        gc.drawImage(bolti.getImage(), bolti.getCenterX(), bolti.getCenterY(), bolti.getRadius(), bolti.getRadius());
    }

    public void teiknaPalla() {
        for (Pallur pallur : pallar) {
            pallur.draw(gc, bolti.getHaed(), bolti.getCenterY());
        }
    }

    public boolean boltiAPall(Bolti ball, Pallur pallur) {
        if(ball.getCenterX() > pallur.getX()-ball.getRadius() && ball.getCenterX() < pallur.getX()+pallur.getWidth()) {
            if(ball.getHaed() > pallur.getY()-ball.getRadius() && ball.getHaed() < pallur.getY()+pallur.getHeight()) {
                System.out.println("Bolti a pall "+ ball.getHaed()+ ", "+pallur.getY());
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
            jumping = true;
        }
    }

}
