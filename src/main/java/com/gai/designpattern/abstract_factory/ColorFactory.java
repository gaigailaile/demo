package com.gai.designpattern.abstract_factory;

import com.gai.designpattern.abstract_factory.color.Blue;
import com.gai.designpattern.abstract_factory.color.Color;
import com.gai.designpattern.abstract_factory.color.Green;
import com.gai.designpattern.abstract_factory.color.Red;
import com.gai.designpattern.abstract_factory.shape.Shape;

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
