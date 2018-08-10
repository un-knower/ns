package com.newsee.common.vo;

public class CustomFormFieldVo{

    /** 菜单id */
    private String menuId;
    
    /** 公司id */
    private String companyId;
    
    /** 字段中文名称  */
    private String cnName;
    
    /** 字段英文名称   */
    private String enName;
    
    /**表格名称 */
    private String gridName;
    
    /** 字段类型：text,select,date,number */
    private String type;
    
    /** 页面显示宽度 */
    private String width;
    
    /** 字段排序 */
    private String order;
    
    /** 是否内置：1：系统内置，0：用户自定义*/
    private String isBuiltIn;
    
    /** 是否在列表展示 */
    private String isShow;
    
    /** 是否表头 */
    private String isHeader;

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

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

    public String getGridName() {
        return gridName;
    }

    public void setGridName(String gridName) {
        this.gridName = gridName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getIsBuiltIn() {
        return isBuiltIn;
    }

    public void setIsBuiltIn(String isBuiltIn) {
        this.isBuiltIn = isBuiltIn;
    }

    public String getIsShow() {
        return isShow;
    }

    public void setIsShow(String isShow) {
        this.isShow = isShow;
    }

    public String getIsHeader() {
        return isHeader;
    }

    public void setIsHeader(String isHeader) {
        this.isHeader = isHeader;
    }
    
}
