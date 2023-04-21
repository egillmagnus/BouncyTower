package hi.bouncytower.vidmot;

import hi.bouncytower.vinnsla.Game;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * ViewSwitcher stjórnar viðmótaskiptum í Bouncy Tower.
 * Klasinn heldur utan um cache af Parent hlutum sem eru geymd viðmót.
 * Hann sér um að hlaða og skipta milli viðmóta.
 *
 * @author Sturla Freyr Magnússon
 * @author Egill Magnússon
 */
public class ViewSwitcher {
    private static Map<View, Parent> cache = new HashMap<>();

    private static final Map<View, Object> controllers = new HashMap<>();
    private static Scene scene;

    /**
     * Stillir grunnsenu fyrir viðmótaskipti.
     *
     * @param scene Sena sem viðmótaskiptin eiga við.
     */
    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    /**
     * Skiptir viðmóti án þess að sækja eða uppfæra módel.
     *
     * @param view Viðmótssena sem á að skipta yfir í.
     */
    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }
        try {
            Parent root;
            if (cache.containsKey(view) && view != View.GAME) {
                System.out.println("Loading from cache");
                root = cache.get(view);
            } else {
                root = FXMLLoader.load(
                        ViewSwitcher.class.getResource(view.getFileName())
                );
                cache.put(view, root);
            }
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Skiptir viðmóti og uppfærir módel.
     *
     * @param view Viðmótssena sem á að skipta yfir í.
     * @param model Módelið sem viðmótið þarf að hafa.
     */
    public static void switchTo(View view, Game model) {
        System.out.println("Switching to " + view.getFileName());
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
            root = loader.load();
            Object controller = loader.getController();
            System.out.println(view);
            if (view == View.HIGHSCORES) {
                ((HighscoreController) controller).setModel(model);
                System.out.println("Setting Model");
                ((HighscoreController) controller).addHighscore();
            }
            controllers.put(view, controller); // save the controller
            cache.put(view, root); // save the view
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}