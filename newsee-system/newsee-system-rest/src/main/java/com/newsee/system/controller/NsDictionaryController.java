package com.newsee.system.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonFormat.Value;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsCoreDictionaryitem;
import com.newsee.system.service.INsDictionaryGroupService;
import com.newsee.system.service.INsDictionaryItemService;
import com.newsee.system.service.INsDictionaryService;
import com.newsee.system.service.impl.NsFieldServiceImpl;
import com.newsee.system.vo.DictionaryDdcodeVo;
import com.newsee.system.vo.DictionaryTreeVo;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionarygroupVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@ResponseBody
@RequestMapping("/dictionary")
@Api(tags = {"com.newsee.system.controller.NsDictionaryController"}, description = "按钮  REST API，包含所有数据字典的操作方法。")
public class NsDictionaryController {
    
    @Autowired
    private INsDictionaryGroupService dictionaryGroupService;
    
    @Autowired
    private INsDictionaryService dictionaryService;
    
    @Autowired
    private INsDictionaryItemService dictionaryItemService;
    
    @Autowired
    private NsFieldServiceImpl fieldService;
    
    @Autowired
    private RedisUtil redisUtil;
    //--------------------字典树-------------------------------
    @ApiOperation(value = "数据字典组列表")
    @RequestMapping(value = "/get-dictionary-tree", method = RequestMethod.GET)
    public RestResult<DictionaryTreeVo> getDictionaryTree(@RequestParam(value="organizationId") Long organizationId){
        DictionaryTreeVo dictionaryTreeVo = dictionaryGroupService.listTree(organizationId);
        return new RestResult<>(dictionaryTreeVo);
    }
    
    
    //--------------------数据字典组---------------------------
    @ApiOperation(value = "新增数据字典组")
    @RequestMapping(value = "/add-dictionaryGroup", method = RequestMethod.POST)
    public RestResult<Boolean> addDictionaryGroup(@RequestBody NsCoreDictionarygroupVo dictionaryGroupVo){
        BizException.isNull(dictionaryGroupVo.getOrganizationId(), "对应的组织ID");
        Long userId = LoginDataHelper.getUserId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        dictionaryGroupVo.setHandlerId(userId);
        dictionaryGroupVo.setEnterpriseId(enterpriseId);
        boolean result = dictionaryGroupService.add(dictionaryGroupVo); 
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "编辑数据字典组")
    @RequestMapping(value = "/edit-dictionaryGroup", method = RequestMethod.POST)
    public RestResult<Boolean> editDictionaryGroup(@RequestBody NsCoreDictionarygroupVo dictionaryGroupVo){
        BizException.isNull(dictionaryGroupVo.getDictionaryGroupId(), "字典组ID");
        Long userId = LoginDataHelper.getUserId();
        dictionaryGroupVo.setHandlerId(userId);
        boolean result = dictionaryGroupService.edit(dictionaryGroupVo); 
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "删除数据字典组")
    @RequestMapping(value = "/delete-dictionaryGroup", method = RequestMethod.GET)
    public RestResult<Boolean> deleteDictionaryGroup(@RequestParam(value="dictionaryGroupId") Long dictionaryGroupId,
            @RequestParam(value="organizationId") Long organizationId){
        BizException.isNull(dictionaryGroupId, "字典组ID");
        boolean result = dictionaryGroupService.delete(dictionaryGroupId,organizationId);  
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "数据字典组详情")
    @RequestMapping(value = "/detail-dictionaryGroup", method = RequestMethod.GET)
    public RestResult<NsCoreDictionarygroupVo> detailDictionaryGroup(@RequestParam(value="dictionaryGroupId") Long dictionaryGroupId){
        BizException.isNull(dictionaryGroupId, "字典组ID");
        NsCoreDictionarygroupVo result = dictionaryGroupService.detail(dictionaryGroupId);  
        return new RestResult<>(result);
    }
    
