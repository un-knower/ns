/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.controller;

import com.github.pagehelper.PageInfo;
import com.newsee.charge.entity.ChargeChargeItem;
import com.newsee.charge.entity.ChargeChargeItemType;
import com.newsee.charge.service.IItemService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.ItemTypeVo;
import com.newsee.charge.vo.ItemVo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

@RestController
@RequestMapping("/item")
@Api(tags = {"com.newsee.charge.controller.ItemController"}, description = "收费科目列表页面操作 REST API，包含收费科目页面的所有操作方法。")
public class ItemController {

    @Autowired
    private IItemService itemService;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    RedisUtil redisUtil;


    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm() {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.ITEM_LIST);
        String interpreter = LoginDataHelper.getFieldInterpreter();
        String formOperateType = LoginDataHelper.getFormOperateType();
        Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
        LoginCommonDataVo commonVo = new LoginCommonDataVo();
        commonVo.setOrganizationId(organizationId);
        commonVo.setGroupLevelOrgId(groupLevelOrgId);
        commonVo.setEnterpriseId(enterpriseId);
        commonVo.setFuncId(funcId);
        commonVo.setInterpreter(interpreter);
        commonVo.setFormOperateType(formOperateType);
        RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
        Map<String, Object> resultData = result.getResultData();
        //检查表单中是否有表格项目，并且做相应处理
        List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
        resultData.put(FormConstants.FORM_FIELDS, formFields);
        return result;
    }


    @ApiOperation(value = "收费科目详情获取")
    @RequestMapping(value = "/detail-item", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailItem(@ApiParam(value = "收费科目ID") @RequestParam(value = "id") Long id)
            throws IllegalArgumentException,
            IllegalAccessException,
            InvocationTargetException,
            IntrospectionException {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.ITEM_LIST);
        String interpreter = LoginDataHelper.getFieldInterpreter();
        String formOperateType = LoginDataHelper.getFormOperateType();
        Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
        LoginCommonDataVo commonVo = new LoginCommonDataVo();
        commonVo.setOrganizationId(organizationId);
        commonVo.setGroupLevelOrgId(groupLevelOrgId);
        commonVo.setEnterpriseId(enterpriseId);
        commonVo.setFuncId(funcId);
        commonVo.setInterpreter(interpreter);
        commonVo.setFormOperateType(formOperateType);
        RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
        Map<String, Object> resultData = result.getResultData();
        List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
        resultData.put(FormConstants.FORM_FIELDS, formFields);
        ItemVo vo = itemService.detail(id);
        String chargeItemType = vo.getChargeItemType();
        String chargeItemClass = vo.getChargeItemClass();
        vo.setChargeItemType("0".equals(chargeItemType) ? "" : chargeItemType);
        vo.setChargeItemClass("0".equals(chargeItemClass) ? "" : chargeItemClass);
        resultData.put(FormConstants.FORM_MODEL_DATA, vo);
        result = new RestResult<>(resultData);
        return result;
    }

    @ApiOperation(value = "编辑收费科目")
    @RequestMapping(value = "/edit-item", method = RequestMethod.POST)
    public RestResult<Boolean> editItem(@ApiParam(value = "收费科目详情") @RequestBody ItemVo vo) {
        //编辑收费科目详情信息
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        //检查收费科目code是否存在
        boolean isExists = itemService.checkItemCodeIsExist(vo.getChargeItemCode(), vo.getId(), enterpriseId);
        if (isExists) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该收费科目编码已存在。");
        }
        vo.setEnterpriseId(enterpriseId);
        vo.setUpdateTime(new Date());
        vo.setUpdateUserId(LoginDataHelper.getUserId());
        vo.setUpdateUserName(LoginDataHelper.getUserName());
        boolean result = itemService.edit(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "删除收费科目")
    @RequestMapping(value = "/delete-item")
    public RestResult<Object> deleteItem(@ApiParam(value = "收费科目ID") @RequestParam("id") Long id) {
        List<ChargeChargeItem> items = itemService.findChildSubject(id);
        if (items != null && items.size() > 0) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "存在子科目,不允许删除!");
        }
        //删除收费科目详情信息
        boolean result = itemService.delete(id);
        return new RestResult<Object>(result);
    }

    @ApiOperation(value = "批量清空税率")
    @RequestMapping(value = "/clear-taxRate")
    public RestResult<Object> clearTaxRate(@ApiParam(value = "收费科目ID") @RequestBody List<Long> ids) {
        List<Long> id = new ArrayList<>();
        ids.forEach(e -> {
            id.add(e);
        });
        Integer result = itemService.clearTaxRate(id);
        if (result != ids.size()) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "选择" + ids.size() + "条," + "成功" + (result) + "条," + "失败" + (ids.size() - result) + "条");
        }
        return new RestResult<Object>(result);
    }

    @ApiOperation(value = "批量删除收费科目")
    @RequestMapping(value = "/delete-item-batch", method = RequestMethod.POST)
    public RestResult<Object> deleteItemBatch(@ApiParam(value = "收费科目ID") @RequestBody List<Long> ids) {
        //删除收费科目详情信息
        List<Long> exist = new ArrayList<Long>();
        int size = ids.size();
        List<Long> id = new ArrayList<>();
        ids.forEach(e -> {
            List<ChargeChargeItem> items = itemService.findChildSubject(e);
            if (items != null && items.size() > 0) {
                exist.add(e);
            } else {
                id.add(e);
            }
        });
        if (size == 1 && exist.size() > 0) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "存在子科目,不允许删除!");
        }

        Integer result = itemService.deleteBatch(id);

        if (exist.size() > 0) {
            //BizException.fail(ResultCodeEnum.SERVER_ERROR, "存在子科目,不允许删除!");
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "选择" + size + "条," + "成功" + (result) + "条," + "失败" + (size - result) + "条");
        }

        if (result != ids.size()) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "选择" + size + "条," + "成功" + (result) + "条," + "失败" + (size - result) + "条");
        }
        return new RestResult<Object>(result);
    }


    @ApiOperation(value = "新增收费科目分类")
    @RequestMapping(value = "/add-item-type", method = RequestMethod.POST)
    public RestResult<Boolean> addItemType(@ApiParam(value = "收费科目详情") @RequestBody ItemTypeVo vo) {
        //编辑收费科目详情信息
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        //检查code是否存在
        boolean isExists = itemService.checkItemTypeCodeIsExist(vo.getChargeItemTypeCode(), null, enterpriseId);
        if (isExists) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该收费科目编码已存在。");
        }
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        vo.setEnterpriseId(enterpriseId);
        Date now = new Date();
        vo.setCreateUserId(userId);
        vo.setCreateUserName(userName);
        vo.setCreateTime(now);
        vo.setUpdateUserId(userId);
        vo.setUpdateUserName(userName);
        vo.setUpdateTime(now);
        boolean result = itemService.addItemType(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "编辑收费科目分类")
    @RequestMapping(value = "/edit-item-type", method = RequestMethod.POST)
    public RestResult<Boolean> editItemType(@ApiParam(value = "收费科目详情") @RequestBody ItemTypeVo vo) {
        //编辑收费科目详情信息
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        //检查code是否存在
        boolean isExists = itemService.checkItemTypeCodeIsExist(vo.getChargeItemTypeCode(), vo.getId(), enterpriseId);
        if (isExists) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该收费科目分类编码已存在。");
        }
        vo.setEnterpriseId(enterpriseId);
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Date now = new Date();
        vo.setUpdateUserId(userId);
        vo.setUpdateUserName(userName);
        vo.setUpdateTime(now);
        boolean result = itemService.editItemType(vo);
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "删除收费科目类型")
    @RequestMapping(value = "/delete-item-type")
    public RestResult<Boolean> deleteItemType(@ApiParam(value = "收费科目类型ID") @RequestParam("id") Long id) {
        //删除收费科目类型信息
        boolean result = itemService.deleteItemType(id);
        if (!result) {
            return new RestResult<Boolean>(ResultCodeEnum.FAILURE.CODE, "删除收费科目类型失败，请检查该类型下是否有收费科目，请先删除收费科目后再试。");
        }
        return new RestResult<Boolean>(result);
    }

    @ApiOperation(value = "检索收费科目类型列表，点击组织树上的节点时触发")
    @RequestMapping(value = "/list-item-type-tree")
    public RestResult<List<ChargeChargeItemType>> listItemTypeTree(@ApiParam(value = "id") @RequestParam("id") Long id) {
        //删除收费科目类型信息
        List<ChargeChargeItemType> list = itemService.listItemTypeTree(id);
        return new RestResult<List<ChargeChargeItemType>>(list);
    }

    @ApiOperation(value = "检索树上的收费科目，点击树上的收费科目类型时触发")
    @RequestMapping(value = "/list-item-tree")
    public RestResult listItemTree(@ApiParam(value = "收费科目类型id") @RequestParam("id") Long id) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        List<ChargeChargeItem> list = itemService.listItemTree(id, enterpriseId);
        if (!Objects.isNull(list) && id != 0) {
            for (ChargeChargeItem item : list) {
                List<ChargeChargeItem> child = itemService.listItemTree(item.getId(), enterpriseId);

                item.setIsHasChild(!Objects.isNull(child) && child.size() > 0 ? Boolean.TRUE : Boolean.FALSE);
            }
        }
        ChargeChargeItem rootItem = new ChargeChargeItem();
        if (id.equals(-1L) || id.equals(0L)) {
            String companyName = "";
            NsSossEnterprise enterprise = LoginDataHelper.getNsPlatformEnterprise();
            if (enterprise != null) {
                companyName = enterprise.getName();
            }
            rootItem.setId(-1L);
            rootItem.setChargeItemName(companyName);
            rootItem.setChargeItemTypeId(-1L);
            rootItem.setChargeItemShortName(companyName);
            rootItem.setIsHasChild((!Objects.isNull(list) && list.size() > 0) ? Boolean.TRUE : Boolean.FALSE);
        } else {
            rootItem = itemService.findById(id);
        }
        generateTree(rootItem, list);
        return new RestResult(rootItem);
    }

    @ApiOperation(value = "收费科目列表获取")
    @RequestMapping(value = "/list-item", method = RequestMethod.POST)
    public RestResult<PageInfo<ChargeChargeItem>> listPage(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        BizException.isNull(searchVo, "查询条件");
        RestResult<PageInfo<ChargeChargeItem>> restResult = null;
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        Long parentId = searchVo.getId();
        if (!Objects.isNull(parentId) && -1L == parentId) {
            searchVo.setId(null);
        }
        PageInfo<ChargeChargeItem> pageInfo = itemService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);
        return restResult;
    }

    @ApiOperation(value = "简单查询")
    @RequestMapping(value = "/simple-query", method = RequestMethod.POST)
    public RestResult selectByCodeOrName(@ApiParam(value = "查询条件") @RequestParam("chargeItemCode") String chargeItemCode) {
        BizException.isNull(chargeItemCode, "查询条件");
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("enterpriseId", enterpriseId);
        map.put("chargeItemCode", chargeItemCode);
        List<ChargeChargeItem> list = itemService.selectByCodeOrName(map);
        return new RestResult<>(list);
    }

    /**
     * wangjun
     *
     * @param vo
     * @return
     */
    @ApiOperation(value = "新增收费科目")
    @RequestMapping(value = "/add-item", method = RequestMethod.POST)
    public RestResult<Boolean> addItem(@ApiParam(value = "收费科目新增") @RequestBody ItemVo vo) {
        //编辑收费科目详情信息
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        //检查收费科目code是否存在
        boolean isExists = itemService.checkItemCodeIsExist(vo.getChargeItemCode(), null, enterpriseId);
        if (isExists) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该收费科目编码已存在");
        }
        //将前台的上级科目id进行转存
        vo.setParentId(Objects.isNull(vo.getChargeItemTypeId()) || "".equals(vo.getChargeItemTypeId()) ? -1L : vo.getChargeItemTypeId());
        //查找有多少子科目
        List<ChargeChargeItem> childItem = itemService.listItemTree(vo.getParentId(), enterpriseId);
        Long orderIndex = Objects.isNull(childItem) ? 0L : childItem.size();
        vo.setOrderIndex(orderIndex + 1);

        //设置科目路径
        if (vo.getParentId().equals(-1L)) {
            vo.setPath("/-1/");
        } else {
            ChargeChargeItem chargeChargeItem = itemService.findById(vo.getParentId());
            vo.setPath(chargeChargeItem.getPath() + chargeChargeItem.getId() + "/");
        }

        vo.setEnterpriseId(enterpriseId);
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Date now = new Date();
        vo.setCreateUserId(userId);
        vo.setCreateUserName(userName);
        vo.setCreateTime(now);
        vo.setUpdateUserId(userId);
        vo.setUpdateUserName(userName);
        vo.setUpdateTime(now);
        boolean result = itemService.add(vo);
        return new RestResult<Boolean>(result);
    }

    /**
     * @param searchVo
     * @return
     */
    @ApiOperation(value = "删除所有收费科目")
    @RequestMapping(value = "/delete-item-all", method = RequestMethod.POST)
    public RestResult<Object> deleteItemALL(@ApiParam(value = "查询条件") @RequestBody SearchVo searchVo) {
        //删除收费科目详情信息
        List<ChargeChargeItem> list = itemService.listPageALL(searchVo);
        List<Long> ids = new ArrayList<>();
        List<Long> id = new ArrayList<>();

        for (ChargeChargeItem long1 : list) {
            ids.add(long1.getId());
        }
        int size = ids.size();
        List<Long> exist = new ArrayList<Long>();
        ids.forEach((e) -> {
            List<ChargeChargeItem> items = itemService.findChildSubject(e);
            if (items != null && items.size() > 0) {
                exist.add(e);

            } else {
                id.add(e);
            }
        });
        if (CollectionUtils.isEmpty(id)) {
            return new RestResult<Object>(true);
        }
        Integer result = itemService.deleteBatch(id);
        if (exist.size() > 0) {
            //BizException.fail(ResultCodeEnum.SERVER_ERROR, "存在子科目,不允许删除!");
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "选择" + size + "条," + "成功" + (result) + "条," + "失败" + (size - result) + "条");
        }

        if (result != ids.size()) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "选择" + size + "条," + "成功" + (result) + "条," + "失败" + (size - result) + "条");
        }
        return new RestResult<Object>(result);
    }


    private void generateTree(ChargeChargeItem rootNodeVo, List<ChargeChargeItem> AllChildNodeVos) {
        List<ChargeChargeItem> childNodeVos = new ArrayList<ChargeChargeItem>();
        for (ChargeChargeItem childNodeVo : AllChildNodeVos) {
            if (rootNodeVo.getId().equals(childNodeVo.getParentId())) {
                childNodeVos.add(childNodeVo);
                generateTree(childNodeVo, AllChildNodeVos);
            }
        }
        if (childNodeVos.size() > 0) {
            rootNodeVo.setIsHasChild(Boolean.TRUE);
        } else {
            rootNodeVo.setIsHasChild(Boolean.FALSE);
        }
        ChargeChargeItem chargeChargeItem = itemService.findById(rootNodeVo.getParentId());
        rootNodeVo.setHighLevelSubjectName(Objects.isNull(chargeChargeItem) ? "" : chargeChargeItem.getChargeItemName());
        rootNodeVo.setChildList(childNodeVos);
    }

    @ApiOperation(value = "科目表单树，点击触发下一级")
    @RequestMapping(value = "/list-item-tree-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> listItemTreeForm(@ApiParam(value = "收费科目类型id") @RequestParam(value = "id", required = false) Long id) {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        if (id == null) id = 0L;
        Map<String, Object> result = itemService.listItemTreeForm(enterpriseId, id);
        return new RestResult<>(result);
    }

    @ApiOperation(value = "科目表单树详情")
    @RequestMapping(value = "/detail-item-form", method = RequestMethod.POST)
    public RestResult<ItemVo> detailItemForm(@RequestBody Long id) {
        ItemVo vo = itemService.detail(id);
        return new RestResult<>(vo);
    }
}
