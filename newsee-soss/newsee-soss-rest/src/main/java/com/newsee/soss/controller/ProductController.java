/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 */
package com.newsee.soss.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.soss.common.SossConstants;
import com.newsee.soss.service.IProductService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.ProductVo;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/product")
@Api(tags = {"com.newsee.soss.controller.ProductController"}, description = "产品列表页面操作 REST API，包含产品页面的所有操作方法。")
public class ProductController{
    
    @Autowired
    private IProductService productService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    
    @Autowired
    RedisUtil redisUtil;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
         String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.SOSS_PRODUCTLIST);
         String interpreter = LoginDataHelper.getFieldInterpreter();
         String formOperateType = LoginDataHelper.getFormOperateType();
         LoginCommonDataVo commonVo = new LoginCommonDataVo();
         commonVo.setOrganizationId(organizationId);
         commonVo.setEnterpriseId(enterpriseId);
         commonVo.setGroupLevelOrgId(groupLevelOrgId);
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
    
    @ApiOperation(value = "产品列表获取")
	@RequestMapping(value = "/list-product", method = RequestMethod.POST)
	public RestResult<PageInfo<ProductVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	RestResult<PageInfo<ProductVo>> restResult = null;
        PageInfo<ProductVo> pageInfo = productService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "产品详情获取")
	@RequestMapping(value = "/detail-product", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailProduct(@ApiParam(value = "产品ID") @RequestParam(value = "id")Long id) 
			throws IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException,
			IntrospectionException{
		RestResult<Map<String, Object>> result = null;
		//获取产品详情信息
		ProductVo vo = productService.detail(id);
		//获取表单
        LoginCommonDataVo commonVo = LoginDataHelper.initLoginCommonDataVo();
        RestResult<Map<String, Object>> results = systemRemoteService.listField(commonVo);
        Map<String, Object> resultData = results.getResultData();
        if (!Objects.isNull(vo)) {
            vo = CommonUtils.clearNull(vo);
            //详情覆盖modelData
            resultData.put(FormConstants.FORM_MODEL_DATA, vo);
            //将json字符串形式的form表单装换成相应的对象
            List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
            resultData.put(FormConstants.FORM_FIELDS, formFields);
        }
	    result = new RestResult<>(resultData);
		return result;
	}

	@ApiOperation(value = "编辑产品")
	@RequestMapping(value = "/add-product", method = RequestMethod.POST)
	public RestResult<Boolean> aaProduct(@ApiParam(value = "产品详情")@RequestBody ProductVo vo) {
		//编辑产品详情信息
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		vo.setCreateUserId(userId);
		vo.setCreateUserName(userName);
		vo.setUpdateUserId(userId);
		vo.setUpdateUserName(userName);
		String code = null;
		if (!StringUtils.isEmpty(vo.getIconUrl())) {
			code  = CommonUtils.getUUId();
			vo.setIconCode(code);
			//TODO 保存系统文件
			
		}
		if (!StringUtils.isEmpty(vo.getImageUrl())) {
			String tCode = CommonUtils.getUUId();
			vo.setImageCode(tCode);
			//TODO 保存系统文件
			
		}
		boolean result = productService.add(vo);
		//存放图片信息
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑产品")
	@RequestMapping(value = "/edit-product", method = RequestMethod.POST)
	public RestResult<Boolean> editProduct(@ApiParam(value = "产品详情")@RequestBody ProductVo vo) {
		String code = null;
		//编辑产品详情信息
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		vo.setUpdateUserId(userId);
		vo.setUpdateUserName(userName);
		if (!StringUtils.isEmpty(vo.getIconUrl())) {
			code  = CommonUtils.getUUId();
			vo.setIconCode(code);
			//TODO 保存系统文件
			
		}
		if (!StringUtils.isEmpty(vo.getImageUrl())) {
			String tCode = CommonUtils.getUUId();
			vo.setImageCode(tCode);
			//TODO 保存系统文件
			
		}
		boolean result = productService.edit(vo);
		return new RestResult<Boolean>(result);
	}

	@ApiOperation(value = "删除产品")
	@RequestMapping(value = "/delete-product")
	public RestResult<Boolean> deleteProduct(@ApiParam(value = "产品ID") @RequestParam("id") Long id) {
		//删除产品详情信息
		boolean result = productService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除产品")
	@RequestMapping(value = "/delete-product-batch")
	public RestResult<Boolean> deleteProductBatch(@ApiParam(value = "产品ID")@RequestBody List<Long> ids) {
		//删除产品详情信息
		boolean result = productService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
	
	//==================客户===========================
	@ApiOperation(value = "客户获取所有产品")
	@RequestMapping(value = "/customer-list-product", method=RequestMethod.GET)
	public RestResult<?> customerProductList() {
		List<ProductVo> productList = null;
		try {
			Map<String, List<ProductVo>> productGroup = productService.findProductList();
			if (CollectionUtils.isEmpty(productGroup)) {
				return RestResult.DATA_NOT_EXIST;
			}
			productList = new ArrayList<>(productGroup.size());
			//获取字典类型
			NsCoreDictionary dictionary = new NsCoreDictionary();
			dictionary.setOrganizationId(LoginDataHelper.getOrgId());			 
			dictionary.setDictionaryDdcode(SossConstants.PRODUCT_TYPE); //产品套餐类型
			RestResult<NsCoreDictionaryVo> tempRes = systemRemoteService.getDictionary(dictionary);
			List<NsCoreDictionaryitemVo> itemList = null;
			if (tempRes != null && !Objects.isNull(tempRes.getResultData())) { //获取产品套餐类型
				NsCoreDictionaryVo dicVo = tempRes.getResultData();
				itemList = dicVo.getDictionaryitemVos();
			}
			//转换字典数据
			Map<String, String> dicMap = new HashMap<>();
			if (!CollectionUtils.isEmpty(itemList)) {
				for (NsCoreDictionaryitemVo item : itemList) {
					dicMap.put(item.getDictionaryitemItemcode(), item.getDictionaryitemItemname());
				}
			}
			//处理返回前端数据
			for (Iterator<Entry<String, List<ProductVo>>> iterator = productGroup.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, List<ProductVo>> entry = iterator.next();
				ProductVo productVo = new ProductVo();
				productVo.setProductCode(entry.getKey());
				for (ProductVo pro : entry.getValue()) {
					productVo.setProductName(pro.getProductName());
					pro.setPrecinctList(new ArrayList<>(1));
					if (dicMap.containsKey(pro.getProductType())) {
						pro.setProductTypeName(dicMap.get(pro.getProductType()));						
					}	
				}
				productVo.setProductList(entry.getValue());
				productList.add(productVo);
			}
		} catch (Exception e) {
			throw new BizException(ResultCodeEnum.FAILURE.CODE, e.getMessage());
		}
		
		return new RestResult<>(productList);
	}
	
}
