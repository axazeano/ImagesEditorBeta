package org.axazeano.ImagesEditor.effects.stylized;

import org.axazeano.ImagesEditor.ColorsModes.ARGBColor;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffectOneParam;

/**
 * Created by vladimir on 24.05.2016.
 */
public class Pixelezation extends BaseEffectOneParam {
    static {
        name = "Pixelezation";
        description = "Pixelezation effect";
        firstParameter = "Blocks";
    }
    private int blocksCount;

    public Pixelezation(EditableImage inputImage) {
        super(inputImage);
    }

    public void setValues(int blocksCount) {
            this.blocksCount = blocksCount;
    }

    @Override
    public void setValues() {

    }

    @Override
    public int[] performEffect() {
        for (int x = selection.getStartX(); x < selection.getWidth() / blocksCount; x++) {
            for (int y = selection.getStartY(); y < selection.getHeight() / blocksCount; y++) {
                int totalRed = 0;
                int totalGreen = 0;
                int totalBlue = 0;
                int totalCount = 0;

                for (int xx = 0; xx < blocksCount; xx++) {
                    for (int yy = 0; yy < blocksCount; yy++, totalCount++) {
                        ARGBColor color = new ARGBColor(sourcePixelArray[x * blocksCount + xx +
                                (y * blocksCount + yy) * inputImage.getWeight()]);
                        totalRed += color.getRed();
                        totalGreen += color.getGreen();
                        totalBlue += color.getBlue();
                    }
                }

                ARGBColor color = new ARGBColor();
                color.setRed(totalRed /= totalCount);
                color.setGreen(totalGreen /= totalCount);
                color.setBlue(totalBlue /= totalCount);

                for (int xx = 0; xx < blocksCount; xx++) {
                    for (int yy = 0; yy < blocksCount; yy++) {
                        targetPixelArray[x * blocksCount + xx +
                                (y * blocksCount + yy) * inputImage.getWeight()] = color.getColor();
                    }
                }
            }
        }
        return targetPixelArray;
    }
}
