package org.axazeano.ImagesEditor.history;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import org.axazeano.ImagesEditor.EditableImage.EditableImage;

/**
 * Created by home on 19.06.16.
 */
public class HistoryItem {
    private SimpleStringProperty name;
    private SimpleBooleanProperty isTemporary;
    private EditableImage editableImage;

    public void setTemporary(boolean temporary) {
        isTemporary = new SimpleBooleanProperty(temporary);
    }

    public String getName() {
        return name.getValue();
    }

    public EditableImage getEditableImage() {
        return editableImage;
    }

    public boolean isTemporary() {
        return isTemporary.getValue();
    }

    public HistoryItem(String name, EditableImage editableImage, boolean isTemporary) {
        this.name = new SimpleStringProperty(name);
        this.editableImage = editableImage;
        this.isTemporary = new SimpleBooleanProperty(isTemporary);
    }
}
