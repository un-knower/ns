package com.newsee.owner.vo;

import java.io.Serializable;

public class HouseBuildingVo implements Serializable{

    private static final long serialVersionUID = -5219811573175564094L;

    private OwnerBuildingInfoVo buildingInfoVo;
    
    private OwnerBuildingExtendInfoVo buildingExtendInfoVo;

    public OwnerBuildingInfoVo getBuildingInfoVo() {
        return buildingInfoVo;
    }

    public void setBuildingInfoVo(OwnerBuildingInfoVo buildingInfoVo) {
        this.buildingInfoVo = buildingInfoVo;
    }

    public OwnerBuildingExtendInfoVo getBuildingExtendInfoVo() {
        return buildingExtendInfoVo;
    }

    public void setBuildingExtendInfoVo(OwnerBuildingExtendInfoVo buildingExtendInfoVo) {
        this.buildingExtendInfoVo = buildingExtendInfoVo;
    }
    
}
