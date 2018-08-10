package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.Constants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.entity.AppUser;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.dao.NsCoreRoleMapper;
import com.newsee.system.dao.NsCoreRoleUserMapper;
import com.newsee.system.dao.NsSystemGroupMapper;
import com.newsee.system.dao.NsSystemOrganizationMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.entity.NsCoreRole;
import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.entity.NsSystemGroup;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.service.INsSystemUserService;
import com.newsee.system.service.remote.IOauthRemoteService;
import com.newsee.system.vo.NsCoreRoleVo;
import com.newsee.system.vo.NsSystemOrganizationVo;
import com.newsee.system.vo.NsSystemUserVo;

@Service
public class NsSystemUserServiceImpl implements INsSystemUserService {

    @Autowired
    NsSystemUserMapper userMapper;
    @Autowired
    NsCoreRoleUserMapper roleUserMapper;
    @Autowired
    NsSystemOrganizationMapper organizationMapper;
    @Autowired
    NsCoreRoleMapper roleMapper;
    @Autowired
    IOauthRemoteService oauthRemoteService;
    @Autowired
    private NsSystemGroupMapper systemGroupMapper;
    @Autowired
    private RedisUtil redisUtil;
    
    @Override
    public Boolean add(NsSystemUserVo userVo) {
        boolean result = false;
//        if (StringUtils.isBlank(userVo.getUserPassword())) {
//            userVo.setUserPassword(CommonUtils.createRandomPWD(6));
//        }
        //组织信息
        NsSystemOrganization organization = organizationMapper.selectById(userVo.getOrganizationId());
        //用户的基本信息
        NsSystemUser user = new NsSystemUser();
        BeanUtils.copyProperties(userVo, user);
        user.setEnterpriseId(organization.getEnterpriseId());
        user.setGroupId(organization.getGroupId());
        user.setCompanyId(organization.getCompanyId());
        user.setDepartmentId(organization.getDepartmentId());
        user.setCreateUserId(userVo.getHandlerId());
        user.setUpdateUserId(userVo.getHandlerId());
        user.setUserPassword(userVo.getUserPassword());
        user.setOrganizationName(organization.getOrganizationName());
        user.setOrganizationShortName(organization.getOrganizationShortName());
        user.setUserType(userVo.getUserType());
        //校验唯一性
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", user.getEnterpriseId());
        map.put("userAccount", user.getUserAccount());
        NsSystemUser onlyUser = userMapper.checkOnlyUser(map);
        if (onlyUser != null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "账号已存在");
        }
        userMapper.insert(user);
        //同步到登录信息用户表（自动注册）
        AppUser appUser = new AppUser();
        appUser.setEnterpriseId(organization.getEnterpriseId());
        appUser.setCompanyId(organization.getCompanyId());
        appUser.setOrganizationId(organization.getOrganizationId());
        appUser.setAppId(userVo.getAppId());
        appUser.setUserId(user.getUserId());
        appUser.setUserName(user.getUserName());
        appUser.setUserAccount(user.getUserAccount());
        appUser.setPassword(user.getUserPassword());
        appUser.setIsActived(1);
        appUser.setUserType(userVo.getUserType());
        oauthRemoteService.register(appUser);
        //用户的角色
        if (!CollectionUtils.isEmpty(userVo.getRoleids())) {
            List<String> roleIds = userVo.getRoleids();
            List<NsCoreRoleUser> roleUsers = new ArrayList<>();
            for (String roleid : roleIds) {
                NsCoreRoleUser roleUser = new NsCoreRoleUser();
                roleUser.setEnterpriseId(organization.getEnterpriseId());
                roleUser.setOrganizationId(organization.getOrganizationId());
                roleUser.setRoleid(roleid);
                roleUser.setUserid(user.getUserId().toString());
                roleUsers.add(roleUser);
            }
            roleUserMapper.insertBatch(roleUsers);
        }
        result = true;
        return result;
    }

    @Override
    public Boolean edit(NsSystemUserVo userVo, Long login_userId, String userName) {
        boolean result = false;
        //组织信息
        NsSystemOrganization organization = organizationMapper.selectById(userVo.getOrganizationId());
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", userVo.getEnterpriseId());
        map.put("userAccount", userVo.getUserAccount());
        map.put("userId", userVo.getUserId());
        NsSystemUser onlyUser = userMapper.checkOnlyUser(map);
        if (onlyUser != null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "账号已存在");
        }
