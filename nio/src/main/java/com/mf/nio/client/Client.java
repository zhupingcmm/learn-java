package com.mf.nio.client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.configureBlocking(false);
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()) {
                System.out.println("connect to server will take some time!!");
            }
        }

        String str = "hello,zp";
        ByteBuffer byteBuffer = ByteBuffer.wrap(str.getBytes());

        socketChannel.write(byteBuffer);
        System.in.read();
    }
}
