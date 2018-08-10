package com.newsee.owner.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 报表数据vo
 * @author Administrator
 *
 */
public class ReportFormVo implements Serializable {
	
	public enum ConstantType {
		空置("1", "空置"),
		已售("2", "已售"),
		待收房("3", "待收房"),
		已收房("4", "已收房"),
		入住("5", "入住"),
		空关("6", "空关"),
		装修中("7", "装修中"),
		已装修("8", "已装修"),
		未装修("9", "未装修"),
		出租("10", "出租"),
		未租("12", "未租"),
		停用("11", "停用"),
		
		期初("20", "期初"),
		本期("21", "本期"),
		期末("22", "期末"),
		累计("23", "累计"),
		
		数量("30", "数量"),
		计费面积("31", "计费面积"),
		建筑面积("32", "建筑面积"),
		公摊面积("33", "公摊面积"),
		套内面积("34", "套内面积"),
		项目名称("35", "项目名称"),
		所属公司("36", "所属公司"),
		房产类型("37", "房产类型"),
		房态类型("38", "房态类型"),
		数量比率("39", "数量比率"),
		面积比率("40", "面积比率"),
		
		/**房产汇总-房产类型*/
		REPORT_OWNER_HOUSETYPE("100", "REPORT_OWNER_HOUSETYPE"),
		/**房产汇总-收费项目*/
		REPORT_OWNER_FREEHOUSE("110", "REPORT_OWNER_FREEHOUSE"),
		/**房态变动信息汇总*/
		REPORT_OWNER_CHANGEINFO("120", "REPORT_OWNER_CHANGEINFO")
		;
		
		private String title;
		private String value;
		private ConstantType(String value, String title) {
			this.value = value;
			this.title = title;
		}
		public String getTitle() {
			return title;
		}
		public String getValue() {
			return value;
		}
	}
	
	/**
	 * 统计结果信息
	 * @author Administrator
	 *
	 */
	public class ReportRes implements Serializable{
		/**
		 * 
		 */
		private static final long serialVersionUID = 2634303305460502959L;
		private Long precinctId;//项目ID
		private String precinctName;//项目名称
		private Long houseId;
		private Long organizationId;
		private String houseName;
		private String companyName; //公司名
		private String houseType;
		private String houseTypeName;  //房产类型
		private String houseSubTypeName; //房产性质类型
		private String houseSubType; //房间性质类型
		private Long count;  //数量
		private BigDecimal chargingArea; //计费面积
		private BigDecimal buildingArea; //建筑面积
		private BigDecimal insidingArea; //套内面积
		private BigDecimal poolArea; //公摊面积
		private List<Long> houseIdList;
		private Float counRatio; //数量比率
		private Float chargingAreaRatio; //计费面积比率
		
		private ReportRes saledInfo; //已售
		private ReportRes saleNotInfo; //空置
		private ReportRes soufangWaitInfo; //待收房
		private ReportRes soufangedInfo; //已收房
		private ReportRes ruzhuInfo; //入住
		private ReportRes ruzhuNotInfo; //空关
		private ReportRes decoratedInfo; //已装修
		private ReportRes decoratingInfo; //装修中
		private ReportRes decorateNotInfo; //未装修
		private ReportRes rentInfo; //出租
		private ReportRes stopInfo; //停用
		
		private ReportRes beforeInfo; //期初
		private ReportRes benInfo; //本期
		private ReportRes afterInfo; //期末
		private ReportRes nowInfo; //累计
		
