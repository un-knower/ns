package com.newsee.soss.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.soss.dao.NsSossNoticeMapper;
import com.newsee.soss.entity.NsSossNotice;
import com.newsee.soss.service.INoticeService;
import com.newsee.soss.vo.NoticeVo;

@Service
public class NoticeServiceImpl implements INoticeService {
	
    @Autowired
    private NsSossNoticeMapper nsSossNoticeMapper;
    
	/**
	 * 获取公告列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<NoticeVo> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
		List<NoticeVo> voList = new ArrayList<>();
		NoticeVo noticeVo = null;
		PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
		List<NsSossNotice> list = nsSossNoticeMapper.listResultBySearch(searchVo);
		for (NsSossNotice notice : list) {
			noticeVo = new NoticeVo();
			BeanUtils.copyProperties(notice, noticeVo);
			noticeVo.setDescription(notice.getContent());
			voList.add(noticeVo);
		}
        PageInfo<NoticeVo> pageInfo = new PageInfo<>(voList);
        return pageInfo;
	}
	
	/**
	 * 获取公告详情
	 * @param id 主键id
	 * @return
	 */
	public NoticeVo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		NoticeVo vo = new NoticeVo();
		NsSossNotice nsSossNotice = nsSossNoticeMapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(nsSossNotice)){
			BeanUtils.copyProperties(nsSossNotice, vo);
			vo.setDescription(nsSossNotice.getContent());
		}
	    return vo;
	}
	
	/**
	 * 编辑公告详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(NoticeVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossNotice nsSossNotice = new NsSossNotice();
		BeanUtils.copyProperties(vo, nsSossNotice);
		nsSossNotice.setContent(vo.getDescription());
		int countnsSossNotice = nsSossNoticeMapper.updateById(nsSossNotice);
		if(countnsSossNotice == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 新增公告
	 * @return boolean 新增成功与否
	 */
	public boolean add(NoticeVo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		NsSossNotice nsSossNotice = new NsSossNotice();
		BeanUtils.copyProperties(vo, nsSossNotice);
		int countnsSossNotice = nsSossNoticeMapper.insert(nsSossNotice);
		if(countnsSossNotice == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键删除公告
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		int countnsSossNotice = nsSossNoticeMapper.deleteById(id);
		if(countnsSossNotice == 0 ){
			return false;
		}
	    return true;
	}
	
	/**
	 * 根据主键批量删除公告
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		int countnsSossNotice = nsSossNoticeMapper.deleteBatch(ids);
		if(countnsSossNotice == 0 ){
			return false;
		}
	    return true;
	}
}
