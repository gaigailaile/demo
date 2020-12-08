package com.gai.designpattern.Singleton;

public class SingletonObject {

    private static SingletonObject singletonObject = new SingletonObject();

    private SingletonObject(){}

    public static SingletonObject getInstance(){
        return singletonObject;
    }

    public void show(){
        System.out.println("hello world");
    }
}
