/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.system.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.vo.SearchVo;

public interface NsSystemUserMapper {
  
    NsSystemUser selectById(Long id);
    
    NsSystemUser checkOnlyUser(Map<String, Object> map);

    int insert(NsSystemUser nsSystemUser);
    
    int insertBatch(List<NsSystemUser> nsSystemUserList);
    
    int updateByIdSelective(NsSystemUser nsSystemUser);
    
    int updateById(NsSystemUser nsSystemUser);
    
    int updateOrgNameByOrgId(NsSystemUser nsSystemUser);

    int deleteById(Long id);
    
    int deleteSoftById(Long id);
    
    List<NsSystemUser> selectByOrganizationId(Long id);
    
    List<NsSystemUser> listResultBySearch(SearchVo searchVo);
    
    List<NsSystemUser> selectByIds(List<Long> list);
    
    List<NsSystemUser> listUserSearch(Map<String, Object> map);
    
    /**
     * 获取企业下员工数量
     * @param enterpriseIdList
     * @return
     */
    List<NsSystemUser> selectUserCountByEnterprise(List<Long> enterpriseIdList);
    
    /**
     * 获取员工基本信息
     * @param enterpriseIdList
     * @param userIdList
     * @return
     */
    List<NsSystemUser> selectUserInfo(Map<String, Object> map);
}
