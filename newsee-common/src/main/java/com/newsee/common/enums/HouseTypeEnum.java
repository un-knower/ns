package com.newsee.common.enums;

/**
 * 房产类型枚举
 * Created by niyang on 2017/9/12.
 */
public enum HouseTypeEnum {
    /**
     * 区域
     */
    AREA("1", "区域"),
    /**
     * 项目
     */
    PRECINCT("2", "项目"),
    /**
     * 组团
     */
    CLUSTER("3", "组团"),
    /**
     * 楼栋
     */
    BUILDING("4", "楼栋"),
    /**
     * 单元
     */
    UNIT("5", "单元"),
    /**
     * 房产
     */
    ROOM("6", "房产"),
    /**
     * 车库
     */
    GARAGE("7", "车库"),
    /**
     * 车位
     */
    CARPORT("8", "车位"),

    PUBLICAREA("9", "公共区域");
    private String value;
    private String title;

    private HouseTypeEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public String getTitle() {
        return this.title;
    }
    public static HouseTypeEnum getInstance(String value) {
        if (value != null) {
            HouseTypeEnum[] instArray = HouseTypeEnum.values();
            for (HouseTypeEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
