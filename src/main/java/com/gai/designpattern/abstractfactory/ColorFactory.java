package com.gai.designpattern.abstractfactory;

import com.gai.designpattern.abstractfactory.color.Blue;
import com.gai.designpattern.abstractfactory.color.Color;
import com.gai.designpattern.abstractfactory.color.Green;
import com.gai.designpattern.abstractfactory.color.Red;
import com.gai.designpattern.abstractfactory.shape.Shape;

public class ColorFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        switch (color){
            case "RED":
                return new Red();
            case "GREEN":
                return new Green();
            case "BLUE":
                return new Blue();
            default:
                return null;
        }
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
