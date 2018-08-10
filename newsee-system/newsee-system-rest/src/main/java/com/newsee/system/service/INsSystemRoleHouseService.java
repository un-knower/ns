package com.newsee.system.service;

import java.util.List;

import com.newsee.system.entity.NsSystemRoleHouse;

public interface INsSystemRoleHouseService {
    
    List<NsSystemRoleHouse> listRoleHouse(Long userId);
}
