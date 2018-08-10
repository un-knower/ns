package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/** 
 * @ClassName NsCoreMenuVo
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author 胡乾亮
 * @date 2017年11月9日 下午4:59:59 
 */
public class NsCoreMenuVo implements Serializable {

    private static final long serialVersionUID = 5912356623437636197L;

    /** 主键ID */
    @ApiModelProperty(value = "主键ID")
    private Long id;
    
    /** 所属企业ID */
    @ApiModelProperty(value = "所属企业ID")
    private Long enterpriseId;
    
    /** 所属组织ID */
    @ApiModelProperty(value = "所属组织ID")
    private Long organizationId;
    
    /** 菜单ID */
    @ApiModelProperty(value = "菜单ID")
    private String jeCoreMenuId;
   
    /** 节点编码 */
    @ApiModelProperty(value = "节点编码")
    private String menuCode;
    
    /** 是否启用 */
    @ApiModelProperty(value = "是否启用")
    private String menuEnabled;
    
    /** 功能类型 */
    @ApiModelProperty(value = "功能类型")
    private String menuFunctype;
    
    /** 功能说明 */
    @ApiModelProperty(value = "功能说明")
    private String menuHelp;
    
    /** 图标 */
    @ApiModelProperty(value = "图标")
    private String menuIcon;
    
    /** 图标CLS */
    @ApiModelProperty(value = "图标CLS")
    private String menuIconcls;

    /** 节点名称 */
    @ApiModelProperty(value = "节点名称")
    private String menuMenuname;
    
    /** 英文标题 */
    @ApiModelProperty(value = "英文标题")
    private String menuMenusubname;
      
    /** 节点信息 */
    @ApiModelProperty(value = "节点信息")
    private String menuNodeinfo;
    
    /** 节点信息类型 */
    @ApiModelProperty(value = "节点信息类型")
    private String menuNodeinfotype;
    
    /** 层次 */
    @ApiModelProperty(value = "层次")
    private Integer syLayer;
    
    /** 节点类型 */
    @ApiModelProperty(value = "节点类型")
    private String syNodetype;
    
    /** 排序字段 */
    @ApiModelProperty(value = "排序字段")
    private Integer syOrderindex;
    
    /** 父节点ID */
    @ApiModelProperty(value = "父节点ID")
    private String syParent;
    
    /** 父节点路径 */
    @ApiModelProperty(value = "父节点路径")
    private String syParentpath;
    
    /** 树形路径 */
    @ApiModelProperty(value = "树形路径")
    private String syPath;
    
    /** 树形路径 */
    @ApiModelProperty(value = "子菜单")
    private List<NsCoreMenuVo> childMenus;
    
    /** 此菜单所挂的功能 */
    @ApiModelProperty(value = "功能")
    private NsCoreFuncinfoVo funcinfoVo;
    
    @ApiModelProperty(value = "功能Id")
    private String funcId;
    
    @ApiModelProperty("操作人")
    private Long handlerId;
    
    
    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId;
    }

    public List<NsCoreMenuVo> getChildMenus() {
        return childMenus;
    }

    public void setChildMenus(List<NsCoreMenuVo> childMenus) {
        this.childMenus = childMenus;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getJeCoreMenuId() {
        return jeCoreMenuId;
    }

    public void setJeCoreMenuId(String jeCoreMenuId) {
        this.jeCoreMenuId = jeCoreMenuId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getMenuEnabled() {
        return menuEnabled;
    }

    public void setMenuEnabled(String menuEnabled) {
        this.menuEnabled = menuEnabled;
    }

    public String getMenuFunctype() {
        return menuFunctype;
    }

    public void setMenuFunctype(String menuFunctype) {
        this.menuFunctype = menuFunctype;
    }

    public String getMenuHelp() {
        return menuHelp;
    }

    public void setMenuHelp(String menuHelp) {
        this.menuHelp = menuHelp;
    }

    public String getMenuIcon() {
        return menuIcon;
    }

    public void setMenuIcon(String menuIcon) {
        this.menuIcon = menuIcon;
    }

    public String getMenuMenuname() {
        return menuMenuname;
    }

    public void setMenuMenuname(String menuMenuname) {
        this.menuMenuname = menuMenuname;
    }

    public String getMenuMenusubname() {
        return menuMenusubname;
    }

    public void setMenuMenusubname(String menuMenusubname) {
        this.menuMenusubname = menuMenusubname;
    }

    public String getMenuNodeinfo() {
        return menuNodeinfo;
    }

    public void setMenuNodeinfo(String menuNodeinfo) {
        this.menuNodeinfo = menuNodeinfo;
    }

    public String getMenuNodeinfotype() {
        return menuNodeinfotype;
    }

    public void setMenuNodeinfotype(String menuNodeinfotype) {
        this.menuNodeinfotype = menuNodeinfotype;
    }

    public Integer getSyLayer() {
        return syLayer;
    }

    public void setSyLayer(Integer syLayer) {
        this.syLayer = syLayer;
    }

    public String getSyNodetype() {
        return syNodetype;
    }

    public void setSyNodetype(String syNodetype) {
        this.syNodetype = syNodetype;
    }

    public Integer getSyOrderindex() {
        return syOrderindex;
    }

    public void setSyOrderindex(Integer syOrderindex) {
        this.syOrderindex = syOrderindex;
    }

    public String getSyParent() {
        return syParent;
    }

    public void setSyParent(String syParent) {
        this.syParent = syParent;
    }

    public String getSyParentpath() {
        return syParentpath;
    }

    public void setSyParentpath(String syParentpath) {
        this.syParentpath = syParentpath;
    }

    public String getSyPath() {
        return syPath;
    }

    public void setSyPath(String syPath) {
        this.syPath = syPath;
    }

    public NsCoreFuncinfoVo getFuncinfoVo() {
        return funcinfoVo;
    }

    public void setFuncinfoVo(NsCoreFuncinfoVo funcinfoVo) {
        this.funcinfoVo = funcinfoVo;
    }

    public Long getHandlerId() {
        return handlerId;
    }

    public void setHandlerId(Long handlerId) {
        this.handlerId = handlerId;
    }

    public String getMenuIconcls() {
        return menuIconcls;
    }

    public void setMenuIconcls(String menuIconcls) {
        this.menuIconcls = menuIconcls;
    }
    
}
