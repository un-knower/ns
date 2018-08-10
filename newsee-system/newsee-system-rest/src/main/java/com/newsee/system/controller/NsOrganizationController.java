package com.newsee.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.constant.Constants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.CommonUtils;
import com.newsee.system.service.INsCompanyService;
import com.newsee.system.service.INsDepartmentService;
import com.newsee.system.service.INsGroupService;
import com.newsee.system.service.INsOrganizationService;
import com.newsee.system.vo.NsOrganizationTreeSortVo;
import com.newsee.system.vo.NsSystemCompanyVo;
import com.newsee.system.vo.NsSystemDepartmentVo;
import com.newsee.system.vo.NsSystemGroupVo;
import com.newsee.system.vo.NsSystemOrganizationVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
@RequestMapping("/organization")
@Api(tags = {"com.newsee.system.controller.NsOrganizationController"}, description = "按钮  REST API，包含所有组织架构的操作方法。")
public class NsOrganizationController {

    @Autowired
    INsOrganizationService organizationService;
    @Autowired
    INsGroupService groupService;
    @Autowired
    INsCompanyService companyService;
    @Autowired
    INsDepartmentService departmentService;

    //------------------组织         ------------------------

    @ApiOperation(value = "获取组织树, orgType:0=获取集团级别的节点，1=获取公司及公司级别以上节点")
    @RequestMapping(value = "/get-organization-tree", method = RequestMethod.GET)
    public RestResult<NsSystemOrganizationVo> getOrganizationTree(@RequestParam(value = "orgType", required = false) Integer orgType) {
        Long organizationId = LoginDataHelper.getOrgId();
        NsSystemOrganizationVo treeNode = organizationService.getOrganizationTree(organizationId, orgType);
        return new RestResult<>(treeNode);
    }


    @ApiOperation(value = "获取组织树for表单")
    @RequestMapping(value = "/get-orgtree-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> getOrgTreeForm() {
        Map<String, Object> map = new HashMap<>();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        List<NsSystemOrganizationVo> organizationVos = new ArrayList<>();
        NsSystemOrganizationVo treeNode = organizationService.getOrganizationTree(organizationId, null);
        organizationVos.add(treeNode);
        map.put("level", 2);
        map.put("organizationVos", organizationVos);
        return new RestResult<>(map);
    }

    @ApiOperation(value = "获取组织详情for表单")
    @RequestMapping(value = "/get-orgDetail-form", method = RequestMethod.POST)
    public RestResult<NsSystemOrganizationVo> getOrgDetailForm(@RequestBody Long organizationId) {
        BizException.isNull(organizationId, "组织ID");
        NsSystemOrganizationVo organizationVo = organizationService.detail(organizationId);
        return new RestResult<>(organizationVo);
    }

    @ApiOperation(value = "获取多个组织详情for表单")
    @RequestMapping(value = "/get-moreOrgDetail-form", method = RequestMethod.POST)
    public RestResult<List<NsSystemOrganizationVo>> getMoreOrgDetailForm(@RequestBody List<Long> organizationIdList) {
        List<NsSystemOrganizationVo> organizationVos = new ArrayList<>();
        if (!CollectionUtils.isEmpty(organizationIdList)) {
            organizationVos = organizationService.moreDetail(organizationIdList);
        }
        return new RestResult<>(organizationVos);
    }

