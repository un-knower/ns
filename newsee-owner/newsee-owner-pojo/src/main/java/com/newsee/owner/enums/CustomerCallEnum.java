package com.newsee.owner.enums;

/**
 * 客户关系枚举
 * Created by wangrenjie on 2017/11/7.
 */
public enum CustomerCallEnum {

    //个人
    ZHANGFU("1","丈夫"),
    QIZI("2","妻子"),
    FUQIN("3","父亲"),
    MUQIN("4","母亲"),
    ERZI("5","儿子"),
    NVER("6","女儿"),
    JIEMEI("7","姐妹"),
    XIONGDI("8","兄弟"),
    XIONGMEI("9","兄妹"),
    JIEDI("10","姐弟"),
    ZUFU("11","祖父"),
    ZUMU("12","祖母"),
    SUNZI("13","孙子"),
    SUNNV("14","孙女"),
    //企业
    DONGSHIZHANG("15","董事长"),
    ZONGJINGLI("16","总经理"),
    CAIWU("17","财务"),
    RENSHI("18","人事"),
    XINGZHENG("19","行政"),
    ZIGONGSI("20","子公司"),
    //其他
    QITA("21","其他");
    private String value;

    private String title;
    
    private CustomerCallEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public String getTitle() {
        return title;
    }

    public static CustomerCallEnum getInstance(String value) {
        if (value != null) {
            CustomerCallEnum[] instArray = CustomerCallEnum.values();
            for (CustomerCallEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
