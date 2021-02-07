package com.gai.nio;

import java.nio.ByteBuffer;

public class ClearDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("Hello World".getBytes());

        byteBuffer.flip();
        byte[] bytes = new byte[5];
        byteBuffer.get(bytes,0,bytes.length);
        System.out.println(new String(bytes));

        byteBuffer.clear();
        System.out.println("position: " + byteBuffer.position());
        byteBuffer.put("h".getBytes());

        //证明clear之后数据还在
        bytes = new byte[11];
        byteBuffer.position(0);
        byteBuffer.get(bytes,0,11);
        System.out.println(new String(bytes));

        //正常的clear情况
        byteBuffer.clear();
        byteBuffer.put("h".getBytes());

        byteBuffer.flip();
        int limit = byteBuffer.limit();
        bytes = new byte[limit];
        byteBuffer.get(bytes,0,limit);
        System.out.println(new String(bytes));
    }
}
