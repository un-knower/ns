package com.newsee.system.service;

import java.util.Map;

import com.newsee.system.entity.NsCoreFuncinfo;

public interface INsFuncinfoService {

	/**
	 * 根据funcCode获取funcInfo
	 * @author xiaosisi add on 2017/11/02
	 */
    NsCoreFuncinfo getFuncInfo( Map<String, Object> map);
}
