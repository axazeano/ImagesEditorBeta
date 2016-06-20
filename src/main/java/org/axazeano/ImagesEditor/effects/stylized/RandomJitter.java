package org.axazeano.ImagesEditor.effects.stylized;

import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffectOneParam;

import java.util.Random;

/**
 * Created by vladimir on 24.05.2016.
 */
public class RandomJitter extends BaseEffectOneParam {
    static {
        name = "Random jitter";
        description = "Random jitter effect";
        firstParameter = "Degree";
    }

    private int degree;
    private int half;
    private Random random;


    public RandomJitter(EditableImage inputImage) {
        super(inputImage);
        random = new Random();
    }

    @Override
    protected int[] performEffect() {
        return new int[0];
    }

    public void setValues(int degree) {
        this.degree = degree;
        this.half = (int) Math.floor(degree / 2.0);
    }


    protected void proceedEffect() {
        for (int x = selection.getStartX(); x < selection.getWidth(); x++) {
            for (int y = selection.getStartY(); y < selection.getHeight(); y++) {
                int newX = random.nextInt(degree) - half;

                if (x + newX < 0 || x + newX >= selection.getWidth()) {
                    newX = 0;
                } else {
                    newX = newX + x;
                }
                int newY = random.nextInt(degree) - half;

                if (y + newY < 0 || y + newY >= selection.getHeight()) {
                    newY = 0;
                } else {
                    newY = newY + y;
                }

//                Color color = pixelReader.getColor(x, y);
//                pixelWriter.setColor(newX, newY, color);
            }
        }
    }

    @Override
    public void setValues() {

    }
}
