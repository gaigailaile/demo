package com.gai.designpattern.abstract_factory;

import com.gai.designpattern.abstract_factory.color.Color;
import com.gai.designpattern.abstract_factory.shape.Circle;
import com.gai.designpattern.abstract_factory.shape.Rectangle;
import com.gai.designpattern.abstract_factory.shape.Shape;
import com.gai.designpattern.abstract_factory.shape.Square;

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
