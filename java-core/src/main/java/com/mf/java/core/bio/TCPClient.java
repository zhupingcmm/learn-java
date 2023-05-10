package com.mf.java.core.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        while (true) {
            Socket socket = new Socket("127.0.0.1", 9999);
            OutputStream outputStream  = socket.getOutputStream();
            System.out.println("请输入：");
            Scanner scanner = new Scanner(System.in);
            String msg = scanner.nextLine();
            outputStream.write(msg.getBytes());

            // 阻塞
            InputStream is = socket.getInputStream();
            byte[] b = new byte[20];
            is.read(b);
            System.out.println("老板说:" + new String(b).trim());
            //4.关闭
            socket.close();
        }
    }
}
