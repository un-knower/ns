package com.newsee.owner.dao;


import com.newsee.owner.entity.WyglChargeCustomerChargeDetail;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface WyglChargeCustomerChargeDetailMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WyglChargeCustomerChargeDetail record);

    int insertSelective(WyglChargeCustomerChargeDetail record);

    WyglChargeCustomerChargeDetail selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(WyglChargeCustomerChargeDetail record);

    int updateByPrimaryKey(WyglChargeCustomerChargeDetail record);

    List<WyglChargeCustomerChargeDetail> loadList(Map<String, Object> params);

    int updateByCustomerId(@Param("customerId") Long customerId);
}