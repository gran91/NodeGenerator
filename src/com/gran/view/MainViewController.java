/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.kles.MainApp;
import javafx.fxml.FXML;

/**
 * FXML Controller class
 *
 * @author Jeremy.CHAUT
 */
public class MainViewController {

    public MainApp mainApp;

    @FXML
    private EntityListManageController entityListManageController;

    @FXML
    private EntityDetailController entityDetailController;

    @FXML
    private FieldDetailController fieldDetailController;

    @FXML
    public void initialize() {

    }

    public void setMainApp(MainApp main) {
        mainApp = main;
        entityListManageController.setMainApp(main);
    }

}
