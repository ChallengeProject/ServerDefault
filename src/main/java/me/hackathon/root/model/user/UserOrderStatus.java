package me.hackathon.root.model.user;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum  UserOrderStatus implements ValueEnum {
    SHIPPING(0),
    COMPLETED(1);

    private int value = 0;

    UserOrderStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
