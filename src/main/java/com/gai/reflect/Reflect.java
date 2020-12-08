package com.gai.reflect;

import java.lang.reflect.Field;

public class Reflect {
//    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Class<?> clazz = Class.forName("com.gai.reflect.People");
//        Method[] methods = clazz.getDeclaredMethods();
//        for (Method method : methods) {
//            System.out.println(method.getName() + " ----------------");
//        }
//        Constructor<?> constructor = clazz.getConstructor(String.class);
//        Object newInstance = constructor.newInstance("盖东旭");
//        People people = (People) newInstance;
//        System.out.println(people.getName());
//    }

//    public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
//        Class<?> clazz = Class.forName("com.gai.reflect.People");
//        Method[] methods = clazz.getDeclaredMethods();
//        System.out.println("获取该类所有方法名:");
//        for (Method method : methods) {
//            System.out.println(method.getName() + " ---------------- " + method.getGenericReturnType());
//        }
//        Method[] allMethods = clazz.getMethods();
//        System.out.println("获取该类所有方法名(包括继承的方法):");
//        for (Method method : allMethods) {
//            System.out.println(method.getName() + " ---------------- " + method.getGenericReturnType());
//        }
//
//        System.out.println("获取属性名:");
//        Field[] fields = clazz.getDeclaredFields();
//        for (Field field : fields) {
//            System.out.println(field.getName() + " ----------------- " + field.getGenericType());
//        }
//    }

    public static void main(String[] args) throws ClassNotFoundException, NoSuchFieldException, IllegalAccessException, InstantiationException {
        Class<?> clazz = Class.forName("com.gai.reflect.People");
        Field name = clazz.getDeclaredField("name");
        Object object = clazz.newInstance();
        name.setAccessible(true);
        name.set(object,"gaidongxu");
        String gai = (String) name.get(object);
        System.out.println(gai);
//        People people = (People) object;
//        System.out.println(people.getName());
    }
}
