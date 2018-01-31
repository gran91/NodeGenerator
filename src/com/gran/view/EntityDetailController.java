/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Entity;
import com.gran.model.Field;
import javafx.beans.Observable;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableObjectValue;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
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

    @FXML
    private Label tEntityName;

    private ObjectProperty<Field> currentField = new SimpleObjectProperty<>();

    private Entity entity;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        parameterColumn.setCellValueFactory(cellData -> cellData.getValue().getRequiredProperty().asString().concat(cellData.getValue().getIndexProperty()));
    }

    @FXML
    public void handleNew() {
        entity.getListField().add(new Field());
    }

    @FXML
    public void handleCopy() {
        if (table.getSelectionModel().getSelectedIndex() != -1) {
            final Field f = new Field();
            f.populateData(table.getSelectionModel().getSelectedItem().extractData());
            f.setName("NewField");
        }
    }

    @FXML
    public void handleEdit() {
        if (table.getSelectionModel().getSelectedIndex() != -1) {
            currentField.setValue(table.getSelectionModel().getSelectedItem());
        }
    }

    @FXML
    public void handleDelete() {
        if (table.getSelectionModel().getSelectedIndex() != -1) {
            entity.getListField().remove(table.getSelectionModel().getSelectedItem());
        }
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

    public TableView<Field> getTable() {
        return table;
    }

    public ObjectProperty<Field> getCurrentField() {
        return currentField;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
        tEntityName.setText(entity.getName());
        table.setItems(entity.getListField());
    }

}
