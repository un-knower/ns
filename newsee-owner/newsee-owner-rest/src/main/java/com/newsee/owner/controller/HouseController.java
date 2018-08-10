package com.newsee.owner.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.google.zxing.WriterException;
import com.newsee.common.constant.Constants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.entity.FilterEntity;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.enums.HouseTypeEnum;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.APPRestResult;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.*;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.ProvinceCityArea;
import com.newsee.common.vo.SearchProjectVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.log.entity.ImportLogEntity;
import com.newsee.owner.entity.*;
import com.newsee.owner.service.ICustomerService;
import com.newsee.owner.service.IHouseService;
import com.newsee.owner.service.remote.ILogRemoteService;
import com.newsee.owner.service.remote.ISystemRemoteService;
import com.newsee.owner.service.remote.IWebSocketRemoteService;
import com.newsee.owner.utils.OwnerUtils;
import com.newsee.owner.vo.*;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.entity.NsSystemRoleHouse;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsSystemAreaVo;
import com.newsee.system.vo.NsSystemOrganizationVo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotNull;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by niyang on 2017/9/12.
 */
@RestController
@RequestMapping(value = "/owner-rest")
@ResponseBody
public class HouseController {

    @Autowired
    private IHouseService houseService;

    @Autowired
    private ICustomerService customerService;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    private IWebSocketRemoteService webSocketRemoteService;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private ILogRemoteService logRemoteService;

    private final static Logger log = LoggerFactory.getLogger(HouseController.class);

    /**
     * 添加房产节点
     *
     * @return
     */
    @ApiOperation(value = "添加房产节点")
    @RequestMapping(value = "/house/add-house-node", method = RequestMethod.POST)
    public RestResult<Long> addHouseNode(@RequestBody AddHouseVo addHouseVo) {

        Long start = new Date().getTime();
        Long organizationId = LoginDataHelper.getOrgId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();

        BizException.isNull(addHouseVo.getHouseType(), "房产类型");
        BizException.isNull(addHouseVo.getHouseName(), "房产名称");
        HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(addHouseVo.getHouseType());
        if (!HouseTypeEnum.AREA.equals(houseTypeEnum)) {
            BizException.isNull(addHouseVo.getHouseInfo(), "房产信息");
        }
        // if (CommonUtils.isObjectEmpty(addHouseVo.getEnterpriseId())) {
        addHouseVo.setEnterpriseId(enterpriseId);
        // }
        // if (CommonUtils.isObjectEmpty(addHouseVo.getOrganizationId())) {
        addHouseVo.setOrganizationId(organizationId);
        // }
        addHouseVo.setUserId(userId);
        addHouseVo.setUserName(userName);
        addHouseVo.setHouseTypeEnum(houseTypeEnum);

        BizException.isNull2(houseTypeEnum, "房产类型参数不正确");
        NsCoreDictionaryVo roomTypeDic = getDictionary("roomType", organizationId);
        NsCoreDictionaryVo carPortTypeDic = getDictionary("carPortType", organizationId);
        NsCoreDictionaryVo roomPropertyDic = getDictionary("roomProperty", organizationId);
        Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
        dicMap.put("roomTypeDic", roomTypeDic);
        dicMap.put("carPortTypeDic", carPortTypeDic);
        dicMap.put("roomPropertyDic", roomPropertyDic);

        long houseId = houseService.addHouseNode(addHouseVo, dicMap);
        // 从redis中删除房产树
        String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
                + organizationId.toString();
        redisUtil.delete(fieldRedisKey);
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("listHouseBaseInfoTreeForSearch========" + time);
        return new RestResult<>(houseId);
    }

    /**
     * 同步企业项目
     *
     * @param enterpriseId
     * @param nameJson
     * @param houseType
     * @return
     */
    @ApiOperation(value = "同步企业项目")
    @RequestMapping(value = "/house/add-remoteHouse", method = RequestMethod.GET)
    public Map<String, Long> addHousePrecinctRemote(@RequestParam(name = "enterpriseId") Long enterpriseId,
                                                    @RequestParam(name = "nameJson") String nameJson, @RequestParam(name = "houseType") String houseType) {
        Map<String, Long> map = null;
        List<OwnerHouseBaseInfo> houseList = null;
        Long organizationId = LoginDataHelper.getOrgId();
        if (!StringUtils.isBlank(nameJson)) {
            OwnerHouseBaseInfo info = null;
            List<OwnerHouseBaseInfo> voList = new ArrayList<>();
            String[] houseNames = nameJson.split(",");
            for (String houseName : houseNames) {
                info = new OwnerHouseBaseInfo();
                info.setEnterpriseId(enterpriseId);
                info.setOrganizationId(organizationId);
                info.setHouseName(houseName);
                info.setHouseType(houseType);
                voList.add(info);
            }
            // 添加房产数据
            houseList = houseService.addHouse(voList);
        }
        if (!CollectionUtils.isEmpty(houseList)) {
            map = new HashMap<>();
            for (OwnerHouseBaseInfo houseBase : houseList) {
                map.put(houseBase.getHouseName(), houseBase.getHouseId());
            }
        }

        return map;
    }

    /**
     * 更改房产排序
     *
     * @param houseId
     * @param parentId
     * @return
     */
    @ApiOperation(value = "更改房产排序")
    @RequestMapping(value = "/house/edit-house-sort", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> editHouseSort(
            @NotEmpty(message = "房产ID不能为空") @ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId,
            @NotNull(message = "父ID不能为空") @ApiParam(value = "父ID") @RequestParam("parentId") Long parentId,
            @ApiParam(value = "排序") @NotNull(message = "sort不能为空") @RequestParam("sort") Integer sort,
            @RequestBody List<Long> sortHouseIdList) {
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        BizException.isNull(houseId, "需更改房产ID");
        BizException.isNull(parentId, "房产父节点ID");

        Map<String, Object> result = houseService.editHouseSort(houseId, sort, parentId, userId, userName,
                sortHouseIdList);

        if ((boolean) result.get("result")) {
            Long organizationId = LoginDataHelper.getOrgId();
            Long enterpriseId = LoginDataHelper.getEnterpriseId();
            // 从redis中删除房产树
            String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
                    + organizationId.toString();
            redisUtil.delete(fieldRedisKey);
        }
        return new RestResult<>(result);
    }

    @ApiOperation(value = "模糊查询房产树节点")
    @RequestMapping(value = "/house/house-tree-search", method = RequestMethod.GET)
    public RestResult<List<HouseBaseInfoTreeNode>> listHouseBaseInfoTreeForSearch(
            @ApiParam(value = "房产名称") @RequestParam("houseName") String houseName,
            @ApiParam(value = "房产类型 6房间 8车位 0全部") @RequestParam("houseType") Byte houseType) {
        Long start = new Date().getTime();
        Long organizationId = LoginDataHelper.getOrgId();
        List<HouseBaseInfoTreeNode> treeNodeList = houseService.listHouseTreeBySearch(houseName, organizationId,
                houseType);
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("listHouseBaseInfoTreeForSearch========" + time);
        return new RestResult<>(treeNodeList);
    }

    @ApiOperation(value = "查询指定节点的房产树")
    @RequestMapping(value = "/house/house-tree-search-detail", method = RequestMethod.GET)
    public RestResult<CompanyHouseVo> detailHouseBaseInfoTreeForSearch(
            @ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId) {
        Long start = new Date().getTime();
        Long organizationId = LoginDataHelper.getOrgId();
        String companyName = LoginDataHelper.getNsPlatformEnterprise().getName();
        CompanyHouseVo companyHouseVo = new CompanyHouseVo();
        companyHouseVo.setOrganizationId(organizationId);
        companyHouseVo.setCompanyName(companyName);
        companyHouseVo.setHouseId(0L);
        List<HouseBaseInfoTreeNode> treeNodeList = new ArrayList<>();
        HouseBaseInfoTreeNode treeNode = houseService.detailHouseInTree(houseId);
        treeNodeList.add(treeNode);
        companyHouseVo.setChildOwnerHouseBaseInfoTreeNodeList(treeNodeList);
        if (!CollectionUtils.isEmpty(treeNodeList)) {
            companyHouseVo.setIsHasChild(true);
        } else {
            companyHouseVo.setIsHasChild(false);
        }
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("detailHouseBaseInfoTreeForSearch========" + time);
        return new RestResult<>(companyHouseVo);
    }

    /**
     * 获取房产树
     *
     * @return
     */
    @ApiOperation(value = "获取房产树")
    @RequestMapping(value = "/house/house-tree", method = RequestMethod.GET)
    public RestResult<CompanyHouseVo> listHouseBaseInfoTree(HttpServletRequest request) {
        Long start = new Date().getTime();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String companyName = "";
        NsSossEnterprise enterprise = LoginDataHelper.getNsPlatformEnterprise();
        if (enterprise != null) {
            companyName = enterprise.getName();
        }
        // 从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取房产树
        String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
                + organizationId.toString();

        CompanyHouseVo companyHouseVo = null;//(CompanyHouseVo) redisUtil.getObjectValue(fieldRedisKey);
        if (companyHouseVo == null) {
            companyHouseVo = new CompanyHouseVo();
            List<HouseBaseInfoTreeNode> treeNodeList = new ArrayList<HouseBaseInfoTreeNode>();
            if ("standard".equals(request.getParameter("type"))) {
                treeNodeList = houseService.listHouseTreeForStandard(organizationId);
            } else {
                treeNodeList = houseService.listHouseTree(organizationId);
            }
            companyHouseVo.setOrganizationId(organizationId);
            companyHouseVo.setCompanyName(companyName);
            companyHouseVo.setHouseId(0L);
            companyHouseVo.setChildOwnerHouseBaseInfoTreeNodeList(treeNodeList);
            if (!CollectionUtils.isEmpty(treeNodeList)) {
                companyHouseVo.setIsHasChild(true);
            } else {
                companyHouseVo.setIsHasChild(false);
            }
            redisUtil.setObjectValue(fieldRedisKey, companyHouseVo);
        }
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("listHouseBaseInfoTree========" + time);
        return new RestResult<>(companyHouseVo);
    }

    @ApiOperation(value = "获取房产树子节点")
    @RequestMapping(value = "/house/house-child-tree", method = RequestMethod.GET)
    public RestResult<List<HouseBaseInfoTreeNode>> listHouseChildTree(@RequestParam("houseId") Long houseId) {
        Long start = new Date().getTime();
        Long organizationId = LoginDataHelper.getOrgId();
        List<HouseBaseInfoTreeNode> treeNodeList = houseService.listHouseChildTree(organizationId, houseId);
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("listHouseChildTree========" + time);
        return new RestResult<>(treeNodeList);
    }

    /**
     * 删除房产节点
     *
     * @param houseId
     * @return
     */
    @ApiOperation(value = "删除房产节点")
    @RequestMapping(value = "/house/delete-house", method = RequestMethod.POST)
    public RestResult<Boolean> deleteHouseBase(@ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId) {
        BizException.isNull(houseId, "房产ID");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        RestResult<Boolean> restResult = null;
        Map<String, Object> resultMap = houseService.deleteHouseNode(houseId, userId, userName);
        if ((boolean) resultMap.get("result")) {
            Long organizationId = LoginDataHelper.getOrgId();
            Long enterpriseId = LoginDataHelper.getEnterpriseId();
            // 从redis中删除房产树
            String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
                    + organizationId.toString();
            redisUtil.delete(fieldRedisKey);
            restResult = new RestResult<>(true);
        } else {
            restResult = new RestResult<>(ResultCodeEnum.FAILURE.CODE, (String) resultMap.get("msg"));
        }
        return restResult;
    }

    @ApiOperation(value = "批量删除房产")
    @RequestMapping(value = "/house/batch-delete-house", method = RequestMethod.POST)
    public RestResult<String> deleteHouseBatch(@ApiParam(value = "房产IDList") @RequestBody List<Long> houseIdList) {
        if (CollectionUtils.isEmpty(houseIdList)) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "未选择房产");
        }
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();

