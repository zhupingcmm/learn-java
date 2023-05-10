package com.mf.java.core.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Test02 {
    public static void main(String[] args) throws IOException {
        File file = new File("basic.txt");
        FileInputStream fileInputStream = new FileInputStream(file);
        FileChannel fileChannel =  fileInputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
        fileChannel.read(byteBuffer);
        System.out.println(new String(byteBuffer.array()));
        //5. 关闭
        fileInputStream.close();

    }
}
