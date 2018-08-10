package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.entity.NsSystemUser;
import com.newsee.system.dao.NsCoreRoleUserMapper;
import com.newsee.system.dao.NsSystemRoleHouseMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.entity.NsSystemRoleHouse;
import com.newsee.system.service.INsSystemRoleHouseService;

@Service
public class NsSystemRoleHouseServiceImpl implements INsSystemRoleHouseService {
    
    @Autowired
    NsSystemUserMapper userMapper;
    @Autowired
    NsSystemRoleHouseMapper roleHouseMapper;
    @Autowired
    NsCoreRoleUserMapper roleUserMapper;

    @Override
    public List<NsSystemRoleHouse> listRoleHouse(Long userId) {
        List<NsSystemRoleHouse> roleHouses = null;
        NsSystemUser user = userMapper.selectById(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", user.getEnterpriseId());
        map.put("organizationId", user.getOrganizationId());
        map.put("userid", user.getUserId());
        List<NsCoreRoleUser> roleUsers= roleUserMapper.selectByUserId(map);
        List<String> roleids = new ArrayList<>();
        roleUsers.forEach(roleUser -> {
            roleids.add(roleUser.getRoleid());
        });
        if (!CollectionUtils.isEmpty(roleids)) {
            roleHouses = roleHouseMapper.selectByRoleids(roleids);
        }
        return roleHouses;
    }

}
