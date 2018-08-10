package com.newsee.system.service;

import java.util.List;
import java.util.Map;

import com.newsee.system.vo.NsOrganizationTreeSortVo;
import com.newsee.system.vo.NsSystemOrganizationVo;

/**
 * @ClassName INsOrganizationService
 * @Description: 组织接口
 * @author 胡乾亮
 * @date 2017年11月14日 上午11:48:26
 */
public interface INsOrganizationService {

    NsSystemOrganizationVo getOrganizationTree(Long organizationId, Integer orgType);
    
    NsSystemOrganizationVo detail(Long organizationId);
    
    List<NsSystemOrganizationVo> moreDetail(List<Long> organizationIdList);
    
    Map<String, Object> editOrganizationTreeSort(NsOrganizationTreeSortVo treeSortVo, Long userId);
    
    Long getOrgIdOutOfDep(Long organizationId);
    
    Long getOrgIdGroupLevel(Long organizationId);
    
    
    List<NsSystemOrganizationVo> getChildOrganization(Long organizationId, Integer orgType);
    
    List<NsSystemOrganizationVo> listHouseTreeBySearch(Long enterpriseId, Long organizationId, String organizationName);
    
    List<NsSystemOrganizationVo> listAllOrganization(Long enterpriseId, Long organizationId);
    
    String getAllParentName(Long organizationId);
    
    Map<String, Object>generateDeptCodeOrShotName(Long parentOrgId, String currentOrgName);

    /**
     * 获取所有子节点（包含当前节点）
     * @param organizationId
     * @param orgType
     * @return
     */
    List<NsSystemOrganizationVo> listAllChildNode(Long organizationId, Integer orgType);
}
