package org.axazeano.ImagesEditor.effects;


import org.axazeano.ImagesEditor.EditableImage.EditableImage;

/**
 * Created by vladimir on 01.06.2016.
 */
public abstract class BaseEffectThreeParam extends BaseEffect {
    public static String firstParameter;
    public static String secondParameter;
    public static String thirdParameter;

    public BaseEffectThreeParam(EditableImage image) {
        super(image);
    }

    public void setValues() {
    }

}
