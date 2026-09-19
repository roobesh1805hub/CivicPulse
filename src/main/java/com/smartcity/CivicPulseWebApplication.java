package com.smartcity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CivicPulseWebApplication {

    public static void main(String[] args) {

        SpringApplication.run(
                CivicPulseWebApplication.class,
                args
        );

    }
}