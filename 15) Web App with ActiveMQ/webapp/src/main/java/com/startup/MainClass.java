package com.startup;

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

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(MainClass.class);
        app.setDefaultProperties(Collections
                .singletonMap("server.port", "80"));
        app.run(args);

        // SpringApplication.run(MainClass.class, args);
    }

}