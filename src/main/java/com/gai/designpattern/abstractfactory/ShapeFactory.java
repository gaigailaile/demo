package com.gai.designpattern.abstractfactory;

import com.gai.designpattern.abstractfactory.color.Color;
import com.gai.designpattern.abstractfactory.shape.Circle;
import com.gai.designpattern.abstractfactory.shape.Rectangle;
import com.gai.designpattern.abstractfactory.shape.Shape;
import com.gai.designpattern.abstractfactory.shape.Square;

public class ShapeFactory extends AbstractFactory{
    @Override
    public Color getColor(String color) {
        return null;
    }

    public Shape getShape(String shapeType){
        switch (shapeType){
            case "CIRCLE":
                return new Circle();
            case "RECTANGLE":
                return new Rectangle();
            case "SQUARE":
                return new Square();
            default:
                return null;
        }
    }
}
