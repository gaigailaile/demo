package com.gai.designpattern.prototype_mode;

public class Rectangle extends Shape {
    public Rectangle(){
        type = "Rectangle";
    }

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