    //--------------------数据字典-----------------------------
    @ApiOperation(value = "新增数据字典")
    @RequestMapping(value = "/add-dictionary", method = RequestMethod.POST)
    public RestResult<Boolean> addDictionary(@RequestBody NsCoreDictionaryVo dictionaryVo){
        BizException.isNull(dictionaryVo.getOrganizationId(), "对应的组织ID");
        BizException.isNull(dictionaryVo.getDictionaryGroupId(), "字典组ID");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long userId = LoginDataHelper.getUserId();
        dictionaryVo.setEnterpriseId(enterpriseId);
        dictionaryVo.setOrganizationId(dictionaryVo.getOrganizationId());
        dictionaryVo.setHandlerId(userId);
        boolean result = dictionaryService.add(dictionaryVo); 
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "编辑数据字典")
    @RequestMapping(value = "/find-dictionary-name", method = RequestMethod.POST)
    public RestResult<String> findDictionaryName(@RequestBody Map< String, Object> map){
    	NsCoreDictionaryitem nsCoreDictionary =dictionaryService.findById(map);
    	
        return new RestResult<String>(nsCoreDictionary.getDictionaryitemItemname());
    }
    
    
    @ApiOperation(value = "编辑数据字典")
    @RequestMapping(value = "/edit-dictionary", method = RequestMethod.POST)
    public RestResult<Boolean> editDictionary(@RequestBody NsCoreDictionaryVo dictionaryVo){
        BizException.isNull(dictionaryVo.getJeCoreDictionaryId(), "字典ID");
        Long userId = LoginDataHelper.getUserId();
        dictionaryVo.setHandlerId(userId);
        boolean result = dictionaryService.edit(dictionaryVo); 
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "删除数据字典")
    @RequestMapping(value = "/delete-dictionary", method = RequestMethod.GET)
    public RestResult<Boolean> deleteDictionary(@RequestParam(value="dictionaryId") String dictionaryId){
        BizException.isNull(dictionaryId, "字典ID");
        boolean result = dictionaryService.delete(dictionaryId);  
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "数据字典列表")
    @RequestMapping(value = "/list-dictionary", method = RequestMethod.GET)
    public RestResult<List<NsCoreDictionaryVo>> listDictionary(@RequestParam(value="dictionarygroupId") Long dictionarygroupId,
            @RequestParam(value="organizationId") Long organizationId){
        BizException.isNull(organizationId, "组织ID");
        List<NsCoreDictionaryVo> dictionaryVos = dictionaryService.list(dictionarygroupId,organizationId);
        return new RestResult<>(dictionaryVos);
    }
    
    @ApiOperation(value = "数据字典详情")
    @RequestMapping(value = "/detail-dictionary", method = RequestMethod.GET)
    public RestResult<NsCoreDictionaryVo> detailDictionary(@RequestParam(value="dictionaryId") String dictionaryId){
        BizException.isNull(dictionaryId, "字典ID");
        NsCoreDictionaryVo result = dictionaryService.detail(dictionaryId);  
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "获取数据字典", notes = "数据字典页面初始化方法", response = RestResult.class)
    @RequestMapping(value = "/getDictionary", method = RequestMethod.POST)
    public RestResult<NsCoreDictionaryVo> getDictionary( @RequestBody NsCoreDictionary dictionary) {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        if(organizationId != null){
            dictionary.setOrganizationId(organizationId);
        }
        NsCoreDictionaryVo dictionaryVo = dictionaryService.getNsCoreDictionaryVo(dictionary);
        RestResult<NsCoreDictionaryVo> result = new RestResult<>(dictionaryVo);
        return result;
    }
  
    @ApiOperation(value = "批量获取数据字典", notes = "数据字典页面初始化方法", response = RestResult.class)
    @RequestMapping(value = "/get-dictionary-list", method = RequestMethod.POST)
    public RestResult<Map<String, List<NsCoreDictionaryVo>>> getDictionaryList( @RequestBody DictionaryDdcodeVo dictionaryDdcodeVo) {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        if(!CommonUtils.isObjectEmpty(organizationId)){
            dictionaryDdcodeVo.setOrganizationId(organizationId);
        }
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        if (!CommonUtils.isObjectEmpty(enterpriseId)) {
            dictionaryDdcodeVo.setEnterpriseId(enterpriseId);
        }
        Map<String, List<NsCoreDictionaryVo>> map = dictionaryService.getNsCoreDictionaryVo(dictionaryDdcodeVo);
        RestResult<Map<String, List<NsCoreDictionaryVo>>> result = new RestResult<>(map);
        return result;
    }
    
