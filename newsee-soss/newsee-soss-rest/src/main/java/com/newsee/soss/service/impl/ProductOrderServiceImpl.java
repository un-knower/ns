package com.newsee.soss.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.constant.Constants;
import com.newsee.common.entity.FilterEntity;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.DateUtils;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.dao.CommonMapper;
import com.newsee.soss.dao.NsSossProductMapper;
import com.newsee.soss.dao.NsSossProductOrderMapper;
import com.newsee.soss.dao.NsSossProductOrderPrecinctMapper;
import com.newsee.soss.dao.NsSossProductOrderProductMapper;
import com.newsee.soss.dao.NsSossProductOrderRecordMapper;
import com.newsee.soss.entity.NsSossProduct;
import com.newsee.soss.entity.NsSossProductOrder;
import com.newsee.soss.entity.NsSossProductOrderPrecinct;
import com.newsee.soss.entity.NsSossProductOrderProduct;
import com.newsee.soss.entity.NsSossProductOrderRecord;
import com.newsee.soss.service.IProductOrderService;
import com.newsee.soss.vo.ProductOrderVo;


@Service
public class ProductOrderServiceImpl implements IProductOrderService {
	
    @Autowired
    private NsSossProductOrderMapper nsSossProductorderMapper;
    @Autowired
    private NsSossProductOrderProductMapper nsSossProductorderProductMapper;
    @Autowired
    private NsSossProductOrderPrecinctMapper nsSossProductorderPrecinctMapper;
    @Autowired
    private CommonMapper commonMapper;
    @Autowired
    private NsSossProductOrderRecordMapper nsSossProductOrderRecordMapper;
    @Autowired
    private NsSossProductMapper nsSossProductMapper;
    //DB库名
    private String tableSchema = "newsee-soss";
    //列名
    private String columnName = "COLUMN_NAME";
    //订单产品表别名
    private String orderProductAlia = "pop.";
    //订单表别名
    private String orderAlia = "p_order.";
    
	/**
	 * 获取产品订单列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<ProductOrderVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
		List<FilterEntity> filterList = searchVo.getFilterList();
		Map<String, String> map = new HashMap<>();
		map.put("tableSchema", tableSchema);
		//订单产品表，参数封装
		map.put("tableName", "ns_soss_productOrder_product");
		List<Map<String, String>> columns = commonMapper.selectTableInfo(map);
		List<String> columnNames = null;
		if (!CollectionUtils.isEmpty(columns)) { //提取列名
			columnNames = columns.stream().map(obj -> obj.get(columnName)).collect(Collectors.toList());
		}
		if (CollectionUtils.isEmpty(columnNames)) {
			return null;
		}
		for (FilterEntity filter : filterList) {
			if(columnNames.contains(filter.getFieldName())) {
				filter.setFieldName(orderProductAlia + filter.getFieldName());				
			}
		}
		if(columnNames.contains(searchVo.getOrderFieldName())) {
			searchVo.setOrderFieldName(orderProductAlia + searchVo.getOrderFieldName());
		}
		columns.clear();
		columnNames.clear();
		//订单表，参数封装
		map.put("tableName", "ns_soss_productOrder");
		columns = commonMapper.selectTableInfo(map);
		if (!CollectionUtils.isEmpty(columns)) { //提取列名
			columnNames = columns.stream().map(obj -> obj.get(columnName)).collect(Collectors.toList());
		}
		if (CollectionUtils.isEmpty(columnNames)) {
			return null;
		}
		for (FilterEntity filter : filterList) {
			if(columnNames.contains(filter.getFieldName())) {
				filter.setFieldName(orderAlia + filter.getFieldName());				
			}
		}
		if(columnNames.contains(searchVo.getOrderFieldName())) {
			searchVo.setOrderFieldName(orderAlia + searchVo.getOrderFieldName());
		}
		columns.clear();
		columnNames.clear();
		map.clear();
		//查询数据
		PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
		List<ProductOrderVo> list = nsSossProductorderProductMapper.listResultBySearch(searchVo);
		List<Long> orderProductIdList = null;
		if (!CollectionUtils.isEmpty(list)) {
			orderProductIdList = list.stream().map(obj -> obj.getId()).collect(Collectors.toList());
			//获取订单产品所服务的项目信息
			Map<String, Object> tMap = new HashMap<>(4);
			tMap.put("orderProductIdList", orderProductIdList);
			List<NsSossProductOrderPrecinct> precinctList = nsSossProductorderPrecinctMapper.selectPrecinctInfos(tMap);
			if (!CollectionUtils.isEmpty(precinctList)) {
				//查询订单记录
				List<NsSossProductOrderRecord> recordList = nsSossProductOrderRecordMapper.selectOrderRecords(orderProductIdList);
				List<NsSossProductOrderPrecinct> tempList = null;
				Map<Long, List<NsSossProductOrderPrecinct>> tempPrecinctGroup = precinctList.stream().collect(Collectors.groupingBy(NsSossProductOrderPrecinct :: getOrderProductId));
				for (ProductOrderVo productOrderVo : list) {
					if (tempPrecinctGroup.containsKey(productOrderVo.getId())) {
						tempList = tempPrecinctGroup.get(productOrderVo.getId());
						productOrderVo.setPrecinctName(String.join(",", tempList.stream().map(obj -> obj.getPrecinctName()).collect(Collectors.toList())));
					}
					for(NsSossProductOrderRecord record : recordList) {
						if(productOrderVo.getId().equals(record.getProductOrderID())) {
							productOrderVo.setRenewEndTime(record.getServiceEndTime());
						}
					}
				}
			}
		}
        PageInfo<ProductOrderVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
	}
	
	/**
	 * 获取产品订单详情
	 * @param id 主键id
	 * @return
	 */
	public ProductOrderVo detail(Long productId){
		if(Objects.isNull(productId)) {
			return null;
		}
		ProductOrderVo vo = null;
		NsSossProductOrderProduct orderProduct = nsSossProductorderProductMapper.selectById(productId);
		if (!Objects.isNull(orderProduct)) {
			Long orderId = orderProduct.getProductOrderId();
			NsSossProductOrder order = nsSossProductorderMapper.selectById(orderId);
			vo = new ProductOrderVo();
			if (!Objects.isNull(order)) {
				BeanUtils.copyProperties(order, vo);	
				vo.setOrderProduct(orderProduct);
			}
			Map<String, Object> map = new HashMap<>();
			map.put("orderProductId", productId);
			List<NsSossProductOrderPrecinct> precinctList = nsSossProductorderPrecinctMapper.selectPrecinctInfos(map);
			vo.setPrecinctList(precinctList);			
		}
		
	    return vo;
	}
	
