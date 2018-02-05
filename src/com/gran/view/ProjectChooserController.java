/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Project;
import com.kles.MainApp;
import com.kles.view.util.ComboboxModelAdd;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Jeremy.CHAUT
 */
public class ProjectChooserController {

    @FXML
    private HBox root;

    @FXML
    private ComboboxModelAdd tProject;

    private MainApp mainApp;

    @FXML
    public void initialize() {

    }

    public void setMainApp(MainApp main) {
        mainApp = main;
        tProject.setMainApp(mainApp);
        tProject.setModel(new Project());
        tProject.setList(mainApp.getDataMap().get("Project").getList());
        tProject.setViewPath("/com/gran/view/ProjectEditDialog.fxml");
        tProject.init();
    }

    public HBox getRoot() {
        return root;
    }

    public void setRoot(HBox root) {
        this.root = root;
    }

    public ComboboxModelAdd getComboProject() {
        return tProject;
    }
}
