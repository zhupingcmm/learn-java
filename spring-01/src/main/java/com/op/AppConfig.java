package com.op;

import com.mf.spring01.SpringAppConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@ComponentScan("com.op")
@Import(SpringAppConfig.class)
public class AppConfig {

//    @Bean
//    public Person createPerson(){
//        return new Person("zp", 18, "shanghai");
//    }
}
