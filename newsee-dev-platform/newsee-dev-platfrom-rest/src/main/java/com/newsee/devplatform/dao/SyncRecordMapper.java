/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.devplatform.dao;

import com.newsee.devplatform.entity.SyncRecord;

public interface SyncRecordMapper {
  
	SyncRecord selectById(Long id);
	
	SyncRecord selectLatestByOrg(SyncRecord recorg);
	
    int insert(SyncRecord syncRecord);
    
    int updateById(SyncRecord syncRecord);
    
    int deleteById(Long id);
}