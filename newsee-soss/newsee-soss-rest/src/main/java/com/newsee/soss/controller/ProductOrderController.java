/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.soss.controller;

import java.beans.IntrospectionException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.Constants;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.constant.MenuEnNameConstants;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.login.MenuHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.redis.util.RedisUtil;
import com.newsee.soss.common.SossConstants;
import com.newsee.soss.entity.NsSossProductOrderPrecinct;
import com.newsee.soss.entity.NsSossProductOrderProduct;
import com.newsee.soss.service.IProductOrderService;
import com.newsee.soss.service.IProductService;
import com.newsee.soss.service.remote.ICustomerRemoteService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.ProductOrderVo;
import com.newsee.soss.vo.ProductVo;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;
import com.newsee.system.vo.NsSystemSuperAdmin;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/productOrder")
@Api(tags = {"com.newsee.soss.controller.ProductOrderController"}, description = "产品订单列表页面操作 REST API，包含产品订单页面的所有操作方法。")
public class ProductOrderController{
	@Autowired
    private IProductService productService;
    
    @Autowired
    private IProductOrderService productOrderService;
    
    @Autowired
    private ISystemRemoteService systemRemoteService;
    @Autowired
    private ICustomerRemoteService customerRemoteService;
    @Autowired
    RedisUtil redisUtil;
    
