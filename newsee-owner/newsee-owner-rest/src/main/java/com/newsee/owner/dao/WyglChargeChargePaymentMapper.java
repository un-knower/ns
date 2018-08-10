package com.newsee.owner.dao;




import com.newsee.owner.entity.WyglChargeChargePayment;

import java.util.List;

public interface WyglChargeChargePaymentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WyglChargeChargePayment record);

    int insertSelective(WyglChargeChargePayment record);

    WyglChargeChargePayment selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WyglChargeChargePayment record);

    int updateByPrimaryKey(WyglChargeChargePayment record);

    void insertList(List<WyglChargeChargePayment> list);

}