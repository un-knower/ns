package com.newsee.system.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.newsee.common.rest.RestResult;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.entity.NsCoreMenu;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.entity.NsCoreResourcecolumn;
import com.newsee.system.entity.NsCoreResourcefield;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.service.IJepfSyncService;
import com.newsee.system.vo.JepfVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@ResponseBody
@RequestMapping("/sync")
@Api(tags = {"com.newsee.system.controller.JepfSyncController"}, description = "JEPF同步 REST API，从JEPF中同步所需数据。")
public class JepfSyncController {
    
    @Autowired
    private IJepfSyncService jepfSyncService;
    
    @ApiOperation(value = "同步menu")
    @RequestMapping(value = "/sync-meuns", method = RequestMethod.POST)
    public RestResult<Boolean> syncMenus(@RequestBody List<NsCoreMenu> menus) {
    	Boolean result = jepfSyncService.syncMenu(menus);
    	return new RestResult<>(result);
    }
    
    @ApiOperation(value = "同步funcinfo")
    @RequestMapping(value = "/sync-funcinfos", method = RequestMethod.POST)
    public RestResult<Boolean> syncFuncinfos(@RequestBody List<NsCoreFuncinfo> funcinfos) {
    	Boolean result = jepfSyncService.syncFuncinfo(funcinfos);
    	return new RestResult<>(result);
    }
    
    @ApiOperation(value = "同步button")
    @RequestMapping(value = "/sync-buttons", method = RequestMethod.POST)
    public RestResult<Boolean> syncButtons(@RequestBody List<NsCoreResourcebutton> buttons) {
    	Boolean result = jepfSyncService.syncResourceButton(buttons);
    	return new RestResult<>(result);
    }
    
    @ApiOperation(value = "同步columns")
    @RequestMapping(value = "/sync-colums", method = RequestMethod.POST)
    public RestResult<Boolean> syncColumns(@RequestBody List<NsCoreResourcecolumn> columns) {
    	Boolean result = jepfSyncService.syncResourceCloumn(columns);
    	return new RestResult<>(result);
    }
    
    @ApiOperation(value = "同步fields")
    @RequestMapping(value = "/sync-fields", method = RequestMethod.POST)
    public RestResult<Boolean> syncFields(@RequestBody List<NsCoreResourcefield> fields) {
    	Boolean result = jepfSyncService.syncResourceField(fields);
    	return new RestResult<>(result);
    }
    
    @ApiOperation(value = "同步JEPF中的所有数据")
    @RequestMapping(value = "/sync-all", method = RequestMethod.POST)
    public RestResult<Boolean> syncAll(@RequestBody JepfVo jepfVo) {
    	Boolean result = jepfSyncService.syncAll(jepfVo);
    	return new RestResult<>(result);
    }
    
    @ApiOperation(value = "获取所有公司级别以上的组织")
    @RequestMapping(value = "/get-all-company-org", method = RequestMethod.POST)
    public RestResult<List<NsSystemOrganization>> getAllCompanyOrg(@ApiParam(name="orgName",value="组织名称")@RequestParam("orgName") String orgName) {
    	List<NsSystemOrganization> list = jepfSyncService.getAllCompanyLevelOrg(orgName);
    	return new RestResult<>(list);
    }

}
