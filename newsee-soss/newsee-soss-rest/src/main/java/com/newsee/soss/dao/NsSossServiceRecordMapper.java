/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;
import com.newsee.soss.entity.NsSossServiceRecord;

public interface NsSossServiceRecordMapper {
	
	List<NsSossServiceRecord> selectServiceRecordList(Map<String, Object> map);
  
    NsSossServiceRecord selectById(Long id);
    
    int insert(NsSossServiceRecord nsSossServiceRecord);
    
    int insertBatch(List<NsSossServiceRecord> nsSossServiceRecordList);
    
    int updateById(NsSossServiceRecord nsSossServiceRecord);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
}