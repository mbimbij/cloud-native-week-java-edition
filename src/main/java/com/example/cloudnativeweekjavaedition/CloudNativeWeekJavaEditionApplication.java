package com.example.cloudnativeweekjavaedition;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CloudNativeWeekJavaEditionApplication implements ApplicationRunner {

    public static void main(String[] args) {
        SpringApplication.run(CloudNativeWeekJavaEditionApplication.class, args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {

    }
}
