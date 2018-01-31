/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Project;
import com.kles.MainApp;
import com.kles.fx.custom.TextFieldValidator;
import com.kles.model.AbstractDataModel;
import com.kles.view.AbstractDataModelEditController;
import javafx.beans.binding.BooleanBinding;
import javafx.beans.binding.LongBinding;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author jchau
 */
public class ProjectEditDialogController extends AbstractDataModelEditController {

    @FXML
    private TextField tname;

    @FXML
    public void initialize() {
    }

    @Override
    public void setMainApp(MainApp main) {
        super.setMainApp(main);
    }

    /**
     * Sets the environment to be edited in the dialog.
     *
     * @param miws
     */
    @Override
    public void setDataModel(AbstractDataModel miws) {
        datamodel = miws;
        tname.setText(((Project) miws).getName());
    }

    @Override
    public void setBooleanMessage() {
        BooleanBinding[] mandotariesBinding = new BooleanBinding[]{TextFieldValidator.emptyTextFieldBinding(tname, errorMessage, messages)};
        BooleanBinding mandatoryBinding = TextFieldValidator.any(mandotariesBinding);
        LongBinding nbMandatoryBinding = TextFieldValidator.count(mandotariesBinding);
    }

    @Override
    public void saveData() {
        ((Project) datamodel).getNameProperty().set(tname.getText());
    }

    @Override
    public boolean isInputValid() {
        errorMessage = "";
        return super.isInputValid();
    }
}
