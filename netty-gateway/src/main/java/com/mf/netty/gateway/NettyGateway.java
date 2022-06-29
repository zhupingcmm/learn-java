package com.mf.netty.gateway;

import java.util.Arrays;

public class NettyGateway {
    public static void main(String[] args) {
        int port = 8888;
        NettyServer server = new NettyServer(port, Arrays.asList("http://localhost:8801"));
        server.run();

    }
}
