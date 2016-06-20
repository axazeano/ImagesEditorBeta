package org.axazeano.ImagesEditor.EditableImage;

import javafx.scene.image.*;

import java.nio.IntBuffer;

/**
 * Created by home on 19.06.16.
 */
public class EditableImage {
    private Image image;
    private PixelWriter pixelWriter;
    private PixelReader pixelReader;
    private final WritablePixelFormat<IntBuffer> format =
            WritablePixelFormat.getIntArgbInstance();

    public EditableImage(Image image) {
        this.image = image;
    }

    public Image getImage() {
        return image;
    }

    public int[] getPixelsArray() {
        int[] result = new int[(int) (image.getWidth() * image.getHeight())];
        pixelReader = image.getPixelReader();
        pixelReader.getPixels(0, 0, (int) image.getWidth(), (int) image.getHeight(), format, result, 0,
                (int) image.getWidth());
        return result;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setImageByPixelsArray(int[] pixelsArray) {
        WritableImage writableImage = new WritableImage((int) image.getWidth(),
                (int) image.getHeight());
        pixelWriter = writableImage.getPixelWriter();
        pixelWriter.setPixels(0, 0, (int) image.getWidth(), (int) image.getHeight(), format, pixelsArray, 0,
                (int) image.getWidth());
        this.image = writableImage;
    }

    public int getHeight() {
        return (int) image.getHeight();
    }

    public int getWeight() {
        return (int) image.getWidth();
    }
}
