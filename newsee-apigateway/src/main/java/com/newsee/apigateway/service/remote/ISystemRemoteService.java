package com.newsee.apigateway.service.remote;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.entity.NsSystemCompany;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.rest.RestResult;

import io.swagger.annotations.ApiOperation;

/**
 * Created by niyang on 2017/8/11.
 */
@FeignClient("system-server")
public interface ISystemRemoteService {

	@RequestMapping(value="/organization/orgid-company-level", method = RequestMethod.GET)
	public RestResult<Long> getOrgIdCompanyLevel(@RequestParam(value="organizationId") Long organizationId);
	
    @RequestMapping(value ="/organization/orgid-Group-level", method = RequestMethod.GET)
    public RestResult<Long> getGroupOrgIdCompanyLevel(@RequestParam(value="organizationId") Long organizationId);
	 
	@RequestMapping(value="/lcinfo/user-info",method=RequestMethod.GET)
	public RestResult<NsSystemUser> userInfo(@RequestParam("appId") String appId, @RequestParam("userId") Long userId);
		
	@RequestMapping(value="/lcinfo/company-info",method=RequestMethod.GET)
	public RestResult<NsSystemCompany> companyInfo(@RequestParam("appId") String appId, @RequestParam("companyId") Long companyId);

	@RequestMapping(value="/lcinfo/enterprise-info",method=RequestMethod.GET)
	public RestResult<NsSossEnterprise> enterpriseInfo(@RequestParam("appId") String appId, @RequestParam("enterpriseId") Long enterpriseId);
	
    @RequestMapping(value = "/permission/list-data-perm", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> listDataPerm(@RequestParam(value="userId") Long userId,@RequestParam(value="funcId") String funcId);
}
