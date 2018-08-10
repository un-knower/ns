package com.newsee.system.vo;

import java.util.List;

public class ResultData {
    
    private String value;
    
    private String label;
    
    private Boolean disabled;
    
    private List<ResultData> children;
    
   
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
    public Boolean getDisabled() {
        return disabled;
    }
    public void setDisabled(Boolean disabled) {
        this.disabled = disabled;
    }
    public List<ResultData> getChildren() {
        return children;
    }
    public void setChildren(List<ResultData> children) {
        this.children = children;
    }
    
    

}
