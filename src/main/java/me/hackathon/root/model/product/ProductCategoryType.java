package me.hackathon.root.model.product;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum  ProductCategoryType implements ValueEnum {
    FRUIT(0),
    ALL(9);

    private int value = 0 ;

    ProductCategoryType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
