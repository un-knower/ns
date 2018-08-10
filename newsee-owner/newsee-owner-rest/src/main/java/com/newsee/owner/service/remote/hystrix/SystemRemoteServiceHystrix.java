package com.newsee.owner.service.remote.hystrix;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
import com.newsee.common.rest.RestResult;
import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.owner.service.remote.ISystemRemoteService;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.entity.NsSystemRoleHouse;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsSystemAreaVo;
import com.newsee.system.vo.NsSystemOrganizationVo;
import com.newsee.system.vo.NsSystemUserVo;

@Component
public class SystemRemoteServiceHystrix implements ISystemRemoteService {

    public RestResult<Map<String, Object>> listField(LoginCommonDataVo commonVo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<Map<String, Object>> listColumnForRemote(NsCoreResourcecolumnVo nsCoreResourcecolumnVo) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<NsCoreDictionaryVo> getDictionary(NsCoreDictionary dictionary) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<NsSystemUserVo> detailUser(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NsSystemArea getArea(String areaCode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<List<NsSystemAreaVo>> areaFuncinfo(String areaLevel, String areaCode) {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public RestResult<Map<String, Object>> detailOrganization(Long organizationId) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public RestResult<List<NsSystemRoleHouse>> getDataPermission(Long userId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<List<NsSystemOrganizationVo>> listAllOrganization(Long enterpriseId, Long organizationId) {
        // TODO Auto-generated method stub
        return null;
    }


}
