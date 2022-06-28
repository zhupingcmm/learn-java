package com.mf.nio.block;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Client {
    public static void main(String[] args) throws IOException {
        // 获取网络通道
        SocketChannel socketChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 6565));
        // 获取文件通道
        FileChannel fileChannel = FileChannel.open(Paths.get("C:\\test\\test\\source\\1.png"), StandardOpenOption.READ);

        // 初始化ByteBuffer
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        while (fileChannel.read(byteBuffer) != -1) {
            // 在发生之前切换成读模式
            byteBuffer.flip();
            socketChannel.write(byteBuffer);
            // 在发送完成后，清空管道中的数据，为下一次读做准备
            byteBuffer.clear();
        }

        // 告诉服务器已经写完了
        socketChannel.shutdownOutput();

        while (socketChannel.read(byteBuffer) != -1) {
            byteBuffer.flip();
            System.out.println(new String(byteBuffer.array(), 0, byteBuffer.limit()));
            byteBuffer.clear();
        }

        fileChannel.close();
        socketChannel.close();

    }
}
