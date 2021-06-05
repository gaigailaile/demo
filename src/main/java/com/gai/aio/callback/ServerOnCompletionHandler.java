package com.gai.aio.callback;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;
import java.nio.charset.Charset;
import java.util.concurrent.ExecutionException;

public class ServerOnCompletionHandler {
    static final int DEFAULT_PORT = 7777;
    static final String IP = "127.0.0.1";

    public static void main(String[] args) {
        try (final AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open()) {
            if (serverSocketChannel.isOpen()) {
                serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 4 * 1024);
                serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                serverSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));
                System.out.println("Waiting for connections...");
                serverSocketChannel.accept(null, new CompletionHandler<AsynchronousSocketChannel, Void>() {
                    final ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

                    @Override
                    public void completed(AsynchronousSocketChannel socketChannel, Void attachment) {
                        //注意接收一个连接之后，紧接着可以接收下一个连接，所以必须再次调用accept方法
                        serverSocketChannel.accept(null, this);
                        try {
                            System.out.println("Incoming connection from : " + socketChannel.getRemoteAddress());
                            while (socketChannel.read(buffer).get() != -1) {
                                buffer.flip();
                                final ByteBuffer duplicate = buffer.duplicate();
                                final CharBuffer decode = Charset.defaultCharset().decode(duplicate);
                                System.out.println(decode.toString());
                                socketChannel.write(buffer).get();
                                if (buffer.hasRemaining()) {
                                    buffer.compact();
                                } else {
                                    buffer.clear();
                                }
                            }

                        } catch (InterruptedException | ExecutionException | IOException e) {
                            System.out.println(e);
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
                        serverSocketChannel.accept(null, this);
                        throw new UnsupportedOperationException("Cannot accept connections!");
                    }
                });
                //主要是阻塞作用，因为AIO是异步的，所以此处不阻塞的话，主线程很快执行完毕，并会关闭通道
                System.in.read();
            } else {
                System.out.println("The asynchronous server-socket channel cannot be opened!");
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}