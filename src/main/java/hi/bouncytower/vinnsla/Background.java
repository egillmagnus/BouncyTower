package hi.bouncytower.vinnsla;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

/**
 * Background klasinn er hluti af vinnslupakkanum og sér um að teikna
 * bakgrunnsmyndina fyrir Bouncy Tower leikinn. Klasinn notar slóðir að
 * myndaskrám og GraphicsContext til að teikna myndirnar á réttum stað.
 *
 * @author Egill Magnússon
 */
public class Background {
    private String imageFilePathBackWall = getClass().getResource("/Images/background_wall.png").toExternalForm();

    private String imageFilePathSideWalls = getClass().getResource("/Images/bricks_background.png").toExternalForm();

    private double lastPosHlidar;

    private double lastPosBack;

    private int count = 0;

    /**
     * Smiður fyrir Background. Upphafstillir staðsetningar fyrir hliðar- og
     * bakveggi á bakgrunni.
     */
    public Background() {
        lastPosHlidar = 0;
        lastPosBack = 0;
    }

    /**
     * Teiknar bakgrunnsmyndina á skjáinn með GraphicsContext. Sér um að
     * uppfæra staðsetningar myndanna og teiknar þær rétt miðað við boltann.
     *
     * @param gc GraphicsContext til að teikna bakgrunnsmyndina
     * @param bolti Bolti tilvik sem stýrir hreyfingu bakgrunnar
     */
    public void draw(GraphicsContext gc, Bolti bolti) {
        double posVeggir;
        double posBackVeggir;
        if(count%10==0) {
            if(bolti.moveBackground()) {
                posVeggir = (-bolti.getHaed() + 800) % 800;
                posBackVeggir = (lastPosBack+((posVeggir-lastPosHlidar)/2)) % 800;
            } else {
                posVeggir = lastPosHlidar;
                posBackVeggir = lastPosBack;
            }
                gc.drawImage(new Image(imageFilePathBackWall), 0, posBackVeggir, 700, 800);
                gc.drawImage(new Image(imageFilePathBackWall), 0, posBackVeggir - 800, 700, 800);
                gc.drawImage(new Image(imageFilePathSideWalls), 0, posVeggir, 700, 800);
                gc.drawImage(new Image(imageFilePathSideWalls), 0, posVeggir - 800, 700, 800);
                lastPosHlidar = posVeggir;
                lastPosBack = posBackVeggir;
                count = 0;

        } else {
            count++;
        }
    }
}
