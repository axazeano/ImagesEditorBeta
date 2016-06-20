package org.axazeano.ImagesEditor.selection;

/**
 * Created by home on 19.06.16.
 */
public class Selection {
    public static final Selection INSTANCE = new Selection();

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    private int startX; //Start x point of selection
    private int startY; //Start y point of selection
    private int width;
    private int height;

    public void updateSelection(int startX, int startY, int width, int height) {
        this.startX = startX;
        this.startY = startY;
        this.width = width;
        this.height = height;
    }
}
