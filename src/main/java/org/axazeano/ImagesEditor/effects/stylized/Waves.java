package org.axazeano.ImagesEditor.effects.stylized;

import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffectThreeParam;

import java.util.Random;

/**
 * Created by vladimir on 01.06.2016.
 */


/**
 * Wave effects
 */
public class Waves extends BaseEffectThreeParam {
    static {
        name = "Waves";
        description = "Waves effect";
        firstParameter = "Count";
        secondParameter = "Strength";
        thirdParameter = "Type";
    }

    public enum WaveType {
        Horizontal,
        Vertical,
        ZigZag
    }

    /**
     * Count of waves
     */
    private int count = 10;
    /**
     * Strength of waves
     */
    private int strength = 10;
    /**
     * Type of waves. Can be Horizontal, Vertical, ZigZag
     */
    private WaveType waveType = WaveType.Horizontal;

    private Random random;

    @Override
    public int[] performEffect() {
        switch (waveType) {
            case Horizontal:
                Horizontal();
                break;
            case Vertical:
                Vertical();
                break;
            case ZigZag:
                ZigZag();
                break;
        }
        return targetPixelArray;
    }


    public Waves(EditableImage inputImage) {
        super(inputImage);
        random = new Random();
    }

    public void setValues(int count, int strength, WaveType waveType) {
        this.count = count;
        this.strength = strength;
        this.waveType = waveType;
    }

    private void Horizontal() {
        int newCoordinate;
        for (int y = selection.getStartY(); y < selection.getHeight(); y++) {
            for (int x = selection.getStartX(); y < selection.getWidth(); x++) {
                newCoordinate = (int) (x + strength * Math.sin(2 * Math.PI * y / count));
                int color = sourcePixelArray[x + y * selection.getWidth()];
                if (newCoordinate < selection.getHeight() && newCoordinate >= selection.getStartY()) {
                    targetPixelArray[x + newCoordinate * inputImage.getWeight()] = color;
                }
            }
        }
    }

    private void Vertical() {
        int newCoordinate;
        for (int y = selection.getStartY(); y < selection.getHeight(); y++) {
            for (int x = selection.getStartX(); y < selection.getWidth(); x++) {
                newCoordinate = (int) (y + strength * Math.sin(2 * Math.PI * x / count));
                int color = sourcePixelArray[x + y * selection.getWidth()];
                if (newCoordinate < selection.getWidth() && newCoordinate >= selection.getStartX()) {
                    targetPixelArray[newCoordinate + y * inputImage.getWeight()] = color;
                }
            }
        }
    }

    private void ZigZag() {
        int newCoordinate;
        for (int y = selection.getStartY(); y < selection.getHeight(); y++) {
            for (int x = selection.getStartX(); y < selection.getWidth(); x++) {
                newCoordinate = (int) (y + strength * Math.cos(2 * Math.PI * x / count));
                int color = sourcePixelArray[x + y * selection.getWidth()];
                if (newCoordinate < selection.getWidth() && newCoordinate >= selection.getStartX())
                    targetPixelArray[newCoordinate + y * inputImage.getWeight()] = color;
            }
        }
        approximateZigZag();
    }

    private void approximateZigZag() {
//        for (int y = selection.getStartY(); y < selection.getHeight(); y++) {
//            for (int x = selection.getStartX(); y < selection.getWidth(); x++) {
//
//                // Getting color of current pixel
//                Color color = pixelReader.getColor(x, y);
//
//                // Looking for empty sourceImagePixels.
//                if (color.getOpacity() == 0) {
//                    // Approximating this pixel.
//                    int coordinateOfSource = (int) (x - this.strength * Math.cos(2 * Math.PI * x / this.count));
//                    // Out of bounds check.
//                    if (coordinateOfSource >= selection.getWidth() || coordinateOfSource < 0 ||
//                            y >= selection.getHeight() || y < 0)
//                        continue;
//
//                    // Applying approximation for pixel.
//                    color = pixelReader.getColor(coordinateOfSource, y);
//                    pixelWriter.setColor(x, y, color);
//                }
//            }
//        }
    }
}
