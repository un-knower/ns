package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.system.dao.NsCoreRoleMapper;
import com.newsee.system.dao.NsCoreRolecategoryMapper;
import com.newsee.system.entity.NsCoreRole;
import com.newsee.system.entity.NsCoreRolecategory;
import com.newsee.system.service.INsRoleCategoryService;
import com.newsee.system.vo.NsCoreRoleVo;
import com.newsee.system.vo.NsCoreRolecategoryVo;

@Service
public class NsRoleCategoryServiceImpl implements INsRoleCategoryService {
    
    @Autowired
    NsCoreRolecategoryMapper rolecategoryMapper;
    @Autowired
    NsCoreRoleMapper roleMapper;

    @Override
    public Boolean add(NsCoreRolecategoryVo rolecategoryVo) {
        boolean result = false;
        NsCoreRolecategory roleCategory =  new NsCoreRolecategory();
        BeanUtils.copyProperties(rolecategoryVo, roleCategory);
        roleCategory.setCreateUserId(rolecategoryVo.getHanderId());
        roleCategory.setUpdateUserId(rolecategoryVo.getHanderId());
        rolecategoryMapper.insertSelective(roleCategory);
        result = true;
        return result;
    }

    @Override
    public Boolean edit(NsCoreRolecategoryVo rolecategoryVo) {
        boolean result = false;
        NsCoreRolecategory roleCategory =  new NsCoreRolecategory();
        BeanUtils.copyProperties(rolecategoryVo, roleCategory);
        roleCategory.setUpdateUserId(rolecategoryVo.getHanderId());
        rolecategoryMapper.updateByPrimaryKeySelective(roleCategory);
        result = true;
        return result;
    }

    @Override
    public Boolean deleteSoft(Long rolecategoryid) {
        boolean result = false;
        NsCoreRolecategory rolecategory = rolecategoryMapper.selectByPrimaryKey(rolecategoryid);
        Map<String, Object> map = new HashMap<>();
        map.put("organizationId", rolecategory.getOrganizationId());
        map.put("rolecategoryId",rolecategoryid);
        List<NsCoreRole> roles = roleMapper.selectByOrgIdAndCategoryId(map);
        if (CollectionUtils.isEmpty(roles)) {
            rolecategoryMapper.deleteSoftById(rolecategoryid);
            result = true;
        }else {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该角色类别下有角色不能被删除");
        }
        return result;
    }

    @Override
    public List<NsCoreRolecategoryVo> listByOrganizationId(Long organizationId) {
        List<NsCoreRolecategoryVo> rolecategoryVos = new ArrayList<>();
        List<NsCoreRolecategory> rolecategories = rolecategoryMapper.listByOrganizationId(organizationId);
        rolecategories.forEach(rolecategory ->{
            NsCoreRolecategoryVo rolecategoryVo = new NsCoreRolecategoryVo();
            BeanUtils.copyProperties(rolecategory, rolecategoryVo);
            rolecategoryVo.setDisabled(false);
            rolecategoryVos.add(rolecategoryVo);
        });
        return rolecategoryVos;
    }

    @Override
    public NsCoreRolecategoryVo detail(Long rolecategoryid) {
        NsCoreRolecategory orgcategory = rolecategoryMapper.selectByPrimaryKey(rolecategoryid);
        NsCoreRolecategoryVo orgcategoryVo = new NsCoreRolecategoryVo();
        BeanUtils.copyProperties(orgcategory, orgcategoryVo);
        return orgcategoryVo;
    }

    @Override
    public List<NsCoreRolecategoryVo> listRoleGroupByCategory(Long organizationId) {
        List<NsCoreRolecategoryVo> rolecategoryVos = new ArrayList<>();
        List<NsCoreRolecategory> rolecategories = rolecategoryMapper.listByOrganizationId(organizationId);
        rolecategories.forEach(rolecategory ->{
            NsCoreRolecategoryVo rolecategoryVo = new NsCoreRolecategoryVo();
            BeanUtils.copyProperties(rolecategory, rolecategoryVo);
            Map<String, Object> map = new HashMap<>();
            map.put("organizationId", organizationId);
            map.put("rolecategoryId", rolecategoryVo.getRolecategoryId());
            List<NsCoreRoleVo> roleVos = new ArrayList<>();
            List<NsCoreRole> roles = roleMapper.selectByOrgIdAndCategoryId(map);
            if (!CollectionUtils.isEmpty(roles)) {
                roles.forEach(role ->{
                    NsCoreRoleVo roleVo = new NsCoreRoleVo();
                    BeanUtils.copyProperties(role, roleVo);
                    roleVo.setDisabled(false);
                    roleVo.setCheckSw(false);
                    roleVos.add(roleVo);
                });
                rolecategoryVo.setRoleVos(roleVos);
                rolecategoryVos.add(rolecategoryVo);
            }
        });
        return rolecategoryVos;
    }

    @Override
    public List<NsCoreRolecategoryVo> listRolecategoryByRoleids(List<String> list) {
        List<NsCoreRolecategoryVo> rolecategoryVos = new ArrayList<>();
        List<NsCoreRole> listrole = roleMapper.selectByRoleIds(list);
        //获取角色组
        if (!CollectionUtils.isEmpty(listrole)) {
            listrole.forEach(role ->{
                NsCoreRolecategory rolecategory = rolecategoryMapper.selectByPrimaryKey(role.getRolecategoryId());
                NsCoreRolecategoryVo rolecategoryVo = new NsCoreRolecategoryVo();
                BeanUtils.copyProperties(rolecategory, rolecategoryVo);
                rolecategoryVos.add(rolecategoryVo);
            });
        }
        //去重
        Set<NsCoreRolecategoryVo> set = new LinkedHashSet<NsCoreRolecategoryVo>(); 
        set.addAll(rolecategoryVos);
        List<NsCoreRolecategoryVo> newRolecategoryVos = new ArrayList<>();
        newRolecategoryVos.addAll(set);
        
        //分配角色到各个角色组
        newRolecategoryVos.forEach(rolecategoryVo ->{
            List<NsCoreRoleVo> tempRoleVos = new ArrayList<>();
            listrole.forEach(role -> {
                if (role.getRolecategoryId().equals(rolecategoryVo.getRolecategoryId())) {
                    NsCoreRoleVo roleVo = new NsCoreRoleVo();
                    BeanUtils.copyProperties(role, roleVo);
                    roleVo.setCheckSw(true);
                    tempRoleVos.add(roleVo);
                }
            });
            rolecategoryVo.setRoleVos(tempRoleVos);
        } );
        return newRolecategoryVos;
    }

}
