package org.axazeano.ImagesEditor.effects.colorCorrection;


import org.axazeano.ImagesEditor.ColorsModes.ARGBColor;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;

/**
 * Created by vladimir on 24.05.2016.
 */
public class LinearCorrection extends FunctionalCorrection {
    static {
        description = "Linear stretching";
        name = "Linear Correction";
    }

    public LinearCorrection(EditableImage image) {
        super(image);
    }

    @Override
    protected void prepareEvaluatingCoefficients() {
        redCoefficient = (maxRed - minRed) / 255;
        greenCoefficient = (maxGreen - minGreen) / 255;
        blueCoefficient = (maxBlue - minBlue) / 255;
    }

    @Override
    public int[] performEffect() {
        for (int y=0; y < selection.getHeight(); y++) {
            for (int x=0; x < selection.getWidth(); x++) {
                ARGBColor color = new ARGBColor(sourcePixelArray[x + y * inputImage.getWeight()]);
                color.setRed((int) ((color.getRed() * 255 - minRed) * redCoefficient));
                color.setGreen((int) ((color.getRed() * 255 - minRed) * redCoefficient));
                color.setBlue((int) ((color.getBlue() * 255 - minBlue) * blueCoefficient));
                targetPixelArray[x + y * inputImage.getWeight()] = color.getColor();
            }
        }
        return targetPixelArray;
    }
}
