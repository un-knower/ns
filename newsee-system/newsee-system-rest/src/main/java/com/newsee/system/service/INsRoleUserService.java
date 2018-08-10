package com.newsee.system.service;

import java.util.List;

import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.vo.NsSystemAuthorizer;

public interface INsRoleUserService {

    Boolean add(NsSystemAuthorizer authorizer);
    
    List<NsCoreRoleUser> list(String roleid);
}
