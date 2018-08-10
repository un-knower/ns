/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossService;

public interface NsSossServiceMapper {
  
	List<NsSossService> listResultBySearch(SearchVo searchVo);
	
    NsSossService selectById(Long id);
    
    int insert(NsSossService nsSossService);
    
    int insertBatch(List<NsSossService> nsSossServiceList);
    
    int updateById(NsSossService nsSossService);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    /**
     * 删除状态
     * @param isDelete
     * @param idList
     * @param id
     * @return
     */
    int updateDelete(Map<String, Object> map);
    
    /**
     * 统计工单信息
     * @param enterpriseId
     * @return [{status:0, count:3}]
     */
    List<Map<String, Object>> selectStatisticalCount(Map<String, Object> map);
}