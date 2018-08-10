package com.newsee.system.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.entity.NsSystemCompany;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.FirstLetterUtil;
import com.newsee.common.utils.StringUtils;
import com.newsee.system.dao.NsSystemGroupMapper;
import com.newsee.system.dao.NsSystemOrganizationMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.entity.NsSystemGroup;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.service.INsGroupService;
import com.newsee.system.vo.NsSystemGroupVo;

@Service
public class NsGroupServiceImpl implements INsGroupService {

    @Autowired
    NsSystemGroupMapper groupMapper;
    @Autowired
    NsSystemOrganizationMapper organizationMapper;
    @Autowired
    NsSystemUserMapper userMapper;
    
    @Override
    public Boolean edit(NsSystemGroupVo groupVo, Long loginUserId) {
        boolean result = false;
        Long organizationId = groupVo.getOrganizationId();
        if (!Objects.isNull(organizationId)) {
            //1.TODO 验证集团名称的唯一性
            
            //2.更新集团               
            NsSystemGroup group = new NsSystemGroup();
            BeanUtils.copyProperties(groupVo, group);
            group.setCreateUserId(loginUserId);
            group.setUpdateUserId(loginUserId);
            //若公司简称"字段为空， 取本节点名称，自动填充到"公司简称"中。
            if (StringUtils.isBlank(group.getGroupShortName())) {
                group.setGroupShortName(group.getGroupName());
            }
            //若公司"编号"字段为空， 取简称的拼音首字母缩写，自动填充到"公司编号"中。
            if (StringUtils.isBlank(group.getGroupCode())) {
                group.setGroupCode(FirstLetterUtil.getFirstLetter(group.getGroupShortName()));
            }
            //验证组织简称和编号的唯一性
            NsSystemOrganization organizationCheck = new NsSystemOrganization();
            organizationCheck.setEnterpriseId(group.getEnterpriseId());
            organizationCheck.setOrganizationId(organizationId);
            organizationCheck.setOrganizationShortName(group.getGroupShortName());
            organizationCheck.setOrganizationCode(group.getGroupCode());
            checkOnlyCodeOrName(organizationCheck, group);
            groupMapper.updateById(group);
            
            //3.更新对应组织节点
            NsSystemOrganization organization = new NsSystemOrganization();
            organization.setOrganizationId(groupVo.getOrganizationId());
            organization.setOrganizationName(groupVo.getGroupName());
            organization.setOrganizationShortName(groupVo.getGroupShortName());
            organization.setOrganizationCode(groupVo.getGroupCode());
            organization.setCreateUserId(loginUserId);
            organization.setUpdateUserId(loginUserId);
            organizationMapper.updateByIdSelective(organization);
            
            NsSystemUser user = new NsSystemUser();
            user.setOrganizationId(organizationId);
            user.setOrganizationName(group.getGroupName());
            user.setOrganizationShortName(group.getGroupShortName());
            userMapper.updateOrgNameByOrgId(user);
            
            result = true;
        }
        return result;
    }

    @Override
    public NsSystemGroupVo detail(Long id) {
        NsSystemGroup group = groupMapper.selectById(id);
        NsSystemGroupVo groupVo = new NsSystemGroupVo();
        BeanUtils.copyProperties(group, groupVo);
        return groupVo;
    }
    
    private void checkOnlyCodeOrName(NsSystemOrganization organization, NsSystemGroup group) {
        List<NsSystemOrganization> org1 = organizationMapper.checkOnlyShortName(organization);
        if (!CollectionUtils.isEmpty(org1)) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "组织简称已存在");
        }
        
        List<NsSystemOrganization> org2 = organizationMapper.checkOnlyCode(organization);
        if (!CollectionUtils.isEmpty(org2)) {
            String orgCode = organization.getOrganizationCode();
            if (orgCode.contains("_")) {
                String[] strArray = orgCode.split("_");
                int num = Integer.parseInt(strArray[1]);
                group.setGroupCode(organization.getOrganizationCode()+"_"+(num+1));
            }else{
                group.setGroupCode(organization.getOrganizationCode()+"_"+1);
            }
            /*BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "组织编号已存在");*/
        }
    }

}
