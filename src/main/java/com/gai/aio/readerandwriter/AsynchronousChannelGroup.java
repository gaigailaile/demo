//package com.gai.aio.readerandwriter;
//
//import java.io.IOException;
//import java.net.InetSocketAddress;
//import java.net.StandardSocketOptions;
//import java.nio.channels.AsynchronousChannelGroup;
//import java.nio.channels.AsynchronousServerSocketChannel;
//import java.util.concurrent.ExecutorService;
//import java.util.concurrent.Executors;
//import java.util.concurrent.TimeUnit;
//
//public class ServerOnReaderAndWriterForMultiClients {
//    static final int DEFAULT_PORT = 7777;
//    static final String IP = "127.0.0.1";
//    static AsynchronousChannelGroup threadGroup = null;
//    static ExecutorService executorService = Executors.newCachedThreadPool(Executors.defaultThreadFactory());
//
//    public static void main(String[] args) {
//        try {
//            threadGroup = AsynchronousChannelGroup.withCachedThreadPool(executorService, 5);
//            //或者使用指定数量的线程池
//            //threadGroup = AsynchronousChannelGroup.withFixedThreadPool(6, Executors.defaultThreadFactory());
//        } catch (IOException e) {
//            System.out.println(e);
//        }
//        try (AsynchronousServerSocketChannel serverSocketChannel = AsynchronousServerSocketChannel.open(threadGroup)) {
//            if (serverSocketChannel.isOpen()) {
//                //服务端的通道支持两种选项SO_RCVBUF和SO_REUSEADDR，一般无需显式设置，使用其默认即可，此处仅为展示设置方法
//                //在面向流的通道中，此选项表示在前一个连接处于TIME_WAIT状态时，下一个连接是否可以重用通道地址
//                serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
//                //设置通道接收的字节大小
//                serverSocketChannel.setOption(StandardSocketOptions.SO_RCVBUF, 8 * 1024);
//                serverSocketChannel.bind(new InetSocketAddress(IP, DEFAULT_PORT));
//                System.out.println("Waiting for connections...");
//                serverSocketChannel.accept(serverSocketChannel, new Acceptor());
//                threadGroup.awaitTermination(Long.MAX_VALUE, TimeUnit.SECONDS);
////                System.in.read();
//            } else {
//                System.out.println("The connection cannot be opened!");
//            }
//        } catch (IOException | InterruptedException e) {
//            System.out.println(e);
//        }
//    }
//}