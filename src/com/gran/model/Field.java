package com.gran.model;

import com.kles.model.AbstractDataModel;
import java.util.ArrayList;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Field extends AbstractDataModel {

    public static transient String[] listType = {"String", "Number", "Date", "Boolean", "Buffer", "Mixed", "ObjectId", "Array"};
    private final StringProperty name;
    private final StringProperty type;
    private final StringProperty defaultValue;
    private final BooleanProperty required;
    private final BooleanProperty unique;
    private final BooleanProperty index;
    private final BooleanProperty lowercase;
    private final BooleanProperty uppercase;
    private final BooleanProperty trim;
    private final StringProperty min;
    private final StringProperty max;
    private final StringProperty arrayType;
    private final StringProperty ref;
    
    public Field() {
        this("");
    }

    public Field(String name) {
        super("Entity");
        this.name = new SimpleStringProperty("");
        this.type = new SimpleStringProperty("");
        this.defaultValue = new SimpleStringProperty("");
        this.required = new SimpleBooleanProperty(false);
        this.unique = new SimpleBooleanProperty(false);
        this.index = new SimpleBooleanProperty(false);
        this.lowercase = new SimpleBooleanProperty(false);
        this.uppercase = new SimpleBooleanProperty(false);
        this.trim = new SimpleBooleanProperty(false);
        this.min = new SimpleStringProperty("");
        this.max = new SimpleStringProperty("");
        this.arrayType = new SimpleStringProperty("");
        this.ref = new SimpleStringProperty("");
    }

    @Override
    public ArrayList<?> extractData() {
        ArrayList a = new ArrayList();
        a.add(name.get());
        a.add(type.get());
        a.add(defaultValue.get());
        a.add(required.get());
        a.add(unique.get());
        a.add(index.get());
        a.add(lowercase.get());
        a.add(uppercase.get());
        a.add(trim.get());
        a.add(min.get());
        a.add(max.get());
        a.add(arrayType.get());
        a.add(ref.get());
        return a;
    }

    @Override
    public void populateData(ArrayList<?> data) {
        if (data != null) {
            if (data.size() == 13) {
                name.set((String) data.get(0));
                type.set((String) data.get(1));
                defaultValue.set((String) data.get(2));
                required.set((Boolean) data.get(3));
                unique.set((Boolean) data.get(4));
                index.set((Boolean) data.get(5));
                lowercase.set((Boolean) data.get(6));
                uppercase.set((Boolean) data.get(7));
                trim.set((Boolean) data.get(8));
                min.set((String) data.get(9));
                max.set((String) data.get(10));
                arrayType.set((String) data.get(11));
                ref.set((String) data.get(12));
            }
        }
    }

    @Override
    public String toString() {
        return name.get();
    }

    @Override
    public AbstractDataModel newInstance() {
        return new Field();
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

    public StringProperty getTypeProperty() {
        return type;
    }

    public StringProperty getDefaultValueProperty() {
        return defaultValue;
    }

    public BooleanProperty getRequiredProperty() {
        return required;
    }

    public BooleanProperty getUniqueProperty() {
        return unique;
    }

    public BooleanProperty getIndexProperty() {
        return index;
    }

    public BooleanProperty getLowercaseProperty() {
        return lowercase;
    }

    public BooleanProperty getUppercaseProperty() {
        return uppercase;
    }

    public BooleanProperty getTrimProperty() {
        return trim;
    }

    public StringProperty getMinProperty() {
        return min;
    }

    public StringProperty getMaxProperty() {
        return max;
    }

    public StringProperty getArrayTypeProperty() {
        return arrayType;
    }

    public StringProperty getRefProperty() {
        return ref;
    }

}
