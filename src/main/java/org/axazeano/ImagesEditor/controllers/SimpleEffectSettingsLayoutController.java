package org.axazeano.ImagesEditor.controllers;



import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.axazeano.ImagesEditor.effects.BaseEffect;
import org.axazeano.ImagesEditor.history.History;

import java.util.Optional;

/**
 * Created by vladimir on 15.05.2016.
 */
public class SimpleEffectSettingsLayoutController {
    protected BaseEffect effect;

    private History history = History.INSTANCE;

    public void setParent(Pane parent) {
        this.parent = parent;
    }

    protected Pane parent;

    @FXML
    protected AnchorPane pane;

    @FXML
    protected ProgressIndicator progressIndicator;

    @FXML
    protected Button okButton;

    @FXML
    protected Button cancelButton;

    @FXML
    protected Button previewButton;

    @FXML
    protected Label effectName;

    @FXML
    protected Label effectDescription;

    @FXML
    public void initialize() {
        cancelButton.setStyle("-fx-base: red;");
        effectName.setText("Rotate");
        effectDescription.setText("Rotate image");
    }

    @FXML
    public void handlePreview() {
        effect.applyEffect();
    }

    @FXML
    public void handleApply() {
        if (history.getCurrentItem().isTemporary()) {
            history.applyEffect();
        } else {
            history.applyEffect();
            history.applyEffect();
        }
        parent.getChildren().remove(0);
    }

    @FXML
    public void handleCancel() {
        showCancelDialog();
    }

    @FXML
    private void cancel() {
        parent.getChildren().remove(0);
        if (history.getCurrentItem().isTemporary()) {
            history.removeCurrentElement();
        }
    }

    private void showCancelDialog() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Confirmation Dialog");
        alert.setHeaderText("Cancel dialog");
        alert.setContentText("Do you want cancel this effect?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            cancel();
        } else {
            alert.close();
        }
    }

    @FXML
    public void setEffect(BaseEffect effect) {
        this.effect = effect;
        effectName.setText(this.effect.name);
        effectDescription.setText(this.effect.description);
    }
}
