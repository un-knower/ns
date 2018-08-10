package com.newsee.system.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.constant.BizListRequestConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.service.INsColumnService;
import com.newsee.system.service.INsDictionaryService;
import com.newsee.system.utils.DictionaryUtils;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.OtherConfig;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import sun.java2d.pipe.OutlineTextRenderer;

@RestController
@RequestMapping(value = "/column")
@Api(tags = {"com.newsee.system.controller.NsColumnController"}, description = "列表字段获取共通 REST API，包含对列表页面表头获取方法。")
public class NsColumnController {

    private static final Logger logger = LoggerFactory.getLogger(NsColumnController.class);
    @Autowired
    private INsColumnService columnService;
    @Autowired
    private INsDictionaryService dictionaryService;
    @Autowired
    private RedisUtil redisUtil;

    @ApiOperation(value = "获取列表页面表头", notes = "根据功能ID获取列表页面表头", response = RestResult.class)
    @RequestMapping(value = "/list-column", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> listColumn() throws BizException {
        //获取功能ID
        String funcId = LoginDataHelper.getFuncId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        List<Long> organizationIds = new ArrayList<Long>();
        logger.info("enterpriseId=" + enterpriseId + "----organizationId=" + organizationId + "-----funcId=" + funcId);
        organizationIds.add(organizationId);
        if (!StringUtils.hasLength(funcId)) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "funcId不存在");
        }
        //从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
        String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX
                + "_" + enterpriseId.toString()
                + "_" + organizationId.toString()
                + "_" + funcId;
        Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
        if (!Objects.isNull(filedRedisObject)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> fieldMap = (Map<String, Object>) filedRedisObject;
            return new RestResult<>(fieldMap);
        }
        //获取本功能的column列表
        Map<String, Object> map = new HashMap<>();
        map.put("enterpriseId", enterpriseId);
        map.put("organizationId", organizationId);
        map.put("funcId", funcId);
        List<NsCoreResourcecolumnVo> columnVos = columnService.list(map);
        logger.info("查询到的数据量是：" + (columnVos != null ? columnVos.size() : 0));
        //enNameMap列英文名称集合
        Map<String, String> enNameMap = new HashMap<String, String>();

