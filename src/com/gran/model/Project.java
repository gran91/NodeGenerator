package com.gran.model;

import com.kles.model.AbstractDataModel;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "Project")
public class Project extends AbstractDataModel {

    private final StringProperty name;
    private final ObservableList<Entity> listEntity;
    private final ObservableList<Module> listModule;
   
    public Project() {
        this("");
    }

    public Project(String name) {
        super("Project");
        this.name = new SimpleStringProperty(name);
        this.listEntity = FXCollections.observableArrayList();
        this.listModule = FXCollections.observableArrayList();
    }

    @Override
    public ArrayList<?> extractData() {
        ArrayList a = new ArrayList();
        a.add(name.get());
        a.add(listEntity);
        a.add(listModule);
        return a;
    }

    @Override
    public void populateData(ArrayList<?> data) {
        if (data != null) {
            if (data.size() == 3) {
                name.set((String) data.get(0));
                listEntity.addAll((ObservableList<Entity>) data.get(1));
                listModule.addAll((ObservableList<Module>) data.get(2));
            }
        }
    }

    @Override
    public String toString() {
        return name.get();
    }

    @Override
    public AbstractDataModel newInstance() {
        return new Project();
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public StringProperty getNameProperty() {
        return this.name;
    }

    @XmlElementWrapper(name = "entities")
    @XmlElement(name = "entity")
    public ObservableList<Entity> getListEntity() {
        return listEntity;
    }
    
    @XmlElementWrapper(name = "modules")
    @XmlElement(name = "module")
    public ObservableList<Module> getListModule() {
        return listModule;
    }
}
