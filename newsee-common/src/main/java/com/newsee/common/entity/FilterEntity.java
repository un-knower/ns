package com.newsee.common.entity;

import java.io.Serializable;
import java.util.List;
import com.newsee.common.entity.SelectEntity;
import com.newsee.common.utils.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * 筛选器entity
 * @author xiaosisi add on 2017/09/04
 *
 */
public class FilterEntity implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5262844284088660218L;
	/** 筛选器条目英文名称 */
	@ApiModelProperty(value = "企业id，前端不需要传入，后端获取")
	private  Long enterpriseId;
	
	/** 筛选器条目英文名称 */
	@ApiModelProperty(value = "公司id，前端不需要传入，后端获取")
	private Long organizationId;
	
	/** 筛选器条目英文名称 */
	@ApiModelProperty(value = "筛选器条目英文名称")
	private String fieldName;

	/** 筛选器条目英文名称(下划线格式) */
    @ApiModelProperty(value = "筛选器条目英文名称(下划线格式)")
	private String fieldUnderLineName;
	
	/** 筛选器条目选中的值 */
	@ApiModelProperty(value = "筛选器条目选中的值")
	private String fieldValue;
	
	@ApiModelProperty(value = "为空不为空的时候控制后面的输入框是否显示")
	private Boolean isShow;
	
	/** 字段对应的数据库字段名称，用于mybatis拼接sql文   */
	/*@ApiModelProperty(value = "字段对应的数据库字段名称")
	private String dbEnCloumnName;*/
	
	/** 筛选器条目条件*/
	@ApiModelProperty(value = "筛选器条目条件")
	private String comparison;
	
	/** 筛选器条目类型，分text，number，date，select四种*/
	@ApiModelProperty(value = "筛选器条目类型，分text，number，date，select四种；注：检索时，不用传此参数")
	private String type;
	
	/**如果type为select，下拉值选项*/
	@ApiModelProperty(value = "如果type为select，下拉值选项；注：检索时，不用传此参数")
	private List<SelectEntity> selectList;

	
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

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
        if (!StringUtils.hasLength(this.fieldUnderLineName)) {
            this.fieldUnderLineName = StringUtils.humpToUnderLine(this.fieldName);
        }
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(String fieldValue) {
        this.fieldValue = fieldValue;
    }

    public String getComparison() {
		return comparison;
	}

	public void setComparison(String comparison) {
		this.comparison = comparison;
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

    public String getFieldUnderLineName() {
        return fieldUnderLineName;
    }

    public void setFieldUnderLineName(String fieldUnderLineName) {
        this.fieldUnderLineName = StringUtils.humpToUnderLine(this.fieldName);
    }
    
    public Boolean getIsShow() {
        return isShow;
    }

    public void setIsShow(Boolean isShow) {
        this.isShow = isShow;
    }


	/*public String getDbEnCloumnName() {
		return dbEnCloumnName;
	}

	public void setDbEnCloumnName(String dbEnCloumnName) {
		this.dbEnCloumnName = dbEnCloumnName;
	}*/
	
}
