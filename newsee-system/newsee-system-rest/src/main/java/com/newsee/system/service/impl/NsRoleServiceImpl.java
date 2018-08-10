package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;
import java.util.UUID;
import java.util.stream.Collectors;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.Constants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.dao.NsCoreFuncinfoMapper;
import com.newsee.system.dao.NsCoreMenuMapper;
import com.newsee.system.dao.NsCorePermissionMapper;
import com.newsee.system.dao.NsCoreResourcebuttonMapper;
import com.newsee.system.dao.NsCoreRoleMapper;
import com.newsee.system.dao.NsCoreRolePermMapper;
import com.newsee.system.dao.NsCoreRoleUserMapper;
import com.newsee.system.dao.NsCoreRolecategoryMapper;
import com.newsee.system.dao.NsCoreRolegroupPermMapper;
import com.newsee.system.dao.NsSystemOrganizationMapper;
import com.newsee.system.dao.NsSystemRoleFunctionOrganizationMapper;
import com.newsee.system.dao.NsSystemRoleHouseMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.entity.NsCoreMenu;
import com.newsee.system.entity.NsCorePermission;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.entity.NsCoreRole;
import com.newsee.system.entity.NsCoreRolePerm;
import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.entity.NsCoreRolecategory;
import com.newsee.system.entity.NsCoreRolegroupPerm;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.entity.NsSystemRoleFunctionOrganization;
import com.newsee.system.entity.NsSystemRoleHouse;
import com.newsee.system.service.INsMenuService;
import com.newsee.system.service.INsRoleFuncOrgService;
import com.newsee.system.service.INsRoleService;
import com.newsee.system.service.INsRoleUserService;
import com.newsee.system.utils.SortList;
import com.newsee.system.vo.NsCoreFuncinfoVo;
import com.newsee.system.vo.NsCoreMenuVo;
import com.newsee.system.vo.NsCoreRoleVo;
import com.newsee.system.vo.NsDataSeeScopeVo;
import com.newsee.system.vo.NsSystemAuthorizer;
import com.newsee.system.vo.NsSystemFunctionVo;
import com.newsee.system.vo.NsSystemMenuVo;
import com.newsee.system.vo.NsSystemRoleFunctionOrganizationVo;
import com.newsee.system.vo.NsSystemSuperAdmin;

@Service
public class NsRoleServiceImpl implements INsRoleService {
    
    @Autowired
    NsCoreRoleMapper roleMapper;
    @Autowired
    NsCorePermissionMapper permissionMapper;
    @Autowired
    NsCoreRolePermMapper rolePermMapper;
    @Autowired
    NsCoreRoleUserMapper roleUserMapper;
    @Autowired
    NsCoreFuncinfoMapper funcinfoMapper;
    @Autowired
    NsCoreResourcebuttonMapper buttonMapper;
    @Autowired
    NsCoreMenuMapper menuMapper;
    @Autowired
    NsCoreRolegroupPermMapper rolegroupPermMapper;
    @Autowired
    NsSystemOrganizationMapper organizationMapper;
    @Autowired
    NsSystemUserMapper userMapper;
    @Autowired
    NsSystemRoleHouseMapper roleHouseMapper;
    @Autowired
    NsCoreRolecategoryMapper rolecategoryMapper;
    @Autowired
    INsMenuService menuService;
    @Autowired
    INsRoleUserService roleUserService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    INsRoleFuncOrgService roleFuncOrgServiceImpl;
    @Autowired
    NsSystemRoleFunctionOrganizationMapper roleFuncOrgMapper;
    
    

    @Override
    public Boolean add(NsCoreRoleVo roleVo) {
        boolean result = false;
        //组织信息
        NsSystemOrganization organization = organizationMapper.selectById(roleVo.getOrganizationId());
        //角色组信息
        NsCoreRolecategory  rolecategory = rolecategoryMapper.selectByPrimaryKey(roleVo.getRolecategoryId());
        //角色基本信息
        NsCoreRole role = new NsCoreRole();
        BeanUtils.copyProperties(roleVo, role);
        role.setEnterpriseId(organization.getEnterpriseId());
        role.setCreateuser(roleVo.getHandlerId().toString());
        role.setModifyuser(roleVo.getHandlerId().toString());
        String roleid = UUID.randomUUID().toString();
        role.setRoleid(roleid);
        role.setOrganizationName(organization.getOrganizationName());
        role.setRolecategoryName(rolecategory.getRolecategoryName());
        roleMapper.insert(role);
        //功能按钮权限
        List<NsSystemFunctionVo> allFunctionVo = new ArrayList<>();
        List<NsSystemMenuVo> systemMenuVo = roleVo.getMenuSaveVos();
        if(!CollectionUtils.isEmpty(systemMenuVo)){
            systemMenuVo.forEach(menu ->{
                List<NsSystemFunctionVo> functionVos = menu.getFunctionVos();
                allFunctionVo.addAll(functionVos);
            });
        }
        List<String> perids = new ArrayList<>();
        if (!CollectionUtils.isEmpty(allFunctionVo)) {
            allFunctionVo.forEach(func -> {
                List<String> buttonIds = func.getButtonIds();
                if (!CollectionUtils.isEmpty(buttonIds)) {
                    List<NsCorePermission> permissions = new ArrayList<>();
                    buttonIds.forEach(buttonId -> {
                        NsCorePermission permission = new NsCorePermission();
                        String perid = UUID.randomUUID().toString();
                        perids.add(perid);
                        permission.setEnterpriseId(organization.getEnterpriseId());
                        permission.setOrganizationId(organization.getOrganizationId());
                        permission.setPerid(perid);
                        permission.setFuncid(func.getJeCoreFuncinfoId());
                        permission.setPermcode(buttonId);
                        permission.setPermtype("button");
                        permissions.add(permission);
                    });
                    permissionMapper.insertBatch(permissions);
                }
            });
            //角色与数据权限的关系
            for (NsSystemFunctionVo funcinfoVo : allFunctionVo) {
                NsDataSeeScopeVo dataSeeScope = funcinfoVo.getDataSeeScopeVo();
                if (!Objects.isNull(dataSeeScope)&&!Objects.isNull(dataSeeScope.getSeeScopeType())) {
                    dataSeeScope.setEnterpriseId(organization.getEnterpriseId());
                    dataSeeScope.setRoleid(roleid);
                    dataSeeScope.setFuncId(funcinfoVo.getJeCoreFuncinfoId());
                    roleFuncOrgServiceImpl.add(dataSeeScope);
                }
               
            }
        }
        
        //建立角色与功能按钮权的关系
        if (!CollectionUtils.isEmpty(perids)) {
            List<NsCoreRolePerm> rolePerms = new ArrayList<>();
            for (String perid : perids) {
                NsCoreRolePerm rolePerm = new NsCoreRolePerm();
                rolePerm.setEnterpriseId(organization.getEnterpriseId());
                rolePerm.setOrganizationId(organization.getOrganizationId());
                rolePerm.setRoleid(roleid);
                rolePerm.setEnabled("1");
                rolePerm.setPerid(perid);
                rolePerms.add(rolePerm);
            }
            rolePermMapper.insertBatch(rolePerms);
        }
   
        //建立角色与房产权限的关系
        List<Long> houseIds = roleVo.getHouseIds();
        if (!CollectionUtils.isEmpty(houseIds)) {
            List<NsSystemRoleHouse> rolePerms = new ArrayList<>();
            houseIds.forEach(houseId -> {
                NsSystemRoleHouse roleHouse = new NsSystemRoleHouse();
                roleHouse.setEnterpriseId(organization.getEnterpriseId());
                roleHouse.setOrganizationId(organization.getOrganizationId());
                roleHouse.setRoleid(roleid);
                roleHouse.setHouseId(houseId);
                rolePerms.add(roleHouse);
            });
            roleHouseMapper.insertBatch(rolePerms);
        }
        
        result = true;
        return result;
    }

