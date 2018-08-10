package com.newsee.owner.vo;

import java.io.Serializable;

public class HouseCarportVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 5126440592108374009L;

    private OwnerCarportInfoVo carportInfoVo;
    
    private OwnerCarportExtendInfoVo carportExtendInfoVo;

    public OwnerCarportInfoVo getCarportInfoVo() {
        return carportInfoVo;
    }

    public void setCarportInfoVo(OwnerCarportInfoVo carportInfoVo) {
        this.carportInfoVo = carportInfoVo;
    }

    public OwnerCarportExtendInfoVo getCarportExtendInfoVo() {
        return carportExtendInfoVo;
    }

    public void setCarportExtendInfoVo(OwnerCarportExtendInfoVo carportExtendInfoVo) {
        this.carportExtendInfoVo = carportExtendInfoVo;
    }

}
