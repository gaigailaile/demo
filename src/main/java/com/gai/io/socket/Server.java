package com.gai.io.socket;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    public static void syncServer(int port) throws IOException {
        ServerSocket socket = new ServerSocket(port);
        Socket accept = socket.accept();
        InputStream inputStream = accept.getInputStream();
        byte[] data = new byte[1024];
        int len = inputStream.read(data);
        String info = new String(data,0,len);
        System.out.println("我是服务端,客户端说的是:" + info);
    }

    public static void pseudoAsyncThread(int port) throws IOException {
        ServerSocket socket = new ServerSocket(port);
        while (true){
            Socket accept = socket.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    InputStream inputStream = null;
                    try {
                        inputStream = accept.getInputStream();
                        byte[] data = new byte[1024];
                        int len = inputStream.read(data);
                        String info = new String(data,0,len);
                        System.out.println("我是服务端,客户端说的是:" + info);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void pseudoAsyncThreadPool(int port) throws IOException {
        ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
        ServerSocket socket = new ServerSocket(port);
        while (true){
            Socket accept = socket.accept();
            newCachedThreadPool.execute(new Runnable() {
                @Override
                public void run() {
                    InputStream inputStream = null;
                    try {
                        inputStream = accept.getInputStream();
                        byte[] data = new byte[1024];
                        int len = inputStream.read(data);
                        String info = new String(data,0,len);
                        System.out.println("我是服务端,客户端说的是:" + info);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static void main(String[] args) throws IOException {
        Server.syncServer(8080);
    }
}
