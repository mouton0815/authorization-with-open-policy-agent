package com.example.authz.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

//@EnableWebSecurity
@SpringBootApplication
@ComponentScan("com.example.authz")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
