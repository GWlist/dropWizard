package com.javaeeee.FullStackProject.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesReader {

    private static Properties prop;

    private static Properties init() throws IOException {
        if(prop == null){
            read();
            return prop;
        } else {
            return prop;
        }
    }

    private static void read() throws IOException {
        prop = new Properties();
        InputStream in = new FileInputStream("server.properties");
        try {
            prop.load(in);
        } catch (IOException ex) {
            throw new IOException("The properties file couldn't be found.");
        }
    }

    public static String getProperty(String name) throws IOException {
        init();
        return prop.getProperty(name);
    }

}
