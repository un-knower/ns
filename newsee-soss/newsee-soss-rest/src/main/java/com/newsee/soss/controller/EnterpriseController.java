/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.entity.AppUser;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.ProvinceCityArea;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.soss.common.SossConstants;
import com.newsee.soss.service.IEnterpriseService;
import com.newsee.soss.service.remote.IJepfSyncRemoteService;
import com.newsee.soss.service.remote.IOauthRemoteService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.AppUserVo;
import com.newsee.soss.vo.EnterpriseVo;
import com.newsee.soss.vo.JepfSyncOrgVo;
import com.newsee.soss.vo.JepfSyncVo;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.vo.NsSystemAreaVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/enterprise")
@Api(tags = {"com.newsee.soss.controller.EnterpriseController"}, description = "企业列表页面操作 REST API，包含企业页面的所有操作方法。")
public class EnterpriseController{
    
    @Autowired
    private IEnterpriseService enterpriseService;
    
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @Autowired
    private IOauthRemoteService oauthRemoteService;
    
    @Autowired
    RedisUtil redisUtil;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getOrgId();
    	 Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.SOSS_ENTERPRISELIST);
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
         
         if(Objects.isNull(resultData.get(FormConstants.FORM_MODEL_DATA))){
        	 EnterpriseVo vo = new EnterpriseVo();
        	 //防止省市区控件报错
             ProvinceCityArea pca = new ProvinceCityArea(null, null, null, null);
             vo.setProvinceCityArea(pca);
             resultData.put(FormConstants.FORM_MODEL_DATA, vo);
         }
         
         return result;
    }
    
    @ApiOperation(value = "企业列表获取")
	@RequestMapping(value = "/list-enterprise", method = RequestMethod.POST)
	public RestResult<PageInfo<EnterpriseVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	RestResult<PageInfo<EnterpriseVo >> restResult = null;
    	//TODO 判断是否是运营人员
    	//1 是, 查询所有数据
    	PageInfo<EnterpriseVo> pageInfo = enterpriseService.listPage(searchVo);
    	
    	//2 否
    	searchVo.setEnterpriseId(2L);
        
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "企业详情获取")
	@RequestMapping(value = "/detail-enterprise", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailEnterprise(@ApiParam(value = "企业ID") @RequestParam(value = "enterpriseid")Long enterpriseid) 
			throws IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException,
			IntrospectionException{
		RestResult<Map<String, Object>> result = null;
		//获取企业详情信息
		EnterpriseVo vo = enterpriseService.detail(enterpriseid);
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
	}
    
    @ApiOperation(value = "初始化省市区控件")
    @RequestMapping(value = "/init-provinceCityArea", method = RequestMethod.GET)
    public RestResult<Map<String, List<NsSystemAreaVo>>> initProvinceCityArea(
            @RequestParam(name="areaLevel", required=false) String areaLevel, 
            @RequestParam(name="areaCode", required=false) String areaCode,
            @RequestParam(name="queryParam", required=false) String queryParam){
        Map<String,List<NsSystemAreaVo>> itemListMap = null;
        
        //点击请求
        if ((!StringUtils.isBlank(areaLevel) || !StringUtils.isBlank(areaCode)) && (StringUtils.isBlank(queryParam))) {
            if (("1".equals(areaLevel) && StringUtils.isBlank(areaCode)) ||
                ("2".equals(areaLevel) && !StringUtils.isBlank(areaCode))||
                ("3".equals(areaLevel) && !StringUtils.isBlank(areaCode))||
                ("4".equals(areaLevel) && !StringUtils.isBlank(areaCode))) {
                itemListMap = new HashMap<>();
                List<NsSystemAreaVo> areaList = new ArrayList<>();
                RestResult<List<NsSystemAreaVo>> resultAreas = systemRemoteService.areaFuncinfo(areaLevel, areaCode);
                if (!Objects.isNull(resultAreas)) {
                   areaList = resultAreas.getResultData();
                }
                if ("1".equals(areaLevel)) {
                    itemListMap.put("provinces", areaList);
                }else if ("2".equals(areaLevel)) {
                    itemListMap.put("cities", areaList);
                }else if ("3".equals(areaLevel)) {
                    itemListMap.put("districts", areaList);
                }else if ("4".equals(areaLevel)) {
                    itemListMap.put("streets", areaList);
                }
            }
        }
        
        //编辑页面初始化请求
        if ((StringUtils.isBlank(areaLevel) && StringUtils.isBlank(areaCode)) && (!StringUtils.isBlank(queryParam))) {
            itemListMap = new HashMap<>();
            Long id = Long.parseLong(queryParam);
            EnterpriseVo vo = enterpriseService.detail(id);
            ProvinceCityArea provincialandcity = vo.getProvinceCityArea();
            //省
            List<NsSystemAreaVo> provinceitemList = new ArrayList<>();
            NsSystemAreaVo provinceEntity = new NsSystemAreaVo();
            NsSystemArea proArea = systemRemoteService.getArea(provincialandcity.getProvince());
            if (!Objects.isNull(proArea)) {
                provinceEntity.setLabel(proArea.getAreaName());
                provinceEntity.setValue(provincialandcity.getProvince());
                provinceitemList.add(provinceEntity);
                itemListMap.put("provinces", provinceitemList);
            }else{
                itemListMap.put("provinces", new ArrayList<>());
            }
            //市
            List<NsSystemAreaVo> cityitemList = new ArrayList<>();
            NsSystemAreaVo cityEntity = new NsSystemAreaVo();
            NsSystemArea cityArea = systemRemoteService.getArea(provincialandcity.getCity());
            if (!Objects.isNull(cityArea)) {
                cityEntity.setLabel(cityArea.getAreaName());
                cityEntity.setValue(provincialandcity.getCity());
                cityitemList.add(cityEntity);
                itemListMap.put("cities", cityitemList);
            }else{
                itemListMap.put("cities", new ArrayList<>());
            }
            //区
            List<NsSystemAreaVo> areaitemList = new ArrayList<>();
            NsSystemAreaVo areaEntity = new NsSystemAreaVo();
            NsSystemArea areaArea = systemRemoteService.getArea(provincialandcity.getDistrict());
            if (!Objects.isNull(areaArea)) {
                areaEntity.setLabel(areaArea.getAreaName());
                areaEntity.setValue(provincialandcity.getDistrict());
                areaitemList.add(areaEntity);
                itemListMap.put("districts", areaitemList);
            }else{
                itemListMap.put("districts", new ArrayList<>());
            }
            //街道
            List<NsSystemAreaVo> streetitemList = new ArrayList<>();
            NsSystemAreaVo streetEntity = new NsSystemAreaVo();
            NsSystemArea streetArea = systemRemoteService.getArea(provincialandcity.getStreet());
            if (!Objects.isNull(streetArea)) {
                areaEntity.setLabel(streetArea.getAreaName());
                areaEntity.setValue(provincialandcity.getStreet());
                areaitemList.add(streetEntity);
                itemListMap.put("streets", streetitemList);
            }else{
                itemListMap.put("streets", new ArrayList<>());
            }
        }
        return new RestResult<>(itemListMap);
    }

	@ApiOperation(value = "新增企业")
	@RequestMapping(value = "/add-enterprise", method = RequestMethod.POST)
	public RestResult<Boolean> aaEnterprise(@ApiParam(value = "企业详情")@RequestBody EnterpriseVo vo) {
		//编辑企业详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();		 
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		vo.setEnterpriseId(enterpriseId);
		vo.setCreateUserId(userId);
		vo.setCreateUserName(userName);		 
		boolean result = enterpriseService.add(vo);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑企业")
	@RequestMapping(value = "/edit-enterprise", method = RequestMethod.POST)
	public RestResult<Boolean> editEnterprise(@ApiParam(value = "企业详情")@RequestBody EnterpriseVo vo) {
		//编辑企业详情信息
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		vo.setUpdateUserId(userId);
		vo.setUpdateUserName(userName);
		boolean result = enterpriseService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除企业")
	@RequestMapping(value = "/delete-enterprise")
	public RestResult<Boolean> deleteEnterprise(@ApiParam(value = "企业ID") @RequestParam("enterpriseid") Long enterpriseid) {
		//删除企业详情信息
		boolean result = enterpriseService.delete(enterpriseid);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除企业")
	@RequestMapping(value = "/delete-enterprise-batch")
	public RestResult<Boolean> deleteEnterpriseBatch(@ApiParam(value = "企业ID")@RequestBody List<Long> ids) {
		 if (CollectionUtils.isEmpty(ids)) {
			 throw new BizException(ResultCodeEnum.FAILURE.CODE, "企业ID is null");
		 }
		//删除企业详情信息
		boolean result = enterpriseService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "获取企业信息")
	@RequestMapping(value = "/get-enterpriseInfo", method = RequestMethod.GET)
	public NsSossEnterprise getEnterpriseInfo(@ApiParam(value = "企业ID") @RequestParam("enterpriseId") Long enterpriseId) {
		 if (Objects.isNull(enterpriseId)) {
			 throw new BizException(ResultCodeEnum.FAILURE.CODE, "企业ID is null");
		 }
		 NsSossEnterprise enterprise = null;
		 try {
			enterprise = enterpriseService.getEnterpriseInfo(enterpriseId);
		} catch (Exception e) {
			throw new BizException(ResultCodeEnum.FAILURE.CODE, e.getMessage());
		}
		
		return enterprise;
	}
	@Autowired
	private IJepfSyncRemoteService JepfSyncRemoteService;
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "企业注册")
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public RestResult<?> registerInfo(@RequestBody EnterpriseVo enterpriseVo, @RequestHeader(name="appId", required=false) String appId) throws Exception {
		List<NsSossEnterprise> enterpriseList  = new ArrayList<NsSossEnterprise>();
		RestResult<?> result= new RestResult<>(enterpriseList);
		if (Objects.isNull(enterpriseVo)) {
			result = RestResult.PARAMS_MISSING;
			return result;
		}
		//1 获取手机号，并查询该手机号注册过的所有企业
		StringBuffer key = null;
		if (enterpriseVo.getRegisterStep() == 1) {
			//进行验证码判断
			key = new StringBuffer(RedisKeysConstants.REDIS_REGISTER_PHONE_CODE_PREFIX);
			key.append(enterpriseVo.getRegisterUserPhone());
			String code = redisUtil.getStringValue(key.toString());
			if (StringUtils.isBlank(code)) { //验证码过期
				result = new RestResult<>(ResultCodeEnum.VERIFICATE_CODE_EXPIRATION.CODE, ResultCodeEnum.VERIFICATE_CODE_EXPIRATION.DESC);
				return result;
			}
			if (!code.equals(enterpriseVo.getVerificateCode())) { //验证码错误
				result = new RestResult<>(ResultCodeEnum.VERIFICATE_CODE_ERROR.CODE, ResultCodeEnum.VERIFICATE_CODE_ERROR.DESC);
				return result;
			} 
			/**验证成功，查询是否已经注册过其他企业*/
			//根据手机号，到system查询该员工信息，注册的企业信息
			 List<NsSystemUser> users = systemRemoteService.getUserInfo(null, enterpriseVo.getRegisterUserPhone());
			if (!CollectionUtils.isEmpty(users)) {
				List<Long> userIds = new ArrayList<Long>();
				for(NsSystemUser user :users) {
					userIds.add(user.getUserId());
				}
				//查询企业名称
				 enterpriseList = enterpriseService.findEnterpriseInfoListByUserIds(userIds);	
				if (!CollectionUtils.isEmpty(enterpriseList)) {
					key = new StringBuffer(RedisKeysConstants.REDIS_REGISTER_ENTERPRISE_PREFIX).append(enterpriseVo.getRegisterUserPhone());
					redisUtil.setObjectValue(key.toString(), enterpriseList, 12*3600);
					//处理企业数据 [{enterpriseId: enterpriseName},...]
					Map<String, Object> map = null;
					List<Map<String, Object>> mapList = new ArrayList<>(enterpriseList.size());
					for (NsSossEnterprise enterprise : enterpriseList) {
						map = new HashMap<>(4);
						map.put("enterpriseId", enterprise.getEnterpriseId());
						map.put("name", enterprise.getName());
						mapList.add(map);
					}
					result = new RestResult<>(enterpriseList);
				}
			}
		} else if (enterpriseVo.getRegisterStep() == 2) { //2 录入注册信息
			//保存企业信息
			if (StringUtils.isBlank(enterpriseVo.getName())) {
				result = new RestResult<>(ResultCodeEnum.ENTERPRISE_NAME_NULL.CODE, ResultCodeEnum.ENTERPRISE_NAME_NULL.DESC);
			} else {
				Long enterpriseId = null;
				 enterpriseList = new ArrayList<NsSossEnterprise>();
				key = new StringBuffer(RedisKeysConstants.REDIS_REGISTER_ENTERPRISE_PREFIX).append(enterpriseVo.getRegisterUserPhone());
				Object obj = redisUtil.getObjectValue(key.toString());
				boolean isContinue = true;
				if (!Objects.isNull(obj)) {
					enterpriseList = (List<NsSossEnterprise>) obj;
					for (NsSossEnterprise enterprise : enterpriseList) {
						if (enterpriseVo.getName().equalsIgnoreCase(enterprise.getName())) {
							result = new RestResult<>(ResultCodeEnum.ENTERPRISE_NAME_EXISTS.CODE, ResultCodeEnum.ENTERPRISE_NAME_EXISTS.DESC);
							isContinue = false;
							break;							
						}
					}
				}
				if (!isContinue) {
					return result;
				}
				if (!CommonUtils.checkPwd(enterpriseVo.getPassword()) && enterpriseList.size()==0) {//密码只能是数字，字母
					result = new RestResult<>(ResultCodeEnum.REGISTER_PASSWORD.CODE, ResultCodeEnum.REGISTER_PASSWORD.DESC);
				} else if (!enterpriseVo.getPassword().equals(enterpriseVo.getConfirmPWD()) && enterpriseList.size()==0) {//确认密码与密码不符
					result = new RestResult<>(ResultCodeEnum.REGISTER_CONFIRM_PASSWORD.CODE, ResultCodeEnum.REGISTER_CONFIRM_PASSWORD.DESC);
				} else {
					//移除缓存的企业数据
					redisUtil.delete(key.toString());
					result = new RestResult<>("");
					Long userId=0L;
					RestResult<Long[]> restResult = null;
					try {
						//1 添加企业
						enterpriseId = enterpriseService.registerEnterpriseInfo(enterpriseVo);				
						//2 sytem添加员工
						RestResult<Long> temp = systemRemoteService.addRegisterUser(enterpriseVo.getCreateUserName(), enterpriseVo.getRegisterUserPhone());
						userId = temp.getResultData();
						// 3 添加集团组织，与登录账号
						AppUser user = new AppUser();
						user.setUserId(userId);
						user.setUserName(enterpriseVo.getCreateUserName());
						user.setEnterpriseId(enterpriseId);
						user.setEnterpriseName(enterpriseVo.getName());
						user.setUserAccount(enterpriseVo.getRegisterUserPhone());
						user.setAppId(appId);
						user.setPassword(enterpriseVo.getPassword());
						restResult = systemRemoteService.updateRegisterUser(user);
						//更新企业信息
						enterpriseVo = new EnterpriseVo();
						enterpriseVo.setEnterpriseId(enterpriseId);
						enterpriseVo.setCreateUserId(userId);
						enterpriseVo.setUpdateUserId(userId);
						enterpriseService.edit(enterpriseVo);
						//开通soss菜单权限
						JepfSyncOrgVo jepfSyncOrgVo = new JepfSyncOrgVo();
						jepfSyncOrgVo.setEnterpriseId(enterpriseId);
						jepfSyncOrgVo.setOrganizationId(restResult.getResultData()[1]);
						List<JepfSyncOrgVo> orgList = new ArrayList<JepfSyncOrgVo>();
						orgList.add(jepfSyncOrgVo);
						JepfSyncVo jepfSyncVo = new JepfSyncVo();
						jepfSyncVo.setUserId(userId);
						jepfSyncVo.setOrgList(orgList);
						jepfSyncVo.setMenuName("运营");
						jepfSyncVo.setSyncType(10);
						RestResult<Boolean> tempRes = JepfSyncRemoteService.syncOriginal(jepfSyncVo);
						//注册结果
						result = tempRes.getResultData() ?  new RestResult<>(ResultCodeEnum.SUCCESS.CODE, ResultCodeEnum.SUCCESS.DESC)
								: new RestResult<>(ResultCodeEnum.FAILURE.CODE, ResultCodeEnum.FAILURE.DESC);
					} catch (Exception e) {
						//异常回滚数据
						enterpriseService.delete(enterpriseId);
						if(!Objects.isNull(restResult) && !CollectionUtils.sizeIsEmpty(restResult.getResultData())) {
							systemRemoteService.deleteRegisterUser(userId, restResult.getResultData()[0] ,restResult.getResultData()[1]);
						}
						throw new BizException(ResultCodeEnum.FAILURE.CODE, e.getMessage());
					}
				}
			}			
		}		
		
		return result;
	}
	@ApiOperation(value = "重置密码")
    @RequestMapping(value = "/resetPassword", method = RequestMethod.POST)
	 public RestResult<?> resetPassword(@RequestBody EnterpriseVo enterpriseVo, @RequestHeader(name="appId", required=false) String appId) throws Exception {
		RestResult<?> result= new RestResult<>();
		//进行验证码判断
		StringBuffer key = new StringBuffer(RedisKeysConstants.REDIS_REGISTER_PHONE_CODE_PREFIX);
		key.append(enterpriseVo.getRegisterUserPhone());
		String code = redisUtil.getStringValue(key.toString());
		if (StringUtils.isBlank(code)) { //验证码过期
			result = new RestResult<>(ResultCodeEnum.VERIFICATE_CODE_EXPIRATION.CODE, ResultCodeEnum.VERIFICATE_CODE_EXPIRATION.DESC);
			return result;
		}else if (!code.equals(enterpriseVo.getVerificateCode())) { //验证码错误
			result = new RestResult<>(ResultCodeEnum.VERIFICATE_CODE_ERROR.CODE, ResultCodeEnum.VERIFICATE_CODE_ERROR.DESC);
			return result;
		} else if (!CommonUtils.checkPwd(enterpriseVo.getPassword()) ) {//密码只能是数字，字母
			result = new RestResult<>(ResultCodeEnum.REGISTER_PASSWORD.CODE, ResultCodeEnum.REGISTER_PASSWORD.DESC);
		} else if (!enterpriseVo.getPassword().equals(enterpriseVo.getConfirmPWD()) ) {//确认密码与密码不符
			result = new RestResult<>(ResultCodeEnum.REGISTER_CONFIRM_PASSWORD.CODE, ResultCodeEnum.REGISTER_CONFIRM_PASSWORD.DESC);
		}else {
			redisUtil.delete(key.toString());
			//根据手机号去查询账号信息
			RestResult<List<AppUserVo>> restResult = oauthRemoteService.getUserInfo(enterpriseVo.getRegisterUserPhone(), appId);
			if(ResultCodeEnum.SUCCESS.CODE.equals(restResult.getResultCode())) {
				List<AppUserVo> appUserVoList = restResult.getResultData();
				if(CollectionUtils.isEmpty(appUserVoList)) {//账号不存在
					result = new RestResult<>(ResultCodeEnum.DATA_NOT_EXIST.CODE, ResultCodeEnum.DATA_NOT_EXIST.DESC);
				}
				appUserVoList.forEach(e ->e.setPassword(enterpriseVo.getPassword()));
				//修改密码
				result  = oauthRemoteService.bashEditUserInfo(appUserVoList,appId);
			}
		}
		return result;
		
	}
	@ApiOperation(value = "获取短信验证码")
    @RequestMapping(value = "/msg-code", method = RequestMethod.GET)
	public RestResult<?> getMsgVerificateCode(@RequestParam(name="phone") String phone,@RequestParam(name="type") String type) throws Exception {
		RestResult<?> result = null;
		if(StringUtils.isBlank(phone)) {
			result = RestResult.PARAMS_MISSING;
			return result;
		}
		//根据手机号，到system查询该员工信息，注册的企业信息
		 List<NsSystemUser> users = systemRemoteService.getUserInfo(null, phone);
		if (!CollectionUtils.isEmpty(users)) {
			List<Long> userIds = new ArrayList<Long>();
			for(NsSystemUser user :users) {
				userIds.add(user.getUserId());
			}
			if(!"reset".equalsIgnoreCase(type)) {
				List<NsSossEnterprise>  enterpriseList = enterpriseService.findEnterpriseInfoListByUserIds(userIds);	
				//查询企业名称
				if(!CollectionUtils.isEmpty(enterpriseList) && enterpriseList.size()>=SossConstants.REGISTER_USER_LIMIT) {//一个账号仅支持三个企业
					return new RestResult<>(ResultCodeEnum.DATA_LIMIT.CODE, ResultCodeEnum.DATA_LIMIT.DESC);
				}
			}
			}
		//生成验证码
		String code = CommonUtils.createRandomPWD(4);
		System.out.println("====企业注册验证码===="+phone+"==="+code);
		//存入缓存
		StringBuffer key = new StringBuffer(RedisKeysConstants.REDIS_REGISTER_PHONE_CODE_PREFIX);
		key.append(phone);
		redisUtil.setStringValue(key.toString(), code, 3*60);
		//TODO 发送验证码
		
		result = new RestResult<String>(code);
		result.setResultMsg("验证码已发送，请在三分钟之内正确输入。");
		return result;
	}
	/**
	 * 单企业登录
	 * @param phone
	 * @param password
	 * @return
	 */
/*	@ApiOperation(value = "登录")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
	public RestResult<Map<String,Object>> login(@RequestParam(name="phone") String phone,@RequestParam(name="password") String password,@RequestHeader(name="appId") String appId,@RequestHeader(name="appClientType") String appClientType) {
		RestResult<Map<String,Object>> result = null;
		if(StringUtils.isAnyBlank(phone,password,appId)) {
			result = RestResult.PARAMS_MISSING;
			return result;
		}
		result = oauthRemoteService.loginOauth(phone,password,appId,appClientType);
		return result;
	}*/
	/**
	 * 多企业登录
	 * @param phone
	 * @param password
	 * @return
	 */
/*	@ApiOperation("多企业登录")
	@RequestMapping(value="/login-enterprise", method=RequestMethod.POST)
	public RestResult<Map<String,Object>> loginEnterprise( String enterpriseId, @RequestHeader(name="appId") String appId) {
		RestResult<Map<String,Object>> result = null;
		if(StringUtils.isAnyBlank(enterpriseId)) {
			result = RestResult.PARAMS_MISSING;
			return result;
		}
		result = oauthRemoteService.mutilEnterpriseLogin("test" , "newsee888"  ,Long.parseLong(enterpriseId), "07d8737811434732","pc");
		return result;
	}*/
	
}
