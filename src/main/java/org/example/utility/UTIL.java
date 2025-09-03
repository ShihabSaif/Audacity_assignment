package org.example.utility;

import java.io.*;
import java.net.URL;
import java.util.Properties;

public class UTIL {

    public Properties readPropData() throws IOException {
        Properties prop=new Properties();
        InputStream inputSteam=null;
        try {
            String propFileName = "application.properties";
            inputSteam = getClass().getClassLoader().getResourceAsStream(propFileName);

            if (inputSteam != null) {
                prop.load(inputSteam);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

        }catch(Exception e){
            System.out.println("Exception: " + e);
        }finally {
            inputSteam.close();
        }
        return prop;
    }
}
