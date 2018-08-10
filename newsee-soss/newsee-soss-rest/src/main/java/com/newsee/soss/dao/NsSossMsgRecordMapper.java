/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.vo.SearchVo;
import com.newsee.soss.entity.NsSossMsgRecord;

public interface NsSossMsgRecordMapper {
  
    NsSossMsgRecord selectById(Long id);
    
    int insert(NsSossMsgRecord nsSossMsgRecord);
    
    int insertBatch(List<NsSossMsgRecord> nsSossMsgRecordList);
    
    int updateById(NsSossMsgRecord nsSossMsgRecord);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsSossMsgRecord> listPage(SearchVo vo);
    
    /**
     * 查询消息记录信息
     * @param enterpriseId
     * @param msgType
     * @param isRead
     * @return
     */
    List<NsSossMsgRecord> selectMsgRecordList(Map<String, Object> map);
    
}