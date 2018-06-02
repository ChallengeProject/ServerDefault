package me.hackathon.root.model.comment;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum CommentGrade implements ValueEnum {
    VERY_POOR(0),
    POOR(1),
    FAIR(2),
    GOOD(3),
    VERY_GOOD(4);

    private int value = 0;

    CommentGrade(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
