package com.newsee.common.vo;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.newsee.common.entity.FilterEntity;
import com.newsee.common.utils.StringUtils;

import io.swagger.annotations.ApiModelProperty;

/**
 * 列表查询VO
 * @author xiaosisi add on 2017/09/04
 *
 */
public class SearchVo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8725496745848691099L;
	
	/** 企业ID */
	@ApiModelProperty(value = "企业ID; 注：检索时，不用传此参数,自动传入登录人所在企业ID")
    private Long enterpriseId;
    
    /** 组织ID */
	@ApiModelProperty(value = "组织ID")
    private Long organizationId;
	
	 /** 组织ID以及其所有子组织ID */
    @ApiModelProperty(value = "组织ID以及其所有子组织ID")
    private List<Long> organizationIdList;
    
    /** 部门ID */
	@ApiModelProperty(value = "部门ID")
    private Long departmentId;
	
	/** 列表搜索条件-输入框 */
	@ApiModelProperty(value = "列表搜索条件-输入框")
	private String mainSearch;
	
    @ApiModelProperty(value = "列表搜索条件-开始时间")
	private Date startTime;
	
    @ApiModelProperty(value = "列表搜索条件-结束时间")
	private Date endTime;
	
	/** 列表排序字段 */
	@ApiModelProperty(value = "列表排序字段")
	private String orderFieldName;
	
    /** 列表排序字段(下划线格式) */
    @ApiModelProperty(value = "列表排序字段(下划线格式)")
    private String orderFieldUnderLineName;
    
    @ApiModelProperty(value = "列表排序类型 ，分text，number，date，select四种")
    private String orderFieldType;
    
	/** 列表排序方法：升序还是降序  */
	@ApiModelProperty(value = "列表排序方法：升序还是降序")
	private String orderBy;
	
	/** 当前页码 */
	@ApiModelProperty(value = "当前页码")
	private Integer pageNum;
	
	/** 每页条数*/
	@ApiModelProperty(value = "每页条数")
	private Integer pageSize;
	
	@ApiModelProperty(value = "统计类型 0不统计 1统计本页 2统计当前所有")
	private Integer totalType;
	
	/**表格名称，应对一个页面中有多个列表的情况*/
	@ApiModelProperty(value = "表格名称，应对一个页面中有多个列表的情况")
	private String gridName;

	/** 筛选器的检索条件+空白行选中的检索条件 */
	@ApiModelProperty(value = "筛选器的检索条件+空白行选中的检索条件")
	private List<FilterEntity> filterList;

	private List<FilterEntity> treeConditions;
	
	/** 其他检索条件  */
	@ApiModelProperty(value = "其他检索条件，用于存放特殊的检索条件")
	private Map<String, Object> otherConditions;
	
	/**查询条件，公告状态|0保存|1发布|2审核|*/
	private String noticeStatus;
	
	 /** 可见组织ID */
    @ApiModelProperty(value = "可见组织ID")
    private List<Long> seeOrganizationIdList;
    
    /** 可见人员ID(Long型) */
    @ApiModelProperty(value = "可见人员ID")
    private Long seeUserId;
    
    /** 可见人员ID(String型) */
    @ApiModelProperty(value = "可见人员ID")
    private String seeUserIdStr;
	
    private Long id;
    
    private List<Long> ids;
    
    private Long houseId;
    
    private List<Long> houseIds;
    
    private  String houseName;
    
    
	
	public List<Long> getIds() {
		return ids;
	}

	public void setIds(List<Long> ids) {
		this.ids = ids;
	}

	public List<Long> getHouseIds() {
		return houseIds;
	}

	public void setHouseIds(List<Long> houseIds) {
		this.houseIds = houseIds;
	}

	public Long getHouseId() {
		return houseId;
	}

	public void setHouseId(Long houseId) {
		this.houseId = houseId;
	}

	public String getHouseName() {
		return houseName;
	}

	public void setHouseName(String houseName) {
		this.houseName = houseName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNoticeStatus() {
		return noticeStatus;
	}

	public void setNoticeStatus(String noticeStatus) {
		this.noticeStatus = noticeStatus;
	}

	public String getMainSearch() {
		return mainSearch;
	}

	public void setMainSearch(String mainSearch) {
		this.mainSearch = mainSearch;
	}

    public String getOrderFieldName() {
        return orderFieldName;
    }

    public void setOrderFieldName(String orderFieldName) {
        this.orderFieldName = orderFieldName;
        if (!StringUtils.hasLength(this.orderFieldUnderLineName)) {
            this.orderFieldUnderLineName = StringUtils.humpToUnderLine(this.orderFieldName);
        }
    }

    public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public List<FilterEntity> getFilterList() {
		return filterList;
	}

	public void setFilterList(List<FilterEntity> filterList) {
		this.filterList = filterList;
	}

	public String getGridName() {
		return gridName;
	}

	public void setGridName(String gridName) {
		this.gridName = gridName;
	}

    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public String getOrderFieldType() {
        return orderFieldType;
    }

    public void setOrderFieldType(String orderFieldType) {
        this.orderFieldType = orderFieldType;
    }

    public List<FilterEntity> getTreeConditions() {
        return treeConditions;
    }

    public void setTreeConditions(List<FilterEntity> treeConditions) {
        this.treeConditions = treeConditions;
    }

    public Integer getTotalType() {
        return totalType;
    }

    public void setTotalType(Integer totalType) {
        this.totalType = totalType;
    }

    public String getOrderFieldUnderLineName() {
        return orderFieldUnderLineName;
    }

    public void setOrderFieldUnderLineName(String orderFieldUnderLineName) {
        this.orderFieldUnderLineName = StringUtils.humpToUnderLine(this.orderFieldUnderLineName);
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

    public List<Long> getOrganizationIdList() {
        return organizationIdList;
    }

    public void setOrganizationIdList(List<Long> organizationIdList) {
        this.organizationIdList = organizationIdList;
    }

	public Map<String, Object> getOtherConditions() {
		return otherConditions;
	}

	public void setOtherConditions(Map<String, Object> otherConditions) {
		this.otherConditions = otherConditions;
	}

    public List<Long> getSeeOrganizationIdList() {
        return seeOrganizationIdList;
    }

    public void setSeeOrganizationIdList(List<Long> seeOrganizationIdList) {
        this.seeOrganizationIdList = seeOrganizationIdList;
    }

    public Long getSeeUserId() {
        return seeUserId;
    }

    public void setSeeUserId(Long seeUserId) {
        this.seeUserId = seeUserId;
    }

    public String getSeeUserIdStr() {
        return seeUserIdStr;
    }

    public void setSeeUserIdStr(String seeUserIdStr) {
        this.seeUserIdStr = seeUserIdStr;
    }
	
}