    @Override
    public Boolean edit(NsCoreRoleVo roleVo) {
        boolean result = false;
        //组织信息
        NsSystemOrganization organization = organizationMapper.selectById(roleVo.getOrganizationId());
        //角色组信息
        NsCoreRolecategory  rolecategory = rolecategoryMapper.selectByPrimaryKey(roleVo.getRolecategoryId());
        //更新角色基本信息
        String roleid = roleVo.getRoleid();
        NsCoreRole role = new NsCoreRole();
        BeanUtils.copyProperties(roleVo, role);
        role.setOrganizationName(organization.getOrganizationName());
        role.setRolecategoryName(rolecategory.getRolecategoryName());
        roleMapper.updateByRoleId(role);
        //更新功能按钮权限
        //1.删除该角色旧权限
        List<String> roleids = new ArrayList<>();
        List<String> oldPermIds = new ArrayList<>();
        roleids.add(roleid);
        List<NsCoreRolePerm> oldRolePerms = rolePermMapper.selectByRoleIds(roleids);
        if (!CollectionUtils.isEmpty(oldRolePerms)) {
            int deleteRolePerm_result = 0;
            int deleteOldPerm_result = 0; 
            for (NsCoreRolePerm oldRolePerm : oldRolePerms) {
                oldPermIds.add(oldRolePerm.getPerid());
            }
            if (!CollectionUtils.isEmpty(oldPermIds)) {
                //删除旧权限
                deleteOldPerm_result = permissionMapper.deleteByPerids(oldPermIds);
            }
            if (deleteOldPerm_result>0) {
                //删除旧权限与角色的关系
                deleteRolePerm_result = rolePermMapper.deleteByRoleId(roleid);
            }
            if (deleteRolePerm_result>0) {
                //2.建立该角色新功能按钮权限
                List<String> newPerids = new ArrayList<>();
                List<NsSystemMenuVo> menuSaveVos = roleVo.getMenuSaveVos();
                if (!CollectionUtils.isEmpty(menuSaveVos)) {
                    for (NsSystemMenuVo menuSaveVo : menuSaveVos) {
                        List<NsSystemFunctionVo> functionVos = menuSaveVo.getFunctionVos();
                        for(NsSystemFunctionVo functionVo : functionVos){
                            List<String> buttonIds = functionVo.getButtonIds();
                            if (!CollectionUtils.isEmpty(buttonIds)) {
                                List<NsCorePermission> permissions = new ArrayList<>();
                                for (String buttonId : buttonIds) {
                                    NsCorePermission permission = new NsCorePermission(); 
                                    String perid = UUID.randomUUID().toString();
                                    newPerids.add(perid);
                                    permission.setEnterpriseId(roleVo.getEnterpriseId());
                                    permission.setOrganizationId(roleVo.getOrganizationId());
                                    permission.setPerid(perid);
                                    permission.setFuncid(functionVo.getJeCoreFuncinfoId());
                                    permission.setPermcode(buttonId);
                                    permission.setPermtype("button");
                                    permissions.add(permission);
                                }
                                permissionMapper.insertBatch(permissions);
                            }
                            
                            //角色与数据权限的关系
                            NsDataSeeScopeVo dataSeeScope = functionVo.getDataSeeScopeVo();              
                            if (!Objects.isNull(dataSeeScope)&&!Objects.isNull(dataSeeScope.getSeeScopeType())) {
                                dataSeeScope.setEnterpriseId(organization.getEnterpriseId());
                                dataSeeScope.setRoleid(roleid);
                                dataSeeScope.setFuncId(functionVo.getJeCoreFuncinfoId());
                                roleFuncOrgServiceImpl.edit(dataSeeScope);
                            }
                        }
                        
                    } 
                    
                }
                //建立角色与新功能按钮权的关系
                if (!CollectionUtils.isEmpty(newPerids)) {
                    List<NsCoreRolePerm> rolePerms = new ArrayList<>();
                    for (String perid : newPerids) {
                        NsCoreRolePerm rolePerm = new NsCoreRolePerm();
                        rolePerm.setEnterpriseId(roleVo.getEnterpriseId());
                        rolePerm.setOrganizationId(roleVo.getOrganizationId());
                        rolePerm.setRoleid(roleid);
                        rolePerm.setEnabled("1");
                        rolePerm.setPerid(perid);
                        rolePerms.add(rolePerm);
                    }
                    rolePermMapper.insertBatch(rolePerms);
                }
            }
        }else{
            //2.建立该角色新功能按钮权限
            List<String> newPerids = new ArrayList<>();
            List<NsSystemMenuVo> menuSaveVos = roleVo.getMenuSaveVos();
            if (!CollectionUtils.isEmpty(menuSaveVos)) {
                for (NsSystemMenuVo menuSaveVo : menuSaveVos) {
                    List<NsSystemFunctionVo> functionVos = menuSaveVo.getFunctionVos();
                    for(NsSystemFunctionVo functionVo : functionVos){
                        List<String> buttonIds = functionVo.getButtonIds();
                        if (!CollectionUtils.isEmpty(buttonIds)) {
                            List<NsCorePermission> permissions = new ArrayList<>();
                            for (String buttonId : buttonIds) {
                                NsCorePermission permission = new NsCorePermission(); 
                                String perid = UUID.randomUUID().toString();
                                newPerids.add(perid);
                                permission.setEnterpriseId(roleVo.getEnterpriseId());
                                permission.setOrganizationId(roleVo.getOrganizationId());
                                permission.setPerid(perid);
                                permission.setFuncid(functionVo.getJeCoreFuncinfoId());
                                permission.setPermcode(buttonId);
                                permission.setPermtype("button");
                                permissions.add(permission);
                            }
                            permissionMapper.insertBatch(permissions);
                        }
                    }
                } 
                
            }
            //建立角色与新功能按钮权的关系
            if (!CollectionUtils.isEmpty(newPerids)) {
                List<NsCoreRolePerm> rolePerms = new ArrayList<>();
                for (String perid : newPerids) {
                    NsCoreRolePerm rolePerm = new NsCoreRolePerm();
                    rolePerm.setEnterpriseId(roleVo.getEnterpriseId());
                    rolePerm.setOrganizationId(roleVo.getOrganizationId());
                    rolePerm.setRoleid(roleid);
                    rolePerm.setEnabled("1");
                    rolePerm.setPerid(perid);
                    rolePerms.add(rolePerm);
                }
                rolePermMapper.insertBatch(rolePerms);
            }
        }
        //删除缓存里对应的授权人的权限
        //获取该角色的产权人
        List<NsCoreRoleUser> roleUsers = roleUserMapper.selectByRoleId(roleid);
        if (!CollectionUtils.isEmpty(roleUsers)) {
            List<Long> userids = new ArrayList<>();
            for (NsCoreRoleUser roleuser : roleUsers) {
                userids.add(Long.parseLong(roleuser.getUserid()));
            }
            List<NsSystemUser> users = userMapper.selectByIds(userids);
            //清除新增产权人redis中的菜单权限
            userids.forEach(userid -> {
                String menuRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + roleVo.getOrganizationId() + "_" + userid;
                redisUtil.deleteByPrefix(menuRediskey);
                //清除redis中的菜单按钮权限
                String menubuttonRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_BUTTON_PREFIX+userid;
                redisUtil.deleteByPrefix(menubuttonRediskey);
            });
        }
        
   
        //更新房产权限
        if (!CollectionUtils.isEmpty(roleVo.getHouseIds())) {
            //1.删除旧关系
            int rhnum = roleHouseMapper.deleteByRoleid(roleid);
            if (rhnum > 0) {
                //2.建立新关系
                List<Long> houseIds = roleVo.getHouseIds();
                if (!CollectionUtils.isEmpty(houseIds)) {
                    List<NsSystemRoleHouse> roleHouses = new ArrayList<>();
                    houseIds.forEach(houseId -> {
                        NsSystemRoleHouse roleHouse = new NsSystemRoleHouse();
                        roleHouse.setEnterpriseId(roleVo.getEnterpriseId());
                        roleHouse.setOrganizationId(roleVo.getOrganizationId());
                        roleHouse.setRoleid(roleid);
                        roleHouse.setHouseId(houseId);
                        roleHouses.add(roleHouse);
                    });
                    roleHouseMapper.insertBatch(roleHouses);
                }
            }
        }
        result = true;
        return result;
    }

