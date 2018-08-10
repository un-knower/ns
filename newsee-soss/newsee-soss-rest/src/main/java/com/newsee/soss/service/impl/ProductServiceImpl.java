package com.newsee.soss.service.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.dao.NsSossProductMapper;
import com.newsee.soss.entity.NsSossProduct;
import com.newsee.soss.service.IProductService;
import com.newsee.soss.vo.ProductVo;

@Service
public class ProductServiceImpl implements IProductService {
	
    @Autowired
    private NsSossProductMapper nsSossProductMapper;
    
	/**
	 * 获取产品列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<ProductVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
		List<ProductVo> voList = new ArrayList<>();
		ProductVo productVo = null;
		PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
		List<NsSossProduct> list = nsSossProductMapper.listResultBySearch(searchVo);
		for (NsSossProduct product : list) {
			productVo = new ProductVo();
			BeanUtils.copyProperties(product, productVo);
			voList.add(productVo);
		}
        PageInfo<ProductVo> pageInfo = new PageInfo<>(voList);
        
        return pageInfo;
	}
	
	/**
	 * 获取产品详情
	 * @param id 主键id
	 * @return
	 */
	public ProductVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		ProductVo vo = new ProductVo();
		NsSossProduct nsSossProduct = nsSossProductMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsSossProduct)){
			BeanUtils.copyProperties(nsSossProduct, vo);
		}
	    return vo;
	}
	
	/**
	 * 编辑产品详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(ProductVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossProduct nsSossProduct = new NsSossProduct();
		BeanUtils.copyProperties(vo, nsSossProduct);		 
		int countnsSossProduct = nsSossProductMapper.updateById(nsSossProduct);
		if(countnsSossProduct == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 新增产品
	 * @return boolean 新增成功与否
	 */
	public boolean add(ProductVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossProduct nsSossProduct = new NsSossProduct();
		BeanUtils.copyProperties(vo, nsSossProduct);		
		int countnsSossProduct = nsSossProductMapper.insert(nsSossProduct);
		if(countnsSossProduct == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键删除产品
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsSossProduct = nsSossProductMapper.deleteById(id);
		if(countnsSossProduct == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键批量删除产品
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsSossProduct = nsSossProductMapper.deleteBatch(ids);
		if(countnsSossProduct == 0 ){
			return false;
		}
	    return true;
	}

	@Override
	public Map<String, List<ProductVo>> findProductList() throws Exception {
		List<ProductVo> voList = null;
		List<NsSossProduct> list = nsSossProductMapper.selectProductList(null);
		if (!CollectionUtils.isEmpty(list)) {
			voList = new ArrayList<>(list.size());
			ProductVo productVo = null;
			for (NsSossProduct product : list) {
				productVo = new ProductVo();
				BeanUtils.copyProperties(product, productVo);
				voList.add(productVo);
			}
		}
		Map<String, List<ProductVo>> productGroup = null;
		if (!CollectionUtils.isEmpty(voList)) {
			//对产品分类-收费易，O2O等
			productGroup = voList.stream().collect(Collectors.groupingBy(ProductVo :: getProductCode));
			//对各产品类型内部排序
			for (Iterator<Entry<String, List<ProductVo>>> iterator = productGroup.entrySet().iterator(); iterator.hasNext();) {
				Entry<String, List<ProductVo>> entry = iterator.next();
				entry.getValue().stream().sorted(new Comparator<ProductVo>() {
					@Override
					public int compare(ProductVo vo1, ProductVo v2) {
						return vo1.getProductType().compareTo(v2.getProductType());
					}
				});
			}
		}
		
		return productGroup;
	}
	
}
