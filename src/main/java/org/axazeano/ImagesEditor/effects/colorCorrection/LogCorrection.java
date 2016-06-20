package org.axazeano.ImagesEditor.effects.colorCorrection;

import org.axazeano.ImagesEditor.ColorsModes.ARGBColor;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;

/**
 * Created by vladimir on 01.06.2016.
 */
public class LogCorrection extends FunctionalCorrection {
    static {
        description = "Log correction";
        name = "Log correction";
    }

    public LogCorrection(EditableImage image) {
        super(image);
    }

    @Override
    protected void prepareEvaluatingCoefficients() {
        redCoefficient = maxRed / 2.412;
        greenCoefficient = maxGreen / 2.412;
        blueCoefficient = maxBlue / 2.412;
    }

    @Override
    public int[] performEffect() {
        for (int y=0; y < selection.getHeight(); y++) {
            for (int x=0; x < selection.getWidth(); x++) {
                ARGBColor color = new ARGBColor(sourcePixelArray[x + y * inputImage.getWeight()]);
                color.setRed((int) Math.log((color.getRed() * 255) * redCoefficient));
                color.setGreen((int) Math.log((color.getGreen() * 255) * greenCoefficient));
                color.setBlue((int) Math.log((color.getBlue() * 255) * blueCoefficient));
                targetPixelArray[x + y * inputImage.getWeight()] = color.getColor();
            }
        }
        return targetPixelArray;
    }
}
