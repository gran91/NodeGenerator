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

    public String getType() {
        return type.get();
    }

    public void setType(String val){
        type.set(val);
    }
    
    public StringProperty getDefaultValueProperty() {
        return defaultValue;
    }

    public String getDefaultValue() {
        return defaultValue.get();
    }
    
    public void setDefaultValue(String val){
        defaultValue.set(val);
    }
    
    public BooleanProperty getRequiredProperty() {
        return required;
    }
    
    public boolean getRequired() {
        return required.get();
    }
    
    public void setRequired(boolean val){
        required.set(val);
    }

    public BooleanProperty getUniqueProperty() {
        return unique;
    }
    
    public boolean getUnique() {
        return unique.get();
    }
    
    public void setUnique(boolean val){
        unique.set(val);
    }

    public BooleanProperty getIndexProperty() {
        return index;
    }
    
    public boolean getIndex() {
        return index.get();
    }
    
    public void setIndex(boolean val){
        index.set(val);
    }

    public BooleanProperty getLowercaseProperty() {
        return lowercase;
    }
    
    public boolean getLowercase() {
        return lowercase.get();
    }
    
    public void setLowercase(boolean val){
        lowercase.set(val);
    }

    public BooleanProperty getUppercaseProperty() {
        return uppercase;
    }

    public boolean getUppercase() {
        return uppercase.get();
    }
    
    public void setUppercase(boolean val){
        uppercase.set(val);
    }
    
    public BooleanProperty getTrimProperty() {
        return trim;
    }

    public boolean getTrim() {
        return trim.get();
    }
    
    public void setTrim(boolean val){
        trim.set(val);
    }
    
    public StringProperty getMinProperty() {
        return min;
    }
    
    public String getMin() {
        return min.get();
    }
    
    public void setMin(String val){
        min.set(val);
    }

    public StringProperty getMaxProperty() {
        return max;
    }
    
    public String getMax() {
        return max.get();
    }
    
    public void setMax(String val){
        max.set(val);
    }

    public StringProperty getArrayTypeProperty() {
        return arrayType;
    }
    
    public String getArrayType() {
        return arrayType.get();
    }

    public void setArrayType(String val){
        arrayType.set(val);
    }
    
    public StringProperty getRefProperty() {
        return ref;
    }
    
    public String getRef() {
        return ref.get();
    }
 
    public void setRef(String val){
        ref.set(val);
    }

}
