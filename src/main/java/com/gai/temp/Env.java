package com.gai.temp;

public class Env {
    public static void main(String[] args) {
        String java = System.getenv("JAVA_HOME");
        System.out.println("JAVA_HOME:" + java);
        String scala_home = System.getenv("SCALA_HOME");
        System.out.println("SCALA_HOME:" +scala_home);
    }
}
