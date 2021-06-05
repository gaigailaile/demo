package com.gai.aio.readerandwriter;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;

public class Reader implements ReaderCallback {
    private ByteBuffer byteBuffer;
    public Reader(ByteBuffer byteBuffer) {
        System.out.println("An reader has been created!");
        this.byteBuffer = byteBuffer;
    }
    @Override
    public void completed(Integer result, AsynchronousSocketChannel socketChannel) {
        System.out.println(String.format("Reader name : %s ", Thread.currentThread().getName()));
        byteBuffer.flip();
        System.out.println("Message size : " + result);
        if (result != null && result < 0) {
            try {
                socketChannel.close();
            } catch (IOException e) {
                System.out.println(e);
            }
            return;
        }
        try {
            SocketAddress localAddress = socketChannel.getLocalAddress();
            SocketAddress remoteAddress = socketChannel.getRemoteAddress();
            System.out.println("localAddress : " + localAddress.toString());
            System.out.println("remoteAddress : " + remoteAddress.toString());
            socketChannel.write(byteBuffer, socketChannel, new Writer(byteBuffer));
        } catch (IOException e) {
            System.out.println(e);
        }
        ByteBuffer duplicate = byteBuffer.duplicate();
        CharBuffer decode = Charset.defaultCharset().decode(duplicate);
        System.out.println("Receive message from client : " + decode.toString());
    }
    @Override
    public void failed(Throwable exc, AsynchronousSocketChannel attachment) {
        System.out.println(exc);
        throw new RuntimeException(exc);
    }
}
