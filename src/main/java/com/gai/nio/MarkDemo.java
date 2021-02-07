package com.gai.nio;

import java.nio.Buffer;
import java.nio.ByteBuffer;

public class MarkDemo {
    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.put("Hello World".getBytes());

        byteBuffer.flip();
        //在此处打个标记
        byteBuffer.mark();
        byte[] bytes = new byte[5];
        byteBuffer.get(bytes,0,bytes.length);
        System.out.println(new String(bytes));
        MarkDemo.getInfo(byteBuffer);

        //调用reset方法重置position的位置
        byteBuffer.reset();
        MarkDemo.getInfo(byteBuffer);
        byteBuffer.get(bytes,0,bytes.length);
        System.out.println(new String(bytes));
    }

    public static void getInfo(Buffer buffer){
        System.out.println("position : " + buffer.position());
        System.out.println("limit : " + buffer.limit());
        System.out.println("capacity : " + buffer.capacity());
        System.out.println("---------------------------------");
    }
}
