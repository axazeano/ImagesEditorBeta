package org.axazeano.ImagesEditor.effects;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import org.axazeano.ImagesEditor.MainApp;
import org.axazeano.ImagesEditor.effects.colorCorrection.*;
import org.axazeano.ImagesEditor.effects.stylized.Glass;
import org.axazeano.ImagesEditor.effects.transform.Rotate;
import org.axazeano.ImagesEditor.history.History;
import org.axazeano.ImagesEditor.controllers.OneControlEffectSettingsController;
import org.axazeano.ImagesEditor.controllers.SimpleEffectSettingsLayoutController;

import java.io.IOException;
import java.util.HashMap;

/**
 * Created by home on 19.06.16.
 */
public class EffectsHolder {
    public static class EffectsNames {
        // Color correction effects
        public static final String greyscale = "Greyscale";
        public static final String greyworld = "Grey world";
        public static final String linearCorrection = "Linear Correction";
        public static final String rootCorrection = "Root Correction";
        public static final String logCorrection = "Log Correction";
        public static final String squareCorrection = "Square Correction";
        public static final String cubeCorrection = "Cube Correction";

        // Stylized effects
        public static final String glass = "Glass";
        public static final String pixelezation = "Pizelezation";
        public static final String randomJitter = "Random Jitter";
        public static final String waves = "Waves";
        public static final String wind = "Wind";

        //Transform effects
        public static final String rotate = "Rotate";
    }
    /**
     * Link to main app
     */
    private MainApp mainApp;

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }
    public HashMap<String, EffectAction> effectsList = new HashMap<>();

    public static final EffectsHolder INSTANCE = new EffectsHolder();
    private History history = History.INSTANCE;

    /**
     * All effects will be added in the constructor
     */
    private EffectsHolder() {
        effectsList.put(EffectsNames.greyscale, (AnchorPane parent) -> {
                    // Load FXML
                    FXMLLoader loader = new FXMLLoader();
                    loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.SimpleEffectSettingsLayout));
                    try {
                        // Create pane with effect
                        AnchorPane effect = loader.load();
                        // Create a controller for pane
                        SimpleEffectSettingsLayoutController controller = loader.getController();
                        // Add an effect
                        controller.setEffect(new Greyscale(history.getCurrentEditableImage()));
                        // Pass a link to parent pane. It use for dismiss this pane.
                        parent.getChildren().add(effect);
                        controller.setParent(parent);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
        );

        effectsList.put(EffectsNames.greyworld, (AnchorPane parent) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.SimpleEffectSettingsLayout));
            try {
                AnchorPane effect = loader.load();
                SimpleEffectSettingsLayoutController controller = loader.getController();
                controller.setEffect(new Greyworld(history.getCurrentEditableImage()));
                parent.getChildren().add(effect);
                controller.setParent(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        effectsList.put(EffectsNames.rotate, (AnchorPane parent) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.OneControlEffectSettings));
            try {
                AnchorPane effect = loader.load();
                OneControlEffectSettingsController controller = loader.getController();
                Rotate rotate = new Rotate(history.getCurrentEditableImage());
                controller.setEffect(rotate);
                parent.getChildren().add(effect);
                controller.setParent(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        effectsList.put(EffectsNames.linearCorrection, (AnchorPane parent) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.SimpleEffectSettingsLayout));
            try {
                AnchorPane effect = loader.load();
                SimpleEffectSettingsLayoutController controller = loader.getController();
                controller.setEffect(new LinearCorrection(history.getCurrentEditableImage()));
                parent.getChildren().add(effect);
                controller.setParent(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        effectsList.put(EffectsNames.rootCorrection, (AnchorPane parent) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.SimpleEffectSettingsLayout));
            try {
                AnchorPane effect = loader.load();
                SimpleEffectSettingsLayoutController controller = loader.getController();
                controller.setEffect(new RootCorrection(history.getCurrentEditableImage()));
                parent.getChildren().add(effect);
                controller.setParent(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        effectsList.put(EffectsNames.logCorrection, (AnchorPane parent) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.SimpleEffectSettingsLayout));
            try {
                AnchorPane effect = loader.load();
                SimpleEffectSettingsLayoutController controller = loader.getController();
                controller.setEffect(new LogCorrection(history.getCurrentEditableImage()));
                parent.getChildren().add(effect);
                controller.setParent(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        effectsList.put(EffectsNames.squareCorrection, (AnchorPane parent) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.SimpleEffectSettingsLayout));
            try {
                AnchorPane effect = loader.load();
                SimpleEffectSettingsLayoutController controller = loader.getController();
                controller.setEffect(new SquareCorrection(history.getCurrentEditableImage()));
                parent.getChildren().add(effect);
                controller.setParent(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        effectsList.put(EffectsNames.cubeCorrection, (AnchorPane parent) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.SimpleEffectSettingsLayout));
            try {
                AnchorPane effect = loader.load();
                SimpleEffectSettingsLayoutController controller = loader.getController();
                controller.setEffect(new CubeCorrection(history.getCurrentEditableImage()));
                parent.getChildren().add(effect);
                controller.setParent(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });

        effectsList.put(EffectsNames.glass, (AnchorPane parent) -> {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(mainApp.getClass().getResource(EffectsSettingsLayouts.SimpleEffectSettingsLayout));
            try {
                AnchorPane effect = loader.load();
                SimpleEffectSettingsLayoutController controller = loader.getController();
                controller.setEffect(new Glass(history.getCurrentEditableImage()));
                parent.getChildren().add(effect);
                controller.setParent(parent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
