package com.newsee.charge.dao;

import com.newsee.charge.entity.ChargeCustomerChargeDetailLog;
import com.newsee.common.vo.SearchVo;

import java.util.List;
import java.util.Map;

/**
 * @author mu.jie
 * @Date 2018/6/12 10:45
 */
public interface ChargeCustomerChargeDetailLogMapper {

    ChargeCustomerChargeDetailLog selectById(Long id);

    int insert(ChargeCustomerChargeDetailLog chargeCustomerChargeDetailLog);

    int insertBatch(List<ChargeCustomerChargeDetailLog> chargeCustomerChargeDetailLogList);

    int updateById(ChargeCustomerChargeDetailLog chargeCustomerChargeDetailLog);

    int deleteById(Long id);

    int deleteBatch(List<Long> ids);

    List<ChargeCustomerChargeDetailLog> listPage(SearchVo vo);

    /**
     * 日志列表
     * @param map
     * @return
     */
    List<ChargeCustomerChargeDetailLog> listPaymentLog(Map<String,Object> map);
}
