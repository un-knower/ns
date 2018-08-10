package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;

import com.newsee.common.vo.BaseVo;

/**
 *
 * 售楼明细日志
 * Created by niyang on 2017/10/13.
 */
public class HouseStageDetailVo extends BaseVo{

    private static final long serialVersionUID = -3293568607836194651L;
    @ApiModelProperty(value = "明细ID")
    private Long detailId;
    @ApiModelProperty(value = "操作类型")
    private String stageValue;
    @ApiModelProperty(value = "操作类型名称")
    private String stage;
    @ApiModelProperty(value = "业主ID")
    private Long ownerId;
    @ApiModelProperty(value = "业主名称")
    private String ownerName;
    @ApiModelProperty(value = "操作时间")
    private String operateDate;
    @ApiModelProperty(value = "各操作中是否当前记录")
    private Byte isCurrentRecord;
    @ApiModelProperty(value = "是否当前房产记录")
    private Byte isNowStage;
    public Long getDetailId() {
        return detailId;
    }
    public void setDetailId(Long detailId) {
        this.detailId = detailId;
    }
    public String getStage() {
        return stage;
    }
    public void setStage(String stage) {
        this.stage = stage;
    }
    public Long getOwnerId() {
        return ownerId;
    }
    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }
    public String getOwnerName() {
        return ownerName;
    }
    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }
    public String getOperateDate() {
        return operateDate;
    }
    public void setOperateDate(String operateDate) {
        this.operateDate = operateDate;
    }
    public Byte getIsCurrentRecord() {
        return isCurrentRecord;
    }
    public void setIsCurrentRecord(Byte isCurrentRecord) {
        this.isCurrentRecord = isCurrentRecord;
    }
    public String getStageValue() {
        return stageValue;
    }
    public void setStageValue(String stageValue) {
        this.stageValue = stageValue;
    }
    public Byte getIsNowStage() {
        return isNowStage;
    }
    public void setIsNowStage(Byte isNowStage) {
        this.isNowStage = isNowStage;
    }
    
}
