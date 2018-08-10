package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class DictionaryTreeVo implements Serializable {

    private static final long serialVersionUID = 3162844526665248660L;
    
    @ApiModelProperty(value = "企业ID")
    private Long enterpriseId;
    @ApiModelProperty(value = "组织ID")
    private Long organizationId;
    @ApiModelProperty(value = "字典树节点类型:根节点(root)、字典组(dicGroup)、字典(dic)")
    private String NodeType;
    @ApiModelProperty(value = "节点名称")
    private String NodeName;
    @ApiModelProperty(value = "节点值")
    private String NodeValue;
    @ApiModelProperty(value = "子节点")
    private List<DictionaryTreeVo> children;
    
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
    public String getNodeType() {
        return NodeType;
    }
    public void setNodeType(String nodeType) {
        NodeType = nodeType;
    }
    public String getNodeName() {
        return NodeName;
    }
    public void setNodeName(String nodeName) {
        NodeName = nodeName;
    }
    public List<DictionaryTreeVo> getChildren() {
        return children;
    }
    public void setChildren(List<DictionaryTreeVo> children) {
        this.children = children;
    }
    public String getNodeValue() {
        return NodeValue;
    }
    public void setNodeValue(String nodeValue) {
        NodeValue = nodeValue;
    }
}
