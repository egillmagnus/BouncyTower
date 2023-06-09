package hi.bouncytower.vidmot;

import hi.bouncytower.vinnsla.*;
import javafx.animation.AnimationTimer;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.*;

import static hi.bouncytower.vidmot.View.HIGHSCORES;
import static hi.bouncytower.vidmot.View.MAINMENU;

/**
 * GameController stjórnar viðmótinu og aðferðum leikjarlykkjunni,
 * svo sem leikjastjórn, teikna leikjaelement og atburðavaka.
 *
 * @author Egill Magnússon
 */
public class GameController implements ControllerWithModel {

    @FXML
    public Label fxEnterNameLabel;
    @FXML
    public TextField fxNameTextField;
    @FXML
    public Button fxEnterNameContinueButton;
    @FXML
    public VBox fxEnterNameVBox;
    @FXML
    private Canvas canvas;
    @FXML
    private Label fxScoreCounter;
    @FXML
    private Label fxPauseLabel;
    @FXML
    private Button fxResumeButton;
    @FXML
    private Button fxBackToMenuButton;
    @FXML
    private Label fxGameOverLabel;
    @FXML
    private Button fxPlayAgainButton;
    @FXML
    private Button fxAddHighscoreButton;
    @FXML
    private Button fxGameOverBackToMenuButton;
    @FXML
    private VBox fxPauseMenuVBox;
    @FXML
    private VBox fxGameOverVBox;
    @FXML
    private Text fxNameLengthNotification;

    private Background background;
    private Bolti bolti;
    private List<Pallur> pallar;

    private double maxHaed = 0;

    private double currentmax = 0;

    private int fpalla;

    private boolean isRunning = false;

    private Boolean gameOver;

    private double sidastaHaedBolta = 0;

    private boolean jumping = false;


    private long jumpTime = 0;

    private double gravity = 0.5;

    private AnimationTimer animationTimer;

    private Game model;


    private long lastUpdateTime = -1;
    private GraphicsContext gc;

    /**
     * Upphafsstillingar fyrir GameController. Setur upp leikjaelement, bakgrunn
     * og byrjar leikjalúppuna.
     */
    public void initialize(){
        System.out.println("GameController initialize() called");
        bolti = new Bolti();
        model = new Game();
        gameOver= false;
        background = new Background();
        maxHaed = 0;
        setVisabilityGameOver(false);
        canvas.setFocusTraversable(true);
        gc = canvas.getGraphicsContext2D();
        pallar = new ArrayList<>();
        pallar.add(new Pallur(25,40, 650, 20));
        fpalla = 1;
        isRunning = true;
        gameLoop();
    }
    /**
     * Atburðavakinn fyrir hnappinn fxEnterNameContinueButton.
     * Sækir nafn og skiptir yfir á HIGHSCORES-skjáinn.
     */
    public void fxEnterNameContinueClickedHandler() {
        String name = fxNameTextField.getText();
        if(name.length() < 1) {
            fxNameLengthNotification.setVisible(true);
        } else {
            model.setPlayerName(name);
            ViewSwitcher.switchTo(HIGHSCORES, model);
        }
    }

    /**
     * Vistar módelið.
     *
     * @param model Leikjamódelið sem á að nota.
     */
    public void setModel(Game model) {
        this.model = model;
    }

