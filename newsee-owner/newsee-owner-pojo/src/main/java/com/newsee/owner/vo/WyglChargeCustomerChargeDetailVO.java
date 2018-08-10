package com.newsee.owner.vo;

import java.io.Serializable;

/**
 * Created by niyang on 2017/8/24.
 */
public class WyglChargeCustomerChargeDetailVO implements Serializable {

    private Long customerId;
    private Boolean isPaid;
    private Integer pageSize;
    private Integer pageIndex;

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Boolean getPaid() {
        return isPaid;
    }

    public void setPaid(Boolean paid) {
        isPaid = paid;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }
}
