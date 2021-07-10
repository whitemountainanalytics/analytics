package com.config;

import com.viewcontrollers.PageViews;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GeneralConfig {

    @Bean(name = "getPageViews")
    public static PageViews getPageViews(){
        return new PageViews();
    }

}
