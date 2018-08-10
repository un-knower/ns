package com.newsee.owner.service.remote;

import com.newsee.common.rest.RestResult;
import com.newsee.owner.service.remote.hystrix.ChargeRemoteServiceHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "charge-server", fallback = ChargeRemoteServiceHystrix.class)
public interface IChargeRemoteService {

    /**
     * 删除税率
     *
     * @param chargeItemId
     * @param houseId
     * @return
     */
    @RequestMapping(value = "/goodsTaxRate/delete-goodsTaxRate", method = RequestMethod.POST)
    RestResult<Boolean> deleteGoodsTaxRate(@RequestParam(value = "chargeItemId", required = false) Long chargeItemId,
                                           @RequestParam(value = "houseId", required = false) Long houseId);


    /**
     * 更新税率的项目名称
     * @param houseId
     * @param houseName
     * @return
     */
    @RequestMapping(value = "/goodsTaxRate/update-precinctName", method = RequestMethod.POST)
    RestResult<Boolean> updatePrecinctName(@RequestParam(value = "houseId") Long houseId,
                                                  @RequestParam(value = "houseName") String houseName);
}
