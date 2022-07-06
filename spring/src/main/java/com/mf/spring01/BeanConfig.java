package com.mf.spring01;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean(name = "person")
    public Person createPerson() {
        return new Person("zp", 18);
    }
}
