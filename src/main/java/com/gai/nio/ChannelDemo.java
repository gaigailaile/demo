package com.gai.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class ChannelDemo {

    public static void indirectRead(String file){
        FileInputStream is = null;
        FileChannel channel = null;
        try {
            is = new FileInputStream(file);
            channel = is.getChannel();
            //创建一个读数据缓冲区对象
            ByteBuffer buf=ByteBuffer.allocate(3);
            int read = 0;
            while ((read = channel.read(buf)) > 0){
                System.out.println("read: " + read);
                buf.flip();
                int limit = buf.limit();
                byte[] bytes = new byte[limit];
                buf.get(bytes,0,limit);
                System.out.println(new String(bytes));
                buf.clear();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(is != null){
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void indirectWrite(String file,String msg){
        FileOutputStream os = null;
        FileChannel channel = null;
        try {
            os = new FileOutputStream(file);
            channel = os.getChannel();
            ByteBuffer buf=ByteBuffer.allocate(1024);
            buf.put(msg.getBytes());
            buf.flip();
            channel.write(buf);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(channel != null){
                try {
                    channel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(os != null){
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void indirectCpFile(String filePath,String targetFilePath) throws IOException {
        Long startTime = System.currentTimeMillis();

        FileInputStream is = new FileInputStream(filePath);
        FileOutputStream os = new FileOutputStream(targetFilePath);
        FileChannel inChannel = is.getChannel();
        FileChannel outChannel = os.getChannel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        while (inChannel.read(buffer) != -1) {
            buffer.flip();
            outChannel.write(buffer);
            buffer.clear();
        }

        inChannel.close();
        is.close();
        outChannel.close();
        os.close();

        Long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime)/1000 + "秒");
    }

    public static void directCpFile(String filePath,String targetFilePath) throws IOException {
        long startTime = System.currentTimeMillis();
        FileChannel inChannel = FileChannel.open(Paths.get(filePath), StandardOpenOption.READ);
        FileChannel outChannel = FileChannel.open(Paths.get(targetFilePath), StandardOpenOption.WRITE,
                StandardOpenOption.READ, StandardOpenOption.CREATE);

        // 内存映射文件
        MappedByteBuffer inMappedByteBuf = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, inChannel.size());
        MappedByteBuffer outMappedByteBuffer = outChannel.map(FileChannel.MapMode.READ_WRITE, 0, inChannel.size());

        // 直接对缓冲区进行数据的读写操作
        byte[] dsf = new byte[inMappedByteBuf.limit()];
        inMappedByteBuf.get(dsf);
        outMappedByteBuffer.put(dsf);
        inChannel.close();
        outChannel.close();
        long endTime = System.currentTimeMillis();
        System.out.println((endTime-startTime)/1000 + "秒");
    }

    public static void main(String[] args) throws IOException {
//        ChannelDemo.indirectRead("/Users/gaidongxu/IdeaProjects/demo/note.txt");
//        ChannelDemo.indirectWrite("/Users/gaidongxu/IdeaProjects/demo/note.txt","盖东旭");
        System.out.println("非直接缓冲区方式移动文件开始---------");
        ChannelDemo.indirectCpFile("/Users/gaidongxu/Downloads/0022-每特教育(蚂蚁课堂)-Nginx与高可用/第四节(Nginx + Keepalived环境搭建).mp4",
                "/Users/gaidongxu/Downloads/demo/a.mp4");
        System.out.println("直接缓冲区方式移动文件开始---------");
        ChannelDemo.directCpFile("/Users/gaidongxu/Downloads/0022-每特教育(蚂蚁课堂)-Nginx与高可用/第四节(Nginx + Keepalived环境搭建).mp4",
                "/Users/gaidongxu/Downloads/demo/a.mp4");
    }
}
