package com.gai.designpattern.builderpattern;

import com.gai.designpattern.builderpattern.Item;
import com.gai.designpattern.builderpattern.Packing;
import com.gai.designpattern.builderpattern.Wrapper;

public abstract class Burger implements Item {
    @Override
    public Packing packing() {
        return new Wrapper();
    }

    @Override
    public abstract float price();
}
