/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.vo.SearchVo;

public interface NsSossEnterpriseMapper {
  
    NsSossEnterprise selectById(Long id);
    /**
     * 查询企业基本集合数据
     * @param enterpriseIdList
     * @param isDelete 0 否，1 删除
     * @return
     */
    List<NsSossEnterprise> selectEnterpriseList(Map<String, Object> map);
    
    /**
     * 查询列表
     * @param searchVo
     * @return
     */
    List<NsSossEnterprise> listResultBySearch(SearchVo searchVo);
    
    int insert(NsSossEnterprise nsSossEnterprise);
    
    int insertBatch(List<NsSossEnterprise> nsSossEnterpriseList);
    
    int updateById(NsSossEnterprise nsSossEnterprise);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
}