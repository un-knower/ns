package com.newsee.system.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.vo.SearchVo;
import com.newsee.system.vo.NsSystemUserVo;

public interface INsSystemUserService {

    Boolean add(NsSystemUserVo userVo);
    
    Boolean edit(NsSystemUserVo userVo, Long login_userId, String userName);
    
    Boolean delete(Long userId, Long login_userId);
    
    Boolean editUser(NsSystemUserVo userVo, String operateType);
    
    List<NsSystemUserVo> list(Long organizationId);
    
    List<NsSystemUserVo> listUserSearch(Long enterpriseId,Long organizationId,String userName);
    
    PageInfo<NsSystemUserVo> listPage(SearchVo searchVo);
    
    NsSystemUserVo detail(Long userId);
    
    List<NsSystemUser> detailUser(Long userId, String userPhone);
    
    Boolean roolbackRegister(Long userId,Long groupId,Long organizationId);
    

    /**
     * 注册企业与新用户
     * @param userVo
     * @return
     */
	Long addRegisterUser(NsSystemUserVo userVo);

	/**
	 * 编辑企业注册用户
	 * @param userVo
	 * @return [0] 集团ID，[1] 组织ID
	 */
	Long[] editRegisterUser(NsSystemUserVo userVo, String enterpriseName);
    
	/**
	 * 获取企业员工数
	 * @param userList
	 * @return
	 * @throws Exception
	 */
	List<NsSystemUser> getUserCountByEnterpriseId(List<NsSystemUser> userList) throws Exception;
}
