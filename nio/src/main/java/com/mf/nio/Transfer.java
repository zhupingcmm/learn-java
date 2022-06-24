package com.mf.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class Transfer {
    public static void main(String[] args) {
        Transfer transfer = new Transfer();
        File source =  new File("C:\\test\\test\\source\\UCMDB_Server_11.6.10.32.exe");
        File des =  new File("C:\\test\\test\\des\\UCMDB_Server_11.6.10.32.exe");
        transfer.transfer(source, new File("C:\\test\\test\\des\\3.exe"));
        transfer.transferWithNio(source, new File("C:\\test\\test\\des\\4.exe"));
    }

    public long transferWithNio(File source, File des) {
        System.out.println("nio transfor start");
        long start = System.currentTimeMillis();
        FileOutputStream fileOutputStream = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(source);
            fileOutputStream = new FileOutputStream(des);

            FileChannel readChannel = fileInputStream.getChannel();
            FileChannel writeChannel = fileOutputStream.getChannel();

            ByteBuffer byteBuffer = ByteBuffer.allocate(1024*1024);
            while (readChannel.read(byteBuffer) != -1) {
                byteBuffer.flip();
                writeChannel.write(byteBuffer);
                byteBuffer.clear();

            }

            readChannel.close();
            writeChannel.close();


        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                fileInputStream.close();
                fileOutputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        long end = System.currentTimeMillis();

        System.out.println("take time:" + (end -start));

        return end -start;
    }
    public long transfer(File source, File des) {
        System.out.println("bio transfor start");
        long start = System.currentTimeMillis();
        FileInputStream inputStream = null;
        FileOutputStream outputStream = null;
        try {
            if (!des.exists()) des.createNewFile();

            inputStream = new FileInputStream(source);
            outputStream = new FileOutputStream(des);

            byte [] bytes = new byte[1024*1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {

            try {
                inputStream.close();
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        long end = System.currentTimeMillis();
        System.out.println("Bio take time:" + (end -start));

        return end - start;
    }
}
