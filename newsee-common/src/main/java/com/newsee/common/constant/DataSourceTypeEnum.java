package com.newsee.common.constant;

/**
 * @Description: 用于区分读写数据库
 * @author: 胡乾亮
 * @date: 2017年8月14日上午9:28:05
 */
public enum DataSourceTypeEnum {

	read("read", "读库"),
	write("write", "写库");
	
	private String type;
	private String name;
	
	DataSourceTypeEnum(String type, String name){
		this.type = type;
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
}
