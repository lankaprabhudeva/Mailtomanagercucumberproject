package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {

    private static Properties prop;

    static {
        prop = new Properties();
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config/Config.properties");
            prop.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load Config.properties file.");
        }
    }

    // static method to get properties
    public static String getProperty(String key) {
        return prop.getProperty(key);
    }
}
