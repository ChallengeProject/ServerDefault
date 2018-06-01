package me.hackathon.root.model.comment;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum  CommentGrade implements ValueEnum {
    BAD(0),
    NORMAL(1),
    GOOD(2);

    private int value = 0;

    CommentGrade(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
