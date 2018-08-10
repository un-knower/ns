package com.newsee.system.service;

import java.util.List;

import com.newsee.system.vo.NsCoreRolegroupVo;

/**
 * @ClassName INsRoleGroupService
 * @Description: 角色组接口 
 * @author 胡乾亮
 * @date 2017年11月15日 下午3:18:52
 */
public interface INsRoleGroupService {
    
    Boolean add(NsCoreRolegroupVo roleGroupVo, Long userId);
    
    List<NsCoreRolegroupVo> listRolegroup(Long organizationId);
}