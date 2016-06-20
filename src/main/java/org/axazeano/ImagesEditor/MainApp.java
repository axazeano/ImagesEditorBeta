package org.axazeano.ImagesEditor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.axazeano.ImagesEditor.histogram.Histogram;
import org.axazeano.ImagesEditor.history.History;
import org.axazeano.ImagesEditor.controllers.EffectsLayoutController;
import org.axazeano.ImagesEditor.controllers.HistogramLayoutController;
import org.axazeano.ImagesEditor.controllers.RootLayoutController;

import java.io.IOException;

/**
 * Created by home on 19.06.16.
 */
public class MainApp extends Application {
    private Stage primaryStage;
    private AnchorPane rootLayout;
    private RootLayoutController rootLayoutController;
    private EffectsLayoutController effectsLayoutController;
    private HistogramLayoutController histogramLayoutController;

    /// Singletons
//    private ImagesHolder imagesHolder = ImagesHolder.INSTANCE;
    private Histogram histogram = Histogram.INSTANCE;
//    private EffectsHolder effectsHolder = EffectsHolder.INSTANCE;
    private History history = History.INSTANCE;


    @Override
    public void start(Stage primaryStage) throws Exception{

        history.addObserver(histogram);
        this.primaryStage = primaryStage;
        this.primaryStage.setTitle("Image Editor Beta");


        initRootLayout();
        initEffectsLayout();
        initHistogramLayout();
//        effectsHolder.setMainApp(this);

    }

    private void initRootLayout() {
        try {
            // Загружаем сведения об адресатах.
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(MainApp.class.getResource("/view/RootLayout.fxml"));
            rootLayout = (AnchorPane) loader.load();

            // Отображаем сцену, содержащую корневой макет.
            Scene scene = new Scene(rootLayout);
            primaryStage.setScene(scene);
            rootLayoutController = loader.getController();
            history.addObserver(rootLayoutController);
//            imagesHolder.addObserver(rootLayoutController);
            rootLayoutController.setMainApp(this);

            primaryStage.show();

            // Помещаем сведения об адресатах в центр корневого макета.
//            rootLayout.setCenter(personOverview);
//
//            PersonOverviewController controller = loader.getController();
//            controller.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initEffectsLayout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/view/EffectsLayout.fxml"));
        try {
            AnchorPane effectsTab = (AnchorPane) loader.load();
            rootLayoutController.effectsTab.setContent(effectsTab);

            effectsLayoutController = loader.getController();
            effectsLayoutController.setMainApp(this);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void initHistogramLayout() {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("/view/HistogramLayout.fxml"));
        try {
            AnchorPane histogramTab = (AnchorPane) loader.load();
            rootLayoutController.histogramTab.setContent(histogramTab);

            histogramLayoutController = loader.getController();
            histogramLayoutController.setMainApp(this);
            histogram.addObserver(histogramLayoutController);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public Stage getPrimaryStage() {
        return primaryStage;
    }
}