    @Override
    public Boolean delete(String roleid) {
        //该角色有用户使用不可被删除
        boolean result = false;
        List<NsCoreRoleUser> roleUsers = roleUserMapper.selectByRoleId(roleid);
        if (CollectionUtils.isEmpty(roleUsers)) {
            //1.删除该角色的功能权限
            List<String> roleids = new ArrayList<>();
            List<String> permIds = new ArrayList<>();
            roleids.add(roleid);
            List<NsCoreRolePerm> oldRolePerms = rolePermMapper.selectByRoleIds(roleids);
            if (!CollectionUtils.isEmpty(oldRolePerms)) {
                for (NsCoreRolePerm oldRolePerm : oldRolePerms) {
                    permIds.add(oldRolePerm.getPerid());
                }
                //删除角色的权限
                permissionMapper.deleteByPerids(permIds);
                //删除角色权限的关系
                rolePermMapper.deleteByRoleId(roleid);
                //删除角色
                roleMapper.deleteByRoleId(roleid);
            }else{
              //删除角色
              roleMapper.deleteByRoleId(roleid);
            }
          //2.删除该角色的项目权限
          roleHouseMapper.deleteByRoleid(roleid);
          Map<String, Object> map = new HashMap<>();
          map.put("roleid", roleid);
          roleFuncOrgMapper.deleteByRoleId(map);
          result = true;   
        }else {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该角色有用户使用不能被删除");
        }
        return result;
    }

    @Override
    public List<NsCoreRoleVo> list(Long organizationId) {
        List<NsCoreRoleVo> roleVos = new ArrayList<>();
        List<NsCoreRole> roles = roleMapper.selectByOrganizationId(organizationId);
        for (NsCoreRole role : roles) {
            NsCoreRoleVo roleVo = new NsCoreRoleVo();
            BeanUtils.copyProperties(role, roleVo);
            roleVos.add(roleVo);
        }
        return roleVos;
    }
    
    @Override
    public PageInfo<NsCoreRoleVo> listPage(SearchVo searchVo) {
       List<NsCoreRole> roles = new ArrayList<>();
       PageInfo<NsCoreRole> pageRole = null;
       if (searchVo.getOrderFieldName().equals("organizationName")||
           searchVo.getOrderFieldName().equals("rolecategoryName")||
           searchVo.getOrderFieldName().equals("authorizerNames")) {
           SearchVo newSearchVo = new SearchVo();
           newSearchVo.setOrganizationId(searchVo.getOrganizationId());
           newSearchVo.setPageNum(searchVo.getPageNum());
           newSearchVo.setPageSize(searchVo.getPageSize());
           newSearchVo.setOrderBy(searchVo.getOrderBy());
           if (searchVo.getOrderFieldName().equals("organizationName")) {
               newSearchVo.setOrderFieldUnderLineName("organization_id");
           }else if (searchVo.getOrderFieldName().equals("rolecategoryName")) {
               newSearchVo.setOrderFieldUnderLineName("rolecategory_id");
           }else if (searchVo.getOrderFieldName().equals("authorizerNames")) {
               newSearchVo.setOrderFieldUnderLineName("");
           }
           PageHelper.startPage(newSearchVo.getPageNum(), newSearchVo.getPageSize());
           roles = roleMapper.listResultBySearch(newSearchVo);
           pageRole = new PageInfo<>(roles);
       }else{
           PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
           roles = roleMapper.listResultBySearch(searchVo);
           pageRole = new PageInfo<>(roles);
       }
      
       List<NsCoreRoleVo> list = new ArrayList<>();
       for (NsCoreRole role : roles) {
            NsCoreRoleVo roleVo = new NsCoreRoleVo();
            BeanUtils.copyProperties(role, roleVo);
            //所属组织
            NsSystemOrganization currentOrg = organizationMapper.selectById(roleVo.getOrganizationId());
            if(!Objects.isNull(currentOrg)){
                roleVo.setOrganizationName(currentOrg.getOrganizationName());
            }
            //所属角色组
            if (!Objects.isNull(roleVo.getRolecategoryId())) {
                NsCoreRolecategory rolecategory =rolecategoryMapper.selectByPrimaryKey(roleVo.getRolecategoryId());
                if (!Objects.isNull(rolecategory)) {
                    roleVo.setRolecategoryName(rolecategory.getRolecategoryName());
                }
            }
            //获取授权人名称
            String authorizerNames = "";
            List<String> authorizerNameList = new ArrayList<>();
            List<NsCoreRoleUser> roleUsers = roleUserMapper.selectByRoleId(roleVo.getRoleid());
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
            roleVo.setAuthorizerNames(authorizerNames);
            list.add(roleVo);
       }
      PageInfo<NsCoreRoleVo> pageInfo = new PageInfo<>();
      BeanUtils.copyProperties(pageRole, pageInfo);
      pageInfo.setList(list);
      return pageInfo;
    }

