package com.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

@SpringBootApplication
public class Application {
    @PostConstruct
    void started(){
        TimeZone.setDefault(TimeZone.getTimeZone("spring.jpa.properties.hibernate.jdbc.time_zone"));
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
