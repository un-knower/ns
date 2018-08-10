package com.newsee.soss.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newsee.common.rest.RestResult;
import com.newsee.soss.vo.JepfSyncVo;

@FeignClient(value="dev-platform-server")
public interface IJepfSyncRemoteService {
	@RequestMapping(value="/devplatform/sync-original", method = RequestMethod.POST)
	 RestResult<Boolean> syncOriginal(@RequestBody JepfSyncVo syncVo);

}
