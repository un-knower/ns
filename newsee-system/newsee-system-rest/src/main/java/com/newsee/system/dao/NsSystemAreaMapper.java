package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.system.entity.NsSystemArea;

public interface NsSystemAreaMapper {
	
	/**
	 * 查询区域信息,按父区域编码，PY排序
	 * @param id
	 * @param areaCode
	 * @param areaLevel
	 * @param parentAreaCode
	 * @return
	 */
	List<NsSystemArea> selectAreaInfo(Map<String, Object> map);
    
	/**
	 * 获取所有区域
	 * @return
	 */
    List<NsSystemArea> selectAllArea();
 
}