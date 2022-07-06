package com.mf.netty.gateway;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class NettyGateway {
    public static void main(String[] args) {
//        int port = 8888;
//        NettyServer server = new NettyServer(port);
//        server.run();
        new ClassPathXmlApplicationContext("applicationContext.xml");

    }
}
