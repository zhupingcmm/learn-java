package com.mf.java.core.nio;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class TestNIO {
    public static void main(String[] args) throws IOException {
        FileOutputStream outputStream = new FileOutputStream("basic.txt");
        FileChannel fileChannel  = outputStream.getChannel();

        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

        byteBuffer.put("hello wolrd".getBytes());
        byteBuffer.flip();
        fileChannel.write(byteBuffer);
        outputStream.close();
    }
}
