package com.newsee.soss.service.remote;


import java.util.List;
import java.util.Map;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.common.entity.AppUser;
import com.newsee.common.entity.NsSystemUser;
import com.newsee.common.rest.RestResult;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsSystemAreaVo;
import com.newsee.system.vo.NsSystemSuperAdmin;
import com.newsee.system.vo.NsSystemUserVo;

@FeignClient(value="system-server")
public interface ISystemRemoteService {
    
    @RequestMapping(value="/field/list-field", method=RequestMethod.POST)
    public RestResult<Map<String, Object>> listField(@RequestBody LoginCommonDataVo commonVo);
    
    @RequestMapping(value = "/column/list-column-for-remote", method = RequestMethod.POST)
    public RestResult<Map<String, Object>> listColumnForRemote(@RequestBody NsCoreResourcecolumnVo nsCoreResourcecolumnVo);
    
    @RequestMapping(value = "/dictionary/getDictionary", method = RequestMethod.POST)
    public RestResult<NsCoreDictionaryVo> getDictionary( @RequestBody NsCoreDictionary dictionary);
    
    @RequestMapping(value = "/user/detail-user-remote", method = RequestMethod.GET)
    public NsSystemUser detailUserRemote(@RequestParam(value="userId") Long userId);
    
    @RequestMapping(value = "/area/getArea", method = RequestMethod.GET)
    public NsSystemArea getArea(@RequestParam(name="areaCode") String areaCode);
    
    @RequestMapping(value = "/area/getAreas", method = RequestMethod.GET)
    public List<NsSystemArea> getAreaList(@RequestParam(name="areaCodeList") String areaCodeList);
    
    @RequestMapping(value = "/area/area-funcinfo", method = RequestMethod.GET)
    public RestResult<List<NsSystemAreaVo>> areaFuncinfo(@RequestParam(name="areaLevel", required=false) String areaLevel, @RequestParam(name="areaCode", required=false) String areaCode);
    
    /**组织详情*/
    @RequestMapping(value = "/organization/detailOrganization", method = RequestMethod.GET)
    public RestResult<Map<String, Object>> detailOrganization(@RequestParam(value="organizationId") Long organizationId);
    
    /**添加企业注册人*/
    @RequestMapping(value = "/user/add-registerUser", method = RequestMethod.GET)
    public RestResult<Long> addRegisterUser(@RequestParam(name="userName", required=false) String userName, 
    		@RequestParam(name="phone", required=false) String phone);
    
    /**更新企业注册人信息*/
    @RequestMapping(value = "/user/update-registerUser", method = RequestMethod.POST)
    public RestResult<Long[]> updateRegisterUser(@RequestBody AppUser user);
    
    /**获取企业员工数*/
    @RequestMapping(value = "/user/user-count", method = RequestMethod.POST)
    public RestResult<List<NsSystemUser>> getEnterpriseUserCount(@RequestParam(name="userListJson", required=false) String userListJson);
    
    /**获取员工信息*/
    @RequestMapping(value = "/user/get-userInfo", method = RequestMethod.GET)
    public  List<NsSystemUser> getUserInfo(@RequestParam(value="userId", required=false) Long userId, @RequestParam(value="userPhone", required=false) String userPhone);
    
    /**创建超级管理员*/
    @RequestMapping(value = "/role/create-super-admin", method = RequestMethod.POST)
    public RestResult<Boolean> createSuperAdmin(@RequestBody NsSystemSuperAdmin superAdmin);
    /**回滚删除registerUser*/
    @RequestMapping(value="/user/rollback-registerUser",method = RequestMethod.GET)
	public  RestResult<Boolean> deleteRegisterUser(@RequestParam(value="userId") Long userId, @RequestParam(value="groupId") Long groupId,@RequestParam(value="organizationId") Long organizationId);
    
}
