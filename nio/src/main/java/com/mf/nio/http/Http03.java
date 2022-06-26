package com.mf.nio.http;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class Http03 {
    public static void main(String[] args) throws IOException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(
                5, 10,
                5, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>()
        );

        ServerSocket serverSocket = new ServerSocket(8803);

        while (true) {
            Socket socket = serverSocket.accept();

            executor.submit(() -> {
                try {
                    service(socket);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });

        }

    }

    private static void service(Socket socket) throws IOException {
        PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
        printWriter.println("HTTP/1.1 200 OK");
        printWriter.println("Content-Type:text/html;charset=utf-8");
        String body = "hello,nio2";
        printWriter.println("Content-Length:" + body.getBytes().length);
        printWriter.println();
        printWriter.write(body);
        printWriter.close();
        socket.close();

    }
}
