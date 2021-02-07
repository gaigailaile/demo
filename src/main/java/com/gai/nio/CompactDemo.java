package com.gai.nio;

import java.nio.ByteBuffer;

public class CompactDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("Hello World".getBytes());

        byteBuffer.flip();
        byte[] bytes = new byte[5];
        byteBuffer.get(bytes,0,bytes.length);
        System.out.println(new String(bytes));

        byteBuffer.compact();
        System.out.println("position: " + byteBuffer.position());
        byteBuffer.put(" gaidongxu".getBytes());

        byteBuffer.flip();
        int limit = byteBuffer.limit();
        bytes = new byte[limit];
        byteBuffer.get(bytes,0,limit);
        System.out.println(new String(bytes));
    }
}
