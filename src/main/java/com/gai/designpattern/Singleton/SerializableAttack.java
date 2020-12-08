package com.gai.designpattern.Singleton;

import org.apache.commons.lang3.SerializationUtils;

public class SerializableAttack {
    public static void main(String[] args) {
        StaticInsideTypeSingleton staticInsideTypeSingleton = StaticInsideTypeSingleton.getInstance();

        //通过反序列化获取对象
        byte[] singletonByte = SerializationUtils.serialize(staticInsideTypeSingleton);
        StaticInsideTypeSingleton singleton = SerializationUtils.deserialize(singletonByte);

        System.out.println("SerializableAttack: " + (staticInsideTypeSingleton == singleton));
    }
}
