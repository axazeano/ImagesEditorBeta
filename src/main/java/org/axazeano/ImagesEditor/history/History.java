package org.axazeano.ImagesEditor.history;

import javafx.scene.image.Image;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

/**
 * Created by home on 19.06.16.
 */
public class History extends Observable{
    private List<HistoryItem> history;

    private int head;
    public static History INSTANCE = new History();
    private History() {
        history = new ArrayList<HistoryItem>();
        head = -1;
    }

    public void init(Image image) {
        clean();
        EditableImage editableImage = new EditableImage(image);
        history.add(new HistoryItem("Init", editableImage, false));
//        history.set(0, new HistoryItem("Init", editableImage, false));
        setChanged();
        notifyObservers();
    }

    public void undo() {
        if (head < 0) {
            throw new UnderflowException();
        }
        head--;
        setChanged();
        notifyObservers();
    }

    public List<HistoryItem> getHistory() {
        return history;
    }

    public void redo() {
        if (head >= history.size()) {
            throw new OverflowException();
        }
        head++;
        setChanged();
        notifyObservers();
    }

    public void add(HistoryItem newItem) {
        head++;
        history.add(newItem);
        setChanged();
        notifyObservers();
    }

    public void clean() {
        head = 0;
        history.clear();
//        setChanged();
//        notifyObservers();
    }

    public void removeCurrentElement() {
        history.remove(head);
        head--;
        setChanged();
        notifyObservers();
    }

    public HistoryItem getCurrentItem() {
        return history.get(head);
    }

    public EditableImage getCurrentEditableImage() {
        return history.get(head).getEditableImage();
    }

    public HistoryItem getOriginalItem() {
        return history.get(0);
    }

    public EditableImage getOriginalEditableImage() {
        return history.get(0).getEditableImage();
    }

    public void applyEffect() {
        history.get(head).setTemporary(false);
    }

    public class OverflowException extends RuntimeException {
    }

    public class UnderflowException extends RuntimeException {
    }
}
