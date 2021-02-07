package com.gai.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class ByteBufferDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        ByteBufferDemo.getInfo(byteBuffer);
        byteBuffer.put("Hello World".getBytes());
        ByteBufferDemo.getInfo(byteBuffer);

        //读取数据，调用flip()开启读模式
        System.out.println("开始读数据!");
        byteBuffer.flip();
        //观察现在三个参数的位置
        ByteBufferDemo.getInfo(byteBuffer);
        byte[] bytes = new byte[5];
        byteBuffer.get(bytes,0,bytes.length);
        System.out.println(new String(bytes));
        ByteBufferDemo.getInfo(byteBuffer);

        bytes = new byte[11];
        byteBuffer.get(bytes,0,byteBuffer.limit()-5);
        System.out.println(new String(bytes));
        ByteBufferDemo.getInfo(byteBuffer);
    }

    public static void getInfo(Buffer buffer){
        System.out.println("position : " + buffer.position());
        System.out.println("limit : " + buffer.limit());
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("---------------------------------");
    }
}
