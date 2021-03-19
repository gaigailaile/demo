package com.gai.designpattern.builder_pattern;

public class VegBurger extends Burger{
    @Override
    public String name() {
        return "VegBurger";
    }

    @Override
    public float price() {
        return 0.25f;
    }
}
