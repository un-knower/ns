package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.system.dao.NsCoreFuncinfoMapper;
import com.newsee.system.dao.NsCoreMenuMapper;
import com.newsee.system.dao.NsCorePermissionMapper;
import com.newsee.system.dao.NsCoreResourcebuttonMapper;
import com.newsee.system.dao.NsCoreRoleMapper;
import com.newsee.system.dao.NsCoreRolePermMapper;
import com.newsee.system.dao.NsCoreRoleUserMapper;
import com.newsee.system.dao.NsCoreRolegroupPermMapper;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.entity.NsCoreMenu;
import com.newsee.system.entity.NsCorePermission;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.entity.NsCoreRole;
import com.newsee.system.entity.NsCoreRolePerm;
import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.entity.NsCoreRolegroupPerm;
import com.newsee.system.service.INsMenuService;
import com.newsee.system.utils.SortList;
import com.newsee.system.vo.NsCoreFuncinfoVo;
import com.newsee.system.vo.NsCoreMenuVo;

@Service
public class NsMenuServiceImpl implements INsMenuService {
    
    @Autowired
    NsCoreMenuMapper menuMapper;
    @Autowired
    NsCoreRoleUserMapper roleUserMapper;
    @Autowired
    NsCoreRoleMapper roleMapper;
    @Autowired
    NsCoreRolePermMapper rolePermMapper;
    @Autowired
    NsCorePermissionMapper permissionMapper;
    @Autowired
    NsCoreFuncinfoMapper funcinfoMapper;
    @Autowired
    NsCoreResourcebuttonMapper buttonMapper;
    @Autowired
    NsCoreRolegroupPermMapper rolegroupPermMapper;

    @Override
    public List<NsCoreMenuVo> listMenu(LoginCommonDataVo loginVo) {
        //获取当前用户的所有功能
        List<NsCoreFuncinfo> funcinfos = getAllFunction(loginVo);
        //获取当前用户的所有菜单
        List<NsCoreMenu>  allMenus = new ArrayList<>();
        for(NsCoreFuncinfo function : funcinfos){
            //获取功能所挂的菜单
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId", loginVo.getEnterpriseId());
            //paramMap.put("organizationId", loginVo.getOrganizationId());
            paramMap.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap.put("functionCode", function.getFuncinfoFunccode());
            NsCoreMenu funcMenu = menuMapper.selectByNodeInfo(paramMap);
            //树形路径
            String treePath = funcMenu.getSyPath();
            String[] menuIdArray = treePath.split("/");
            List<String> menuIds = Arrays.asList(menuIdArray);
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("enterpriseId", function.getEnterpriseId());
            //paramMap1.put("organizationId", function.getOrganizationId());
            paramMap1.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap1.put("menuIds", menuIds);
            List<NsCoreMenu> menus = menuMapper.selectByMenuIds(paramMap1);
            List<NsCoreMenu> trueMenus = new ArrayList<>();
            for (NsCoreMenu nsCoreMenu : menus) {
                if (nsCoreMenu.getOrganizationId().equals(LoginDataHelper.getGroupLevelOrgId()) 
                        && nsCoreMenu.getEnterpriseId().equals(loginVo.getEnterpriseId())) {
                    trueMenus.add(nsCoreMenu);
                }
            }
            allMenus.addAll(trueMenus);
        }
        //生成树
        List<NsCoreMenuVo> allMenuVos = new ArrayList<>();
        List<NsCoreMenuVo> rootMenus = new ArrayList<>();
        if (!CollectionUtils.isEmpty(allMenus)) {
            for (Iterator<NsCoreMenu> iterator = allMenus.iterator(); iterator.hasNext();) {
                NsCoreMenu menu = (NsCoreMenu) iterator.next();
                NsCoreMenuVo menuVo = new NsCoreMenuVo();
                BeanUtils.copyProperties(menu, menuVo);
                for (NsCoreFuncinfo funcinfo : funcinfos) {
                    if (funcinfo.getFuncinfoFunccode().equals(menuVo.getMenuNodeinfo())) {
                        menuVo.setFuncId(funcinfo.getJeCoreFuncinfoId());
                    }
                }
                allMenuVos.add(menuVo);
                if (menuVo.getSyNodetype().equals("GENERAL")) {
                    rootMenus.add(menuVo);
                    iterator.remove();
                }
            }
            rootMenus = removeDupliById(rootMenus);
            //调用排序通用类,按syOrderindex排序
            SortList<NsCoreMenuVo> sortList = new SortList<NsCoreMenuVo>();
            sortList.Sort(rootMenus, "getSyOrderindex", "ASC");
            getChildNode(rootMenus,allMenuVos);
        }
        return rootMenus; 
    }
    
