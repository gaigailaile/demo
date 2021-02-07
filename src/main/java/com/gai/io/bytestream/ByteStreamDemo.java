package com.gai.io.bytestream;

import java.io.*;

public class ByteStreamDemo {

    public static void inputStream(String file){
        InputStream is = null;
        try {
            is = new FileInputStream(file);
            byte[] bytes = new byte[512];
            int read = 0;
            while ((read = is.read(bytes)) > 0){
                System.out.println(new String(bytes,0,read));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void outputStream(String file,byte[] bytes){
        OutputStream os = null;
        try {
            os = new FileOutputStream(file);
            os.write(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        ByteStreamDemo.inputStream("/Users/gaidongxu/IdeaProjects/demo/note.txt");
        ByteStreamDemo.outputStream("/Users/gaidongxu/IdeaProjects/demo/note.txt","写入测试".getBytes());
    }
}
