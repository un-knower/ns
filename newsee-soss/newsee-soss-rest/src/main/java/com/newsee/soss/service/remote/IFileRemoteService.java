package com.newsee.soss.service.remote;

import java.util.List;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.rest.RestResult;
import com.newsee.common.vo.FileVo;
 

@FeignClient(value="fastdfs-server")
public interface IFileRemoteService {
	
	@RequestMapping(value = "/fastdfs/save-file", method= RequestMethod.POST)
    public RestResult<?> saveFile(@RequestBody FileVo fileVo);
	
	@RequestMapping(value = "/fastdfs/findFileByCode", method= RequestMethod.GET)
	public List<FileVo> findFileList(@RequestParam(name="fileCode",required=false) String fileCode, @RequestParam(name="enterpriseId") Long enterpriseId);
	
}
