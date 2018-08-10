package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class NsSystemAreaVo implements Serializable{
	
	private static final long serialVersionUID = -2307596134211846131L;
	
	/** 区域编码 */
	@ApiModelProperty(value = "区域编码")
	private String value;
	/** 区域名 */
	@ApiModelProperty(value = "区域名")
	private String label;
	/** 区域的子区域 */
	@ApiModelProperty(value = " 区域的子区域")
	private List<NsSystemAreaVo> children;
	/** 是否禁用 */
	@ApiModelProperty(value = "是否禁用")
	private Boolean isResourcefieldRemoved;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<NsSystemAreaVo> getChildren() {
		return children;
	}

	public void setChildren(List<NsSystemAreaVo> children) {
		this.children = children;
	}

	public Boolean getIsResourcefieldRemoved() {
		return isResourcefieldRemoved == null ? false : isResourcefieldRemoved;
	}

	public void setIsResourcefieldRemoved(Boolean isResourcefieldRemoved) {
		this.isResourcefieldRemoved = isResourcefieldRemoved;
	}
	

}
