package com.gai.designpattern.abstractfactory;

import com.gai.designpattern.abstractfactory.color.Color;
import com.gai.designpattern.abstractfactory.shape.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
