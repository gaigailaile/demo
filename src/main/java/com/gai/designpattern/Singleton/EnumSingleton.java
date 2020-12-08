package com.gai.designpattern.Singleton;

public enum EnumSingleton {
    INSTANCE;

    public void doSomeThing(){
        System.out.println("do Something");
    }
}

class EnumDemo{
    public static void main(String[] args) {
        EnumSingleton.INSTANCE.doSomeThing();
    }
}