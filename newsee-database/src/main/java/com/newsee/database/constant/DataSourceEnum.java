package com.newsee.database.constant;

/**
 * @Description: 用户分库，当前共有3个数据库(DB00、DB01、DB02)
 * @author: 胡乾亮
 * @date: 2017年8月21日下午3:11:54
 */
public enum DataSourceEnum {

	DB00("DB00", "数据库00"),
	DB01("DB01", "数据库01"),
	DB02("DB02", "数据库02");

	private String value;
	private String name;
	
	
	DataSourceEnum(String value, String name){
		this.value = value;
		this.name = name;
	}


	public String getValue() {
		return value;
	}


	public void setValue(String value) {
		this.value = value;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
