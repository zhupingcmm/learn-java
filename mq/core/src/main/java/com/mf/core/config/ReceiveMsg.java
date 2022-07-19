package com.mf.core.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.TextMessage;

@Component
@Slf4j
public class ReceiveMsg {
    @JmsListener(destination = "my.topic")
    public void receivedMsg(TextMessage message) {
        log.info("received msg::{}", message);
    }
}
