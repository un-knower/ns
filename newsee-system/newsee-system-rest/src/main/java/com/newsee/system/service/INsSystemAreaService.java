package com.newsee.system.service;

import java.util.List;

import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.vo.NsSystemAreaVo;

public interface INsSystemAreaService {
	
	/**
	 * 查询区域信息
	 * @param id
	 * @param areaCode
	 * @param areaLevel
	 * @param parentAreaCode
	 * @return
	 */
	List<NsSystemArea> findAreaInfo(NsSystemArea area) throws Exception;
    
	/**
	 * 获取所有区域
	 * @return
	 */
    List<NsSystemAreaVo> findAllArea() throws Exception;
    
    /**
     * 根据区域编码，查询区域信息
     * @param areaCodeList
     * @return
     * @throws Exception
     */
    List<NsSystemArea> findAreaInfo(List<String> areaCodeList) throws Exception;

}
