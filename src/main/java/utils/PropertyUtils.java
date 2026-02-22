package utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertyUtils {

    private static Properties properties = new Properties();
    private static final String CONFIG_PATH = 
            System.getProperty("user.dir") + "/src/test/resources/config/";

    static {
        loadProperties();
    }

    private static void loadProperties() {

        String env = System.getProperty("env");

        if (env == null || env.isEmpty()) {
            env = "dev";   // default environment
        }

        String filePath = CONFIG_PATH + env + ".properties";

        try (FileInputStream fis = new FileInputStream(filePath)) {
            properties.load(fis);
            System.out.println("Loaded environment: " + env);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load property file: " + filePath);
        }
    }

    public static String getProperty(String key) {

        // First check system property (command line override)
        String value = System.getProperty(key);

        if (value != null) {
            return value;
        }

        // Otherwise get from property file
        value = properties.getProperty(key);

        if (value == null) {
            throw new RuntimeException("Property not found: " + key);
        }

        return value;
    }
}

