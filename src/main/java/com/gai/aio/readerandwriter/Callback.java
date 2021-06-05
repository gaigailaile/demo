package com.gai.aio.readerandwriter;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public interface Callback extends CompletionHandler<Integer, AsynchronousSocketChannel> {
    // 某种程度上说，AIO编程其实是attachment编程
    @Override
    void failed(Throwable exc, AsynchronousSocketChannel socketChannel);
    @Override
    void completed(Integer result, AsynchronousSocketChannel socketChannel);
}