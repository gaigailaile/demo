package com.gai.designpattern.facade_pattern;

import com.gai.designpattern.abstract_factory.shape.Circle;
import com.gai.designpattern.abstract_factory.shape.Rectangle;
import com.gai.designpattern.abstract_factory.shape.Shape;
import com.gai.designpattern.abstract_factory.shape.Square;

public class ShapeMaker {
    private Shape circle;
    private Shape rectangle;
    private Shape square;

    public ShapeMaker() {
        circle = new Circle();
        rectangle = new Rectangle();
        square = new Square();
    }

    public void drawCircle(){
        circle.draw();
    }
    public void drawRectangle(){
        rectangle.draw();
    }
    public void drawSquare(){
        square.draw();
    }
}
