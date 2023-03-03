package ru.job4j.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesAdditional {
    public static Properties properties = new Properties();
    static {
        ClassLoader classLoader = PropertiesAdditional.class.getClassLoader();
        try (InputStream io = classLoader.getResourceAsStream("app.properties")) {
            properties.load(io);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
