package com.newsee.common.enums;

/**
 * 装修状态枚举
 * Created by wangrenjie on 2017/11/5.
 */
public enum HouseDecorateStageEnum {

    /**
     * 未装修
     */
    DECORATE_STAGE_NONE("0","未装修"),
    /**
     * 装修中
     */
    DECORATE_STAGE_IN("1","装修中"),
    /**
     * 已装修
     */
    DECORATE_STAGE_DONE("2","已装修");
    private String value;

    private String title;
    
    private HouseDecorateStageEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public String getTitle() {
        return title;
    }

    public static HouseDecorateStageEnum getInstance(String value) {
        if (value != null) {
            HouseDecorateStageEnum[] instArray = HouseDecorateStageEnum.values();
            for (HouseDecorateStageEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
