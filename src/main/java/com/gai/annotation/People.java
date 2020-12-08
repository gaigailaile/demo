package com.gai.annotation;

public class People implements FullName{
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Deprecated
    public String sayHello(){
        return this.name + " 你好啊!";
    }

    @Override
    public void sayName() {
        System.out.println(name);
    }
}
