package com.yrkh.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by Yuriy on 15.04.2016.
 */
public class ConfigurationReader {

    private Properties properties;
    private File file;
    private FileInputStream fileInput;

    public ConfigurationReader(){
        init();
    }

    private void init() {
        try{
            this.file = new File(System.getProperty("user.dir")+ File.separator + "res" + File.separator + "config.properties");
            this.fileInput = new FileInputStream(file);
            this.properties = new Properties();
            this.properties.load(fileInput);
            fileInput.close();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }
        catch(IOException e){
            e.printStackTrace();
        }
    }

    public String getProperty(String key){
        return this.properties.getProperty(key);
    }
}
