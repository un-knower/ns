package com.newsee.system.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.system.vo.NsCoreRoleVo;
import com.newsee.system.vo.NsSystemSuperAdmin;
/**
 * @ClassName INsRoleService
 * @Description:角色接口
 * @author 胡乾亮
 * @date 2017年11月14日 上午11:48:44
 */
public interface INsRoleService {

    Boolean add(NsCoreRoleVo roleVo);
    
    Boolean edit(NsCoreRoleVo roleVo);
    
    Boolean delete(String roleid);
    
    List<NsCoreRoleVo> list(Long organizationId);
    
    NsCoreRoleVo detail(String roleid);
    
    PageInfo<NsCoreRoleVo> listPage(SearchVo searchVo);
    
    List<NsCoreRoleVo> listByRoleids(List<String> list);
    
    Boolean createSuperAdmin(NsSystemSuperAdmin superAdmin);
}
