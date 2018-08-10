package com.newsee.owner.enums;

/**
 * 客户关系枚举
 * Created by wangrenjie on 2017/11/7.
 */
public enum CustomerRelationEnum {

    //个人
    FUQI("1","夫妻"),
    FUMU("2","父母"),
    ZINV("3","子女"),
    XIONGDI("4","兄弟"),
    XIONGMEI("5","兄妹"),
    JIEDI("6","姐弟"),
    JIEMEI("7","姐妹"),
    ZUFUMU("8","祖父母"),
    SUNZINV("9","孙子女"),
    //企业
    DONGSHIZHANG("10","董事长"),
    ZONGJINGLI("11","总经理"),
    CAIWU("12","财务"),
    RENSHI("13","人事"),
    XINGZHENG("14","行政"),
    ZIGONGSI("15","子公司"),
    //其他
    QITA("16","其他"),
    QIYE("17","企业");
    private String value;

    private String title;
    
    private CustomerRelationEnum(String value, String title) {
        this.value = value;
        this.title = title;
    }

    public String getValue() {
        return this.value;
    }

    public String getTitle() {
        return title;
    }

    public static CustomerRelationEnum getInstance(String value) {
        if (value != null) {
            CustomerRelationEnum[] instArray = CustomerRelationEnum.values();
            for (CustomerRelationEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
}
