package com.newsee.devplatform.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.devplatform.constants.DevPlatformContants;
import com.newsee.devplatform.entity.SyncRecord;
import com.newsee.devplatform.service.IJepfSyncService;
import com.newsee.devplatform.vo.JepfSyncVo;

@RestController
@RequestMapping("/devplatform")
public class JepfSyncController {

	@Autowired
	private IJepfSyncService jepfSyncService;
	
	/**
	 * 同步原始数据，主要是menu数据，功能数据，按钮数据，表头，表单数据
	 * 同步后作为SaaS系统的原始数据
	 * @return
	 */
	@RequestMapping(value="/sync-original", method = RequestMethod.POST)
	public RestResult<Boolean> syncOriginal(@RequestBody JepfSyncVo syncVo){
		//插入同步记录
		SyncRecord addSyncRecord = new SyncRecord();
		if(DevPlatformContants.NS_SOOS_NAME.equals(syncVo.getMenuName())){
			addSyncRecord.setEnterpriseId(syncVo.getOrgList().get(0).getEnterpriseId());
			addSyncRecord.setOrganizationId(syncVo.getOrgList().get(0).getOrganizationId());
			addSyncRecord.setUserId(syncVo.getUserId());
			addSyncRecord.setUserName(syncVo.getOrgName());
		}else{
			addSyncRecord.setEnterpriseId(0L);
			addSyncRecord.setOrganizationId(0L);
			addSyncRecord.setUserId(LoginDataHelper.getUserId());
			addSyncRecord.setUserName(LoginDataHelper.getUserName());
		}
		//同步到newsee-system数据库中
		jepfSyncService.syncOriginal(syncVo, addSyncRecord);
		RestResult<Boolean> result = new RestResult<>(true);
		return result;
	}
}
