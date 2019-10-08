package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadSettings {
    private static String pathToConfigurationFile = "config.settings";
    public static String readProperty(String value){
        InputStream stream = null;
        String prop = null;
        try {
            stream = ReadSettings.class.getClassLoader().getResourceAsStream(pathToConfigurationFile);
            Properties properties = new Properties();
            properties.load(stream);
            prop = properties.getProperty(value);
            System.out.println("// " + prop + " / " + value);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }
}
