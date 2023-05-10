package com.mf.java.core.bio;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {

    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动, 端口 为 9999");

        ServerSocket serverSocket = new ServerSocket(9999);
        while (true) {
            Socket socket = serverSocket.accept();
            InputStream in = socket.getInputStream();
            byte [] bytes = new byte[10];
            in.read(bytes);
            String clientIp = socket.getInetAddress().getHostAddress();
            System.out.println(clientIp + "说: " + new String(bytes).trim());

           OutputStream outputStream = socket.getOutputStream();
            outputStream.write("没钱".getBytes());
            socket.close();
        }

    }
}
