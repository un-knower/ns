package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

public class NsSystemMenuVo implements Serializable {

    private static final long serialVersionUID = -3571187313517159364L;
    
    private String jeCoreMenuId;
    
    private Integer syOrderindex;
    
    private List<NsSystemFunctionVo> functionVos;

    public String getJeCoreMenuId() {
        return jeCoreMenuId;
    }

    public void setJeCoreMenuId(String jeCoreMenuId) {
        this.jeCoreMenuId = jeCoreMenuId;
    }

    public List<NsSystemFunctionVo> getFunctionVos() {
        return functionVos;
    }

    public void setFunctionVos(List<NsSystemFunctionVo> functionVos) {
        this.functionVos = functionVos;
    }

    public Integer getSyOrderindex() {
        return syOrderindex;
    }

    public void setSyOrderindex(Integer syOrderindex) {
        this.syOrderindex = syOrderindex;
    }

}
