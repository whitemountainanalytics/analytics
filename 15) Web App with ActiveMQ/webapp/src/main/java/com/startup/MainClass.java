package com.startup;

import com.viewcontrollers.PageViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;

import java.util.Collections;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@ComponentScan("com.database")
@ComponentScan("com.messagecontrol")
@ImportResource({"classpath*:beans.xml"})
@ComponentScan("com.viewcontrollers")
public class MainClass  {

    private static final Logger LOGGER = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainClass.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "80"));
        PageViews pageViews = new PageViews();

        pageViews.setThrowMQException(args[0]);
        pageViews.setThrowDBException(args[1]);
        LOGGER.info("ThrowMQException is set to: " + args[0] + ", ThrowDBException is set to: " + args[1]);
        app.run(args);

        // SpringApplication.run(MainClass.class, args);
    }

}