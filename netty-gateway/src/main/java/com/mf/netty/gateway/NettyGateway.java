package com.mf.netty.gateway;

import com.mf.netty.gateway.inbound.HttpInboundServer;

import java.util.Arrays;

public class NettyGateway {
    public static void main(String[] args) {
        int port = 8888;
        HttpInboundServer server = new HttpInboundServer(port, Arrays.asList("http://localhost:8801"));
        server.run();

    }
}
