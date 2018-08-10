package com.newsee.owner.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

public class SearchHouseTreeApp implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "房产编号")
	private Long HouseId;
	
	@ApiModelProperty(value = "房产名称")
	private String HouseName;
	
	@ApiModelProperty(value = "房产全称")
	private String AncestorName;
	
	@ApiModelProperty(value = "房产类型")
	private String Reskind;
	
	@ApiModelProperty(value = "房态编号")
	private String RoomStatus;
	
	@ApiModelProperty(value = "房态名称")
	private String RoomStatusName;
	
	@ApiModelProperty(value = "业主名称")
	private String Owner;
	
	@ApiModelProperty(value = "建筑面积")
	private String FloorSpace;
	
	@ApiModelProperty(value = "计价面积")
	private String SalesArea;
	
	@ApiModelProperty(value = "单价")
	private String HousePrice;
	
	@ApiModelProperty(value = "楼层")
	private Integer BelongFloor;
	
	@ApiModelProperty(value = "总价")
	private String TotalSum;
	
	@ApiModelProperty(value = "层级")
	private String Level;
	
	@ApiModelProperty(value = "合同号")
	private Integer ContractID;
	
	@ApiModelProperty(value = "是否最末节点")
	private Integer DeepestNode;
	
	@ApiModelProperty(value = "绑定的主房产")
	private Integer SalesMainHouseID;
	
	@ApiModelProperty(value = "绑定关系")
	private Integer SalesRelation;

	public Integer getSalesMainHouseID() {
		return SalesMainHouseID;
	}

	public void setSalesMainHouseID(Integer salesMainHouseID) {
		SalesMainHouseID = salesMainHouseID;
	}

	public Integer getSalesRelation() {
		return SalesRelation;
	}

	public void setSalesRelation(Integer salesRelation) {
		SalesRelation = salesRelation;
	}

	public Long getHouseId() {
		return HouseId;
	}

	public void setHouseId(Long houseId) {
		HouseId = houseId;
	}

	public String getHouseName() {
		return HouseName;
	}

	public void setHouseName(String houseName) {
		HouseName = houseName;
	}

	public String getAncestorName() {
		return AncestorName;
	}

	public void setAncestorName(String ancestorName) {
		AncestorName = ancestorName;
	}

	public String getReskind() {
		return Reskind;
	}

	public void setReskind(String reskind) {
		Reskind = reskind;
	}

	public String getRoomStatus() {
		return RoomStatus;
	}

	public void setRoomStatus(String roomStatus) {
		RoomStatus = roomStatus;
	}

	public String getRoomStatusName() {
		return RoomStatusName;
	}

	public void setRoomStatusName(String roomStatusName) {
		RoomStatusName = roomStatusName;
	}

	public String getOwner() {
		return Owner;
	}

	public void setOwner(String owner) {
		Owner = owner;
	}

	public String getFloorSpace() {
		return FloorSpace;
	}

	public void setFloorSpace(String floorSpace) {
		FloorSpace = floorSpace;
	}

	public String getSalesArea() {
		return SalesArea;
	}

	public void setSalesArea(String salesArea) {
		SalesArea = salesArea;
	}

	public String getHousePrice() {
		return HousePrice;
	}

	public void setHousePrice(String housePrice) {
		HousePrice = housePrice;
	}

	public Integer getBelongFloor() {
		return BelongFloor;
	}

	public void setBelongFloor(Integer belongFloor) {
		BelongFloor = belongFloor;
	}

	public String getTotalSum() {
		return TotalSum;
	}

	public void setTotalSum(String totalSum) {
		TotalSum = totalSum;
	}

	public String getLevel() {
		return Level;
	}

	public void setLevel(String level) {
		Level = level;
	}

	public Integer getContractID() {
		return ContractID;
	}

	public void setContractID(Integer contractID) {
		ContractID = contractID;
	}

	public Integer getDeepestNode() {
		return DeepestNode;
	}

	public void setDeepestNode(Integer deepestNode) {
		DeepestNode = deepestNode;
	}
	
	
}
