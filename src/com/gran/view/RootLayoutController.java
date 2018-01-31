package com.gran.view;

import com.gran.NodeGeneratorMain;
import com.kles.MainApp;
import com.kles.fx.custom.FxUtil;
import java.io.File;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.RadioMenuItem;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import resources.ResourceApp;

/**
 * The controller for the root layout. The root layout provides the basic
 * application layout containing a menu bar and space where other JavaFX
 * elements can be placed.
 *
 * @author Jérémy Chaut
 */
public class RootLayoutController {

    @FXML
    private RadioMenuItem menuFR;
    @FXML
    private RadioMenuItem menuEN;
    @FXML
    private Menu langmenu, skinmenu;
    private MainApp mainApp;
    private Stage environmentStage;

    /**
     * Is called by the main application to give a reference back to itself.
     *
     * @param mainApp
     */
    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
        this.mainApp.getListSkin().entrySet().stream().forEach((nameSkin) -> {
            final CheckMenuItem menuItemSkin = new CheckMenuItem(nameSkin.getKey());
            menuItemSkin.setSelected(this.mainApp.prefs.get(NodeGeneratorMain.SKIN, null).equals(nameSkin.getKey()));
            menuItemSkin.setOnAction(e -> {
                Application.setUserAgentStylesheet(nameSkin.getValue());
                this.mainApp.prefs.put(NodeGeneratorMain.SKIN, nameSkin.getKey());
                skinmenu.getItems().stream().forEach(m -> {
                    ((CheckMenuItem) m).setSelected(m.getText().equals(nameSkin.getKey()));
                });
            });
            skinmenu.getItems().add(menuItemSkin);
        });
        langmenu.getItems().stream().forEach(m -> {
            ((RadioMenuItem) m).setSelected(this.mainApp.prefs.get(NodeGeneratorMain.LANGUAGE, null).equals(m.getId()));
            m.setOnAction(e -> {
                Locale.setDefault(new Locale(m.getId()));
                this.mainApp.prefs.put(NodeGeneratorMain.LANGUAGE, m.getId());
                langmenu.getItems().stream().forEach(m1 -> {
                    ((RadioMenuItem) m).setSelected(m1.getId().equals(this.mainApp.prefs.get(NodeGeneratorMain.LANGUAGE, null)));
                });
                try {
                    mainApp.getPrimaryStage().close();
                } catch (Exception ex) {
                    Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
                }
                new NodeGeneratorMain().start(new Stage());
            });
        });
    }

    /**
     * Creates an empty address book.
     */
    @FXML
    private void handleNew() {
        mainApp.clearData();
        mainApp.setRegistryFilePath(null);
    }

    /**
     * Opens a FileChooser to let the user select an address book to load.
     */
    @FXML
    private void handleOpen() {
        DirectoryChooser fileChooser = new DirectoryChooser();
        File file = fileChooser.showDialog(mainApp.getPrimaryStage());
        if (file != null) {
            mainApp.loadDataDirectory(file);
        }
    }

    /**
     * Saves the file to the person file that is currently open. If there is no
     * open file, the "save as" dialog is shown.
     */
    @FXML
    private void handleSave() {
        File directory = mainApp.getDataDirectoryPath();
        if (directory != null) {
            try {
                mainApp.saveDataToFile(directory);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            handleSaveAs();
        }
    }

    /**
     * Opens a FileChooser to let the user select a file to save to.
     */
    @FXML
    public void handleSaveAs() {
        DirectoryChooser fileChooser = new DirectoryChooser();
        File file = fileChooser.showDialog(mainApp.getPrimaryStage());
        if (file != null) {
            try {
                mainApp.saveDataToFile(file);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(RootLayoutController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Opens an about dialog.
     */
    @FXML
    private void handleAbout() {
        FxUtil.showAlert(Alert.AlertType.INFORMATION, mainApp.getResourceMessage().getString("about.title"), String.format(mainApp.getResourceMessage().getString("about.header"), ResourceApp.VERSION), String.format(mainApp.getResourceMessage().getString("about.text"), ResourceApp.VERSION));
    }

    /**
     * Opens an doc
     */
    @FXML
    private void handleHelp() {
        WebView browser = new WebView();
        WebEngine webEngine = browser.getEngine();
        webEngine.load("http://3kles-consulting.com/updates/PMS070ToolsDoc");
        FxUtil.showInDialog(browser, "doc");
    }

    /**
     * Closes the application.
     */
    @FXML
    private void handleExit() {
        handleSave();
        System.exit(0);
    }
}
