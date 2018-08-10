package com.newsee.bill.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.newsee.bill.service.IBillReService;
import com.newsee.bill.vo.BillReVo;
import com.newsee.common.vo.SearchVo;

@Service
public class BillReServiceImpl implements IBillReService {

	@Override
	public PageInfo<BillReVo> listPage(SearchVo searchVo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BillReVo detail(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean edit(BillReVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean add(BillReVo vo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBatch(List<Long> ids) {
		// TODO Auto-generated method stub
		return false;
	}
	
  /*  @Autowired
    private NsBillAutobillnumsetMapper nsBillAutobillnumsetMapper;
    
	*//**
	 * 获取票据补录列表信息
	 * @param searchVo 检索条件
	 * @return
	 *//*
	public PageInfo<BillRe> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<BillRe> list = nsBillAutobillnumsetMapper.listPage(searchVo);
        PageInfo<BillRe> pageInfo = new PageInfo<>(list);
        return pageInfo;
	}
	
	*//**
	 * 获取票据补录详情
	 * @param id 主键id
	 * @return
	 *//*
	public BillReVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		BillReVo vo = new BillReVo();
		NsBillAutobillnumset nsBillAutobillnumset = nsBillAutobillnumsetMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsBillAutobillnumset)){
			BeanUtils.copyProperties(nsBillAutobillnumset, vo);
		}
	    return vo;
	}
	
	*//**
	 * 编辑票据补录详情
	 * @return boolean 编辑成功与否
	 *//*
	public boolean edit(BillReVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsBillAutobillnumset nsBillAutobillnumset = new NsBillAutobillnumset();
		BeanUtils.copyProperties(vo, nsBillAutobillnumset);
		int countnsBillAutobillnumset = nsBillAutobillnumsetMapper.updateById(nsBillAutobillnumset);
		if(countnsBillAutobillnumset == 0 ){
			return false;
		}
	    return true;
	}
	
	*//**
	 * 新增票据补录
	 * @return boolean 新增成功与否
	 *//*
	public boolean add(BillReVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsBillAutobillnumset nsBillAutobillnumset = new NsBillAutobillnumset();
		BeanUtils.copyProperties(vo, nsBillAutobillnumset);
		int countnsBillAutobillnumset = nsBillAutobillnumsetMapper.insert(nsBillAutobillnumset);
		if(countnsBillAutobillnumset == 0 ){
			return false;
		}
	    return true;
	}
	
	*//**
	 * 根据主键删除票据补录
	 * @param id 主键id
	 * @return
	 *//*
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsBillAutobillnumset = nsBillAutobillnumsetMapper.deleteById(id);
		if(countnsBillAutobillnumset == 0 ){
			return false;
		}
	    return true;
	}
	
	*//**
	 * 根据主键批量删除票据补录
	 * @param ids
	 * @return
	 *//*
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsBillAutobillnumset = nsBillAutobillnumsetMapper.deleteBatch(ids);
		if(countnsBillAutobillnumset == 0 ){
			return false;
		}
	    return true;
	}*/
}