    @Override
    public List<NsCoreMenuVo> listMenuButton(LoginCommonDataVo loginVo) {
        //============当前用户所有权限
        List<NsCorePermission> permissions = getAllPermission(loginVo);
        
        //============当前用户所有功能
        List<NsCoreFuncinfo> funcinfos = new ArrayList<>();
        List<String> functionIds = new ArrayList<>();
        for(NsCorePermission permission : permissions){
            functionIds.add(permission.getFuncid());
        }
        //functionIds 去重
        CommonUtils.removeDuplicate(functionIds);
        for (String functionId : functionIds) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId", loginVo.getEnterpriseId());
            //paramMap.put("organizationId", loginVo.getOrganizationId());
            paramMap.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap.put("jeCoreFuncinfoId", functionId);
            NsCoreFuncinfo funcinfo = funcinfoMapper.selectByOrgIdAndFuncId(paramMap);
            if (funcinfo != null) {
                funcinfos.add(funcinfo);
            }
        }
        //============当前用户所有菜单
        List<NsCoreMenu>  allMenus = new ArrayList<>();
        for(NsCoreFuncinfo function : funcinfos){
            //获取功能所挂的菜单
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId", function.getEnterpriseId());
            //paramMap.put("organizationId", function.getOrganizationId());
            paramMap.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap.put("functionCode", function.getFuncinfoFunccode());
            NsCoreMenu funcMenu = menuMapper.selectByNodeInfo(paramMap);
            //树形路径
            String treePath = funcMenu.getSyPath();
            String[] menuIdArray = treePath.split("/");
            List<String> menuIds = Arrays.asList(menuIdArray);
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("enterpriseId", function.getEnterpriseId());
            //paramMap1.put("organizationId", function.getOrganizationId());
            paramMap1.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap1.put("menuIds", menuIds);
            List<NsCoreMenu> menus = menuMapper.selectByMenuIds(paramMap1);
            allMenus.addAll(menus);
        }
        //============当前用户的所有按钮
        List<String> buttonIds = new ArrayList<>();
        for (NsCorePermission permission : permissions) {
            if (permission.getPermtype().equals("button")) {
                buttonIds.add(permission.getPermcode());
            }
        }
        Map<String, Object> paramMap2 = new HashMap<>();
        paramMap2.put("enterpriseId", loginVo.getEnterpriseId());
        //paramMap2.put("organizationId", loginVo.getOrganizationId());
        paramMap2.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
        paramMap2.put("buttonIds", buttonIds);
        List<NsCoreResourcebutton> resourcebuttons = buttonMapper.selectByButtonIds(paramMap2);
        
        
        //============设置功能所挂的按钮
        List<NsCoreFuncinfoVo> funcinfoVos = new ArrayList<>();
        for (NsCoreFuncinfo funcinfo : funcinfos) {
            NsCoreFuncinfoVo funcinfoVo = new NsCoreFuncinfoVo();
            BeanUtils.copyProperties(funcinfo, funcinfoVo);
            List<NsCoreResourcebutton> groupByFuncIdButtons= new ArrayList<>();
            for (NsCoreResourcebutton button : resourcebuttons) {
                if (button.getResourcebuttonFuncinfoId().equals(funcinfoVo.getJeCoreFuncinfoId())) {
                    groupByFuncIdButtons.add(button);
                }
            }
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
        
        //调用排序通用类,按syOrderindex排序
        SortList<NsCoreFuncinfoVo> sortFuncList = new SortList<NsCoreFuncinfoVo>();
        sortFuncList.Sort(funcinfoVos, "getSyOrderindex", "ASC");
        //===========将功能挂到菜单上
        List<NsCoreMenuVo> allMenuVos = new ArrayList<>();
        List<NsCoreMenuVo> rootMenus = new ArrayList<>();
        if (!CollectionUtils.isEmpty(allMenus)) {
            for (Iterator<NsCoreMenu> iterator = allMenus.iterator(); iterator.hasNext();) {
                NsCoreMenu menu = (NsCoreMenu) iterator.next();
                NsCoreMenuVo menuVo = new NsCoreMenuVo();
                BeanUtils.copyProperties(menu, menuVo);
                for (NsCoreFuncinfoVo funcinfo : funcinfoVos) {
                    if (funcinfo.getFuncinfoFunccode().equals(menuVo.getMenuNodeinfo())) {
                        menuVo.setFuncId(funcinfo.getJeCoreFuncinfoId());
                        menuVo.setFuncinfoVo(funcinfo);
                    }
                }
                allMenuVos.add(menuVo);
                if (menuVo.getSyNodetype().equals("GENERAL")) {
                    rootMenus.add(menuVo);
                    iterator.remove();
                }
            }
            rootMenus = removeDupliById(rootMenus);
            //调用排序通用类,按syOrderindex排序
            SortList<NsCoreMenuVo> sortList = new SortList<NsCoreMenuVo>();
            sortList.Sort(rootMenus, "getSyOrderindex", "ASC");
            getChildNode(rootMenus,allMenuVos);
        }
        return rootMenus;
    }
    
    
    @Override
    public List<NsCoreMenuVo> listAllMenuButton(LoginCommonDataVo loginVo) {
        
        //当前用户所在组织的所有功能
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", loginVo.getEnterpriseId());
        //paramMap.put("organizationId", loginVo.getOrganizationId());
        paramMap.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
        List<NsCoreFuncinfo>  funcinfos = funcinfoMapper.selectByOrgId(paramMap);
        
        //当前用户所在组织的所有菜单
        List<NsCoreMenu>  allMenus = new ArrayList<>();
        for(NsCoreFuncinfo function : funcinfos){
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
            Map<String, Object> paramMap2 = new HashMap<>();
            paramMap2.put("enterpriseId", function.getEnterpriseId());
            //paramMap2.put("organizationId", function.getOrganizationId());
            paramMap2.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap2.put("menuIds", menuIds);
            List<NsCoreMenu> menus = menuMapper.selectByMenuIds(paramMap2);
            allMenus.addAll(menus);
        }
        
        //当前用户所在组织的所有按钮
        Map<String, Object> paramMap3 = new HashMap<>();
        paramMap3.put("enterpriseId", loginVo.getEnterpriseId());
        //paramMap3.put("organizationId", loginVo.getOrganizationId());
        paramMap3.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
        List<NsCoreResourcebutton> resourcebuttons = buttonMapper.selectByOrgId(paramMap3);
        
        //设置功能所挂的按钮
        List<NsCoreFuncinfoVo> funcinfoVos = new ArrayList<>();
        for (NsCoreFuncinfo func : funcinfos) {
            NsCoreFuncinfoVo funcinfoVo = new NsCoreFuncinfoVo();
            BeanUtils.copyProperties(func, funcinfoVo);
            List<NsCoreResourcebutton> groupByFuncIdButtons= new ArrayList<>();
            for (NsCoreResourcebutton button : resourcebuttons) {
                if (button.getResourcebuttonFuncinfoId().equals(funcinfoVo.getJeCoreFuncinfoId())) {
                    groupByFuncIdButtons.add(button);
                }
            }
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
        
        //调用排序通用类,按syOrderindex排序
        SortList<NsCoreFuncinfoVo> sortFuncList = new SortList<NsCoreFuncinfoVo>();
        sortFuncList.Sort(funcinfoVos, "getSyOrderindex", "ASC");
        
        List<NsCoreMenuVo> allMenuVos = new ArrayList<>();
        List<NsCoreMenuVo> rootMenus = new ArrayList<>();
        if (!CollectionUtils.isEmpty(allMenus)) {
            for (Iterator<NsCoreMenu> iterator = allMenus.iterator(); iterator.hasNext();) {
                NsCoreMenu menu = (NsCoreMenu) iterator.next();
                NsCoreMenuVo menuVo = new NsCoreMenuVo();
                BeanUtils.copyProperties(menu, menuVo);
                for (NsCoreFuncinfoVo funcVo : funcinfoVos) {
                    if (funcVo.getFuncinfoFunccode().equals(menuVo.getMenuNodeinfo())) {
                        menuVo.setFuncId(funcVo.getJeCoreFuncinfoId());
                        menuVo.setFuncinfoVo(funcVo);
                    }
                }
                allMenuVos.add(menuVo);
                if (menuVo.getSyNodetype().equals("GENERAL")) {
                    rootMenus.add(menuVo);
                    iterator.remove();
                }
            }
            rootMenus = removeDupliById(rootMenus);
            //调用排序通用类,按syOrderindex排序
            SortList<NsCoreMenuVo> sortMenuList = new SortList<NsCoreMenuVo>();
            sortMenuList.Sort(rootMenus, "getSyOrderindex", "ASC");
            getChildNode(rootMenus,allMenuVos);
        }
        return rootMenus;
    }

    /**
     * @Description: 获取当前用户的所有菜单
     * @author 胡乾亮
     * @date 2017年11月15日上午11:38:54
     */
    private List<NsCoreMenu> getAllMenus(LoginCommonDataVo loginVo) {
        //获取当前用户的所有功能
        List<NsCoreFuncinfo> funcinfos = getAllFunction(loginVo);
        //获取菜单
        List<NsCoreMenu>  allMenus = new ArrayList<>();
        for(NsCoreFuncinfo function : funcinfos){
            //获取功能所挂的菜单
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId", function.getEnterpriseId());
            //paramMap.put("organizationId", function.getOrganizationId());
            paramMap.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap.put("functionCode", function.getFuncinfoFunccode());
            NsCoreMenu funcMenu = menuMapper.selectByNodeInfo(paramMap);
            //树形路径
            String treePath = funcMenu.getSyPath();
            String[] menuIdArray = treePath.split("/");
            List<String> menuIds = Arrays.asList(menuIdArray);
            Map<String, Object> paramMap1 = new HashMap<>();
            paramMap1.put("enterpriseId", function.getEnterpriseId());
            //paramMap1.put("organizationId", function.getOrganizationId());
            paramMap1.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap1.put("menuIds", menuIds);
            List<NsCoreMenu> menus = menuMapper.selectByMenuIds(paramMap1);
            allMenus.addAll(menus);
        }
        return allMenus;
    }

    /**
     * @Description: 获取当前用户的所有功能
     * @author 胡乾亮
     * @date 2017年11月15日下午12:12:47
     */
    private List<NsCoreFuncinfo> getAllFunction(LoginCommonDataVo loginVo) {
        //当前用户所有权限
        List<NsCorePermission> permissions = getAllPermission(loginVo);
        //获取功能
        List<String> functionIds = new ArrayList<>();
        for(NsCorePermission permission : permissions){
            functionIds.add(permission.getFuncid());
        }
        //functionIds 去重
        CommonUtils.removeDuplicate(functionIds);
        List<NsCoreFuncinfo> funcinfos = new ArrayList<>();
        for (String functionId : functionIds) {
            Map<String, Object> paramMap = new HashMap<>();
            paramMap.put("enterpriseId", loginVo.getEnterpriseId());
            //paramMap.put("organizationId", loginVo.getOrganizationId());
            paramMap.put("organizationId", LoginDataHelper.getGroupLevelOrgId());
            paramMap.put("jeCoreFuncinfoId", functionId);
            NsCoreFuncinfo funcinfo = funcinfoMapper.selectByOrgIdAndFuncId(paramMap);
            if (funcinfo != null) {
                funcinfos.add(funcinfo);
            }
        }
        return funcinfos;
    }

    /**
     * @Description: 当前用户所有权限 
     * @author 胡乾亮
     * @date 2017年11月15日下午12:00:25
     */
    private List<NsCorePermission> getAllPermission(LoginCommonDataVo loginVo) {
        //获取角色ID集合
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("enterpriseId", loginVo.getEnterpriseId());
        paramMap.put("organizationId", loginVo.getOrganizationId());
        paramMap.put("userid", loginVo.getUserId());
        List<NsCoreRoleUser> roleUsers = roleUserMapper.selectByUserId(paramMap);
        List<String> roleIds = new ArrayList<>();
        for(NsCoreRoleUser roleUser : roleUsers){
            roleIds.add(roleUser.getRoleid());
        }
        if (CollectionUtils.isEmpty(roleIds)) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该用户无任何角色！！！");
        }
        //获取该用户的所有权限
        //1.所有角色本身的权限
        List<NsCoreRolePerm> rolePerms = rolePermMapper.selectByRoleIds(roleIds);
        List<String> permIds = new ArrayList<>();
        for(NsCoreRolePerm rolePerm : rolePerms){
            permIds.add(rolePerm.getPerid());
        }
        //2.每个角色继承的权限组的权限
        List<NsCoreRole> roles = roleMapper.selectByRoleIds(roleIds);
        for (NsCoreRole role : roles) {
            List<NsCoreRolegroupPerm> rolegroupPerms = rolegroupPermMapper.selectByRolegroupId(role.getGroupcode());
            for (NsCoreRolegroupPerm rolegroupPerm : rolegroupPerms) {
                permIds.add(rolegroupPerm.getPerid());
            }
        }
        if(CollectionUtils.isEmpty(permIds)){
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该用户无任何权限！！！");
        }
        //去重
        permIds.stream().distinct();
        List<NsCorePermission> permissions = permissionMapper.selectByPerids(permIds);
        return permissions;
    }
    
    
    /**
     * @Description: 去重
     * @author 胡乾亮
     * @date 2017年11月10日下午1:50:38
     */
    private static List<NsCoreMenuVo> removeDupliById(List<NsCoreMenuVo> menuVo) {
        Set<NsCoreMenuVo> menuVoSet = new TreeSet<>((o1, o2) -> o1.getId().compareTo(o2.getId()));
        menuVoSet.addAll(menuVo);
        return new ArrayList<>(menuVoSet);
    }
    
    /**
     * @Description: 递归子节点
     * @author 胡乾亮
     * @date 2017年11月15日上午11:40:03
     */
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
                    SortList<NsCoreMenuVo> sortList = new SortList<NsCoreMenuVo>();
                    sortList.Sort(childMenus, "getSyOrderindex", "ASC");
                    rootMenuVo.setChildMenus(childMenus);
                    getChildNode(childMenus,allMenuVos);
                }
            }
        }
    }
}
