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
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.exception.BizException;
import com.newsee.system.dao.NsCoreRoleUserMapper;
import com.newsee.system.dao.NsSystemRoleFunctionOrganizationMapper;
import com.newsee.system.dao.NsSystemUserMapper;
import com.newsee.system.entity.NsCoreRoleUser;
import com.newsee.system.entity.NsSystemRoleFunctionOrganization;
import com.newsee.system.service.INsRoleFuncOrgService;
import com.newsee.system.vo.NsDataSeeScopeVo;
import com.newsee.system.vo.NsSystemRoleFunctionOrganizationVo;

@Service
public class NsRoleFuncOrgServiceImpl implements INsRoleFuncOrgService {
    
    @Autowired
    NsSystemRoleFunctionOrganizationMapper roleFuncOrgMapper;
    @Autowired
    NsSystemUserMapper userMapper;
    @Autowired
    NsCoreRoleUserMapper roleUserMapper;

    @Override
    public Boolean add(NsDataSeeScopeVo dataSeeScope) {
        addDataPermission(dataSeeScope);
        return true;
    }

    

    @Override
    public NsSystemRoleFunctionOrganizationVo detail(Long id) {
        NsSystemRoleFunctionOrganization roleFuncOrg = roleFuncOrgMapper.selectByPrimaryKey(id);
        NsSystemRoleFunctionOrganizationVo roleFuncOrgVo = new NsSystemRoleFunctionOrganizationVo();
        BeanUtils.copyProperties(roleFuncOrg, roleFuncOrgVo);
        return roleFuncOrgVo;
    }

    @Override
    public Boolean edit(NsDataSeeScopeVo dataSeeScope) {
        Long enterpriseId = dataSeeScope.getEnterpriseId();
        String funcId = dataSeeScope.getFuncId();
        String roleid = dataSeeScope.getRoleid();
        BizException.isNull(enterpriseId, "租户ID");
        BizException.isNull(funcId, "功能ID");
        BizException.isNull(roleid, "角色ID");
       
        NsSystemRoleFunctionOrganizationVo rfo = new NsSystemRoleFunctionOrganizationVo();
        rfo.setEnterpriseId(enterpriseId);
        rfo.setRoleid(roleid);
        rfo.setFuncId(funcId);
        List<NsSystemRoleFunctionOrganization> rfoList = roleFuncOrgMapper.selectBySelective(rfo);
        if (!CollectionUtils.isEmpty(rfoList)) {
            //删除该角色原先的数据权限,重新插入
            boolean deleteFlag = deleteDataPermission(enterpriseId, roleid, funcId);
            if (deleteFlag) {
                addDataPermission(dataSeeScope);
            }
        }else{
            addDataPermission(dataSeeScope);
        }
        return true;
    }


    @Override
    public List<NsSystemRoleFunctionOrganizationVo> list(Long userId, String funcId) {
        NsSystemRoleFunctionOrganizationVo roleFuncOrgVo = new NsSystemRoleFunctionOrganizationVo();
        NsSystemUser user = userMapper.selectById(userId);
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", user.getEnterpriseId());
        map.put("organizationId", user.getOrganizationId());
        map.put("userid", user.getUserId());
        List<NsCoreRoleUser> roleUsers= roleUserMapper.selectByUserId(map);
        List<String> roleids = new ArrayList<>();
        roleUsers.forEach(roleUser -> {
            roleids.add(roleUser.getRoleid());
        });
        roleFuncOrgVo.setEnterpriseId(user.getEnterpriseId());
        roleFuncOrgVo.setRoleids(roleids);
        roleFuncOrgVo.setFuncId(funcId);
        List<NsSystemRoleFunctionOrganization> roleFuncOrgList = roleFuncOrgMapper.selectBySelective(roleFuncOrgVo);
        List<NsSystemRoleFunctionOrganizationVo> roleFuncOrgVoList = new ArrayList<>();
        for (NsSystemRoleFunctionOrganization roleFuncOrgTemp : roleFuncOrgList) {
            NsSystemRoleFunctionOrganizationVo roleFuncOrgVoTemp = new NsSystemRoleFunctionOrganizationVo();
            BeanUtils.copyProperties(roleFuncOrgTemp, roleFuncOrgVoTemp);
            roleFuncOrgVoList.add(roleFuncOrgVoTemp);
        }
        return roleFuncOrgVoList;
    }
    
    
    private boolean deleteDataPermission(Long enterpriseId, String roleid, String funcId) {
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("roleid", roleid);
        map.put("funcId", funcId);
        boolean deleteFlag = roleFuncOrgMapper.deleteByRoleId(map);
        return deleteFlag;
    }
    
    private void addDataPermission(NsDataSeeScopeVo dataSeeScope) {
        Long enterpriseId = dataSeeScope.getEnterpriseId();
        String funcId = dataSeeScope.getFuncId();
        String roleid = dataSeeScope.getRoleid();
        Long seeOrgId = dataSeeScope.getSeeOrganizationId();
        List<Long> seeOtherOrgIdList = dataSeeScope.getSeeOtherOrgIdList();
        Long seeUserId = dataSeeScope.getSeeUserId();
        
        BizException.isNull(enterpriseId, "租户ID");
        BizException.isNull(roleid, "角色ID");
        BizException.isNull(funcId, "功能ID");
       
        
        NsSystemRoleFunctionOrganization roleFuncOrg = new NsSystemRoleFunctionOrganization();
        roleFuncOrg.setEnterpriseId(enterpriseId);
        roleFuncOrg.setRoleid(roleid);
        roleFuncOrg.setFuncId(funcId);
        if (dataSeeScope.getSeeScopeType()!=Constants.SEE_OTHER_ORGANIZATION) {
          //本集团、本公司、本部门、本人
            roleFuncOrg.setSeeScopeType(dataSeeScope.getSeeScopeType());
            roleFuncOrgMapper.insertSelective(roleFuncOrg);
        }
        //经管部门
        if (!CollectionUtils.isEmpty(seeOtherOrgIdList)) {
            for (Long seeOtherOrgId : seeOtherOrgIdList) {
                NsSystemRoleFunctionOrganization roleFuncOrgTemp = new NsSystemRoleFunctionOrganization();
                roleFuncOrgTemp.setEnterpriseId(enterpriseId);
                roleFuncOrgTemp.setRoleid(roleid);
                roleFuncOrgTemp.setFuncId(funcId);
                roleFuncOrgTemp.setSeeOrganizationId(seeOtherOrgId);
                roleFuncOrgTemp.setSeeScopeType(Constants.SEE_OTHER_ORGANIZATION);
                roleFuncOrgMapper.insertSelective(roleFuncOrgTemp);
            }
        }
    }

}
