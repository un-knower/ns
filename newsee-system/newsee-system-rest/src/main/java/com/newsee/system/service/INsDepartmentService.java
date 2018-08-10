package com.newsee.system.service;

import com.newsee.system.vo.NsSystemDepartmentVo;

/**
 * @ClassName INsDepartmentService
 * @Description: 部门接口
 * @author 胡乾亮
 * @date 2017年11月14日 上午11:47:29
 */
public interface INsDepartmentService {

    Boolean add(NsSystemDepartmentVo departmentVo, Long loginUserId, String loginUserName);
    
    Boolean edit(NsSystemDepartmentVo departmentVo, Long loginUserId, String loginUserName);
    
    Boolean delete(Long organizationId, Long departmentId, Long loginUserId, String loginUserName);
    
    NsSystemDepartmentVo detail(Long id);
}
