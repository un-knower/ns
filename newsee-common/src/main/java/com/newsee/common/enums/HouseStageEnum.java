package com.newsee.common.enums;

/**
 * 房产状态枚举
 * Created by niyang on 2017/9/12.
 */
public enum HouseStageEnum {
    /**
     * 空置
     */
    KONG_ZHI("10", "空置"),
    /**
     * 未领
     */
    WEI_LING("20", "未领"),
    /**
     * 空关
     */
    KONG_GUAN("30", "空关"),
    /**
     * 入住
     */
    RU_ZHU("40", "入住");


    private String value;
    private String title;

    private HouseStageEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }
    public String getTitle() {
        return this.title;
    }
    public static HouseStageEnum getInstance(String value) {
        if (value != null) {
            HouseStageEnum[] instArray = HouseStageEnum.values();
            for (HouseStageEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
