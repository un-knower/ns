package com.newsee.bill.service.remote;

import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.rest.RestResult;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsSystemAreaVo;
import com.newsee.system.vo.NsSystemUserVo;

@FeignClient(value="system-server")
public interface ISystemRemoteService {
    
    @RequestMapping(value="/field/list-field", method=RequestMethod.POST)
    public RestResult<Map<String, Object>> listField(@RequestBody LoginCommonDataVo commonVo);
    
    @RequestMapping(value = "/column/list-column-for-remote", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> listColumnForRemote(@RequestBody NsCoreResourcecolumnVo nsCoreResourcecolumnVo);
    
    @RequestMapping(value = "/dictionary/getDictionary", method = RequestMethod.POST)
    public RestResult<NsCoreDictionaryVo> getDictionary( @RequestBody NsCoreDictionary dictionary);
    
    @RequestMapping(value = "/user/detailUser", method = RequestMethod.POST)
    public RestResult<NsSystemUserVo> detailUser(@RequestParam(value="userId") Long userId);
    
    @RequestMapping(value = "/area/getArea", method = RequestMethod.GET)
    public NsSystemArea getArea(@RequestParam(name="areaCode") String areaCode);
    
    
    @RequestMapping(value = "/area/area-funcinfo", method = RequestMethod.GET)
    public RestResult<List<NsSystemAreaVo>> areaFuncinfo(@RequestParam(name="areaLevel", required=false) String areaLevel, @RequestParam(name="areaCode", required=false) String areaCode);
    
    /**组织详情*/
    @RequestMapping(value = "/organization/detailOrganization", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailOrganization(@RequestParam(value="organizationId") Long organizationId);
    
}
