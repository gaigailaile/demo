package com.gai.io.charstream;

import java.io.*;

public class CharStreamDemo {
    public static void reader(String file){
        Reader reader = null;
        try {
            reader = new FileReader(file);
            int read = 0;
            char[] chars = new char[512];
            while ((read = reader.read(chars)) > 0){
                System.out.println(new String(chars,0,read));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writer(String file,String msg){
        Writer writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
//        CharStreamDemo.reader("/Users/gaidongxu/IdeaProjects/demo/note.txt");
        CharStreamDemo.writer("/Users/gaidongxu/IdeaProjects/demo/note.txt","字符流测试");
    }
}
