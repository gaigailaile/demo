package com.gai.io.socket;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1",8080);
        OutputStream outputStream = socket.getOutputStream();
        byte[] data = "hello world".getBytes();
        outputStream.write(data);
    }
}
