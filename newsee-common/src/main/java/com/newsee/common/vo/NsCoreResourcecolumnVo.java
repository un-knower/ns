package com.newsee.common.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.newsee.common.entity.SelectEntity;

import io.swagger.annotations.ApiModelProperty;
/**
 * 
 * @ClassName columnVo
 * @author 胡乾亮
 * @date 2017年10月26日 下午4:26:06
 */
public class NsCoreResourcecolumnVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -6032767923491266315L;
    
    /** 企业id*/
    private Long enterpriseId;
    
    private Object eidtConfig;
    /** 所属组织ID */
    @ApiModelProperty(value = "所属组织ID")
    private Long organizationId;
    
    /** 列ID */
    @ApiModelProperty(value = "列ID")
    private String jeCoreResourcecolumnId;
    
    /** 功能id*/
    private String resourcecolumnFuncinfoId;

    /** 列中文名称 */
    private String resourcecolumnName;
    
    /** 列英文名称 */
    private String resourcecolumnNameEn;
    
    /** 列code */
    private String resourcecolumnCode;
    
    /** 列类型  */
    private String resourcecolumnXtype;
    
    /** 列宽度*/
    private String resourcecolumnWidth;
    
    /** 排序 */
    private BigDecimal resourcecolumnFieldorderindex;
    
    /**多表头名称*/
    private String resourcecolumnMorecolumnname;
    
    /** 统计类型 */
    private String resourcecolumnStatisticstype;
    
    /** 是否允许编辑*/
    private String resourcecolumnAllowedit;
    
    /** 是否可排序*/
    private String resourcecolumnOrder;
    
    /** 是否隐藏*/
    private String resourcecolumnHidden;
    
    /** 如果type为select，存储下拉选项的值  */
    private List<SelectEntity> selectList;

    
    
    public Object getEidtConfig() {
		return eidtConfig;
	}

	public void setEidtConfig(Object eidtConfig) {
		this.eidtConfig = eidtConfig;
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

    public String getJeCoreResourcecolumnId() {
        return jeCoreResourcecolumnId;
    }

    public void setJeCoreResourcecolumnId(String jeCoreResourcecolumnId) {
        this.jeCoreResourcecolumnId = jeCoreResourcecolumnId;
    }

    public String getResourcecolumnFuncinfoId() {
        return resourcecolumnFuncinfoId;
    }

    public void setResourcecolumnFuncinfoId(String resourcecolumnFuncinfoId) {
        this.resourcecolumnFuncinfoId = resourcecolumnFuncinfoId;
    }

    public String getResourcecolumnName() {
        return resourcecolumnName;
    }

    public void setResourcecolumnName(String resourcecolumnName) {
        this.resourcecolumnName = resourcecolumnName;
    }

    public String getResourcecolumnNameEn() {
        return resourcecolumnNameEn;
    }

    public void setResourcecolumnNameEn(String resourcecolumnNameEn) {
        this.resourcecolumnNameEn = resourcecolumnNameEn;
    }

    public String getResourcecolumnXtype() {
        return resourcecolumnXtype;
    }

    public void setResourcecolumnXtype(String resourcecolumnXtype) {
        this.resourcecolumnXtype = resourcecolumnXtype;
    }

    public String getResourcecolumnWidth() {
        return resourcecolumnWidth;
    }

    public void setResourcecolumnWidth(String resourcecolumnWidth) {
        this.resourcecolumnWidth = resourcecolumnWidth;
    }

    public BigDecimal getResourcecolumnFieldorderindex() {
        return resourcecolumnFieldorderindex;
    }

    public void setResourcecolumnFieldorderindex(BigDecimal resourcecolumnFieldorderindex) {
        this.resourcecolumnFieldorderindex = resourcecolumnFieldorderindex;
    }

    public String getResourcecolumnMorecolumnname() {
        return resourcecolumnMorecolumnname;
    }

    public void setResourcecolumnMorecolumnname(String resourcecolumnMorecolumnname) {
        this.resourcecolumnMorecolumnname = resourcecolumnMorecolumnname;
    }

    public String getResourcecolumnStatisticstype() {
        return resourcecolumnStatisticstype;
    }

    public void setResourcecolumnStatisticstype(String resourcecolumnStatisticstype) {
        this.resourcecolumnStatisticstype = resourcecolumnStatisticstype;
    }

    public String getResourcecolumnAllowedit() {
        return resourcecolumnAllowedit;
    }

    public void setResourcecolumnAllowedit(String resourcecolumnAllowedit) {
        this.resourcecolumnAllowedit = resourcecolumnAllowedit;
    }

    public String getResourcecolumnOrder() {
        return resourcecolumnOrder;
    }

    public void setResourcecolumnOrder(String resourcecolumnOrder) {
        this.resourcecolumnOrder = resourcecolumnOrder;
    }

    public String getResourcecolumnHidden() {
        return resourcecolumnHidden;
    }

    public void setResourcecolumnHidden(String resourcecolumnHidden) {
        this.resourcecolumnHidden = resourcecolumnHidden;
    }

    public List<SelectEntity> getSelectList() {
        return selectList;
    }

    public void setSelectList(List<SelectEntity> selectList) {
        this.selectList = selectList;
    }

	public String getResourcecolumnCode() {
		return resourcecolumnCode;
	}

	public void setResourcecolumnCode(String resourcecolumnCode) {
		this.resourcecolumnCode = resourcecolumnCode;
	}
    
}
