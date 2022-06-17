package com.senla.property;

import annitations.Prototype;
import org.reflections.Reflections;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

@Prototype
public class ConfigurationProperty {

    private Properties properties;
    private final String configPath = "bookmarket.properties";
    private static ConfigurationProperty configurationProperty;

    public static ConfigurationProperty getInstance() throws IOException {
        if(configurationProperty == null) {
            configurationProperty = new ConfigurationProperty();
        }
        return configurationProperty;
    }

    private ConfigurationProperty() throws IOException {
        properties = new Properties();
        Path path = Paths.get(configPath);
        if (Files.exists(path)) {
            try (InputStream in = Files.newInputStream(Paths.get(configPath))) {
                properties.load(in);
            }
        }
    }

    public String getFileName() {
        return configPath;
    }

    public String getPropertyByName(String name) {
        return properties.getProperty(name);
    }
}
