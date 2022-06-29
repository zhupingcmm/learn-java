package com.mf.server.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class ZkRegister {

    private final ZkConfig zkConfig;

    @Value("${spring.application.name}")
    private String applicationName;

    @PostConstruct
    private void init () {
        String zkUrl = zkConfig.getHost()+":"+ zkConfig.getPort();
        String zkRootServer = "/" + applicationName;
        ZkClient zkClient = new ZkClient(zkUrl);
        zkClient.setZkSerializer(new ZkSerializer());
        Random random = new Random();
        int num = random.nextInt(10);
        if (!zkClient.exists(zkRootServer)) {
            zkClient.createPersistent(zkRootServer);
        }

        zkClient.createEphemeral(zkRootServer + "/" + num, "http://localhost:8889");
        log.info("Success to register application to zk.");
    }
}
