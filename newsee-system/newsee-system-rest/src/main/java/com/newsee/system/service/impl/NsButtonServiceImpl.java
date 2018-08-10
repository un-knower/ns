package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.system.dao.NsCorePermissionMapper;
import com.newsee.system.dao.NsCoreResourcebuttonMapper;
import com.newsee.system.dao.NsCoreRoleMapper;
import com.newsee.system.dao.NsCoreRolePermMapper;
import com.newsee.system.dao.NsCoreRoleUserMapper;
import com.newsee.system.entity.NsCorePermission;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.entity.NsCoreRolePerm;
import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.service.INsButtonService;

@Service
public class NsButtonServiceImpl implements INsButtonService {
    
    @Autowired
    NsCoreRolePermMapper rolePermMapper;
    @Autowired
    NsCoreRoleUserMapper roleUserMapper;
    @Autowired
    NsCoreRoleMapper roleMapper;
    @Autowired
    NsCorePermissionMapper permissionMapper;
    @Autowired
    NsCoreResourcebuttonMapper buttonMapper;


    @Override
    public List<NsCoreResourcebutton> listButton(LoginCommonDataVo loginVo) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", loginVo.getEnterpriseId());
        paramMap.put("organizationId", loginVo.getOrganizationId());
        paramMap.put("userid", loginVo.getUserId());
        List<NsCoreRoleUser> roleUsers = roleUserMapper.selectByUserId(paramMap);
        List<String> roleIds = new ArrayList<>();
        for(NsCoreRoleUser roleUser : roleUsers){
            roleIds.add(roleUser.getRoleid());
        }
        //获取该用户的所有权限
        //1.所有角色本身的权限
        List<NsCoreRolePerm> rolePerms = rolePermMapper.selectByRoleIds(roleIds);
        List<String> permIds = new ArrayList<>();
        for(NsCoreRolePerm rolePerm : rolePerms){
            permIds.add(rolePerm.getPerid());
        }
        //2.每个角色继承的权限组的权限
        /*List<NsCoreRole> roles = roleMapper.selectByRoleIds(roleIds);
        for (NsCoreRole role : roles) {
            List<NsCoreRolegroupPerm> rolegroupPerms = rolegroupPermMapper.selectByRolegroupId(role.getGroupcode());
            for (NsCoreRolegroupPerm rolegroupPerm : rolegroupPerms) {
                permIds.add(rolegroupPerm.getPerid());
            }
        }*/
        //去重
        permIds.stream().distinct();
        List<NsCorePermission> permissions = permissionMapper.selectByPerids(permIds);
        
        //当前用户、当前功能的所有按钮
        List<NsCoreResourcebutton> resourcebuttons = null;
        List<String> buttonIds = new ArrayList<>();
        for (NsCorePermission permission : permissions) {
            if (permission.getPermtype().equals("button") && permission.getFuncid().equals(loginVo.getFuncId())) {
                buttonIds.add(permission.getPermcode());
            }
        }
        if (!CollectionUtils.isEmpty(buttonIds)) {
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("enterpriseId", loginVo.getEnterpriseId());
            //paramMap2.put("organizationId", loginVo.getOrganizationId());
            paramMap2.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap2.put("buttonIds", buttonIds);
            resourcebuttons = buttonMapper.selectByButtonIds(paramMap2);
        }else{
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该页面没有按钮权限");
        }
        
        return resourcebuttons;
    }

}
