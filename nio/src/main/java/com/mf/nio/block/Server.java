package com.mf.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Server {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel server = ServerSocketChannel.open();
        server.bind(new InetSocketAddress(6565));

        FileChannel fileChannel = FileChannel.open(Paths.get("2.png"), StandardOpenOption.WRITE, StandardOpenOption.CREATE);

        SocketChannel client = server.accept();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (client.read(byteBuffer) != -1) {
            byteBuffer.flip();
            fileChannel.write(byteBuffer);
            byteBuffer.clear();
        }

        // 图片保存完成，想要告诉客户端，图片已经上传成功
        byteBuffer.put("img is success".getBytes());
        byteBuffer.flip();
        client.write(byteBuffer);
        byteBuffer.clear();

        //关闭通道
        fileChannel.close();
        server.close();
        client.close();

    }
}
