package org.axazeano.ImagesEditor.effects.colorCorrection;


import org.axazeano.ImagesEditor.ColorsModes.ARGBColor;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;

/**
 * Created by vladimir on 01.06.2016.
 */
public class RootCorrection extends FunctionalCorrection {
    static {
        description = "Root correction";
        name = "Root correction";
    }

    public RootCorrection(EditableImage image) {
        super(image);
    }

    @Override
    protected void prepareEvaluatingCoefficients() {
        redCoefficient = maxRed / 15.95;
        greenCoefficient = maxGreen / 15.95;
        blueCoefficient = maxBlue / 15.95;
    }

    @Override
    public int[] performEffect() {
        for (int y=0; y < selection.getHeight(); y++) {
            for (int x=0; x < selection.getWidth(); x++) {
                ARGBColor color = new ARGBColor(sourcePixelArray[x + y * inputImage.getWeight()]);
                color.setRed((int) Math.sqrt((color.getRed() * 255) * redCoefficient));
                color.setGreen((int) Math.sqrt((color.getGreen() * 255) * greenCoefficient));
                color.setBlue((int) Math.sqrt((color.getBlue() * 255) * blueCoefficient));
                targetPixelArray[x + y * inputImage.getWeight()] = color.getColor();
            }
        }
        return targetPixelArray;
    }
}
