package com.props;

import java.io.*;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class ApplicationProperties {

    public  ApplicationProperties(){};

    private static final Logger logger = LoggerFactory.getLogger(ApplicationProperties.class);

    private  Map<String, String> pMap = new HashMap<String, String>();
    public  Map<String, String> refMap = new HashMap<String, String>();

    @PostConstruct
    public void init(){
    }

    public Map getProps() {

        try {
            File file = new File("C:\\\\Users\\abcde\\Documents\\Pi projects\\development_stuff\\development_stuff\\webapp\\src\\main\\resources\\application.properties");
            FileInputStream fileInput = new FileInputStream(file);
            Properties properties = new Properties();
            properties.load(fileInput);
            fileInput.close();

            Enumeration enuKeys = properties.keys();
            while (enuKeys.hasMoreElements()) {
                String key = (String) enuKeys.nextElement();
                String value = properties.getProperty(key);
                System.out.println(key + " = " + value);
                pMap.put(key, value);
            }
        }
        catch (IOException ex) {
            logger.error(ex.getLocalizedMessage());
        }

        return pMap;
    }
}