package com.mf.nio.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();

        Selector selector = Selector.open();

        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            if (selector.select(1000) ==0){
                System.out.println("等待客户端1秒，无连接");
                continue;
            }

            Set<SelectionKey> selectionKeys = selector.selectedKeys();

            for (SelectionKey selectionKey : selectionKeys) {
                if (selectionKey.isAcceptable()) {
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    System.out.println("socketChannel:" + socketChannel);
                    socketChannel.configureBlocking(false);
                    socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                }

                if (selectionKey.isReadable()) {
                   SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
                   ByteBuffer byteBuffer = (ByteBuffer) selectionKey.attachment();
                   socketChannel.read(byteBuffer);
                   System.out.println("CLIENT HAVE SEND MESSAGE " + new String(byteBuffer.array()));

                }
                selectionKeys.remove(selectionKey);
            }

        }
    }
}
