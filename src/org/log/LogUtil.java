package org.log;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.LogManager;

public class LogUtil {

    public static void configLogger() {

        InputStream configFile = LogUtil.class.getResourceAsStream("logging.properties");

        try {
            LogManager.getLogManager().readConfiguration(configFile);
        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
