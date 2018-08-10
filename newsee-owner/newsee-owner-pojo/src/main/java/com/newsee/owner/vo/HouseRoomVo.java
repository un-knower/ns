package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

public class HouseRoomVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 786562064188142294L;

    @ApiModelProperty(value = "房产基本信息")
    private OwnerRoomInfoVo roomInfoVo;

    @ApiModelProperty(value = "房产扩展详细信息")
    private OwnerRoomExtendInfoVo roomExtendInfoVo;

    public OwnerRoomInfoVo getRoomInfoVo() {
        return roomInfoVo;
    }

    public void setRoomInfoVo(OwnerRoomInfoVo roomInfoVo) {
        this.roomInfoVo = roomInfoVo;
    }

    public OwnerRoomExtendInfoVo getRoomExtendInfoVo() {
        return roomExtendInfoVo;
    }

    public void setRoomExtendInfoVo(OwnerRoomExtendInfoVo roomExtendInfoVo) {
        this.roomExtendInfoVo = roomExtendInfoVo;
    }
    
}
