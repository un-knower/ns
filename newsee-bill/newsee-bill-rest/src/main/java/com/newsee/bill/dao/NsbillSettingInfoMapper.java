/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import java.util.List;
import java.util.Map;
import com.newsee.bill.entity.NsbillSettingInfo;
import com.newsee.common.vo.SearchVo;

public interface NsbillSettingInfoMapper {
  
    NsbillSettingInfo selectById(Long id);
    
    int insert(NsbillSettingInfo nsbillSettingInfo);
    
    int insertBatch(List<NsbillSettingInfo> nsbillSettingInfoList);
    
    int updateById(NsbillSettingInfo nsbillSettingInfo);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillSettingInfo> listPage(SearchVo vo);
    
}