    @ApiOperation(value = "模糊查询获取数据字典", notes = "模糊查询获取数据字典方法", response = RestResult.class)
    @RequestMapping(value = "/get-dictionary-for-search", method = RequestMethod.POST)
    public RestResult<NsCoreDictionaryVo> getDictionaryForSearch(@RequestBody NsCoreDictionary dictionary) {
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        if(organizationId != null){
            dictionary.setOrganizationId(organizationId);
        }
        NsCoreDictionaryVo dictionaryVo = dictionaryService.getNsCoreDictionaryVoForSearch(dictionary);
        RestResult<NsCoreDictionaryVo> result = new RestResult<>(dictionaryVo);
        return result;
    }
    //--------------------数据字典(项)item----------------------------
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        Map<String, Object> resultData = fieldService.listField(commonVo);
        //将json字符串形式的form表单装换成相应的对象
        List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
        //modelData数据格式处理
       /* if(!Objects.isNull(resultData.get("modelData"))){
            Map<String, Object> userVomodelMap = (Map<String, Object>)resultData.get("modelData");
            if (Objects.isNull(userVomodelMap.get("roleids")) || StringUtils.isBlank((String)userVomodelMap.get("roleids"))) {
                userVomodelMap.put("roleids", new ArrayList<>());
            }
        }*/
        resultData.put(FormConstants.FORM_FIELDS, formFields);
        //redisUtil.setObjectValue(RedisKeysConstants.REDIS_FUNCTION_FIELDS_PREFIX + "_" + enterpriseId.toString() + "_" + organizationId.toString() + "_" + funcId + "_" + interpreter + "_" + formOperateType, formFields);
        return new RestResult<Map<String, Object>>(resultData);
    }
    
    @ApiOperation(value = "新增数据字典Item")
    @RequestMapping(value = "/add-dictionaryItem", method = RequestMethod.POST)
    public RestResult<Boolean> addDictionaryItem(@RequestBody NsCoreDictionaryitemVo dictionaryitemVo){
        BizException.isNull(dictionaryitemVo.getDictionaryitemDictionaryId(), "字典ID");
        BizException.isNull(dictionaryitemVo.getOrganizationId(), "组织ID");
        Long userId = LoginDataHelper.getUserId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        dictionaryitemVo.setHandlerId(userId);
        dictionaryitemVo.setEnterpriseId(enterpriseId);
        dictionaryitemVo.setOrganizationId(dictionaryitemVo.getOrganizationId());
        boolean result = dictionaryItemService.add(dictionaryitemVo); 
        if (result) {
            String columnRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX
                    + "_" + enterpriseId.toString()
                    + "_" + organizationId.toString();
            redisUtil.deleteByPrefix(columnRedisKey);
            String fieldRedisKey = RedisKeysConstants.REDIS_FUNCTION_INFO_PREFIX
                    + "_" + enterpriseId.toString()
                    + "_" + organizationId.toString();
            redisUtil.deleteByPrefix(fieldRedisKey);
        }
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "编辑数据字典Item")
    @RequestMapping(value = "/edit-dictionaryItem", method = RequestMethod.POST)
    public RestResult<Boolean> editDictionaryItem(@RequestBody NsCoreDictionaryitemVo dictionaryitemVo){
        BizException.isNull(dictionaryitemVo.getJeCoreDictionaryitemId(), "字典项ID");
        Long userId = LoginDataHelper.getUserId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        dictionaryitemVo.setHandlerId(userId);
        boolean result = dictionaryItemService.edit(dictionaryitemVo); 
        if (result) {
            String columnRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX
                    + "_" + enterpriseId.toString()
                    + "_" + organizationId.toString();
            redisUtil.deleteByPrefix(columnRedisKey);
            String fieldRedisKey = RedisKeysConstants.REDIS_FUNCTION_INFO_PREFIX
                    + "_" + enterpriseId.toString()
                    + "_" + organizationId.toString();
            redisUtil.deleteByPrefix(fieldRedisKey);
        }
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "删除数据字典Item")
    @RequestMapping(value = "/delete-dictionaryItem", method = RequestMethod.GET)
    public RestResult<Boolean> deleteDictionaryItem(@RequestParam(value="dictionaryItemId") String dictionaryItemId){
        BizException.isNull(dictionaryItemId, "字典项ID");
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
        boolean result = dictionaryItemService.delete(dictionaryItemId);  
        if (result) {
            String columnRedisKey = RedisKeysConstants.REDIS_COLUMN_PREFIX
                    + "_" + enterpriseId.toString()
                    + "_" + organizationId.toString();
            redisUtil.deleteByPrefix(columnRedisKey);
            String fieldRedisKey = RedisKeysConstants.REDIS_FUNCTION_INFO_PREFIX
                    + "_" + enterpriseId.toString()
                    + "_" + organizationId.toString();
            redisUtil.deleteByPrefix(fieldRedisKey);
        }
        return new RestResult<>(result);
    }
    
    @ApiOperation(value = "数据字典Item列表")
    @RequestMapping(value = "/list-dictionaryItem", method = RequestMethod.POST)
    public RestResult<PageInfo<NsCoreDictionaryitemVo>> listDictionaryItem(@RequestBody SearchVo searchVo,
            @RequestParam(value="dictionaryitemDictionaryId", required=false) String dictionaryitemDictionaryId,
            @RequestParam(value="dictionaryGroupId", required=false) String dictionaryGroupId){
        PageInfo<NsCoreDictionaryitemVo> dictionaryItemVos = dictionaryItemService.listPage(searchVo,dictionaryitemDictionaryId, dictionaryGroupId);
        return new RestResult<>(dictionaryItemVos);
    }
    
   
    
    @ApiOperation(value = "数据字典Item详情")
    @RequestMapping(value = "/detail-dictionaryItem", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailDictionaryItem(@RequestParam(value="dictionaryItemId") String dictionaryItemId){
        BizException.isNull(dictionaryItemId, "字典项ID");
        NsCoreDictionaryitemVo dicItem = dictionaryItemService.detail(dictionaryItemId);
        //获取表单
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        Map<String, Object> resultData = fieldService.listField(commonVo);
        if (!Objects.isNull(dicItem)) {
            dicItem = CommonUtils.clearNull(dicItem);
            //详情覆盖modelData
            resultData.put("modelData", dicItem);
            //将json字符串形式的form表单装换成相应的对象
            List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
            resultData.put("fields", formFields);
        }
        return new RestResult<>(resultData);
    }
    
    
    @ApiOperation(value = "数据字典的值")
    @RequestMapping(value = "/list-dictionaryItem-select", method = RequestMethod.POST)
    public RestResult< List<Map<String, Object>>> listDictionaryItemSelect(@RequestBody Map<String, Object> map){
    	String dictionaryGroupId = String.valueOf(map.get("dictionaryGroupId"));
    	String dictionaryitemDictionaryId = String.valueOf(map.get("dictionaryitemDictionaryId"));
        List<NsCoreDictionaryitemVo> dictionaryItemVos = dictionaryItemService.listPageSelect(dictionaryitemDictionaryId, dictionaryGroupId);
        List<Map<String, Object>> result =  new ArrayList();
        dictionaryItemVos.forEach(e->{
         Map<String, Object> value = Maps.newHashMap();
         value.put("label",e.getDictionaryitemItemname());
         value.put("value",e.getDictionaryitemItemcode());
         value.put("disabled",false);
         result.add(value);
        });
        return new RestResult<>(result);
    }
    
}
