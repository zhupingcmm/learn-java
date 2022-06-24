package com.mf.nio;

import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("初始时 -->limit --->" + byteBuffer.limit());
        System.out.println("初始时 -->position --->" + byteBuffer.position());
        System.out.println("初始时 --> capacity --->" + byteBuffer.capacity());
        System.out.println("初始时 --> mark --->" + byteBuffer.mark());

        System.out.println("-----------------------");
        String str = "zp";
        byteBuffer.put(str.getBytes());

        System.out.println("put后 -->limit --->" + byteBuffer.limit());
        System.out.println("put后 -->position --->" + byteBuffer.position());
        System.out.println("put后 --> capacity --->" + byteBuffer.capacity());
        System.out.println("put后 --> mark --->" + byteBuffer.mark());

        byteBuffer.flip();

        System.out.println("-----------------------");

        System.out.println("flip后 -->limit --->" + byteBuffer.limit());
        System.out.println("flip后 -->position --->" + byteBuffer.position());
        System.out.println("flip后 --> capacity --->" + byteBuffer.capacity());
        System.out.println("flip后 --> mark --->" + byteBuffer.mark());

        System.out.println("-----------------------");
        byte[] bytes= new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println("读到的数据:" + new String(bytes, 0, bytes.length));

        System.out.println("flip后 -->limit --->" + byteBuffer.limit());
        System.out.println("flip后 -->position --->" + byteBuffer.position());
        System.out.println("flip后 --> capacity --->" + byteBuffer.capacity());
        System.out.println("flip后 --> mark --->" + byteBuffer.mark());

        System.out.println("-----------------------");

        byteBuffer.clear();
        System.out.println("clear后 -->limit --->" + byteBuffer.limit());
        System.out.println("clear后 -->position --->" + byteBuffer.position());
        System.out.println("clear后 --> capacity --->" + byteBuffer.capacity());
        System.out.println("clear后 --> mark --->" + byteBuffer.mark());


    }
}
