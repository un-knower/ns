/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;

public interface CommonMapper {
  
	/**
	 * 查询表列等信息
	 * @param tableSchema
	 * @param tableName
	 * @return
	 */
	List<Map<String, String>> selectTableInfo(Map<String, String> map);
    
}