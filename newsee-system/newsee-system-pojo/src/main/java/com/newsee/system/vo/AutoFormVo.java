package com.newsee.system.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

public class AutoFormVo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Long id;

	private String userName;
	
	private Date birthday;
	
	private String mobile;
	
	private Map<String, Object> autoForm;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, Object> getAutoForm() {
		return autoForm;
	}

	public void setAutoForm(Map<String, Object> autoForm) {
		this.autoForm = autoForm;
	}

}
