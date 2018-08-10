package com.newsee.system.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class MenuResultVo implements Serializable{
    
    /**
     * 
     */
    private static final long serialVersionUID = -2072877629180614821L;
    @ApiModelProperty(value = "结果表ID")
    private String id;
    @ApiModelProperty(value = "菜单ID")
    private Long menuId;
    @ApiModelProperty(value = "父菜单ID")
    private Long menuParentId;
    @ApiModelProperty(value = "父菜单名称")
    private String menuParentName;
    @ApiModelProperty(value = "菜单路径")
    private String menuPath;
    @ApiModelProperty(value = "菜单名称")
    private String menuName;
    @ApiModelProperty(value = "菜单链接地址")
    private String menuUrl;
    @ApiModelProperty(value = "菜单图标")
    private String menuIcon;
    @ApiModelProperty(value = "菜单按钮个数")
    private Integer menuButtonCount;
    @ApiModelProperty(value = "菜单排序")
    private Integer menuOrder;
    @ApiModelProperty(value = "0：未删除，1：已删除")
    private Byte isDeleted;
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "创建人")
    private Long createUserId;
    @ApiModelProperty(value = "创建时间")
    private Date createTime;
    @ApiModelProperty(value = "修改人")
    private Long updateUserId;
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public Long getMenuId() {
        return menuId;
    }
    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
    public Long getMenuParentId() {
        return menuParentId;
    }
    public void setMenuParentId(Long menuParentId) {
        this.menuParentId = menuParentId;
    }
    public String getMenuParentName() {
        return menuParentName;
    }
    public void setMenuParentName(String menuParentName) {
        this.menuParentName = menuParentName;
    }
    public String getMenuPath() {
        return menuPath;
    }
    public void setMenuPath(String menuPath) {
        this.menuPath = menuPath;
    }
    public String getMenuName() {
        return menuName;
    }
    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }
    public String getMenuUrl() {
        return menuUrl;
    }
    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
    public String getMenuIcon() {
        return menuIcon;
    }
    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }
    public Integer getMenuButtonCount() {
        return menuButtonCount;
    }
    public void setMenuButtonCount(Integer menuButtonCount) {
        this.menuButtonCount = menuButtonCount;
    }
    public Integer getMenuOrder() {
        return menuOrder;
    }
    public void setMenuOrder(Integer menuOrder) {
        this.menuOrder = menuOrder;
    }
    public Byte getIsDeleted() {
        return isDeleted;
    }
    public void setIsDeleted(Byte isDeleted) {
        this.isDeleted = isDeleted;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
    public Long getCreateUserId() {
        return createUserId;
    }
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Long getUpdateUserId() {
        return updateUserId;
    }
    public void setUpdateUserId(Long updateUserId) {
        this.updateUserId = updateUserId;
    }
    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
    
    
}
