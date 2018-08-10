/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.controller;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.FileVo;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.soss.common.SossConstants;
import com.newsee.soss.entity.NsSossServiceRecord;
import com.newsee.soss.service.IServiceService;
import com.newsee.soss.service.remote.IFileRemoteService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.ServiceVo;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/service")
@Api(tags = {"com.newsee.soss.controller.ServiceController"}, description = "工单列表页面操作 REST API，包含工单页面的所有操作方法。")
public class ServiceController{
    
    @Autowired
    private IServiceService serviceService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    @Autowired
    private IFileRemoteService fileRemoteService;
    
    @Autowired
    RedisUtil redisUtil;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getOrgId();
    	 Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.SOSS_SERVICELIST);
         String interpreter = LoginDataHelper.getFieldInterpreter();
         String formOperateType = LoginDataHelper.getFormOperateType();
         LoginCommonDataVo commonVo = new LoginCommonDataVo();
         commonVo.setOrganizationId(organizationId);
         commonVo.setEnterpriseId(enterpriseId);
         commonVo.setGroupLevelOrgId(groupLevelOrgId);
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
    
    @ApiOperation(value = "工单列表获取")
	@RequestMapping(value = "/list-service", method = RequestMethod.POST)
	public RestResult<PageInfo<ServiceVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	RestResult<PageInfo<ServiceVo >> restResult = null;
    	Long enterpriseId = LoginDataHelper.getEnterpriseId();
    	Integer userType = LoginDataHelper.getAppUser().getUserType();
    	if(0 != userType ) searchVo.setEnterpriseId(enterpriseId);
        PageInfo<ServiceVo> pageInfo = serviceService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}
    

    /*@ApiOperation(value = "工单详情获取")
	@RequestMapping(value = "/detail-service", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailService(@ApiParam(value = "工单ID") @RequestParam(value = "id")Long id) 
			throws IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException,
			IntrospectionException{
		RestResult<Map<String, Object>> result = null;
		//获取工单详情信息
		ServiceVo vo = serviceService.detail(id);
		//获取表单
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        RestResult<Map<String, Object>> results = systemRemoteService.listField(commonVo);
        Map<String, Object> resultData = results.getResultData();
        if (!Objects.isNull(vo)) {
            vo = CommonUtils.clearNull(vo);
            //详情覆盖modelData
            resultData.put(FormConstants.FORM_MODEL_DATA, vo);
            //将json字符串形式的form表单装换成相应的对象
            List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
            resultData.put(FormConstants.FORM_FIELDS, formFields);
        }
	    result = new RestResult<>(resultData);
		return result;
	}*/
    
    @ApiOperation(value = "工单详情获取")
	@RequestMapping(value = "/detail-service", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailService(@ApiParam(value = "工单ID") @RequestParam(value = "id")Long id) {
    	RestResult<Map<String, Object>> result = RestResult.SUCCESS;
    	result.setResultData(null);
    	Long enterpriseId = LoginDataHelper.getEnterpriseId();
    	Integer userType = LoginDataHelper.getAppUser().getUserType();
    	ServiceVo vo = serviceService.detail(id);
    	vo.setUserType(userType);
    	List<Integer> serviceModels = null;
    	if (!Objects.isNull(vo)) {
    		Long orgId = LoginDataHelper.getOrgId();
    		Map<String, String> dicMap = this.getDictionary(SossConstants.SERVICE_STATUS, orgId);
    		vo.setOldStatus(vo.getStatus());
    		vo.setStatusName(dicMap.get(vo.getStatus()));
    		List<NsSossServiceRecord> recordList = vo.getServiceRecordList(); 
    		if (CollectionUtils.isEmpty(recordList)) {
    			if (enterpriseId == 1) { //运营
    				if(SossConstants.SERVICE_STATUS_VALUE_OVER.compareTo(vo.getStatus()) > 0) {
    					serviceModels = Arrays.asList(1,1,1,0);
    				} else {
    					serviceModels = Arrays.asList(1,1,0,0);
    				}    				
    			} else {
    				serviceModels = Arrays.asList(1,0,0,0);
    			}
    		} else {
    			if(SossConstants.SERVICE_STATUS_VALUE_OVER.compareTo(vo.getStatus()) > -1) {//已完成
    				serviceModels = Arrays.asList(1,1,0,1);
    			} else {
    				if (enterpriseId == 1) { //运营
    					serviceModels = Arrays.asList(1,1,1,0);
    				} else {
    					serviceModels = Arrays.asList(1,1,0,0);    				
    				}    				 
    			}
    		}
    		//获取图片
    		List<FileVo> fileList = fileRemoteService.findFileList(vo.getImageCode(), enterpriseId);
    		if (!CollectionUtils.isEmpty(fileList)) {
    			List<String> tList = fileList.stream().map(obj -> obj.getFileUrl()).collect(Collectors.toList());
    			vo.setImageUrl(String.join(",", tList));
    		}
    		
    	}
    	Map<String, Object> map = new HashMap<>();
    	map.put("serviceModels", serviceModels);
    	map.put("serviceVo", vo);
    	result = new RestResult<>(map);    	
		return result;    	
    }
    
	@ApiOperation(value = "新增工单")
	@RequestMapping(value = "/add-service", method = RequestMethod.POST)
	public RestResult<Boolean> aaService(@ApiParam(value = "工单详情")@RequestBody ServiceVo vo) {
		//编辑工单详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long userId = LoginDataHelper.getUserId();
		vo.setEnterpriseId(enterpriseId);
		vo.setCreateUserId(userId);
		vo.setCreateUserName(LoginDataHelper.getUserName());
		//工单图片
		if (!StringUtils.isEmpty(vo.getImageUrl())) {
			List<FileVo> urlList = JSON.parseArray(vo.getImageUrl(), FileVo.class);
			String imgCode = this.saveFile(urlList);
			vo.setImageCode(imgCode);
		}
		boolean result = serviceService.add(vo);
		return new RestResult<Boolean>(result);
	}
	@ApiOperation(value = "编辑工单")
	@RequestMapping(value = "/edit-service", method = RequestMethod.POST)
	public RestResult<Boolean> editService(@ApiParam(value = "工单详情")@RequestBody ServiceVo vo) {
		//编辑工单详情信息
		Long userId = LoginDataHelper.getUserId();
		Integer userType = LoginDataHelper.getAppUser().getUserType();
		vo.setUserType(userType);
		vo.setCreateUserId(userId);
		vo.setCreateUserName(LoginDataHelper.getUserName());
		NsSystemUser user = LoginDataHelper.getNsSystemUser();
		vo.setHandleUserPhone(user.getUserTelephone());
		if(0 ==userType) {//运维人员
			if(SossConstants.SERVICE_STATUS_VALUE_WAIT.equals(vo.getOldStatus())) {
				vo.setStatus(SossConstants.SERVICE_STATUS_VALUE_DOING);
			}else if(SossConstants.SERVICE_STATUS_VALUE_DOING.equals(vo.getOldStatus())) {
				vo.setStatus(SossConstants.SERVICE_STATUS_VALUE_CONFIRM);
			}else {
				vo.setStatus(vo.getOldStatus());
			}
		}else {//客户
			if(SossConstants.SERVICE_STATUS_VALUE_EVLUING.compareTo(vo.getStatus()) == 0) {//客户确认结单
				vo.setStatus(String.valueOf(vo.getStatus()));
			}else if(SossConstants.SERVICE_STATUS_VALUE_EVLUED.equals(vo.getStatus())) {//客户评价
				vo.setStatus(String.valueOf(vo.getStatus()));
			}else if(!SossConstants.SERVICE_STATUS_VALUE_WAIT.equals(vo.getOldStatus())){
				vo.setStatus(SossConstants.SERVICE_STATUS_VALUE_DOING);
			}else {
				vo.setStatus(SossConstants.SERVICE_STATUS_VALUE_WAIT);
			}
		}
		if (!StringUtils.isEmpty(vo.getImageUrl())) {
			List<FileVo> urlList = JSON.parseArray(vo.getImageUrl(), FileVo.class);
			String imgCode = this.saveFile(urlList);
			vo.setImageCode(imgCode);
		}
		/*if (SossConstants.SERVICE_STATUS_VALUE_EVLUING.compareTo(vo.getOldStatus()) < 0 ) { //TODO 先不做评价功能
			return RestResult.SUCCESS;
		} else if(SossConstants.SERVICE_STATUS_VALUE_EVLUING.compareTo(vo.getOldStatus()) ==0){ 
			vo.setStatus(String.valueOf(vo.getOldStatus()));
		}else{
			int status = Integer.parseInt(vo.getOldStatus());
			status += 1;
			vo.setStatus(String.valueOf(status));
		}
		if (SossConstants.SERVICE_STATUS_VALUE_CONFIRM.equals(vo.getOldStatus())) { //客户确认，反馈
			//判断是否有图片
			if (!StringUtils.isEmpty(vo.getImageUrl())) {
				List<FileVo> urlList = JSON.parseArray(vo.getImageUrl(), FileVo.class);
				String imgCode = this.saveFile(urlList);
				vo.setImageCode(imgCode);
			}
		}*/
		//修改工单
		boolean result = serviceService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除工单")
	@RequestMapping(value = "/delete-service")
	public RestResult<Boolean> deleteService(@ApiParam(value = "工单ID") @RequestParam("id") Long id) {
		//删除工单详情信息
		boolean result = serviceService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除工单")
	@RequestMapping(value = "/delete-service-batch")
	public RestResult<Boolean> deleteServiceBatch(@ApiParam(value = "工单ID")@RequestBody List<Long> ids) {
		//删除工单详情信息
		boolean result = serviceService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
	
	/**
	 * 获取字典数据
	 * @param code 类型编码
	 * @param orgId
	 * @return
	 */
	private Map<String, String> getDictionary(String code, Long orgId) {		 
		//获取字典类型
		NsCoreDictionary dictionary = new NsCoreDictionary();
		dictionary.setOrganizationId(orgId);			 
		dictionary.setDictionaryDdcode(code); //产品套餐类型
		RestResult<NsCoreDictionaryVo> tempRes = systemRemoteService.getDictionary(dictionary);
		List<NsCoreDictionaryitemVo> itemList = null;
		if (tempRes != null && !Objects.isNull(tempRes.getResultData())) { //获取产品套餐类型
			NsCoreDictionaryVo dicVo = tempRes.getResultData();
			itemList = dicVo.getDictionaryitemVos();
		}
		//转换字典数据
		Map<String, String> dicMap = new HashMap<>();
		if (!CollectionUtils.isEmpty(itemList)) {
			for (NsCoreDictionaryitemVo item : itemList) {
				dicMap.put(item.getDictionaryitemItemcode(), item.getDictionaryitemItemname());
			}
		}
		
		return dicMap;
	}
	
	private String saveFile(List<FileVo> urlList) {
		if (CollectionUtils.isEmpty(urlList)) {
			return null;
		}
		List<String> temps = urlList.stream().map(obj -> obj.getFileUrl()).collect(Collectors.toList());
		List<String> tempPaths = urlList.stream().map(obj -> obj.getPath()).collect(Collectors.toList());
		String imgCode = CommonUtils.getUUId();		 
		FileVo fileVo = new FileVo();		
		fileVo.setFileUrl(String.join(",", temps));
		fileVo.setPath(String.join(",", tempPaths));
		fileVo.setFileCode(imgCode);
		fileVo.setEnterpriseId(LoginDataHelper.getEnterpriseId());
		RestResult<?> result = fileRemoteService.saveFile(fileVo);
		if (!ResultCodeEnum.SUCCESS.CODE.equals(result.getResultCode())) {
			throw new BizException(ResultCodeEnum.FAILURE.CODE, ResultCodeEnum.FAILURE.DESC);
		}
		return imgCode;
	}
	
}
