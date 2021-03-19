package com.gai.designpattern.abstract_factory.color;

public class Red implements Color {
    @Override
    public void fill() {
        System.out.println("Red fill()");
    }
}
