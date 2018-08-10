package com.newsee.system.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.entity.NsSossEnterprise;

import io.swagger.annotations.ApiParam;

@FeignClient(value="soss-server")
public interface ISossRemoteService {
    
	@RequestMapping(value = "/enterprise/get-enterpriseInfo", method = RequestMethod.GET)
	public NsSossEnterprise getEnterpriseInfo(@ApiParam(value = "企业ID") @RequestParam("enterpriseId") Long enterpriseId);
}
