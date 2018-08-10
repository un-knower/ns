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
import com.newsee.system.dao.NsSystemDepartmentMapper;
import com.newsee.system.dao.NsSystemGroupMapper;
import com.newsee.system.dao.NsSystemOrganizationMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.entity.NsCoreRole;
import com.newsee.system.entity.NsSystemDepartment;
import com.newsee.system.entity.NsSystemGroup;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.service.INsDepartmentService;
import com.newsee.system.vo.NsSystemDepartmentVo;
@Service
public class NsDepartmentServiceImpl implements INsDepartmentService {
    
    @Autowired
    NsSystemCompanyMapper companyMapper;
    @Autowired
    NsSystemOrganizationMapper organizationMapper;
    @Autowired
    NsSystemGroupMapper groupMapper;
    @Autowired
    NsSystemDepartmentMapper departmentMapper;
    @Autowired
    NsCoreRoleMapper roleMapper;
    @Autowired
    NsSystemUserMapper userMapper;
    
    @Override
    public Boolean add(NsSystemDepartmentVo departmentVo, Long loginUserId, String loginUserName) {
        boolean result = false;
        Long organizationId = departmentVo.getParentOrganizationId();
        if (!Objects.isNull(organizationId)) {
            
            NsSystemOrganization parentOrg = organizationMapper.selectById(organizationId);
            int orgType = parentOrg.getOrganizationType();
            
            if (orgType == Constants.ORG_TYPE_GROUP) {
                //集团下新建部门
                
                NsSystemGroup currentGroup = groupMapper.selectById(parentOrg.getGroupId());
                Long enterpriseId = currentGroup.getEnterpriseId();
                Long groupId = currentGroup.getGroupId();
                
                NsSystemDepartment department = new NsSystemDepartment();
                BeanUtils.copyProperties(departmentVo, department);
                department.setEnterpriseId(enterpriseId);
                department.setGroupId(groupId);
                department.setDepartmentPath("/");
                department.setCreateUserId(loginUserId);
                department.setCreateUserName(loginUserName);
                department.setUpdateUserId(loginUserId);
                department.setUpdateUserName(loginUserName);
                //若部门简称"字段为空， 取本节点名称，自动填充到"部门简称"中。
                if (StringUtils.isBlank(department.getDepartmentShortName())) {
                    department.setDepartmentShortName(department.getDepartmentName());
                }
                //若部门"编号"字段为空， 取简称的拼音首字母缩写，自动填充到"部门编号"中。
                if (StringUtils.isBlank(department.getDepartmentCode())) {
                    department.setDepartmentCode(FirstLetterUtil.getFirstLetter(department.getDepartmentShortName()));
                }
                //验证组织简称和编号的唯一性
                NsSystemOrganization organizationCheck = new NsSystemOrganization();
                organizationCheck.setEnterpriseId(enterpriseId);
                organizationCheck.setOrganizationShortName(department.getDepartmentShortName());
                organizationCheck.setOrganizationCode(department.getDepartmentCode());
                checkOnlyCodeOrName(organizationCheck, department);
                departmentMapper.insert(department);
                
                //3.同时新建对应组织节点
                NsSystemOrganization organization = new NsSystemOrganization();
                organization.setEnterpriseId(enterpriseId);
                organization.setGroupId(groupId);
                organization.setDepartmentId(department.getDepartmentId());
                organization.setOrganizationName(department.getDepartmentName());
                organization.setOrganizationShortName(department.getDepartmentShortName());
                organization.setOrganizationCode(department.getDepartmentCode());
                organization.setOrganizationLevel(parentOrg.getOrganizationLevel()+1);
                organization.setOrganizationOrdercolumn(department.getOrderNo());
                organization.setOrganizationEnablestate(Constants.ENABLE_YES);
                organization.setOrganizationType(Constants.ORG_TYPE_DEPARTMENT);
                organization.setOrganizationParentId(parentOrg.getOrganizationId());
                organization.setOrganizationPath(parentOrg.getOrganizationPath()+parentOrg.getOrganizationId()+"/");
                organization.setCreateUserId(loginUserId);
                organization.setUpdateUserId(loginUserId);
                organizationMapper.insert(organization);
                result = true;
            }else if (orgType == Constants.ORG_TYPE_COMPANY) {
                //公司下面新增部门
                
                //2.新增部门
                NsSystemCompany currentCompany = companyMapper.selectById(parentOrg.getCompanyId());
                Long enterpriseId = currentCompany.getEnterpriseId();
                Long groupId = currentCompany.getGroupId();
                
                NsSystemDepartment department = new NsSystemDepartment();
                BeanUtils.copyProperties(departmentVo, department);
                department.setEnterpriseId(enterpriseId);
                department.setGroupId(groupId);
                department.setCompanyId(currentCompany.getCompanyId());
                department.setDepartmentPath("/");
                department.setCreateUserId(loginUserId);
                department.setUpdateUserId(loginUserId);
                //若部门简称"字段为空， 取本节点名称，自动填充到"部门简称"中。
                if (StringUtils.isBlank(department.getDepartmentShortName())) {
                    department.setDepartmentShortName(department.getDepartmentName());
                }
                //若部门"编号"字段为空， 取简称的拼音首字母缩写，自动填充到"部门编号"中。
                if (StringUtils.isBlank(department.getDepartmentCode())) {
                    department.setDepartmentCode(FirstLetterUtil.getFirstLetter(department.getDepartmentShortName()));
                }
                //验证组织简称和编号的唯一性
                NsSystemOrganization organizationCheck = new NsSystemOrganization();
                organizationCheck.setEnterpriseId(enterpriseId);
                organizationCheck.setOrganizationShortName(department.getDepartmentShortName());
                organizationCheck.setOrganizationCode(department.getDepartmentCode());
                checkOnlyCodeOrName(organizationCheck, department);
                departmentMapper.insert(department);
                
                //3.同时新建对应组织节点
                NsSystemOrganization organization = new NsSystemOrganization();
                organization.setEnterpriseId(enterpriseId);
                organization.setGroupId(groupId);
                organization.setCompanyId(currentCompany.getCompanyId());
                organization.setDepartmentId(department.getDepartmentId());
                organization.setOrganizationName(department.getDepartmentName());
                organization.setOrganizationShortName(department.getDepartmentShortName());
                organization.setOrganizationCode(department.getDepartmentCode());
                organization.setOrganizationLevel(parentOrg.getOrganizationLevel()+1);
                organization.setOrganizationOrdercolumn(department.getOrderNo());
                organization.setOrganizationEnablestate(Constants.ENABLE_YES);
                organization.setOrganizationType(Constants.ORG_TYPE_DEPARTMENT);
                organization.setOrganizationParentId(parentOrg.getOrganizationId());
                organization.setOrganizationPath(parentOrg.getOrganizationPath()+parentOrg.getOrganizationId()+"/");
                organization.setCreateUserId(loginUserId);
                organization.setUpdateUserId(loginUserId);
                organizationMapper.insert(organization);
                result = true;
            }else if (orgType == Constants.ORG_TYPE_DEPARTMENT) {
                //部门下面新增子部门
                
                //2.新增部门
                NsSystemDepartment parentDepartment = departmentMapper.selectById(parentOrg.getDepartmentId());
                Long enterpriseId = parentDepartment.getEnterpriseId();
                Long groupId = parentDepartment.getGroupId();
                Long companyId = parentDepartment.getCompanyId();
                
                NsSystemDepartment department = new NsSystemDepartment();
                BeanUtils.copyProperties(departmentVo, department);
                department.setEnterpriseId(enterpriseId);
                department.setGroupId(groupId);
                department.setCompanyId(companyId);
                department.setDepartmentParentId(parentDepartment.getDepartmentId());
                department.setDepartmentPath(parentDepartment.getDepartmentPath()+parentDepartment.getDepartmentId()+"/");
                department.setCreateUserId(loginUserId);
                department.setUpdateUserId(loginUserId);
                //若部门简称"字段为空， 取本节点名称，自动填充到"部门简称"中。
                if (StringUtils.isBlank(department.getDepartmentShortName())) {
                    department.setDepartmentShortName(department.getDepartmentName());
                }
                //若部门"编号"字段为空， 取简称的拼音首字母缩写，自动填充到"部门编号"中。
                if (StringUtils.isBlank(department.getDepartmentCode())) {
                    department.setDepartmentCode(FirstLetterUtil.getFirstLetter(department.getDepartmentShortName()));
                }
                //验证组织简称和编号的唯一性
                NsSystemOrganization organizationCheck = new NsSystemOrganization();
                organizationCheck.setEnterpriseId(enterpriseId);
                organizationCheck.setOrganizationShortName(department.getDepartmentShortName());
                organizationCheck.setOrganizationCode(department.getDepartmentCode());
                checkOnlyCodeOrName(organizationCheck, department);
                departmentMapper.insert(department);
                
                //3.同时新建对应组织节点
                NsSystemOrganization organization = new NsSystemOrganization();
                organization.setEnterpriseId(enterpriseId);
                organization.setGroupId(groupId);
                organization.setCompanyId(companyId);
                organization.setDepartmentId(department.getDepartmentId());
                organization.setOrganizationName(department.getDepartmentName());
                organization.setOrganizationShortName(department.getDepartmentShortName());
                organization.setOrganizationCode(department.getDepartmentCode());
                organization.setOrganizationLevel(parentOrg.getOrganizationLevel()+1);
                organization.setOrganizationOrdercolumn(department.getOrderNo());
                organization.setOrganizationEnablestate(Constants.ENABLE_YES);
                organization.setOrganizationType(Constants.ORG_TYPE_DEPARTMENT);
                organization.setOrganizationParentId(parentOrg.getOrganizationId());
                organization.setOrganizationPath(parentOrg.getOrganizationPath()+parentOrg.getOrganizationId()+"/");
                organization.setCreateUserId(loginUserId);
                organization.setUpdateUserId(loginUserId);
                organizationMapper.insert(organization);
                result = true;
            }
            
        }
        return result;
    }

