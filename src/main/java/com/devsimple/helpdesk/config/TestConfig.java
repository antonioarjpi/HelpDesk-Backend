package com.devsimple.helpdesk.config;

import com.devsimple.helpdesk.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestConfig {

    @Autowired
    private DatabaseService service;

    @Bean
    public void instanceDB(){
        this.service.instanceDatabase();
    }
}
