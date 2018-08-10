package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;

import java.util.List;

import com.newsee.common.vo.BaseVo;

/**
 * Created by niyang on 2017/9/19.
 */
public class HouseStageChangeVo extends BaseVo{

    /**
	 * 
	 */
	private static final long serialVersionUID = -5409261619311041792L;
	
	@ApiModelProperty(value = "房产ID")
    private Long houseId;
    @ApiModelProperty(value = "房态操作动作：1售楼 2收房 3入住 4搬出 5出租 6转租 7退租 8延长空关 9转让")
    private String houseOperateType;
    @ApiModelProperty(value = "关联业主ID")
    private Long ownerId;
    @ApiModelProperty(value = "关联业主姓名")
    private String ownerName;
    @ApiModelProperty(value = "关联业主手机")
    private String ownerPhone;
    @ApiModelProperty(value = "收费对象业主ID")
    private Long changeOwnerId;
    @ApiModelProperty(value = "业务发生时间 yyyy-MM-dd")
    private String handlerTime;
    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "租户ID")
    private Long rentOwnerId;

    @ApiModelProperty(value = "捆绑销售房产ID")
    private List<Long> bindingSellHouseIdList;

    @ApiModelProperty(value = "门禁卡号")
    private List<String> entranceGuardCardList;

    @ApiModelProperty(value = "业务开始时间")
    private String businessStartTime;

    @ApiModelProperty(value = "业务结束时间")
    private String businessEndTime;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getHouseOperateType() {
        return houseOperateType;
    }

    public void setHouseOperateType(String houseOperateType) {
        this.houseOperateType = houseOperateType;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getHandlerTime() {
        return handlerTime;
    }

    public void setHandlerTime(String handlerTime) {
        this.handlerTime = handlerTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<Long> getBindingSellHouseIdList() {
        return bindingSellHouseIdList;
    }

    public void setBindingSellHouseIdList(List<Long> bindingSellHouseIdList) {
        this.bindingSellHouseIdList = bindingSellHouseIdList;
    }

    public List<String> getEntranceGuardCardList() {
        return entranceGuardCardList;
    }

    public void setEntranceGuardCardList(List<String> entranceGuardCardList) {
        this.entranceGuardCardList = entranceGuardCardList;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getOwnerPhone() {
        return ownerPhone;
    }

    public void setOwnerPhone(String ownerPhone) {
        this.ownerPhone = ownerPhone;
    }

    public Long getChangeOwnerId() {
        return changeOwnerId;
    }

    public void setChangeOwnerId(Long changeOwnerId) {
        this.changeOwnerId = changeOwnerId;
    }

    public Long getRentOwnerId() {
        return rentOwnerId;
    }

    public void setRentOwnerId(Long rentOwnerId) {
        this.rentOwnerId = rentOwnerId;
    }

    public String getBusinessStartTime() {
        return businessStartTime;
    }

    public void setBusinessStartTime(String businessStartTime) {
        this.businessStartTime = businessStartTime;
    }

    public String getBusinessEndTime() {
        return businessEndTime;
    }

    public void setBusinessEndTime(String businessEndTime) {
        this.businessEndTime = businessEndTime;
    }
}
