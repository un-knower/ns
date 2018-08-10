package com.newsee.owner.vo;

import io.swagger.annotations.ApiModelProperty;


import com.newsee.common.vo.BaseVo;

/**
 * Created by niyang on 2017/10/13.
 */
public class HouseStageDetailList extends BaseVo{

    /**
	 * 
	 */
	private static final long serialVersionUID = -224932563421622426L;

	@ApiModelProperty(value = "房产ID")
    private Long houseId;

    @ApiModelProperty(value = "项目名称")
    private String precinctName;

    @ApiModelProperty(value = "房产简称")
    private String houseShortName;

    @ApiModelProperty(value = "业主ID")
    private Long ownerId;

    @ApiModelProperty(value = "业主名称")
    private String ownerName;

    @ApiModelProperty(value = "租户ID")
    private Long rentOwnerId;

    @ApiModelProperty(value = "租户名称")
    private String rentOwnerName;

    @ApiModelProperty(value = "证件号码")
    private String certificate;

    @ApiModelProperty(value = "移动电话")
    private String phone;

    @ApiModelProperty(value = "是否当前记录")
    private Boolean isCurrentRecord;

    @ApiModelProperty(value = "状态")
    private Integer stage;

    @ApiModelProperty(value = "操作日期")
    private String handleDate;

    @ApiModelProperty(value = "租户入住日期")
    private String rentCheckInDate;

    public Long getHouseId() {
        return houseId;
    }

    public void setHouseId(Long houseId) {
        this.houseId = houseId;
    }

    public String getPrecinctName() {
        return precinctName;
    }

    public void setPrecinctName(String precinctName) {
        this.precinctName = precinctName;
    }

    public String getHouseShortName() {
        return houseShortName;
    }

    public void setHouseShortName(String houseShortName) {
        this.houseShortName = houseShortName;
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

    public Long getRentOwnerId() {
        return rentOwnerId;
    }

    public void setRentOwnerId(Long rentOwnerId) {
        this.rentOwnerId = rentOwnerId;
    }

    public String getRentOwnerName() {
        return rentOwnerName;
    }

    public void setRentOwnerName(String rentOwnerName) {
        this.rentOwnerName = rentOwnerName;
    }

    public String getCertificate() {
        return certificate;
    }

    public void setCertificate(String certificate) {
        this.certificate = certificate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Boolean getCurrentRecord() {
        return isCurrentRecord;
    }

    public void setCurrentRecord(Boolean currentRecord) {
        isCurrentRecord = currentRecord;
    }

    public Integer getStage() {
        return stage;
    }

    public void setStage(Integer stage) {
        this.stage = stage;
    }

    public String getHandleDate() {
        return handleDate;
    }

    public void setHandleDate(String handleDate) {
        this.handleDate = handleDate;
    }

    public String getRentCheckInDate() {
        return rentCheckInDate;
    }

    public void setRentCheckInDate(String rentCheckInDate) {
        this.rentCheckInDate = rentCheckInDate;
    }
}