	/**
	 * 编辑产品订单详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(ProductOrderVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
//		NsSossProductOrder order = new NsSossProductOrder();
//		order.setId(vo.getProductOrderId());
//		order.setUpdateUserId(vo.getUpdateUserId());
//		order.setUpdateUserName(vo.getUpdateUserName());
//		order.setServiceEndTime(vo.getEndTime());
//		order.setPayMoney(vo.getPayMoney());
//		order.setServiceTotalArea(vo.getServiceArea());
//		order.setServiceTotalCount(vo.getServiceTotalCount());
//		order.setUpdateTime(new Date());
//		 nsSossProductorderMapper.updateById(order);
		//订单记录
		NsSossProductOrderRecord record = new NsSossProductOrderRecord();
		record.setProductOrderID(vo.getProductOrderId());
		record.setIsLast(0);
		//更新最新订单状态
		nsSossProductOrderRecordMapper.updateByOrderID(record);
		BeanUtils.copyProperties(vo, record);
		record.setIsLast(1);
		record.setProductOrderID(vo.getId());
		record.setProductName(vo.getProductName());
		record.setProductCode("");
		record.setServiceArea(vo.getPrecinctList().get(0).getPrecinctArea());
		record.setServiceStartTime(vo.getStartTime());
		record.setServiceEndTime(vo.getEndTime());
		record.setServiceCount(vo.getServiceTotalCount());
		record.setPayStatus(Integer.parseInt(vo.getPayStatus()));
		//插入新订单到订单记录表
		int countsNsSossOrderRecord = nsSossProductOrderRecordMapper.insert(record);
//			NsSossProductOrderProduct orderProduct = new NsSossProductOrderProduct();
//			orderProduct.setId(vo.getId());
//			orderProduct.setEndTime(vo.getEndTime());
//			 int res = nsSossProductorderProductMapper.updateById(orderProduct);
	    return countsNsSossOrderRecord >0 ? true : false;
	}
	
	/**
	 * 新增产品订单
	 * @return boolean 新增成功与否
	 */
	public boolean add(ProductOrderVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		//订单
		NsSossProductOrder nsSossProductorder = new NsSossProductOrder();
		BeanUtils.copyProperties(vo, nsSossProductorder);
		nsSossProductorder.setId(null);
		nsSossProductorder.setUpdateUserId(nsSossProductorder.getUpdateUserId());
		nsSossProductorder.setUpdateUserName(nsSossProductorder.getUpdateUserName());		
		List<NsSossProductOrderPrecinct> precinctList = vo.getPrecinctList();
		long totalArea = 0;
		if (!CollectionUtils.isEmpty(precinctList)) {
			for (NsSossProductOrderPrecinct precinct : precinctList) {
				totalArea += precinct.getPrecinctArea();
			}
			nsSossProductorder.setServiceTotalCount(precinctList.size()); //总服务面积
			nsSossProductorder.setServiceTotalArea(totalArea); //总项目数
		}
		//购买周期
		nsSossProductorder.setServiceStartTime(vo.getStartTime());
		nsSossProductorder.setServiceEndTime(vo.getEndTime());
		nsSossProductorder.setOrderCode(Constants.BP+CommonUtils.getOrderIdByUUId(1));
		int countnsSossProductorder = nsSossProductorderMapper.insert(nsSossProductorder);
		if(countnsSossProductorder == 0 ) {
			return false;
		}		
		
		Long orderId = nsSossProductorder.getId();
		
		//订单产品
		NsSossProductOrderProduct nsSossProductorderProduct = vo.getOrderProduct();
		nsSossProductorderProduct.setProductName(vo.getProductName());
		nsSossProductorderProduct.setProductOrderId(orderId);
		nsSossProductorderProduct.setStartTime(vo.getStartTime());
		nsSossProductorderProduct.setEndTime(vo.getEndTime());		
		//TODO 1. 是否试用，2. 是否开通，3. 产品服务状态
		
		int countnsSossProductorderProduct = nsSossProductorderProductMapper.insert(nsSossProductorderProduct);
		if(countnsSossProductorderProduct == 0 ) {
			return false;
		}
		Long orderProductId = nsSossProductorderProduct.getId();
		//订单记录
				NsSossProductOrderRecord record = new NsSossProductOrderRecord();
				record.setProductOrderID(orderId);
				record.setIsLast(0);
				//更新最新订单状态
				nsSossProductOrderRecordMapper.updateByOrderID(record);
				BeanUtils.copyProperties(vo, record);
				record.setIsLast(1);
				record.setProductOrderID(orderProductId);
				record.setProductName(vo.getProductName());
				record.setServiceArea(vo.getPrecinctList().get(0).getPrecinctArea());
				record.setServiceStartTime(vo.getStartTime());
				record.setServiceEndTime(vo.getEndTime());
				record.setServiceCount(vo.getServiceTotalCount());
				record.setPayStatus(0);
				//插入新订单到订单记录表
				int countsNsSossOrderRecord = nsSossProductOrderRecordMapper.insert(record);
				if(countsNsSossOrderRecord ==0) {
					return false;
				}
		//产品管辖项目
		int res = 0;
		for (NsSossProductOrderPrecinct precinct : precinctList) {
			precinct.setOrderProductId(orderProductId);			
			res += nsSossProductorderPrecinctMapper.insert(precinct);
		}
	 
	    return res > 0;
	}
	
