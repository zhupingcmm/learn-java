package com.mf.clint.config;


import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.jms.Topic;

@Component
public class BeanConfig {
    @Bean
    public Topic topic () {
        return new ActiveMQTopic("my.topic");
    }
}
