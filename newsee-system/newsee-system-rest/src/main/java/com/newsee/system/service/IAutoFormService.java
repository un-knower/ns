package com.newsee.system.service;

import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;

public interface IAutoFormService {
	
	PageInfo<Map<String, Object>> listPage(SearchVo searchVo);
	
	Map<String, Object> detail(Long id);
	
	Boolean editAutoForm(Map<String, Object> map);
	
	Boolean addAutoFrom(Map<String, Object> map);
	
}
