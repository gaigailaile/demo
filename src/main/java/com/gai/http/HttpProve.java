package com.gai.http;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class HttpProve {
    /* 证明Http底层使用的socket协议 */
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        System.out.println("服务器启动,可以访问!");
        while (true){
            Socket socket = serverSocket.accept();
            FileInputStream in = new FileInputStream(new File("/Users/gaidongxu/Downloads/index.html"));
            StringBuffer sb = new StringBuffer();
            sb.append("http/2.0 200 ok").append("\n\n");
            OutputStream out = socket.getOutputStream();
            byte[] data = new byte[1024];
            int len = 0;
            while ((len = in.read(data)) != -1){
                sb.append(new String(data,0,len));
            }
            out.write(sb.toString().getBytes());
            out.close();
            in.close();
        }
    }
}