	/**
	 * 根据主键删除产品订单
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		NsSossProductOrder productOrder = new NsSossProductOrder();
		productOrder.setId(id);
		productOrder.setIsDelete(1);
		int countnsSossProductorder = nsSossProductorderMapper.updateById(productOrder);
		if(countnsSossProductorder == 0 ) {
			return false;
		}
		
	    return true;
	}
	
	/**
	 * 根据主键批量删除产品订单
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		Map<String, Object> map = new HashMap<>();
		map.put("isDelete", 1);
		map.put("ids", ids);
		int res = nsSossProductorderMapper.updateDeleteBatch(map);
	 
	    return res > 0;
	}

	@Override
	public boolean updateOrderProduct(NsSossProductOrderProduct product) {
		if (Objects.isNull(product)) {
			return false;
		}
		int res = nsSossProductorderProductMapper.updateById(product);
		return res > 0;
	}

	@Override
	public List<ProductOrderVo> findProductOrderInfo(Long enterpriseId) {
		Map<String, Object> map = new HashMap<>();
		List<NsSossProductOrderProduct> orderProductList = null;
		List<NsSossProductOrderPrecinct> precinctList = null;
		//获取订单
		map.put("enterpriseId", enterpriseId);
		List<NsSossProductOrder> productOrders = nsSossProductorderMapper.selectProductOrders(map);
		if (!CollectionUtils.isEmpty(productOrders)) {//获取订单产品
			List<Long> orderIdList = productOrders.stream().map(obj -> obj.getId()).collect(Collectors.toList());
			map.clear();
			map.put("productOrderIdList", orderIdList);
			orderProductList = nsSossProductorderProductMapper.selectOrderProducts(map);
		}
		if (!CollectionUtils.isEmpty(orderProductList)) {//获取产品项目
			map.clear();
			List<Long> orderProductIdList = orderProductList.stream().map(obj -> obj.getId()).collect(Collectors.toList());
			map.put("orderProductIdList", orderProductIdList);
			precinctList = nsSossProductorderPrecinctMapper.selectPrecinctInfos(map);
		}
		
		List<ProductOrderVo> voList = null;
		if (!CollectionUtils.isEmpty(precinctList)) { //处理合并数据
			voList = new ArrayList<>();
			ProductOrderVo vo = null;
			List<NsSossProductOrderPrecinct> list = null;
			for (NsSossProductOrderProduct orderProduct : orderProductList) {
				vo = new ProductOrderVo();
				vo.setProductOrderId(orderProduct.getProductOrderId());
				vo.setOrderProduct(orderProduct);
				for (NsSossProductOrderPrecinct precinct : precinctList) {
					if(orderProduct.getId().compareTo(precinct.getOrderProductId()) == 0) {
						if (CollectionUtils.isEmpty(vo.getPrecinctList())) {
							list = new ArrayList<>();
						} else {
							list = vo.getPrecinctList();
						}
						list.add(precinct);
						vo.setPrecinctList(list);						
					}
				}
				voList.add(vo);
			}
		}
		//插入订单数据
		if (!CollectionUtils.isEmpty(voList)) {
			for (ProductOrderVo vo : voList) {
				for (NsSossProductOrder order : productOrders) {
					if (vo.getProductOrderId().compareTo(order.getId()) == 0) {
						vo.setOrderCode(order.getOrderCode());
						vo.setEnterpriseId(order.getEnterpriseId());
						vo.setEnterpriseName(order.getEnterpriseName());
						vo.setPayMoney(order.getPayMoney());
						vo.setPayStatus(order.getPayStatus());
						vo.setPayTime(order.getPayTime());
						vo.setServiceTotalArea(order.getServiceTotalArea());
						vo.setServiceTotalCount(order.getServiceTotalCount());
					}
				}
			}
		}
		
		return voList;
	}
	@Override
	public List<NsSossProductOrderProduct> checkOrderProductStatus(int expire) {
		//查询未过期的订单产品
		Map<String, Object> map = new HashMap<>();
		map.put("serviceStatusIn", "0,"+Constants.ORDER_PRODUCT_NORMAL+","+Constants.ORDER_PRODUCT_WILL_EXPR);
		//TODO 数据多时，可以分布操作
		
		List<NsSossProductOrderProduct> productList = nsSossProductorderProductMapper.selectOrderProducts(map);
		if (!CollectionUtils.isEmpty(productList)) {
			NsSossProductOrderProduct product = null;
			for (Iterator<NsSossProductOrderProduct> iterator = productList.iterator(); iterator.hasNext();) {
				product = iterator.next();
				int diff = DateUtils.differentDays(product.getEndTime(), new Date());
				if (diff > expire || diff < 0) {
					iterator.remove();
				}
			}
		}
		
		return productList;
	}

	@Override
	public boolean updateOrderProductStatus(List<Long> idList) {
		//更新过期产品
		Map<String, Object> map = new HashMap<>();
		map.put("serviceStatus", Constants.ORDER_PRODUCT_EXPRED);
		map.put("serviceWork", Constants.ORDER_PRODUCT_CLOSE);
		map.put("expire", 1);
		nsSossProductorderProductMapper.updateExprireProduct(map);
		map.clear();
		if (!CollectionUtils.isEmpty(idList)) {
			//更新即将过期产品
			map.put("idList", idList);
			map.put("serviceStatus", Constants.ORDER_PRODUCT_WILL_EXPR);
			nsSossProductorderProductMapper.updateExprireProduct(map);
		}
		
		return true;
	}

	@Override
	public NsSossProductOrderPrecinct findProductPrecinct(Long precinctId) {
		NsSossProductOrderPrecinct precinct = null;
		Map<String, Object> map = new HashMap<>();
		map.put("precinctId", precinctId);
		List<NsSossProductOrderPrecinct> list = nsSossProductorderPrecinctMapper.selectPrecinctInfos(map);
		if (!CollectionUtils.isEmpty(list)) {
			precinct = list.get(0);
		}
		
		return precinct;
	}

	@Override
	public int updateProductPrecinct(Long precinctId, String precinctName) {
		NsSossProductOrderPrecinct precinct = new NsSossProductOrderPrecinct();
		precinct.setPrecinctId(precinctId);
		precinct.setPrecinctName(precinctName);
		int res = nsSossProductorderPrecinctMapper.updateById(precinct);
		
		return res;
	}

	@Override
	public boolean editProductOrderStatus(NsSossProductOrderProduct vo) {
		if(Objects.isNull(vo)) {
			return false;
		}
		int res = nsSossProductorderProductMapper.updateById(vo);
		return res>0;
	}

/*	@Override
	public boolean editPayStatus(ProductOrderVo vo) {
		if(Objects.isNull(vo)) {
			return false;
		}
//		NsSossProductOrderRecord record = nsSossProductOrderRecordMapper.selectOrderRecordByOrderID(vo.getId());
//		if(CommonUtils.isObjectEmpty(record)) {
//			vo.setEndTime(record.getServiceEndTime());
//			vo.setPayStatus("2");
//		}
		return false;
	}
*/	
	
	
}
