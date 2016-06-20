package org.axazeano.ImagesEditor.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import org.axazeano.ImagesEditor.MainApp;
import org.axazeano.ImagesEditor.effects.EffectAction;
import org.axazeano.ImagesEditor.effects.EffectsHolder;
import org.axazeano.ImagesEditor.history.History;
import java.util.Optional;

/**
 * Created by vladimir on 15.05.2016.
 */
public class EffectsLayoutController {
    private MainApp mainApp;
    private History history = History.INSTANCE;
//    private EffectsHolder effectsHolder = EffectsHolder.INSTANCE;
    private static final ObservableList<String> transformEffects =
            FXCollections.observableArrayList();

    public static final ObservableList<String> stylizedEffects =
            FXCollections.observableArrayList();

    public static final ObservableList<String> colorCorrectionEffects =
            FXCollections.observableArrayList();



    @FXML
    private ListView<String> colorCorrection;

    @FXML
    ListView<String> stylized;

    @FXML
    ListView<String> transform;

    @FXML
    private AnchorPane effectSettings;



    private void showCancelDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel dialog");
        alert.setContentText("Do you want cancel this effect?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            history.removeCurrentElement();
        } else {
            alert.close();
        }
    }

    @FXML
    public void initialize() {

        colorCorrection.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (history.getCurrentItem().isTemporary()) {
                    showCancelDialog();
                }
            }
        });

        transformEffects.addAll(EffectsHolder.EffectsNames.rotate);
        transform.setItems(transformEffects);

        colorCorrectionEffects.add(EffectsHolder.EffectsNames.greyscale);
        colorCorrectionEffects.add(EffectsHolder.EffectsNames.greyworld);
        colorCorrectionEffects.add(EffectsHolder.EffectsNames.linearCorrection);
        colorCorrectionEffects.add(EffectsHolder.EffectsNames.rootCorrection);
        colorCorrectionEffects.add(EffectsHolder.EffectsNames.squareCorrection);
        colorCorrectionEffects.add(EffectsHolder.EffectsNames.cubeCorrection);
        colorCorrection.setItems(colorCorrectionEffects);

        stylizedEffects.add(EffectsHolder.EffectsNames.waves);
        stylizedEffects.add(EffectsHolder.EffectsNames.wind);
        stylizedEffects.add(EffectsHolder.EffectsNames.glass);
        stylizedEffects.add(EffectsHolder.EffectsNames.randomJitter);
        stylizedEffects.add(EffectsHolder.EffectsNames.pixelezation);
        stylized.setItems(stylizedEffects);

        transform.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> loadEffectLayout((String) newValue));

        colorCorrection.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> loadEffectLayout((String) newValue));

        stylized.getSelectionModel()
                .selectedItemProperty()
                .addListener((observable, oldValue, newValue) -> loadEffectLayout((String) newValue));

    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }


    /**
     * Load effect layout by name of an effect
     * @param name name of the effect
     */
    private void loadEffectLayout(String name) {
        effectSettings.getChildren().clear();
        EffectsHolder effectsHolder = EffectsHolder.INSTANCE;
        EffectAction effect = effectsHolder.effectsList.get(name);
        effect.operation(effectSettings);
    }
}
