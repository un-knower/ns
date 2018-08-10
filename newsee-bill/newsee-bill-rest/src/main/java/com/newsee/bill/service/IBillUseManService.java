/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.service;

import java.util.List;

import java.util.Map;


import com.github.pagehelper.PageInfo;
import com.newsee.bill.entity.NsbillBillUsed;
import com.newsee.bill.vo.BillUseManCheckVo;
import com.newsee.bill.vo.BillUseManSearchVo;
import com.newsee.bill.vo.BillUseManThird;
import com.newsee.common.vo.SearchVo;
import com.newsee.bill.entity.NsbillBillUsed;
import com.newsee.bill.vo.BillUseManVo;

public interface IBillUseManService {

    /**
     * 获取票据使用管理列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    PageInfo<NsbillBillUsed> listPage(SearchVo searchVo);


	List<NsbillBillUsed> listPageAll(SearchVo searchVo);
	
	List<NsbillBillUsed> selectByIds(List<Long> ids);

	Integer enableBillUseMan(Map<String, Object> map);


    /**
     * 获取票据使用管理详情
     *
     * @param id 主键id
     * @return
     */
	NsbillBillUsed detail(Long id);

    /**
     * 编辑票据使用管理详情
     *
     * @return boolean 编辑成功与否
     */
    boolean edit(BillUseManVo vo);

    /**
     * 新增票据使用管理
     *
     * @return boolean 新增成功与否
     */
    boolean add(BillUseManVo vo);

    /**
     * 根据主键删除票据使用管理
     *
     * @param id 主键id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据主键批量删除票据使用管理
     *
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Long> ids);

    /**
     * 使用票据
     *
     * @param billUseManThird
     * @return
     */
    boolean usedBillUseMan(BillUseManThird billUseManThird);

    /**
     * 票据销号/反销号（核销，未核销）
     * @param map
     * @return
     */
    Integer checkBillUseManBatch(Map<String,Object> map);

    /**
     * 票据批量销号/反销号查询
     * @param billUseManSearchVo
     */
    List<BillUseManCheckVo> listCheckBillUseManBatch(BillUseManSearchVo billUseManSearchVo);


	List<NsbillBillUsed> listbillCode(String billCode, Long enterpriseId, Long organizationId);
}
