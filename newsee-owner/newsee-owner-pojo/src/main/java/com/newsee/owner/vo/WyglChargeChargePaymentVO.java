package com.newsee.owner.vo;



import com.newsee.owner.entity.WyglChargeCustomerChargeDetail;

import java.io.Serializable;
import java.util.List;

/**
 * Created by niyang on 2017/8/24.
 */
public class WyglChargeChargePaymentVO implements Serializable {

    private Long customerId;
    private List<WyglChargeCustomerChargeDetail> detailList;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public List<WyglChargeCustomerChargeDetail> getDetailList() {
        return detailList;
    }

    public void setDetailList(List<WyglChargeCustomerChargeDetail> detailList) {
        this.detailList = detailList;
    }
}
