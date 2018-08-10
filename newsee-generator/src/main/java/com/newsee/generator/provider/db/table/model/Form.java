package com.newsee.generator.provider.db.table.model;

public class Form {

	/**表单字段中文名称*/
	private String label;
	
	/**表单字段英文名称*/
	private String property;
	
	/**表单字段类型*/
	private String type;
	
	/** 是否必填 */
	private Boolean isRequired;

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getProperty() {
		return property;
	}

	public void setProperty(String property) {
		this.property = property;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Boolean getIsRequired() {
		return isRequired;
	}

	public void setIsRequired(Boolean isRequired) {
		this.isRequired = isRequired;
	}
	
	
}
