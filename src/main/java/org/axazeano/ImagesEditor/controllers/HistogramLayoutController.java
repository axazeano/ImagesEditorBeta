package org.axazeano.ImagesEditor.controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.*;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import org.axazeano.ImagesEditor.MainApp;
import org.axazeano.ImagesEditor.histogram.Histogram;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by vladimir on 15.05.2016.
 */
public class HistogramLayoutController implements Observer {

    enum HistogramMode {
        Red,
        Green,
        Blue,
        Lightness
    }

    private HistogramMode histogramMode;
    private Histogram histogram = Histogram.INSTANCE;
    final static String austria = "Austria";
    final static String brazil = "Brazil";
    final static String france = "France";
    final static String italy = "Italy";
    final static String usa = "USA";

    @FXML
    private BarChart chart;

    @FXML
    private RadioButton redRadioButton;

    @FXML
    private RadioButton greenRadioButton;

    @FXML
    private RadioButton blueRadioButton;

    @FXML
    private RadioButton lightnessRadioButton;

    final ToggleGroup group = new ToggleGroup();


    private CategoryAxis xAxis;
    private NumberAxis yAxis;

    private ObservableList monthNames = FXCollections.observableArrayList();
    private MainApp mainApp;

    @FXML
    public void initialize() {
        final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        chart.setTitle("Histogram");
        xAxis.setLabel("Color");
        yAxis.setLabel("Value");

        histogramMode = HistogramMode.Red;

        redRadioButton.setToggleGroup(group);
        greenRadioButton.setToggleGroup(group);
        blueRadioButton.setToggleGroup(group);
        lightnessRadioButton.setToggleGroup(group);

        group.selectedToggleProperty().addListener(new ChangeListener<Toggle>() {
                                                       public void changed(ObservableValue<? extends Toggle> ov,
                                                                           Toggle old_toggle, Toggle new_toggle) {
                                                           if (group.getSelectedToggle() != null) {
                                                               RadioButton chk = (RadioButton) group.getSelectedToggle();
                                                               String mode = chk.getText();
                                                               switch (mode) {
                                                                   case "Red":
                                                                       histogramMode = HistogramMode.Red;
                                                                       break;
                                                                   case "Green":
                                                                       histogramMode = HistogramMode.Green;
                                                                       break;
                                                                   case "Blue":
                                                                       histogramMode = HistogramMode.Blue;
                                                                       break;
                                                                   case "Lightness":
                                                                       histogramMode = HistogramMode.Lightness;
                                                               }
                                                               ;
                                                               updateChart();
                                                           }
                                                       }
                                                   }
            );
        }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    @Override
    public void update(Observable o, Object arg) {
        updateChart();
    }

    private void updateChart() {
        if (chart.getData().size() > 0) {
            chart.getData().remove(0);
        }
        XYChart.Series series = new XYChart.Series();
        switch (histogramMode) {
            case Red:
                for (int i=0; i<=255; i++) {
                    series.getData().add(new XYChart.Data(Integer.toString(i), histogram.getRed()[i]));
                }
                chart.getData().addAll(series);
                for(Node n: chart.lookupAll(".default-color0.chart-bar")) {
                    n.setStyle("-fx-bar-fill: red;");
                }
                break;
            case Green:
                for (int i=0; i<=255; i++) {
                    series.getData().add(new XYChart.Data(Integer.toString(i), histogram.getGreen()[i]));
                }
                chart.getData().addAll(series);
                for(Node n: chart.lookupAll(".default-color0.chart-bar")) {
                    n.setStyle("-fx-bar-fill: green;");
                }
                break;
            case Blue:
                for (int i=0; i<=255; i++) {
                    series.getData().add(new XYChart.Data(Integer.toString(i), histogram.getBlue()[i]));
                }
                chart.getData().addAll(series);
                for(Node n: chart.lookupAll(".default-color0.chart-bar")) {
                    n.setStyle("-fx-bar-fill: blue;");
                }
                break;
            case Lightness:
                for (int i=0; i<=255; i++) {
                    series.getData().add(new XYChart.Data(Integer.toString(i), histogram.getLightness()[i]));
                }
                chart.getData().addAll(series);
                for(Node n: chart.lookupAll(".default-color0.chart-bar")) {
                    n.setStyle("-fx-bar-fill: grey;");
                }
                break;
            default:
                return;
        }
    }
}

