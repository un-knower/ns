package com.newsee.system.enums;

public enum DictionaryStatusEnum {

    BUILT_IN(1,"内置数据字典，不能修改"),
    NOT_BUILT_IN(0,"用户自定义数据字典，用户自己可以修改"),
    DISABLE(1,"已停用"),
    NOT_DISABLE(0,"未停用");
    
    public Integer VALUE;
    public String NAME;
    
    DictionaryStatusEnum(Integer value, String name){
        this.VALUE = value;
        this.NAME = name;
    }
}