    private List<NsSystemOrganization> getAllParentOrgAndSelf(Long currentOrgID) {
        NsSystemOrganization currentOrg = organizationMapper.selectById(currentOrgID);
        List<NsSystemOrganization> parentOrgs = null;
        String path = currentOrg.getOrganizationPath();
        if (!StringUtils.isBlank(path)) {
            String[] orgIdArray = path.split("/");
            List<Long> orgIdList = new ArrayList<>();
            for (int i = 0; i < orgIdArray.length; i++) {
                String orgid = orgIdArray[i];
                if (!StringUtils.isBlank(orgid)) {
                    orgIdList.add(Long.parseLong(orgid));
                }
            }
            if (!CollectionUtils.isEmpty(orgIdList)) {
                parentOrgs = organizationMapper.selectByIds(orgIdList);
            }
            parentOrgs.add(currentOrg);
        }
        
        return parentOrgs;
    }

    @Override
    public NsCoreRoleVo detail(String roleid) {
        NsCoreRoleVo roleVo = new NsCoreRoleVo();
        //1.基本信息
        NsCoreRole role = roleMapper.selectByRoleId(roleid);
        BeanUtils.copyProperties(role, roleVo);
        //2.项目权限
        List<NsSystemRoleHouse> systemRoleHouses = roleHouseMapper.selectByRoleid(roleid);
        List<Long> houseIds = new ArrayList<>();
        systemRoleHouses.forEach(roleHouse -> {
            houseIds.add(roleHouse.getHouseId());
        });
        roleVo.setHouseIds(houseIds);
        //3.菜单按钮功能权限
       /* List<NsCoreMenuVo> menuVos = getMenuVos(roleVo);
        roleVo.setMenuVos(menuVos);*/
        List<NsSystemMenuVo> menuSaveVos = getMenuSaveVos(roleVo);
//        List<NsSystemMenuVo> resultMenuList = new ArrayList<>();
//        if (!CollectionUtils.isEmpty(menuVoList)) {
//            resultMenuList.addAll(menuSaveVos);
//            //一级菜单
//            List<NsCoreMenuVo> noPermissonCoreMenuList = new ArrayList<>();
//            List<NsCoreMenuVo> permissonCoreMenuList = new ArrayList<>();
//            List<String> tempPermissonMenu = new ArrayList<>();
//            for (NsSystemMenuVo nsSystemMenuVo : menuSaveVos) {
//                tempPermissonMenu.add(nsSystemMenuVo.getJeCoreMenuId());
//            }
//            noPermissonCoreMenuList = menuVoList.stream().filter(
//                    menuVo->!tempPermissonMenu.contains(menuVo.getJeCoreMenuId())).collect(Collectors.toList());
//            permissonCoreMenuList = menuVoList.stream().filter(
//                    menuVo->tempPermissonMenu.contains(menuVo.getJeCoreMenuId())).collect(Collectors.toList());
//            if (!CollectionUtils.isEmpty(noPermissonCoreMenuList)) {
//                //没有权限的一级菜单
//                List<NsSystemFunctionVo> functionVos = new ArrayList<>();
//                for (NsCoreMenuVo menuVo : noPermissonCoreMenuList) {
//                    NsSystemMenuVo tempMenuVo = new NsSystemMenuVo();
//                    tempMenuVo.setJeCoreMenuId(menuVo.getJeCoreMenuId());
//                    tempMenuVo.setSyOrderindex(menuVo.getSyOrderindex());
//                    for (NsCoreMenuVo childMenuVo : menuVo.getChildMenus()) {
//                        NsSystemFunctionVo nsSystemFunctionVo = new NsSystemFunctionVo();
//                        nsSystemFunctionVo.setJeCoreFuncinfoId(childMenuVo.getFuncId());
//                        nsSystemFunctionVo.setSyOrderindex(childMenuVo.getSyOrderindex());
//                        nsSystemFunctionVo.setFuncinfoFunccode(childMenuVo.getMenuMenusubname());
//                        nsSystemFunctionVo.setButtonIds(new ArrayList<>());
//                        functionVos.add(nsSystemFunctionVo);
//                    }
//                    tempMenuVo.setFunctionVos(functionVos);
//                    resultMenuList.add(tempMenuVo);
//                }
//            }
//            if (!CollectionUtils.isEmpty(permissonCoreMenuList)) {
//                //有权限的一级菜单
//                Map<String, List<NsCoreMenuVo>> coreMenuMap = permissonCoreMenuList.stream().collect(
//                        Collectors.groupingBy(NsCoreMenuVo::getJeCoreMenuId));
//                //二级菜单
//                for (NsSystemMenuVo nsSystemMenuVo : menuSaveVos) {
//                    List<NsCoreMenuVo> noPermissonCoreFuncList = new ArrayList<>();
//                    List<String> tempPermisson = new ArrayList<>();
//                    for (NsSystemFunctionVo nsSystemFunctionVo : nsSystemMenuVo.getFunctionVos()) {
//                        tempPermisson.add(nsSystemFunctionVo.getJeCoreFuncinfoId());
//                    }
//                    if (coreMenuMap.get(nsSystemMenuVo.getJeCoreMenuId()) != null) {
//                        noPermissonCoreFuncList = coreMenuMap.get(nsSystemMenuVo.getJeCoreMenuId()).get(0).getChildMenus().stream().filter(
//                                menuVo->!tempPermisson.contains(menuVo.getFuncId())).collect(Collectors.toList());
//                    }
//                    List<NsSystemFunctionVo> tempFuncList = new ArrayList<>();
//                    if (!CollectionUtils.isEmpty(noPermissonCoreFuncList)) {
//                        //没有权限的二级菜单
//                        for (NsCoreMenuVo nsCoreMenuVo : noPermissonCoreFuncList) {
//                            NsSystemFunctionVo tempFunctionVo = new NsSystemFunctionVo();
//                            tempFunctionVo.setJeCoreFuncinfoId(nsCoreMenuVo.getFuncId());
//                            tempFunctionVo.setSyOrderindex(nsCoreMenuVo.getSyOrderindex());
//                            tempFunctionVo.setFuncinfoFunccode(nsCoreMenuVo.getMenuMenusubname());
//                            tempFunctionVo.setButtonIds(new ArrayList<>());
//                            tempFuncList.add(tempFunctionVo);
//                        }
//                    }
//                    nsSystemMenuVo.getFunctionVos().addAll(tempFuncList);
//                    //调用排序通用类,按syOrderindex排序
//                    SortList<NsSystemFunctionVo> menuSortList = new SortList<NsSystemFunctionVo>();
//                    menuSortList.Sort(nsSystemMenuVo.getFunctionVos(), "getSyOrderindex", "ASC");
//                }
//            }
//            //调用排序通用类,按syOrderindex排序
//            SortList<NsSystemMenuVo> menuSortList = new SortList<NsSystemMenuVo>();
//            menuSortList.Sort(resultMenuList, "getSyOrderindex", "ASC");
//        }
        roleVo.setMenuSaveVos(menuSaveVos);
        return roleVo;
    }
    
    private List<NsSystemMenuVo> getMenuSaveVos(NsCoreRoleVo roleVo){
        //1获取该角色的所有权限
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleVo.getRoleid());
        List<NsCoreRolePerm> rolePerms = rolePermMapper.selectByRoleIds(roleIds);
        List<String> permIds = new ArrayList<>();
        for(NsCoreRolePerm rolePerm : rolePerms){
            permIds.add(rolePerm.getPerid());
        }
        
