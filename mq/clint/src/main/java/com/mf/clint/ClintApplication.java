package com.mf.clint;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@EnableJms
@SpringBootApplication
public class ClintApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClintApplication.class, args);
    }

}
