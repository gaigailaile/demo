package com.gai.network.udp;

import java.io.IOException;
import java.net.*;

public class UDPClient {
    public static void main(String[] args) throws IOException {
        byte[] data = "UDP 测试demo".getBytes();
        DatagramPacket packet = new DatagramPacket(data,data.length,InetAddress.getByName("127.0.0.1"),8800);
        DatagramSocket socket = new DatagramSocket(9999);
        socket.send(packet);

        byte[] data2 = new byte[1024];
        DatagramPacket packet2 = new DatagramPacket(data2, data2.length);
        socket.receive(packet2);
        String reply = new String(data2, 0, packet2.getLength());
        System.out.println("我是客户端，服务器说：" + reply);
        socket.close();
    }
}
