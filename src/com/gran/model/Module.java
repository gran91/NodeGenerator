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

@XmlRootElement(name = "Module")
public class Module extends AbstractDataModel {

    private final StringProperty name;
    private final ObservableList<Entity> listEntity;
   
    public Module() {
        this("");
    }

    public Module(String name) {
        super("Module");
        this.name = new SimpleStringProperty(name);
        this.listEntity = FXCollections.observableArrayList();
    }

    @Override
    public ArrayList<?> extractData() {
        ArrayList a = new ArrayList();
        a.add(name.get());
        a.add(listEntity);
        return a;
    }

    @Override
    public void populateData(ArrayList<?> data) {
        if (data != null) {
            if (data.size() == 2) {
                name.set((String) data.get(0));
                listEntity.addAll((ObservableList<Entity>) data.get(1));
            }
        }
    }

    @Override
    public String toString() {
        return name.get();
    }

    @Override
    public AbstractDataModel newInstance() {
        return new Module();
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
    
}
