package com.gran.model;

import com.kles.model.AbstractDataModel;
import java.util.ArrayList;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

public class Entity extends AbstractDataModel {

    private final StringProperty name;
    private final ObservableList<Field> listField;
    public static transient String[] listLabelID = {"environment.name", "environment.host", "environment.port", "environment.login", "environment.password", "label.faci", "label.facn"};

    public Entity() {
        this("");
    }

    public Entity(String name) {
        super("Entity");
        this.name = new SimpleStringProperty("");
        this.listField = FXCollections.observableArrayList();
    }

    @Override
    public ArrayList<?> extractData() {
        ArrayList a = new ArrayList();
        a.add(name.get());
        a.add(listField);
        return a;
    }

    @Override
    public void populateData(ArrayList<?> data) {
        if (data != null) {
            if (data.size() == 2) {
                name.set((String) data.get(0));
                listField.addAll((ObservableList<Field>) data.get(1));
            }
        }
    }

    @Override
    public String toString() {
        return name.get();
    }

    @Override
    public AbstractDataModel newInstance() {
        return new Entity();
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

    @XmlElementWrapper(name = "fields")
    @XmlElement(name = "field")
    public ObservableList<Field> getListField() {
        return listField;
    }
}
