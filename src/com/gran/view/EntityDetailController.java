/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Entity;
import com.gran.model.Field;
import com.kles.fx.custom.FxUtil;
import com.kles.model.AbstractDataModel;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Jeremy.CHAUT
 */
public class EntityDetailController {

    @FXML
    private TableView<Field> table;

    @FXML
    private TableColumn<Field, String> nameColumn;
    @FXML
    private TableColumn<Field, String> typeColumn;
    @FXML
    private TableColumn<Field, String> parameterColumn;

    private Entity entity;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        parameterColumn.setCellValueFactory(cellData -> cellData.getValue().getRequiredProperty().asString().concat(cellData.getValue().getIndexProperty()));
    }

    @FXML
    public void handleNew() {

    }

    @FXML
    public void handleCopy() {

    }

    @FXML
    public void handleEdit() {
    }

    @FXML
    public void handleDelete() {
    }

    @FXML
    protected void onKeyPressed(KeyEvent event) {
        if (event.isControlDown() && event.getCode().equals(KeyCode.DIGIT1)) {
            handleNew();
        }
        if (event.isControlDown() && event.getCode().equals(KeyCode.DIGIT2)) {
            handleEdit();
        }
        if (event.isControlDown() && event.getCode().equals(KeyCode.DIGIT3)) {
            handleCopy();
        }
        if (event.isControlDown() && event.getCode().equals(KeyCode.DIGIT4)) {
            handleDelete();
        }
    }

    @FXML
    protected void onMousePressed(MouseEvent event) {
        if (event.isPrimaryButtonDown() && event.getClickCount() == 2) {
            handleEdit();
        }
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        table.setItems(entity.getListField());
    }

}
