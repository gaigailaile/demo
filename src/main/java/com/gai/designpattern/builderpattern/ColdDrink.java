package com.gai.designpattern.builderpattern;

import com.gai.designpattern.builderpattern.Bottle;
import com.gai.designpattern.builderpattern.Item;
import com.gai.designpattern.builderpattern.Packing;

public abstract class ColdDrink implements Item {
    @Override
    public Packing packing() {
        return new Bottle();
    }

    public abstract float price();
}