    /**
     * Leikjalykkja sem sér um að uppfæra og teikna element í leiknum.
     */
    private void gameLoop(){
        animationTimer = new AnimationTimer() {

            @Override
            public void handle(long now) {

                if (lastUpdateTime == -1) {
                    lastUpdateTime = now;
                    return;
                }

                lastUpdateTime = now;
                if(lastUpdateTime - jumpTime > 32026200) {
                    jumping = false;
                }

                bolti.update(canvas, gravity);
                if(bolti.getSpeedY()>=0) {
                    for (Pallur pallur : pallar) {
                        if (boltiAPall(bolti, pallur)) {
                            bolti.updateBoltiAPall(pallur, canvas);
                            if(jumping){
                                bolti.jump();
                                jumping = false;
                            }
                        }
                    }
                }

                fxScoreCounter.setText(" Score: "+ (int)Math.ceil(-maxHaed));
                if(bolti.getHaed()<maxHaed) {
                    maxHaed = bolti.getHaed();
                }
                if(bolti.getHaed()-maxHaed>850) {
                    stop();
                    gameover();
                }

                addPallar();

                teiknabakgrunn();
                pallar.removeIf(pallur -> pallur.getY()- bolti.getHaed()>800);

                teiknaPalla();
                teiknaBolta();
                if(bolti.getSpeedY() <= 0) {
                    currentmax = 0;
                }

                if(bolti.getHaed() < currentmax) {
                    currentmax = bolti.getHaed();
                }

                if(gameOver || !isRunning) {
                    teiknaOverlay();
                    animationTimer.stop();
                }
            }
        };
        animationTimer.start();
    }

    /**
     * Lýkur leik og sýnir lokaskjá með valmöguleikum.
     */
    public void gameover(){
        teiknaOverlay();
        model.setScore((int)(-maxHaed));
        setVisabilityGameOver(true);
        gameOver=true;
    }

    /**
     * Stýrir sýnileika lokaskjás.
     *
     * @param visable Satt ef lokaskjár á að vera sýnilegur, annars ósatt.
     */
    public void setVisabilityGameOver(boolean visable) {
        fxPlayAgainButton.setVisible(visable);
        fxGameOverLabel.setVisible(visable);
        fxAddHighscoreButton.setVisible(visable);
        fxGameOverBackToMenuButton.setVisible(visable);
        fxGameOverLabel.setMouseTransparent(!visable);
        fxPlayAgainButton.setMouseTransparent(!visable);
        fxAddHighscoreButton.setMouseTransparent(!visable);
        fxGameOverBackToMenuButton.setMouseTransparent(!visable);
        fxGameOverVBox.setMouseTransparent(!visable);
        fxGameOverVBox.setVisible(visable);
    }

    /**
     * Atburðavakinn fyrir hnappinn fxPlayAgainButton.
     * Endurræsir leikinn og felur lokaskjáinn.
     */
    public void fxPlayAgainButtonClickedHandler() {
        setVisabilityGameOver(false);
        initialize();
    }

    /**
     * Stýrir sýnileika pásuskjás.
     *
     * @param visable Satt ef leikur er í pásu, annars ósatt.
     */
    public void setVisabilityPauseMenu(boolean visable) {
        fxBackToMenuButton.setVisible(visable);
        fxResumeButton.setVisible(visable);
        fxPauseLabel.setVisible(visable);
        fxBackToMenuButton.setMouseTransparent(!visable);
        fxResumeButton.setMouseTransparent(!visable);
        fxPauseLabel.setMouseTransparent(!visable);
        fxPauseMenuVBox.setVisible(visable);
        fxPauseMenuVBox.setMouseTransparent(!visable);
    }
    /**
     * Stýrir sýnileika nafnaskráningar.
     *
     * @param visable Satt ef nafnaskráningarskjár á að vera sýnilegur, annars ósatt.
     */
    public void setVisabilityEnterName(boolean visable) {
        fxEnterNameVBox.setVisible(visable);
        fxEnterNameVBox.setMouseTransparent(!visable);
        fxEnterNameContinueButton.setVisible(visable);
        fxEnterNameContinueButton.setMouseTransparent(!visable);
        fxEnterNameLabel.setVisible(visable);
        fxEnterNameLabel.setMouseTransparent(!visable);
        fxNameTextField.setVisible(visable);
        fxNameTextField.setMouseTransparent(!visable);
    }

    /**
     * Teiknar mynd bakgrunn á skjánum.
     */
    public void teiknabakgrunn() {
        background.draw(gc, bolti);
    }

