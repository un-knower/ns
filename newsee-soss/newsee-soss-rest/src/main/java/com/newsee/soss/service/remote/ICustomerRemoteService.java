package com.newsee.soss.service.remote;

import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value="owner-server")
public interface ICustomerRemoteService {
	
	@RequestMapping(value = "/owner-rest/house/add-remoteHouse", method = RequestMethod.GET)
    public Map<String, Long> addHousePrecinctRemote(@RequestParam(name="enterpriseId") Long enterpriseId, @RequestParam(name="nameJson") String nameJson
    		,@RequestParam(name="houseType") String houseType);
	
}
