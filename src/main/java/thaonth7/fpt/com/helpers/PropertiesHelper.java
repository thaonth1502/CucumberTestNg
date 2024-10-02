package thaonth7.fpt.com.helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelper {

    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;
    private static FileOutputStream out;
    private static String relPropertiesFilePathDefault = "src/test/resources/configs/config.properties";

    public static Properties loadAllFiles(){
        LinkedList<String> files = new LinkedList<>();

        //Add all file Properties
        files.add("src/test/resources/configs/config.properties");

        try {
            properties = new Properties();

            for (String f : files){
                Properties temProp = new Properties();
                linkFile = SystemHelper.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                temProp.load(file);
                properties.putAll(temProp);

            }
            System.out.println("Load all properties: " + properties);
            return properties;
        } catch (IOException ioe){
            return new Properties();
        }
    }

    public static void setFile(String relPropertiesFilePath){
        properties = new Properties();

        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePath;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static void setDefaultFile(){
        properties = new Properties();

        try {
            linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
            file = new FileInputStream(linkFile);
            properties.load(file);
            file.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public static String getValue(String key){
        String value = null;

        try {
            if (file == null){
                properties = new Properties();
                linkFile = SystemHelper.getCurrentDir() + relPropertiesFilePathDefault;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            // Get value from file is set
            value = properties.getProperty(key);

        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return value;
    }

    public static void setValue(String key, String keyValue){
        try {
            if (file == null) {
                properties = new Properties();
                file = new FileInputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
                properties.load(file);
                file.close();
                out = new FileOutputStream(SystemHelper.getCurrentDir() + relPropertiesFilePathDefault);
            }
            //Ghi vào cùng file Prop với file lấy ra
            out = new FileOutputStream(linkFile);
            System.out.println(linkFile);
            properties.setProperty(key, keyValue);
            properties.store(out, null);
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
