package com.newsee.common.vo;

import java.io.Serializable;
import java.util.List;

public class SelectVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 46956126287122801L;

    private String key;
    
    private List<FormItemDataVo> formItemData;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<FormItemDataVo> getFormItemData() {
        return formItemData;
    }

    public void setFormItemData(List<FormItemDataVo> formItemData) {
        this.formItemData = formItemData;
    }

}
