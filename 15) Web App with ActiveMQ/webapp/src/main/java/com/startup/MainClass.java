package com.startup;

import com.config.GeneralConfig;
import com.database.Dao;
import com.viewcontrollers.PageViews;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Collections;

@SpringBootApplication(exclude=DataSourceAutoConfiguration.class)
@ComponentScan(basePackages = {"com.database" , "com.messagecontrol", "com.viewcontrollers"})
@ImportResource({"classpath*:beans.xml"})
public class MainClass  {

    private static final Logger logger = LoggerFactory.getLogger(MainClass.class);

    public static void main(String[] args) {

        String throwMQException;
        String throwDBException;

        SpringApplication app = new SpringApplication(MainClass.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "8080"));

        ApplicationContext ctx = new AnnotationConfigApplicationContext(GeneralConfig.class);
        PageViews pageViews = (PageViews) ctx.getBean("getPageViews");

        // use the command line arguments for testing
        // args[0] sets TestMQException to true and throws an exception
        // when attempting to post a message to the message bus
        // args[1] sets TestDBException to true and throws an exception
        // when attempting to write to the database
        try {throwMQException = args[0];} catch (Exception e) {throwMQException = "false";}
        try {throwDBException = args[1];} catch (Exception e) {throwDBException = "false";}
        pageViews.setThrowMQException(throwMQException);
        pageViews.setThrowDBException(throwDBException);
        logger.info("Starting app, throwMQException = " + throwMQException + ", throwDBException = " + throwDBException);
        app.run(args);
    }
}