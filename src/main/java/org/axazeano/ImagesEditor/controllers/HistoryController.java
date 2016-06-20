package org.axazeano.ImagesEditor.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import org.axazeano.ImagesEditor.history.History;
import org.axazeano.ImagesEditor.history.HistoryItem;

/**
 * Created by vladimir on 19.05.2016.
 */
public class HistoryController {
    private History history = History.INSTANCE;

    protected ObservableList<HistoryItem> historyItems = FXCollections.observableArrayList(history.getHistory());

    @FXML
    private ListView<HistoryItem> historyListView;



    @FXML
    public void initialize() {
        historyListView.setItems(historyItems);
//        historyListView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
//            @Override public ListCell<String> call(ListView<String> list) {
//                return new ListCell<String>("Test");
//            }
//        });

//        historyListView.setCellFactory();
//        historyListView.setCellValueFactory(new PropertyValueFactory<>("title"));
//        descriptionTc.setCellValueFactory(new PropertyValueFactory<>
//                ("description"));

    }

}
