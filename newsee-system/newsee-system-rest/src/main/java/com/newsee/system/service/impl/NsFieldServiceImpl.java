package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.newsee.common.constant.BizListRequestConstants;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.ProvinceCityArea;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.dao.NsCoreResourcefieldMapper;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.entity.NsCoreResourcefield;
import com.newsee.system.service.INsDictionaryService;
import com.newsee.system.service.INsFieldService;
import com.newsee.system.service.INsFuncinfoService;
import com.newsee.system.utils.DictionaryUtils;
import com.newsee.system.utils.FieldUtils;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreFuncinfoVo;

@Service
public class NsFieldServiceImpl implements INsFieldService {
    
    @Autowired
    NsCoreResourcefieldMapper fieldMapper;
    
    @Autowired
    private RedisUtil redisUtil;
    
    @Autowired
    private INsDictionaryService dictionaryService;
    
    @Autowired
    private INsFuncinfoService funcinfoService;

    @Override
    public List<NsCoreResourcefield> list(Map<String, Object> map) {
        List<NsCoreResourcefield> fields = fieldMapper.selectByMap(map);
         return fields;
    }

    @Override
    public Map<String, Object> listField(LoginCommonDataVo commonVo) {
        //获取登录账号的userid和当前的菜单id
        Long enterpriseId = commonVo.getEnterpriseId();
        Long organizationId = commonVo.getOrganizationId();
        Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
        if (CommonUtils.isObjectEmpty(groupLevelOrgId)) {
            groupLevelOrgId = commonVo.getGroupLevelOrgId();
        }
        String funcId = commonVo.getFuncId();
        String interpreter = commonVo.getInterpreter();
        String formOperateType = commonVo.getFormOperateType();
        //从redis中获取是否有缓存，如有，从缓存中获取，如无，从数据库中重新获取表单项目
        String fieldRedisKey = RedisKeysConstants.REDIS_FUNCTION_INFO_PREFIX
                + "_" + enterpriseId.toString()
                + "_" + organizationId.toString()
                + "_" + funcId
                + "_" + interpreter
                + "_" + formOperateType;
        Object filedRedisObject = redisUtil.getObjectValue(fieldRedisKey);
        if(!Objects.isNull(filedRedisObject)){
            Map<String, Object> fieldMap = (Map<String, Object>)filedRedisObject;
            return fieldMap;
        }
        //如果从缓存中取出来的数据为空，则从数据中获取
        List<Long> oranizationIds = new ArrayList<Long>();
        oranizationIds.add(0L);
        oranizationIds.add(organizationId);
        //获取当前用户和当前菜单的筛选器
        Map<String, Object> map = new HashMap<String, Object>();
       // Map<String, Object> modelMap  = new HashMap<String, Object>();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("enterpriseId", enterpriseId);
        paramMap.put("organizationId", organizationId);
        paramMap.put("funcId", funcId);
        List<NsCoreResourcefield> fields = fieldMapper.selectByMap(paramMap);
        //根据显隐表达式interpreter过滤字段
        FieldUtils.filterFieldsByInterpreter(fields, interpreter);
        //根据辅助配置中的formOperateType（0：新增1：编辑）过滤字段
        FieldUtils.filterFieldsByFormOperateType(fields, formOperateType);
        //获取表单信息
        Map<String, Object> funcMap = new HashMap<String, Object>();
        funcMap.put("enterpriseId", enterpriseId);
        funcMap.put("organizationId", groupLevelOrgId);
        funcMap.put("funcId", funcId);
        NsCoreFuncinfo info =  funcinfoService.getFuncInfo(funcMap);
        NsCoreFuncinfoVo infoVo = new NsCoreFuncinfoVo();
        BeanUtils.copyProperties(info, infoVo);
        //处理表单中的特殊字段
        Map<String, Object> formInfoMap = formFiledsHandler(fields, enterpriseId, organizationId);
        //根据groupname重新组合fields
        List<NsCoreResourcefieldVo> groupedFormfields = groupFieldsByGroupName((List<NsCoreResourcefieldVo>)formInfoMap.get(FormConstants.FORM_FIELDS));
        map.put(FormConstants.FORM_MODEL_DATA, formInfoMap.get(FormConstants.FORM_MODEL_DATA));
        map.put(FormConstants.FORM_INFO, infoVo);
        map.put(FormConstants.FORM_FIELDS, JSON.toJSONString(groupedFormfields));
        //将获取到的数据塞入redis缓存中
        redisUtil.setObjectValue(fieldRedisKey, map);
        return map;
    }
    
