/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;

import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossMsg;

public interface NsSossMsgMapper {
  
    NsSossMsg selectById(Long id);
    
    int insert(NsSossMsg nsSossMsg);
    
    int insertBatch(List<NsSossMsg> nsSossMsgList);
    
    int updateById(NsSossMsg nsSossMsg);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsSossMsg> listPage(SearchVo vo);
    
}