package org.axazeano.ImagesEditor.effects.stylized;



import org.axazeano.ImagesEditor.EditableImage.EditableImage;
import org.axazeano.ImagesEditor.effects.BaseEffectOneParam;
import org.axazeano.ImagesEditor.effects.Direction;

import java.util.Random;

/**
 * Created by vladimir on 01.06.2016.
 */
public class Glass extends BaseEffectOneParam {

    static {
        name = "Glass";
        description = "Glass effect";
        firstParameter = "Strength";
    }

    private int strength = 100;
    private Direction direction = Direction.DOWN;

    private Random random;

    @Override
    public void setValues() {

    }
    @Override
    public int[] performEffect() {
        switch (direction) {
            case UP:
                up();
                break;
            case DOWN:
                down();
                break;
            case RIGHT:
                right();
                break;
            case LEFT:
                left();
                break;
        }
        return targetPixelArray;
    }

    public void setValues(int strength, Direction direction) {
        this.strength = strength;
        this.direction = direction;
    }

    public Glass(EditableImage inputImage) {
        super(inputImage);
        random = new Random();
    }

    private void left() {
        for (int x = selection.getStartX(); x < selection.getWidth(); x++) {
            for (int y = selection.getStartY(); y < selection.getHeight(); y++) {
                // Random shift.
                int newX = x - (random.nextInt() % this.strength + 1);
                int color = sourcePixelArray[y*inputImage.getWeight()+x];
                if (newX > 0 && newX < selection.getWidth() && y > 0 && y < selection.getHeight()) {
                    targetPixelArray[y*inputImage.getWeight()+newX] = color;
                }
            }
        }
    }

    private void right() {
        for (int x = selection.getWidth() - 1; x > selection.getStartX(); x--) {
            for (int y = selection.getStartY(); y < selection.getHeight(); y++) {
                int newX = x + (random.nextInt() % strength + 1);
                int color = sourcePixelArray[y*inputImage.getWeight()+x];
                if (newX > 0 && newX < selection.getWidth() && y > 0 && y < selection.getHeight()) {
                    targetPixelArray[y*inputImage.getWeight()+newX] = color;
                }
            }
        }
    }

    private void up() {
        for (int x = selection.getStartX(); x < selection.getWidth(); x++) {
            for (int y = selection.getStartY(); y < selection.getHeight(); y++) {
                int newY = y - (random.nextInt() % strength + 1);
                int color = sourcePixelArray[y*inputImage.getWeight()+x];
                if (x > 0 && x < selection.getWidth() && newY > 0 && newY < selection.getHeight()) {
                    targetPixelArray[newY*inputImage.getWeight() + x] = color;
                }
            }
        }
    }

    private void down() {
        for (int x = selection.getStartX(); x < selection.getWidth(); x++) {
            for (int y = selection.getHeight() - 1; y > selection.getStartY(); y--) {
                int newY = y + (random.nextInt() % this.strength + 1);
                int color = sourcePixelArray[y*inputImage.getWeight()+x];
                if (x > 0 && x < selection.getWidth() && newY > 0 && newY < selection.getHeight()) {
                    targetPixelArray[newY*inputImage.getWeight() + x] = color;
                }
            }
        }
    }
}