    /**
     * 表单项目特殊字段处理
     * @return
     */
    private Map<String, Object> formFiledsHandler(List<NsCoreResourcefield> fields, Long enterpriseId, Long organizationId){
    	 List<NsCoreResourcefieldVo> formFields = new ArrayList<NsCoreResourcefieldVo>();
    	 Map<String, Object> map = Maps.newHashMap();
    	 Map<String, Object> modelMap  = Maps.newHashMap();
    	 map.put(FormConstants.FORM_FIELDS, formFields);
    	 map.put(FormConstants.FORM_MODEL_DATA, modelMap);
    	//特殊字段处理
        if (CollectionUtils.isEmpty(fields)) {
        	return map;
        }
        for(NsCoreResourcefield field : fields) {
        	NsCoreResourcefieldVo vo = new NsCoreResourcefieldVo();
        	// 1.如果字段属性为select，checkbox，cascader，从数据字典中获取下拉列表选项
            if (BizListRequestConstants.HEADER_TYPE_SELECT.equals(field.getResourcefieldXtype())
                    || BizListRequestConstants.HEADER_TYPE_CHECKBOX.equals(field.getResourcefieldXtype())
                    || BizListRequestConstants.HEADER_TYPE_CASCADER.equals(field.getResourcefieldXtype())
                    || BizListRequestConstants.HEADER_TYPE_RADIO.equals(field.getResourcefieldXtype())) {
                NsCoreDictionary dictionary = new NsCoreDictionary();
                dictionary.setEnterpriseId(enterpriseId);
                dictionary.setOrganizationId(organizationId);
                dictionary.setDictionaryDdcode(field.getResourcefieldNameEn());
                NsCoreDictionaryVo select = dictionaryService.getNsCoreDictionaryVo(dictionary);
                vo.setItems(DictionaryUtils.getSelectedEntity(select));
                if(!isNoUseField(field.getResourcefieldCode())){
                    if(BizListRequestConstants.HEADER_TYPE_CHECKBOX.equals(field.getResourcefieldXtype())){
                        modelMap.put(field.getResourcefieldCode(), new ArrayList<String>());
                    }else if(BizListRequestConstants.HEADER_TYPE_CASCADER.equals(field.getResourcefieldXtype())){
                        modelMap.put(field.getResourcefieldCode(), new ArrayList<String>());
                    }else if("provinceCityArea".equals(field.getResourcefieldCode())){
                        modelMap.put("provinceCityArea", CommonUtils.clearNull(new ProvinceCityArea()));
                    }else{
                        modelMap.put(field.getResourcefieldCode(), StringUtils.nullToBlank(field.getResourcefieldValue()));
                    }
                }else{
                    
                }
            }else{
                if(!isNoUseField(field.getResourcefieldCode())){
                    modelMap.put(field.getResourcefieldCode(), StringUtils.nullToBlank(field.getResourcefieldValue()));
                }
            }
            BeanUtils.copyProperties(field, vo);
            //2.特殊属性转换
            if(BizListRequestConstants.FORM_TYPE_TABLE.equals(field.getResourcefieldXtype())){
                vo.setTableHeader(field.getResourcefieldCode());
            }
            vo.setFieldHeight(StringUtils.parseInteger(field.getResourcefieldHeight()));
            vo.setFieldWidth(StringUtils.parseInteger(field.getResourcefieldWidth()));
            vo.setIsfieldRequired(!StringUtils.parseBoolean(field.getResourcefieldAllowblank()));
            vo.setIsResourcefieldDisabled(StringUtils.parseBoolean(field.getResourcefieldDisabled()));
            vo.setIsResourcefieldRemoved(StringUtils.parseBoolean(field.getResourcefieldRemoved()));
            vo.setIsResourcefieldEditable(StringUtils.parseBoolean(field.getResourcefieldEditable()));
            vo.setIsResourcefieldReadonly(StringUtils.parseBoolean(field.getResourcefieldReadonly()));
            vo.setIsResourcefieldHidden(StringUtils.parseBoolean(field.getResourcefieldHidden()));
            vo.setResourcefieldBindingfnList(StringUtils.isBlank(field.getResourcefieldBinding())?new ArrayList<>():StringUtils.parseList(field.getResourcefieldBinding(), "#"));
            vo.setResourcefieldName(StringUtils.addBlank(field.getResourcefieldName()));
            vo.setResourcefieldOtherconfigObject(StringUtils.isBlank(field.getResourcefieldOtherconfig())?null:JSON.parseObject(field.getResourcefieldOtherconfig(), Map.class));
            formFields.add(vo);
        }
        map.put(FormConstants.FORM_FIELDS, formFields);
        map.put(FormConstants.FORM_MODEL_DATA, modelMap);
        return map;
    }
    /**
     * 判断该字段是否是无用字段
     * @param fieldCode
     * @return
     */
    private boolean isNoUseField(String fieldCode){
        if(StringUtils.isBlank(fieldCode)){
            return true;
        }
        if(fieldCode.startsWith("Child") || fieldCode.startsWith("displayfield") || fieldCode.startsWith("fieldset")){
            return true;
        }
        return false;
    }
    
    /**
     * 根据分组框重新组合数据
     * @param fields
     * @return
     */
    private List<NsCoreResourcefieldVo> groupFieldsByGroupName(List<NsCoreResourcefieldVo> fields){
        List<NsCoreResourcefieldVo> groupedFields = new ArrayList<NsCoreResourcefieldVo>();
        Map<String, List<NsCoreResourcefieldVo>> childrenMap = Maps.newHashMap();
        for(NsCoreResourcefieldVo field: fields){
            //如果控件为分组框或者不属于任何分组，则放入到list中，否则放到相应分组框中的children属性中
            String fieldGroupName = field.getResourcefieldGroupname();
            if(BizListRequestConstants.FORM_TYPE_GROUP.equals(field.getResourcefieldXtype())
                    || "".equals(fieldGroupName)){
                groupedFields.add(field);
            }else{
                if(Objects.isNull(childrenMap.get(fieldGroupName))){
                     List<NsCoreResourcefieldVo> children = new ArrayList<NsCoreResourcefieldVo>();
                     children.add(field);
                     childrenMap.put(field.getResourcefieldGroupname(), children);
                }else{
                     List<NsCoreResourcefieldVo> children = (List<NsCoreResourcefieldVo>)childrenMap.get(fieldGroupName);
                     children.add(field);
                }
            }
        }
        for(NsCoreResourcefieldVo field: groupedFields){
            String fieldGroupName = field.getResourcefieldCode();
            if(BizListRequestConstants.FORM_TYPE_GROUP.equals(field.getResourcefieldXtype())){
                field.setChildren(childrenMap.get(fieldGroupName));
            }
        }
        return groupedFields;
    }

}
