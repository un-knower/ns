package com.newsee.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.entity.NsSystemCompany;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.system.dao.NsSossEnterpriseMapper;
import com.newsee.system.dao.NsSystemCompanyMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.service.ILoginInfoService;

@Service
public class LoginInfoServiceImpl implements ILoginInfoService{
	
	@Autowired
	private NsSystemUserMapper nsSystemUserMapper;
	
	@Autowired
	private NsSystemCompanyMapper nsSystemCompanyMapper;
	
	@Autowired
	private NsSossEnterpriseMapper nsSossEnterpriseMapper;
	
	public NsSystemUser getNsSystemUser(Long userId){
		return nsSystemUserMapper.selectById(userId);
	}
	
	public NsSystemCompany getNsSystemCompany(Long companyId){
		return nsSystemCompanyMapper.selectById(companyId);
	}
	
	/*public NsSossEnterprise  getNsPlatformEnterprise(Long enterpriseId){
		return nsSossEnterpriseMapper.selectByPrimaryKey(enterpriseId);
	}*/
}
