/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.Date;
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

import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
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
import com.newsee.soss.service.INoticeService;
import com.newsee.soss.service.remote.IFileRemoteService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.NoticeVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/notice")
@Api(tags = {"com.newsee.soss.controller.NoticeController"}, description = "公告列表页面操作 REST API，包含公告页面的所有操作方法。")
public class NoticeController{
    
    @Autowired
    private INoticeService noticeService;
    
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
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
         String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.SOSS_NOTICELIST);
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
    
    @ApiOperation(value = "公告列表获取")
	@RequestMapping(value = "/list-notice", method = RequestMethod.POST)
	public RestResult<PageInfo<NoticeVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	RestResult<PageInfo<NoticeVo >> restResult = null;
        PageInfo<NoticeVo> pageInfo = noticeService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "公告详情获取")
	@RequestMapping(value = "/detail-notice", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailNotice(@ApiParam(value = "公告ID") @RequestParam(value = "id")Long id) 
			throws IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException,
			IntrospectionException{
		RestResult<Map<String, Object>> result = null;
		//获取公告详情信息
		NoticeVo vo = noticeService.detail(id);
		//获取表单
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        RestResult<Map<String, Object>> results = systemRemoteService.listField(commonVo);
        Map<String, Object> resultData = results.getResultData();
        if (!Objects.isNull(vo)) {
            vo = CommonUtils.clearNull(vo);
        	//获取Image图片
    		List<FileVo> fileList = fileRemoteService.findFileList(vo.getImageCode(),enterpriseId);
    		if (!CollectionUtils.isEmpty(fileList)) {
    			List<String> tList = fileList.stream().map(obj -> obj.getFileUrl()).collect(Collectors.toList());
    			vo.setImageUrl(String.join(",", tList));
    		}
    		//获取Icon图片
    		 fileList = fileRemoteService.findFileList(vo.getIconCode(),enterpriseId);
    		if (!CollectionUtils.isEmpty(fileList)) {
    			List<String> tList = fileList.stream().map(obj -> obj.getFileUrl()).collect(Collectors.toList());
    			vo.setIconUrl(String.join(",", tList));
    		}
            //详情覆盖modelData
            resultData.put(FormConstants.FORM_MODEL_DATA, vo);
            //将json字符串形式的form表单装换成相应的对象
            List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
            resultData.put(FormConstants.FORM_FIELDS, formFields);
            
        }
	    result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "编辑公告")
	@RequestMapping(value = "/add-notice", method = RequestMethod.POST)
	public RestResult<Boolean> aaNotice(@ApiParam(value = "公告详情")@RequestBody NoticeVo vo) {
		RestResult<?> result = null;
		//编辑公告详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long userId = LoginDataHelper.getUserId();
		vo.setHandleUserId(userId);
		vo.setHandleUserName(LoginDataHelper.getUserName());
		vo.setEnterpriseId(enterpriseId);
		//添加图片
		if (!StringUtils.isEmpty(vo.getIconUrl())) {
			String code = CommonUtils.getUUId();
			FileVo fileVo = new FileVo();
			fileVo.setPath(vo.getIconUrl());
			fileVo.setEnterpriseId(enterpriseId);
			fileVo.setFileCode(code);
			result = fileRemoteService.saveFile(fileVo);
			if (!ResultCodeEnum.SUCCESS.CODE.equals(result.getResultCode())) {
				throw new BizException(ResultCodeEnum.FAILURE.CODE, ResultCodeEnum.FAILURE.DESC);
			} else {
				vo.setIconCode(code);
			}
		}
		if (!StringUtils.isEmpty(vo.getImageUrl())) {
			String code = CommonUtils.getUUId();
			FileVo fileVo = new FileVo();
			fileVo.setPath(vo.getImageUrl());
			fileVo.setEnterpriseId(enterpriseId);
			fileVo.setFileCode(code);
			result = fileRemoteService.saveFile(fileVo);
			if (!ResultCodeEnum.SUCCESS.CODE.equals(result.getResultCode())) {
				throw new BizException(ResultCodeEnum.FAILURE.CODE, ResultCodeEnum.FAILURE.DESC);
			} else {
				vo.setImageCode(code);
			}
		}
		boolean res = noticeService.add(vo);
		
		return new RestResult<Boolean>(true);
	}
	
	@ApiOperation(value = "编辑公告")
	@RequestMapping(value = "/edit-notice", method = RequestMethod.POST)
	public RestResult<Boolean> editNotice(@ApiParam(value = "公告详情")@RequestBody NoticeVo vo) {
		//编辑公告详情信息
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		vo.setHandleUserId(userId);
		vo.setHandleUserName(userName);
		vo.setEnterpriseId(enterpriseId);
		if("1".equals(vo.getNoticeStatus())) vo.setPublishTime(new Date());
		RestResult<?> result = null;
		//修改图片
		if (!StringUtils.isEmpty(vo.getIconUrl())) {
			String code = CommonUtils.getUUId();
			FileVo fileVo = new FileVo();
			fileVo.setPath(vo.getIconUrl());
			fileVo.setEnterpriseId(enterpriseId);
			fileVo.setFileCode(code);
			result  = fileRemoteService.saveFile(fileVo);
			if (!ResultCodeEnum.SUCCESS.CODE.equals(result.getResultCode())) {
				throw new BizException(ResultCodeEnum.FAILURE.CODE, ResultCodeEnum.FAILURE.DESC);
			} else {
				vo.setIconCode(code);
			}
		}
		if (!StringUtils.isEmpty(vo.getImageUrl())) {
			String code = CommonUtils.getUUId();
			FileVo fileVo = new FileVo();
			fileVo.setPath(vo.getImageUrl());
			fileVo.setFileCode(code);
			fileVo.setEnterpriseId(enterpriseId);
			result = fileRemoteService.saveFile(fileVo);
			if (!ResultCodeEnum.SUCCESS.CODE.equals(result.getResultCode())) {
				throw new BizException(ResultCodeEnum.FAILURE.CODE, ResultCodeEnum.FAILURE.DESC);
			} else {
				vo.setImageCode(code);
			}
		}
		boolean res = noticeService.edit(vo);
		
		return new RestResult<Boolean>(res);
	}

	@ApiOperation(value = "删除公告")
	@RequestMapping(value = "/delete-notice")
	public RestResult<Boolean> deleteNotice(@ApiParam(value = "公告ID") @RequestParam("id") Long id) {
		//删除公告详情信息
		boolean result = noticeService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除公告")
	@RequestMapping(value = "/delete-notice-batch")
	public RestResult<Boolean> deleteNoticeBatch(@ApiParam(value = "公告ID")@RequestBody List<Long> ids) {
		//删除公告详情信息
		boolean result = noticeService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
}
