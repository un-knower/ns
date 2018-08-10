package com.newsee.system.service;

import com.newsee.system.vo.NsSystemCompanyVo;
/**
 * @ClassName INsCompanyService
 * @Description: 公司接口 
 * @author 胡乾亮
 * @date 2017年11月14日 上午11:47:03
 */
public interface INsCompanyService {

    Boolean add(NsSystemCompanyVo company, Long loginUserId, String loginUserName);
    
    Boolean edit(NsSystemCompanyVo company, Long loginUserId, String loginUserName);
    
    Boolean delete(Long organizationId, Long companyId, Long loginUserId, String loginUserName);
    
    NsSystemCompanyVo detail(Long id);
}