        Map<String, Object> map = houseService.deleteHouseBatch(houseIdList, userId, userName);
        String deleteResult = "选择" + houseIdList.size() + "条，删除成功" + map.get("deleteNum") + "条，失败"
                + map.get("deleteFailNum") + "条。";
        if (Integer.valueOf(map.get("deleteNum").toString()) > 0) {
            Long organizationId = LoginDataHelper.getOrgId();
            Long enterpriseId = LoginDataHelper.getEnterpriseId();
            // 从redis中删除房产树
            String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
                    + organizationId.toString();
            redisUtil.delete(fieldRedisKey);
        }
        return new RestResult<>(deleteResult);
    }

    @ApiOperation(value = "删除全部房产")
    @RequestMapping(value = "/house/all-delete-house", method = RequestMethod.POST)
    public RestResult<String> deleteHouseBatch(@ApiParam(value = "房产列表查询条件") @RequestBody SearchVo searchVo)
            throws Exception {
        BizException.isNull(searchVo, "房产列表查询条件");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        searchVo.setEnterpriseId(LoginDataHelper.getEnterpriseId());
        searchVo.setOrganizationId(LoginDataHelper.getOrgId());
        Map<String, Object> map = houseService.deleteAllHouseBySearch(searchVo, userId, userName);
        String deleteResult = "选择" + map.get("total") + "条，删除成功" + map.get("deleteNum") + "条，失败"
                + map.get("deleteFailNum") + "条。";
        if (Integer.valueOf(map.get("deleteNum").toString()) > 0) {
            Long organizationId = LoginDataHelper.getOrgId();
            Long enterpriseId = LoginDataHelper.getEnterpriseId();
            // 从redis中删除房产树
            String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
                    + organizationId.toString();
            redisUtil.delete(fieldRedisKey);
        }
        return new RestResult<>(deleteResult);
    }

    /**
     * 房间锁定
     *
     * @param houseIdList
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "房间全部锁定")
    @RequestMapping(value = "/house/lock-all-house", method = RequestMethod.POST)
    public RestResult<String> editLockAllHouse(
            @ApiParam(value = "锁定true，解锁false") @RequestParam("isLock") Boolean isLock,
            @ApiParam(value = "房产列表查询条件") @RequestBody SearchVo searchVo) throws Exception {
        BizException.isNull2(searchVo, "房产列表查询条件");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        searchVo.setEnterpriseId(LoginDataHelper.getEnterpriseId());
        searchVo.setOrganizationId(LoginDataHelper.getOrgId());
        Map<String, Object> map = houseService.editLockAllHouseBySearch(isLock, searchVo, userId, userName);

        String lockResult = "";
        if (isLock) {
            lockResult = "选择" + map.get("total") + "条，锁定成功" + map.get("lockNum") + "条，失败" + map.get("lockFailNum")
                    + "条。";
        } else {
            lockResult = "选择" + map.get("total") + "条，解锁成功" + map.get("lockNum") + "条，失败" + map.get("lockFailNum")
                    + "条。";
        }
        return new RestResult<>(lockResult);
    }

    @ApiOperation(value = "房间锁定")
    @RequestMapping(value = "/house/lock-house", method = RequestMethod.POST)
    public RestResult<String> editToggleLockHouse(
            @ApiParam(value = "锁定true，解锁false") @RequestParam("isLock") Boolean isLock,
            @ApiParam(value = "房产IDList") @RequestBody List<Long> houseIdList) {
        if (CollectionUtils.isEmpty(houseIdList)) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "未选择房产");
        }
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Map<String, Object> map = houseService.editToggleLockHouse(isLock, houseIdList, userId, userName);
        String lockResult = "";
        if (isLock) {
            lockResult = "选择" + map.get("total") + "条，锁定成功" + map.get("lockNum") + "条，失败" + map.get("lockFailNum")
                    + "条。";
        } else {
            lockResult = "选择" + map.get("total") + "条，解锁成功" + map.get("lockNum") + "条，失败" + map.get("lockFailNum")
                    + "条。";
        }
        return new RestResult<>(lockResult);
    }

    @ApiOperation(value = "房间停用")
    @RequestMapping(value = "/house/block-up-house", method = RequestMethod.POST)
    public RestResult<String> editBlockUpHouse(
            @ApiParam(value = "停用true，启用false") @RequestParam("isBlockUp") Boolean isBlockUp,
            @ApiParam(value = "房产IDList") @RequestBody List<Long> houseIdList) {
        if (CollectionUtils.isEmpty(houseIdList)) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "未选择房产");
        }
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Map<String, Object> map = houseService.editBlockUpHouse(isBlockUp, houseIdList, userId, userName);
        String blockUpResult = "";
        if (isBlockUp) {
            blockUpResult = "选择" + map.get("total") + "条，停用成功" + map.get("blockUpNum") + "条，失败"
                    + map.get("blockUpFailNum") + "条。";
        } else {
            blockUpResult = "选择" + map.get("total") + "条，启用成功" + map.get("blockUpNum") + "条，失败"
                    + map.get("blockUpFailNum") + "条。";
        }
        return new RestResult<>(blockUpResult);
    }

    @ApiOperation(value = "房间全部停用")
    @RequestMapping(value = "/house/block-up-all-house", method = RequestMethod.POST)
    public RestResult<String> editBlockUpAllHouse(
            @ApiParam(value = "锁定true，解锁false") @RequestParam("isBlockUp") Boolean isBlockUp,
            @ApiParam(value = "房产列表查询条件") @RequestBody SearchVo searchVo) throws Exception {
        BizException.isNull2(searchVo, "房产列表查询条件");
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        searchVo.setEnterpriseId(LoginDataHelper.getEnterpriseId());
        searchVo.setOrganizationId(LoginDataHelper.getOrgId());
        Map<String, Object> map = houseService.editBlockUpAllHouseBySearch(isBlockUp, searchVo, userId, userName);
        String blockUpResult = "";
        if (isBlockUp) {
            blockUpResult = "选择" + map.get("total") + "条，停用成功" + map.get("blockUpNum") + "条，失败"
                    + map.get("blockUpFailNum") + "条。";
        } else {
            blockUpResult = "选择" + map.get("total") + "条，启用成功" + map.get("blockUpNum") + "条，失败"
                    + map.get("blockUpFailNum") + "条。";
        }
        return new RestResult<>(blockUpResult);
    }

    /**
     * 房产列表
     *
     * @param searchConditionVo
     * @return
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @ApiOperation(value = "房产列表")
    @RequestMapping(value = "/house/house-list", method = RequestMethod.POST)
    public RestResult<PageInfo<HouseListVo>> listPageHouseInfo(@RequestBody SearchVo searchConditionVo)
            throws Exception {
        Long start = new Date().getTime();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        String funcId = LoginDataHelper.getFuncId();
        searchConditionVo.setEnterpriseId(enterpriseId);
        searchConditionVo.setOrganizationId(organizationId);

        NsCoreResourcecolumnVo nsCoreResourcecolumnVo = new NsCoreResourcecolumnVo();
        nsCoreResourcecolumnVo.setEnterpriseId(enterpriseId);
        nsCoreResourcecolumnVo.setOrganizationId(organizationId);
        nsCoreResourcecolumnVo.setResourcecolumnFuncinfoId(funcId);

        // 从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
        String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_"
                + organizationId.toString() + "_" + funcId;
        Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
        if (filedRedisObject == null) {
            RestResult<Map<String, Object>> columnResult = systemRemoteService
                    .listColumnForRemote(nsCoreResourcecolumnVo);
            filedRedisObject = columnResult.getResultData();
        }

        PageInfo<HouseListVo> result = houseService.listHouse(searchConditionVo, (Map<String, Object>) filedRedisObject,
                true);
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("listPageHouseInfo========" + time);
        return new RestResult<>(result);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "获取列表合计")
    @RequestMapping(value = "/house/get-total", method = RequestMethod.POST)
    public RestResult<HouseListVo> getTotal(@RequestBody SearchVo searchConditionVo) throws Exception {
        String funcId = LoginDataHelper.getFuncId();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchConditionVo.setEnterpriseId(enterpriseId);
        searchConditionVo.setOrganizationId(organizationId);

        NsCoreResourcecolumnVo nsCoreResourcecolumnVo = new NsCoreResourcecolumnVo();
        nsCoreResourcecolumnVo.setEnterpriseId(enterpriseId);
        nsCoreResourcecolumnVo.setOrganizationId(organizationId);
        nsCoreResourcecolumnVo.setResourcecolumnFuncinfoId(funcId);

        // 从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
        String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_"
                + organizationId.toString() + "_" + funcId;
        Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
        if (filedRedisObject == null) {
            RestResult<Map<String, Object>> columnResult = systemRemoteService
                    .listColumnForRemote(nsCoreResourcecolumnVo);
            filedRedisObject = columnResult.getResultData();
        }
        HouseListVo total = houseService.getTotal(searchConditionVo, (Map<String, Object>) filedRedisObject);
        return new RestResult<>(total);
    }

    /**
     * 新增装修
     *
     * @param decorateVo
     * @return
     */
    @ApiOperation(value = "新增装修")
    @RequestMapping(value = "/house/add-decorate", method = RequestMethod.POST)
    public RestResult<Boolean> addDecorate(@RequestBody DecorateVo decorateVo) {
        BizException.isNull(decorateVo.getHouseId(), "房产ID");
        BizException.isNull(decorateVo.getHandleTime(), "申请日期");
        BizException.isNull(decorateVo.getStartDecorateTime(), "装修开始日期");
        BizException.isNull(decorateVo.getEndDecorateTime(), "装修结束日期");
        BizException.isNull(decorateVo.getDecorateStage(), "装修状态");
        Long userId = LoginDataHelper.getUserId();
        Boolean result = houseService.addDecorate(decorateVo, userId);
        return new RestResult<>(result);
    }

    /**
     * 合并房间
     *
     * @param houseCombineVo
     * @return
     */
    @ApiOperation(value = "合并房间")
    @RequestMapping(value = "/house/house-combine", method = RequestMethod.POST)
    public RestResult<Long> editHouseCombine(@RequestBody HouseCombineVo houseCombineVo) {
        if (CollectionUtils.isEmpty(houseCombineVo.getHouseId())) {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "未选择合并房间");
        }
        BizException.isNull(houseCombineVo.getChargingArea(), "收费面积");
        Long userId = LoginDataHelper.getUserId();
        Long organizationId = LoginDataHelper.getOrgId();
        Long result = houseService.editHouseCombine(houseCombineVo, userId, organizationId);
        return new RestResult<>(result);
    }

    /**
     * 拆分房间
     *
     * @param houseSplitVo
     * @return
     */
    @ApiOperation(value = "拆分房间")
    @RequestMapping(value = "/house/house-split", method = RequestMethod.POST)
    public RestResult<Boolean> editHouseSplit(@RequestBody HouseSplitVo houseSplitVo) {
        BizException.isNull(houseSplitVo.getHouseId(), "未选择拆分房间");
        if (CollectionUtils.isEmpty(houseSplitVo.getHouseSplitInfoVoList())) {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "拆分房间数不能为0");
        }
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Boolean result = houseService.editHouseSplit(houseSplitVo.getHouseId(), houseSplitVo.getHouseSplitInfoVoList(),
                userId, userName);
        return new RestResult<>(result);
    }

    @ApiOperation(value = "生成二维码")
    @RequestMapping(value = "/create-qrcode", method = RequestMethod.POST)
    public RestResult<Boolean> createQrcode(@RequestParam("houseId") Long houseId,
                                            // @RequestParam("content") String content,
                                            @RequestParam("qrCodeSize") Integer qrCodeSize, HttpServletResponse response)
            throws WriterException, IOException {
        // 清空response
        response.reset();
        // 禁止图像缓存。
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");
        OutputStream os = response.getOutputStream();
        CommonUtils.createQrCode(os, houseId.toString(), qrCodeSize, "jpeg");
        // os.flush();
        // os.close();
        // response.flushBuffer();
        return new RestResult<>();
    }

    @ApiOperation(value = "初始化省市区控件")
    @RequestMapping(value = "/house/init-province-city-area", method = RequestMethod.GET)
    public RestResult<Map<String, List<NsSystemAreaVo>>> initProvinceCityArea(
            @ApiParam("区域级别Level 1省 2市 3区 4街道") @RequestParam(name = "areaLevel", required = false) String areaLevel,
            @ApiParam("区域编码，根据区域编码，获取其下的子区域") @RequestParam(name = "areaCode", required = false) String areaCode,
            @ApiParam("省市区ID{province: \"\",city: \"\",district: \"\"}") @RequestParam(name = "queryParam", required = false) String queryParam) {
        Map<String, List<NsSystemAreaVo>> itemListMap = null;

        // 点击请求
        if ((!StringUtils.isBlank(areaLevel) || !StringUtils.isBlank(areaCode)) && (StringUtils.isBlank(queryParam))) {
            if (("1".equals(areaLevel) && StringUtils.isBlank(areaCode))
                    || ("2".equals(areaLevel) && !StringUtils.isBlank(areaCode))
                    || ("3".equals(areaLevel) && !StringUtils.isBlank(areaCode))
                    || ("4".equals(areaLevel) && !StringUtils.isBlank(areaCode))) {
                itemListMap = new HashMap<>();
                List<NsSystemAreaVo> areaList = new ArrayList<>();
                RestResult<List<NsSystemAreaVo>> resultAreas = systemRemoteService.areaFuncinfo(areaLevel, areaCode);
                if (!Objects.isNull(resultAreas)) {
                    areaList = resultAreas.getResultData();
                }
                if ("1".equals(areaLevel)) {
                    itemListMap.put("provinces", areaList);
                } else if ("2".equals(areaLevel)) {
                    itemListMap.put("cities", areaList);
                } else if ("3".equals(areaLevel)) {
                    itemListMap.put("districts", areaList);
                } else if ("4".equals(areaLevel)) {
                    itemListMap.put("streets", areaList);
                }
            }
        }

        // 编辑页面初始化请求
        if ((StringUtils.isBlank(areaLevel) && StringUtils.isBlank(areaCode)) && (!StringUtils.isBlank(queryParam))) {
            itemListMap = new HashMap<>();
            ProvinceCityArea provincialandcity = JSONObject.parseObject(queryParam, ProvinceCityArea.class);
            // 省
            List<NsSystemAreaVo> provinceitemList = new ArrayList<>();
            NsSystemAreaVo provinceEntity = new NsSystemAreaVo();
            if (StringUtils.hasLength(provincialandcity.getProvince())) {
                NsSystemArea proArea = systemRemoteService.getArea(provincialandcity.getProvince());
                if (!Objects.isNull(proArea)) {
                    provinceEntity.setLabel(proArea.getAreaName());
                    provinceEntity.setValue(provincialandcity.getProvince());
                    provinceitemList.add(provinceEntity);
                    itemListMap.put("provinces", provinceitemList);
                } else {
                    itemListMap.put("provinces", new ArrayList<>());
                }
            } else {
                itemListMap.put("provinces", new ArrayList<>());
            }

            // 市
            List<NsSystemAreaVo> cityitemList = new ArrayList<>();
            NsSystemAreaVo cityEntity = new NsSystemAreaVo();
            if (StringUtils.hasLength(provincialandcity.getCity())) {
                NsSystemArea cityArea = systemRemoteService.getArea(provincialandcity.getCity());
                if (!Objects.isNull(cityArea)) {
                    cityEntity.setLabel(cityArea.getAreaName());
                    cityEntity.setValue(provincialandcity.getCity());
                    cityitemList.add(cityEntity);
                    itemListMap.put("cities", cityitemList);
                } else {
                    itemListMap.put("cities", new ArrayList<>());
                }
            } else {
                itemListMap.put("cities", new ArrayList<>());
            }

            // 区
            List<NsSystemAreaVo> areaitemList = new ArrayList<>();
            NsSystemAreaVo areaEntity = new NsSystemAreaVo();
            if (StringUtils.hasLength(provincialandcity.getDistrict())) {
                NsSystemArea areaArea = systemRemoteService.getArea(provincialandcity.getDistrict());
                if (!Objects.isNull(areaArea)) {
                    areaEntity.setLabel(areaArea.getAreaName());
                    areaEntity.setValue(provincialandcity.getDistrict());
                    areaitemList.add(areaEntity);
                    itemListMap.put("districts", areaitemList);
                } else {
                    itemListMap.put("districts", new ArrayList<>());
                }
            } else {
                itemListMap.put("districts", new ArrayList<>());
            }

            // 街道
            List<NsSystemAreaVo> streetitemList = new ArrayList<>();
            NsSystemAreaVo streetEntity = new NsSystemAreaVo();
            if (StringUtils.hasLength(provincialandcity.getStreet())) {
                NsSystemArea streetArea = systemRemoteService.getArea(provincialandcity.getStreet());
                if (!Objects.isNull(streetArea)) {
                    streetEntity.setLabel(streetArea.getAreaName());
                    streetEntity.setValue(provincialandcity.getStreet());
                    streetitemList.add(streetEntity);
                    itemListMap.put("streets", streetitemList);
                } else {
                    itemListMap.put("streets", new ArrayList<>());
                }
            } else {
                itemListMap.put("streets", new ArrayList<>());
            }
        }
        return new RestResult<>(itemListMap);
    }

    /**
     * @param @param  houseId
     * @param @return
     * @param @throws Exception 设定文件
     * @return RestResult<OwnerRoomVo> 返回类型
     * @throws @Title:   detailHouse
     * @throws Exception
     * @Description: 查看房产详情
     * @author wangrenjie
     */
    @ApiOperation(value = "查看房间详情")
    @RequestMapping(value = "/house/detail", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> detailHouse(@ApiParam(value = "房间ID") @RequestParam Long houseId)
            throws Exception {

        Long start = new Date().getTime();
        Map<String, Object> resultMap = new HashMap<>();
        if (houseId == null) {
            return new RestResult<>(ResultCodeEnum.PARAMS_MISSING.CODE, "未选择房产");
        }
        Long organizationId = LoginDataHelper.getOrgId();
        // 权限验证
        // 查询数据
        Long ownerId = houseService.getOwnerIdByhouseId(houseId);
        Map<String, Object> map = houseService.detailHouseByHouseId(houseId, ownerId);
        HouseBaseVo owner = (HouseBaseVo) map.get("houseBaseVo");
        if (owner != null) {
            HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(owner.getHouseType());
            String houseJson = "";
            StringBuffer jsonBuffer = new StringBuffer();
            JSONObject.DEFFAULT_DATE_FORMAT = DateUtils.YYYYMMDD_CROSS;
            if ("1".equals(owner.getHouseType())) {
                houseJson = jsonBuffer.append(CommonUtils.toJson(owner)).toString();
            } else {
                String json = CommonUtils.toJson(owner);
                jsonBuffer.append(json.substring(0, json.length() - 1)).append(",");
            }
            List<CustomerVo> customerVoList = null;
            NsCoreDictionaryVo houseTypeDic = getDictionary("houseType", organizationId);
            switch (houseTypeEnum) {
                case PRECINCT:
                    OwnerProjectInfoVo projectInfoVo = (OwnerProjectInfoVo) map.get("projectInfoVo");
                    if (projectInfoVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(projectInfoVo).replace("{", "").replace("}", ",")
                                .replace("\"houseId\":" + projectInfoVo.getHouseId() + ",", ""));
                    }
                    OwnerProjectExtendInfoVo projectExtendInfoVo = (OwnerProjectExtendInfoVo) map
                            .get("projectExtendInfoVo");
                    if (projectExtendInfoVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(projectExtendInfoVo).replace("{", "").replace("}", ",")
                                .replace("\"houseId\":" + projectExtendInfoVo.getHouseId() + ",", ""));
                        customerVoList = getCustomer(projectExtendInfoVo.getDeveloper(), organizationId);
                    }
                    OwnerProjectPropertyInfoVo projectPropertyInfoVo = (OwnerProjectPropertyInfoVo) map
                            .get("projectPropertyInfoVo");
                    if (projectPropertyInfoVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(projectPropertyInfoVo).replace("{", "")
                                .replace("\"houseId\":" + projectPropertyInfoVo.getHouseId() + ",", "")).toString();
                    }
                    break;
                case BUILDING:
                    OwnerBuildingInfoVo buildingInfoVo = (OwnerBuildingInfoVo) map.get("buildingInfoVo");
                    if (buildingInfoVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(buildingInfoVo).replace("{", "").replace("}", ",")
                                .replace("\"houseId\":" + buildingInfoVo.getHouseId() + ",", ""));
                    }
                    OwnerBuildingExtendInfoVo buildingExtendInfoVo = (OwnerBuildingExtendInfoVo) map
                            .get("buildingExtendInfoVo");
                    if (buildingExtendInfoVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(buildingExtendInfoVo).replace("{", "")
                                .replace("\"houseId\":" + buildingExtendInfoVo.getHouseId() + ",", "")).toString();
                        customerVoList = getCustomer(buildingExtendInfoVo.getDeveloper(), organizationId);
                    }
                    break;
                case UNIT:
                    OwnerUnitInfoVo unitInfoVo = (OwnerUnitInfoVo) map.get("unitInfoVo");
                    if (unitInfoVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(unitInfoVo).replace("{", "")
                                .replace("\"houseId\":" + unitInfoVo.getHouseId() + ",", "")).toString();
                    }
                    break;
                case CLUSTER:
                    OwnerClusterInfoVo clusterInfoVo = (OwnerClusterInfoVo) map.get("clusterInfoVo");
                    if (clusterInfoVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(clusterInfoVo).replace("{", "")
                                .replace("\"houseId\":" + clusterInfoVo.getHouseId() + ",", "")).toString();
                        customerVoList = getCustomer(clusterInfoVo.getDeveloper(), organizationId);
                    }
                    break;
                case ROOM:
                    OwnerRoomInfoVo roomInfoVo = (OwnerRoomInfoVo) map.get("roomInfoVo");
                    if (roomInfoVo != null) {
                        String roomTypeName = OwnerUtils.getDicName(houseTypeDic, roomInfoVo.getRoomTypeId());
                        roomInfoVo.setRoomTypeName(roomTypeName);
                        jsonBuffer.append(CommonUtils.toJson(roomInfoVo).replace("{", "").replace("}", ",")
                                .replace("\"houseId\":" + roomInfoVo.getHouseId() + ",", ""));
                    }
                    OwnerRoomExtendInfoVo roomExtendInfoVo = (OwnerRoomExtendInfoVo) map.get("roomExtendInfoVo");
                    if (roomExtendInfoVo != null) {
                        List<Date> roomMaintenanceDate = new ArrayList<>();
                        roomMaintenanceDate.add(roomExtendInfoVo.getMaintenanceStartDate());
                        roomMaintenanceDate.add(roomExtendInfoVo.getMaintenanceEndDate());
                        roomExtendInfoVo.setMaintenanceDate(roomMaintenanceDate);
                        jsonBuffer.append(CommonUtils.toJson(roomExtendInfoVo).replace("{", "")
                                .replace("\"houseId\":" + roomExtendInfoVo.getHouseId() + ",", "")).toString();
                    }
                    break;
                case GARAGE:
                    OwnerGarageInfoVo garageInfoVo = (OwnerGarageInfoVo) map.get("garageInfoVo");
                    if (garageInfoVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(garageInfoVo).replace("{", "")
                                .replace("\"houseId\":" + garageInfoVo.getHouseId() + ",", "")).toString();
                        customerVoList = getCustomer(garageInfoVo.getDeveloper(), organizationId);
                    }
                    break;
                case CARPORT:
                    OwnerCarportInfoVo carportInfoVo = (OwnerCarportInfoVo) map.get("carportInfoVo");
                    if (carportInfoVo != null) {
                        String carportTypeName = OwnerUtils.getDicName(houseTypeDic, carportInfoVo.getCarportTypeId());
                        carportInfoVo.setCarportTypeName(carportTypeName);
                        jsonBuffer.append(CommonUtils.toJson(carportInfoVo).replace("{", "").replace("}", ",")
                                .replace("\"houseId\":" + carportInfoVo.getHouseId() + ",", ""));
                    }
                    OwnerCarportExtendInfoVo carportExtendInfoVo = (OwnerCarportExtendInfoVo) map
                            .get("carportExtendInfoVo");
                    if (carportExtendInfoVo != null) {
                        List<Date> carMaintenanceDate = new ArrayList<>();
                        carMaintenanceDate.add(carportExtendInfoVo.getMaintenanceStartDate());
                        carMaintenanceDate.add(carportExtendInfoVo.getMaintenanceEndDate());
                        carportExtendInfoVo.setMaintenanceDate(carMaintenanceDate);
                        jsonBuffer.append(CommonUtils.toJson(carportExtendInfoVo).replace("{", "")
                                .replace("\"houseId\":" + carportExtendInfoVo.getHouseId() + ",", "")).toString();
                    }
                    break;
                case PUBLICAREA:
                    OwnerPublicAreaVo publicAreaVo = (OwnerPublicAreaVo) map.get("publicAreaVo");
                    if (publicAreaVo != null) {
                        jsonBuffer.append(CommonUtils.toJson(publicAreaVo).replace("{", "")
                                .replace("\"houseId\":" + publicAreaVo.getHouseId() + ",", "")).toString();
                        customerVoList = getCustomer(publicAreaVo.getDeveloper(), organizationId);
                    }
                    break;
                default:
                    break;
            }
            houseJson = jsonBuffer.toString();
            resultMap.put("houseJson", houseJson);
            String customerListJson = CommonUtils.toJson(customerVoList);
            resultMap.put("customerListJson", customerListJson);
            @SuppressWarnings("unchecked")
            Map<String, List<HouseStageDetailVo>> stageMap = (Map<String, List<HouseStageDetailVo>>) map
                    .get("stageMap");
            List<HouseStageDetailVo> salesStageVoList = (List<HouseStageDetailVo>) stageMap.get("salesStageVoList");
            if (!CollectionUtils.isEmpty(salesStageVoList)) {
                resultMap.put("salesStageVoList", salesStageVoList);
            }
            List<HouseStageDetailVo> rentStageVoList = (List<HouseStageDetailVo>) stageMap.get("rentStageVoList");
            if (!CollectionUtils.isEmpty(rentStageVoList)) {
                resultMap.put("rentStageVoList", rentStageVoList);
            }
            List<HouseStageDetailVo> takeStageVoList = (List<HouseStageDetailVo>) stageMap.get("takeStageVoList");
            if (!CollectionUtils.isEmpty(takeStageVoList)) {
                resultMap.put("takeStageVoList", takeStageVoList);
            }
            List<HouseStageDetailVo> decorateStageVoList = (List<HouseStageDetailVo>) stageMap
                    .get("decorateStageVoList");
            if (!CollectionUtils.isEmpty(decorateStageVoList)) {
                resultMap.put("decorateStageVoList", decorateStageVoList);
            }
            List<HouseStageDetailVo> checkInStageVoList = (List<HouseStageDetailVo>) stageMap.get("checkInStageVoList");
            if (!CollectionUtils.isEmpty(checkInStageVoList)) {
                resultMap.put("checkInStageVoList", checkInStageVoList);

            }
        }
        // 获取关联房产
        List<Map<String, Object>> slaveHouseList = houseService.listSlaveHouse(houseId);
        resultMap.put("slaveHouseList", slaveHouseList);
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("detailHouse========" + time);
        // 数据字典
        return new RestResult<>(resultMap);
    }

    /**
     * 房产视图
     *
     * @return
     */
    @ApiOperation(value = "房产视图")
    @RequestMapping(value = "/house/house-view", method = RequestMethod.GET)
    public RestResult<HouseViewVo> detailHouseView(@ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId) {
        HouseViewVo houseViewVo = new HouseViewVo();
        Long organizationId = LoginDataHelper.getOrgId();
        Long ownerId = houseService.getOwnerIdByhouseId(houseId);
        // 房产信息
        Map<String, Object> map = houseService.detailHouseByHouseId(houseId, ownerId);
        HouseBaseVo houseBaseVo = (HouseBaseVo) map.get("houseBaseVo");
        OwnerRoomInfoVo roomInfoVo = (OwnerRoomInfoVo) map.get("roomInfoVo");
        OwnerCarportInfoVo carportInfoVo = (OwnerCarportInfoVo) map.get("carportInfoVo");
        if (houseBaseVo != null) {
            BeanUtils.copyProperties(houseBaseVo, houseViewVo);
        }
        if (roomInfoVo != null) {
            // houseViewVo.setHouseId(roomInfoVo.getHouseId());
            String roomTypeName = "";
            NsCoreDictionaryVo houseTypeDic = getDictionary("roomTypeId", organizationId);
            roomTypeName = OwnerUtils.getDicName(houseTypeDic, roomInfoVo.getRoomTypeId());
            houseViewVo.setRoomTypeId(roomInfoVo.getRoomTypeId());
            houseViewVo.setRoomTypeName(roomTypeName);
            houseViewVo.setChargingArea(roomInfoVo.getChargingArea());
            houseViewVo.setRemark(roomInfoVo.getRemark());
        }
        if (carportInfoVo != null) {
            String roomTypeName = "";
            NsCoreDictionaryVo houseTypeDic = getDictionary("carportTypeId", organizationId);
            roomTypeName = OwnerUtils.getDicName(houseTypeDic, carportInfoVo.getCarportTypeId());
            houseViewVo.setRoomTypeId(carportInfoVo.getCarportTypeId());
            houseViewVo.setRoomTypeName(roomTypeName);
            houseViewVo.setChargingArea(carportInfoVo.getChargingArea());
            OwnerCarportExtendInfoVo carportExtendInfoVo = (OwnerCarportExtendInfoVo) map.get("carportExtendInfoVo");
            if (carportExtendInfoVo != null) {
                houseViewVo.setRemark(carportExtendInfoVo.getRemark());
            }
        }

        // 客户信息
        if (!CommonUtils.isObjectEmpty(ownerId)) {
            CustomerVo customerVo = customerService.detail(ownerId);
            if (customerVo != null) {
                houseViewVo.setCustomerVo(customerVo);
            }
            // 家庭成员
            List<FamilyInfoVo> list = customerService.listFamilyByOwnerId(ownerId);
            if (!CollectionUtils.isEmpty(list)) {
                houseViewVo.setFamilyInfoList(list);
            }
        }
        // 房态变更
        // Map<String, List<HouseStageDetailVo>> stageMap =
        // houseService.listStageByhouseId(houseId);
        @SuppressWarnings("unchecked")
        Map<String, List<HouseStageDetailVo>> stageMap = (Map<String, List<HouseStageDetailVo>>) map.get("stageMap");
        List<HouseStageDetailVo> salesStageVoList = (List<HouseStageDetailVo>) stageMap.get("salesStageVoList");
        if (!CollectionUtils.isEmpty(salesStageVoList)) {
            houseViewVo.setSalesStageVoList(salesStageVoList);
        }
        List<HouseStageDetailVo> rentStageVoList = (List<HouseStageDetailVo>) stageMap.get("rentStageVoList");
        if (!CollectionUtils.isEmpty(rentStageVoList)) {
            houseViewVo.setRentStageVoList(rentStageVoList);
        }
        List<HouseStageDetailVo> takeStageVoList = (List<HouseStageDetailVo>) stageMap.get("takeStageVoList");
        if (!CollectionUtils.isEmpty(takeStageVoList)) {
            houseViewVo.setTakeStageVoList(takeStageVoList);
        }
        List<HouseStageDetailVo> decorateStageVoList = (List<HouseStageDetailVo>) stageMap.get("decorateStageVoList");
        if (!CollectionUtils.isEmpty(decorateStageVoList)) {
            houseViewVo.setDecorateStageVoList(decorateStageVoList);
        }
        List<HouseStageDetailVo> checkInStageVoList = (List<HouseStageDetailVo>) stageMap.get("checkInStageVoList");
        if (!CollectionUtils.isEmpty(checkInStageVoList)) {
            houseViewVo.setCheckInStageVoList(checkInStageVoList);
        }
        return new RestResult<>(houseViewVo);
    }

    // @ApiOperation(value = "获取房态变更操作记录")
    // @RequestMapping(value = "/list-owner-house-stage", method =
    // RequestMethod.POST)
    // public RestResult<Map<String, Object>> listOwnerHouseStage(Long houseId){
    // Map<String, Object> map = new HashMap<>();
    // //房产信息
    // Map<String, Object> houseMap =
    // houseService.detailHouseByHouseId(houseId);
    // OwnerRoomInfoVo roomInfoVo = (OwnerRoomInfoVo)
    // houseMap.get("roomInfoVo");
    // if (roomInfoVo != null) {
    // map.put("roomInfoVo", roomInfoVo);
    // }
    // //房态变更
    // Map<String, List<HouseStageDetailVo>> stageMap = (Map<String,
    // List<HouseStageDetailVo>>) houseMap.get("stageMap");
    // List<HouseStageDetailVo> salesStageVoList = (List<HouseStageDetailVo>)
    // stageMap.get("salesStageVoList");
    // if (!CollectionUtils.isEmpty(salesStageVoList)) {
    // map.put("salesStageVoList", salesStageVoList);
    // }
    // List<HouseStageDetailVo> rentStageVoList = (List<HouseStageDetailVo>)
    // stageMap.get("rentStageVoList");
    // if (!CollectionUtils.isEmpty(rentStageVoList)) {
    // map.put("rentStageVoList", rentStageVoList);
    // }
    // List<HouseStageDetailVo> takeStageVoList = (List<HouseStageDetailVo>)
    // stageMap.get("takeStageVoList");
    // if (!CollectionUtils.isEmpty(takeStageVoList)) {
    // map.put("takeStageVoList", takeStageVoList);
    // }
    // List<HouseStageDetailVo> decorateStageVoList = (List<HouseStageDetailVo>)
    // stageMap.get("decorateStageVoList");
    // if (!CollectionUtils.isEmpty(decorateStageVoList)) {
    // map.put("decorateStageVoList", decorateStageVoList);
    // }
    // List<HouseStageDetailVo> checkInStageVoList = (List<HouseStageDetailVo>)
    // stageMap.get("checkInStageVoList");
    // if (!CollectionUtils.isEmpty(checkInStageVoList)) {
    // map.put("checkInStageVoList", checkInStageVoList);
    // }
    // return new RestResult<Map<String,Object>>(map);
    // }

    @ApiOperation(value = "获取房产实体json")
    @RequestMapping(value = "/house/detail-field", method = RequestMethod.GET)
    public RestResult<Map<String, String>> detailField(@ApiParam("房产类型") @RequestParam("houseType") String houseType) {
        Map<String, String> map = new HashMap<>();
        HouseBaseVo owner = new HouseBaseVo();
        HouseTypeEnum houseTypeEnum = HouseTypeEnum.getInstance(houseType);
        String houseJson = "";
        StringBuffer jsonBuffer = new StringBuffer();
        if ("1".equals(houseType)) {
            houseJson = jsonBuffer.append(CommonUtils.toJson(owner)).toString();
        } else {
            jsonBuffer.append(CommonUtils.toJson(owner).replace("}", ","));
        }

        switch (houseTypeEnum) {
            case PRECINCT:
                OwnerProjectInfoVo projectInfoVo = new OwnerProjectInfoVo();
                jsonBuffer.append(
                        CommonUtils.toJson(projectInfoVo).replace("{", "").replace("}", ",").replace("\"houseId\":0,", ""));
                OwnerProjectExtendInfoVo projectExtendInfoVo = new OwnerProjectExtendInfoVo();
                jsonBuffer.append(CommonUtils.toJson(projectExtendInfoVo).replace("{", "").replace("}", ",")
                        .replace("\"houseId\":0,", ""));
                OwnerProjectPropertyInfoVo projectPropertyInfoVo = new OwnerProjectPropertyInfoVo();
                houseJson = jsonBuffer
                        .append(CommonUtils.toJson(projectPropertyInfoVo).replace("{", "").replace("\"houseId\":0,", ""))
                        .toString();
                break;
            case BUILDING:
                OwnerBuildingInfoVo buildingInfoVo = new OwnerBuildingInfoVo();
                jsonBuffer.append(CommonUtils.toJson(buildingInfoVo).replace("{", "").replace("}", ",")
                        .replace("\"houseId\":0,", ""));
                OwnerBuildingExtendInfoVo buildingExtendInfoVo = new OwnerBuildingExtendInfoVo();
                houseJson = jsonBuffer
                        .append(CommonUtils.toJson(buildingExtendInfoVo).replace("{", "").replace("\"houseId\":0,", ""))
                        .toString();
                break;
            case UNIT:
                OwnerUnitInfoVo unitInfoVo = new OwnerUnitInfoVo();
                houseJson = jsonBuffer.append(CommonUtils.toJson(unitInfoVo).replace("{", "").replace("\"houseId\":0,", ""))
                        .toString();
                break;
            case CLUSTER:
                OwnerClusterInfoVo clusterInfoVo = new OwnerClusterInfoVo();
                houseJson = jsonBuffer
                        .append(CommonUtils.toJson(clusterInfoVo).replace("{", "").replace("\"houseId\":0,", ""))
                        .toString();
                break;
            case ROOM:
                OwnerRoomInfoVo roomInfoVo = new OwnerRoomInfoVo();
                jsonBuffer.append(
                        CommonUtils.toJson(roomInfoVo).replace("{", "").replace("}", ",").replace("\"houseId\":0,", ""));
                OwnerRoomExtendInfoVo roomExtendInfoVo = new OwnerRoomExtendInfoVo();
                houseJson = jsonBuffer
                        .append(CommonUtils.toJson(roomExtendInfoVo).replace("{", "").replace("\"houseId\":0,", ""))
                        .toString();
                break;
            case GARAGE:
                OwnerGarageInfoVo garageInfoVo = new OwnerGarageInfoVo();
                houseJson = jsonBuffer
                        .append(CommonUtils.toJson(garageInfoVo).replace("{", "").replace("\"houseId\":0,", "")).toString();
                break;
            case CARPORT:
                OwnerCarportInfoVo carportInfoVo = new OwnerCarportInfoVo();
                jsonBuffer.append(
                        CommonUtils.toJson(carportInfoVo).replace("{", "").replace("}", ",").replace("\"houseId\":0,", ""));
                OwnerCarportExtendInfoVo carportExtendInfoVo = new OwnerCarportExtendInfoVo();
                houseJson = jsonBuffer
                        .append(CommonUtils.toJson(carportExtendInfoVo).replace("{", "").replace("\"houseId\":0,", ""))
                        .toString();
                break;
            case PUBLICAREA:
                OwnerPublicAreaVo publicAreaVo = new OwnerPublicAreaVo();
                houseJson = jsonBuffer
                        .append(CommonUtils.toJson(publicAreaVo).replace("{", "").replace("\"houseId\":0,", "")).toString();
                break;
            default:
                break;
        }
        map.put("houseJson", houseJson);
        return new RestResult<>(map);
    }

    @ApiOperation(value = "导入房产")
    @RequestMapping(value = "/import-house", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> importHouse(MultipartFile file,
                                                       @ApiParam("1 覆盖导入，0 不导入") @RequestParam("updateFlag") Byte updateFlag, @RequestParam("guid") String guid) {
        Map<Integer, String> hashMap = null;
        List<Map<Integer, String>> errListMessage = new ArrayList<Map<Integer, String>>();
        /*
         * try { if(true){ for(int i =1;i <=100; i++){ Thread.sleep(60); hashMap
         * = new HashMap<String,Object>(); log.info("fasong+"+i);
         * webSocketRemoteService.returnProgress("superAdmin"+guid, i+"S"+100);
         * log.info("fasongwanbi+"+i); } return new RestResult<Map<String,
         * Object>>(hashMap); } } catch (InterruptedException e1) { return new
         * RestResult<Map<String, Object>>(hashMap);
         *
         * }
         */
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        List<ArrayList<Object>> rowList = null;
        // 解析上传的excel文件
        if (file != null) {
            try {
                if (file.getInputStream() instanceof FileInputStream) {
                    FileInputStream is = (FileInputStream) file.getInputStream();
                    rowList = ExcelUploadUtil.readExcel(file.getOriginalFilename(), is);
                }
            } catch (IOException e) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "模板解析失败");
            }
        } else {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "请上传模板");
        }
        int progress = 0;
        // List<Map<String, Object>> importResultList = new ArrayList<>();
        Map<String, Integer> importResult = new HashMap<>();
        Map<String, Object> resultMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(rowList)) {
            rowList.remove(0);
            rowList.remove(0);
            rowList.remove(0);
            NsCoreDictionaryVo proTypeDic = getDictionary("proType", organizationId);
            NsCoreDictionaryVo certificateTypeDic = getDictionary("certificateType", organizationId);
            NsCoreDictionaryVo maritalStatusDic = getDictionary("maritalStatus", organizationId);
            NsCoreDictionaryVo educationDic = getDictionary("education", organizationId);
            NsCoreDictionaryVo regionDic = getDictionary("region", organizationId);
            NsCoreDictionaryVo nationDic = getDictionary("nation", organizationId);
            NsCoreDictionaryVo ownerTypeDic = getDictionary("ownerType", organizationId);
            NsCoreDictionaryVo roomTypeDic = getDictionary("roomType", organizationId);
            NsCoreDictionaryVo carPortTypeDic = getDictionary("carPortType", organizationId);
            NsCoreDictionaryVo bankDic = getDictionary("bank", organizationId);
            NsCoreDictionaryVo importResultDic = getDictionary("importResult", organizationId);
            Map<String, Object> importResultMap = new HashMap<>();
            importResultDic.getDictionaryitemVos().forEach(itemVo -> {
                importResultMap.put(itemVo.getDictionaryitemItemcode(), itemVo.getDictionaryitemItemname());
            });
            Map<String, NsCoreDictionaryVo> dicMap = new HashMap<>();
            dicMap.put("proType", proTypeDic);
            dicMap.put("certificateType", certificateTypeDic);
            dicMap.put("ownerType", ownerTypeDic);
            dicMap.put("maritalStatus", maritalStatusDic);
            dicMap.put("education", educationDic);
            dicMap.put("region", regionDic);
            dicMap.put("nation", nationDic);
            dicMap.put("roomTypeDic", roomTypeDic);
            dicMap.put("carPortTypeDic", carPortTypeDic);
            dicMap.put("bankDic", bankDic);
            Map<String, Object> excelValueMap = null;
            // 获取当前操作人所属公司下所有部门
            RestResult<List<NsSystemOrganizationVo>> orgResult = systemRemoteService.listAllOrganization(enterpriseId,
                    organizationId);
            List<NsSystemOrganizationVo> orgList = orgResult.getResultData();
            // 获取部门的项目权限
            RestResult<List<NsSystemRoleHouse>> roleHouseResult = systemRemoteService.getDataPermission(userId);
            List<Long> orgHouseIdList = new ArrayList<>();
            if (roleHouseResult != null) {
                List<NsSystemRoleHouse> roleHouseList = roleHouseResult.getResultData();
                if (!CollectionUtils.isEmpty(roleHouseList)) {
                    for (NsSystemRoleHouse nsSystemRoleHouse : roleHouseList) {
                        orgHouseIdList.add(nsSystemRoleHouse.getHouseId());
                    }
                }
            }
            // if (CollectionUtils.isEmpty(orgHouseIdList)) {
            // BizException.fail(ResultCodeEnum.UNAUTHORIZED, "暂无项目权限");
            // }
            String importId = UUID.randomUUID().toString();
            resultMap.put("importId", importId);
            String date = DateUtils.getNowDate(DateUtils.YYYYMMDDCROSSHHMMSSSSS_NOSYBOL);
            int len = rowList.size();
            for (int i = 0; i < rowList.size(); i++) {
                progress++;
                hashMap = new HashMap<>();
                try {
                    ImportLogEntity importLogEntity = new ImportLogEntity();
                    importLogEntity.setImportId(importId);
                    importLogEntity.setImportName(userName + date);
                    importLogEntity.setImportDate(new Date());
                    importLogEntity.setImportIndex(i + 1);
                    importLogEntity.setUserId(userId);
                    importLogEntity.setUserName(userName);
                    importLogEntity.setEnterpriseId(enterpriseId);
                    importLogEntity.setOrganizationId(organizationId);
                    excelValueMap = getExcelValue(rowList.get(i), dicMap, orgList);
                    // resultMap.put("result", excelValueMap.get("result"));
                    // resultMap.put("message", excelValueMap.get("message"));

                    if (Boolean.TRUE.equals(excelValueMap.get("result"))) {
                        // 导入房产
                        Map<String, Object> houseMap = houseService.importHouse(rowList.get(i), userId, userName,
                                updateFlag, excelValueMap, orgHouseIdList, orgList);
                        Long houseId = (Long) houseMap.get("houseId");
                        if (Constants.RESULT_SUCCESS.equals(houseMap.get("resultType"))) {
                            // 导入客户
                            Map<String, Object> ownerMap = houseService.importCustomer(rowList.get(i), enterpriseId,
                                    organizationId, userId, userName, updateFlag, houseId, excelValueMap);
                            Long ownerId = (Long) ownerMap.get("ownerId");
                            if (Constants.RESULT_SUCCESS.equals(ownerMap.get("resultType"))) {
                                // 导入房态操作
                                Map<String, Object> detailMap = houseService.importHouseStageOperate(rowList.get(i),
                                        enterpriseId, organizationId, userId, userName, updateFlag, houseId, ownerId,
                                        excelValueMap);
                                if (Constants.RESULT_SUCCESS.equals(detailMap.get("resultType"))) {
                                    // 保存导入日志
                                    importLogEntity.setImportResult(Constants.RESULT_SUCCESS);
                                    importLogEntity.setImportResultName(
                                            (String) importResultMap.get(importLogEntity.getImportResult().toString()));
                                    importLogEntity.setImportRemark("导入成功");
                                } else {
                                    importLogEntity.setImportResult((Byte) detailMap.get("result"));
                                    importLogEntity.setImportResultName(
                                            (String) importResultMap.get(importLogEntity.getImportResult().toString()));
                                    importLogEntity.setImportRemark((String) detailMap.get("message"));
                                }
                            } else {
                                importLogEntity.setImportResult((Byte) ownerMap.get("result"));
                                importLogEntity.setImportResultName(
                                        (String) importResultMap.get(importLogEntity.getImportResult().toString()));
                                importLogEntity.setImportRemark((String) ownerMap.get("message"));
                            }
                        } else {
                            importLogEntity.setImportResult((Byte) houseMap.get("result"));
                            importLogEntity.setImportResultName(
                                    (String) importResultMap.get(importLogEntity.getImportResult().toString()));
                            importLogEntity.setImportRemark((String) houseMap.get("message"));
                        }
                        importLogEntity.setPrecinctId((Long) houseMap.get("precinctId"));
                        importLogEntity.setHouseId(houseId);
                        importLogEntity.setHouseFullName((String) houseMap.get("houseFullName"));

                    } else {
                        importLogEntity.setImportResult((Byte) excelValueMap.get("result"));
                        importLogEntity.setImportResultName(
                                (String) importResultMap.get(importLogEntity.getImportResult().toString()));
                        String mString = (String) excelValueMap.get("message");
                        importLogEntity.setImportRemark(mString.substring(0, mString.length() - 1));
                        hashMap.put(i + 1, mString);
                        errListMessage.add(hashMap);
                    }
                    importLogEntity.setPrecinctName((String) excelValueMap.get("precinctName"));
                    importLogEntity.setHouseName((String) excelValueMap.get("houseName"));
                    importLogEntity.setHouseShortName((String) excelValueMap.get("houseShortName"));
                    importLogEntity.setHouseNo((String) excelValueMap.get("houseNo"));
                    // logList.add(importLogEntity);
                    logRemoteService.sendImportLog(importLogEntity);
                    // 返回导入数据的进度
                    importResult.put("importTotal", rowList.size());
                    importResult.put("progress", progress);
                    // double tempProgress = new BigDecimal(total).divide(new
                    // BigDecimal(rowList.size()), 2,
                    // RoundingMode.HALF_UP).doubleValue();
                    // int progress = (int) (tempProgress * 100);
                    String importResultJson = JSONObject.toJSONString(importResult);
                    System.out.println("循环第" + (i + 1));
                    webSocketRemoteService.returnProgress(userName.toString() + guid, progress + "S" + len);
                } catch (Exception e) {
                    hashMap.put(i + 1, e.getMessage());
                    errListMessage.add(hashMap);
                    webSocketRemoteService.returnProgress(userName.toString() + guid, progress + "S" + len);
                    e.printStackTrace();
                }

                // importResultList.add(resultMap);
            }
            // 从redis中删除房产树
            String fieldRedisKey = RedisKeysConstants.REDIS_HOUSE_TREE_PREFIX + "_" + enterpriseId.toString() + "_"
                    + organizationId.toString();
            redisUtil.delete(fieldRedisKey);
        } else {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "模板没有可用数据");
        }
        // restResult = new RestResult<>(index);
        resultMap.put("errorRecord", errListMessage);
        return new RestResult<>(resultMap);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation(value = "导出房产")
    @RequestMapping(value = "/export-house", method = RequestMethod.POST)
    public RestResult<Object> exportHouse(@RequestBody SearchVo searchConditionVo, HttpServletResponse response)
            throws Exception {
        Long start = new Date().getTime();
        String funcId = LoginDataHelper.getFuncId();
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchConditionVo.setEnterpriseId(enterpriseId);
        searchConditionVo.setOrganizationId(organizationId);
        PageInfo<HouseListVo> result = houseService.listHouse(searchConditionVo, null, false);
        NsCoreResourcecolumnVo nsCoreResourcecolumnVo = new NsCoreResourcecolumnVo();
        nsCoreResourcecolumnVo.setEnterpriseId(enterpriseId);
        nsCoreResourcecolumnVo.setOrganizationId(organizationId);
        nsCoreResourcecolumnVo.setResourcecolumnFuncinfoId(funcId);
        // 从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
        String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_"
                + organizationId.toString() + "_" + funcId;
        Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
        RestResult<Map<String, Object>> columnResult = null;
        Map<String, Object> columnMap = null;
        if (filedRedisObject == null) {
            columnResult = systemRemoteService.listColumnForRemote(nsCoreResourcecolumnVo);
            columnMap = columnResult.getResultData();
        } else {
            columnMap = (Map<String, Object>) filedRedisObject;
        }
        if (columnMap != null) {
            String json = JSONObject.toJSONString(columnMap.get("columns"));
            List<NsCoreResourcecolumnVo> columnList = JSONArray.parseArray(json, NsCoreResourcecolumnVo.class);
            if (!CollectionUtils.isEmpty(columnList)) {
                columnList = columnList.stream()
                        .filter(column -> Constants.FALSE.toString().equals(column.getResourcecolumnHidden()))
                        .collect(Collectors.toList());
                ExcelHelper.exportExcel(result.getList(), columnList, "房产信息", response);
            }
        }
        Long end = new Date().getTime();
        Long time = end - start;
        System.out.println("exportHouse========" + time);
        return new RestResult<>();
    }

    private List<CustomerVo> getCustomer(Long ownerId, Long organizationId) throws Exception {
        List<CustomerVo> customerVoList = new ArrayList<>(0);
        if (!CommonUtils.isObjectEmpty(ownerId)) {
            SearchVo searchVo = new SearchVo();
            List<FilterEntity> list = new ArrayList<>();
            FilterEntity filterEntity = new FilterEntity();
            filterEntity.setOrganizationId(organizationId);
            filterEntity.setType(Constants.FIELD_TYPE_TEXT);
            filterEntity.setFieldName("ownerId");
            filterEntity.setFieldValue(ownerId.toString());
            list.add(filterEntity);
            searchVo.setFilterList(list);
            PageInfo<OwnerCustomerResult> pageInfo = customerService.listPage(searchVo, null, false);
            for (OwnerCustomerResult OwnerCustomerResult : pageInfo.getList()) {
                CustomerVo customerVo = new CustomerVo();
                BeanUtils.copyProperties(OwnerCustomerResult, customerVo);
                customerVoList.add(customerVo);
            }
        }
        return customerVoList;
    }

    private NsCoreDictionaryVo getDictionary(String code, Long organizationId) {
        NsCoreDictionaryVo dictionaryVo = null;
        NsCoreDictionary dictionary = new NsCoreDictionary();
        dictionary.setDictionaryDdcode(code);
        dictionary.setOrganizationId(organizationId);
        RestResult<NsCoreDictionaryVo> dictionaryResult = systemRemoteService.getDictionary(dictionary);
        if (dictionaryResult != null) {
            dictionaryVo = dictionaryResult.getResultData();
        }
        return dictionaryVo;
    }

    private Map<String, Object> getExcelValue(List<Object> list, Map<String, NsCoreDictionaryVo> dicMap,
                                              List<NsSystemOrganizationVo> orgList) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", Constants.RESULT_SUCCESS);
        if (CollectionUtils.isEmpty(list)) {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "excel数据不存在");
        } else {
            Map<String, Object> checkExcelValueMap = checkExcelValue(list, dicMap, orgList);
            map.put("precinctName", checkExcelValueMap.get("precinctName"));
            map.put("houseName", checkExcelValueMap.get("houseName"));
            map.put("houseNo", checkExcelValueMap.get("houseNo"));
            map.put("houseShortName", checkExcelValueMap.get("houseShortName"));
            if (Constants.RESULT_ERROR.equals((Byte) checkExcelValueMap.get("result"))) {
                map.put("result", Constants.RESULT_ERROR);
                map.put("message", checkExcelValueMap.get("message"));
                return map;
            }
            List<String> roomTypeList = new ArrayList<>();
            List<String> carPortTypeList = new ArrayList<>();
            dicMap.get("roomTypeDic").getDictionaryitemVos().forEach(itemVo -> {
                roomTypeList.add(itemVo.getDictionaryitemItemname());
            });
            dicMap.get("carPortTypeDic").getDictionaryitemVos().forEach(itemVo -> {
                carPortTypeList.add(itemVo.getDictionaryitemItemname());
            });
            if (CollectionUtils.isEmpty(roomTypeList)) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "房间类型不存在");
            }
            if (CollectionUtils.isEmpty(carPortTypeList)) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "车位类型不存在");
            }
            map.put("roomTypeList", roomTypeList);
            map.put("carPortTypeList", carPortTypeList);
            if (list.size() == 36) {
                // 基础版
                map.put("orgName", list.get(0));
                map.put("areaName", list.get(1));
                map.put("precinctName", list.get(2));
                NsCoreDictionaryVo proType = dicMap.get("proType");
                String proTypeId = OwnerUtils.getDicValue(proType, (String) list.get(3));
                map.put("proTypeId", proTypeId);
                String roomType = (String) list.get(9);
                map.put("roomType", roomType);

                // 保存房产
                if (roomTypeList.contains(roomType)) {
                    NsCoreDictionaryVo roomTypeDic = dicMap.get("roomTypeDic");
                    map.put("clusterName", list.get(4));
                    map.put("buildingName", list.get(5));
                    map.put("unitName", list.get(6));
                    map.put("roomName", list.get(7));
                    map.put("roomShortName", list.get(8));
                    map.put("roomTypeId", OwnerUtils.getDicValue(roomTypeDic, roomType));
                    map.put("clusterNo", list.get(12));
                    map.put("buildingNo", list.get(13));
                    map.put("unitNo", list.get(14));
                    map.put("roomNo", list.get(15));
                } else if (carPortTypeList.contains(roomType)) {
                    NsCoreDictionaryVo carPortTypeDic = dicMap.get("carPortTypeDic");
                    List<Object> garageNameList = new ArrayList<>();
                    garageNameList.add(list.get(4));
                    garageNameList.add(list.get(5));
                    garageNameList.add(list.get(6));
                    List<Object> garageNoList = new ArrayList<>();
                    garageNoList.add(list.get(12));
                    garageNoList.add(list.get(13));
                    garageNoList.add(list.get(14));
                    map.put("garageNameList", garageNameList);
                    map.put("garageNoList", garageNoList);
                    map.put("carPortName", list.get(7));
                    map.put("carPortShortName", list.get(8));
                    map.put("carPortTypeId", OwnerUtils.getDicValue(carPortTypeDic, roomType));

                }
                if (CommonUtils.isNumber((String) list.get(10)) || CommonUtils.isDecimal((String) list.get(10))) {
                    map.put("chargingArea", list.get(10));
                } else {
                    map.put("chargingArea", "0");
                }
                map.put("assistChargingArea", "0");
                map.put("buildingArea", "0");
                map.put("insideArea", "0");
                map.put("poolArea", "0");
                map.put("gardenArea", "0");
                map.put("basementArea", "0");
                map.put("giftArea", "0");
                map.put("proNo", list.get(11));
                map.put("carPortNo", list.get(15));
                map.put("ownerName", list.get(16));
                String certificateTypeId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(18))) {
                    NsCoreDictionaryVo certificateTypeVo = dicMap.get("certificateType");
                    certificateTypeId = OwnerUtils.getDicValue(certificateTypeVo, (String) list.get(18));
                    if (CommonUtils.isNullOrBlank(certificateTypeId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", "证件类型不存在");
                    }
                }
                map.put("certificateType", certificateTypeId);
                map.put("certificateTypeName", (String) list.get(18));
                String certificateType = (String) list.get(18);
                if ("统一社会信用代码（营业执照）".equals(certificateType) || "组织机构代码".equals(certificateType)) {
                    // 公司
                    map.put("ownerType", Constants.OWNER_TYPE_ENTERPRISE);
                    map.put("ownerTypeName",
                            OwnerUtils.getDicName(dicMap.get("ownerType"), Constants.OWNER_TYPE_ENTERPRISE));
                    map.put("companyPhone", list.get(17));
                    map.put("linkman", list.get(20));
                    map.put("linkmanPhone", list.get(21));
                } else {
                    // 个人
                    map.put("ownerType", Constants.OWNER_TYPE_PERSON);
                    map.put("ownerTypeName",
                            OwnerUtils.getDicName(dicMap.get("ownerType"), Constants.OWNER_TYPE_PERSON));
                    map.put("mobile", list.get(17));
                    map.put("emergencyContact", list.get(20));
                    map.put("emergencyContactPhone", list.get(21));
                }
                map.put("certificate", list.get(19));
                map.put("deliveryTime", list.get(22));
                map.put("salesDate", list.get(23));
                map.put("takeDate", list.get(24));
                map.put("decorateStartDate", list.get(25));
                map.put("decorateEndDate", list.get(26));
                map.put("checkInDate", list.get(27));
                map.put("rentStartDate", list.get(28));
                map.put("rentEndDate", list.get(29));
                map.put("rentOwnerName", list.get(30));

                String rentCertificateTypeId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(32))) {
                    NsCoreDictionaryVo certificateTypeVo = dicMap.get("certificateType");
                    rentCertificateTypeId = OwnerUtils.getDicValue(certificateTypeVo, (String) list.get(32));
                }
                map.put("rentCertificateType", rentCertificateTypeId);
                map.put("rentCertificateTypeName", (String) list.get(32));
                String rentCertificateType = (String) list.get(32);
                if ("统一社会信用代码（营业执照）".equals(rentCertificateType) || "组织机构代码".equals(rentCertificateType)) {
                    // 公司
                    map.put("rentOwnerType", Constants.OWNER_TYPE_ENTERPRISE);
                    map.put("rentOwnerTypeName",
                            OwnerUtils.getDicName(dicMap.get("ownerType"), Constants.OWNER_TYPE_ENTERPRISE));
                    map.put("rentCompanyPhone", list.get(31));
                    map.put("rentLinkman", list.get(34));
                    map.put("rentLinkmanPhone", list.get(35));
                } else {
                    // 个人
                    map.put("rentOwnerType", Constants.OWNER_TYPE_PERSON);
                    map.put("rentOwnerTypeName",
                            OwnerUtils.getDicName(dicMap.get("ownerType"), Constants.OWNER_TYPE_PERSON));
                    map.put("rentMobile", list.get(31));
                    map.put("rentEmergencyContact", list.get(34));
                    map.put("rentEmergencyContactPhone", list.get(35));
                }
                map.put("rentCertificate", list.get(33));
            } else if (list.size() == 71) {
                // 高级版
                map.put("orgName", list.get(0));
                map.put("areaName", list.get(1));
                map.put("precinctName", list.get(2));
                NsCoreDictionaryVo proType = dicMap.get("proType");
                String proTypeId = OwnerUtils.getDicValue(proType, (String) list.get(3));
                map.put("proTypeId", proTypeId);
                String roomType = (String) list.get(9);
                map.put("roomType", roomType);

                if (roomTypeList.contains(roomType)) {
                    NsCoreDictionaryVo roomTypeDic = dicMap.get("roomTypeDic");
                    map.put("clusterName", list.get(4));
                    map.put("buildingName", list.get(5));
                    map.put("unitName", list.get(6));
                    map.put("clusterNo", list.get(19));
                    map.put("buildingNo", list.get(20));
                    map.put("unitNo", list.get(21));
                    map.put("roomName", list.get(7));
                    map.put("roomShortName", list.get(8));
                    map.put("roomTypeId", OwnerUtils.getDicValue(roomTypeDic, roomType));
                    map.put("roomNo", list.get(22));
                } else if (carPortTypeList.contains(roomType)) {
                    NsCoreDictionaryVo carPortTypeDic = dicMap.get("carPortTypeDic");
                    List<Object> garageNameList = new ArrayList<>();
                    garageNameList.add(list.get(4));
                    garageNameList.add(list.get(5));
                    garageNameList.add(list.get(6));
                    List<Object> garageNoList = new ArrayList<>();
                    garageNoList.add(list.get(19));
                    garageNoList.add(list.get(20));
                    garageNoList.add(list.get(21));
                    map.put("garageNameList", garageNameList);
                    map.put("garageNoList", garageNoList);
                    map.put("carPortName", list.get(7));
                    map.put("carPortShortName", list.get(8));
                    map.put("carPortTypeId", OwnerUtils.getDicValue(carPortTypeDic, roomType));
                    map.put("carPortNo", list.get(22));
                }
                if (CommonUtils.isNumber((String) list.get(10)) || CommonUtils.isDecimal((String) list.get(10))) {
                    map.put("chargingArea", list.get(10));
                } else {
                    map.put("chargingArea", "0");
                }
                if (CommonUtils.isNumber((String) list.get(11)) || CommonUtils.isDecimal((String) list.get(11))) {
                    map.put("assistChargingArea", list.get(11));
                } else {
                    map.put("assistChargingArea", "0");
                }
                if (CommonUtils.isNumber((String) list.get(12)) || CommonUtils.isDecimal((String) list.get(12))) {
                    map.put("buildingArea", list.get(12));
                } else {
                    map.put("buildingArea", "0");
                }
                if (CommonUtils.isNumber((String) list.get(13)) || CommonUtils.isDecimal((String) list.get(13))) {
                    map.put("insideArea", list.get(13));
                } else {
                    map.put("insideArea", "0");
                }
                if (CommonUtils.isNumber((String) list.get(14)) || CommonUtils.isDecimal((String) list.get(14))) {
                    map.put("poolArea", list.get(14));
                } else {
                    map.put("poolArea", "0");
                }
                if (CommonUtils.isNumber((String) list.get(15)) || CommonUtils.isDecimal((String) list.get(15))) {
                    map.put("gardenArea", list.get(15));
                } else {
                    map.put("gardenArea", "0");
                }
                if (CommonUtils.isNumber((String) list.get(16)) || CommonUtils.isDecimal((String) list.get(16))) {
                    map.put("basementArea", list.get(16));
                } else {
                    map.put("basementArea", "0");
                }
                if (CommonUtils.isNumber((String) list.get(17)) || CommonUtils.isDecimal((String) list.get(17))) {
                    map.put("giftArea", list.get(17));
                } else {
                    map.put("giftArea", "0");
                }
                map.put("proNo", list.get(18));
                map.put("ownerName", list.get(23));
                String certificateTypeId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(25))) {
                    NsCoreDictionaryVo certificateTypeVo = dicMap.get("certificateType");
                    certificateTypeId = OwnerUtils.getDicValue(certificateTypeVo, (String) list.get(25));
                }
                map.put("certificateType", certificateTypeId);
                map.put("certificateTypeName", (String) list.get(25));
                String certificateType = (String) list.get(25);
                if ("统一社会信用代码（营业执照）".equals(certificateType) || "组织机构代码".equals(certificateType)) {
                    // 公司
                    map.put("ownerType", Constants.OWNER_TYPE_ENTERPRISE);
                    map.put("ownerTypeName",
                            OwnerUtils.getDicName(dicMap.get("ownerType"), Constants.OWNER_TYPE_ENTERPRISE));
                    map.put("companyPhone", list.get(24));
                    map.put("linkman", list.get(32));
                    map.put("linkmanPhone", list.get(33));
                    map.put("fax", list.get(34));
                } else {
                    // 个人
                    map.put("ownerType", Constants.OWNER_TYPE_PERSON);
                    map.put("ownerTypeName",
                            OwnerUtils.getDicName(dicMap.get("ownerType"), Constants.OWNER_TYPE_PERSON));
                    map.put("mobile", list.get(24));
                    map.put("nativePlace", list.get(27));
                    String maritalStatusId = null;
                    if (!CommonUtils.isNullOrBlank((String) list.get(28))) {
                        NsCoreDictionaryVo maritalStatusVo = dicMap.get("maritalStatus");
                        maritalStatusId = OwnerUtils.getDicValue(maritalStatusVo, (String) list.get(28));
                    }
                    map.put("maritalStatus", maritalStatusId);
                    map.put("maritalStatusName", (String) list.get(28));

                    String educationId = null;
                    if (!CommonUtils.isNullOrBlank((String) list.get(29))) {
                        NsCoreDictionaryVo educationVo = dicMap.get("education");
                        educationId = OwnerUtils.getDicValue(educationVo, (String) list.get(29));
                    }
                    map.put("education", educationId);
                    map.put("educationName", (String) list.get(29));
                    String regionId = null;
                    if (!CommonUtils.isNullOrBlank((String) list.get(30))) {
                        NsCoreDictionaryVo regionVo = dicMap.get("region");
                        regionId = OwnerUtils.getDicValue(regionVo, (String) list.get(30));
                    }
                    map.put("region", regionId);
                    map.put("regionName", (String) list.get(30));

                    String nationId = null;
                    if (!CommonUtils.isNullOrBlank((String) list.get(31))) {
                        NsCoreDictionaryVo nationVo = dicMap.get("nation");
                        nationId = OwnerUtils.getDicValue(nationVo, (String) list.get(31));
                    }
                    map.put("nation", nationId);
                    map.put("nationName", (String) list.get(31));
                    map.put("emergencyContact", list.get(32));
                    map.put("emergencyContactPhone", list.get(33));
                    map.put("phone", list.get(34));
                }
                map.put("certificate", list.get(26));
                String bankId = null;
                NsCoreDictionaryVo bankVo = dicMap.get("bankDic");
                bankId = OwnerUtils.getDicValue(bankVo, (String) list.get(35));
                map.put("bankId", bankId);
                map.put("bankName", list.get(35));
                map.put("bankAddress", list.get(36));
                map.put("accountName", list.get(37));
                map.put("account", list.get(38));
                if (CommonUtils.isObjectEmpty(list.get(39))) {
                    map.put("isEnable", "已启用");
                } else {
                    map.put("isEnable", list.get(39));
                }
                map.put("protocolNumber", list.get(40));
                map.put("collectionNumber", list.get(41));
                map.put("bankRemark", list.get(42));
                map.put("deliveryTime", list.get(43));
                map.put("salesDate", list.get(44));
                map.put("takeDate", list.get(45));
                map.put("decorateStartDate", list.get(46));
                map.put("decorateEndDate", list.get(47));
                map.put("checkInDate", list.get(48));
                map.put("rentStartDate", list.get(49));
                map.put("rentEndDate", list.get(50));
                map.put("rentOwnerName", list.get(51));
                String rentCertificateTypeId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(53))) {
                    NsCoreDictionaryVo certificateTypeVo = dicMap.get("certificateType");
                    certificateTypeId = OwnerUtils.getDicValue(certificateTypeVo, (String) list.get(53));
                }
                map.put("rentCertificateType", rentCertificateTypeId);
                map.put("rentCertificateTypeName", (String) list.get(53));
                String rentCertificateType = (String) list.get(53);
                if ("统一社会信用代码（营业执照）".equals(rentCertificateType) || "组织机构代码".equals(rentCertificateType)) {
                    // 公司
                    map.put("rentOwnerType", Constants.OWNER_TYPE_ENTERPRISE);
                    map.put("rentOwnerTypeName",
                            OwnerUtils.getDicName(dicMap.get("ownerType"), Constants.OWNER_TYPE_ENTERPRISE));
                    map.put("rentCompanyPhone", list.get(52));
                    map.put("rentLinkman", list.get(60));
                    map.put("rentLinkmanPhone", list.get(61));
                    map.put("rentFax", list.get(62));
                } else {
                    // 个人
                    map.put("rentOwnerType", Constants.OWNER_TYPE_PERSON);
                    map.put("rentOwnerTypeName",
                            OwnerUtils.getDicName(dicMap.get("ownerType"), Constants.OWNER_TYPE_PERSON));
                    map.put("rentMobile", list.get(52));
                    map.put("rentNativePlace", list.get(55));
                    String maritalStatusId = null;
                    if (!CommonUtils.isNullOrBlank((String) list.get(56))) {
                        NsCoreDictionaryVo maritalStatusVo = dicMap.get("maritalStatus");
                        maritalStatusId = OwnerUtils.getDicValue(maritalStatusVo, (String) list.get(56));
                    }
                    map.put("rentMaritalStatus", maritalStatusId);
                    map.put("rentMaritalStatusName", (String) list.get(56));
                    String educationId = null;
                    if (!CommonUtils.isNullOrBlank((String) list.get(57))) {
                        NsCoreDictionaryVo educationVo = dicMap.get("education");
                        educationId = OwnerUtils.getDicValue(educationVo, (String) list.get(57));
                    }
                    map.put("rentEducation", educationId);
                    map.put("rentEducationName", (String) list.get(57));
                    String regionId = null;
                    if (!CommonUtils.isNullOrBlank((String) list.get(58))) {
                        NsCoreDictionaryVo regionVo = dicMap.get("region");
                        regionId = OwnerUtils.getDicValue(regionVo, (String) list.get(58));
                    }
                    map.put("rentRegion", regionId);
                    map.put("rentRegionName", (String) list.get(58));
                    String nationId = null;
                    if (!CommonUtils.isNullOrBlank((String) list.get(59))) {
                        NsCoreDictionaryVo nationVo = dicMap.get("nation");
                        nationId = OwnerUtils.getDicValue(nationVo, (String) list.get(59));
                    }
                    map.put("rentNation", nationId);
                    map.put("rentNationName", (String) list.get(59));
                    map.put("rentEmergencyContact", list.get(60));
                    map.put("rentEmergencyContactPhone", list.get(61));
                    map.put("rentPhone", list.get(62));
                }
                map.put("rentCertificate", list.get(54));
                String rentBankId = null;
                NsCoreDictionaryVo rentBankVo = dicMap.get("bankDic");
                rentBankId = OwnerUtils.getDicValue(rentBankVo, (String) list.get(63));
                map.put("rentBankId", rentBankId);
                map.put("rentBankName", list.get(63));
                map.put("rentBankAddress", list.get(64));
                map.put("rentAccountName", list.get(65));
                map.put("rentAccount", list.get(66));
                if (CommonUtils.isObjectEmpty(list.get(67))) {
                    map.put("rentIsEnable", "已启用");
                } else {
                    map.put("rentIsEnable", list.get(67));
                }
                map.put("rentProtocolNumber", list.get(68));
                map.put("rentCollectionNumber", list.get(69));
                map.put("rentBankRemark", list.get(70));
            } else {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "excel数据不完整");
            }
        }
        return map;
    }

    private Map<String, Object> checkExcelValue(List<Object> list, Map<String, NsCoreDictionaryVo> dicMap,
                                                List<NsSystemOrganizationVo> orgList) {
        Map<String, Object> map = new HashMap<>();
        map.put("result", true);
        map.put("message", "");

        if (list.size() > 0 && CommonUtils.isObjectEmpty(list.get(0))) {
            map.put("result", Constants.RESULT_ERROR);
            map.put("message", getMessage((String) map.get("message"), "部门简称不能为空"));
        } else {
            boolean flag = orgList.stream()
                    .anyMatch(org -> org.getOrganizationShortName().equals((String) list.get(0)));
            if (!flag) {
                map.put("result", Constants.RESULT_ERROR);
                map.put("message", getMessage((String) map.get("message"), "部门不存在"));
            }
        }
        if (list.size() > 2 && CommonUtils.isObjectEmpty(list.get(2))) {
            map.put("result", Constants.RESULT_ERROR);
            map.put("message", getMessage((String) map.get("message"), "项目名称不能为空"));
        }
        if (list.size() > 3 && CommonUtils.isObjectEmpty(list.get(3))) {
            map.put("result", Constants.RESULT_ERROR);
            map.put("message", getMessage((String) map.get("message"), "项目类型不能为空"));
        } else {
            NsCoreDictionaryVo proType = dicMap.get("proType");
            String proTypeId = OwnerUtils.getDicValue(proType, list.size() > 3 ? (String) list.get(3) : "");
            if (CommonUtils.isNullOrBlank(proTypeId)) {
                map.put("result", Constants.RESULT_ERROR);
                map.put("message", getMessage((String) map.get("message"), "项目类型不存在"));
            }
        }
        if (list.size() > 7 && CommonUtils.isObjectEmpty(list.get(7))) {
            map.put("result", Constants.RESULT_ERROR);
            map.put("message", getMessage((String) map.get("message"), "房产名称/车位名称不能为空"));
        }
        if (list.size() > 8 && CommonUtils.isObjectEmpty(list.get(8))) {
            map.put("result", Constants.RESULT_ERROR);
            map.put("message", getMessage((String) map.get("message"), "房产简称/车位简称不能为空"));
        }
        if (list.size() > 9 && CommonUtils.isObjectEmpty(list.get(9))) {
            map.put("result", Constants.RESULT_ERROR);
            map.put("message", "房产类型不能为空");
        } else {
            NsCoreDictionaryVo roomTypeDic = dicMap.get("roomTypeDic");
            String roomTypeId = OwnerUtils.getDicValue(roomTypeDic, list.size() > 9 ? (String) list.get(9) : "");
            if (CommonUtils.isNullOrBlank(roomTypeId)) {
                map.put("result", Constants.RESULT_ERROR);
                map.put("message", getMessage((String) map.get("message"), "房产类型不存在"));
            }
        }
        if (list.size() > 10 && CommonUtils.isObjectEmpty(list.get(10))) {
            map.put("result", Constants.RESULT_ERROR);
            map.put("message", getMessage((String) map.get("message"), "计费面积不能为空"));
        }
        if (list.size() == 36) {
            map.put("precinctName", list.get(2));
            map.put("houseName", list.get(7));
            map.put("houseNo", list.get(15));
            map.put("houseShortName", list.get(8));
            if (CommonUtils.isObjectEmpty(list.get(16))) {
                map.put("result", Constants.RESULT_ERROR);
                map.put("message", "客户名称不能为空");
            } else {
                if (CommonUtils.isObjectEmpty(list.get(17)) && !"统一社会信用代码（营业执照）".equals((String) list.get(18))
                        && !"组织机构代码".equals((String) list.get(18))) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "个人客户移动电话/企业客户公司电话不能为空"));
                }
                String certificateTypeId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(18))) {
                    NsCoreDictionaryVo certificateTypeVo = dicMap.get("certificateType");
                    certificateTypeId = OwnerUtils.getDicValue(certificateTypeVo, (String) list.get(18));
                    if (CommonUtils.isNullOrBlank(certificateTypeId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "证件类型不存在"));
                    }
                }
            }
        } else if (list.size() == 71) {
            map.put("precinctName", list.get(2));
            map.put("houseName", list.get(7));
            map.put("houseNo", list.get(22));
            map.put("houseShortName", list.get(8));
            if (CommonUtils.isObjectEmpty(list.get(23))) {
                map.put("result", Constants.RESULT_ERROR);
                map.put("message", getMessage((String) map.get("message"), "客户名称不能为空"));
            } else {
                if (CommonUtils.isObjectEmpty(list.get(24)) && !"统一社会信用代码（营业执照）".equals((String) list.get(25))
                        && !"组织机构代码".equals((String) list.get(25))) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "个人客户移动电话/企业客户公司电话不能为空"));
                }
                String certificateTypeId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(25))) {
                    NsCoreDictionaryVo certificateTypeVo = dicMap.get("certificateType");
                    certificateTypeId = OwnerUtils.getDicValue(certificateTypeVo, (String) list.get(25));
                    if (CommonUtils.isNullOrBlank(certificateTypeId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "证件类型不存在"));
                    }
                }
                if (CommonUtils.isObjectEmpty(list.get(32)) && "统一社会信用代码（营业执照）".equals((String) list.get(25))
                        && "组织机构代码".equals((String) list.get(25))) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "联系人不能为空"));
                }
                if (CommonUtils.isObjectEmpty(list.get(33)) && "统一社会信用代码（营业执照）".equals((String) list.get(25))
                        && "组织机构代码".equals((String) list.get(25))) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "联系人电话不能为空"));
                }
                String maritalStatusId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(28))) {
                    NsCoreDictionaryVo maritalStatusVo = dicMap.get("maritalStatus");
                    maritalStatusId = OwnerUtils.getDicValue(maritalStatusVo, (String) list.get(28));
                    if (CommonUtils.isNullOrBlank(maritalStatusId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "婚姻状况类型不存在"));
                    }
                }
                String educationId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(29))) {
                    NsCoreDictionaryVo educationVo = dicMap.get("education");
                    educationId = OwnerUtils.getDicValue(educationVo, (String) list.get(29));
                    if (CommonUtils.isNullOrBlank(educationId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "文化类型不存在"));
                    }
                }
                String regionId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(30))) {
                    NsCoreDictionaryVo regionVo = dicMap.get("region");
                    regionId = OwnerUtils.getDicValue(regionVo, (String) list.get(30));
                    if (CommonUtils.isNullOrBlank(regionId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "国籍类型不存在"));
                    }
                }
                String nationId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(31))) {
                    NsCoreDictionaryVo nationVo = dicMap.get("nation");
                    nationId = OwnerUtils.getDicValue(nationVo, (String) list.get(31));
                    if (CommonUtils.isNullOrBlank(nationId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "民族类型不存在"));
                    }
                }
                String bankId = null;
                NsCoreDictionaryVo bankVo = dicMap.get("bankDic");
                bankId = OwnerUtils.getDicValue(bankVo, (String) list.get(35));
                if (CommonUtils.isNullOrBlank(bankId)) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "开户银行不存在"));
                }
            }

            if (CommonUtils.isObjectEmpty(list.get(51))) {
                map.put("result", Constants.RESULT_ERROR);
                map.put("message", getMessage((String) map.get("message"), "承租人名称不能为空"));
            } else {
                if (CommonUtils.isObjectEmpty(list.get(52)) && !"统一社会信用代码（营业执照）".equals((String) list.get(53))
                        && !"组织机构代码".equals((String) list.get(53))) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "承租人个人客户移动电话/企业客户公司电话不能为空"));
                }
                String rentCertificateTypeId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(53))) {
                    NsCoreDictionaryVo certificateTypeVo = dicMap.get("certificateType");
                    rentCertificateTypeId = OwnerUtils.getDicValue(certificateTypeVo, (String) list.get(53));
                    if (CommonUtils.isNullOrBlank(rentCertificateTypeId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "承租人证件类型不存在"));
                    }
                }
                String rentMaritalStatusId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(56))) {
                    NsCoreDictionaryVo maritalStatusVo = dicMap.get("maritalStatus");
                    rentMaritalStatusId = OwnerUtils.getDicValue(maritalStatusVo, (String) list.get(56));
                    if (CommonUtils.isNullOrBlank(rentMaritalStatusId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "承租人婚姻状况类型不存在"));
                    }
                }
                String rentEducationId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(57))) {
                    NsCoreDictionaryVo educationVo = dicMap.get("education");
                    rentEducationId = OwnerUtils.getDicValue(educationVo, (String) list.get(57));
                    if (CommonUtils.isNullOrBlank(rentEducationId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "承租人文化类型不存在"));
                    }
                }
                String rentRegionId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(58))) {
                    NsCoreDictionaryVo regionVo = dicMap.get("region");
                    rentRegionId = OwnerUtils.getDicValue(regionVo, (String) list.get(58));
                    if (CommonUtils.isNullOrBlank(rentRegionId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "承租人国籍类型不存在"));
                    }
                }
                String rentNationId = null;
                if (!CommonUtils.isNullOrBlank((String) list.get(59))) {
                    NsCoreDictionaryVo nationVo = dicMap.get("nation");
                    rentNationId = OwnerUtils.getDicValue(nationVo, (String) list.get(59));
                    if (CommonUtils.isNullOrBlank(rentNationId)) {
                        map.put("result", Constants.RESULT_ERROR);
                        map.put("message", getMessage((String) map.get("message"), "承租人民族类型不存在"));
                    }
                }
                String rentBankId = null;
                NsCoreDictionaryVo rentBankVo = dicMap.get("bankDic");
                rentBankId = OwnerUtils.getDicValue(rentBankVo, (String) list.get(63));
                if (CommonUtils.isNullOrBlank(rentBankId)) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "承租人开户银行不存在"));
                }
                if (CommonUtils.isObjectEmpty(list.get(60)) && "统一社会信用代码（营业执照）".equals((String) list.get(53))
                        && "组织机构代码".equals((String) list.get(53))) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "承租人联系人不能为空"));
                }
                if (CommonUtils.isObjectEmpty(list.get(61)) && "统一社会信用代码（营业执照）".equals((String) list.get(53))
                        && "组织机构代码".equals((String) list.get(53))) {
                    map.put("result", Constants.RESULT_ERROR);
                    map.put("message", getMessage((String) map.get("message"), "承租人联系人电话不能为空"));
                }
            }
        }
        return map;
    }

    private String getMessage(String mapMsg, String msg) {
        if (StringUtils.hasLength(msg)) {
            msg = mapMsg + msg + Constants.SEPARATOR_PATH;
        }
        return msg;
    }

    @ApiOperation(value = "获取项目")
    @RequestMapping(value = "/list-precinct", method = RequestMethod.GET)
    public RestResult<List<OwnerHouseBaseInfo>> listPrecinct() {
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getOrgId();
        List<OwnerHouseBaseInfo> list = houseService.listPrecinct(enterpriseId, organizationId);
        return new RestResult<>(list);
    }

    @ApiOperation(value = "查询指定节点下的所有房间、车位、公共区域")
    @RequestMapping(value = "/list-all-leaf-node", method = RequestMethod.GET)
    public RestResult<List<HouseListEntity>> listAllLeafNode(@RequestParam("houseId") Long houseId) {
        BizException.isNull(houseId, "房产ID不能为空");
        List<HouseListEntity> houseList = houseService.listAllLeafNode(houseId);
        return new RestResult<>(houseList);
    }

    @ApiOperation(value = "查询指定节点下的所有子节点")
    @RequestMapping(value = "/list-all-child-node", method = RequestMethod.GET)
    public RestResult<List<OwnerHouseBaseInfo>> listAllChildNode(
            @ApiParam(value = "房产id") @RequestParam("houseId") Long houseId,
            @ApiParam(value = "所需查询子节点房产类型，0.初始值 1.区域 2.项目 3.组团 4.楼栋 5.单元 6.房产 7.车库 8.车位 9.公共区域") @RequestParam("houseTypes") List<String> houseTypes) {
        BizException.isNull(houseId, "房产ID不能为空");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getOrgId();
        List<OwnerHouseBaseInfo> houseList = houseService.listAllChildNode(houseId, enterpriseId, organizationId, houseTypes);
        // List<Long> idList = houseList.stream().map(m -> m.getHouseId()).collect(Collectors.toList());
        return new RestResult<>(houseList);
    }

    @ApiOperation(value = "查询房产信息")
    @RequestMapping(value = "/info", method = RequestMethod.GET)
    public RestResult<OwnerHouseBaseInfo> getHouseInfo(@ApiParam(value = "房产id") @RequestParam("houseId") Long houseId) {
        BizException.isNull(houseId, "房产Id");
        OwnerHouseBaseInfo houseInfo = houseService.getHouseInfo(houseId);
        return new RestResult<>(houseInfo);
    }

    @ApiOperation(value = "根据房产名称查询房产信息")
    @RequestMapping(value = "/search-info", method = RequestMethod.GET)
    public RestResult<List<OwnerHouseBaseInfo>> searchHouseInfo(@ApiParam(value = "项目名称") @RequestParam("precinctName") String precinctName,
                                                                @ApiParam(value = "房产简称") @RequestParam("houseName") String houseName) {
        List<OwnerHouseBaseInfo> houseBaseInfoList = houseService.searchHouseInfo(precinctName, houseName);
        return new RestResult<>(houseBaseInfoList);
    }

    @ApiOperation(value = "查询房产扩展信息")
    @RequestMapping(value = "/house-info", method = RequestMethod.GET)
    public RestResult<OwnerHouseHouseInfo> getHouseHouseInfo(@ApiParam(value = "房产Id") @RequestParam("houseId") Long houseId) {
        BizException.isNull(houseId, "房产id");
        OwnerHouseHouseInfo houseHouseInfo = houseService.getHouseHouseInfo(houseId);
        return new RestResult<>(houseHouseInfo);
    }

    @ApiOperation(value = "根据房产全称搜索房产信息")
    @RequestMapping(value = "/search-fullName", method = RequestMethod.GET)
    public RestResult<List<OwnerHouseBaseInfo>> searchHouseInfoByFullName(@ApiParam(value = "租户id") @RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                                                          @ApiParam(value = "组织id") @RequestParam(value = "organizationId", required = false) Long organizationId,
                                                                          @ApiParam(value = "房产全称") @RequestParam(value = "fullName") String fullName) {
        BizException.isNull(fullName, "房产id");
        if (enterpriseId == null) {
            enterpriseId = LoginDataHelper.getEnterpriseId();
        }
        if (organizationId == null) {
            organizationId = LoginDataHelper.getOrgId();
        }
        List<OwnerHouseBaseInfo> houseBaseInfo = houseService.searchHouseInfoByFullName(enterpriseId, organizationId, fullName);
        return new RestResult<>(houseBaseInfo);
    }

    @ApiOperation(value = "项目查询")
    @RequestMapping(value = "/search-precinct-app", method = RequestMethod.POST)
    public APPRestResult<List<SearchProjectApp>> searchPrecinctApp(@ApiParam(value ="版本号") @RequestHeader("NWVersion") String NWVersion,
    															@ApiParam(value ="业务代码") @RequestHeader("NWCode") String NWCode,
    															@ApiParam(value ="唯一ID") @RequestHeader("NWGUID") String NWGUID,
    															@ApiParam(value ="交易码") @RequestHeader("NWExID") String NWExID,
    															@ApiParam(value = "查询条件") @RequestBody SearchProjectVo searchVo,
    															HttpServletRequest request,HttpServletResponse response) {
    	List<SearchProjectApp> projectList = new ArrayList<SearchProjectApp>();
        List<OwnerHouseBaseInfo> list = houseService.searchPrecinctApp(searchVo);
        for (OwnerHouseBaseInfo ownerHouseBaseInfo : list) {
        	SearchProjectApp project = new SearchProjectApp();
        	project.setProjectName(ownerHouseBaseInfo.getHouseName());
        	project.setProjectId(ownerHouseBaseInfo.getHouseId());
        	project.setAreaName(ownerHouseBaseInfo.getParentName());
        	projectList.add(project);
		}
        return new APPRestResult<>(projectList);
    }
    
    @ApiOperation(value = "房产结构树查询")
    @RequestMapping(value = "/search-houseTree-app", method = RequestMethod.POST)
    public APPRestResult<List<SearchHouseTreeApp>> searchHouseTreeApp(@ApiParam(value ="版本号") @RequestHeader("NWVersion") String NWVersion,
    															@ApiParam(value ="业务代码") @RequestHeader("NWCode") String NWCode,
    															@ApiParam(value ="唯一ID") @RequestHeader("NWGUID") String NWGUID,
    															@ApiParam(value ="交易码") @RequestHeader("NWExID") String NWExID,
    															@ApiParam(value = "查询条件") @RequestBody SearchProjectVo searchVo,
    															HttpServletRequest request,HttpServletResponse response) {
    	List<SearchHouseTreeApp> projectList = new ArrayList<SearchHouseTreeApp>();
        List<HouseListEntity> list = houseService.searchHouseTreeApp(searchVo);
        for (HouseListEntity houseListEntity : list) {
        	SearchHouseTreeApp houseTree = new SearchHouseTreeApp();
        	houseTree.setHouseId(houseListEntity.getHouseId());
        	houseTree.setHouseName(houseListEntity.getHouseName());
        	houseTree.setAncestorName(houseListEntity.getHouseFullName());
        	houseTree.setReskind(houseListEntity.getHouseType());
        	houseTree.setRoomStatus(houseListEntity.getHouseStage());
        	houseTree.setRoomStatusName(houseListEntity.getStageName());
        	houseTree.setOwner(houseListEntity.getOwnerName());
        	houseTree.setFloorSpace(houseListEntity.getBuildingArea()+"m²");
        	houseTree.setSalesArea(houseListEntity.getChargingArea()+"m²");
        	houseTree.setBelongFloor(houseListEntity.getFloor());
        	projectList.add(houseTree);
		}
        return new APPRestResult<>(projectList);
    }
}
