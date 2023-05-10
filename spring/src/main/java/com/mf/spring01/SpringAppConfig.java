package com.mf.spring01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(value = "com.mf")
public class SpringAppConfig {
    @Bean
    public Student createStudent() {
        return new Student(1, "ZP", "PP");
    }
}
