package com.newsee.common.vo;

import java.io.Serializable;
import java.util.List;
import com.newsee.common.entity.FilterEntity;

/**
 * ɸѡ��vo
 * @author xiaosisi add on 2017/09/04
 *
 */
public class FilterVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7419077878262025711L;
	
	/** id */
	private String _id;
	
	/** 筛选器中文名称 */
	private String filterName;
	
	/** 是否默认 */
	private Boolean isDefault;
	
	/** 是否显示 */
    private Boolean isShow;
	
	/** 菜单id */
	private String funcId;
	
	/** 用户id */
	private Long userId;
	
	/** 企业id */
    private Long enterpriseId;
    
    /** 公司id */
    private Long organizationId;
	
	/** 筛选器条目list */
	private List<FilterEntity> filterList;
	
	/** 排序编号 */
	private int orderNo;
	

	public String getFilterName() {
		return filterName;
	}

	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}

	public List<FilterEntity> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<FilterEntity> filterList) {
		this.filterList = filterList;
	}

	public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public String get_id() {
		return _id;
	}

	public void set_id(String _id) {
		this._id = _id;
	}

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }

	public String getFuncId() {
		return funcId;
	}

	public void setFuncId(String funcId) {
		this.funcId = funcId;
	}

}
