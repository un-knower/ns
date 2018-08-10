package com.newsee.bill.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.bill.dao.NsbillBillDrawMapper;
import com.newsee.bill.entity.NsbillBillDraw;
import com.newsee.bill.service.IBillCoService;
import com.newsee.bill.vo.BillCoVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.WriteDataSource;

@Service
public class  BillCoServiceImpl implements IBillCoService {
	
    @Autowired
    private NsbillBillDrawMapper nsBillBilldrawMapper;
    
	/**
	 * 获取票据领用列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<BillCoVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<BillCoVo> list = nsBillBilldrawMapper.listPage(searchVo);
        PageInfo<BillCoVo> pageInfo = new PageInfo<>(list);
        return pageInfo;
	}
	
	/**
	 * 获取票据领用详情
	 * @param id 主键id
	 * @return
	 */
	public BillCoVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		BillCoVo vo = new BillCoVo();
		BillCoVo nsBillBilldraw = nsBillBilldrawMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsBillBilldraw)){
			BeanUtils.copyProperties(nsBillBilldraw, vo);
		}
	    return vo;
	}
	
	/**
	 * 编辑票据领用详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(BillCoVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		BillCoVo nsBillBilldraw = new BillCoVo();
		BeanUtils.copyProperties(vo, nsBillBilldraw);
		int countnsBillBilldraw = nsBillBilldrawMapper.updateById(nsBillBilldraw);
		if(countnsBillBilldraw == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 新增票据领用
	 * @return boolean 新增成功与否
	 */
	@WriteDataSource
	public boolean add(NsbillBillDraw vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		int countnsBillBilldraw = nsBillBilldrawMapper.insert(vo);
		if(countnsBillBilldraw == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键删除票据领用
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsBillBilldraw = nsBillBilldrawMapper.deleteById(id);
		if(countnsBillBilldraw == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键批量删除票据领用
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsBillBilldraw = nsBillBilldrawMapper.deleteBatch(ids);
		if(countnsBillBilldraw == 0 ){
			return false;
		}
	    return true;
	}
}
