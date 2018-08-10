package com.newsee.log.vo;

import java.io.Serializable;
import java.util.List;

import com.newsee.log.entity.RequestLogEntity;

public class RequestLogVo implements Serializable{

    /**
     * 
     */
    private static final long serialVersionUID = 7540108878736669367L;

    private Long total;
    
    private List<RequestLogEntity> requestLogList;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<RequestLogEntity> getRequestLogList() {
        return requestLogList;
    }

    public void setRequestLogList(List<RequestLogEntity> requestLogList) {
        this.requestLogList = requestLogList;
    }

}