		public ReportRes getBeforeInfo() {
			return beforeInfo;
		}
		public void setBeforeInfo(ReportRes beforeInfo) {
			this.beforeInfo = beforeInfo;
		}
		public ReportRes getBenInfo() {
			return benInfo;
		}
		public void setBenInfo(ReportRes benInfo) {
			this.benInfo = benInfo;
		}
		public ReportRes getAfterInfo() {
			return afterInfo;
		}
		public void setAfterInfo(ReportRes afterInfo) {
			this.afterInfo = afterInfo;
		}
		public ReportRes getNowInfo() {
			return nowInfo;
		}
		public void setNowInfo(ReportRes nowInfo) {
			this.nowInfo = nowInfo;
		}
		public Float getCounRatio() {
			return counRatio;
		}
		public void setCounRatio(Float counRatio) {
			this.counRatio = counRatio;
		}
		public Float getChargingAreaRatio() {
			return chargingAreaRatio;
		}
		public void setChargingAreaRatio(Float chargingAreaRatio) {
			this.chargingAreaRatio = chargingAreaRatio;
		}
		public String getHouseSubType() {
			return houseSubType;
		}
		public List<Long> getHouseIdList() {
			return houseIdList;
		}
		public void setHouseIdList(List<Long> houseIdList) {
			this.houseIdList = houseIdList;
		}
		public String getHouseType() {
			return houseType;
		}
		public void setHouseType(String houseType) {
			this.houseType = houseType;
		}
		public void setHouseSubType(String houseSubType) {
			this.houseSubType = houseSubType;
		}
		public Long getHouseId() {
			return houseId;
		}
		public void setHouseId(Long houseId) {
			this.houseId = houseId;
		}
		public Long getOrganizationId() {
			return organizationId;
		}
		public void setOrganizationId(Long organizationId) {
			this.organizationId = organizationId;
		}
		public String getHouseName() {
			return houseName;
		}
		public void setHouseName(String houseName) {
			this.houseName = houseName;
		}
		public String getCompanyName() {
			return companyName;
		}
		public void setCompanyName(String companyName) {
			this.companyName = companyName;
		}
		public String getHouseTypeName() {
			return houseTypeName;
		}
		public void setHouseTypeName(String houseTypeName) {
			this.houseTypeName = houseTypeName;
		}
		public String getHouseSubTypeName() {
			return houseSubTypeName;
		}
		public void setHouseSubTypeName(String houseSubTypeName) {
			this.houseSubTypeName = houseSubTypeName;
		}
		
		public BigDecimal getChargingArea() {
            return chargingArea;
        }
        public void setChargingArea(BigDecimal chargingArea) {
            this.chargingArea = chargingArea;
        }
        public BigDecimal getBuildingArea() {
            return buildingArea;
        }
        public void setBuildingArea(BigDecimal buildingArea) {
            this.buildingArea = buildingArea;
        }
        public BigDecimal getInsidingArea() {
            return insidingArea;
        }
        public void setInsidingArea(BigDecimal insidingArea) {
            this.insidingArea = insidingArea;
        }
        public BigDecimal getPoolArea() {
            return poolArea;
        }
        public void setPoolArea(BigDecimal poolArea) {
            this.poolArea = poolArea;
        }
        public ReportRes getSaledInfo() {
			return saledInfo;
		}
		public void setSaledInfo(ReportRes saledInfo) {
			this.saledInfo = saledInfo;
		}
		public ReportRes getSaleNotInfo() {
			return saleNotInfo;
		}
		public void setSaleNotInfo(ReportRes saleNotInfo) {
			this.saleNotInfo = saleNotInfo;
		}
		public ReportRes getSoufangWaitInfo() {
			return soufangWaitInfo;
		}
		public void setSoufangWaitInfo(ReportRes soufangWaitInfo) {
			this.soufangWaitInfo = soufangWaitInfo;
		}
		public ReportRes getSoufangedInfo() {
			return soufangedInfo;
		}
		public void setSoufangedInfo(ReportRes soufangedInfo) {
			this.soufangedInfo = soufangedInfo;
		}
		public ReportRes getRuzhuInfo() {
			return ruzhuInfo;
		}
		public void setRuzhuInfo(ReportRes ruzhuInfo) {
			this.ruzhuInfo = ruzhuInfo;
		}
		public ReportRes getRuzhuNotInfo() {
			return ruzhuNotInfo;
		}
		public void setRuzhuNotInfo(ReportRes ruzhuNotInfo) {
			this.ruzhuNotInfo = ruzhuNotInfo;
		}
		public ReportRes getDecoratedInfo() {
			return decoratedInfo;
		}
		public void setDecoratedInfo(ReportRes decoratedInfo) {
			this.decoratedInfo = decoratedInfo;
		}
		public ReportRes getDecoratingInfo() {
			return decoratingInfo;
		}
		public void setDecoratingInfo(ReportRes decoratingInfo) {
			this.decoratingInfo = decoratingInfo;
		}
		public ReportRes getDecorateNotInfo() {
			return decorateNotInfo;
		}
		public void setDecorateNotInfo(ReportRes decorateNotInfo) {
			this.decorateNotInfo = decorateNotInfo;
		}
		public ReportRes getRentInfo() {
			return rentInfo;
		}
		public void setRentInfo(ReportRes rentInfo) {
			this.rentInfo = rentInfo;
		}
		public ReportRes getStopInfo() {
			return stopInfo;
		}
		public void setStopInfo(ReportRes stopInfo) {
			this.stopInfo = stopInfo;
		}
        public Long getPrecinctId() {
            return precinctId;
        }
        public void setPrecinctId(Long precinctId) {
            this.precinctId = precinctId;
        }
        public String getPrecinctName() {
            return precinctName;
        }
        public void setPrecinctName(String precinctName) {
            this.precinctName = precinctName;
        }
        public Long getCount() {
            return count;
        }
        public void setCount(Long count) {
            this.count = count;
        }
		
	} 

