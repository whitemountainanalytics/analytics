package com.properties;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@Component
public class LoadProperties {

    private static String appProperties;

    public void setAppProperties(String appProperties) {
        this.appProperties = appProperties;
    }

    public static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        InputStream inStream = LoadProperties.class
                .getClassLoader()
                .getResourceAsStream(appProperties);

        props.load(inStream);
        inStream.close();
        return props;
    }

}