package com.newsee.common.constant;

/**
 * carse系统数据操作方法命名规范
 * 主要用于业务日志拦截处理
 * @author: xiaosisi add on 2017/08/21
 */
public enum NewseeMethodRuleEnum {

	addBatch("addBatch", "批量增加"),
	add("add", "新增"),
	deleteBatch("deleteBatch", "批量删除"),
	delete("delete", "删除"),
	edit("edit", "修改"),
	detail("detail", "详情");
	
	private String type;
	private String name;
	
	NewseeMethodRuleEnum(String type, String name){
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
