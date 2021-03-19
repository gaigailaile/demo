package com.gai.designpattern.abstractfactory.shape;

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("draw Square");
    }
}
