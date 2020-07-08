package com.example.spring_mvc_demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main entry point for our application. Under the hood, the SpringApplication.run() will actually perform
 * various initialization steps and start an embedded servlet container for us.
 *
 * In typical SpringBoot applications, the main class will often be this simple - and should be kept that way.
 * All application configuration should be factored out into separate classes.
 *
 * The SpringBootApplication annotation does expose several useful parameters for customizing the initialization,
 * but in our simple example we just use the defaults.
 * */
@SpringBootApplication(proxyBeanMethods = false)
public class SpringMvcDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringMvcDemoApplication.class, args);
    }
}
