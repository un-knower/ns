package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsSystemRoleHouse;

public interface NsSystemRoleHouseMapper {
    int deleteByPrimaryKey(Long id);

    int insert(NsSystemRoleHouse record);

    int insertSelective(NsSystemRoleHouse record);

    NsSystemRoleHouse selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(NsSystemRoleHouse record);

    int updateByPrimaryKey(NsSystemRoleHouse record);
    
    int insertBatch(List<NsSystemRoleHouse> nsSystemRoleHouseList);
    
    int deleteByRoleid(String roleid);
    
    List<NsSystemRoleHouse> selectByRoleid(String roleid);
    
    List<NsSystemRoleHouse> selectByRoleids(List<String> list);
    
}