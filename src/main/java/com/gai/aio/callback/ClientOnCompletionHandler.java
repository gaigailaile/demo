package com.gai.aio.callback;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.Random;
import java.util.concurrent.ExecutionException;

public class ClientOnCompletionHandler {
    static final int DEFAULT_PORT = 7777;
    static final String IP = "127.0.0.1";

    public static void main(String[] args) {
        try (final AsynchronousSocketChannel socketChannel = AsynchronousSocketChannel.open()) {
            if (socketChannel.isOpen()) {
                socketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 128 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_SNDBUF, 128 * 1024);
                socketChannel.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
                socketChannel.connect(new InetSocketAddress(IP, DEFAULT_PORT), null, new CompletionHandler<Void, Void>() {
                    final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                    @Override
                    public void completed(Void result, Void attachment) {
                        try {
                            System.out.println("Successfully connected at : " + socketChannel.getRemoteAddress());
                            socketChannel.write(ByteBuffer.wrap("Hello Server！".getBytes())).get();
                            while (socketChannel.read(buffer).get() != -1) {
                                buffer.flip();
                                ByteBuffer duplicate = buffer.duplicate();
                                CharBuffer decode = Charset.defaultCharset().decode(duplicate);
                                System.out.println(decode.toString());
//                                只要还有多余位置就可以继续从通道读入buffer，但是其实没必要，除非你要保留上一次通信的信息，一般全清空即可
//                                if (buffer.hasRemaining()) {
//                                    buffer.compact();
//                                } else {
                                buffer.clear();
//                                }
                                int r = new Random().nextInt(1000);
                                if (r == 50) {
                                    System.out.println("Client closed!");
                                    break;
                                } else {
                                    socketChannel.write(ByteBuffer.wrap("Random number ".concat(String.valueOf(r)).getBytes())).get();
                                }
                            }
                        } catch (IOException | InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                socketChannel.close();
                            } catch (IOException e) {
                                System.out.println(e);
                            }

                        }
                    }

                    @Override
                    public void failed(Throwable exc, Void attachment) {
                        System.out.println("Connection cannot be established!");
                        throw new UnsupportedOperationException("Connection cannot be established!");
                    }
                });
                //如果没有可读取的数据，那么返回-1，该方法阻塞直到有可读取数据
                System.in.read();
            } else {
                System.out.println("The asynchronous socket channel cannot be opened!");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}