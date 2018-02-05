/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.task;

import com.gran.model.Entity;
import com.gran.model.Field;
import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 *
 * @author Jeremy.CHAUT
 */
public class EntityModelGenerator extends DefaultGenerator {

    protected Entity entity;

    public EntityModelGenerator() {
    }

    public EntityModelGenerator(Entity entity) {
        this.entity = entity;
    }

    @Override
    protected Void call() throws Exception {
        String template = readTemplate("/com/resources/js/mongoose_template.js");
        template.replaceAll("##NAME##", entity.getName());
        if (entity != null) {
            for (Field f : entity.getListField()) {

            }
        }
        return null;
    }

    public static String generateField(Field f) {
        String s = f.getName() + ":{";
        if (f.getType().equals(Field.listType[6]) || f.getType().equals(Field.listType[7])) {
            s += "type= mongoose.Schema.Types." + f.getType();
        } else if (f.getType().equals(Field.listType[5])) {
            s += "type= [" + f.getArrayType() + "]";
        } else {
            s += "type=" + f.getType();
        }

        if (f.getRequired()) {
            s += ", required=true";
        }
        if (f.getUnique()) {
            s += ", unique=true";
        }
        if (f.getIndex()) {
            s += ", index=true";
        }
        if (f.getTrim()) {
            s += ", trim=true";
        }
        if (f.getUppercase()) {
            s += ", uppercase=true";
        }
        if (f.getLowercase()) {
            s += ", lowercase=true";
        }
        if (!f.getDefaultValue().isEmpty()) {
            s += ", default=" + f.getDefaultValue();
        }

        return s += "}";
    }

}
