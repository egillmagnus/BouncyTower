package hi.bouncytower.vidmot;


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;

public class Background {
    private String imageFilePathBackWall = getClass().getResource("/Images/background_wall.png").toExternalForm();

    private String imageFilePathSideWalls = getClass().getResource("/Images/bricks_background.png").toExternalForm();

    private double lastPosHlidar;

    private double lastPosBack;

    private int count = 0;

    Background() {
        lastPosHlidar = 0;
        lastPosBack = 0;
    }

    public void draw(GraphicsContext gc, Bolti bolti) {
        double posVeggir;
        double posBackVeggir;
        if(count%100==0) {
            if(bolti.moveBackground()) {
                System.out.println("Teiknar Bakgrunn");
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