	/**
	 * 
	 */
	private static final long serialVersionUID = 2374305352151439709L;
	
	private Long enterpriseId;
	@ApiModelProperty(name="项目ID，以\",\"号隔开")
	private String houseIdJson;
	@ApiModelProperty(name="开始时间")
	private Date startTime;
	@ApiModelProperty(name="截止时间")
	private Date endTime;
	private Date endGtTime;
	@ApiModelProperty(name="报表类型 1 项目汇总表 - 房产类型，2 项目汇总表 - 收费项目，3 房态变动信息汇总 ")
	private String reportType;
	private String houseOperateType; //房态操作动作
	//业务发展时间
	private Date handleTime;
	private Date handleStartTime;
	//未开始时间
	private Date notStartTime;
	private Date startLtTime;
	
	//房产ID
	private Long houseId;
	//房产ID，数据集
	private List<Long> houseIdList;
	//组织ID
	private Long organizationId;
	//项目名
	private String houseName;
	//项目全称
	private String houseFullName;
	@ApiModelProperty(name="房产类型")
	private String houseType;
	//树节点路径，例：/1/2/3
	private String path;
	@ApiModelProperty(name="房态当前状态")
	private String stage;
	//大于，房态当前状态
	private String stageGt;
	//出租状态
	private String rentStage;
	//装修状态
	private String decorateStage;
	//是否停用
	private Integer isBlockUp;	
	private Date blockUpTime;
	
	//========房产===========
	/**房产类型*/
	private String roomTypeId;
	 /**计费面积*/
    private Long chargingArea;
    /**建筑面积*/
    private Long buildingArea;
    /**辅助计费面积*/
    private Long assistChargingArea;    
    /**套内面积*/
    private Long insideArea;
    /**公摊面积*/
    private Long poolArea;
    /**花园面积*/
    private Long gardenArea;
    /**地下室面积*/
    private Long basementArea;
    /**赠送面积*/
    private Long giftArea;
    /**房产性质ID*/
    private String roomPropertyId;
    /**房产户型ID*/
    private String roomHouseType;
    /**收房日期*/
    private Date takeOverTime;
    private List<String> houseTypeList;
    
  //========车位===========
    
  //========公共区域===========
	
