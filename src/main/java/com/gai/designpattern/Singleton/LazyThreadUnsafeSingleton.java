package com.gai.designpattern.Singleton;

public class LazyThreadUnsafeSingleton {
    private static LazyThreadUnsafeSingleton instance;

    private LazyThreadUnsafeSingleton(){};

    public static LazyThreadUnsafeSingleton getInstance(){
        if(instance == null){
            instance = new LazyThreadUnsafeSingleton();
        }
        return instance;
    }
}
