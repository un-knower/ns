package com.newsee.soss.vo;

import java.io.Serializable;
import java.util.List;


import io.swagger.annotations.ApiModelProperty;

public class JepfSyncVo implements Serializable{
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "选中需要同步的组织，isAll为true时不需传此参数 ")
	private List<JepfSyncOrgVo> orgList;
	
	@ApiModelProperty(value = "10：同步到部分组织中，20：同步到所有组织中")
	private Integer syncType;
	
	@ApiModelProperty(value = "组织名称")
	private String orgName;
	
	@ApiModelProperty(value = "同步哪个菜单下的所有菜单")
    private String menuName;
    
	private Long userId;

	public List<JepfSyncOrgVo> getOrgList() {
		return orgList;
	}

	public void setOrgList(List<JepfSyncOrgVo> orgList) {
		this.orgList = orgList;
	}

	public Integer getSyncType() {
		return syncType;
	}

	public void setSyncType(Integer syncType) {
		this.syncType = syncType;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}
	

}
