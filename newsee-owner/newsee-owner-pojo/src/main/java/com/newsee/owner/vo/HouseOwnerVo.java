package com.newsee.owner.vo;

import java.io.Serializable;
import java.util.List;
import com.newsee.owner.entity.HouseOwner;
import com.newsee.owner.entity.OwnerCarsInfo;

public class HouseOwnerVo extends HouseOwner implements Serializable {

	private static final long serialVersionUID = -2799545686696897012L;
	
	/**车辆信息集合*/
	private List<OwnerCarsInfo> ownerCarsInfoList;
	/**业主集合*/
	private List<HouseOwnerVo> householdList;
	/**业主ID集合*/
	private List<Long> ids;
//	/**业主日志集合*/
//	private List<Log> logList;
	
    public List<OwnerCarsInfo> getOwnerCarsInfoList() {
		return ownerCarsInfoList;
	}

	public void setOwnerCarsInfoList(List<OwnerCarsInfo> ownerCarsInfoList) {
		this.ownerCarsInfoList = ownerCarsInfoList;
	}

	public List<HouseOwnerVo> getHouseholdList() {
        return householdList;
    }

    public void setHouseholdList(List<HouseOwnerVo> householdList) {
        this.householdList = householdList;
    }

	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

//	public List<Log> getLogList() {
//		return logList;
//	}
//
//	public void setLogList(List<Log> logList) {
//		this.logList = logList;
//	}
	
	
    
}
