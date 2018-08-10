package com.newsee.owner.vo;

import java.io.Serializable;

public class OwnerSerchConditionVo implements Serializable{

	private static final long serialVersionUID = -8956542469495928244L;
	
	private static final Integer PAGENUM = 1;
	private static final Integer PAGESIZE = 20;

    private String ownerName;
    
    private String houseName;
    
    private String contactTel;
    
    private Integer pageNum;
    
    private Integer pageSize;
    

    public Integer getPageNum() {
    	if(pageNum==null){
    		pageNum = PAGENUM;
    	}
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
    	if(pageSize==null){
    		pageSize = PAGESIZE;
    	}
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}
    
    
}
