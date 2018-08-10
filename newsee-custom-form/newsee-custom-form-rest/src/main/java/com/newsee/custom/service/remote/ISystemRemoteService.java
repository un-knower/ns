package com.newsee.custom.service.remote;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newsee.common.rest.RestResult;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreFuncinfoVo;

@FeignClient(value = "system-server")
public interface ISystemRemoteService {
	
	@RequestMapping(value = "/system/dictionary/getDictionary", method = RequestMethod.POST)
    public RestResult<NsCoreDictionaryVo> getDictionary(@RequestBody NsCoreDictionary dictionary);
	
	@RequestMapping(value = "/menu/detail-funcinfo", method = RequestMethod.GET)
    public  RestResult<NsCoreFuncinfoVo> detailFuncinfo(@RequestHeader("functionId") String functionId);
}
