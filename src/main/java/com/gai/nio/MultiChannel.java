package com.gai.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class MultiChannel {

    public static void scatterRead(String filePath){
        //1.开启通道
        RandomAccessFile raf = null;
        FileChannel fileChannel = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            fileChannel = raf.getChannel();
            // 2.分配指定大小的指定缓冲区
            ByteBuffer buf1 = ByteBuffer.allocate(3);
            ByteBuffer buf2 = ByteBuffer.allocate(3);
            ByteBuffer[] byteBuffers = {buf1,buf2};

            while (fileChannel.read(byteBuffers) != -1){
                //把两个通道的东西读出来
                for (ByteBuffer byteBuffer : byteBuffers) {
                    byteBuffer.flip();
                    int limit = byteBuffer.limit();
                    byte[] bytes = new byte[limit];
                    byteBuffer.get(bytes,0,limit);
                    System.out.println(new String(bytes));
                    byteBuffer.clear();
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fileChannel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void gatherWrite(String filePath,String msg){
        RandomAccessFile raf = null;
        FileChannel channel = null;
        try {
            raf = new RandomAccessFile(filePath, "rw");
            channel = raf.getChannel();
            byte[] bytes = msg.getBytes();
            int length = bytes.length;
            int start = 0;
            int space = 3;
            ByteBuffer buf1 = ByteBuffer.allocate(space);
            ByteBuffer buf2 = ByteBuffer.allocate(space);
            ByteBuffer[] bufs = {buf1,buf2};

            while (length > start){
                for (ByteBuffer byteBuffer : bufs) {
                    byteBuffer.clear();
                    byteBuffer.put(bytes,start,(length-start) < space ? (length-start) : space);
                    byteBuffer.flip();
                    start += 3;
                }
                channel.write(bufs);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if(raf != null){
                try {
                    raf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void multiChannelCpFile(String filePath,String targetFilePath) throws IOException {

        Long startTime = System.currentTimeMillis();

        FileInputStream is = new FileInputStream(filePath);
        FileOutputStream os = new FileOutputStream(targetFilePath);
        FileChannel inChannel = is.getChannel();
        FileChannel outChannel = os.getChannel();

        ByteBuffer buffer1 = ByteBuffer.allocate(1024);
        ByteBuffer buffer2 = ByteBuffer.allocate(1024);
        ByteBuffer[] buffers = {buffer1,buffer2};

        while (inChannel.read(buffers) != -1) {
            for (ByteBuffer byteBuffer : buffers) {
                byteBuffer.flip();
            }
            outChannel.write(buffers);
            for (ByteBuffer byteBuffer : buffers) {
                byteBuffer.clear();
            }
        }

        inChannel.close();
        is.close();
        outChannel.close();
        os.close();

        Long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime)/1000 + "秒");
    }

    public static void main(String[] args) throws IOException {
//        MultiChannel.scatterRead("/Users/gaidongxu/IdeaProjects/demo/note.txt");
//        MultiChannel.gatherWrite("/Users/gaidongxu/IdeaProjects/demo/note.txt","盖东旭,中文!!");
        MultiChannel.multiChannelCpFile("/Users/gaidongxu/Downloads/0022-每特教育(蚂蚁课堂)-Nginx与高可用/第四节(Nginx + Keepalived环境搭建).mp4",
                "/Users/gaidongxu/Downloads/demo/a.mp4");
    }
}
