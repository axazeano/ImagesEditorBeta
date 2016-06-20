package org.axazeano.ImagesEditor.effects.colorCorrection;


import org.axazeano.ImagesEditor.ColorsModes.ARGBColor;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffect;

/**
 * Created by vladimir on 16.05.2016.
 */
public class Greyworld extends BaseEffect {

    private double redSum, greenSum, blueSum;
    private double redGlobal, greenGlobal, blueGlobal;
    private double redDiv, greenDiv, blueDiv;
    private int redNew, greenNew, blueNew;
    private double avg;

    static {
        name = "Grey world";
        description = "Grey world normalization";
    }

    public Greyworld(EditableImage inputImage) {
        super(inputImage);
    }


    @Override
    public int[] performEffect() {
        calculateDivValues();
        for (int y =  selection.getStartY(); y < selection.getHeight(); y++)
        {
            for (int x = selection.getStartX(); x < selection.getWidth(); x++)
            {
                ARGBColor color = new ARGBColor(sourcePixelArray[x + y * inputImage.getWeight()]);
                color.setRed((int) (color.getRed() * redDiv));
                color.setGreen((int) (color.getGreen() * greenDiv));
                color.setBlue((int) (color.getBlue() * blueDiv));
                targetPixelArray[x + y * inputImage.getWeight()] = color.getColor();
            }
        }
        return targetPixelArray;
    }

    private void calculateSumValues()
    {
        for (int y = selection.getStartY(); y < selection.getHeight(); y++)
        {
            for (int x = selection.getStartX(); x < selection.getWidth(); x++)
            {
                ARGBColor color = new ARGBColor(sourcePixelArray[x + y * inputImage.getWeight()]);
                redSum += color.getRed();
                greenSum += color.getGreen();
                blueSum += color.getBlue();
            }
        }
    }

    private void calculateGlobalValues()
    {
        calculateSumValues();

        int size = inputImage.getHeight() * inputImage.getWeight();
        redGlobal = 1d / size * redSum;
        greenGlobal = 1d / size * greenSum;
        blueGlobal = 1d / size * blueSum;
        avg = (redGlobal + greenGlobal + blueGlobal) / 3;
    }

    private void calculateDivValues()
    {
        calculateGlobalValues();
        redDiv = avg / redGlobal;
        greenDiv = avg / greenGlobal;
        blueDiv = avg / blueGlobal;
    }
}
