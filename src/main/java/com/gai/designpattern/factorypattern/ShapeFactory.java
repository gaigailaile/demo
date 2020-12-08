package com.gai.designpattern.factorypattern;

public class ShapeFactory {
    public Shape getShape(String shapeType){
        Shape shape = null;
        switch (shapeType){
            case "CIRCLE":
                shape = new Circle();
                break;
            case "RECTANGLE":
                shape = new Rectangle();
                break;
            case "SQUARE":
                shape = new Square();
                break;
            default:
                break;
        }
        return shape;
    }
}
