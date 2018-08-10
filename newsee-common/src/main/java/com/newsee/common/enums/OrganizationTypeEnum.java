package com.newsee.common.enums;

public enum OrganizationTypeEnum {

    /**
     * 集团
     */
    GROUP(0, "集团"),
    /**
     * 公司
     */
    COMPANY(1, "公司"),
    /**
     * 部门
     */
    DEPARTMENT(2, "部门");
    
    private Integer value;
    private String name;
    
    
    
    private OrganizationTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }
    
    public Integer getValue() {
        return value;
    }
    public void setValue(Integer value) {
        this.value = value;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public static OrganizationTypeEnum getInstance(Integer value) {
        if (value != null) {
            OrganizationTypeEnum[] instArray = OrganizationTypeEnum.values();
            for (OrganizationTypeEnum instance : instArray) {
                if (instance.getValue().equals(value)) {
                    return instance;
                }
            }

        }
        return null;
    }
    
    
    
}
