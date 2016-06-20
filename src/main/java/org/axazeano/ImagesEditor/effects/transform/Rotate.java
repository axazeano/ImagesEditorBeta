package org.axazeano.ImagesEditor.effects.transform;

import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffectOneParam;

/**
 * Created by vladimir on 17.05.2016.
 */
public class Rotate extends BaseEffectOneParam {
    private double degrees;
    private int centerX;
    private int centerY;

    static {
        name = "Rotate";
        description = "Rotate image";
        firstParameter = "Angle";
    }

    public Rotate(EditableImage inputImage) {
        super(inputImage);
        centerX = selection.getWidth() / 2;
        centerY = selection.getHeight() / 2;
    }

    public void setValues(int angle) {
        degrees = Math.PI * angle / 180.0;
    }

    @Override
    public void setValues() {
        this.degrees = degrees;
    }

    @Override
    public int[] performEffect() {
        for (int y = selection.getStartY(); y < selection.getHeight(); ++y)
        {
            for (int x = selection.getStartX(); x < selection.getWidth(); ++x)
            {
                int newX = (int) (Math.cos(degrees) * (x - centerX) - Math.sin(degrees) * (y - centerY) + centerX);
                if (newX >= selection.getWidth() || newX < 0) { continue; }
                int newY = (int) (Math.sin(degrees) * (x - centerX) + Math.cos(degrees) * (y - centerY) + centerY);
                if (newY >= selection.getHeight() || newY < 0) { continue; }
                targetPixelArray[newX + newY * inputImage.getWeight()] =
                        sourcePixelArray[x + y * inputImage.getWeight()];
            }
        }
        return targetPixelArray;
    }
}