    private void checkOnlyCodeOrName(NsSystemOrganization organization, NsSystemDepartment department) {
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
           /*     String lastStr = strArray[strArray.length-1];
                if (lastStr.matches("^[0-9]*$")) {
                    int num = Integer.parseInt(lastStr);
                    department.setDepartmentCode(organization.getOrganizationCode().substring(0, organization.getOrganizationCode().lastIndexOf("_"))+"_"+(num+1));
                }else{*/
                    department.setDepartmentCode(strArray[0]+"_"+(code_count+1));
                /*}*/
            }else{
                int code_count = organizationMapper.selectCountByOrgCode(organization);
                department.setDepartmentCode(organization.getOrganizationCode()+"_"+(code_count+1));
            }
            /*BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "组织编号已存在");*/
        }
    }

    @Override
    public Boolean edit(NsSystemDepartmentVo departmentVo, Long loginUserId, String loginUserName) {
        boolean result = false;
        Long organizationId = departmentVo.getOrganizationId();
        if (!Objects.isNull(organizationId)) {
            //2.更新部门
            NsSystemDepartment department = new NsSystemDepartment();
            BeanUtils.copyProperties(departmentVo, department);
            department.setUpdateUserId(loginUserId);
            department.setUpdateUserName(loginUserName);
            //若部门简称"字段为空， 取本节点名称，自动填充到"部门简称"中。
            if (StringUtils.isBlank(department.getDepartmentShortName())) {
                department.setDepartmentShortName(department.getDepartmentName());
            }
            //若部门"编号"字段为空， 取简称的拼音首字母缩写，自动填充到"部门编号"中。
            if (StringUtils.isBlank(department.getDepartmentCode())) {
                department.setDepartmentCode(FirstLetterUtil.getFirstLetter(department.getDepartmentShortName()));
            }
            //验证组织简称和编号的唯一性
            NsSystemOrganization organizationCheck = new NsSystemOrganization();
            organizationCheck.setEnterpriseId(departmentVo.getEnterpriseId());
            organizationCheck.setOrganizationId(organizationId);
            organizationCheck.setOrganizationShortName(department.getDepartmentShortName());
            organizationCheck.setOrganizationCode(department.getDepartmentCode());
            checkOnlyCodeOrName(organizationCheck, department);
            departmentMapper.updateById(department);
            
            //3.同时更新对应的组织节点
            NsSystemOrganization organization = organizationMapper.selectById(departmentVo.getOrganizationId());
            organization.setOrganizationId(organizationId);
            organization.setOrganizationName(department.getDepartmentName());
            organization.setOrganizationShortName(department.getDepartmentShortName());
            organization.setOrganizationCode(department.getDepartmentCode());
            organization.setUpdateUserId(loginUserId);
            organization.setUpdateUserName(loginUserName);
            organization.setOrganizationParentId(departmentVo.getParentOrganizationId());
            organizationMapper.updateById(organization);
            
            //同步修改User表中的冗余字典组织称
            NsSystemUser user = new NsSystemUser();
            user.setOrganizationId(organizationId);
            user.setOrganizationName(department.getDepartmentName());
            user.setOrganizationShortName(department.getDepartmentShortName());
            userMapper.updateOrgNameByOrgId(user);
            result = true;
        }
        return result;
    }

    @Override
    public Boolean delete(Long organizationId, Long departmentId, Long loginUserId, String loginUserName) {
        boolean result = false;
        //部门有下级组织不可删除
        Map<String, Object> map = new HashMap<>();
        map.put("organizationId", organizationId);
        List<NsSystemOrganization> organizations = organizationMapper.selectChildsByParentId(map);
        if (CollectionUtils.isEmpty(organizations)) {
            // 部门有员工不可删除
            List<NsCoreRole> roleList = roleMapper.selectByOrganizationId(organizationId);
            if (!CollectionUtils.isEmpty(roleList)) {
                BizException.fail(ResultCodeEnum.SERVER_ERROR, "该部门有角色不能被删除");
            }
            List<NsSystemUser> userList = userMapper.selectByOrganizationId(organizationId);
            if (!CollectionUtils.isEmpty(userList)) {
                BizException.fail(ResultCodeEnum.SERVER_ERROR, "该部门有员工不能被删除");
            }
            //删除部门
            NsSystemDepartment department = new NsSystemDepartment();
            department.setDepartmentId(departmentId);
            department.setIsDeleted((int)Constants.DELETE_YES);
            department.setUpdateUserId(loginUserId);
            department.setUpdateUserName(loginUserName);
            departmentMapper.updateByIdSelective(department);
            //删除对应的组织节点
            NsSystemOrganization organization = new NsSystemOrganization();
            organization.setOrganizationId(organizationId);
            organization.setIsDeleted((int)Constants.DELETE_YES);
            organization.setUpdateUserId(loginUserId);
            organization.setUpdateUserName(loginUserName);
            organizationMapper.updateByIdSelective(organization);
            result = true;
        }else{
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该部门有下级组织不能被删除");
        }
        return result;
    }

    @Override
    public NsSystemDepartmentVo detail(Long id) {
        //上级组织名称,自己的组织id
        String prentOrgName = "";
        NsSystemOrganization orginfo = organizationMapper.selectByDepartmentId(id);
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
        //基本信息
        NsSystemDepartment department = departmentMapper.selectById(id);
        NsSystemDepartmentVo departmentVo = new NsSystemDepartmentVo();
        BeanUtils.copyProperties(department, departmentVo);
        NsSystemUser user = userMapper.selectById(departmentVo.getDepartmentManagerId());
        if (user != null) {
            departmentVo.setDepartmentManagerName(user.getUserName());
        }
        //返回结果
        departmentVo.setParentOrganizationName(prentOrgName);
        departmentVo.setParentOrganizationId(orginfo.getOrganizationParentId());
        departmentVo.setOrganizationId(orginfo.getOrganizationId());
        return departmentVo;
    }

}
