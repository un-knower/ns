package com.newsee.charge.service.remote.hystrix;

import java.util.ArrayList;
import java.util.List;

import com.newsee.owner.entity.OwnerHouseHouseInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import com.newsee.common.rest.RestResult;
import com.newsee.owner.entity.HouseListEntity;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.owner.vo.HouseViewVo;
import com.newsee.charge.service.remote.IOwnerRemoteService;

@Component
public class OwnerRemoteServiceHystrix implements IOwnerRemoteService {

    private static final Logger logger = LoggerFactory.getLogger(OwnerRemoteServiceHystrix.class);

    public RestResult<List<HouseListEntity>> listAllLeafNode(Long houseId) {
        logger.equals("△△△△△△△△△△    远程SystemJepfRemoteService.syncAll失败  △△△△△△△△△△  ");
        List<HouseListEntity> list = new ArrayList<HouseListEntity>();
        return new RestResult<>(list);
    }

    @Override
    public RestResult<List<OwnerHouseBaseInfo>> listAllChildNode(Long houseId, List<String> param) {
        logger.equals("△△△△△△△△△△    远程SystemJepfRemoteService.syncAll失败  △△△△△△△△△△  ");
        List<OwnerHouseBaseInfo> list = new ArrayList<OwnerHouseBaseInfo>();
        return new RestResult<>(list);
    }

    @Override
    public RestResult<List<CustomerVo>> listAllCustomer(Long houseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<CustomerVo> getCustomer(Long houseId, String ownerProperty, String type) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<List<CustomerVo>> listCustomerForSearch(String customerName, Integer isDeveloper, Long ownerId) {
        return null;
    }

    @Override
    public RestResult<OwnerHouseBaseInfo> getHouseInfo(Long houseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<HouseViewVo> detailHouseView(Long houseId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public RestResult<List<OwnerHouseBaseInfo>> searchHouseInfo(String precinctName, String houseName) {
        return null;
    }

    @Override
    public RestResult<Long> addCustomer(CustomerVo customerVo) {
        return null;
    }

    @Override
    public RestResult<OwnerHouseHouseInfo> getHouseHouseInfo(Long houseId) {
        return null;
    }

    @Override
    public RestResult<OwnerHouseBaseInfo> searchHouseInfoByFullName(Long enterpriseId, Long organizationId, String fullName) {
        return null;
    }
}
