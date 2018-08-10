package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.constant.Constants;
import com.newsee.common.entity.NsSystemCompany;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.FirstLetterUtil;
import com.newsee.common.utils.StringUtils;
import com.newsee.system.dao.NsCoreRoleMapper;
import com.newsee.system.dao.NsSystemCompanyMapper;
import com.newsee.system.dao.NsSystemGroupMapper;
import com.newsee.system.dao.NsSystemOrganizationMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.entity.NsCoreRole;
import com.newsee.system.entity.NsSystemGroup;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.service.INsCompanyService;
import com.newsee.system.vo.NsSystemCompanyVo;

@Service
public class NsCompanyServiceImpl implements INsCompanyService {
    
    @Autowired
    NsSystemCompanyMapper companyMapper;
    @Autowired
    NsSystemOrganizationMapper organizationMapper;
    @Autowired
    NsSystemGroupMapper groupMapper;
    @Autowired
    NsCoreRoleMapper roleMapper;
    @Autowired
    NsSystemUserMapper userMapper;
    
    @Override
    public Boolean add(NsSystemCompanyVo companyVo, Long UserId, String loginUserName) {
        boolean result = false;
        Long organizationId = companyVo.getParentOrganizationId();
        if (!Objects.isNull(organizationId)) {
            
            NsSystemOrganization parentOrg = organizationMapper.selectById(organizationId);
            int orgType = parentOrg.getOrganizationType();
            
            if (orgType == Constants.ORG_TYPE_GROUP) {
                //集团下新建公司
                //2.新建公司
                NsSystemGroup currentGroup = groupMapper.selectById(parentOrg.getGroupId());
                Long enterpriseId = currentGroup.getEnterpriseId();
                Long groupId = currentGroup.getGroupId();
                
                NsSystemCompany company = new NsSystemCompany();
                BeanUtils.copyProperties(companyVo, company);
                company.setEnterpriseId(enterpriseId);
                company.setGroupId(groupId);
                company.setCompanyPath("/");
                company.setCreateUserId(UserId);
                company.setCreateUserName(loginUserName);
                company.setUpdateUserId(UserId);
                company.setUpdateUserName(loginUserName);
                //若公司简称"字段为空， 本节点名称，自动填充到"公司简称"中。
                if (StringUtils.isBlank(company.getCompanyShortName())) {
                    company.setCompanyShortName(company.getCompanyName());
                }
                //若公司"编号"字段为空， 取简称的拼音首字母缩写，自动填充到"公司编号"中。
                if (StringUtils.isBlank(company.getCompanyCode())) {
                    company.setCompanyCode(FirstLetterUtil.getFirstLetter(company.getCompanyShortName()));
                }
                //验证公司简称和编号的唯一性
                NsSystemOrganization organizationCheck = new NsSystemOrganization();
                organizationCheck.setEnterpriseId(enterpriseId);
                organizationCheck.setOrganizationShortName(company.getCompanyShortName());
                organizationCheck.setOrganizationCode(company.getCompanyCode());
                checkOnlyCodeOrName(organizationCheck, company);
                
                companyMapper.insert(company);
                
                //3.同时新建对应组织节点
                NsSystemOrganization organization = new NsSystemOrganization();
                organization.setEnterpriseId(enterpriseId);
                organization.setGroupId(groupId);
                organization.setCompanyId(company.getCompanyId());
                organization.setOrganizationName(company.getCompanyName());
                organization.setOrganizationShortName(company.getCompanyShortName());
                organization.setOrganizationCode(company.getCompanyCode());
                organization.setOrganizationLevel(parentOrg.getOrganizationLevel()+1);
                organization.setOrganizationOrdercolumn(company.getOrderNo());
                organization.setOrganizationEnablestate(Constants.ENABLE_YES);
                organization.setOrganizationType(Constants.ORG_TYPE_COMPANY);
                organization.setOrganizationParentId(parentOrg.getOrganizationId());
                organization.setOrganizationPath(parentOrg.getOrganizationPath()+parentOrg.getOrganizationId()+"/");
                organization.setCreateUserId(UserId);
                organization.setCreateUserName(loginUserName);
                organization.setUpdateUserId(UserId);
                organization.setUpdateUserName(loginUserName);
                organizationMapper.insert(organization);
               //4.TODO 默认创建四个角色
                
                
                result = true;
            }else if (orgType == Constants.ORG_TYPE_COMPANY) {
                //公司下面新增子公司
                
                //2.新增子公司
                NsSystemCompany parentCompany = companyMapper.selectById(parentOrg.getCompanyId());
                Long enterpriseId = parentCompany.getEnterpriseId();
                Long groupId = parentCompany.getGroupId();
                
                NsSystemCompany company = new NsSystemCompany();
                BeanUtils.copyProperties(companyVo, company);
                company.setEnterpriseId(enterpriseId);
                company.setGroupId(groupId);
                company.setCompanyParentId(parentCompany.getCompanyId());
                company.setCompanyPath(parentCompany.getCompanyPath()+parentCompany.getCompanyId()+"/");
                company.setCreateUserId(UserId);
                company.setCreateUserName(loginUserName);
                company.setUpdateUserId(UserId);
                company.setUpdateUserName(loginUserName);
                //若公司简称"字段为空， 取本节点名称，自动填充到"公司简称"中。
                if (StringUtils.isBlank(company.getCompanyShortName())) {
                    company.setCompanyShortName(company.getCompanyName());
                }
                //若公司"编号"字段为空， 取简称的拼音首字母缩写，自动填充到"公司编号"中。
                if (StringUtils.isBlank(company.getCompanyCode())) {
                    company.setCompanyCode(FirstLetterUtil.getFirstLetter(company.getCompanyShortName()));
                }
                //验证公司简称和编号的唯一性
                NsSystemOrganization organizationCheck = new NsSystemOrganization();
                organizationCheck.setEnterpriseId(enterpriseId);
                organizationCheck.setOrganizationShortName(company.getCompanyShortName());
                organizationCheck.setOrganizationCode(company.getCompanyCode());
                checkOnlyCodeOrName(organizationCheck, company);
                companyMapper.insert(company);
                
                //3.同时新建对应组织节点
                NsSystemOrganization organization = new NsSystemOrganization();
                organization.setEnterpriseId(enterpriseId);
                organization.setGroupId(groupId);
                organization.setCompanyId(company.getCompanyId());
                organization.setOrganizationName(company.getCompanyName());
                organization.setOrganizationShortName(company.getCompanyShortName());
                organization.setOrganizationCode(company.getCompanyCode());
                organization.setOrganizationLevel(parentOrg.getOrganizationLevel()+1);
                organization.setOrganizationOrdercolumn(company.getOrderNo());
                organization.setOrganizationEnablestate(Constants.ENABLE_YES);
                organization.setOrganizationType(Constants.ORG_TYPE_COMPANY);
                organization.setOrganizationParentId(parentOrg.getOrganizationId());
                organization.setOrganizationPath(parentOrg.getOrganizationPath()+parentOrg.getOrganizationId()+"/");
                organization.setCreateUserId(UserId);
                organization.setCreateUserName(loginUserName);
                organization.setUpdateUserId(UserId);
                organization.setUpdateUserName(loginUserName);
                organizationMapper.insert(organization);
                //3.TODO 默认创建四个角色
                
                
                result = true;
            }
            
        }
        return result;
    }

