package com.newsee.owner.dao;


import com.newsee.owner.entity.BillBillUsedDetail;

public interface BillBillUsedDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillBillUsedDetail record);

    int insertSelective(BillBillUsedDetail record);

    BillBillUsedDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillBillUsedDetail record);

    int updateByPrimaryKey(BillBillUsedDetail record);
}