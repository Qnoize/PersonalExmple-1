package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
    private static String pathToConfigurationFile = "config.properties";
    public static String readProperty(String value){
        InputStream stream;
        String prop = null;
        try {
            stream = ReadProperties.class.getClassLoader().getResourceAsStream(pathToConfigurationFile);
            Properties properties = new Properties();
            properties.load(stream);
            prop = properties.getProperty(value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