    @Override
    public Boolean edit(NsSystemCompanyVo companyVo, Long UserId, String loginUserName) {
        boolean result = false;
        Long organizationId = companyVo.getOrganizationId();
        if (!Objects.isNull(organizationId)) {
            //2.更新公司               
            NsSystemCompany company = new NsSystemCompany();
            BeanUtils.copyProperties(companyVo, company);
            company.setUpdateUserId(UserId);
            company.setUpdateUserName(loginUserName);
            //若公司简称"字段为空， 取本节点名称，自动填充到"公司简称"中。
            if (StringUtils.isBlank(company.getCompanyShortName())) {
                company.setCompanyShortName(company.getCompanyName());
            }
            //若公司"编号"字段为空， 取简称的拼音首字母缩写，自动填充到"公司编号"中。
            if (StringUtils.isBlank(company.getCompanyCode())) {
                company.setCompanyCode(FirstLetterUtil.getFirstLetter(company.getCompanyShortName()));
            }
            //验证组织简称和编号的唯一性
            NsSystemOrganization organizationCheck = new NsSystemOrganization();
            organizationCheck.setEnterpriseId(companyVo.getEnterpriseId());
            organizationCheck.setOrganizationId(organizationId);
            organizationCheck.setOrganizationShortName(company.getCompanyShortName());
            organizationCheck.setOrganizationCode(company.getCompanyCode());
            checkOnlyCodeOrName(organizationCheck, company);
            companyMapper.updateById(company);
            
            //3.更新对应组织节点
            NsSystemOrganization organization = organizationMapper.selectById(organizationId);
            organization.setOrganizationId(companyVo.getOrganizationId());
            organization.setOrganizationName(companyVo.getCompanyName());
            organization.setOrganizationShortName(company.getCompanyShortName());
            organization.setOrganizationCode(company.getCompanyCode());
            organization.setUpdateUserId(UserId);
            organization.setUpdateUserName(loginUserName);
            organization.setOrganizationParentId(companyVo.getParentOrganizationId());
            organizationMapper.updateById(organization);
            
            //同步修改User表中的冗余字典组织称
            NsSystemUser user = new NsSystemUser();
            user.setOrganizationId(organizationId);
            user.setOrganizationName(company.getCompanyName());
            user.setOrganizationShortName(company.getCompanyShortName());
            userMapper.updateOrgNameByOrgId(user);
            
            result = true;
        }
        return result;
    }

