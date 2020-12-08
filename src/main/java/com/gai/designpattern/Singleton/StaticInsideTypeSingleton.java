package com.gai.designpattern.Singleton;

import java.io.Serializable;

public class StaticInsideTypeSingleton implements Serializable {
    private static class StaticInsideType{
        private static StaticInsideTypeSingleton INSTANCE = new StaticInsideTypeSingleton();
    }

    private StaticInsideTypeSingleton(){}

    public static StaticInsideTypeSingleton getInstance(){
        return StaticInsideType.INSTANCE;
    }
}