        List<NsCorePermission> permissions = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permIds)) {
            permissions = permissionMapper.selectByPerids(permIds);
        }
        
        //2获取该角色的所有按钮
        List<String> allButtonIds = new ArrayList<>();
        if (!CollectionUtils.isEmpty(permissions)) {
            for (NsCorePermission permission : permissions) {
                if (permission.getPermtype().equals("button")) {
                    allButtonIds.add(permission.getPermcode());
                }
            }
        }
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("enterpriseId", roleVo.getEnterpriseId());
        //paramMap2.put("organizationId", roleVo.getOrganizationId());
        paramMap2.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
        paramMap2.put("buttonIds", allButtonIds);
        List<NsCoreResourcebutton>  allResourcebuttons = new ArrayList<>();
        if (!CollectionUtils.isEmpty(allButtonIds)) {
            allResourcebuttons = buttonMapper.selectByButtonIds(paramMap2);
        }
        
        //3该角色的所有数据的可见范围
        NsSystemRoleFunctionOrganizationVo rfo = new NsSystemRoleFunctionOrganizationVo();
        rfo.setEnterpriseId(roleVo.getEnterpriseId());
        rfo.setRoleid(roleVo.getRoleid());
        List<NsSystemRoleFunctionOrganization> allDataPermission = roleFuncOrgMapper.selectBySelective(rfo);
        
        //-----------------其余没被选中的功能按钮也要返回空的modelData
        //当前用户所在组织的所有功能
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", roleVo.getEnterpriseId());
        //paramMap.put("organizationId", roleVo.getOrganizationId());
        paramMap.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
        List<NsCoreFuncinfo>  orgFuncinfos = funcinfoMapper.selectByOrgId(paramMap);
      
        //当前用户所在组织的所有菜单
        List<NsCoreMenu>  orgAllMenus = new ArrayList<>();
        for(NsCoreFuncinfo function : orgFuncinfos){
            //获取功能所挂的菜单
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("enterpriseId", function.getEnterpriseId());
            //paramMap1.put("organizationId", function.getOrganizationId());
            paramMap1.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap1.put("functionCode", function.getFuncinfoFunccode());
            NsCoreMenu funcMenu = menuMapper.selectByNodeInfo(paramMap1);
            //树形路径
            String treePath = funcMenu.getSyPath();
            String[] menuIdArray = treePath.split("/");
            List<String> menuIds = Arrays.asList(menuIdArray);
            Map<String, Object> paramMap4 = new HashMap<>();
            paramMap4.put("enterpriseId", function.getEnterpriseId());
           // paramMap4.put("organizationId", function.getOrganizationId());
            paramMap4.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap4.put("menuIds", menuIds);
            List<NsCoreMenu> menus = menuMapper.selectByMenuIds(paramMap4);
//            List<NsCoreMenu> trueMenus = new ArrayList<>();
//            for (NsCoreMenu nsCoreMenu : menus) {
//                if (nsCoreMenu.getOrganizationId().equals(LoginDataHelper.getGroupLevelOrgId()) 
//                        && nsCoreMenu.getEnterpriseId().equals(roleVo.getEnterpriseId())) {
//                    trueMenus.add(nsCoreMenu);
//                }
//            }
            orgAllMenus.addAll(menus);
        }
        //调用排序通用类,按syOrderindex排序
        SortList<NsCoreMenu> menuSortList = new SortList<NsCoreMenu>();
        menuSortList.Sort(orgAllMenus, "getSyOrderindex", "ASC");
        //设置组织所有功能的按钮
        List<NsSystemFunctionVo> orgAllSysFuncList = new ArrayList<>();
        for (NsCoreFuncinfo funcinfo : orgFuncinfos) {
            NsSystemFunctionVo systemFunctionVo = new NsSystemFunctionVo();
            systemFunctionVo.setJeCoreFuncinfoId(funcinfo.getJeCoreFuncinfoId());
            List<String> buttonIds = new ArrayList<>();
            if (!CollectionUtils.isEmpty(allResourcebuttons)){
                allResourcebuttons.forEach(button -> {
                    if (button.getResourcebuttonFuncinfoId().equals(funcinfo.getJeCoreFuncinfoId())) {
                        buttonIds.add(button.getJeCoreResourcebuttonId());
                    }
                });
            }
            //设置组织所有功能的可见范围
            if (!CollectionUtils.isEmpty(allDataPermission)) {
                NsDataSeeScopeVo dataSeeScopeVo = new NsDataSeeScopeVo();
                List<NsSystemRoleFunctionOrganization> seeOtheOrgList = new ArrayList<>();
                for (NsSystemRoleFunctionOrganization roleFuncOrg : allDataPermission) {
                    if (roleFuncOrg.getSeeScopeType()!=Constants.SEE_OTHER_ORGANIZATION ) {
                        //本人、本部门、本公司、本集团
                        if ( roleFuncOrg.getFuncId().equals(funcinfo.getJeCoreFuncinfoId())) {
                            dataSeeScopeVo.setEnterpriseId(roleFuncOrg.getEnterpriseId());
                            dataSeeScopeVo.setRoleid(roleFuncOrg.getRoleid());
                            dataSeeScopeVo.setFuncId(funcinfo.getJeCoreFuncinfoId());
                            dataSeeScopeVo.setSeeScopeType(roleFuncOrg.getSeeScopeType());
                            systemFunctionVo.setDataSeeScopeVo(dataSeeScopeVo);
                        }
                    }else{
                      //经管组织
                      seeOtheOrgList.add(roleFuncOrg);
                    }
               
                }
                if (!CollectionUtils.isEmpty(seeOtheOrgList)) {
                    Map<String, List<NsSystemRoleFunctionOrganization>> groupBFuncIdMap =
                            seeOtheOrgList.stream().collect(Collectors.groupingBy(NsSystemRoleFunctionOrganization::getFuncId));
                    for (String key : groupBFuncIdMap.keySet()) {
                        if (key.equals(funcinfo.getJeCoreFuncinfoId())) {
                            List<NsSystemRoleFunctionOrganization> rfoList = groupBFuncIdMap.get(key);
                            List<Long> rfoIdList = new ArrayList<>();
                            for (NsSystemRoleFunctionOrganization rfoTemp : rfoList) {
                                rfoIdList.add(rfoTemp.getSeeOrganizationId());
                            }
                            dataSeeScopeVo.setEnterpriseId(roleVo.getEnterpriseId());
                            dataSeeScopeVo.setRoleid(roleVo.getRoleid());
                            dataSeeScopeVo.setFuncId(funcinfo.getJeCoreFuncinfoId());
                            dataSeeScopeVo.setSeeOtherOrgIdList(rfoIdList);
                            systemFunctionVo.setDataSeeScopeVo(dataSeeScopeVo);
                        }
                    }
                }
            }else{
                List<Long> rfoIdList = new ArrayList<>();
                NsDataSeeScopeVo dataSeeScopeVo = new NsDataSeeScopeVo();
                dataSeeScopeVo.setSeeScopeType(Constants.SEE_GROUP);
                dataSeeScopeVo.setSeeOtherOrgIdList(rfoIdList);
                systemFunctionVo.setDataSeeScopeVo(dataSeeScopeVo);
            }
            systemFunctionVo.setButtonIds(buttonIds);
            systemFunctionVo.setFuncinfoFunccode(funcinfo.getFuncinfoFunccode());
            systemFunctionVo.setSyOrderindex(funcinfo.getSyOrderindex());
            orgAllSysFuncList.add(systemFunctionVo);
        }
        
        //设置组织所有菜单的功能
        //一级菜单
        List<NsSystemMenuVo> orgRootMenuVos = new ArrayList<>();
        for (NsCoreMenu menu : orgAllMenus) {
            if (menu.getSyNodetype().equals("GENERAL")) {
                NsSystemMenuVo systemMenuVo = new NsSystemMenuVo();
                systemMenuVo.setJeCoreMenuId(menu.getJeCoreMenuId());
                systemMenuVo.setSyOrderindex(menu.getSyOrderindex());
                orgRootMenuVos.add(systemMenuVo);
            }
        }
        orgRootMenuVos = removeNsSystemMenuVoDupliById(orgRootMenuVos);
        //设置组织每个一集菜单下的功能
        orgRootMenuVos.forEach(rootMenu ->{
            List<NsSystemFunctionVo> systemFunctionVos = new ArrayList<>();
            orgAllMenus.forEach(menu ->{
                if (rootMenu.getJeCoreMenuId().equals(menu.getSyParent())) {
                    orgAllSysFuncList.forEach(sysFunc -> {
                        if (sysFunc.getFuncinfoFunccode().equals(menu.getMenuNodeinfo())) {
                            systemFunctionVos.add(sysFunc);
                        }
                    });
                }
            });
           //调用排序通用类,按syOrderindex排序
            SortList<NsSystemFunctionVo> sortFuncList = new SortList<NsSystemFunctionVo>();
            sortFuncList.Sort(systemFunctionVos, "getSyOrderindex", "ASC");
            rootMenu.setFunctionVos(systemFunctionVos);
        });
        //角色的modelData+组织的modelData
       /* List<NsSystemMenuVo>  allRootMenuVos = new ArrayList<>();
        allRootMenuVos.addAll(orgRootMenuVos);
        allRootMenuVos.addAll(rootMenuVos);*/
        
        //调用排序通用类,按syOrderindex排序
        SortList<NsSystemMenuVo> sortMenuList = new SortList<NsSystemMenuVo>();
        sortMenuList.Sort(orgRootMenuVos, "getSyOrderindex", "ASC");
        System.out.println(JSONObject.toJSON(orgRootMenuVos));
        return orgRootMenuVos;
    }

    private List<NsCoreMenuVo> getMenuVos(NsCoreRoleVo roleVo) {
        List<NsCoreMenuVo> rootMenus= new ArrayList<>();
        List<String> roleIds = new ArrayList<>();
        roleIds.add(roleVo.getRoleid());
        //3.1获取该角色的所有权限
        List<NsCoreRolePerm> rolePerms = rolePermMapper.selectByRoleIds(roleIds);
        List<String> permIds = new ArrayList<>();
        //角色本身的权限
        for(NsCoreRolePerm rolePerm : rolePerms){
            permIds.add(rolePerm.getPerid());
        }
        //继承的权限组的权限
        List<NsCoreRolegroupPerm> rolegroupPerms = rolegroupPermMapper.selectByRolegroupId(roleVo.getGroupcode());
        for (NsCoreRolegroupPerm rolegroupPerm : rolegroupPerms) {
            permIds.add(rolegroupPerm.getPerid());
        }
        //去重
        permIds.stream().distinct();
        if (!CollectionUtils.isEmpty(permIds)) {
            List<NsCorePermission> permissions = permissionMapper.selectByPerids(permIds);
            //3.2获取该角色的所有功能
            List<String> functionIds = new ArrayList<>();
            for(NsCorePermission permission : permissions){
                functionIds.add(permission.getFuncid());
            }
            //functionIds 去重
            CommonUtils.removeDuplicate(functionIds);
            List<NsCoreFuncinfo> funcinfos = funcinfoMapper.selectByFunctionIds(functionIds);
            //3.3获取该角色的所有菜单
            List<NsCoreMenu>  allMenus = new ArrayList<>();
            for(NsCoreFuncinfo function : funcinfos){
                //获取功能所挂的菜单
                Map<String, Object> paramMap = new HashMap<>();
                paramMap.put("enterpriseId", roleVo.getEnterpriseId());
                paramMap.put("organizationId", roleVo.getOrganizationId());
                paramMap.put("functionCode", function.getFuncinfoFunccode());
                NsCoreMenu funcMenu = menuMapper.selectByNodeInfo(paramMap);
                //树形路径
                String treePath = funcMenu.getSyPath();
                String[] menuIdArray = treePath.split("/");
                List<String> menuIds = Arrays.asList(menuIdArray);
                Map<String, Object> paramMap1 = new HashMap<>();
                paramMap1.put("enterpriseId", function.getEnterpriseId());
                paramMap1.put("organizationId", function.getOrganizationId());
                paramMap1.put("menuIds", menuIds);
                List<NsCoreMenu> menus = menuMapper.selectByMenuIds(paramMap1);
                allMenus.addAll(menus);
            }
            //3.4获取该角色的所有按钮
            List<String> buttonIds = new ArrayList<>();
            for (NsCorePermission permission : permissions) {
                if (permission.getPermtype().equals("button")) {
                    buttonIds.add(permission.getPermcode());
                }
            }
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("enterpriseId", roleVo.getEnterpriseId());
            paramMap2.put("organizationId", roleVo.getOrganizationId());
            paramMap2.put("buttonIds", buttonIds);
            List<NsCoreResourcebutton> resourcebuttons = buttonMapper.selectByButtonIds(paramMap2);
            //3.5功能所挂的按钮
            List<NsCoreFuncinfoVo> funcinfoVos = new ArrayList<>();
            for (NsCoreFuncinfo funcinfo : funcinfos) {
                NsCoreFuncinfoVo funcinfoVo = new NsCoreFuncinfoVo();
                BeanUtils.copyProperties(funcinfo, funcinfoVo);
                List<NsCoreResourcebutton> groupByFuncIdButtons= new ArrayList<>();
                List<String> buttonIds_func = new ArrayList<>();
                for (NsCoreResourcebutton button : resourcebuttons) {
                    if (button.getResourcebuttonFuncinfoId().equals(funcinfoVo.getJeCoreFuncinfoId())) {
                        groupByFuncIdButtons.add(button);
                        buttonIds_func.add(button.getJeCoreResourcebuttonId());
                    }
                }
                //设置属于该功能的所有按钮
                funcinfoVo.setButtonIds(buttonIds_func);
                
                //对groupByFuncIdButtons根据按钮类型分类
                List<NsCoreResourcebutton> actionButtonList = new ArrayList<>();
                List<NsCoreResourcebutton> gridButtonList = new ArrayList<>();
                List<NsCoreResourcebutton> formButtonList = new ArrayList<>();
                for (NsCoreResourcebutton button : groupByFuncIdButtons) {
                    if (button.getResourcebuttonType().equals("ACTION")) {
                        actionButtonList.add(button);
                    }else if (button.getResourcebuttonType().equals("GRID")) {
                        gridButtonList.add(button);
                    }else if (button.getResourcebuttonType().equals("FORM")) {
                        formButtonList.add(button);
                    }
                }
               
                List<Map<String,Object>> buttonList = new ArrayList<>();
                if (!CollectionUtils.isEmpty(actionButtonList)) {
                    Map<String, Object> actionmap = new HashMap<>();
                    actionmap.put("buttonType", "页面");
                    actionmap.put("buttons", actionButtonList);
                    buttonList.add(actionmap);
                }
                if (!CollectionUtils.isEmpty(gridButtonList)) {
                    Map<String, Object> gridmap = new HashMap<>();
                    gridmap.put("buttonType", "列表");
                    gridmap.put("buttons", gridButtonList);
                    buttonList.add(gridmap);
                }
                if (!CollectionUtils.isEmpty(formButtonList)) {
                    Map<String, Object> formmap = new HashMap<>();
                    formmap.put("buttonType", "表单");
                    formmap.put("buttons", formButtonList);
                    buttonList.add(formmap);
                }
                funcinfoVo.setResourcebuttons(buttonList);
                funcinfoVos.add(funcinfoVo);
            }
            //3.6设置菜单所挂的功能
            List<NsCoreMenuVo> allMenuVos = new ArrayList<>();
            for (NsCoreMenu menu : allMenus) {
                NsCoreMenuVo menuVo = new NsCoreMenuVo();
                BeanUtils.copyProperties(menu, menuVo);
                for (NsCoreFuncinfoVo funcVo : funcinfoVos) {
                    if (funcVo.getFuncinfoFunccode().equals(menuVo.getMenuNodeinfo())) {
                        menuVo.setFuncinfoVo(funcVo);
                    }
                }
                allMenuVos.add(menuVo);
            }
            //生成树
            //rootMenus = new ArrayList<>();
            if (!CollectionUtils.isEmpty(allMenuVos)) {
                for (Iterator<NsCoreMenuVo> iterator = allMenuVos.iterator(); iterator.hasNext();) {
                    NsCoreMenuVo menuVo = (NsCoreMenuVo) iterator.next();
                    if (menuVo.getSyNodetype().equals("GENERAL")) {
                        rootMenus.add(menuVo);
                        iterator.remove();
                    }
                }
                rootMenus = removeDupliById(rootMenus);
                getChildNode(rootMenus,allMenuVos);
            }
        }
        return rootMenus;
        
    }
    
    private static List<NsCoreMenuVo> removeDupliById(List<NsCoreMenuVo> menuVo) {
        Set<NsCoreMenuVo> menuVoSet = new TreeSet<>((o1, o2) -> o1.getId().compareTo(o2.getId()));
        menuVoSet.addAll(menuVo);
        return new ArrayList<>(menuVoSet);
    }
    
    private static List<NsSystemMenuVo> removeNsSystemMenuVoDupliById(List<NsSystemMenuVo> menuVo) {
        Set<NsSystemMenuVo> menuVoSet = new TreeSet<>((o1, o2) -> o1.getJeCoreMenuId().compareTo(o2.getJeCoreMenuId()));
        menuVoSet.addAll(menuVo);
        return new ArrayList<>(menuVoSet);
    }
    
    
    private void getChildNode(List<NsCoreMenuVo> rootMenus, List<NsCoreMenuVo> allMenuVos){
        if (!CollectionUtils.isEmpty(allMenuVos)) {
            for (NsCoreMenuVo rootMenuVo : rootMenus) {
                List<NsCoreMenuVo> childMenus = new ArrayList<>();
                for (Iterator<NsCoreMenuVo> iterator = allMenuVos.iterator(); iterator.hasNext();) {
                    NsCoreMenuVo menuVo = (NsCoreMenuVo) iterator.next();
                    if (rootMenuVo.getJeCoreMenuId().equals(menuVo.getSyParent())) {
                        childMenus.add(menuVo);
                        iterator.remove();
                    }
                }
                if (!CollectionUtils.isEmpty(childMenus)) {
                    rootMenuVo.setChildMenus(childMenus);
                    getChildNode(childMenus,allMenuVos);
                }
            }
        }
    }

    @Override
    public List<NsCoreRoleVo> listByRoleids(List<String> list) {
        List<NsCoreRole> listrole = roleMapper.selectByRoleIds(list);
        List<NsCoreRoleVo> listroleVo = new ArrayList<>(); 
        if (!CollectionUtils.isEmpty(listrole)) {
            listrole.forEach(role ->{
                NsCoreRoleVo roleVo = new NsCoreRoleVo();
                BeanUtils.copyProperties(role, roleVo);
                roleVo.setDisabled(false);
                roleVo.setCheckSw(false);
                listroleVo.add(roleVo);
            });
        }
        return listroleVo;
    }

    @Override
    public Boolean createSuperAdmin(NsSystemSuperAdmin superAdmin) {
       /* JEPF同步企业客户服务的同时默认给该企业创建超级管理员角色，并将企业负责人添加到该角色*/
        boolean result = false;
        Map<String, Object> paramMap0 = new HashMap<>();
        paramMap0.put("organizationId", superAdmin.getOrganizationId());
        if("soss".equals(superAdmin.getRoleType())){
        	paramMap0.put("roleName", "运维管理员");
        }else {
        	paramMap0.put("roleName", "超级管理员");
		}
        NsCoreRole supAdmin = roleMapper.selectByOrgIdAndName(paramMap0);
        String roleid = null;
        NsSystemOrganization organization = organizationMapper.selectById(superAdmin.getOrganizationId());
        //组织类型集团的创建超级管理员
        if (organization.getOrganizationType()==0) {
            if (!Objects.isNull(supAdmin)) {
                roleid =  supAdmin.getRoleid();
            }else{
              //1默认创建角色组
                NsCoreRolecategory roleCategory =  new NsCoreRolecategory();
                roleCategory.setEnterpriseId(organization.getEnterpriseId());
                roleCategory.setOrganizationId(organization.getOrganizationId());
                if("soss".equals(superAdmin.getRoleType())){
                	roleCategory.setRolecategoryName("运维管理员组");
                }else {
                	roleCategory.setRolecategoryName("超级管理员组");
        		}
                rolecategoryMapper.insertSelective(roleCategory);
                //2默认创建角色基本信息
                NsCoreRole role = new NsCoreRole();
                role.setEnterpriseId(organization.getEnterpriseId());
                role.setOrganizationId(organization.getOrganizationId());
                roleid = UUID.randomUUID().toString();
                role.setRoleid(roleid);
                if("soss".equals(superAdmin.getRoleType())){
                	role.setRolename("运维管理员");
                }else {
                	role.setRolename("超级管理员");
        		}
                role.setRolecategoryId(roleCategory.getRolecategoryId());
                roleMapper.insert(role);
            }
            
            //3获取该企业客户的所有功能
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId", organization.getEnterpriseId());
            paramMap.put("organizationId", organization.getOrganizationId());
            List<NsCoreFuncinfo>  funcinfos = funcinfoMapper.selectByOrgId(paramMap);
            //4获取该企业客户的所有菜单
            List<NsCoreMenu>  allMenus = new ArrayList<>();
            for(NsCoreFuncinfo function : funcinfos){
                //获取功能所挂的菜单
                Map<String, Object> paramMap1 = new HashMap<>();
                paramMap1.put("enterpriseId", organization.getEnterpriseId());
                paramMap1.put("organizationId", organization.getOrganizationId());
                paramMap1.put("functionCode", function.getFuncinfoFunccode());
                NsCoreMenu funcMenu = menuMapper.selectByNodeInfo(paramMap1);
                //树形路径
                String treePath = funcMenu.getSyPath();
                String[] menuIdArray = treePath.split("/");
                List<String> menuIds = Arrays.asList(menuIdArray);
                Map<String, Object> paramMap2 = new HashMap<>();
                paramMap2.put("enterpriseId", organization.getEnterpriseId());
                paramMap2.put("organizationId", organization.getOrganizationId());
                paramMap2.put("menuIds", menuIds);
                List<NsCoreMenu> menus = menuMapper.selectByMenuIds(paramMap2);
                allMenus.addAll(menus);
            }
            
            //5获取该企业客户的所有按钮
            Map<String, Object> paramMap3 = new HashMap<>();
            paramMap3.put("enterpriseId", organization.getEnterpriseId());
            paramMap3.put("organizationId", organization.getOrganizationId());
            List<NsCoreResourcebutton> resourcebuttons = buttonMapper.selectByOrgId(paramMap3);
            //6设置功能所挂的按钮
            List<NsCoreFuncinfoVo> funcinfoVos = new ArrayList<>();
            for (NsCoreFuncinfo func : funcinfos) {
                NsCoreFuncinfoVo funcinfoVo = new NsCoreFuncinfoVo();
                BeanUtils.copyProperties(func, funcinfoVo);
                List<NsCoreResourcebutton> groupByFuncIdButtons= new ArrayList<>();
                List<String> buttonIds = new ArrayList<>();
                for (NsCoreResourcebutton button : resourcebuttons) {
                    if (button.getResourcebuttonFuncinfoId().equals(funcinfoVo.getJeCoreFuncinfoId())) {
                        groupByFuncIdButtons.add(button);
                        buttonIds.add(button.getJeCoreResourcebuttonId());
                    }
                }
                funcinfoVo.setButtonIds(buttonIds);
                funcinfoVos.add(funcinfoVo);
            }
            
            SortList<NsCoreFuncinfoVo> sortFuncList = new SortList<NsCoreFuncinfoVo>();
            sortFuncList.Sort(funcinfoVos, "getSyOrderindex", "ASC");//调用排序通用类,按syOrderindex排序
            
            //默认新增功能按钮权限
            //先清除超级管理员的原先的权限
            List<NsCoreRolePerm> oldRolePerms = rolePermMapper.selectByRoleId(roleid);
            if (!CollectionUtils.isEmpty(oldRolePerms)) {
                List<String> oldPerids = new ArrayList<>();
                for (NsCoreRolePerm rolePerm : oldRolePerms) {
                    oldPerids.add(rolePerm.getPerid());
                }
                permissionMapper.deleteByPerids(oldPerids);
                rolePermMapper.deleteByRoleId(roleid);
            }
            List<NsSystemRoleHouse> roleHouses = roleHouseMapper.selectByRoleid(roleid);
            if (!CollectionUtils.isEmpty(roleHouses)) {
                roleHouseMapper.deleteByRoleid(roleid);
            }
            
            //重新新增
            List<String> perids = new ArrayList<>();
            if (!CollectionUtils.isEmpty(funcinfoVos)) {
                funcinfoVos.forEach(func -> {
                    List<String> buttonIds = func.getButtonIds();
                    if (!CollectionUtils.isEmpty(buttonIds)) {
                        List<NsCorePermission> permissions = new ArrayList<>();
                        buttonIds.forEach(buttonId -> {
                            NsCorePermission permission = new NsCorePermission();
                            String perid = UUID.randomUUID().toString();
                            perids.add(perid);
                            permission.setEnterpriseId(organization.getEnterpriseId());
                            permission.setOrganizationId(organization.getOrganizationId());
                            permission.setPerid(perid);
                            permission.setFuncid(func.getJeCoreFuncinfoId());
                            permission.setPermcode(buttonId);
                            permission.setPermtype("button");
                            permissions.add(permission);
                        });
                        permissionMapper.insertBatch(permissions);
                    }
                });
            }
            
            //建立角色与功能按钮权的关系
            if (!CollectionUtils.isEmpty(perids)) {
                List<NsCoreRolePerm> rolePerms = new ArrayList<>();
                for (String perid : perids) {
                    NsCoreRolePerm rolePerm = new NsCoreRolePerm();
                    rolePerm.setEnterpriseId(organization.getEnterpriseId());
                    rolePerm.setOrganizationId(organization.getOrganizationId());
                    rolePerm.setRoleid(roleid);
                    rolePerm.setEnabled("1");
                    rolePerm.setPerid(perid);
                    rolePerms.add(rolePerm);
                }
                rolePermMapper.insertBatch(rolePerms);
            }
       
            //建立角色与房产权限的关系
            List<Long> houseIds = superAdmin.getHouseIds();
            if (!CollectionUtils.isEmpty(houseIds)) {
                List<NsSystemRoleHouse> rolePerms = new ArrayList<>();
                for (Long houseId : houseIds) {
                    NsSystemRoleHouse roleHouse = new NsSystemRoleHouse();
                    roleHouse.setEnterpriseId(organization.getEnterpriseId());
                    roleHouse.setOrganizationId(organization.getOrganizationId());
                    roleHouse.setRoleid(roleid);
                    roleHouse.setHouseId(houseId);
                    
                    roleHouse.setCreateuserid(1L);
                    roleHouse.setUpdateuserid(1L);
                    roleHouse.setHouseType((byte)0);
                    roleHouse.setIsdelete((byte)0);
                    roleHouse.setCreateusername("zrk");
                    roleHouse.setUpdateusername("zrk");
                    roleHouse.setRemark("");
                    roleHouse.setHouseId(1L);
                    
                    rolePerms.add(roleHouse);
                }
                roleHouseMapper.insertBatch(rolePerms);
            }
            //新增默认授权人
            List<String> userIds = new ArrayList<>();
            userIds.add(superAdmin.getUserid().toString());
            NsSystemAuthorizer authorizer = new NsSystemAuthorizer();
            authorizer.setEnterpriseId(organization.getEnterpriseId());
            authorizer.setOrganizationId(organization.getOrganizationId());
            authorizer.setRoleid(roleid);
            authorizer.setUserIds(userIds);
            roleUserService.add(authorizer);
            
            //清除superAdmin redis中的菜单按钮权限
            String menubuttonRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_BUTTON_PREFIX+superAdmin.getUserid();
            redisUtil.deleteByPrefix(menubuttonRediskey);
            //清除superAdmin redis中的菜单权限
            String menuRediskey = RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + organization.getOrganizationId() + "_" + superAdmin.getUserid();
            redisUtil.deleteByPrefix(menuRediskey);
            
            result = true;
        }else{
            result = false;
        }
        
        return result;
    }
    
    

}
