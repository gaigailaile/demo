package com.gai.network.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
    public static void main(String[] args) throws IOException {
        System.out.println("服务器启动等待连接.......");
        ServerSocket socket = new ServerSocket(8080);
        Socket accept = socket.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] data = new byte[1024];
        int len = inputStream.read(data);
        String info = new String(data,0,len);
        System.out.println("我是服务端,客户端说的是:" + info);

        //回应客户端
        OutputStream outputStream = accept.getOutputStream();
        byte[] reply = "欢迎您!".getBytes();
        outputStream.write(reply);

        socket.close();
    }
}
