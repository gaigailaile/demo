package com.gai.designpattern.Singleton;

public class SingletonDemo {
    public static void main(String[] args) {
        SingletonObject singletonObject = SingletonObject.getInstance();
        singletonObject.show();
    }
}
