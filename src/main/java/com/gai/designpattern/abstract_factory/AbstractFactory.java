package com.gai.designpattern.abstract_factory;

import com.gai.designpattern.abstract_factory.color.Color;
import com.gai.designpattern.abstract_factory.shape.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);
}
