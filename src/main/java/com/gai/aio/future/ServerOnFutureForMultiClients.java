package com.gai.aio.future;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.Charset;
import java.util.concurrent.*;

public class ServerOnFutureForMultiClients {
    static final int DEFAULT_PORT = 7777;
    static final String IP = "127.0.0.1";
    static ExecutorService taskExecutorService = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
    static ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

    public static void main(String[] args) {
        try (AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open()) {
            if (serverSocketChannel.isOpen()) {
                serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
                serverSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));
                System.out.println("Waiting for connections...");
                while (true) {
                    Future<AsynchronousSocketChannel> socketChannelFuture = serverSocketChannel.accept();
                    try {
                        final AsynchronousSocketChannel socketChannel = socketChannelFuture.get();
                        Callable<String> worker = new Callable<String>() {
                            @Override
                            public String call() throws Exception {
                                String s = socketChannel.getRemoteAddress().toString();
                                System.out.println("Incoming connection from : " + s);
                                while (socketChannel.read(buffer).get() != -1) {
                                    buffer.flip();
                                    ByteBuffer duplicate = buffer.duplicate();
                                    showMessage(duplicate);
                                    socketChannel.write(buffer).get();
                                    if (buffer.hasRemaining()) {
                                        buffer.compact();
                                    } else {
                                        buffer.clear();
                                    }
                                }
                                socketChannel.close();
                                System.out.println(s + " was successfully served!");
                                return s;
                            }
                        };
                        taskExecutorService.submit(worker);
                    } catch (InterruptedException | ExecutionException e) {
                        System.out.println(e);
                        taskExecutorService.shutdown();
                        while (!taskExecutorService.isTerminated()) {

                        }
                        break;
                    }
                }
            } else {
                System.out.println("The asynchronous server-socket channel cannot be opened!");;
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }

    protected static void showMessage(ByteBuffer buffer) {
        CharBuffer decode = Charset.defaultCharset().decode(buffer);
        System.out.println(decode.toString());
    }
}