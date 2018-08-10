package com.newsee.common.enums;

/**
 * 房态操作动作枚举
 * Created by niyang on 2017/9/19.
 */
public enum HouseOperateTypeEnum {

    /**
     * 售楼
     */
    SHOU_LOU("1","售楼"),
    /**
     * 收房
     */
    SHOU_FANG("2","收房"),
    /**
     * 入住
     */
    RU_ZHU("3","入住"),
    /**
     * 搬出
     */
    BAN_CHU("4","搬出"),
    /**
     * 出租
     */
    CHU_ZU("5","出租"),
    /**
     * 转租
     */
    ZHUAN_ZU("6","转租"),
    /**
     * 退租
     */
    TUI_ZU("7","退租"),
    /**
     * 空关
     */
    KONG_GUAN("8","空关"),
    /**
     * 转让
     */
    ZHUAN_RANG("9","转让"),

    ZHUANG_XIU("10","装修");
    private String value;

    private String title;
    
    private HouseOperateTypeEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public String getTitle() {
        return title;
    }

    public static HouseOperateTypeEnum getInstance(String value) {
        if (value != null) {
            HouseOperateTypeEnum[] instArray = HouseOperateTypeEnum.values();
            for (HouseOperateTypeEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
