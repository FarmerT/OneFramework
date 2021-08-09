package core;

import org.apache.log4j.Logger;
import tools.SeleniumDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class BaseClass {

    public static SeleniumDriver SeleniumDriverInstance;
    public static String TestName;
    public static Logger Log;
    public static Properties property;
    public static String logFilePath = System.getProperty("user.dir") + "\\Reports\\";

    private static String reportDirectory;

    public static void setReportDirectory(String dir) {
        reportDirectory = dir;
    }

    public static String getReportDirectory() {
        return reportDirectory;
    }

    public BaseClass() {

        Log = Logger.getLogger(this.getClass());
        try {
            property = new Properties();
            FileInputStream inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/main/resources/log4j.properties");
            property.load(inputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static String getCurrentDateTime() {
        Date dateNow = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");

        return dateFormat.format(dateNow);
    }

}
