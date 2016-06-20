package org.axazeano.ImagesEditor.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import org.axazeano.ImagesEditor.effects.BaseEffectOneParam;

/**
 * Created by vladimir on 17.05.2016.
 */
public class OneControlEffectSettingsController extends SimpleEffectSettingsLayoutController{

    protected BaseEffectOneParam effect;
    @FXML
    private Label parameterName;

    @FXML
    private Spinner<Integer> parameterValue;

    @FXML
    public void initialize() {
        cancelButton.setStyle("-fx-base: red;");
        parameterValue.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(Integer.MIN_VALUE, Integer.MAX_VALUE, 0, 1));
    }

    public void setEffect(BaseEffectOneParam effect) {

        this.effect = effect;
        effectName.setText(this.effect.name);
        effectDescription.setText(this.effect.description);
        parameterName.setText(this.effect.firstParameter);
    }

    public void handlePreview() {
        int value = parameterValue.getValue();
//        effect.setValues(value);
        super.handlePreview();
    }

}
