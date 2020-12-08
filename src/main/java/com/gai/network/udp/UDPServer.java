package com.gai.network.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class UDPServer {
    public static void main(String[] args) throws IOException {
        /*接受客户端的信息*/
        //创建服务端DatagramSocket 指定端口
        DatagramSocket datagramSocket = new DatagramSocket(8800);
        //创建数组用于接收数据
        byte[] data = new byte[1024];
        DatagramPacket packet = new DatagramPacket(data,data.length);
        System.out.println("服务器端即将启动!!!!");
        //等待客户端传送数据，一直阻塞
        datagramSocket.receive(packet);
        //获取ip
        InetAddress inetAddress = packet.getAddress();
        //获取端口
        int port = packet.getPort();
        System.out.println("数据来源  " + inetAddress.toString() + ":" + port);
        String info = new String(packet.getData(),0,packet.getLength());
        System.out.println("我是UDP服务端,客户端说:" + info);

        /*反馈给客户端*/
        byte[] bytes = "欢迎您!".getBytes();
        DatagramPacket datagramPacket = new DatagramPacket(bytes,bytes.length,inetAddress,port);
        datagramSocket.send(datagramPacket);
        //关闭资源
        datagramSocket.close();
    }
}
