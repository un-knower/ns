package com.newsee.owner.enums;

/**
 * 房态合并拆分动作枚举
 * Created by niyang on 2017/9/19.
 */
public enum HouseEditStatusEnum {

    /**
     * 合并
     */
    COMBINE(1),
    /**
     * 拆分
     */
    SPLIT(2);

    private Integer value;

    private HouseEditStatusEnum(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return this.value;
    }

    public static HouseEditStatusEnum getInstance(Integer value) {
        if (value != null) {
            HouseEditStatusEnum[] instArray = HouseEditStatusEnum.values();
            for (HouseEditStatusEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
