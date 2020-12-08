package com.gai.network.tcp;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8080);
        OutputStream outputStream = socket.getOutputStream();
        byte[] data = "TCP 联系Demo".getBytes();
        outputStream.write(data);

        //接收服务端回应
        InputStream inputStream = socket.getInputStream();
        byte[] reply = new byte[1024];
        int len = inputStream.read(reply);
        String info = new String(reply,0,len);
        System.out.println("服务端回复:" + info);

        socket.close();
    }
}
