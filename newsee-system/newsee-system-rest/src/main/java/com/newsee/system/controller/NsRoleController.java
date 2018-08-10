package com.newsee.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
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

import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.service.INsMenuService;
import com.newsee.system.service.INsRoleCategoryService;
import com.newsee.system.service.INsRoleFuncOrgService;
import com.newsee.system.service.INsRoleService;
import com.newsee.system.service.INsRoleUserService;
import com.newsee.system.service.impl.NsFieldServiceImpl;
import com.newsee.system.vo.NsCoreFuncinfoVo;
import com.newsee.system.vo.NsCoreRoleVo;
import com.newsee.system.vo.NsCoreRolecategoryVo;
import com.newsee.system.vo.NsSystemAuthorizer;
import com.newsee.system.vo.NsSystemRoleFunctionOrganizationVo;
import com.newsee.system.vo.NsSystemSuperAdmin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
@RequestMapping("/role")
@Api(tags = {"com.newsee.system.controller.NsRoleController"}, description = "角色操作  REST API，包含角色相关的所有操作方法。")
public class NsRoleController {

    @Autowired
    private INsRoleService roleService;
    @Autowired
    private INsRoleUserService roleUserService;
    @Autowired
    INsRoleCategoryService roleCategoryrService;
    @Autowired
    NsFieldServiceImpl fieldService;
    @Autowired
    private INsMenuService menuService;
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
        return new RestResult<Map<String, Object>>(resultData);
    }
    
    //-------------角色---------------------
    @ApiOperation(value = "新增角色")
    @RequestMapping(value = "/add-role", method = RequestMethod.POST)
    public RestResult<Boolean> addRole(@RequestBody NsCoreRoleVo roleVo){
        BizException.isNull(roleVo.getOrganizationId(), "组织ID为空，请选择某个组织后再新增角色");
        RestResult<Boolean> restResult = null;
        roleVo.setHandlerId(LoginDataHelper.getUserId());
        boolean result = roleService.add(roleVo);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "创建超级管理员")
    @RequestMapping(value = "/create-super-admin", method = RequestMethod.POST)
    public RestResult<Boolean> createSuperAdmin(@RequestBody NsSystemSuperAdmin superAdmin){
        BizException.isNull(superAdmin.getOrganizationId(), "组织ID");
        RestResult<Boolean> restResult = null;
        boolean result = roleService.createSuperAdmin(superAdmin);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "编辑角色")
    @RequestMapping(value = "/edit-role", method = RequestMethod.POST)
    public RestResult<Boolean> editRole(@RequestBody NsCoreRoleVo roleVo){
        RestResult<Boolean> restResult = null;
        boolean result = roleService.edit(roleVo);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "删除角色")
    @RequestMapping(value = "/delete-role", method = RequestMethod.GET)
    public RestResult<Boolean> deleteRole(@RequestParam(value="roleid") String roleid){
        RestResult<Boolean> restResult = null;
        boolean result = roleService.delete(roleid); 
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "角色列表")
    @RequestMapping(value = "/list-role", method = RequestMethod.POST)
    public RestResult<PageInfo<NsCoreRoleVo>> listRole(@RequestBody SearchVo searchVo){
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
        
        RestResult<PageInfo<NsCoreRoleVo>> restResult = null;
        PageInfo<NsCoreRoleVo> pageInfo = roleService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);
        return restResult;
    }
    
    @ApiOperation(value = "角色列表(按角色类别分类)")
    @RequestMapping(value = "/list-role-group-by-Category", method = RequestMethod.GET)
    public RestResult<List<NsCoreRolecategoryVo>> listRoleGroupByCategory(@RequestParam(value="organizationId") Long organizationId){
        RestResult<List<NsCoreRolecategoryVo>> restResult = null;
        List<NsCoreRolecategoryVo> rolecategoryVos = roleCategoryrService.listRoleGroupByCategory(organizationId);
        restResult = new RestResult<>(rolecategoryVos);
        return restResult;
    }
    
    @ApiOperation(value = "角色列表for表单")
    @RequestMapping(value = "/list-rolecategory-for-edit", method = RequestMethod.POST)
    public RestResult<List<NsCoreRolecategoryVo>> listRolecategoryForEdit(@RequestBody List<String> roleids){
        BizException.isNull(roleids, "角色IDs");
        List<NsCoreRolecategoryVo> roleCategoryVos = roleCategoryrService.listRolecategoryByRoleids(roleids);
        return new RestResult<>(roleCategoryVos);
    }
    
    @ApiOperation(value = "角色详情")
    @RequestMapping(value = "/detail-role", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailRole(@RequestParam(value="roleid") String roleid){
        BizException.isNull(roleid, "角色ID");
        RestResult<Map<String, Object>> restResult = null;
//        Long userId = LoginDataHelper.getUserId();
//        Long organizationId = LoginDataHelper.getGroupLevelOrgId();
//        Long enterpriseId = LoginDataHelper.getEnterpriseId();
//        LoginCommonDataVo loginVo = new LoginCommonDataVo();
//        loginVo.setUserId(userId);
//        loginVo.setEnterpriseId(enterpriseId);
//        loginVo.setOrganizationId(organizationId);
//        List<NsCoreMenuVo> menuVoList = menuService.listMenuButton(loginVo);
        //获取详情
        NsCoreRoleVo roleVo = roleService.detail(roleid);
        //获取表单
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        Map<String, Object> resultData = fieldService.listField(commonVo);
        
        if (!Objects.isNull(roleVo)) {
            roleVo = CommonUtils.clearNull(roleVo);
            //详情覆盖modelData
            resultData.put("modelData", roleVo);
            //将json字符串形式的form表单装换成相应的对象
            List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
            resultData.put("fields", formFields);
        }
        restResult = new RestResult<>(resultData);
        return restResult;
    }
    
    @ApiOperation(value = "新增角色授权人")
    @RequestMapping(value = "/add-person-to-role", method = RequestMethod.POST)
    public RestResult<Boolean> addPersonToRole(@RequestBody NsSystemAuthorizer authorizer){
        BizException.isNull(authorizer.getOrganizationId(), "组织ID");
        BizException.isNull(authorizer.getRoleid(), "角色ID");
        RestResult<Boolean> restResult = null;
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        authorizer.setEnterpriseId(enterpriseId);
        boolean result = roleUserService.add(authorizer);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "初始化表单项目(新增授权人)")
    @RequestMapping(value = "/init-form-add-person", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initFormAddPerson(@RequestParam(value="roleid") String roleid){
        BizException.isNull(roleid, "角色ID");
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> modelDataMap = new HashMap<>();
        modelDataMap.put("roleid", roleid);
        modelDataMap.put("organizationId", "");
        List<Long> userIds  = new ArrayList<>();
        List<NsCoreRoleUser> roleUsers = roleUserService.list(roleid);
        if (!CollectionUtils.isEmpty(roleUsers)) {
            for (NsCoreRoleUser nsCoreRoleUser : roleUsers) {
                userIds.add(Long.parseLong(nsCoreRoleUser.getUserid()));
            }
        }
        modelDataMap.put("userIds", userIds);
        
        result.put(FormConstants.FORM_MODEL_DATA, modelDataMap);
        result.put(FormConstants.FORM_INFO, new NsCoreFuncinfoVo());
        result.put(FormConstants.FORM_FIELDS, new ArrayList<>());
        return new RestResult<Map<String, Object>>(result);
    }
    
    //-------------角色类别---------------------
    
    @ApiOperation(value = "保存角色类别")
    @RequestMapping(value = "/save-role-category", method = RequestMethod.POST)
    public RestResult<Boolean> saveRoleCategory(@RequestBody NsCoreRolecategoryVo roleCategoryVo){
        RestResult<Boolean> restResult = null;
        Long rolecategoryId = roleCategoryVo.getRolecategoryId();
        if (Objects.isNull(rolecategoryId) ||rolecategoryId.equals(0L)) {//新增
            roleCategoryVo.setOrganizationId(roleCategoryVo.getOrganizationId());
            BizException.isNull(roleCategoryVo.getOrganizationId(), "组织ID");
            roleCategoryVo.setHanderId(LoginDataHelper.getUserId());
            roleCategoryVo.setEnterpriseId(LoginDataHelper.getEnterpriseId());
            boolean result = roleCategoryrService.add(roleCategoryVo);
            restResult = new RestResult<>(result);
        }else{//编辑
            Long userId = LoginDataHelper.getUserId();
            roleCategoryVo.setHanderId(userId);
            boolean result = roleCategoryrService.edit(roleCategoryVo);
            restResult = new RestResult<>(result);
        }
  
        return restResult;
    }
    
    @ApiOperation(value = "新增角色类别")
    @RequestMapping(value = "/add-role-category", method = RequestMethod.POST)
    public RestResult<Boolean> addRoleCategory(@RequestBody NsCoreRolecategoryVo roleCategoryVo){
        roleCategoryVo.setOrganizationId(roleCategoryVo.getOrganizationId());
        BizException.isNull(roleCategoryVo.getOrganizationId(), "组织ID");
        RestResult<Boolean> restResult = null;
        roleCategoryVo.setHanderId(LoginDataHelper.getUserId());
        roleCategoryVo.setEnterpriseId(LoginDataHelper.getEnterpriseId());
        boolean result = roleCategoryrService.add(roleCategoryVo);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "角色类别详情")
    @RequestMapping(value = "/detail-role-category", method = RequestMethod.GET)
    public RestResult<NsCoreRolecategoryVo> detailRoleCategory(@RequestParam(value="rolecategoryId") Long rolecategoryId){
        BizException.isNull(rolecategoryId, "角色类别ID");
        NsCoreRolecategoryVo result = roleCategoryrService.detail(rolecategoryId);
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "编辑角色类别")
    @RequestMapping(value = "/edit-role-category", method = RequestMethod.POST)
    public RestResult<Boolean> editRoleCategory(@RequestBody NsCoreRolecategoryVo roleCategoryVo){
        BizException.isNull(roleCategoryVo.getRolecategoryId(), "角色类别ID");
        RestResult<Boolean> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        roleCategoryVo.setHanderId(userId);
        boolean result = roleCategoryrService.edit(roleCategoryVo);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "删除角色类别")
    @RequestMapping(value = "/delete-role-category", method = RequestMethod.GET)
    public RestResult<Boolean> deleteRoleCategory(@RequestParam(value="rolecategoryId") Long rolecategoryId){
        RestResult<Boolean> restResult = null;
        boolean result = roleCategoryrService.deleteSoft(rolecategoryId); 
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "角色类别列表")
    @RequestMapping(value = "/list-role-category", method = RequestMethod.GET)
    public RestResult<List<NsCoreRolecategoryVo>> listRoleCategory(@RequestParam(value="organizationId") Long organizationId){
        RestResult<List<NsCoreRolecategoryVo>> restResult = null;
        List<NsCoreRolecategoryVo> rolecategoryVos = roleCategoryrService.listByOrganizationId(organizationId);
        restResult = new RestResult<>(rolecategoryVos);
        return restResult;
    }
    
}
