package com.newsee.log.controller;

import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.constant.BizListRequestConstants;
import com.newsee.common.constant.MongoCollectionsConstants;
import com.newsee.common.entity.FilterEntity;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.vo.FilterVo;
import com.newsee.log.service.remote.ISystemRemoteService;
import com.newsee.log.utils.BussinessUtils;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.NsCoreDictionaryVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 页面筛选器管理Controller
 * @author xiaosisi add on 2017/09/05
 *
 */
@RestController
@RequestMapping(value = "/filter")
@Api(tags = {"com.newsee.custom.controller.PageFilterController"}, description = "列表页面筛选器操作共通 REST API，包含对页面筛选器的增删改查方法。")
public class PageFilterController {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Autowired
	private ISystemRemoteService systemRemoteService;
	
    @ApiOperation(
    		value = "获取用户定义的筛选器列表，userId和menuId可在请求头中获取，故无需参数。", 
    		notes = "根据userId和menuId获取用户定义的当前页面的筛选器，",
    		response = RestResult.class)
    @RequestMapping(value = "/list-filter", method = RequestMethod.GET)
    public RestResult<List<FilterVo>> listFilter() throws BizException{
    	//获取登录账号的userid和当前的菜单id
    	Long userId = LoginDataHelper.getUserId();
    	String funcId = LoginDataHelper.getFuncId();
    	//获取当前用户和当前菜单的筛选器
		Query query = new Query();
		//从本公司或者内置对照数据中获取
	    query.addCriteria(Criteria.where("userId").is(userId).and("funcId").is(funcId));
	    query.with(new Sort(new Sort.Order(Sort.Direction.ASC, "orderNo")));
	    List<FilterVo> filterVoList = mongoTemplate.find(query, FilterVo.class, MongoCollectionsConstants.SYSTEM_FUNCTION_FILTER);
	    RestResult<List<FilterVo>> result = new RestResult<>(filterVoList);
	    return result;
    }
    
    @ApiOperation(
    		value = "获取筛选器详情，userId和menuId可在请求头中获取，故无需传入此二参数。", 
    		notes = "根据userId和menuId以及筛选第id获取响应的筛选器详情，",
    		response = RestResult.class)
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    public RestResult<FilterVo> detail(@ApiParam(name="filterId", value="筛选器id")@RequestParam("filterId")String filterId)throws BizException{
    	Query query = new Query();
    	Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
	    query.addCriteria(Criteria.where("_id").is(filterId));
	  //  Long companyId = LoginDataHelper.getCompanyId();
	    FilterVo filterVo = mongoTemplate.findOne(query, FilterVo.class, MongoCollectionsConstants.SYSTEM_FUNCTION_FILTER);
	    if(!Objects.isNull(filterVo)){
	    		List<FilterEntity> filters = filterVo.getFilterList();
	    		if(!CollectionUtils.isEmpty(filters)){
	    			for(FilterEntity filter : filters){
	    				//如果字段属性为select，从数据字典中获取下拉列表选项
	    				if(BizListRequestConstants.HEADER_TYPE_SELECT.equals(filter.getType())){
	    					NsCoreDictionary dictionary = new NsCoreDictionary();
	                        dictionary.setOrganizationId(organizationId);
	                        dictionary.setDictionaryDdcode(filter.getFieldName());
	    					RestResult<NsCoreDictionaryVo> selectResult = systemRemoteService.getDictionary(dictionary);
	    					NsCoreDictionaryVo select = selectResult.getResultData();
	    					filter.setSelectList(BussinessUtils.getSelectedEntity(select));
	    				}
	    			}
	    		}
	    }
	    RestResult<FilterVo> result = new RestResult<>(filterVo);
    	return result;
    }
    
    @ApiOperation(
    		value = "保存筛选器。", 
    		notes = "根据userId和menuId删除所有的筛选器，然后将页面传入的筛选器重新插入到数据库中。",
    		response = RestResult.class)
    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public RestResult<Integer> edit(@ApiParam(name="filterList", value="页面传入的筛选器")@RequestBody List<FilterVo> filterList)throws BizException{
    	RestResult<Integer> result = new RestResult<Integer>();
    	//先将该用户和该menu所有的筛选器删除，然后重新插入
    	//获取登录账号的userid和当前的菜单id
    	Long userId = LoginDataHelper.getUserId();
    	String funcId = LoginDataHelper.getFuncId();
    	Long orgId = LoginDataHelper.getCompanyLevelOrgId();
    	Query query = new Query();
		//从本公司或者内置对照数据中获取
	    query.addCriteria(Criteria.where("userId").is(userId).and("funcId").is(funcId));
	    query.with(new Sort(new Sort.Order(Direction.ASC, "orderNo")));
	    mongoTemplate.remove(query, FilterVo.class, MongoCollectionsConstants.SYSTEM_FUNCTION_FILTER);
	    //对页面传入的筛选器排序
	    if(!CollectionUtils.isEmpty(filterList)){
	        //过滤字符串前后半角空格
	        trimFileterList(filterList);
	        
	    	for(int i = 1; i <= filterList.size(); i ++){
	    		filterList.get(i-1).setOrderNo(i);
	    		filterList.get(i-1).setFuncId(funcId);
	    		filterList.get(i-1).setUserId(userId);
	    		filterList.get(i-1).setOrganizationId(orgId);
	    	}
	    	//批量插入筛选器
	    	mongoTemplate.insert(filterList, MongoCollectionsConstants.SYSTEM_FUNCTION_FILTER);
	    }
	    //如果数据类型为select,从数据字典中获取下拉列表的值
    	result.setResultData(filterList.size());
    	result.setResultCode(ResultCodeEnum.SUCCESS.CODE);
    	result.setResultMsg(ResultCodeEnum.SUCCESS.DESC);
    	return result;
    }
    
    private void trimFileterList(List<FilterVo> filterList){
        for (FilterVo filterVo : filterList) {
            String  filtername = filterVo.getFilterName().trim();
            filterVo.setFilterName(filtername);
            
            List<FilterEntity> filterEntities = filterVo.getFilterList();
            for (FilterEntity filterEntity : filterEntities) {
                String fieldname = filterEntity.getFieldName().trim();
                String fieldvalue = filterEntity.getFieldValue().trim();
                filterEntity.setFieldName(fieldname);
                filterEntity.setFieldValue(fieldvalue);
            }
        }
    }
    
}
