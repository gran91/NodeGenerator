/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Field;
import com.kles.fx.custom.InputConstraints;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Jeremy.CHAUT
 */
public class FieldDetailController {

    @FXML
    private CheckBox trequired;

    @FXML
    private ComboBox<String> tType;

    @FXML
    private TextField tMax;

    @FXML
    private ComboBox<String> tRef;

    @FXML
    private GridPane headerGrid;

    @FXML
    private TextField tMin;

    @FXML
    private HBox okGrid;

    @FXML
    private CheckBox tUppercase;

    @FXML
    private CheckBox tLowercase;

    @FXML
    private CheckBox tIndex;

    @FXML
    private TextField tArrayType;

    @FXML
    private GridPane MinMaxGrid;

    @FXML
    private VBox root;

    @FXML
    private TextField tName;

    @FXML
    private TextField tDefault;

    @FXML
    private CheckBox tUnique;

    @FXML
    private CheckBox tTrim;

    @FXML
    private GridPane StringGrid;

    private Field currentField;

    private final BooleanProperty isArraytype = new SimpleBooleanProperty(false);
    private final BooleanProperty isReftype = new SimpleBooleanProperty(false);

    @FXML
    public void initialize() {
        tArrayType.visibleProperty().bind(isArraytype);
        tRef.visibleProperty().bind(isReftype);
        root.getChildren().clear();
        tType.setItems(FXCollections.observableArrayList(Field.listType));
        tType.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            Platform.runLater(() -> {
                buildRootFromType(newValue);
            });
        });
    }

    private void buildRootFromType(String type) {
        root.getChildren().clear();
        tMin.setOnKeyTyped(null);
        tMax.setOnKeyTyped(null);

        isArraytype.set(false);
        isReftype.set(false);

        root.getChildren().add(headerGrid);
        if (type.equals(Field.listType[0])) {
            root.getChildren().add(StringGrid);
            InputConstraints.decimalOnly(tMin, 0);
            InputConstraints.decimalOnly(tMax, 0);
            root.getChildren().add(MinMaxGrid);
        }

        if (type.equals(Field.listType[1])) {
            InputConstraints.decimalOnly(tMin, 0);
            InputConstraints.decimalOnly(tMax, 0);
            root.getChildren().add(MinMaxGrid);
        }
        if (type.equals(Field.listType[2])) {
            root.getChildren().add(MinMaxGrid);
        }
        if (type.equals(Field.listType[6])) {
            isReftype.set(true);
        }
        if (type.equals(Field.listType[7])) {
            isArraytype.set(true);
        }

        root.getChildren().add(okGrid);
    }

    @FXML
    private void handleOK(ActionEvent e) {
        currentField.setName(tName.getText());
        currentField.getTypeProperty().set(tType.getSelectionModel().getSelectedItem());
        currentField.getDefaultValueProperty().set(tDefault.getText());
        currentField.getMinProperty().set(tMin.getText());
        currentField.getMaxProperty().set(tMax.getText());
        currentField.getArrayTypeProperty().set(tArrayType.getText());
        currentField.getRequiredProperty().set(trequired.isSelected());
        currentField.getUniqueProperty().set(tUnique.isSelected());
        currentField.getIndexProperty().set(tIndex.isSelected());
        currentField.getLowercaseProperty().set(tLowercase.isSelected());
        currentField.getUppercaseProperty().set(tUppercase.isSelected());
        currentField.getTrimProperty().set(tTrim.isSelected());
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
        tName.setText(currentField.getName());
        tType.getSelectionModel().select(currentField.getTypeProperty().get());
        tArrayType.setText(currentField.getArrayTypeProperty().get());
        tDefault.setText(currentField.getDefaultValueProperty().get());
        tMax.setText(currentField.getMaxProperty().get());
        tMin.setText(currentField.getMinProperty().get());
        trequired.setSelected(currentField.getRequiredProperty().get());
        tIndex.setSelected(currentField.getIndexProperty().get());
        tTrim.setSelected(currentField.getTrimProperty().get());
        tUppercase.setSelected(currentField.getUppercaseProperty().get());
        tLowercase.setSelected(currentField.getLowercaseProperty().get());
    }
}