//        if (StringUtils.isBlank(userVo.getUserPassword())) {
//            userVo.setUserPassword(CommonUtils.createRandomPWD(6));
//        }
        //更新用户的基本信息
        NsSystemUser user = new NsSystemUser();
        BeanUtils.copyProperties(userVo, user);
        user.setUpdateUserId(login_userId);
        user.setUpdateUserName(userName);
        user.setOrganizationName(organization.getOrganizationName());
        user.setOrganizationShortName(organization.getOrganizationShortName());
        userMapper.updateById(user);
        
        //同步修改注册信息
        AppUser appUser = new AppUser();
        if (Constants.JOB_NO.equals(userVo.getUserState())) {
            //离职并停用
            appUser.setIsActived(Integer.parseInt(Constants.ACTIVE_NO));
        }else{
            appUser.setIsActived(Integer.parseInt(Constants.ACTIVE_YES)); 
        }
        appUser.setEnterpriseId(userVo.getEnterpriseId());
        appUser.setCompanyId(userVo.getCompanyId());
        appUser.setOrganizationId(userVo.getOrganizationId());
        appUser.setAppId(userVo.getAppId());
        appUser.setUserId(userVo.getUserId());
        appUser.setUserName(userVo.getUserName());
        appUser.setUserAccount(user.getUserAccount());
        appUser.setPassword(userVo.getUserPassword());
        AppUser resultAppUser = oauthRemoteService.detail(userVo.getUserId());
        if (Objects.isNull(resultAppUser)) {
            oauthRemoteService.register(appUser);
        }
        oauthRemoteService.edit(appUser);
        //更新用户的角色
        //1.删除旧的角色关系
        roleUserMapper.deleteByUserId(userVo.getUserId().toString());
        //2.建立新的角色关系
        List<String> roleids = userVo.getRoleids();
        if (!CollectionUtils.isEmpty(roleids)) {
            List<NsCoreRoleUser> roleUsers = new ArrayList<>();
            for (String roleid : roleids) {
                NsCoreRoleUser roleUser = new NsCoreRoleUser();
                roleUser.setEnterpriseId(userVo.getEnterpriseId());
                roleUser.setOrganizationId(userVo.getOrganizationId());
                roleUser.setRoleid(roleid);
                roleUser.setUserid(user.getUserId().toString());
                roleUsers.add(roleUser);
            }
            roleUserMapper.insertBatch(roleUsers);
            //清除新增产权人redis中的菜单权限
            String menuRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + userVo.getOrganizationId() + "_" + userVo.getUserId();
            redisUtil.deleteByPrefix(menuRediskey);
        }
        

        result = true;
        return result;
    }
    
    @Override
    public Boolean delete(Long userId, Long login_userId) {
        boolean result = false;
        //解除用户角色关系
        roleUserMapper.deleteByUserId(userId.toString());
        //删除用户
        userMapper.deleteSoftById(userId);
        //删除注册信息
        oauthRemoteService.delete(userId);
        result = true;
        return result;
    }

    @Override
    public List<NsSystemUserVo> list(Long organizationId) {
        List<NsSystemUser> users = userMapper.selectByOrganizationId(organizationId);
        List<NsSystemUserVo> userVos = new ArrayList<>();
        for (NsSystemUser user : users) {
            NsSystemUserVo userVo = new NsSystemUserVo();
            BeanUtils.copyProperties(user, userVo);
            userVo.setUserPassword(null);
            userVos.add(userVo);
        }
        return userVos;
    }
    
    @Override
    public List<NsSystemUserVo> listUserSearch(Long enterpriseId, Long organizationId, String userName) {
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        if (!CommonUtils.isObjectEmpty(organizationId)) {
            map.put("organizationId", organizationId);
        }
        map.put("userName", userName);
        List<NsSystemUser> userList = userMapper.listUserSearch(map);
        List<NsSystemUserVo> userVoList =  new ArrayList<>();
        if (!CollectionUtils.isEmpty(userList)) {
            userList.forEach(user -> {
                NsSystemUserVo userVo = new NsSystemUserVo();
                BeanUtils.copyProperties(user, userVo);
                userVoList.add(userVo);
            });
        }
        return userVoList;
    }

    @Override
    public PageInfo<NsSystemUserVo> listPage(SearchVo searchVo) {
      /* //员工组织信息
       NsSystemOrganization org = organizationMapper.selectByOrganizationId(searchVo.getOrganizationId());
       String orgpath = org.getOrganizationPath();*/
       Map<String, Object> map = new HashMap<>();
       map.put("organizationPath", "/"+searchVo.getOrganizationId()+"/");
       List<NsSystemOrganization> organizations =  organizationMapper.selectByPath(map);
       List<Long> organizationIdList = new ArrayList<>();
       organizationIdList.add(searchVo.getOrganizationId());
       for (NsSystemOrganization org  : organizations) {
           organizationIdList.add(org.getOrganizationId());
       }
       searchVo.setOrganizationIdList(organizationIdList);
       
       List<NsSystemUserVo> list = new ArrayList<>();
       PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
       List<NsSystemUser> users = userMapper.listResultBySearch(searchVo);
       PageInfo<NsSystemUser> pageUser = new PageInfo<>(users);
       for (NsSystemUser user : users) {
            NsSystemUserVo userVo = new NsSystemUserVo();
            BeanUtils.copyProperties(user, userVo);
            /* userVo.setOrganizationName(org.getOrganizationName());*/
            list.add(userVo);
       }
       PageInfo<NsSystemUserVo> pageInfo = new PageInfo<>();
       BeanUtils.copyProperties(pageUser, pageInfo);
       pageInfo.setList(list);
      return pageInfo;
    }
    
    @Override
    public NsSystemUserVo detail(Long userId) {
        NsSystemUserVo userVo = new NsSystemUserVo();
        //员工基本信息
        NsSystemUser user = userMapper.selectById(userId);
        BeanUtils.copyProperties(user, userVo);
        //员工组织信息
        NsSystemOrganization org = organizationMapper.selectByOrganizationId(user.getOrganizationId());
        NsSystemOrganizationVo orgVo = new NsSystemOrganizationVo();
        BeanUtils.copyProperties(org, orgVo);
        userVo.setOrganizationVo(orgVo);
        userVo.setOrganizationName(org.getOrganizationName());
        userVo.setOrganizationShortName(org.getOrganizationShortName());
        //员工角色
        List<String> roleIds = new ArrayList<>();
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", user.getEnterpriseId());
        paramMap.put("organizationId", user.getOrganizationId());
        paramMap.put("userid", userId.toString());
        List<NsCoreRoleUser> roleUsers = roleUserMapper.selectByUserId(paramMap);
        for (NsCoreRoleUser roleUser : roleUsers) {
            roleIds.add(roleUser.getRoleid());
        }
        if (!CollectionUtils.isEmpty(roleIds)) {
            userVo.setRoleids(roleIds);
            List<NsCoreRole> roles = roleMapper.selectByRoleIds(roleIds);
            List<NsCoreRoleVo> roleVos = new ArrayList<>();
            for (NsCoreRole role : roles) {
                NsCoreRoleVo roleVo = new NsCoreRoleVo();
                BeanUtils.copyProperties(role, roleVo);
                roleVo.setDisabled(false);
                roleVo.setCheckSw(true);
                roleVos.add(roleVo);
            }
            userVo.setRoleVos(roleVos);
        }
        return userVo;
    }

    @Override
    public List<NsSystemUser> detailUser(Long userId, String userPhone) {
    	List<NsSystemUser> users = new ArrayList<NsSystemUser>();
    	if (!Objects.isNull(userId)) {
    		NsSystemUser user = userMapper.selectById(userId);
    		users.add(user);
    	} else if (!StringUtils.isBlank(userPhone)) {
    		Map<String, Object> map = new HashMap<>();
    		map.put("userPhone", userPhone);
    		map.put("isDelete", 0); //未删除
    		map.put("userState", 1); //在职
    		 users = userMapper.selectUserInfo(map);
    	}
    	 
        return users;
    }

	@Override
	public Long addRegisterUser(NsSystemUserVo userVo) {
		NsSystemUser user = new NsSystemUser();
		BeanUtils.copyProperties(userVo, user);
		user.setCreateTime(new Date());
		user.setUpdateTime(new Date());
		userMapper.insert(user);
		Long userId = user.getUserId();
		return userId;
	}
    
	@Override
	public Boolean roolbackRegister(Long userId,Long groupId,Long organizationId) {
		//delete system_user 中的记录
		userMapper.deleteById(userId);
		oauthRemoteService.delete(userId);
		//delete system_group
		if(Objects.isNull(groupId))return Boolean.TRUE;
		systemGroupMapper.deleteById(groupId);
		// delete system_organization
		if(Objects.isNull(organizationId)) return Boolean.TRUE;
		organizationMapper.deleteById(organizationId);
		return true;
	}
	@Override
	public Long[] editRegisterUser(NsSystemUserVo userVo, String enterpriseName) {
		Long[] results = null;
		//修改注册人信息
		NsSystemUser user = new NsSystemUser();
		user.setUserId(userVo.getUserId());
		user.setEnterpriseId(userVo.getEnterpriseId());
		//user.setUserType(String.valueOf(Constants.USER_TYPE_PLAT));
		user.setUpdateTime(new Date());
		int res = userMapper.updateByIdSelective(user);
		//添加组织，集团信息
		//添加集团信息
		NsSystemGroup systemGroup = new NsSystemGroup();
		systemGroup.setEnterpriseId(userVo.getEnterpriseId());
		systemGroup.setGroupName(enterpriseName);
		systemGroup.setCreateUserId(userVo.getUserId());
		systemGroup.setCreateUserName(userVo.getUserName());
		systemGroup.setCreateTime(new Date());
		systemGroup.setUpdateUserId(userVo.getUserId());
		systemGroup.setUpdateUserName(userVo.getUserName());
		systemGroup.setUpdateTime(new Date());
		res = systemGroupMapper.insert(systemGroup);
		if (res > 0) {
			//添加组织信息
			NsSystemOrganization organization = new NsSystemOrganization();
			organization.setEnterpriseId(userVo.getEnterpriseId());
			organization.setGroupId(systemGroup.getGroupId());
			organization.setOrganizationName(enterpriseName);
			organization.setOrganizationType(Constants.ORG_TYPE_GROUP);
			organization.setOrganizationPath(Constants.SEPARATOR_PATH);
			organization.setOrganizationLevel(Constants.LEVEL_HIGH);
			organization.setCreateUserId(userVo.getUserId());
			organization.setCreateUserName(userVo.getUserName());
			organization.setCreateTime(new Date());
			organization.setUpdateUserId(userVo.getUserId());
			organization.setUpdateUserName(userVo.getUserName());
			organization.setUpdateTime(new Date());
			res = organizationMapper.insert(organization);			
			results = new Long[] {systemGroup.getGroupId(), organization.getOrganizationId()};
		}
		
		return results;
	}

	@Override
	public List<NsSystemUser> getUserCountByEnterpriseId(List<NsSystemUser> userList) throws Exception {
		if (CollectionUtils.isEmpty(userList)) {
			throw new NullPointerException("userList is null");
		}
		List<Long> enterpriseIds = userList.stream().map(NsSystemUser :: getEnterpriseId).collect(Collectors.toList());
		Map<String, Object> map = new HashMap<>();
		//获取员工数量
		List<NsSystemUser> list = userMapper.selectUserCountByEnterprise(enterpriseIds);
		//获取企业账号人员
		List<Long> userIds = userList.stream().map(NsSystemUser :: getUserId).collect(Collectors.toList());
		map.put("enterpriseIdList", enterpriseIds);
		map.put("userIdList", userIds);
		List<NsSystemUser> temps = userMapper.selectUserInfo(map);
		if (!CollectionUtils.isEmpty(list)) { //合并数据
			for (NsSystemUser user : list) {
				for (NsSystemUser temp : temps) {
					if (user.getEnterpriseId().equals(temp.getEnterpriseId())) {
						BeanUtils.copyProperties(temp, user, "userCount");
					}
				}
			}
		}
		temps = null;
		
		return list;
	}

    @Override
    public Boolean editUser(NsSystemUserVo userVo, String operateType) {
        boolean result = false; 
        NsSystemUser userDetail = userMapper.selectById(userVo.getUserId());
        userVo.setEnterpriseId(userDetail.getEnterpriseId());
        userVo.setCompanyId(userDetail.getCompanyId());
        userVo.setOrganizationId(userDetail.getOrganizationId());
        
        NsSystemUser user = new NsSystemUser();
        user.setUserId(userVo.getUserId());
        user.setCreateUserId(userVo.getHandlerId());
        user.setUpdateUserId(userVo.getHandlerId());
        
        int num = 0;
        if (Constants.OPERATE_TYPE_LEAVE.equals(operateType)) {
            //离职并停用
            user.setUserState(Constants.JOB_NO);
            user.setIsActived(Constants.ACTIVE_NO);
            num = userMapper.updateByIdSelective(user);
            sycnRegistryUser(userVo,Integer.parseInt(Constants.ACTIVE_NO));
        }else if (Constants.OPERATE_TYPE_STOP.equals(operateType)) {
            //停用
            user.setIsActived(Constants.ACTIVE_NO);
            num = userMapper.updateByIdSelective(user);
            sycnRegistryUser(userVo,Integer.parseInt(Constants.ACTIVE_NO));
        }else if (Constants.OPERATE_TYPE_ENABLE.equals(operateType)) {
            //启用
            user.setIsActived(Constants.ACTIVE_YES);
            num = userMapper.updateByIdSelective(user);
            sycnRegistryUser(userVo,Integer.parseInt(Constants.ACTIVE_YES));
        }
       if (num > 0) {
           result = true;
       }
        
        return result;
    }

    private void sycnRegistryUser(NsSystemUserVo userVo, Integer isActive) {
        //同步修改注册信息
        AppUser appUser = new AppUser();
        appUser.setEnterpriseId(userVo.getEnterpriseId());
        appUser.setCompanyId(userVo.getCompanyId());
        appUser.setOrganizationId(userVo.getOrganizationId());
        appUser.setAppId(userVo.getAppId());
        appUser.setUserId(userVo.getUserId());
        appUser.setIsActived(isActive);
        oauthRemoteService.edit(appUser);
    }

	
	
}
