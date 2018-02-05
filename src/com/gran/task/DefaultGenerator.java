/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.gran.task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import javafx.concurrent.Task;

/**
 *
 * @author Jeremy.CHAUT
 */
public class DefaultGenerator extends Task<Void> implements ITaskGenerator {

    public DefaultGenerator() {
    }

    @Override
    protected Void call() throws Exception {
        return null;
    }

    @Override
    public String readTemplate(String path) throws IOException {
        InputStream is = DefaultGenerator.class.getResourceAsStream(path);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String line;
        String content = "";
        while ((line = br.readLine()) != null) {
            content += line;
        }
        br.close();
        isr.close();
        is.close();
        return content;
    }

}
