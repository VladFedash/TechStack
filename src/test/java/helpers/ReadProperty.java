package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ReadProperty {
    protected Properties prop = new Properties();
    protected String property;

    public String readProperty(String str){
        try {
            prop.load(new FileInputStream("src/main/resources/config.properties"));
            property = prop.getProperty(str);
        } catch (
                IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}