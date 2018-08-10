package com.newsee.devplatform.service;

import com.newsee.devplatform.entity.JeCoreFuncinfo;
import com.newsee.devplatform.entity.SyncRecord;
import com.newsee.devplatform.vo.JepfSyncVo;

public interface IJepfSyncService {
	
	/**
	 * 同步原始数据
	 * @return
	 */
	void syncOriginal(JepfSyncVo syncVo, SyncRecord record);
	
	
	/**
	 * 根据id获取funcinfo
	 * 获取功能是使用
	 * @param funCode
	 * @return
	 */
	JeCoreFuncinfo getFuncinfoById(String funId);
	
}
