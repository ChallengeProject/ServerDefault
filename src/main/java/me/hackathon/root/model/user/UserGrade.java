package me.hackathon.root.model.user;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum UserGrade implements ValueEnum {
    SILVER(0),
    GOLD(1);

    private int value = 0;

    UserGrade(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
