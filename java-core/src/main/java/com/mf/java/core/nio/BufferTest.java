package com.mf.java.core.nio;

import java.nio.ByteBuffer;

public class BufferTest {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        System.out.println("初始时 -> limit: " + byteBuffer.limit());
        System.out.println("初始时 -> position: " + byteBuffer.position());
        System.out.println("初始时 -> capacity: " + byteBuffer.capacity());
        System.out.println("初始时 -> mark: " + byteBuffer.mark());

        String s = "zpoo";
        byteBuffer.put(s.getBytes());
        System.out.println("put完之后 -> limit: " + byteBuffer.limit());
        System.out.println("put完之后 -> position: " + byteBuffer.position());
        System.out.println("put完之后 -> capacity: " + byteBuffer.capacity());
        System.out.println("put完之后 -> mark: " + byteBuffer.mark());

        byteBuffer.flip();

        System.out.println("flip完之后 -> limit: " + byteBuffer.limit());
        System.out.println("flip完之后 -> position: " + byteBuffer.position());
        System.out.println("flip完之后 -> capacity: " + byteBuffer.capacity());
        System.out.println("flip完之后 -> mark: " + byteBuffer.mark());

        byte [] bytes = new byte[byteBuffer.limit()];
        byteBuffer.get(bytes);
        System.out.println(new String(bytes, 0, bytes.length));
    }
}
