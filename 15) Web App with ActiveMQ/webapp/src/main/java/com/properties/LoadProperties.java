package com.properties;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class LoadProperties {

    private static String appProperties;

    public void setAppProperties(String appProperties) {
        LoadProperties.appProperties = appProperties;
    }

    public static Properties loadProperties() throws IOException {
        Properties props = new Properties();
        InputStream inStream = LoadProperties.class
                .getClassLoader()
                .getResourceAsStream(appProperties);

        props.load(inStream);
        assert inStream != null;
        inStream.close();
        return props;
    }

}