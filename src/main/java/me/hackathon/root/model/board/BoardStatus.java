package me.hackathon.root.model.board;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum  BoardStatus implements ValueEnum {
    ENROLLING(0),
    COMPLETED(1);

    private int value = 0;

    BoardStatus(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
