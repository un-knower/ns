package com.newsee.system.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
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

import com.newsee.common.constant.Constants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.service.INsButtonService;
import com.newsee.system.service.INsMenuService;
import com.newsee.system.service.INsRoleFuncOrgService;
import com.newsee.system.service.INsRoleGroupService;
import com.newsee.system.service.INsSystemRoleHouseService;
import com.newsee.system.vo.NsCoreMenuVo;
import com.newsee.system.vo.NsCoreRolegroupVo;
import com.newsee.system.vo.NsSystemRoleFunctionOrganizationVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
@RequestMapping("/permission")
@Api(tags = {"com.newsee.system.controller.NsPermissonController"}, description = "权限操作  REST API，包含权限相关的所有操作方法。")
public class NsPermissonController {

    @Autowired
    private INsMenuService menuService;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private INsRoleGroupService roleGroupService;
    
    @Autowired
    INsSystemRoleHouseService roleHouseService;
    
    @Autowired
    private INsButtonService buttonService;
    
    @Autowired
    private INsRoleFuncOrgService roleFuncOrgService;
    
    
   /* @ApiOperation(value = "全部菜单按钮初始化")
    @RequestMapping(value = "/list-menu-button", method = RequestMethod.GET)
    public RestResult<List<NsCoreMenuVo>> listMenuButton(){
        RestResult<List<NsCoreMenuVo>> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        BizException.isNull(userId, "登录人ID");
        LoginCommonDataVo loginVo = new LoginCommonDataVo();
        loginVo.setUserId(userId);
        loginVo.setEnterpriseId(enterpriseId);
        loginVo.setOrganizationId(organizationId);
        List<NsCoreMenuVo> menuVoList = menuService.listAllMenuButton(loginVo);
        restResult = new RestResult<List<NsCoreMenuVo>>(menuVoList);
        return restResult;
    }*/
    
