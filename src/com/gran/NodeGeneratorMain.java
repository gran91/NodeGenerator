package com.gran;

import com.kles.MainApp;
import com.gran.model.Entity;
import com.gran.view.MainViewController;
import com.gran.view.RootLayoutController;
import com.kles.jaxb.JAXBObservableList;
import insidefx.undecorator.Undecorator;
import insidefx.undecorator.UndecoratorScene;
import java.io.File;
import java.io.IOException;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import static javafx.application.Application.launch;
import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import resources.ResourceApp;

public class NodeGeneratorMain extends MainApp {

    private BorderPane rootLayout;
    private RootLayoutController rootController;

    @Override
    public void start(Stage primaryStage) {
        super.start(primaryStage);
         prefs = Preferences.userRoot().node("3KLES_" + resources.ResourceApp.TITLE);
        initPrefs();
        initApp();
        this.getDataMap().put("Project", new JAXBObservableList(FXCollections.observableArrayList(), Entity.class));
        loadView();
    }

    @Override
    public void loadView() {
        super.title.unbind();
        super.title.bind(Bindings.concat(ResourceApp.TITLE).concat("\t").concat(super.clock.getTimeText()));
        if (rootController == null) {
            initRootLayout();
            showMainView();
        }
        primaryStage.show();
    }

    /**
     * Initializes the root layout and tries to load the last opened person
     * file.
     */
    public void initRootLayout() {
        try {
            // Load root layout from fxml file.
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(ResourceBundle.getBundle("resources.language", this.getLocale()));
            loader.setLocation(NodeGeneratorMain.class.getResource("view/RootLayout.fxml"));
            rootLayout = (BorderPane) loader.load();

            // Show the scene containing the root layout.
            //Scene scene = new Scene(rootLayout);
            UndecoratorScene scene = new UndecoratorScene(super.getPrimaryStage(), rootLayout);
            scene.setFadeInTransition();
            scene.getStylesheets().add(getClass().getResource("/com/gran/view/application.css").toExternalForm());
            super.getPrimaryStage().setOnCloseRequest((WindowEvent we) -> {
                we.consume();   // Do not hide yet
                scene.setFadeOutTransition();
            });
            super.getPrimaryStage().setScene(scene);

            rootController = loader.getController();
            rootController.setMainApp(this);
            scene.widthProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneWidth, Number newSceneWidth) -> {
                System.out.println("Width: " + newSceneWidth);
            });
            scene.heightProperty().addListener((ObservableValue<? extends Number> observableValue, Number oldSceneHeight, Number newSceneHeight) -> {
                System.out.println("Height: " + newSceneHeight);
            });
            Undecorator undecorator = scene.getUndecorator();
            super.getPrimaryStage().setMinWidth(undecorator.getMinWidth());
            super.getPrimaryStage().setMinHeight(undecorator.getMinHeight());

            super.getPrimaryStage().show();
        } catch (IOException e) {
            e.printStackTrace();
        }

        File file = getDataDirectoryPath();
        if (file != null) {
            loadDataDirectory(file);
        }
    }

    public void showMainView() {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setResources(ResourceBundle.getBundle("resources.language", super.getLocale()));
            loader.setLocation(NodeGeneratorMain.class.getResource("view/MainView.fxml"));
            Pane anchor = loader.load();
            rootLayout.setCenter(anchor);
            MainViewController controller = loader.getController();
            controller.setMainApp(this);

            Undecorator undecorator = ((UndecoratorScene) getPrimaryStage().getScene()).getUndecorator();
            super.getPrimaryStage().setMinWidth(getMax(anchor.getPrefWidth() + 40, undecorator.getMinWidth()));
            super.getPrimaryStage().setMinHeight(getMax(anchor.getPrefHeight(), undecorator.getMinHeight()));
            super.getPrimaryStage().setWidth(getMax(anchor.getPrefWidth() + 40, undecorator.getWidth()));
            super.getPrimaryStage().setHeight(getMax(anchor.getPrefHeight(), undecorator.getHeight()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private double getMax(double val1, double val2) {
        return (val1 > val2) ? val1 : val2;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
