package com.newsee.system.service;

import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.entity.NsSystemCompany;
import com.newsee.common.entity.NsSystemUser;

/**
 * 登录信息服务，根据相关id获取相关信息
 * 包含用户信息，公司信息，企业信息
 * @author xiaosisi add on 2017/12/13
 *
 */
public interface ILoginInfoService {

	NsSystemUser getNsSystemUser(Long userId);
	
	NsSystemCompany getNsSystemCompany(Long companyId);
	
	/*NsSossEnterprise getNsPlatformEnterprise(Long enterpriseId);*/
}
