package com.newsee.common.vo;

import java.io.Serializable;
import java.util.List;

public class FormItemDataVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = -5902541426560880814L;

    private String index;

    private List<SelectItemVo> items;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<SelectItemVo> getItems() {
        return items;
    }

    public void setItems(List<SelectItemVo> items) {
        this.items = items;
    }
    
    
}
