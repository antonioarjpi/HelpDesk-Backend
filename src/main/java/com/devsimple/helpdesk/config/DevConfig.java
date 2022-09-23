package com.devsimple.helpdesk.config;

import com.devsimple.helpdesk.service.DatabaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    @Autowired
    private DatabaseService service;

    @Value("{spring.jpa.hibernate.ddl-auto}")
    private String value;

    @Bean
    public boolean instanceDB() {
        if (value.equals("create")) {
            this.service.instanceDatabase();
        }
        return false;
    }
}
