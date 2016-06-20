package org.axazeano.ImagesEditor.effects.colorCorrection;


import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffectOneParam;
import org.axazeano.ImagesEditor.history.History;

/**
 * Created by vladimir on 01.06.2016.
 */

public class Otsu extends BaseEffectOneParam {
    private History history = History.INSTANCE;

    public Otsu(EditableImage image) {
        super(image);
    }

    public void applyEffect() {
        int numberOfFragmentsMultiplier = 1;
        int horizontalStep = (selection.getWidth() - selection.getStartX()) / numberOfFragmentsMultiplier;
        int verticalStep   = (selection.getHeight() - selection.getStartY()) / numberOfFragmentsMultiplier;

        for (int x = 0; x < numberOfFragmentsMultiplier * horizontalStep; x += horizontalStep)
        {
            for (int y = 0; y < numberOfFragmentsMultiplier * verticalStep; y += verticalStep) {
                int minHalftone = 255;
                int maxHalftone = 0;


                for (int xx = selection.getStartX() + x; xx < selection.getStartX() + x + horizontalStep; xx++) {
                    for (int yy = selection.getStartY() + y; yy < selection.getStartY() + y + verticalStep; yy++) {
                        // Getting current pixel.
//                        Color currentPixel = pixelReader.getColor(xx, yy);

                        // Counting its grayscale tone.
                        int pixelGrayscale = 4;//(int) (currentPixel.getBrightness() * 255);

                        // Updating local min and max.
                        if (pixelGrayscale > maxHalftone) {
                            maxHalftone = pixelGrayscale;
                        }
                        if (pixelGrayscale < minHalftone) {
                            minHalftone = pixelGrayscale;
                        }
                        }
                    }
                }
            }
    }

    @Override
    protected int[] performEffect() {
        return new int[0];
    }

    @Override
    public void setValues() {

    }
}