    /**
     * Atburðavakinn fyrir hnappinn fxResumeButton.
     * Endurræsir eða stöðvar leikinn.
     */
    public void fxResumeClickedHandler() {
        pauseGame();
    }

    /**
     * Atburðavakinn fyrir hnappinn fxBackToMenuButton.
     * Skiptir yfir á aðalskjáinn.
     */
    public void fxBackToMenuClickedHandler() {
        ViewSwitcher.switchTo(MAINMENU);
    }

    /**
     * Atburðavakinn fyrir hnappinn fxAddHighscoreButton.
     * Sýnir nafnaskráningar-valmyndina.
     */
    public void fxAddHighscoreClickedHandler() {
        setVisabilityGameOver(false);
        setVisabilityEnterName(true);
    }

    /**
     * Bætir við pöllum á skjáinn.
     */
    private void addPallar() {
        if(-fpalla*100 < bolti.getHaed() - bolti.getCenterY() + 800) {
            fpalla++;
            Random random = new Random();
            double platformX = 90+(random.nextDouble() * (canvas.getWidth() - 360));
            pallar.add(new Pallur(platformX, (-fpalla*100)+140, Math.max(100, random.nextDouble() * 250 ) , 20 ));
        }
    }

    /**
     * Teiknar boltann á skjáinn
     */
    private void teiknaBolta() {
        gc.drawImage(bolti.getImage(), bolti.getCenterX(), bolti.getCenterY(), bolti.getRadius(), bolti.getRadius());
    }

    /**
     * Teiknar alla palla á skjáinn.
     */
    public void teiknaPalla() {
        for (Pallur pallur : pallar) {
            pallur.draw(gc, bolti.getHaed(), bolti.getCenterY());
        }
    }

    /**
     * Athugar hvort boltinn er á palli.
     *
     * @param ball Boltinn sem notandi stjórnar.
     * @param pallur Pallurinn sem á að athuga.
     * @return Skilar satt ef boltinn er á pallinum, annars ósatt.
     */
    public boolean boltiAPall(Bolti ball, Pallur pallur) {
        if(ball.getCenterX() > pallur.getX()-ball.getRadius() && ball.getCenterX() < pallur.getX()+pallur.getWidth()) {
            if(ball.getHaed() > pallur.getY()-ball.getRadius() && ball.getHaed() < pallur.getY()+pallur.getHeight() && currentmax < pallur.getY()-20) {
                return true;
            }
        }
        return false;
    }

    /**
     * Setur á pásu eða endurræsir leikinn.
     */
    public void pauseGame() {
        if(isRunning) {
            teiknaOverlay();
            setVisabilityPauseMenu(true);
            isRunning = false;
            animationTimer.stop();
        } else {
            setVisabilityPauseMenu(false);
            animationTimer.start();
            isRunning = true;
        }
    }

    /**
     * Teiknar overlay yfir leikinn þegar hann er í pause.
     */
    public void teiknaOverlay() {
        gc.setFill(Color.rgb(0, 0, 0, 0.5));
        gc.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        animationTimer.stop();
    }
    /**
     * Atburðavakinn fyrir lyklaborðsyttur.
     *
     * @param event KeyEvent hlutur sem lýsir atburðinum.
     */
    @FXML
    public void handleKeyPress(KeyEvent event) {
        KeyCode code = event.getCode();
        if (code == KeyCode.LEFT || code == KeyCode.A && !isRunning) {
            bolti.moveLeft();
            event.consume();
        } else if (event.getCode() == KeyCode.RIGHT || code == KeyCode.D && isRunning) {
            bolti.moveRight();
            event.consume();
        } else if (event.getCode() == KeyCode.UP || code == KeyCode.W || code == KeyCode.SPACE && isRunning) {
            jumping = true;
            jumpTime = lastUpdateTime;
        } else if (code == KeyCode.ESCAPE || code == KeyCode.P && !gameOver) {
            pauseGame();
        }
    }
}
