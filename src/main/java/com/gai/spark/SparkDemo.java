package com.gai.spark;

import org.apache.spark.launcher.SparkLauncher;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class SparkDemo {
//    public static void main(String[] args) throws IOException, InterruptedException {
//        Map<String, String> env = new HashMap();
//        env.put("SPARK_HOME", "/usr/local/Cellar/apache-spark/3.0.1");
//        Process process = new SparkLauncher(env)
//                .setAppResource("/Users/gaidongxu/IdeaProjects/demo1/target/demo1.jar")
//                .setMainClass("com.RunDemo")
//                .setMaster("local").launch();
//        BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
//        String line = null;
//        while ((line = in.readLine()) != null) {
//            System.out.println(line);
//        }
//        int i = process.waitFor();
//        if(i == 0){
//            System.out.println("运行成功!!");
//        }else {
//            BufferedReader in1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
//            String line1 = null;
//            while ((line1 = in1.readLine()) != null) {
//                System.out.println(line1);
//            }
//        }
//    }

    public static void main(String[] args) {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec("spark-submit --class com.Env --master local /Users/gaidongxu/IdeaProjects/demo1/target/demo1.jar");
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = null;
            while ((line = in.readLine()) != null) {
                System.out.println(line);
            }
            int i = process.waitFor();
            if(i == 0){
                System.out.println("运行成功!!");
            }else {
                BufferedReader in1 = new BufferedReader(new InputStreamReader(process.getErrorStream()));
                String line1 = null;
                while ((line1 = in1.readLine()) != null) {
                    System.out.println(line1);
                }
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