    @ApiOperation(value = "初始化表单项目")
    @RequestMapping(value = "/init-form", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> initForm(){
    	 Long organizationId= LoginDataHelper.getOrgId();
         Long enterpriseId = LoginDataHelper.getEnterpriseId();
         Long groupLevelOrgId = LoginDataHelper.getGroupLevelOrgId();
         String funcId = MenuHelper.getFuncIdByMenuEnName(MenuEnNameConstants.SOSS_PRODUCTORDERLIST);
         String interpreter = LoginDataHelper.getFieldInterpreter();
         String formOperateType = LoginDataHelper.getFormOperateType();
         LoginCommonDataVo commonVo = new LoginCommonDataVo();
         commonVo.setOrganizationId(organizationId);
         commonVo.setEnterpriseId(enterpriseId);
         commonVo.setFuncId(funcId);
         commonVo.setGroupLevelOrgId(groupLevelOrgId);
         commonVo.setInterpreter(interpreter);
         commonVo.setFormOperateType(formOperateType);
         RestResult<Map<String, Object>> result = systemRemoteService.listField(commonVo);
         Map<String, Object> resultData = result.getResultData();
         //检查表单中是否有表格项目，并且做相应处理
         List<NsCoreResourcefieldVo> formFields = FormUtils.getFormFields(resultData);
         resultData.put(FormConstants.FORM_FIELDS, formFields);
         return result;
    }
    
    @ApiOperation(value = "产品订单列表获取")
	@RequestMapping(value = "/list-productOrder", method = RequestMethod.POST)
	public RestResult<PageInfo<ProductOrderVo>> listPage(@ApiParam(value = "查询条件")@RequestBody SearchVo searchVo) {
    	Long enterpriseId = LoginDataHelper.getEnterpriseId();
    	Integer userType = LoginDataHelper.getAppUser().getUserType();
    	if(0 != userType ) {searchVo.setEnterpriseId(enterpriseId);}
    	RestResult<PageInfo<ProductOrderVo >> restResult = null;
        PageInfo<ProductOrderVo> pageInfo = productOrderService.listPage(searchVo);
        restResult = new RestResult<>(pageInfo);            
        return restResult;
	}

    @ApiOperation(value = "产品订单详情获取")
	@RequestMapping(value = "/detail-productOrder", method = RequestMethod.GET)
	public RestResult<Map<String, Object>> detailProductOrder(@ApiParam(value = "产品订单ID") @RequestParam(value = "id")Long id) 
			throws IllegalArgumentException,
			IllegalAccessException,
			InvocationTargetException,
			IntrospectionException{
		RestResult<Map<String, Object>> result = null;
		//获取产品订单详情信息
		ProductOrderVo vo = productOrderService.detail(id);
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

	@ApiOperation(value = "购买产品订单")
	@RequestMapping(value = "/add-productOrder", method = RequestMethod.POST)
	public RestResult<Boolean> aaProductOrder(@ApiParam(value = "产品订单详情")@RequestBody ProductOrderVo vo) {
		//编辑产品订单详情信息
		Long enterpriseId = LoginDataHelper.getEnterpriseId();		
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		Long orgId = LoginDataHelper.getOrgId();
		
		
		NsSossEnterprise enterprise = LoginDataHelper.getNsPlatformEnterprise();
		vo.setEnterpriseName(enterprise.getName());		 
		vo.setEnterpriseId(enterpriseId);	 
		vo.setCreateUserId(userId);
		vo.setCreateUserName(userName);
		if (!Objects.isNull(vo.getId())) { //获取对应的产品信息
			ProductVo product = productService.detail(vo.getId());
			NsSossProductOrderProduct orderProduct = new NsSossProductOrderProduct();
			BeanUtils.copyProperties(product, orderProduct);
			orderProduct.setProductId(product.getId());
			orderProduct.setId(null);
			vo.setOrderProduct(orderProduct);
		}
		
		//订单保存成功后，同步房产表信息
		List<NsSossProductOrderPrecinct> precintList = vo.getPrecinctList();
		if (!CollectionUtils.isEmpty(precintList)) {
			List<String> names = precintList.stream().map(obj -> obj.getPrecinctName()).collect(Collectors.toList());
			String houseNameJson = String.join(",", names);
			Map<String, Long> map = customerRemoteService.addHousePrecinctRemote(enterpriseId, houseNameJson, Constants.HOUSE_TYPE_PRECINCT);
			if (map != null) {
				//合并项目数据
				for (NsSossProductOrderPrecinct precinct : precintList) {
					if (map.containsKey(precinct.getPrecinctName())) {
						precinct.setPrecinctId(map.get(precinct.getPrecinctName()));
					}
				}
				
			}
		}
		
		//保存购买订单
		boolean result = productOrderService.add(vo);
		//TODO 根据购买产品，开通对应产品权限
		if (result) {
			List<Long> houseIds = precintList.stream().map(obj -> obj.getPrecinctId()).collect(Collectors.toList());
			NsSystemSuperAdmin superAdmin = new NsSystemSuperAdmin();
			superAdmin.setOrganizationId(orgId);
			superAdmin.setUserid(userId);
			superAdmin.setHouseIds(houseIds);
			superAdmin.setEnterpriseId(enterpriseId);
			RestResult<Boolean> res = systemRemoteService.createSuperAdmin(superAdmin);
			result = res.getResultData();
		}
		
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "编辑产品订单")
	@RequestMapping(value = "/edit-productOrder", method = RequestMethod.POST)
	public RestResult<Boolean> editProductOrder(@ApiParam(value = "产品订单详情")@RequestBody ProductOrderVo vo) {
		//编辑产品订单详情信息
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		vo.setUpdateUserId(userId);
		vo.setUpdateUserName(userName);
		boolean result = productOrderService.edit(vo);
		return new RestResult<Boolean>(result);
	}
	@ApiOperation(value = "开通关闭订单服务")
	@RequestMapping(value = "/edit-productOrderStatus", method = RequestMethod.POST)
	public RestResult<Boolean> editProductOrderStatus(@ApiParam(value = "产品订单详情")@RequestBody NsSossProductOrderProduct vo) {
		//编辑产品订单详情信息
		boolean result = productOrderService.editProductOrderStatus(vo);
		return new RestResult<Boolean>(result);
	}
	/*@ApiOperation(value = "续费")
	@RequestMapping(value = "/edit-productOrder-payStatus", method = RequestMethod.POST)
	public RestResult<Boolean> editPayStatus(@ApiParam(value = "产品订单详情")@RequestParam("id") Long id) {
		//编辑产品订单详情信息
		Long userId = LoginDataHelper.getUserId();
		String userName = LoginDataHelper.getUserName();
		ProductOrderVo vo = new ProductOrderVo();
		vo.setUpdateUserId(userId);
		vo.setUpdateUserName(userName);
		vo.setId(id);
		boolean result = productOrderService.editPayStatus(vo);
		return new RestResult<Boolean>(result);
	}*/

	@ApiOperation(value = "删除产品订单")
	@RequestMapping(value = "/delete-productOrder")
	public RestResult<Boolean> deleteProductOrder(@ApiParam(value = "产品订单ID") @RequestParam("id") Long id) {
		//删除产品订单详情信息
		boolean result = productOrderService.delete(id);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "批量删除产品订单")
	@RequestMapping(value = "/delete-productOrder-batch")
	public RestResult<Boolean> deleteProductOrderBatch(@ApiParam(value = "产品订单ID") @RequestBody List<Long> ids) {
		//删除产品订单详情信息
		boolean result = productOrderService.deleteBatch(ids);
		return new RestResult<Boolean>(result);
	}
	
	@ApiOperation(value = "客户获取产品订单详情")
	@RequestMapping(value = "/get-detail", method = RequestMethod.GET)
	public RestResult<?> getDetailInfo(@ApiParam(value = "订单产品ID") @RequestParam(value = "productId")Long productId) {
		RestResult<?> result = RestResult.FAILURE;
		ProductOrderVo order = productOrderService.detail(productId);
		if (Objects.isNull(order)) {
			return result;
		}
		Map<String, String> dicMap = getDictionary(SossConstants.PRODUCT_TYPE);
		if (!Objects.isNull(order.getOrderProduct())) {
			NsSossProductOrderProduct pro = order.getOrderProduct();			 
			order.setProductName(pro.getProductName());
			order.setProductType(pro.getProductType());
			order.setProductTypeName(dicMap.get(pro.getProductType()));			 				
			order.setServiceStatus(pro.getServiceStatus());
			order.setServiceWork(pro.getServiceWork());
			order.setTrialCycle(pro.getTrialCycle());
			order.setIsTrial(pro.getIsTrial());
			order.setServiceArea(pro.getServiceArea());
			order.setServiceCount(pro.getServiceCount());
			order.setStartTime(pro.getStartTime());
			order.setEndTime(pro.getEndTime());
			order.setProductOrderId(pro.getProductOrderId());
			order.setId(pro.getId());
			order.setPrice(pro.getPrice().longValue());
			order.setOrderProduct(null);
			order.setIntroduce(pro.getIntroduce());
		}
		
		result = new RestResult<>(order);		
		return result;
	}
	
	/**
	 * 过滤服务项目，服务面积 - remote
	 * @param precinctId
	 * @param area
	 * @return
	 */
	public boolean filterProductOrderPrecinct(Long precinctId, Long area) {
		if (precinctId == null || precinctId <= 0L) {
			throw new NullPointerException("precinctId is null");
		}
		NsSossProductOrderPrecinct precinct = productOrderService.findProductPrecinct(precinctId);
		if (precinct != null) {
			if(precinct.getPrecinctArea().compareTo(area) >= 0) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 更新服务项目名称 - remote
	 * @param precinctId
	 * @param precinctName
	 * @return
	 */
	public boolean updateProductOrderPrecinct(Long precinctId, String precinctName) {
		if (precinctId == null || precinctId <= 0L) {
			throw new NullPointerException("precinctId is null");
		} else if (StringUtils.isBlank(precinctName)) {
			throw new NullPointerException("precinctName is null");
		}
		int result = productOrderService.updateProductPrecinct(precinctId, precinctName);
		return result > 0;
	}
	
	@ApiOperation(value = "产品服务是否开通")
	@RequestMapping(value = "/update-serviceWork", method = RequestMethod.POST)
	public RestResult<Boolean> modifyServiceStatus(@ApiParam(value = "服务是否开通") @RequestBody NsSossProductOrderProduct product) {
		boolean result = productOrderService.updateOrderProduct(product);
		return new RestResult<Boolean>(result);
	}
	
	//检查服务状态（生效中，即将过期，已过期）
	public void checkOrderProduct(int day) {
		//TODO 配置过期提醒时间，比如提前15天，提醒
		List<NsSossProductOrderProduct> list = productOrderService.checkOrderProductStatus(day);
		List<Long> idList = null;
		if (!CollectionUtils.isEmpty(list)) {
			idList = list.stream().map(obj -> obj.getId()).collect(Collectors.toList());
		}
		//修改服务状态
		productOrderService.updateOrderProductStatus(idList);
		
	}
	
	private Map<String, String> getDictionary(String type) {		
		//获取字典类型
		NsCoreDictionary dictionary = new NsCoreDictionary();
		dictionary.setOrganizationId(LoginDataHelper.getOrgId());			 
		dictionary.setDictionaryDdcode(type); //产品套餐类型
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
		return dicMap;
	}
	
}
