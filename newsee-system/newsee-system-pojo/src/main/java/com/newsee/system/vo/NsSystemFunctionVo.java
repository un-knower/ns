package com.newsee.system.vo;

import java.io.Serializable;
import java.util.List;

public class NsSystemFunctionVo implements Serializable {

    private static final long serialVersionUID = 8701272041035014674L;
    private String jeCoreFuncinfoId;
    private String funcinfoFunccode;
    private Integer syOrderindex;
    private List<String> buttonIds;
    private NsDataSeeScopeVo dataSeeScopeVo;
    
    public String getJeCoreFuncinfoId() {
        return jeCoreFuncinfoId;
    }
    public void setJeCoreFuncinfoId(String jeCoreFuncinfoId) {
        this.jeCoreFuncinfoId = jeCoreFuncinfoId;
    }
    public List<String> getButtonIds() {
        return buttonIds;
    }
    public void setButtonIds(List<String> buttonIds) {
        this.buttonIds = buttonIds;
    }
    public String getFuncinfoFunccode() {
        return funcinfoFunccode;
    }
    public void setFuncinfoFunccode(String funcinfoFunccode) {
        this.funcinfoFunccode = funcinfoFunccode;
    }
    public Integer getSyOrderindex() {
        return syOrderindex;
    }
    public void setSyOrderindex(Integer syOrderindex) {
        this.syOrderindex = syOrderindex;
    }
    public NsDataSeeScopeVo getDataSeeScopeVo() {
        return dataSeeScopeVo;
    }
    public void setDataSeeScopeVo(NsDataSeeScopeVo dataSeeScopeVo) {
        this.dataSeeScopeVo = dataSeeScopeVo;
    }
}
