package com.gai.designpattern.Singleton;

public class DclSingleton {
    private static DclSingleton instance;

    private DclSingleton(){}

    public static DclSingleton getInstance(){
        if(instance == null){
            synchronized (DclSingleton.class){
                if(instance == null){
                    instance = new DclSingleton();
                }
            }
        }
        return instance;
    }
}
