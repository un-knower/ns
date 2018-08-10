package com.newsee.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.entity.AppUser;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.system.service.INsRoleFuncOrgService;
import com.newsee.system.service.INsSystemUserService;
import com.newsee.system.service.impl.NsFieldServiceImpl;
import com.newsee.system.service.remote.IOauthRemoteService;
import com.newsee.system.vo.NsSystemRoleFunctionOrganizationVo;
import com.newsee.system.vo.NsSystemUserVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@ResponseBody
@RequestMapping("/user")
@Api(tags = {"com.newsee.system.controller.NsUserController"}, description = "员工操作  REST API，包含员工相关的所有操作方法。")
public class NsUserController {
    
    @Autowired
    INsSystemUserService userService;
    @Autowired
    NsFieldServiceImpl fieldService;
    @Autowired
    IOauthRemoteService oauthRemoteService;
    @Autowired
    private INsRoleFuncOrgService roleFuncOrgService;
    
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        Map<String, Object> resultData = fieldService.listField(commonVo);
        //将json字符串形式的form表单装换成相应的对象
        List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
        //modelData数据格式处理
       /* if(!Objects.isNull(resultData.get("modelData"))){
            Map<String, Object> userVomodelMap = (Map<String, Object>)resultData.get("modelData");
            if (Objects.isNull(userVomodelMap.get("roleids")) || StringUtils.isBlank((String)userVomodelMap.get("roleids"))) {
                userVomodelMap.put("roleids", new ArrayList<>());
            }
        }*/
        resultData.put(FormConstants.FORM_FIELDS, formFields);
        //redisUtil.setObjectValue(RedisKeysConstants.REDIS_FUNCTION_FIELDS_PREFIX + "_" + enterpriseId.toString() + "_" + organizationId.toString() + "_" + funcId + "_" + interpreter + "_" + formOperateType, formFields);
        if(!Objects.isNull(resultData.get(FormConstants.FORM_MODEL_DATA))){
            Map<String, Object> userVmodelMap = (Map<String, Object>)resultData.get(FormConstants.FORM_MODEL_DATA);
            if (Objects.isNull(userVmodelMap.get("roleids")) || StringUtils.isBlank((String)userVmodelMap.get("roleids"))) {
                userVmodelMap.put("roleids", new ArrayList<>());
            }
        }
        return new RestResult<Map<String, Object>>(resultData);
    }
    
    @ApiOperation(value = "新增员工")
    @RequestMapping(value = "/add-user", method = RequestMethod.POST)
    public RestResult<Boolean> addUser(@RequestBody NsSystemUserVo userVo){
        BizException.isNull(userVo.getOrganizationId(), "组织ID为空，请选择某个组织后再新增员工");
        RestResult<Boolean> restResult = null;
        Long login_userId = LoginDataHelper.getUserId();
        //获取当前登录人的用户类型，若果登录人是内部运营账号，则新建内部运营公司的员工，其他同理
        Integer userType = LoginDataHelper.getAppUser().getUserType();
        userVo.setUserType(userType);
        userVo.setHandlerId(login_userId);
        userVo.setAppId(LoginDataHelper.getAppId());
        boolean result = userService.add(userVo);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "编辑员工")
    @RequestMapping(value = "/edit-user", method = RequestMethod.POST)
    public RestResult<Boolean> editUser(@RequestBody NsSystemUserVo userVo){
        RestResult<Boolean> restResult = null;
        Long login_userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        userVo.setAppId(LoginDataHelper.getAppId());
        boolean result = userService.edit(userVo, login_userId, userName);
        restResult = new RestResult<>(result);
        return restResult;
    }

    
    @ApiOperation(value = "删除员工")
    @RequestMapping(value = "/delete-user", method = RequestMethod.GET)
    public RestResult<Boolean> deleteUser(@RequestParam(value="userId") Long userId){
        RestResult<Boolean> restResult = null;
        Long login_userId = LoginDataHelper.getUserId();
        boolean result = userService.delete(userId, login_userId);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "员工列表")
    @RequestMapping(value = "/list-user", method = RequestMethod.POST)
    public RestResult<PageInfo<NsSystemUserVo>> listUser(@RequestBody SearchVo searchVo){
        BizException.isNull(searchVo, "查询条件");
        /*//设置数据权限
        Long userId = LoginDataHelper.getUserId();
        String funcId = LoginDataHelper.getFuncId();
        List<NsSystemRoleFunctionOrganizationVo> dataPermissions=roleFuncOrgService.list(userId, funcId);
        List<Long> seeOrganizationIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dataPermissions)) {
            for (NsSystemRoleFunctionOrganizationVo rfo : dataPermissions) {
                if (!Objects.isNull(rfo.getSeeOrganizationId())) {
                    seeOrganizationIdList.add(rfo.getSeeOrganizationId());
                }
                if (!Objects.isNull(rfo.getSeeUserId())) {
                    searchVo.setSeeUserIdStr(rfo.getSeeUserId().toString());
                }
            }
        }
        if (!CollectionUtils.isEmpty(seeOrganizationIdList)) {
            searchVo.setSeeOrganizationIdList(seeOrganizationIdList);
        }*/
        RestResult<PageInfo<NsSystemUserVo>> restResult = null;
        PageInfo<NsSystemUserVo> pageInfo = userService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);
        return restResult;
    }
    
    @ApiOperation(value = "某个组织下的所有员工")
    @RequestMapping(value = "/list-user-by-organizationId", method = RequestMethod.GET)
    public RestResult<List<NsSystemUserVo>> listUserByOrganizationId(@RequestParam(value="organizationId") Long organizationId){
        BizException.isNull(organizationId, "组织ID");
        RestResult<List<NsSystemUserVo>> restResult = null;
        List<NsSystemUserVo> userList = userService.list(organizationId);
        restResult = new RestResult<>(userList);
        return restResult;
    }
    
    @ApiOperation(value = "模糊查询员工")
    @RequestMapping(value = "/list-user-search", method = RequestMethod.GET)
    public RestResult<List<NsSystemUserVo>> listUserSearch(@RequestParam(value="organizationId", required=false) Long organizationId,@RequestParam("userName") String userName){
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        List<NsSystemUserVo> userList = userService.listUserSearch(enterpriseId,organizationId,userName);
        return new RestResult<>(userList);
    }
    
    @ApiOperation(value = "员工详情")
    @RequestMapping(value = "/detail-user", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailUser(@RequestParam(value="userId") Long userId){
        RestResult<Map<String, Object>> restResult = null;
        NsSystemUserVo userVo =userService.detail(userId);
        //获取表单
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        Map<String, Object> resultData = fieldService.listField(commonVo);
        
        if (!Objects.isNull(userVo)) {
            userVo = CommonUtils.clearNull(userVo);
            //详情覆盖modelData
            resultData.put("modelData", userVo);
            //将json字符串形式的form表单装换成相应的对象
            List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
            resultData.put("fields", formFields);
        }
        restResult = new RestResult<>(resultData);
        return restResult;
    }
    
    @ApiOperation(value = "员工详情forRemote")
    @RequestMapping(value = "/detail-user-remote", method = RequestMethod.GET)
    public NsSystemUser detailUserRemote(@RequestParam(value="userId") Long userId){
        List<NsSystemUser> users =userService.detailUser(userId, null);
        return users.get(0);
    }
    
    @ApiOperation(value = "注册人员")
    @RequestMapping(value = "/add-registerUser", method = RequestMethod.GET)
    public RestResult<Long> addRegisterUser(@RequestParam(name="userName", required=false) String userName, 
    		@RequestParam(name="phone", required=false) String phone){
        RestResult<Long> restResult = null;
        NsSystemUserVo userVo = new NsSystemUserVo();
        userVo.setUserName(userName);
        userVo.setUserAccount(phone);
        userVo.setUserTelephone(phone);
        Long userId = userService.addRegisterUser(userVo);
        restResult = new RestResult<>(userId);
        return restResult;
    }
    
    @ApiOperation(value = "注册回滚")
    @RequestMapping(value = "/rollback-registerUser", method = RequestMethod.GET)
    public RestResult<Boolean> roolbackRegister(@RequestParam(name="userId") Long userId,@RequestParam(name="groupId") Long groupId
    		,@RequestParam(name="organizationId" ) Long organizationId){
        if(Objects.isNull(userId)){return new RestResult<>(Boolean.TRUE);}
        userService.roolbackRegister(userId,groupId,organizationId);
        return new RestResult<>(Boolean.TRUE);
    }
    
    @ApiOperation(value = "修改人员信息")
    @RequestMapping(value = "/update-registerUser", method = RequestMethod.POST)
    public RestResult<Long[]> updateRegisterUser(@RequestBody AppUser user) {
        RestResult<Long[]> restResult =null;
        NsSystemUserVo userVo = new NsSystemUserVo();
        userVo.setUserId(user.getUserId());
        userVo.setEnterpriseId(user.getEnterpriseId());
        userVo.setUserName(user.getUserName());
        //添加组织人员信息
        Long[] results = userService.editRegisterUser(userVo, user.getEnterpriseName());
        if (results !=  null) { //添加登录信息
        	user.setOrganizationId(results[1]);
        	oauthRemoteService.register(user);
        	restResult =  new RestResult<Long[]>(results);
        }
        return restResult;
    }

    @ApiOperation(value = "根据企业ID，获取企业员工数量")
    @RequestMapping(value = "/user-count", method = RequestMethod.POST)
    public RestResult<List<NsSystemUser>> getEnterpriseUserCount(@RequestParam(name="userListJson", required=false) String userListJson) {
    	@SuppressWarnings("unchecked")
		RestResult<List<NsSystemUser>> restResult = RestResult.FAILURE;
    	if (StringUtils.isBlank(userListJson)) {
    		return restResult;
    	}
    	try {
    		List<NsSystemUser> userList = JSON.parseArray(userListJson, NsSystemUser.class);
    		List<NsSystemUser> list = userService.getUserCountByEnterpriseId(userList);
			restResult = new RestResult<>(list);
		} catch (Exception e) {
			 throw new RuntimeException(e);
		}
    	
    	return restResult;
    }
    
    @ApiOperation(value = "停用/启用/离职 员工")
    @RequestMapping(value = "/modify-user", method = RequestMethod.GET)
    public RestResult<Boolean> modifyUser(@RequestParam(value="userId") Long userId,
            @ApiParam("操作类型：停用=stop、启用=enable、离职=leave")@RequestParam(value="operateType") String operateType){
        BizException.isNull(userId, "员工ID");
        BizException.isNull(operateType, "操作类型");
        RestResult<Boolean> restResult = new RestResult<>();
        NsSystemUserVo userVo = new NsSystemUserVo();
        userVo.setUserId(userId);
        userVo.setAppId(LoginDataHelper.getAppId());
        userVo.setHandlerId(LoginDataHelper.getUserId());
        boolean result =  userService.editUser(userVo, operateType);
        if (result) {
            restResult.setResultCode(RestResult.SUCCESS.getResultCode());
            restResult.setResultMsg("操作成功！");
            restResult.setResultData(result);
        }else{
            restResult.setResultCode(RestResult.FAILURE.getResultCode());
            restResult.setResultMsg("操作失败！");
            restResult.setResultData(result);
        }
        
        return restResult;
    }
    
    @ApiOperation(value = "根据手机或userId获取员工信息")
    @RequestMapping(value = "/get-userInfo", method = RequestMethod.GET)
    public List<NsSystemUser> getUserInfo(@RequestParam(value="userId", required=false) Long userId, @RequestParam(value="userPhone", required=false) String userPhone) {
    	List<NsSystemUser> users = userService.detailUser(userId, userPhone);
    	return users;
    }
    
}
