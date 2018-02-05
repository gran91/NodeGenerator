/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Field;
import com.kles.fx.custom.InputConstraints;
import com.kles.model.AbstractDataModel;
import com.kles.view.AbstractDataModelEditController;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
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
public class FieldEditDialogController extends AbstractDataModelEditController {

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

    @Override
    public void setDataModel(AbstractDataModel field) {
        datamodel = field;
        tName.setText(((Field) datamodel).getName());
        tType.getSelectionModel().select(((Field) datamodel).getTypeProperty().get());
        tArrayType.setText(((Field) datamodel).getArrayTypeProperty().get());
        tDefault.setText(((Field) datamodel).getDefaultValueProperty().get());
        tMax.setText(((Field) datamodel).getMaxProperty().get());
        tMin.setText(((Field) datamodel).getMinProperty().get());
        trequired.setSelected(((Field) datamodel).getRequiredProperty().get());
        tUnique.setSelected(((Field) datamodel).getUniqueProperty().get());
        tIndex.setSelected(((Field) datamodel).getIndexProperty().get());
        tTrim.setSelected(((Field) datamodel).getTrimProperty().get());
        tUppercase.setSelected(((Field) datamodel).getUppercaseProperty().get());
        tLowercase.setSelected(((Field) datamodel).getLowercaseProperty().get());
    }

    @Override
    public void setBooleanMessage() {
    }

    @Override
    public void saveData() {
        ((Field)datamodel).setName(tName.getText());
        ((Field)datamodel).getTypeProperty().set(tType.getSelectionModel().getSelectedItem());
        ((Field)datamodel).getDefaultValueProperty().set(tDefault.getText());
        ((Field)datamodel).getMinProperty().set(tMin.getText());
        ((Field)datamodel).getMaxProperty().set(tMax.getText());
        ((Field)datamodel).getArrayTypeProperty().set(tArrayType.getText());
        ((Field)datamodel).getRequiredProperty().set(trequired.isSelected());
        ((Field)datamodel).getUniqueProperty().set(tUnique.isSelected());
        ((Field)datamodel).getIndexProperty().set(tIndex.isSelected());
        ((Field)datamodel).getLowercaseProperty().set(tLowercase.isSelected());
        ((Field)datamodel).getUppercaseProperty().set(tUppercase.isSelected());
        ((Field)datamodel).getTrimProperty().set(tTrim.isSelected());
    }
    
    @Override
    public boolean isInputValid() {
        errorMessage = "";
        return super.isInputValid();
    }
}
