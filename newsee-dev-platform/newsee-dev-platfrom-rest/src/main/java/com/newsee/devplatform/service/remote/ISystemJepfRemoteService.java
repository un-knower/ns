package com.newsee.devplatform.service.remote;

import java.util.List;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.rest.RestResult;
import com.newsee.devplatform.hystrix.SystemJepfRemoteServiceHystrix;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.vo.JepfVo;
import com.newsee.system.vo.NsSystemSuperAdmin;


@FeignClient(value="system-server", fallback = SystemJepfRemoteServiceHystrix.class)
public interface ISystemJepfRemoteService {
	
//	@RequestMapping(value = "/sync/sync-meuns", method = RequestMethod.POST)
//	public RestResult<Boolean> syncMenus(@RequestBody List<NsCoreMenu> menus);
//	    
//	@RequestMapping(value = "/sync/sync-funcinfos", method = RequestMethod.POST)
//	public RestResult<Boolean> syncFuncinfos(@RequestBody List<NsCoreFuncinfo> funcinfos);
//	    
//	@RequestMapping(value = "/sync/sync-buttons", method = RequestMethod.POST)
//	public RestResult<Boolean> syncButtons(@RequestBody List<NsCoreResourcebutton> buttons);
//	    
//	@RequestMapping(value = "/sync/sync-colums", method = RequestMethod.POST)
//	public RestResult<Boolean> syncColumns(@RequestBody List<NsCoreResourcecolumn> columns);
//	    
//	@RequestMapping(value = "/sync/sync-fields", method = RequestMethod.POST)
//	public RestResult<Boolean> syncFields(@RequestBody List<NsCoreResourcefield> fields);
	
	@RequestMapping(value = "/sync/sync-all", method = RequestMethod.POST)
	public RestResult<Boolean> syncAll(@RequestBody JepfVo jepfVo);
	
	 @RequestMapping(value = "/sync/get-all-company-org", method = RequestMethod.POST)
	public RestResult<List<NsSystemOrganization>> getAllCompanyOrg(@RequestParam("orgName") String orgName);
	 
	 @RequestMapping(value = "/role/create-super-admin", method = RequestMethod.POST)
	 public RestResult<Boolean> createSuperAdmin(@RequestBody NsSystemSuperAdmin superAdmin);
}
