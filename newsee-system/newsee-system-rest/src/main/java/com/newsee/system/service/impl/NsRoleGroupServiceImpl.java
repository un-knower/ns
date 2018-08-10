package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsee.system.dao.NsCorePermissionMapper;
import com.newsee.system.dao.NsCoreRolegroupMapper;
import com.newsee.system.dao.NsCoreRolegroupPermMapper;
import com.newsee.system.entity.NsCorePermission;
import com.newsee.system.entity.NsCoreRolegroup;
import com.newsee.system.entity.NsCoreRolegroupPerm;
import com.newsee.system.service.INsRoleGroupService;
import com.newsee.system.vo.NsCoreFuncinfoVo;
import com.newsee.system.vo.NsCoreRolegroupVo;

@Service
public class NsRoleGroupServiceImpl implements INsRoleGroupService {
    
    @Autowired
    NsCoreRolegroupMapper rolegroupMapper;
    @Autowired
    NsCoreRolegroupPermMapper rolegroupPermMapper;
    @Autowired
    NsCorePermissionMapper permissionMapper;

    @Override
    public Boolean add(NsCoreRolegroupVo roleGroupVo, Long userId) {
        //1.权限组基本信息
        NsCoreRolegroup roleGroup = new NsCoreRolegroup();
        BeanUtils.copyProperties(roleGroupVo, roleGroup);
        rolegroupMapper.insert(roleGroup);
        //2.权限组拥有的功能按钮权限
        List<String> perids = new ArrayList<>();
        List<NsCoreFuncinfoVo>  funcinfoVos = roleGroupVo.getFunctionVos();
        for (NsCoreFuncinfoVo funcinfoVo : funcinfoVos) {
            List<String> resourcebuttons = funcinfoVo.getButtonIds();
            List<NsCorePermission> permissions = new ArrayList<>();
            for (String buttonId : resourcebuttons) {
                NsCorePermission permission = new NsCorePermission(); 
                String perid = UUID.randomUUID().toString();
                perids.add(perid);
                permission.setPerid(perid);
                permission.setFuncid(funcinfoVo.getJeCoreFuncinfoId());
                permission.setPermcode(buttonId);
                permission.setPermtype("button");
                permissions.add(permission);
            }
            permissionMapper.insertBatch(permissions);
        }   
        //TODO 项目权限
        
        //建立权限组与权限关系
        List<NsCoreRolegroupPerm> rolegroupPerms = new ArrayList<>();
        for (String perid : perids) {
            NsCoreRolegroupPerm rolegroupPerm = new NsCoreRolegroupPerm();
            rolegroupPerm.setRolegroupid(roleGroup.getJeCoreRolegroupId());
            rolegroupPerm.setPerid(perid);
            rolegroupPerms.add(rolegroupPerm);
        }
        rolegroupPermMapper.insertBatch(rolegroupPerms);
        return true;
    }

    @Override
    public List<NsCoreRolegroupVo> listRolegroup(Long organizationId) {
        List<NsCoreRolegroupVo> rolegroupVos = new ArrayList<>();
        List<NsCoreRolegroup> rolegroups = rolegroupMapper.selectByOrganizationId(organizationId);
        for (NsCoreRolegroup rolegroup : rolegroups) {
            NsCoreRolegroupVo rolegroupVo = new NsCoreRolegroupVo();
            BeanUtils.copyProperties(rolegroup, rolegroupVo);
            rolegroupVos.add(rolegroupVo);
        }
        return rolegroupVos;
    }

}
