/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Field;
import com.kles.fx.custom.InputConstraints;
import com.sun.media.jfxmedia.events.NewFrameEvent;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
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

    @FXML
    public void initialize() {
        root.getChildren().removeAll();
        root.getChildren().add(headerGrid);
        root.getChildren().add(okGrid);
        tType.setItems(FXCollections.observableArrayList(Field.listType));
        tType.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            Platform.runLater(() -> {
                buildRootFromType(newValue);
            });
        });
    }

    private void buildRootFromType(String type) {
        root.getChildren().removeAll();
        root.getChildren().add(headerGrid);
        if (type.equals(Field.listType[0])) {
            root.getChildren().add(StringGrid);
        }

        if (type.equals(Field.listType[1])) {
            InputConstraints.decimalOnly(tMin, 0);
            InputConstraints.decimalOnly(tMax, 0);
            root.getChildren().add(MinMaxGrid);
        }
        if (type.equals(Field.listType[2])) {
            root.getChildren().add(MinMaxGrid);
        }
    }

    public Field getCurrentField() {
        return currentField;
    }

    public void setCurrentField(Field currentField) {
        this.currentField = currentField;
    }
}
