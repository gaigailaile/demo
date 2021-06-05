package com.gai.aio.readerandwriter;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ClientOnReaderAndWriter {
    static final int DEFAULT_PORT = 7777;
    static final String IP = "127.0.0.1";
    static ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    public static void main(String[] args) {
        try (AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open()) {
            Void aVoid = socketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT)).get();
            if (socketChannel.isOpen()) {
                if (aVoid == null) {
                    socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
                    socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
                    socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                    socketChannel.write(ByteBuffer.wrap("Hello server".getBytes())).get();
                    while (socketChannel.read(buffer).get() != -1) {
                        buffer.flip();
                        CharBuffer decode = Charset.defaultCharset().decode(buffer);
                        System.out.println(decode.toString());
//                        如果调用的是clear()方法，position将被设回0，limit被设置成capacity的值。换句话说，Buffer被清空了。
//                        Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。
//                        如果Buffer中有一些未读的数据，调用clear()方法，数据将“被遗忘”，意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有。
//                        如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。
//                        compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。
//                        limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。
                        if (buffer.hasRemaining()) {
                            buffer.compact();
                        } else {
                            buffer.clear();
                        }
                        int r = new Random().nextInt(10000);
                        if (r == 50) {
                            break;
                        } else {
                            socketChannel.write(ByteBuffer.wrap("Random number ".concat(String.valueOf(r)).getBytes())).get();
                            System.out.println("Client send successfully!");
                        }
                    }
                } else {
                    System.out.println("The connection cannot be established!");
                }
            } else {
                System.out.println("The asynchronous socket-channel cannot be opened!");
            }
        } catch (IOException | InterruptedException | ExecutionException e) {
            System.out.println(e);
        }
    }
}