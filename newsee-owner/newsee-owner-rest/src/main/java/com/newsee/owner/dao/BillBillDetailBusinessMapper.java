package com.newsee.owner.dao;


import com.newsee.owner.entity.BillBillDetailBusiness;

public interface BillBillDetailBusinessMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillBillDetailBusiness record);

    int insertSelective(BillBillDetailBusiness record);

    BillBillDetailBusiness selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillBillDetailBusiness record);

    int updateByPrimaryKey(BillBillDetailBusiness record);
}