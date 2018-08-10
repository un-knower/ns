package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.constant.Constants;
import com.newsee.common.entity.NsSystemCompany;
import com.newsee.common.enums.OrganizationTypeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FirstLetterUtil;
import com.newsee.common.utils.StringUtils;
import com.newsee.system.dao.NsSystemCompanyMapper;
import com.newsee.system.dao.NsSystemDepartmentMapper;
import com.newsee.system.dao.NsSystemOrganizationMapper;
import com.newsee.system.entity.NsSystemDepartment;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.service.INsOrganizationService;
import com.newsee.system.vo.NsOrganizationTreeSortVo;
import com.newsee.system.vo.NsSystemOrganizationVo;

/**
 * @author 胡乾亮
 * @ClassName NsOrganizationServiceImpl
 * @Description: 组织树接口实现类
 * @date 2017年11月14日 上午9:47:14
 */
@Service
public class NsOrganizationServiceImpl implements INsOrganizationService {

    @Autowired
    NsSystemOrganizationMapper organizationMapper;
    @Autowired
    NsSystemDepartmentMapper departmentMapper;
    @Autowired
    NsSystemCompanyMapper companyMapper;
    //初始化展开2层
    private static int levelNum = 2;

    @Override
    public NsSystemOrganizationVo getOrganizationTree(Long organizationId, Integer orgType) {
        //根节点
        NsSystemOrganizationVo rootNode = new NsSystemOrganizationVo();
        NsSystemOrganization currentNode = organizationMapper.selectById(organizationId);
        int orglevel = currentNode.getOrganizationLevel();
        int paramlevel = orglevel + levelNum - 1;
        BeanUtils.copyProperties(currentNode, rootNode);
        //获取当前节点及其所有子节点
        Map<String, Object> map = new HashMap<>();
        map.put("organizationPath", "/" + organizationId + "/");
        map.put("paramlevel", paramlevel);
        if (!Objects.isNull(orgType)) {
            map.put("orgType", orgType);
        }
        List<NsSystemOrganization> AllChildNodes = organizationMapper.selectByPath(map);
        List<NsSystemOrganizationVo> AllChildNodeVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(AllChildNodes)) {
            for (NsSystemOrganization childNode : AllChildNodes) {
                NsSystemOrganizationVo childNodeVo = new NsSystemOrganizationVo();
                BeanUtils.copyProperties(childNode, childNodeVo);
                AllChildNodeVos.add(childNodeVo);
            }
        }
        if (!CollectionUtils.isEmpty(AllChildNodeVos)) {
            rootNode.setIsHasChild(true);
            //判断每个子节点是否有子节点
            for (NsSystemOrganizationVo childNodeVo : AllChildNodeVos) {
                Map<String, Object> map1 = new HashMap<>();
                map1.put("organizationPath", "/" + childNodeVo.getOrganizationId() + "/");
                if (!Objects.isNull(orgType)) {
                    map1.put("orgType", orgType);
                }
                int childCount = organizationMapper.selectChildCount(map1);
                if (childCount > 0) {
                    childNodeVo.setIsHasChild(true);
                } else {
                    childNodeVo.setIsHasChild(false);
                }
            }
            
/*            if (!Objects.isNull(orgType)) {
                //节点展示集团级
                if (orgType.intValue()==OrganizationTypeEnum.GROUP.getValue().intValue()) {
                    for (Iterator iterator = AllChildNodeVos.iterator(); iterator.hasNext();) {
                      NsSystemOrganizationVo orgVo = (NsSystemOrganizationVo) iterator.next();
                      if (orgVo.getOrganizationType().intValue()!=OrganizationTypeEnum.GROUP.getValue().intValue()) {
                          AllChildNodeVos.remove(orgVo);
                      }
                    }
                }
                //节点展示公司及公司级别以上
                if (orgType.intValue()==OrganizationTypeEnum.COMPANY.getValue().intValue()) {
                    for (Iterator iterator = AllChildNodeVos.iterator(); iterator.hasNext();) {
                        NsSystemOrganizationVo orgVo = (NsSystemOrganizationVo) iterator.next();
                        if (orgVo.getOrganizationType().intValue()==OrganizationTypeEnum.DEPARTMENT.getValue().intValue()) {
                            AllChildNodeVos.remove(orgVo);
                        }
                     }
                }
           }*/
            //递归生成组织树
            generateOrganizationTree(rootNode, AllChildNodeVos);
        } else {
            rootNode.setIsHasChild(false);
        }
        return rootNode;
    }

    @Override
    public List<NsSystemOrganizationVo> getChildOrganization(Long organizationId, Integer orgType) {
        List<NsSystemOrganizationVo> childNodeVos = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("organizationId", organizationId);
        if (!Objects.isNull(orgType)) {
            map.put("orgType", orgType);
        }
        List<NsSystemOrganization> childNodes = organizationMapper.selectChildsByParentId(map);
        //判断每个节点是否有子节点
        if (!CollectionUtils.isEmpty(childNodes)) {
            for (NsSystemOrganization nsOrg : childNodes) {
                NsSystemOrganizationVo nsOrgVo = new NsSystemOrganizationVo();
                BeanUtils.copyProperties(nsOrg, nsOrgVo);
                Map<String, Object> map1 = new HashMap<>();
                map1.put("organizationPath", "/" + nsOrgVo.getOrganizationId() + "/");
                if (!Objects.isNull(orgType)) {
                    map1.put("orgType", orgType);
                }
                int childCount = organizationMapper.selectChildCount(map1);
                if (childCount > 0) {
                    nsOrgVo.setIsHasChild(true);
                } else {
                    nsOrgVo.setIsHasChild(false);
                }
                nsOrgVo.setChildOrganizations(new ArrayList<>());
                childNodeVos.add(nsOrgVo);
            }
        }

        return childNodeVos;
    }

    @Override
    public List<NsSystemOrganizationVo> listHouseTreeBySearch(Long enterpriseId, Long organizationId, String organizationName) {
        if (CommonUtils.isNullOrBlank(organizationName)) {
            return null;
        }
        //登录人所在公司节点
        NsSystemOrganization rootnode = organizationMapper.selectById(organizationId);
        NsSystemOrganizationVo rootnodeVo = new NsSystemOrganizationVo();
        BeanUtils.copyProperties(rootnode, rootnodeVo);

        //根据组织名字匹配登录人所在公司所有子节点
        List<NsSystemOrganizationVo> list = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("organizationName", organizationName);
        map.put("organizationPath", "/" + organizationId + "/");
        List<NsSystemOrganization> organizationList = organizationMapper.listOrganizationTreeBySearch(map);
        if (!CollectionUtils.isEmpty(organizationList)) {
            organizationList.forEach(organization -> {
                NsSystemOrganizationVo organizationVo = new NsSystemOrganizationVo();
                BeanUtils.copyProperties(organization, organizationVo);
                list.add(organizationVo);
            });
        }
        if (rootnodeVo.getOrganizationName().contains(organizationName)) {
            list.add(rootnodeVo);
        }
        return list;
    }

    @Override
    public NsSystemOrganizationVo detail(Long organizationId) {
        NsSystemOrganizationVo organizationVo = new NsSystemOrganizationVo();
        NsSystemOrganization organization = organizationMapper.selectById(organizationId);
        BeanUtils.copyProperties(organization, organizationVo);
        return organizationVo;
    }


    @Override
    public List<NsSystemOrganizationVo> moreDetail(List<Long> organizationIdList) {
        List<NsSystemOrganizationVo> organizationVos = new ArrayList<>();
        List<NsSystemOrganization> organizations = organizationMapper.selectByIds(organizationIdList);
        for (NsSystemOrganization organization : organizations) {
            NsSystemOrganizationVo organizationVo = new NsSystemOrganizationVo();
            BeanUtils.copyProperties(organization, organizationVo);
            organizationVos.add(organizationVo);
        }
        return organizationVos;
    }

    @Override
    public Map<String, Object> editOrganizationTreeSort(NsOrganizationTreeSortVo treeSortVo, Long userId) {
        Map<String, Object> resultMap = new HashMap<>();
        //当前节点信息
        long organizationId = treeSortVo.getOrganizationId();
        NsSystemOrganization currentOganization = organizationMapper.selectById(organizationId);
        //目的位置父级节点信息
        long organizationParentId = treeSortVo.getOrganizationParentId();
        NsSystemOrganization parentOrganization = organizationMapper.selectById(organizationParentId);
        //验证是否可以移动
        if (currentOganization.getOrganizationType() == OrganizationTypeEnum.GROUP.getValue() &&
                (parentOrganization.getOrganizationType() != OrganizationTypeEnum.GROUP.getValue() &&
                        parentOrganization.getOrganizationType() != OrganizationTypeEnum.COMPANY.getValue())) {
            resultMap.put("result", false);
            resultMap.put("message", "集团不可拖到部门下");
            return resultMap;
        } else if (currentOganization.getOrganizationType() == OrganizationTypeEnum.COMPANY.getValue() &&
                (parentOrganization.getOrganizationType() != OrganizationTypeEnum.GROUP.getValue() &&
                        parentOrganization.getOrganizationType() != OrganizationTypeEnum.COMPANY.getValue())) {
            resultMap.put("result", false);
            resultMap.put("message", "公司不可拖到部门下");
            return resultMap;
        } else if (currentOganization.getOrganizationType() == OrganizationTypeEnum.GROUP.getValue() &&
                currentOganization.getOrganizationType() == OrganizationTypeEnum.COMPANY.getValue()) {
            resultMap.put("result", false);
            resultMap.put("message", "集团不可拖到公司下");
            return resultMap;
        }
        //目的位置序号
        int sort = treeSortVo.getSort();
        //目的位置(本层级)以下的所有组织Id
        List<Long> organizationIds = treeSortVo.getSortOrganizationIds();
        //1.更新当前节点父级Id,路径path,排序字段
        NsSystemOrganization organization = new NsSystemOrganization();
        organization.setOrganizationId(organizationId);
        organization.setOrganizationParentId(organizationParentId);
        organization.setOrganizationOrdercolumn(sort);
        organization.setOrganizationPath(parentOrganization.getOrganizationPath() + parentOrganization.getOrganizationId() + "/");
        organization.setOrganizationLevel(parentOrganization.getOrganizationLevel() + 1);
        organizationMapper.updateByIdSelective(organization);
        //2.更新目的位置以下节点的序号+1
        organizationMapper.updateOrderAddOneByIds(organizationIds);
        //3.判断当前节点是否有子节点，当前节点以及所有子节点的层级level要改变
        int beforLevel = currentOganization.getOrganizationLevel();
        int newLevel = parentOrganization.getOrganizationLevel() + 1;
        int leveNum = beforLevel - newLevel;
        if (Objects.isNull(leveNum)) {
            leveNum = 0;
        }

        //获取当前节点及其所有子节点
        int paramlevel = currentOganization.getOrganizationLevel() + levelNum - 1;
        Map<String, Object> parammap = new HashMap<>();
        parammap.put("organizationPath", "/" + organizationId + "/");
        /*  parammap.put("paramlevel", paramlevel);*/
        List<NsSystemOrganization> allChildNodes = organizationMapper.selectByPath(parammap);
        if (!CollectionUtils.isEmpty(allChildNodes)) {
            /*List<Long> allChildIdList = new ArrayList<>();*/
            for (NsSystemOrganization org : allChildNodes) {
                /* allChildIdList.add(org.getOrganizationId());*/
                String organizationPath = org.getOrganizationPath().replace(currentOganization.getOrganizationPath(), parentOrganization.getOrganizationPath() + parentOrganization.getOrganizationId() + "/");
                org.setOrganizationPath(organizationPath);
                org.setOrganizationLevel(org.getOrganizationLevel() - leveNum);
                organizationMapper.updateByIdSelective(org);
            }
            //organizationMapper.batchUpdatePath(allChildNodes);
            /*Map<String,Object> map = new HashMap<>();
            map.put("leveNum", leveNum);
            map.put("allChildIdList", allChildIdList);
            organizationMapper.updateLevelByIds(map);*/
        }
        resultMap.put("result", true);
        return resultMap;
    }

    private void generateOrganizationTree(NsSystemOrganizationVo rootNodeVo, List<NsSystemOrganizationVo> AllChildNodeVos) {
        List<NsSystemOrganizationVo> childNodeVos = new ArrayList<NsSystemOrganizationVo>();
        for (NsSystemOrganizationVo childNodeVo : AllChildNodeVos) {
            if (rootNodeVo.getOrganizationId().equals(childNodeVo.getOrganizationParentId())) {
                childNodeVos.add(childNodeVo);
                generateOrganizationTree(childNodeVo, AllChildNodeVos);
            }
        }
        rootNodeVo.setChildOrganizations(childNodeVos);
    }

    @Override
    public Long getOrgIdOutOfDep(Long organizationId) {
        Long result = organizationId;
        NsSystemOrganization organization = organizationMapper.selectById(organizationId);
        if (!Objects.isNull(organization)) {
            int orgType = organization.getOrganizationType();
            if (orgType == Constants.ORG_TYPE_DEPARTMENT) {
	          /*  int paramlevel = organization.getOrganizationLevel()+levelNum-1;
	            Map<String, Object> parammap = new HashMap<>();*/
//	            parammap.put("organizationId", organizationId);
	           /* parammap.put("organizationPath", Constants.SEPARATOR_PATH+organizationId+Constants.SEPARATOR_PATH);
	            parammap.put("paramlevel", paramlevel);
	            List<NsSystemOrganization> organizations = organizationMapper.selectByPath(parammap);*/
                String orgPath = organization.getOrganizationPath();
                String[] parentOrgIdArray = orgPath.split("/");
                List<Long> parentOrgIdList = new ArrayList<>();
                if (parentOrgIdArray != null && parentOrgIdArray.length > 0) {
                    for (String orgId : parentOrgIdArray) {
                        if (!StringUtils.isBlank(orgId)) {
                            parentOrgIdList.add(Long.parseLong(orgId));
                        }
                    }
                }
                List<NsSystemOrganization> organizations = organizationMapper.selectByIds(parentOrgIdList);
                List<NsSystemOrganization> organizations_company = new ArrayList<>();
                organizations_company = organizations.stream().filter(o
                        -> o.getOrganizationType() == Constants.ORG_TYPE_COMPANY).collect(Collectors.toList());
                //使用层级对org进行排序
                organizations_company.sort((NsSystemOrganization o1, NsSystemOrganization o2)
                        -> Integer.compare(o1.getOrganizationLevel(), o2.getOrganizationLevel()));
                NsSystemOrganization maxLeveOrg = organizations_company.get(0);
                result = maxLeveOrg.getOrganizationId();
            }
        }
        return result;
    }

    @Override
    public Long getOrgIdGroupLevel(Long organizationId) {
        Long result = organizationId;
        NsSystemOrganization organization = organizationMapper.selectById(organizationId);
        if (!Objects.isNull(organization)) {
            int orgType = organization.getOrganizationType();
            if (orgType != Constants.ORG_TYPE_GROUP) {
                String path = organization.getOrganizationPath();
                String[] orgIdArr = path.split("/");
                List<Long> orgIdList = new ArrayList<>();
                for (int i = 0; i < orgIdArr.length; i++) {
                    if (!StringUtils.isBlank(orgIdArr[i])) {
                        orgIdList.add(Long.parseLong(orgIdArr[i]));
                    }
                }
                result = orgIdList.get(0);
            }
        }
        return result;
    }

    //    @SuppressWarnings("unchecked")
    @Override
    public List<NsSystemOrganizationVo> listAllOrganization(Long enterpriseId, Long organizationId) {
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("organizationName", "");
        map.put("organizationPath", "/" + organizationId + "/");
        List<NsSystemOrganizationVo> organizationVos = new ArrayList<>();
        List<NsSystemOrganization> organizationList = organizationMapper.listOrganizationTreeBySearch(map);
        if (!CollectionUtils.isEmpty(organizationList)) {
            Map<Integer, List<NsSystemOrganization>> orgMap = organizationList.stream().collect(Collectors.groupingBy(NsSystemOrganization::getOrganizationType));
            //获取所有子公司
            List<NsSystemOrganization> companyList = (List<NsSystemOrganization>) orgMap.get(Constants.ORG_TYPE_COMPANY);
            if (!CollectionUtils.isEmpty(companyList)) {
                List<Long> companytIdList = new ArrayList<>();
                companyList.forEach(company -> {
                    companytIdList.add(company.getDepartmentId());
                });
                List<NsSystemCompany> nsSystemCompanies = companyMapper.listById(companytIdList);
                for (NsSystemCompany nsSystemCompany : nsSystemCompanies) {
                    NsSystemOrganizationVo organizationVo = new NsSystemOrganizationVo();
                    BeanUtils.copyProperties(nsSystemCompany, organizationVo);
                    organizationVo.setOrganizationId(nsSystemCompany.getCompanyId());
                    organizationVo.setOrganizationType(Constants.ORG_TYPE_COMPANY);
                    organizationVo.setOrganizationName(nsSystemCompany.getCompanyName());
                    organizationVo.setOrganizationShortName(nsSystemCompany.getCompanyShortName());
                    organizationVos.add(organizationVo);
                }
            }

            //获取所有部门
            List<NsSystemOrganization> departmentList = (List<NsSystemOrganization>) orgMap.get(Constants.ORG_TYPE_DEPARTMENT);
            if (!CollectionUtils.isEmpty(departmentList)) {
                List<Long> departmentIdList = new ArrayList<>();
                departmentList.forEach(department -> {
                    departmentIdList.add(department.getDepartmentId());
                });
                List<NsSystemDepartment> nsSystemDepartments = departmentMapper.listById(departmentIdList);
                for (NsSystemDepartment nsSystemDepartment : nsSystemDepartments) {
                    NsSystemOrganizationVo organizationVo = new NsSystemOrganizationVo();
                    BeanUtils.copyProperties(nsSystemDepartment, organizationVo);
                    organizationVo.setOrganizationId(nsSystemDepartment.getDepartmentId());
                    organizationVo.setOrganizationType(Constants.ORG_TYPE_DEPARTMENT);
                    organizationVo.setOrganizationName(nsSystemDepartment.getDepartmentName());
                    organizationVo.setOrganizationShortName(nsSystemDepartment.getDepartmentShortName());
                    organizationVos.add(organizationVo);
                }
            }
        }
        return organizationVos;
    }

    @Override
    public String getAllParentName(Long organizationId) {
        String prentOrgName = "";
        NsSystemOrganization orginfo = organizationMapper.selectByOrganizationId(organizationId);
        String orgPath = orginfo.getOrganizationPath();
        if (orgPath.equals("/")) {
            return orginfo.getOrganizationName();
        }
        String[] parentOrgIdArray = orgPath.split("/");
        List<Long> parentOrgIdList = new ArrayList<>();
        if (parentOrgIdArray != null && parentOrgIdArray.length > 0) {
            for (String orgId : parentOrgIdArray) {
                if (!StringUtils.isBlank(orgId)) {
                    parentOrgIdList.add(Long.parseLong(orgId));
                }
            }
        }
        List<NsSystemOrganization> organizations = organizationMapper.selectByIds(parentOrgIdList);
        StringBuilder stringBuilder = new StringBuilder();
        for (NsSystemOrganization org : organizations) {
            stringBuilder.append(org.getOrganizationName()).append("-");
        }
        stringBuilder.deleteCharAt(stringBuilder.lastIndexOf("-"));
        prentOrgName = stringBuilder.toString() + "-" + orginfo.getOrganizationName();
        return prentOrgName;
    }

    @Override
    public Map<String, Object> generateDeptCodeOrShotName(Long parentOrgId, String currentOrgName) {
        Map<String, Object> map = new HashMap<>();
        String shortName = "";
        String code = "";
        NsSystemOrganization parentOrg = organizationMapper.selectById(parentOrgId);
        shortName = parentOrg.getOrganizationShortName() + "-" + currentOrgName;
        code = FirstLetterUtil.getFirstLetter(shortName);
        map.put("shortName", shortName);
        map.put("code", code);
        return map;
    }

    @Override
    public List<NsSystemOrganizationVo> listAllChildNode(Long organizationId, Integer orgType) {
        //根节点
        NsSystemOrganizationVo rootNode = new NsSystemOrganizationVo();
        NsSystemOrganization currentNode = organizationMapper.selectById(organizationId);
        BeanUtils.copyProperties(currentNode, rootNode);
        //获取当前节点及其所有子节点
        Map<String, Object> map = new HashMap<>();
        map.put("organizationPath", "/" + organizationId + "/");
        if (!Objects.isNull(orgType)) {
            map.put("orgType", orgType);
        }
        List<NsSystemOrganizationVo> allChildNodeVos = new ArrayList<>();
        allChildNodeVos.add(rootNode);
        List<NsSystemOrganization> AllChildNodes = organizationMapper.selectByPath(map);
        if (!CollectionUtils.isEmpty(AllChildNodes)) {
            for (NsSystemOrganization childNode : AllChildNodes) {
                NsSystemOrganizationVo childNodeVo = new NsSystemOrganizationVo();
                BeanUtils.copyProperties(childNode, childNodeVo);
                allChildNodeVos.add(childNodeVo);
            }
        }
        return allChildNodeVos;
    }

}
