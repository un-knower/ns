/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;
import com.newsee.soss.entity.NsSossNoticeType;

public interface NsSossNoticeTypeMapper {
  
    NsSossNoticeType selectById(Long id);
    
    int insert(NsSossNoticeType nsSossNoticeType);
    
    int insertBatch(List<NsSossNoticeType> nsSossNoticeTypeList);
    
    int updateById(NsSossNoticeType nsSossNoticeType);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
}