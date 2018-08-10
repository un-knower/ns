package com.newsee.owner.service;


import com.github.pagehelper.PageInfo;
import com.newsee.owner.entity.WyglChargeCustomerChargeDetail;
import com.newsee.owner.vo.WyglChargeChargePaymentVO;
import com.newsee.owner.vo.WyglChargeCustomerChargeDetailVO;

/**
 * Created by niyang on 2017/8/24.
 */
public interface IPressureTestService {

    public PageInfo<WyglChargeCustomerChargeDetail> listPage(WyglChargeCustomerChargeDetailVO wyglChargeCustomerChargeDetailVO);

    public Boolean payTheFees(WyglChargeChargePaymentVO vo);


}