    @ApiOperation(value = "模糊查询组织树节点")
    @RequestMapping(value = "/organization-tree-search", method = RequestMethod.GET)
    public RestResult<List<NsSystemOrganizationVo>> listOrganizationTreeForSearch(@RequestParam("organizationName") String organizationName) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        List<NsSystemOrganizationVo> organizationVoList = organizationService.listHouseTreeBySearch(enterpriseId, organizationId, organizationName);
        return new RestResult<>(organizationVoList);
    }

    @ApiOperation(value = "查询指定节点的组织树")
    @RequestMapping(value = "organization-tree-search-detail", method = RequestMethod.GET)
    public RestResult<NsSystemOrganizationVo> detailOrganizationTreeForSearch(@RequestParam("organizationId") Long organizationId) {
        NsSystemOrganizationVo organizationVo = organizationService.detail(organizationId);
        List<NsSystemOrganizationVo> organizationVoList = organizationService.getChildOrganization(organizationId, null);
        if (!CollectionUtils.isEmpty(organizationVoList)) {
            organizationVo.setIsHasChild(true);
            organizationVo.setChildOrganizations(organizationVoList);
        } else {
            organizationVo.setIsHasChild(false);
        }
        return new RestResult<>(organizationVo);
    }


    @ApiOperation(value = "获取下一层级子节点")
    @RequestMapping(value = "/get-child-organization", method = RequestMethod.GET)
    public RestResult<List<NsSystemOrganizationVo>> getChildOrganization(
            @RequestParam(value = "organizationId") Long organizationId,
            @RequestParam(value = "orgType", required = false) Integer orgType) {
        BizException.isNull(organizationId, "对应的组织ID");
        List<NsSystemOrganizationVo> chidOrg = organizationService.getChildOrganization(organizationId, orgType);
        return new RestResult<>(chidOrg);
    }

    @ApiOperation(value = "获取下一层级子节点for表单")
    @RequestMapping(value = "/get-child-organization-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> getChildOrganizationForm(@RequestParam(value = "id") Long organizationId) {
        BizException.isNull(organizationId, "对应的组织ID");
        List<NsSystemOrganizationVo> chidOrgs = organizationService.getChildOrganization(organizationId, null);
        Map<String, Object> map = new HashMap<>();
        map.put("level", 1);
        map.put("organizationVos", chidOrgs);
        return new RestResult<>(map);
    }

    @ApiOperation(value = "删除组织")
    @RequestMapping(value = "/delete-organization", method = RequestMethod.POST)
    public RestResult<Boolean> deleteDepartment(@RequestParam(value = "organizationId") Long organizationId) {
        BizException.isNull(organizationId, "对应的组织ID");
        boolean result = false;
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        NsSystemOrganizationVo organizationVo = organizationService.detail(organizationId);
        int orgType = organizationVo.getOrganizationType();

        if (orgType == Constants.ORG_TYPE_COMPANY) {
            result = companyService.delete(organizationId, organizationVo.getCompanyId(), userId, userName);
        } else if (orgType == Constants.ORG_TYPE_DEPARTMENT) {
            result = departmentService.delete(organizationId, organizationVo.getDepartmentId(), userId, userName);
        }
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "更改组织节点顺序")
    @RequestMapping(value = "/edit-node-sort", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> editNodeSort(@RequestParam("organizationId") Long organizationId,
                                                        @RequestParam("organizationParentId") Long organizationParentId,
                                                        @RequestParam("sort") Integer sort,
                                                        @RequestBody List<Long> sortOrganizationIds) {
        BizException.isNull(organizationId, "对应的组织ID");
        BizException.isNull(organizationParentId, "对应的父组织ID");
        BizException.isNull(sort, "对应的组织排序编号");
        Long userId = LoginDataHelper.getUserId();
        NsOrganizationTreeSortVo treeSortVo = new NsOrganizationTreeSortVo();
        treeSortVo.setOrganizationId(organizationId);
        treeSortVo.setOrganizationParentId(organizationParentId);
        treeSortVo.setSort(sort);
        treeSortVo.setSortOrganizationIds(sortOrganizationIds);

        Map<String, Object> result = organizationService.editOrganizationTreeSort(treeSortVo, userId);
        return new RestResult<>(result);
    }

    @ApiOperation(value = "组织详情")
    @RequestMapping(value = "/detail-organization", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailOrganization(@RequestParam(value = "organizationId") Long organizationId) {
        Map<String, Object> map = new HashMap<>();
        NsSystemOrganizationVo organizationVo = organizationService.detail(organizationId);
        int orgType = organizationVo.getOrganizationType();
        if (orgType == Constants.ORG_TYPE_GROUP) {
            NsSystemGroupVo group = groupService.detail(organizationVo.getGroupId());
            group.setOrganizationId(organizationId);
            map.put("group", group);
        } else if (orgType == Constants.ORG_TYPE_COMPANY) {
            NsSystemCompanyVo company = companyService.detail(organizationVo.getCompanyId());
            map.put("company", company);
        } else if (orgType == Constants.ORG_TYPE_DEPARTMENT) {
            NsSystemDepartmentVo department = departmentService.detail(organizationVo.getDepartmentId());
            map.put("department", department);
        }
        return new RestResult<>(map);
    }

    @ApiOperation(value = "获取员工的组织ID(除部门之外)")
    @RequestMapping(value = "/orgid-company-level", method = RequestMethod.GET)
    public RestResult<Long> getOrgIdCompanyLevel(@RequestParam(value = "organizationId") Long organizationId) {
        Long orgId = organizationService.getOrgIdOutOfDep(organizationId);
        return new RestResult<>(orgId);
    }


    @ApiOperation(value = "获取员工的集团组织ID")
    @RequestMapping(value = "/orgid-Group-level", method = RequestMethod.GET)
    public RestResult<Long> getGroupOrgIdCompanyLevel(@RequestParam(value = "organizationId") Long organizationId) {
        Long orgId = organizationService.getOrgIdGroupLevel(organizationId);
        return new RestResult<>(orgId);
    }


    //------------------组织：集团------------------------

    @ApiOperation(value = "编辑集团")
    @RequestMapping(value = "/edit-group", method = RequestMethod.POST)
    public RestResult<Boolean> editGroup(@RequestBody NsSystemGroupVo groupVo) {
        BizException.isNull(groupVo.getOrganizationId(), "对应的组织ID");
        Long userId = LoginDataHelper.getUserId();
        boolean result = groupService.edit(groupVo, userId);
        return new RestResult<Boolean>(result);
    }

    //------------------组织：公司------------------------

    @ApiOperation(value = "新建公司")
    @RequestMapping(value = "/add-company", method = RequestMethod.POST)
    public RestResult<Boolean> addCompany(@RequestBody NsSystemCompanyVo companyVo) {
        BizException.isNull(companyVo.getParentOrganizationId(), "上级组织ID");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        boolean result = companyService.add(companyVo, userId, userName);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑公司")
    @RequestMapping(value = "/edit-company", method = RequestMethod.POST)
    public RestResult<Boolean> editCompany(@RequestBody NsSystemCompanyVo companyVo) {
        BizException.isNull(companyVo.getOrganizationId(), "对应的组织ID");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        boolean result = companyService.edit(companyVo, userId, userName);
        return new RestResult<Boolean>(result);
    }

    //------------------组织：部门------------------------

    @ApiOperation(value = "新建部门")
    @RequestMapping(value = "/add-department", method = RequestMethod.POST)
    public RestResult<Boolean> addDepartment(@RequestBody NsSystemDepartmentVo departmentVo) {
        BizException.isNull(departmentVo.getParentOrganizationId(), "上级组织ID");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        boolean result = departmentService.add(departmentVo, userId, userName);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑部门")
    @RequestMapping(value = "/edit-department", method = RequestMethod.POST)
    public RestResult<Boolean> editDepartment(@RequestBody NsSystemDepartmentVo departmentVo) {
        BizException.isNull(departmentVo.getOrganizationId(), "对应的组织ID");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        boolean result = departmentService.edit(departmentVo, userId, userName);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "获取用户所有组织")
    @RequestMapping(value = "/list-all-organization", method = RequestMethod.GET)
    public RestResult<List<NsSystemOrganizationVo>> listAllOrganization(@RequestParam(value = "enterpriseId") Long enterpriseId, @RequestParam(value = "organizationId") Long organizationId) {
        if (CommonUtils.isObjectEmpty(enterpriseId)) {
            enterpriseId = LoginDataHelper.getEnterpriseId();
        }
        if (CommonUtils.isObjectEmpty(organizationId)) {
            organizationId = LoginDataHelper.getCompanyLevelOrgId();
        }
        List<NsSystemOrganizationVo> organizationVos = organizationService.listAllOrganization(enterpriseId, organizationId);
        return new RestResult<>(organizationVos);
    }

    @ApiOperation(value = "获取当前组织的所有父级名称")
    @RequestMapping(value = "/get-all-parent-name", method = RequestMethod.GET)
    public RestResult<String> getAllParentName(@RequestParam(value = "organizationId") Long organizationId) {
        String allParentName = "";
        allParentName = organizationService.getAllParentName(organizationId);
        return new RestResult<>(allParentName);
    }


    @ApiOperation(value = "自动生成部门公司、部门编号")
    @RequestMapping(value = "/generate-Dept-code-or-shotName", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> generateDeptCodeOrShotName(
            @RequestParam(value = "parentOrgId") Long parentOrgId,
            @RequestParam(value = "currentOrgName") String currentOrgName) {
        BizException.isNull(parentOrgId, "父组织ID");
        BizException.isNull(currentOrgName, "当前组织名称");
        Map<String, Object> CodeOrShotName = organizationService.generateDeptCodeOrShotName(parentOrgId, currentOrgName);
        return new RestResult<>(CodeOrShotName);
    }

    @ApiOperation(value = "获取所有子节点(包含当前节点)")
    @RequestMapping(value = "/list-all-child")
    public RestResult<List<NsSystemOrganizationVo>> listAllChildNode(
            @ApiParam(value = "组织Id") @RequestParam(value = "organizationId") Long organizationId,
            @ApiParam(value = "orgType:0=获取集团级别的节点，1=获取公司及公司级别以上节点") @RequestParam(value = "orgType", required = false) Integer orgType) {
        if (organizationId == null) {
            organizationId = LoginDataHelper.getOrgId();
        }
        List<NsSystemOrganizationVo> list = organizationService.listAllChildNode(organizationId, orgType);
        return new RestResult<>(list);
    }
}
