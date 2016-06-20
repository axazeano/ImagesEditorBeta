package org.axazeano.ImagesEditor.effects.stylized;


import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffectOneParam;
import org.axazeano.ImagesEditor.effects.Direction;

import java.util.Random;

/**
 * Created by vladimir on 24.05.2016.
 */
public class Wind extends BaseEffectOneParam {
    static {
        name = "Wind";
        description = "Wind noize";
        firstParameter = "strength";
    }

    private Direction direction;
    private int size;
    private Random random;

    public Wind(EditableImage inputImage) {
        super(inputImage);
    }

    public void setValues(Direction direction, int size) {
        this.direction = direction;
        this.size = size;
    }

    @Override
    public void setValues() {

    }

    @Override
    public int[] performEffect() {
        return new int[0];
    }
}
