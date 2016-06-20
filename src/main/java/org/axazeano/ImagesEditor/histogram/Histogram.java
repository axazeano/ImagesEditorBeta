package org.axazeano.ImagesEditor.histogram;

import org.axazeano.ImagesEditor.ColorsModes.ARGBColor;
import org.axazeano.ImagesEditor.history.History;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by home on 19.06.16.
 */
public class Histogram extends Observable implements Observer{
    public int[] getRed() {
        return red;
    }

    public int[] getGreen() {
        return green;
    }

    public int[] getBlue() {
        return blue;
    }

    public int[] getLightness() {
        return lightness;
    }

    private int[] red;
    private int[] green;
    private int[] blue;
    private int[] lightness;
    private int[] pixelsArray;
    private History history = History.INSTANCE;

    public static final Histogram INSTANCE = new Histogram();


    public Histogram() {
        cleanHistogram();
    }

    private void cleanHistogram() {
        red = new int[256];
        green = new int[256];
        blue = new int[256];
        lightness = new int[256];
    }

    public void update(Observable o, Object arg) {
        cleanHistogram();
        pixelsArray = history.getCurrentEditableImage().getPixelsArray();
        recalculateHistogram();
        setChanged();
        notifyObservers();
    }

    private void recalculateHistogram() {
        for (int pixel : pixelsArray) {
            ARGBColor color = new ARGBColor(pixel);
            red[color.getRed()]++;
            green[color.getGreen()]++;
            blue[color.getBlue()]++;
            lightness[Math.min((int) ((color.getRed() + color.getGreen() + color.getBlue()) / 3), 255)]++;
        }
    }
}
