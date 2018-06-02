package me.hackathon.root.model.product;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum  ProductCategoryType implements ValueEnum {
    BEST(0), // 추천
    FRUIT(1), // 과일
    ANIMAL_HUSBANDRY(2), //축산
    PROCESSED_FOOD(3), //가공식품
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
