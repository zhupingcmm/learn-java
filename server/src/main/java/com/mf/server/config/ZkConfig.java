package com.mf.server.config;


import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "zookeeper")
@Data
public class ZkConfig {
    private String host = "localhost";
    private int port = 2181;
}
