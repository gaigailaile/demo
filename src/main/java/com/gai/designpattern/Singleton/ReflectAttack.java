package com.gai.designpattern.Singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class ReflectAttack {
    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        StaticInsideTypeSingleton staticInsideTypeSingleton = StaticInsideTypeSingleton.getInstance();

        //通过反射创建对象
        Class clazz = Class.forName("com.gai.designpattern.Singleton.StaticInsideTypeSingleton");
        Constructor constructor =  clazz.getDeclaredConstructor();
        constructor.setAccessible(true);
        StaticInsideTypeSingleton s = (StaticInsideTypeSingleton) constructor.newInstance();

        System.out.println("ReflectAttack: " + (staticInsideTypeSingleton == s));
    }
}
