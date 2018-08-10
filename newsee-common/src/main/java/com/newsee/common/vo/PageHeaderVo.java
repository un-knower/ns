package com.newsee.common.vo;

import java.io.Serializable;
import java.util.List;

import com.newsee.common.entity.SelectEntity;

/**
 * 表头entity
 * @author xiaosisi add on 2017/09/04
 *
 */
public class PageHeaderVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2018828491704723083L;
	
	/** 头部中文名称 */
	private String cnName;
	
	/** 头部英文名称 */
	private String enName;
	
	/** 公司id*/
	private Long companyId;
	
	/** 是否显示 */
	private Boolean isShow;
	
	/** 头部项目类型  */
	private String type;
	
	/** 是否汇总列，如果是汇总列，需要计算单页汇总和检索条件下的结果汇总 */
	private Boolean isSummaryColumn;
	
	/** 头部宽度*/
	private Integer width;
	
	/** 排序 */
	private int orderNo;
	
	/**表格名称*/
	private String gridName;
	
	/** 如果type为select，存储下拉选项的值  */
	private List<SelectEntity> selectList;
	

	public String getCnName() {
		return cnName;
	}

	public void setCnName(String cnName) {
		this.cnName = cnName;
	}

	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public Boolean getIsShow() {
		return isShow;
	}

	public void setIsShow(Boolean isShow) {
		this.isShow = isShow;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<SelectEntity> getSelectList() {
		return selectList;
	}

	public void setSelectList(List<SelectEntity> selectList) {
		this.selectList = selectList;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	public int getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	public Boolean getIsSummaryColumn() {
		return isSummaryColumn;
	}

	public void setIsSummaryColumn(Boolean isSummaryColumn) {
		this.isSummaryColumn = isSummaryColumn;
	}

	public String getGridName() {
		return gridName;
	}

	public void setGridName(String gridName) {
		this.gridName = gridName;
	}

}
