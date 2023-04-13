package hi.bouncytower.vidmot;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class ViewSwitcher {
    private static Map<View, Parent> cache = new HashMap<>();

    private static final Map<View, Object> controllers = new HashMap<>();
    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewSwitcher.scene = scene;
    }

    public static void switchTo(View view) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }
        try {
            Parent root;
            if (cache.containsKey(view)) {
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

    public static void switchTo(View view, Game model) {
        if (scene == null) {
            System.out.println("No scene was set");
            return;
        }

        try {
            Parent root;
            FXMLLoader loader = new FXMLLoader(ViewSwitcher.class.getResource(view.getFileName()));
            root = loader.load();
            Object controller = loader.getController();
            if (controller instanceof ControllerWithModel) {
                ((ControllerWithModel) controller).setModel(model); // set the model
            }
            controllers.put(view, controller); // save the controller
            cache.put(view, root); // save the view
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}