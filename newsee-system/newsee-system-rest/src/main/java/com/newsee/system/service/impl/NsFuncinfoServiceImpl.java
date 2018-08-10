package com.newsee.system.service.impl;


import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.newsee.system.dao.NsCoreFuncinfoMapper;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.service.INsFuncinfoService;

@Service
public class NsFuncinfoServiceImpl implements INsFuncinfoService {
	
	@Autowired
	NsCoreFuncinfoMapper nsCoreFuncinfoMapper;
	
	/**
	 * 根据funcCode获取funcInfo
	 * @author xiaosisi add on 2017/11/02
	 */
	public NsCoreFuncinfo getFuncInfo(Map<String, Object> map){
	    NsCoreFuncinfo info = nsCoreFuncinfoMapper.selectByFuncId(map);
		return info;
	}
	
}
 