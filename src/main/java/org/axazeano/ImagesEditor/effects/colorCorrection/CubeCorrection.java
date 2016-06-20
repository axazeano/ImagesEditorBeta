package org.axazeano.ImagesEditor.effects.colorCorrection;

import org.axazeano.ImagesEditor.ColorsModes.ARGBColor;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;

/**
 * Created by vladimir on 01.06.2016.
 */
public class CubeCorrection extends FunctionalCorrection {
    static {
        description = "Cube correction";
        name = "Cube correction";
    }

    public CubeCorrection(EditableImage image) {
        super(image);
    }

    @Override
    protected void prepareEvaluatingCoefficients() {
        redCoefficient = maxRed / (255 * 255 * 255);
        greenCoefficient = maxGreen / (255 * 255 * 255);
        blueCoefficient = maxBlue / (255 * 255 * 255);
    }

    @Override
    public int[] performEffect() {
        for (int y=0; y < selection.getHeight(); y++) {
            for (int x=0; x < selection.getWidth(); x++) {
                ARGBColor color = new ARGBColor(sourcePixelArray[x + y * inputImage.getWeight()]);
                color.setRed((int) (Math.pow((color.getRed() * 255), 3) * redCoefficient));
                color.setGreen((int) (Math.pow((color.getGreen() * 255), 3) * greenCoefficient));
                color.setBlue((int) (Math.pow((color.getBlue() * 255), 3) * blueCoefficient));
                targetPixelArray[x + y * inputImage.getWeight()] = color.getColor();
            }
        }
        return targetPixelArray;
    }
}
