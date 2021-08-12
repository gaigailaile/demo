package com;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class RunDemo {
//    public static void main(String[] args) {
//        List<Long> list = Arrays.asList(1L,2L,3L,2L,128L,300L,-128L,300L,-128L);
//        List<Long> longs = list.stream().distinct().collect(Collectors.toList());
//        System.out.println("size: " + longs.size() + " " +longs.toString());
//    }

    public static void fileDelete(File file) {
        System.out.println("模拟他人合并代码4");
        if(file.isDirectory()){
            File[] childrenFiles = file.listFiles();
            for (File childFile:childrenFiles){
                fileDelete(childFile);
            }
        }
        //删除 文件、空目录
        file.delete();
    }

    public static void main(String[] args) {
        fileDelete(new File("/Users/gaidongxu/Desktop/面试/a"));
    }
}
