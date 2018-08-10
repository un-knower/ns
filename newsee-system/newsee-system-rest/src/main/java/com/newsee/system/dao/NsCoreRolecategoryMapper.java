package com.newsee.system.dao;

import java.util.List;

import com.newsee.system.entity.NsCoreRolecategory;

public interface NsCoreRolecategoryMapper {
    int deleteByPrimaryKey(Long rolecategoryId);
    
    int deleteSoftById(Long rolecategoryId);

    int insert(NsCoreRolecategory record);

    int insertSelective(NsCoreRolecategory record);

    NsCoreRolecategory selectByPrimaryKey(Long rolecategoryId);

    int updateByPrimaryKeySelective(NsCoreRolecategory record);

    int updateByPrimaryKey(NsCoreRolecategory record);
    
    List<NsCoreRolecategory> listByOrganizationId(Long organizationId);
}