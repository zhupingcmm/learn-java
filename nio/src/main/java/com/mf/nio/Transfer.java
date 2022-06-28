package com.mf.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Transfer {
    public static void main(String[] args) throws IOException {
        Transfer transfer = new Transfer();
        File source =  new File("C:\\test\\test\\source\\UCMDB_Server_11.6.10.32.exe");
        File des =  new File("C:\\test\\test\\des\\UCMDB_Server_11.6.10.32.exe");
        transfer.transfer(source, new File("C:\\test\\test\\des\\3.exe"));
        transfer.transferWithNio(source, new File("C:\\test\\test\\des\\4.exe"));
//        transfer.transferWithNioCache(source, des);
    }

    public long transferWithNioCache(File source, File des) throws IOException {
        System.out.println("nio transfer with cache start");
        long start = System.currentTimeMillis();

        FileChannel inChannel = FileChannel.open(Paths.get(source.getAbsolutePath()),StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(des.getAbsolutePath()), StandardOpenOption.CREATE, StandardOpenOption.WRITE);
        //内存映射文件
        MappedByteBuffer inMapperBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());

        MappedByteBuffer outMapperBuf = outChannel.map(FileChannel.MapMode.READ_WRITE, 0,inChannel.size());

        byte [] dist = new byte[inMapperBuf.limit()];
        inMapperBuf.get(dist);
        outMapperBuf.put(dist);
        long end = System.currentTimeMillis();
        System.out.println("take time:" + (end -start));

        return end -start;
    }
    public long transferWithNio(File source, File des) {
        System.out.println("nio transfer start");
        long start = System.currentTimeMillis();
        try (FileInputStream fileInputStream = new FileInputStream(source);
             FileOutputStream fileOutputStream = new FileOutputStream(des)){

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


        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();

        System.out.println("take time:" + (end -start));

        return end -start;
    }
    public long transfer(File source, File des) {
        System.out.println("bio transfor start");
        long start = System.currentTimeMillis();

        try ( FileInputStream inputStream = new FileInputStream(source); FileOutputStream outputStream = new FileOutputStream(des)){
            if (!des.exists()) des.createNewFile();
            byte [] bytes = new byte[1024*1024];
            int len;
            while ((len = inputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, len);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        long end = System.currentTimeMillis();
        System.out.println("Bio take time:" + (end -start));

        return end - start;
    }
}
