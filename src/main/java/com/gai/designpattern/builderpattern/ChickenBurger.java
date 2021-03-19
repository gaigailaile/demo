package com.gai.designpattern.builderpattern;

public class ChickenBurger extends Burger {
    @Override
    public String name() {
        return "ChickenBurger";
    }

    @Override
    public float price() {
        return 50.2f;
    }
}