    private void checkOnlyCodeOrName(NsSystemOrganization organization, NsSystemCompany company) {
        List<NsSystemOrganization> org1 = organizationMapper.checkOnlyShortName(organization);
        if (!CollectionUtils.isEmpty(org1)) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "组织简称已存在");
        }
        
        List<NsSystemOrganization> org2 = organizationMapper.checkOnlyCode(organization);
        if (!CollectionUtils.isEmpty(org2)) {
            String orgCode = organization.getOrganizationCode();
            if (orgCode.contains("_")) {
                String[] strArray = orgCode.split("_");
                organization.setOrganizationCode(strArray[0]);
                int code_count = organizationMapper.selectCountByOrgCode(organization);
                /*int num = Integer.parseInt(strArray[1]);*/
                company.setCompanyCode(strArray[0]+"_"+(code_count+1));
            }else{
                int code_count = organizationMapper.selectCountByOrgCode(organization);
                company.setCompanyCode(organization.getOrganizationCode()+"_"+(code_count+1));
            }
            /*BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "组织编号已存在");*/
        }
    }

    @Override
    public Boolean delete(Long organizationId, Long companyId, Long UserId, String loginUserName) {
        boolean result = false;
        //公司有下级组织不可删除
        Map<String, Object> map = new HashMap<>();
        map.put("organizationId", organizationId);
        List<NsSystemOrganization> organizations = organizationMapper.selectChildsByParentId(map);
        if (CollectionUtils.isEmpty(organizations)) {
            //公司有员工不可删除
            List<NsCoreRole> roleList = roleMapper.selectByOrganizationId(organizationId);
            if (!CollectionUtils.isEmpty(roleList)) {
                BizException.fail(ResultCodeEnum.SERVER_ERROR, "该公司有角色不能被删除");
            }
            List<NsSystemUser> userList = userMapper.selectByOrganizationId(organizationId);
            if (!CollectionUtils.isEmpty(userList)) {
                BizException.fail(ResultCodeEnum.SERVER_ERROR, "该公司有员工不能被删除");
            }
            //删除公司
            NsSystemCompany company = new NsSystemCompany();
            company.setCompanyId(companyId);
            company.setIsDeleted((int)Constants.DELETE_YES);
            company.setUpdateUserId(UserId);
            companyMapper.updateByIdSelective(company);
            //删除对应的组织节点
            NsSystemOrganization organization = new NsSystemOrganization();
            organization.setOrganizationId(organizationId);
            organization.setIsDeleted((int)Constants.DELETE_YES);
            organization.setUpdateUserId(UserId);
            organization.setUpdateUserName(loginUserName);
            organizationMapper.updateByIdSelective(organization);
            result = true;
        }else{
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该公司有下级组织不能被删除");
        }
        
        
        return result;
    }

    @Override
    public NsSystemCompanyVo detail(Long id) {
        //上级组织名称,自己的组织id
        String prentOrgName = "";
        NsSystemOrganization orginfo = organizationMapper.selectByCompanyId(id);
        String orgPath = orginfo.getOrganizationPath();
        String[] parentOrgIdArray = orgPath.split("/");
        List<Long> parentOrgIdList = new ArrayList<>();
        if (parentOrgIdArray!=null && parentOrgIdArray.length>0) {
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
        prentOrgName = stringBuilder.toString();
        //公司基本信息
        NsSystemCompany company = companyMapper.selectById(id);
        NsSystemCompanyVo companyVo =new NsSystemCompanyVo();
        BeanUtils.copyProperties(company, companyVo);
        NsSystemUser user = userMapper.selectById(companyVo.getCompanyManagerId());
        if (user != null) {
            companyVo.setCompanyManagerName(user.getUserName());
        }
        //组装结果
        companyVo.setParentOrganizationName(prentOrgName);
        companyVo.setParentOrganizationId(orginfo.getOrganizationParentId());
        companyVo.setOrganizationId(orginfo.getOrganizationId());
        return companyVo;
    }
    

}
