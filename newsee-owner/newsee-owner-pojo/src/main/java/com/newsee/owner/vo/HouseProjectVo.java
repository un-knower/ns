package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class HouseProjectVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 6739415108693586843L;

    @ApiModelProperty(value = "项目基础信息")
    private OwnerProjectInfoVo projectInfoVo;

    @ApiModelProperty(value = "项目扩展详细信息")
    private OwnerProjectExtendInfoVo projectExtendInfoVo;

    @ApiModelProperty(value = "项目物业信息")
    private OwnerProjectPropertyInfoVo projectPropertyInfoVo;

    public OwnerProjectInfoVo getProjectInfoVo() {
        return projectInfoVo;
    }

    public void setProjectInfoVo(OwnerProjectInfoVo projectInfoVo) {
        this.projectInfoVo = projectInfoVo;
    }

    public OwnerProjectExtendInfoVo getProjectExtendInfoVo() {
        return projectExtendInfoVo;
    }

    public void setProjectExtendInfoVo(OwnerProjectExtendInfoVo projectExtendInfoVo) {
        this.projectExtendInfoVo = projectExtendInfoVo;
    }

    public OwnerProjectPropertyInfoVo getProjectPropertyInfoVo() {
        return projectPropertyInfoVo;
    }

    public void setProjectPropertyInfoVo(OwnerProjectPropertyInfoVo projectPropertyInfoVo) {
        this.projectPropertyInfoVo = projectPropertyInfoVo;
    }

}