        //如果数据类型为select,从数据字典中获取下拉列表的值
        if (!CollectionUtils.isEmpty(columnVos)) {
            for (NsCoreResourcecolumnVo column : columnVos) {
                // 如果字段属性为select，从数据字典中获取下拉列表选项
                if (BizListRequestConstants.HEADER_TYPE_SELECT.equals(column.getResourcecolumnXtype()) ||
                        BizListRequestConstants.HEADER_TYPE_RADIO.equals(column.getResourcecolumnXtype())) {
                    NsCoreDictionary dictionary = new NsCoreDictionary();
                    dictionary.setOrganizationId(organizationId);
                    dictionary.setDictionaryDdcode(column.getResourcecolumnCode());
                    NsCoreDictionaryVo dictionaryVo = dictionaryService.getNsCoreDictionaryVo(dictionary);
                    column.setSelectList(DictionaryUtils.getSelectedEntity(dictionaryVo));
                    enNameMap.put(column.getResourcecolumnCode(), "");
                }
                //如果是可编辑项，配置otherConfig属性
                genOtherConfig(column);
            }
        }
        Map<String, Object> resultmap = new HashMap<String, Object>();
        resultmap.put("columns", columnVos);
        resultmap.put("columnsEnNames", enNameMap);
        //将获取到的数据塞入redis缓存中
        if (funcId != null && !"undefined".equals(funcId) && !"null".equals(funcId)) {
            redisUtil.setObjectValue(fieldRedisKey, resultmap);
        }
        RestResult<Map<String, Object>> result = new RestResult<>(resultmap);
        return result;
    }

    /**
     * 设置Column中的OtherConfig属性，这个方法代优化
     */
    private void genOtherConfig(NsCoreResourcecolumnVo column) {
        if ("1".equals(column.getResourcecolumnAllowedit())) {
            OtherConfig otherConfig = new OtherConfig();
            otherConfig.setSwitchType(true);
            otherConfig.setDisabled(false);
            if ("text".equals(column.getResourcecolumnXtype())) {
                otherConfig.setType("input");
            } else {
                otherConfig.setType(column.getResourcecolumnXtype());
            }
            if ("ChargeEndTime".equals(column.getResourcecolumnNameEn())) {
                otherConfig.setPlaceHolder(BizListRequestConstants.EDIT_HEADER_TYPE);
                otherConfig.setRequire(false);
            } else {
                otherConfig.setRequire(true);
            }
            if (column.getResourcecolumnNameEn().toLowerCase().contains("rate")) {
                otherConfig.setType("rate");
                otherConfig.setMin(0);
                otherConfig.setMax(100);
                otherConfig.setDecimal(2);
            }
            column.setEidtConfig(otherConfig);
        }
    }

    @ApiOperation(value = "远程调用获取列表页面表头", notes = "远程调用根据功能ID获取列表页面表头", response = RestResult.class)
    @RequestMapping(value = "/list-column-for-remote", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> listColumnForRemote(@RequestBody NsCoreResourcecolumnVo nsCoreResourcecolumnVo) throws BizException {
        //获取功能ID
        String funcId = LoginDataHelper.getFuncId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();

        //获取本功能的column列表
        if (!CommonUtils.isObjectEmpty(nsCoreResourcecolumnVo.getEnterpriseId())) {
            enterpriseId = nsCoreResourcecolumnVo.getEnterpriseId();
        }
        if (!CommonUtils.isObjectEmpty(nsCoreResourcecolumnVo.getOrganizationId())) {
            organizationId = nsCoreResourcecolumnVo.getOrganizationId();
        }
        if (!CommonUtils.isObjectEmpty(nsCoreResourcecolumnVo.getResourcecolumnFuncinfoId())) {
            funcId = nsCoreResourcecolumnVo.getResourcecolumnFuncinfoId();
        }
        if (!StringUtils.hasLength(funcId)) {
            BizException.fail(ResultCodeEnum.PARAMS_MISSING, "funcId不存在");
        }
        //从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
        String fieldRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX
                + "_" + enterpriseId.toString()
                + "_" + organizationId.toString()
                + "_" + funcId;
        Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
        if (!Objects.isNull(filedRedisObject)) {
            @SuppressWarnings("unchecked")
            Map<String, Object> fieldMap = (Map<String, Object>) filedRedisObject;
            return new RestResult<>(fieldMap);
        }
        nsCoreResourcecolumnVo.setEnterpriseId(enterpriseId);
        nsCoreResourcecolumnVo.setOrganizationId(organizationId);
        nsCoreResourcecolumnVo.setResourcecolumnFuncinfoId(funcId);
        List<NsCoreResourcecolumnVo> columnVos = columnService.listForRemote(nsCoreResourcecolumnVo);
        //enNameMap列英文名称集合
        Map<String, String> enNameMap = new HashMap<String, String>();

        //如果数据类型为select,从数据字典中获取下拉列表的值
        if (!CollectionUtils.isEmpty(columnVos)) {
            for (NsCoreResourcecolumnVo column : columnVos) {
                // 如果字段属性为select，从数据字典中获取下拉列表选项
                if (BizListRequestConstants.HEADER_TYPE_SELECT.equals(column.getResourcecolumnXtype())) {
                    NsCoreDictionary dictionary = new NsCoreDictionary();
                    dictionary.setOrganizationId(organizationId);
                    dictionary.setDictionaryDdcode(column.getResourcecolumnCode());
                    NsCoreDictionaryVo dictionaryVo = dictionaryService.getNsCoreDictionaryVo(dictionary);
                    column.setSelectList(DictionaryUtils.getSelectedEntity(dictionaryVo));
                    enNameMap.put(column.getResourcecolumnCode(), "");
                }
            }
        }
        Map<String, Object> resultmap = new HashMap<String, Object>();
        resultmap.put("columns", columnVos);
        resultmap.put("columnsEnNames", enNameMap);
        //将获取到的数据塞入redis缓存中
        if (funcId != null && !"undefined".equals(funcId) && !"null".equals(funcId)) {
            redisUtil.setObjectValue(fieldRedisKey, resultmap);
        }
        RestResult<Map<String, Object>> result = new RestResult<>(resultmap);
        return result;
    }
}
