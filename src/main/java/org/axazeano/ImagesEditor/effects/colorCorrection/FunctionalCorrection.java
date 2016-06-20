package org.axazeano.ImagesEditor.effects.colorCorrection;

import org.axazeano.ImagesEditor.ColorsModes.ARGBColor;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffect;

/**
 * Created by vladimir on 24.05.2016.
 */
abstract class FunctionalCorrection extends BaseEffect {
    int minRed = 255;
    int minGreen = 255;
    int minBlue = 255;
    int maxRed = 0;
    int maxGreen = 0;
    int maxBlue = 0;
    double redCoefficient;
    double greenCoefficient;
    double blueCoefficient;

    public FunctionalCorrection(EditableImage image) {
        super(image);
    }

    private void getMinColors() {
        int[] pixelsArray = this.inputImage.getPixelsArray();
        for (int pixel : pixelsArray) {
            ARGBColor color = new ARGBColor(pixel);
            if (color.getRed() < minRed) {
                minRed = color.getRed();
            }
            if (color.getGreen() < minGreen) {
                minGreen = color.getGreen();
            }
            if (color.getBlue() < minBlue) {
                minBlue = color.getBlue();
            }
        }
    }

    private void getMaxColors() {
        int[] pixelsArray = this.inputImage.getPixelsArray();
        for (int pixel : pixelsArray) {
            ARGBColor color = new ARGBColor(pixel);
            if (color.getRed() > maxRed) {
                maxRed = color.getRed();
            }
            if (color.getGreen() > maxGreen) {
                maxGreen = color.getGreen();
            }
            if (color.getBlue() > maxBlue) {
                maxBlue = color.getBlue();
            }
        }
    }

    abstract protected void prepareEvaluatingCoefficients();

    public void applyEffect() {
        getMaxColors();
        getMinColors();
        prepareEvaluatingCoefficients();
        inputImage.setImageByPixelsArray(performEffect());
    }
}
