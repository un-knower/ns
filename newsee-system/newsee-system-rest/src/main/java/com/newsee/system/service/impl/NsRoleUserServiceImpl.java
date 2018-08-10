package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.dao.NsCoreRoleMapper;
import com.newsee.system.dao.NsCoreRoleUserMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.entity.NsCoreRole;
import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.service.INsRoleUserService;
import com.newsee.system.vo.NsSystemAuthorizer;


@Service
public class NsRoleUserServiceImpl implements INsRoleUserService {
    
    @Autowired
    NsCoreRoleUserMapper roleUserMapper;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    NsCoreRoleMapper roleMapper;
    
    @Autowired
    NsSystemUserMapper userMapper;

    @Override
    public Boolean add(NsSystemAuthorizer authorizer) {
        boolean result = false;
        List<NsCoreRoleUser>  oldRoleUsers = roleUserMapper.selectByRoleId(authorizer.getRoleid());
        if (!CollectionUtils.isEmpty(oldRoleUsers)) {
            //将原先的删除
            int num = roleUserMapper.deleteByRoleid(authorizer.getRoleid());
            //重新插入
            if (num > 0) {
                List<String> userids = authorizer.getUserIds();
                if (!CollectionUtils.isEmpty(userids)) {
                    List<NsCoreRoleUser> roleUserstemp = new ArrayList<>();
                    userids.forEach(userid -> {
                        NsCoreRoleUser roleUser = new NsCoreRoleUser();
                        roleUser.setEnterpriseId(authorizer.getEnterpriseId());
                        roleUser.setOrganizationId(authorizer.getOrganizationId());
                        roleUser.setRoleid(authorizer.getRoleid());
                        roleUser.setUserid(userid);
                        roleUserstemp.add(roleUser);
                    });
                    roleUserMapper.insertBatch(roleUserstemp);
                    //清除新增产权人redis中的菜单权限
                    userids.forEach(userid -> {
                        String menuRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + authorizer.getOrganizationId() + "_" + userid;
                        redisUtil.deleteByPrefix(menuRediskey);
                        //清除 redis中的菜单按钮权限
                        String menubuttonRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_BUTTON_PREFIX+userid;
                        redisUtil.deleteByPrefix(menubuttonRediskey);
                    });
                    
                    
                    result = true;
                }
            }
        }else{
            List<String> userids = authorizer.getUserIds();
            if (!CollectionUtils.isEmpty(userids)) {
                List<NsCoreRoleUser> roleUserstemp = new ArrayList<>();
                userids.forEach(userid -> {
                    NsCoreRoleUser roleUser = new NsCoreRoleUser();
                    roleUser.setEnterpriseId(authorizer.getEnterpriseId());
                    roleUser.setOrganizationId(authorizer.getOrganizationId());
                    roleUser.setRoleid(authorizer.getRoleid());
                    roleUser.setUserid(userid);
                    roleUserstemp.add(roleUser);
                });
                roleUserMapper.insertBatch(roleUserstemp);
                //清除新增产权人redis中的菜单权限
                userids.forEach(userid -> {
                    String menuRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + authorizer.getOrganizationId() + "_" + userid;
                    redisUtil.deleteByPrefix(menuRediskey);
                    //清除 redis中的菜单按钮权限
                    String menubuttonRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_BUTTON_PREFIX+userid;
                    redisUtil.deleteByPrefix(menubuttonRediskey);
                });
                
                result = true;
            }
        }
        
        //同步修改该角色表的授权人名字
        NsCoreRole coreRole = new NsCoreRole();
        coreRole.setRoleid(authorizer.getRoleid());
        String authorizerNames = "";
        List<String> authorizerNameList = new ArrayList<>();
        List<NsCoreRoleUser> roleUsers = roleUserMapper.selectByRoleId(authorizer.getRoleid());
        if (!CollectionUtils.isEmpty(roleUsers)) {
            List<Long> userids = new ArrayList<>();
            for (NsCoreRoleUser roleuser : roleUsers) {
                userids.add(Long.parseLong(roleuser.getUserid()));
            }
            List<NsSystemUser> users = userMapper.selectByIds(userids);
            for (NsSystemUser user : users) {
                authorizerNameList.add(user.getUserName());
            }
            authorizerNames=StringUtils.join(authorizerNameList, ",");
        }
        coreRole.setAuthorizerNames(authorizerNames);
        roleMapper.updateByRoleId(coreRole);
        
        return result;
    }

    @Override
    public List<NsCoreRoleUser> list(String roleid) {
        return roleUserMapper.selectByRoleId(roleid);
    }

}
