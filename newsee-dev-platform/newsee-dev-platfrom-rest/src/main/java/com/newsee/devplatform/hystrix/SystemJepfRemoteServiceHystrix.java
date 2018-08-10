package com.newsee.devplatform.hystrix;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import com.newsee.common.rest.RestResult;
import com.newsee.devplatform.service.remote.ISystemJepfRemoteService;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.vo.JepfVo;
import com.newsee.system.vo.NsSystemSuperAdmin;

@Component
public class SystemJepfRemoteServiceHystrix implements ISystemJepfRemoteService{
	private static final Logger logger = LoggerFactory.getLogger(SystemJepfRemoteServiceHystrix.class);
	
//	public RestResult<Boolean> syncMenus(@RequestBody List<NsCoreMenu> menus){
//		return null;
//	}
//	    
//	public RestResult<Boolean> syncFuncinfos(@RequestBody List<NsCoreFuncinfo> funcinfos){
//		return null;
//	}
//	    
//	public RestResult<Boolean> syncButtons(@RequestBody List<NsCoreResourcebutton> buttons){
//		return null;
//	}
//	    
//	public RestResult<Boolean> syncColumns(@RequestBody List<NsCoreResourcecolumn> columns){
//		return null;
//	}
//	    
//	public RestResult<Boolean> syncFields(@RequestBody List<NsCoreResourcefield> fields){
//		return null;
//	}
	public RestResult<Boolean> syncAll(@RequestBody JepfVo jepfVo){
		logger.equals("△△△△△△△△△△    远程SystemJepfRemoteService.syncAll失败  △△△△△△△△△△  ");
		return new RestResult<>(false);
	}
	
	public RestResult<List<NsSystemOrganization>> getAllCompanyOrg(@RequestParam("orgName") String orgName){
		logger.equals("△△△△△△△△△△    远程SystemJepfRemoteService.getAllCompanyOrg失败  △△△△△△△△△△  ");
		return null;
	}

    @Override
    public RestResult<Boolean> createSuperAdmin(NsSystemSuperAdmin superAdmin) {
        logger.equals("△△△△△△△△△△    远程SystemJepfRemoteService.createSuperAdmin失败  △△△△△△△△△△  ");
        return null;
    }
}
