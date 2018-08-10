/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.bill.dao;

import com.newsee.bill.entity.NsbillBillUsed;
import com.newsee.bill.vo.BillUseManCheckVo;
import com.newsee.bill.vo.BillUseManSearchVo;
import com.newsee.common.vo.SearchVo;

import java.util.List;
import java.util.Map;

public interface NsbillBillUsedMapper {
  
    NsbillBillUsed selectById(Long id);
    
    int insert(NsbillBillUsed nsbillBillUsed);
    
    int insertBatch(List<NsbillBillUsed> nsbillBillUsedList);
    
    int updateById(NsbillBillUsed nsbillBillUsed);
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    List<NsbillBillUsed> listPage(SearchVo vo);

	Integer enableBill(Map<String, Object> map);

    int checkBillUseMan(NsbillBillUsed nsbillBillUsed);

    List<NsbillBillUsed> listCheckBillUseManBatch(BillUseManSearchVo billUseManSearchVo);

    /**
     * 销号统计查询
     */
    List<BillUseManCheckVo> listCheckBillUseManBatchGroupBy(BillUseManSearchVo billUseManSearchVo);

	List<NsbillBillUsed> selectByIds(List<Long> ids);

	List<NsbillBillUsed> selectByCode(BillUseManSearchVo vo);
}