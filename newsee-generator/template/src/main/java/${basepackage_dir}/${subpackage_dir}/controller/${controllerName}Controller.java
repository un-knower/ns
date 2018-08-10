<#include "/java_copyright.include">
<#assign pageNameLower = pageName?uncap_first>
<#assign pageNameUpper = pageName?upper_case>
<#assign controllerNameLower = controllerName?uncap_first>
package ${basepackage}.${subpackage}.controller;

import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.newsee.common.constant.Constants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.common.vo.SystemResourceFieldVo;
import com.newsee.common.constant.FormConstants;
<#list tablesName as tableString>
import ${basepackage}.${subpackage}.entity.${tableString};
</#list>
import ${basepackage}.${subpackage}.service.I${pageName}Service;
import ${basepackage}.${subpackage}.service.remote.ISystemRemoteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/${controllerNameLower}")
@Api(tags = ${r'{'}"${basepackage}.${subpackage}.controller.${controllerName}Controller"${r'}'}, description = "${pageCnName}列表页面操作 REST API，包含${pageCnName}页面的所有操作方法。")
public class ${controllerName}Controller{
    
    @Autowired
    private I${controllerName}Service ${controllerNameLower}Service;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getCompanyLevelOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.${pageNameUpper});
         String interpreter = LoginDataHelper.getFieldInterpreter();
         String formOperateType = LoginDataHelper.getFormOperateType();
         LoginCommonDataVo commonVo = new LoginCommonDataVo();
         commonVo.setOrganizationId(organizationId);
         commonVo.setEnterpriseId(enterpriseId);
         commonVo.setFuncId(funcId);
         commonVo.setInterpreter(interpreter);
         commonVo.setFormOperateType(formOperateType);
         RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
         Map<String, Object> resultData = result.getResultData();
         //检查表单中是否有表格项目，并且做相应处理
         List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
         resultData.put(FormConstants.FORM_FIELDS, formFields);
         return result;
    }
    
    @ApiOperation(value = "${pageCnName}列表获取")
	@RequestMapping(value = "/list-${controllerNameLower}", method = RequestMethod.POST)
	public RestResult<PageInfo<${controllerName}>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	BizException.isNull(searchVo, "查询条件");
    	RestResult<PageInfo<${controllerName}>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = LoginDataHelper.getFuncId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        PageInfo<${controllerName}> pageInfo =  ${controllerNameLower}Service.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "${pageCnName}详情获取")
	@RequestMapping(value = "/detail-${controllerNameLower}", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detail${controllerName}(@ApiParam(value = "${pageCnName}ID") @RequestParam(value = "id")Long id){
		Long organizationId= LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.ITEM_LIST);
        String interpreter = LoginDataHelper.getFieldInterpreter();
        String formOperateType = LoginDataHelper.getFormOperateType();
        Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
        LoginCommonDataVo commonVo = new LoginCommonDataVo();
        commonVo.setOrganizationId(organizationId);
        commonVo.setGroupLevelOrgId(groupLevelOrgId);
        commonVo.setEnterpriseId(enterpriseId);
        commonVo.setFuncId(funcId);
        commonVo.setInterpreter(interpreter);
        commonVo.setFormOperateType(formOperateType);
        RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
        Map<String, Object> resultData = result.getResultData();
        List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
        resultData.put(FormConstants.FORM_FIELDS, formFields);
		//获取${pageCnName}详情信息
		${controllerName}Vo vo = ${controllerNameLower}Service.detail(id);
		resultData.put(FormConstants.FORM_MODEL_DATA, vo);
	    result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "编辑${pageCnName}")
	@RequestMapping(value = "/add-${controllerNameLower}", method = RequestMethod.POST)
	public RestResult<Boolean> aa${controllerName}(@ApiParam(value = "${pageCnName}详情")@RequestBody ${controllerName}Vo vo) {
		//编辑${pageCnName}详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
		Long userId = LoginDataHelper.getUserId();
		vo.setEnterpriseId(enterpriseId);
		vo.setOrganizationId(organizationId);
		vo.setHandlerId(userId);
		boolean result = ${controllerNameLower}Service.add(vo);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑${pageCnName}")
	@RequestMapping(value = "/edit-${controllerNameLower}", method = RequestMethod.POST)
	public RestResult<Boolean> edit${controllerName}(@ApiParam(value = "${pageCnName}详情")@RequestBody ${controllerName}Vo vo) {
		//编辑${pageCnName}详情信息
		Long userId = LoginDataHelper.getUserId();
		vo.setHandlerId(userId);
		boolean result = ${controllerNameLower}Service.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除${pageCnName}")
	@RequestMapping(value = "/delete-${controllerNameLower}")
	public RestResult<Boolean> delete${controllerName}(@ApiParam(value = "${pageCnName}ID") @RequestParam("id") Long id) {
		//删除${pageCnName}详情信息
		boolean result = ${controllerNameLower}Service.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除${pageCnName}")
	@RequestMapping(value = "/delete-${controllerNameLower}-batch")
	public RestResult<Boolean> delete${controllerName}Batch(@ApiParam(value = "${pageCnName}ID") @RequestBody List<Long> ids) {
		//删除${pageCnName}详情信息
		boolean result = ${controllerNameLower}Service.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
}