	public String getReportType() {
		return reportType;
	}
	public Date getBlockUpTime() {
		return blockUpTime;
	}
	public void setBlockUpTime(Date blockUpTime) {
		this.blockUpTime = blockUpTime;
	}
	public List<String> getHouseTypeList() {
		return houseTypeList;
	}
	public void setHouseTypeList(List<String> houseTypeList) {
		this.houseTypeList = houseTypeList;
	}
	public Date getEndGtTime() {
		return endGtTime;
	}
	public void setEndGtTime(Date endGtTime) {
		this.endGtTime = endGtTime;
	}
	public Date getStartLtTime() {
		return startLtTime;
	}
	public void setStartLtTime(Date startLtTime) {
		this.startLtTime = startLtTime;
	}
	public Date getHandleStartTime() {
		return handleStartTime;
	}
	public void setHandleStartTime(Date handleStartTime) {
		this.handleStartTime = handleStartTime;
	}
	public void setReportType(String reportType) {
		this.reportType = reportType;
	}
	public String getRoomTypeId() {
		return roomTypeId;
	}
	public void setRoomTypeId(String roomTypeId) {
		this.roomTypeId = roomTypeId;
	}
	public Long getChargingArea() {
		return chargingArea;
	}
	public void setChargingArea(Long chargingArea) {
		this.chargingArea = chargingArea;
	}
	public Long getBuildingArea() {
		return buildingArea;
	}
	public void setBuildingArea(Long buildingArea) {
		this.buildingArea = buildingArea;
	}
	public Long getAssistChargingArea() {
		return assistChargingArea;
	}
	public void setAssistChargingArea(Long assistChargingArea) {
		this.assistChargingArea = assistChargingArea;
	}
	public Long getInsideArea() {
		return insideArea;
	}
	public void setInsideArea(Long insideArea) {
		this.insideArea = insideArea;
	}
	public Long getPoolArea() {
		return poolArea;
	}
	public void setPoolArea(Long poolArea) {
		this.poolArea = poolArea;
	}
	public Long getGardenArea() {
		return gardenArea;
	}
	public void setGardenArea(Long gardenArea) {
		this.gardenArea = gardenArea;
	}
	public Long getBasementArea() {
		return basementArea;
	}
	public void setBasementArea(Long basementArea) {
		this.basementArea = basementArea;
	}
	public Long getGiftArea() {
		return giftArea;
	}
	public void setGiftArea(Long giftArea) {
		this.giftArea = giftArea;
	}
	public String getRoomPropertyId() {
		return roomPropertyId;
	}
	public void setRoomPropertyId(String roomPropertyId) {
		this.roomPropertyId = roomPropertyId;
	}
	public String getRoomHouseType() {
		return roomHouseType;
	}
	public void setRoomHouseType(String roomHouseType) {
		this.roomHouseType = roomHouseType;
	}
	public Date getTakeOverTime() {
		return takeOverTime;
	}
	public void setTakeOverTime(Date takeOverTime) {
		this.takeOverTime = takeOverTime;
	}
	public Date getHandleTime() {
		return handleTime;
	}
	public void setHandleTime(Date handleTime) {
		this.handleTime = handleTime;
	}
	public Date getNotStartTime() {
		return notStartTime;
	}
	public void setNotStartTime(Date notStartTime) {
		this.notStartTime = notStartTime;
	}
	public String getHouseFullName() {
		return houseFullName;
	}
	public void setHouseFullName(String houseFullName) {
		this.houseFullName = houseFullName;
	}
	public String getHouseIdJson() {
		return houseIdJson;
	}
	public void setHouseIdJson(String houseIdJson) {
		this.houseIdJson = houseIdJson;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public Long getHouseId() {
		return houseId;
	}
	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}
	public Long getOrganizationId() {
		return organizationId;
	}
	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}
	public String getHouseName() {
		return houseName;
	}
	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}
	public String getHouseType() {
		return houseType;
	}
	public void setHouseType(String houseType) {
		this.houseType = houseType;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getStage() {
		return stage;
	}
	public void setStage(String stage) {
		this.stage = stage;
	}
	public String getStageGt() {
		return stageGt;
	}
	public void setStageGt(String stageGt) {
		this.stageGt = stageGt;
	}
	public String getRentStage() {
		return rentStage;
	}
	public void setRentStage(String rentStage) {
		this.rentStage = rentStage;
	}
	public String getDecorateStage() {
		return decorateStage;
	}
	public void setDecorateStage(String decorateStage) {
		this.decorateStage = decorateStage;
	}
	public Integer getIsBlockUp() {
		return isBlockUp;
	}
	public void setIsBlockUp(Integer isBlockUp) {
		this.isBlockUp = isBlockUp;
	}
	public List<Long> getHouseIdList() {
		return houseIdList;
	}
	public void setHouseIdList(List<Long> houseIdList) {
		this.houseIdList = houseIdList;
	}
	public String getHouseOperateType() {
		return houseOperateType;
	}
	public void setHouseOperateType(String houseOperateType) {
		this.houseOperateType = houseOperateType;
	}
    public Long getEnterpriseId() {
        return enterpriseId;
    }
    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

}
