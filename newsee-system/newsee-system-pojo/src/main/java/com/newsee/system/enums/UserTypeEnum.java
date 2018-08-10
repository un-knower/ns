package com.newsee.system.enums;

public enum UserTypeEnum {
    
    /**平台管理员*/
    PLATFORM_MANAGER(0, "平台管理员"),
    
    /** 系统管理员*/
    SYSTEM_MANAGER(1, "系统管理员"),
   
    /** 普通员工*/
    STAFF(2, "普通员工");

    private Integer value;
    private String name;

    private UserTypeEnum(Integer value, String name) {
        this.value = value;
        this.name = name;
    }

    public Integer getValue() {
        return this.value;
    }
    
    public String getName() {
        return this.name;
    }

    public static UserTypeEnum getUserTypeEnum(Integer value) {
        UserTypeEnum  result = null;
        if (value != null) {
            UserTypeEnum[] emumArray = UserTypeEnum.values();
            for (UserTypeEnum userTypeEnum : emumArray) {
                if (userTypeEnum.getValue() == value) {
                    result = userTypeEnum;
                }
            }
        }
        return result;
    }
}
