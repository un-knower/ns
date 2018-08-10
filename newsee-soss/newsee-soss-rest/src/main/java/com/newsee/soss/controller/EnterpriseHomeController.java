package com.newsee.soss.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.constant.Constants;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.soss.common.SossConstants;
import com.newsee.soss.entity.NsSossMsgRecord;
import com.newsee.soss.entity.NsSossProductOrderPrecinct;
import com.newsee.soss.entity.NsSossProductOrderProduct;
import com.newsee.soss.service.IMsgRecordService;
import com.newsee.soss.service.IProductOrderService;
import com.newsee.soss.service.IServiceService;
import com.newsee.soss.service.remote.ISystemRemoteService;
import com.newsee.soss.vo.MsgRecordVo;
import com.newsee.soss.vo.ProductOrderVo;
import com.newsee.soss.vo.ServiceVo;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/home")
@Api(tags = {"com.newsee.soss.controller.EnterpriseHomeController"}, description = "企业概率首页")
public class EnterpriseHomeController {
	@Autowired
	private IServiceService serviceService;
	@Autowired
	private IProductOrderService productOrderService;
	@Autowired
	private ISystemRemoteService systemRemoteService;
	@Autowired
	private IMsgRecordService msgRecordService;
	
	/**客户企业*****************************/
	@ApiOperation(value = "客户购买的服务")
    @RequestMapping(value = "/enterprise-orderProduct", method = RequestMethod.GET)
	public RestResult<?> customerHomeProduct() {
		RestResult<?> result = null;
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long orgId = LoginDataHelper.getOrgId();
		//获取字典类型
		NsCoreDictionary dictionary = new NsCoreDictionary();
		dictionary.setOrganizationId(orgId);			 
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
		//获取订单产品
		List<ProductOrderVo> productOrders = productOrderService.findProductOrderInfo(enterpriseId);
		if (!CollectionUtils.isEmpty(productOrders)) {
			List<NsSossProductOrderPrecinct> tempList = null;
			NsSossProductOrderProduct pro = null;
			for (ProductOrderVo order : productOrders) {
				//产品信息
				pro = order.getOrderProduct();				 
				order.setProductName(pro.getProductName());
				order.setProductType(pro.getProductType());
				if (dicMap.containsKey(pro.getProductType())) {
					order.setProductTypeName(dicMap.get(pro.getProductType()));
				}				
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
				order.setOrderProduct(null);
				//TODO 过滤产品是否过期
				
				//项目信息
				tempList = order.getPrecinctList();
				if (!CollectionUtils.isEmpty(tempList)) {
					String tp = String.join(",", tempList.stream().map(obj -> obj.getPrecinctName()).collect(Collectors.toList()));
					order.setPrecinctName(tp);
				}
				order.setPrecinctList(null);
			}
		}
		result = new RestResult<Object>(productOrders);
		return result;
	}
	
	@ApiOperation(value = "客户工单情况")
    @RequestMapping(value = "/service-count", method = RequestMethod.GET)
	public RestResult<?> customerHomeService() {
		RestResult<?> result = RestResult.SUCCESS;
		ServiceVo serviceVo = new ServiceVo();
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		Long orgId = LoginDataHelper.getOrgId();
		Long userId = LoginDataHelper.getUserId();
		
		Map<String, Integer> map = serviceService.getServiceCountInfo(enterpriseId, userId);
		if(!CollectionUtils.isEmpty(map)) {
			//获取数据字典状态值
			NsCoreDictionary dictionary = new NsCoreDictionary();
			dictionary.setEnterpriseId(enterpriseId);
			dictionary.setOrganizationId(orgId);
			dictionary.setDictionaryDdcode(SossConstants.SERVICE_STATUS);
			RestResult<NsCoreDictionaryVo> dic = systemRemoteService.getDictionary(dictionary);
			if (dic != null && ResultCodeEnum.SUCCESS.CODE.equals(dic.getResultCode())) {
				NsCoreDictionaryVo dicVo = dic.getResultData();
				List<NsCoreDictionaryitemVo> items = dicVo.getDictionaryitemVos();
				int total = 0;
				for (NsCoreDictionaryitemVo item : items) {
					if (map.containsKey(item.getDictionaryitemItemcode())) { //数据匹配并处理								 
						total += map.get(item.getDictionaryitemItemcode());
						if (SossConstants.SERVICE_STATUS_VALUE_WAIT.equals(item.getDictionaryitemItemcode())) {//待受理
							serviceVo.setServiceWait(map.get(item.getDictionaryitemItemcode()));
						} else if (SossConstants.SERVICE_STATUS_VALUE_CONFIRM.equals(item.getDictionaryitemItemcode())) { //待确认
							serviceVo.setServiceConfirm(map.get(item.getDictionaryitemItemcode()));
						} else if (SossConstants.SERVICE_STATUS_VALUE_EVLUING.equals(item.getDictionaryitemItemcode())) { //待评价
							serviceVo.setServiceEvalu(map.get(item.getDictionaryitemItemcode()));
						}				 
						
					}				
				}
				serviceVo.setServiceTotal(total);
				result = new RestResult<>(serviceVo);
			}
		}
		
		
		return result;
	}
	
	@ApiOperation(value = "查询未读消息信息")
    @RequestMapping(value = "/get-msg-record", method = RequestMethod.GET)
	public RestResult<?> findMessage() {
		MsgRecordVo vo = new MsgRecordVo();
		List<NsSossMsgRecord> list = null;
		Long enterpriseId = LoginDataHelper.getEnterpriseId();
		NsSystemUser user = LoginDataHelper.getNsSystemUser();
		if (String.valueOf(Constants.USER_TYPE_PLAT).equals(user.getUserType())) { //平台管理员
			vo.setEnterpriseId(enterpriseId);
			vo.setIsRead("0"); //查询未读的
			list = msgRecordService.findMsgRecordList(vo);
		}
		
		return new RestResult<>(list);
	}
	
	/**运营企业*****************************/
	

}
