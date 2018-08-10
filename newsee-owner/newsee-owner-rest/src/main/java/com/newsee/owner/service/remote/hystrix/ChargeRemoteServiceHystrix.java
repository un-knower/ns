package com.newsee.owner.service.remote.hystrix;

import com.newsee.common.rest.RestResult;
import com.newsee.owner.service.remote.IChargeRemoteService;
import org.springframework.stereotype.Component;

@Component
public class ChargeRemoteServiceHystrix implements IChargeRemoteService {
    @Override
    public RestResult<Boolean> deleteGoodsTaxRate(Long chargeItemId, Long houseId) {
        return null;
    }

    @Override
    public RestResult<Boolean> updatePrecinctName(Long houseId, String houseName) {
        return null;
    }
}
