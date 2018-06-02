package me.hackathon.root.model.board;

import me.hackathon.root.support.enumeration.ValueEnum;

public enum  VLTCategoryType implements ValueEnum {
    EDUCATION(0), //교육
    SOCUAL_WEKFARE(1), // 사회복지
    PROTECTION_OF_ENVIRONMENT(2), // 환경 보전
    DISASTER_RELIEF(3), //재해 구호
    ALL(9);

    private int value = 0;

    VLTCategoryType(int value) {
        this.value = value;
    }

    @Override
    public int getValue() {
        return value;
    }
}
