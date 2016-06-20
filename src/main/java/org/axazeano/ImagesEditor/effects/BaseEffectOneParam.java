package org.axazeano.ImagesEditor.effects;

/**
 * Created by vladimir on 17.05.2016.
 */

import org.axazeano.ImagesEditor.EditableImage.EditableImage;

/**
 * The effect, which takes one parameter
 */
abstract public class BaseEffectOneParam extends BaseEffect {
    public static String firstParameter;

    public BaseEffectOneParam(EditableImage image) {
        super(image);
    }

    abstract public void setValues();
}