    @ApiOperation(value = "新增/编辑角色(功能按钮组件初始化)", notes="功能按钮组件初始化")
    @RequestMapping(value = "/list-menu-button", method = RequestMethod.GET)
    public RestResult<List<NsCoreMenuVo>> listMenuButton(){
        //登录用户角色是管理员的时，才能调此方法
        Long start = new Date().getTime();
        
        RestResult<List<NsCoreMenuVo>> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        LoginCommonDataVo loginVo = new LoginCommonDataVo();
        loginVo.setUserId(userId);
        loginVo.setEnterpriseId(enterpriseId);
        loginVo.setOrganizationId(organizationId);
        
        List<NsCoreMenuVo> menuVoList = null;
        //保存该用户的菜单按钮权限到redis
        String menuButtonRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_BUTTON_PREFIX +userId;
        
        if (Objects.isNull(redisUtil.getObjectValue(menuButtonRediskey))) {
            menuVoList = menuService.listMenuButton(loginVo);
            // 将登录信息缓存到redis中
            redisUtil.setObjectValue(menuButtonRediskey, menuVoList);
        } else {
            menuVoList = (List<NsCoreMenuVo>) redisUtil.getObjectValue(menuButtonRediskey);
        }
        restResult = new RestResult<List<NsCoreMenuVo>>(menuVoList);
        
        Long end = new Date().getTime();
        Long time = end-start;
        System.out.println("listMenuButton========" + time);
        return restResult;
    }
    
    
    
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "菜单权限")
    @RequestMapping(value = "/list-menu", method = RequestMethod.POST)
    public RestResult<List<NsCoreMenuVo>> listMenu(){
        RestResult<List<NsCoreMenuVo>> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        //Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Integer userType = LoginDataHelper.getAppUser().getUserType();
        BizException.isNull(userId, "登录人ID");
        //保存menu的redis key，按照用户保存到redis数据库中
        String menuRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + organizationId + "_" + userId;
        List<NsCoreMenuVo> menuVoList = null;  
		if (Objects.isNull(redisUtil.getObjectValue(menuRediskey))) {
			LoginCommonDataVo loginVo = new LoginCommonDataVo();
			loginVo.setUserId(userId);
			loginVo.setEnterpriseId(enterpriseId);
			loginVo.setOrganizationId(organizationId);
			menuVoList = menuService.listMenu(loginVo);
			
			menuVoList = dealSossMenu(menuVoList , userType);//TODO SOSS菜单权限临时解决，后期删除
			// 将登录信息缓存到redis中
			redisUtil.setObjectValue(menuRediskey, menuVoList);
		} else {
			menuVoList = (List<NsCoreMenuVo>) redisUtil.getObjectValue(menuRediskey);
		}
        restResult = new RestResult<List<NsCoreMenuVo>>(menuVoList);
        return restResult;
    }
	/**此方法为SOSS菜单权限临时解决方案，后期前端改造删除*/
	private List<NsCoreMenuVo> dealSossMenu(List<NsCoreMenuVo> menuVoList,Integer userType) {
		//TODO 此段代码为SOSS菜单权限临时解决方案，后期前端改造去除此段代码 0-运维人员
		List<String>menuList = new ArrayList<>();
		menuList.add("customerProductOrderList");
		menuList.add("msgRecordList");
		menuList.add("home");
		if(0 == userType) {
			Iterator<NsCoreMenuVo> iterator = menuVoList.iterator();
			while(iterator.hasNext()) {
			NsCoreMenuVo vo =	iterator.next();
			if("soss".equals(vo.getMenuMenusubname())) {
					List<NsCoreMenuVo> childMenus = vo.getChildMenus();
					for (Iterator<NsCoreMenuVo> iterator2 = childMenus.iterator(); iterator2.hasNext();) {
						NsCoreMenuVo nsCoreMenuVo = (NsCoreMenuVo) iterator2.next();
						if(menuList.contains(nsCoreMenuVo.getMenuMenusubname())) {
							iterator2.remove();
						}
					}
				}
			}
		}else {// 非运维人员
			menuList.clear();
			menuList.add("operationHome");
			menuList.add("productOrderList");
			menuList.add("productList");
			menuList.add("noticeList");
			menuList.add("msgList");
			menuList.add("enterpriseList");
			Iterator<NsCoreMenuVo> iterator = menuVoList.iterator();
			while(iterator.hasNext()) {
			NsCoreMenuVo vo =	iterator.next();
			if("soss".equals(vo.getMenuMenusubname())) {
					List<NsCoreMenuVo> childMenus = vo.getChildMenus();
					for (Iterator<NsCoreMenuVo> iterator2 = childMenus.iterator(); iterator2.hasNext();) {
						NsCoreMenuVo nsCoreMenuVo = (NsCoreMenuVo) iterator2.next();
						if(menuList.contains(nsCoreMenuVo.getMenuMenusubname())) {
							iterator2.remove();
						}
					}
				}
			}
			
		}
		return menuVoList;
		
	}
    @ApiOperation(value = "按钮权限",notes = "根据功能ID获取按钮")
    @RequestMapping(value = "/list-button", method = RequestMethod.POST)
    public RestResult<List<NsCoreResourcebutton>> listButton(){
	    String funcId = LoginDataHelper.getFuncId();
        Long userId = LoginDataHelper.getUserId();
        //Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        BizException.isNull(funcId, "功能ID");
        BizException.isNull(userId, "登录人ID");
        List<NsCoreResourcebutton> buttonList = null;
        LoginCommonDataVo loginVo = new LoginCommonDataVo();
        loginVo.setFuncId(funcId);
        loginVo.setUserId(userId);
        loginVo.setEnterpriseId(enterpriseId);
        loginVo.setOrganizationId(organizationId);
        buttonList = buttonService.listButton(loginVo);
        return  new RestResult<List<NsCoreResourcebutton>>(buttonList);
    }
	
    //-------------权限组---------------------
    @ApiOperation(value = "新增权限组")
    @RequestMapping(value = "/add-role-group", method = RequestMethod.POST)
    public RestResult<Boolean> addRoleGroup(@RequestBody NsCoreRolegroupVo roleGroupVo){
        RestResult<Boolean> restResult = null;
        Long userId = LoginDataHelper.getUserId();
        roleGroupVo.setEnterpriseId(1L);
        boolean result = roleGroupService.add(roleGroupVo,userId);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    @ApiOperation(value = "权限组列表")
    @RequestMapping(value = "/list-role-group", method = RequestMethod.GET)
    public RestResult<List<NsCoreRolegroupVo>> listRoleGroup(@RequestParam(value="organizationId") Long organizationId){
        RestResult<List<NsCoreRolegroupVo>> restResult = null;
        List<NsCoreRolegroupVo> result = roleGroupService.listRolegroup(organizationId);
        restResult = new RestResult<>(result);
        return restResult;
    }
    
    
    @ApiOperation(value = "数据权限")
    @RequestMapping(value = "/list-data-permission", method = RequestMethod.GET)
    public RestResult<List<NsSystemRoleFunctionOrganizationVo>> listDataPermission(
            @RequestParam(value="userId") Long userId,
            @RequestParam(value="funcId") String funcId){
        BizException.isNull(userId, "用户ID");
        BizException.isNull(funcId, "功能ID");
        RestResult<List<NsSystemRoleFunctionOrganizationVo>> restResult = null;
        List<NsSystemRoleFunctionOrganizationVo> roleFuncOrgVos  = roleFuncOrgService.list(userId, funcId);
        restResult = new RestResult<>(roleFuncOrgVos);
        return restResult;
    }
    
    @ApiOperation(value = "数据权限")
    @RequestMapping(value = "/list-data-perm", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> listDataPerm(@RequestBody LoginCommonDataVo commonVo){
        BizException.isNull(commonVo, "请求参数commonVo");
        BizException.isNull(commonVo.getUserId(), "用户ID");
        BizException.isNull(commonVo.getFuncId(), "功能ID");
        
        Map<String, Object> dataPermMap=new HashMap<>();
        List<NsSystemRoleFunctionOrganizationVo> dataPermissions=roleFuncOrgService.list(commonVo.getUserId(), commonVo.getFuncId());
        List<Long> seeOrganizationIdList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(dataPermissions)) {
            for (NsSystemRoleFunctionOrganizationVo rfo : dataPermissions) {
                if (rfo.getSeeScopeType()!=Constants.SEE_OTHER_ORGANIZATION) {
                    if (rfo.getSeeScopeType()==Constants.SEE_USER) {
                        //本人
                        dataPermMap.put("seeUserId", commonVo.getUserId());
                    }else if (rfo.getSeeScopeType()==Constants.SEE_GROUP) {
                        //本集团    
                        seeOrganizationIdList.add(commonVo.getGroupLevelOrgId());
                    }else if (rfo.getSeeScopeType()==Constants.SEE_COMPANY) {
                        //本公司    
                        seeOrganizationIdList.add(commonVo.getCompanyLevelOrgId());
                    }else if (rfo.getSeeScopeType()==Constants.SEE_DEPARTMENT) {
                        //本部门  
                        seeOrganizationIdList.add(commonVo.getOrganizationId());
                    }
                    
                }
                //分管组织
                if (!Objects.isNull(rfo.getSeeOrganizationId())) {
                    seeOrganizationIdList.add(rfo.getSeeOrganizationId());
                }
            }
        }
        if (!CollectionUtils.isEmpty(seeOrganizationIdList)) {
            dataPermMap.put("seeOrganizationIdList", seeOrganizationIdList);
        }
        return new RestResult<>(dataPermMap);
    }
    
    
}
