/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Entity;
import com.gran.model.Field;
import com.kles.view.IModelManagerView;
import com.kles.view.ModelManagerTableViewController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;

/**
 * FXML Controller class
 *
 * @author Jeremy.CHAUT
 */
public class FieldOverviewController extends ModelManagerTableViewController implements IModelManagerView {

    @FXML
    private TableColumn<Field, String> nameColumn;
    @FXML
    private TableColumn<Field, String> typeColumn;
    @FXML
    private TableColumn<Field, String> parameterColumn;

    @FXML
    private Label tEntityName;

    private final ObjectProperty<Field> currentField = new SimpleObjectProperty<>();

    private Entity entity;

    public FieldOverviewController() {
        super("Field");
    }

    @FXML
    @Override
    public void handleEdit() {
        if (table.getSelectionModel().getSelectedIndex() != -1) {
            currentField.setValue((Field) table.getSelectionModel().getSelectedItem());
        }
    }

    public ObjectProperty<Field> getCurrentField() {
        return currentField;
    }

    @Override
    public void loadColumnTable() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().getNameProperty());
        typeColumn.setCellValueFactory(cellData -> cellData.getValue().getTypeProperty());
        parameterColumn.setCellValueFactory(cellData -> cellData.getValue().getRequiredProperty().asString().concat(cellData.getValue().getIndexProperty()));
    }

    @Override
    public void newInstance() {
        datamodel = new Field();
    }

}
