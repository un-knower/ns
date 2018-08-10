package com.newsee.common.enums;

/**
 * 装修状态枚举
 * Created by wangrenjie on 2017/11/5.
 */
public enum HouseRentStageEnum {

    /**
     * 未出租
     */
    RENT_STAGE_NONE("0","未出租"),
    /**
     * 已出租
     */
    RENT_STAGE_IN("1","已出租");

    private String value;

    private String title;
    
    private HouseRentStageEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public String getTitle() {
        return title;
    }

    public static HouseRentStageEnum getInstance(String value) {
        if (value != null) {
            HouseRentStageEnum[] instArray = HouseRentStageEnum.values();
            for (HouseRentStageEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
