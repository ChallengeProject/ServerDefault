package me.hackathon.root.model.board;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum  VLTCategoryType implements ValueEnum {
    EDUCATION(0);

    private int value = 0;

    VLTCategoryType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
