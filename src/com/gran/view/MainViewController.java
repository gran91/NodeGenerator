/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Entity;
import com.gran.model.Field;
import com.gran.model.Project;
import com.kles.MainApp;
import com.kles.view.util.PanelIndicator;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Jeremy.CHAUT
 */
public class MainViewController {

    public MainApp mainApp;

    @FXML
    private ProjectChooserController projectChooserController;

    @FXML
    private EntityListManageController entityListManageController;

    @FXML
    private FieldOverviewController fieldOverviewController;

    @FXML
    private FieldEditDialogController fieldEditDialogController;

    @FXML
    public void initialize() {

    }

    public void setMainApp(MainApp main) {
        mainApp = main;
        projectChooserController.setMainApp(main);
        entityListManageController.setMainApp(main);
        entityListManageController.setProgressListEntity(new PanelIndicator().build());

        projectChooserController.getComboProject().getListModel().valueProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                entityListManageController.setProject((Project) newValue);
            }
        });

        entityListManageController.getEntityList().setOnMouseClicked(click -> {
            if (click.getClickCount() == 2 && !entityListManageController.getEntityList().getSelectionModel().getSelectedIndices().isEmpty()) {
                final Entity t = entityListManageController.getEntityList().getSelectionModel().getSelectedItem();
                fieldOverviewController.setData(t.getListField());
            }
        });

        fieldOverviewController.getCurrentField().addListener((ObservableValue<? extends Field> observable, Field oldValue, Field newValue) -> {
            fieldEditDialogController.setDataModel(newValue);
        });
    }

}
