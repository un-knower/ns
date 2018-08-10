package com.newsee.owner.dao;


import com.newsee.owner.entity.BillBillUsed;
import com.newsee.owner.entity.BillBillUsedWithBLOBs;

public interface BillBillUsedMapper {
    int deleteByPrimaryKey(Long id);

    int insert(BillBillUsedWithBLOBs record);

    int insertSelective(BillBillUsedWithBLOBs record);

    BillBillUsedWithBLOBs selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(BillBillUsedWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(BillBillUsedWithBLOBs record);

    int updateByPrimaryKey(BillBillUsed record);
}