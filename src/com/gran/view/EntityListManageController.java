/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.view;

import com.gran.model.Entity;
import com.kles.MainApp;
import com.kles.view.util.PanelIndicator;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author jchau
 */
public class EntityListManageController {

    @FXML
    private StackPane root;

    @FXML
    private ListView<Entity> entityList;

//    @FXML
    private TextField tfilterentity;

    private PanelIndicator progressListEntity;

    @FXML
    private Button bNew, bRemoveEntity, bEditEntity, bGenerate;

    @FXML
    private HBox searchBar, buttonBar;

    private String entityname = "";
    private ObservableList<Entity> listEntity = FXCollections.observableArrayList();
    private FilteredList<Entity> filteredEntity;

    private Entity entity;
    private MainApp mainApp;
    private Stage stage;
    private List<Entity> listDelEntity;

    @FXML
    public void initialize() {
        tfilterentity = TextFields.createClearableTextField();
        HBox.setHgrow(tfilterentity, Priority.ALWAYS);
        filteredEntity = new FilteredList(listEntity, s -> true);
        tfilterentity.textProperty().addListener(obs -> {
            String filter = tfilterentity.getText();
            if (filter == null || filter.length() == 0) {
                filteredEntity.setPredicate(s -> true);
            } else {
                filteredEntity.setPredicate(s -> s.getName().contains(filter));
            }
        });
        searchBar.getChildren().add(tfilterentity);
        progressListEntity = PanelIndicator.create().build();

        root.getChildren().add(progressListEntity.getPanel());
        entityList.disableProperty().bind(Bindings.isEmpty(listEntity));

        entityList.setItems(filteredEntity);

        entityList.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        bEditEntity.disableProperty().bind(entityList.getSelectionModel().selectedItemProperty().isNull());
        bRemoveEntity.disableProperty().bind(entityList.getSelectionModel().selectedItemProperty().isNull());
        bGenerate.disableProperty().bind(entityList.getSelectionModel().selectedItemProperty().isNull());
        tfilterentity.disableProperty().bind(Bindings.isEmpty(listEntity));
    }

    @FXML
    private void removeEntity(ActionEvent event) {
        if (entityList.getSelectionModel().getSelectedItem() != null) {
            listDelEntity = entityList.getSelectionModel().getSelectedItems();
            listEntity.removeAll(entityList.getSelectionModel().getSelectedItems());

        }
    }

    @FXML
    private void editEntity(ActionEvent event) {
        if (entityList.getSelectionModel().getSelectedItem() != null) {
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    //showMDBREAD(entityList.getSelectionModel().getSelectedItem());
                }
            });
        }
    }

    @FXML
    private void generate(ActionEvent event) {
        if (entityList.getSelectionModel().getSelectedItem() != null) {

        }
    }

    public void setMainApp(MainApp main) {
        mainApp = main;
        listEntity = mainApp.getDataMap().get("Entity").getList();
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public StackPane getLeft() {
        return root;
    }

    public void setLeft(StackPane left) {
        this.root = left;
    }

    public ListView<Entity> getEntityList() {
        return entityList;
    }

    public void setEntityList(ListView<Entity> transactionList) {
        this.entityList = transactionList;
    }

    public TextField getTfilterEntity() {
        return tfilterentity;
    }

    public void setTfilterEntity(TextField tfiltertransaction) {
        this.tfilterentity = tfiltertransaction;
    }

    public PanelIndicator getProgressListEntity() {
        return progressListEntity;
    }

    public void setProgressListEntity(PanelIndicator progressListEntity) {
        this.progressListEntity = progressListEntity;
    }

    public Button getbNew() {
        return bNew;
    }

    public void setbNew(Button bNew) {
        this.bNew = bNew;
    }

    public Button getbRemoveEntity() {
        return bRemoveEntity;
    }

    public void setbRemoveEntity(Button bRemoveEntity) {
        this.bRemoveEntity = bRemoveEntity;
    }

    public String getEntityName() {
        return entityname;
    }

    public void setEntityName(String transactionName) {
        this.entityname = transactionName;
    }

    public FilteredList<Entity> getFilteredEntity() {
        return filteredEntity;
    }

    public void setFilteredEntity(FilteredList<Entity> filteredEntity) {
        this.filteredEntity = filteredEntity;
    }

    public List<Entity> getListDelTrans() {
        return listDelEntity;
    }

    public void setListDelTrans(List<Entity> listDelTrans) {
        this.listDelEntity = listDelTrans;
    }

    public ObservableList<Entity> getListEntity() {
        return listEntity;
    }

    public MainApp getMainApp() {
        return mainApp;
    }

    public Stage getStage() {
        return stage;
    }

}
