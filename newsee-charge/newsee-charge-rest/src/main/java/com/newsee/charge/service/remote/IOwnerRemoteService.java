package com.newsee.charge.service.remote;

import java.util.List;

import com.newsee.owner.entity.OwnerHouseHouseInfo;
import com.newsee.owner.vo.HouseViewVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.newsee.charge.service.remote.hystrix.OwnerRemoteServiceHystrix;
import com.newsee.common.rest.RestResult;
import com.newsee.owner.entity.HouseListEntity;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.vo.CustomerVo;

import io.swagger.annotations.ApiParam;

@FeignClient(value = "owner-server", fallback = OwnerRemoteServiceHystrix.class)
public interface IOwnerRemoteService {

    @RequestMapping(value = "/owner-rest/list-all-leaf-node", method = RequestMethod.GET)
    public RestResult<List<HouseListEntity>> listAllLeafNode(@RequestParam(name = "houseId") Long houseId);

    @RequestMapping(value = "/owner-rest/list-all-child-node", method = RequestMethod.GET)
    public RestResult<List<OwnerHouseBaseInfo>> listAllChildNode(@RequestParam("houseId") Long houseId, @RequestParam("houseTypes") List<String> param);


    @RequestMapping(value = "/customer/list-all-customer", method = RequestMethod.GET)
    public RestResult<List<CustomerVo>> listAllCustomer(@ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId);

    @RequestMapping(value = "/customer/get-customer", method = RequestMethod.GET)
    public RestResult<CustomerVo> getCustomer(
            @ApiParam(value = "房产ID") @RequestParam("houseId") Long houseId,
            @ApiParam(value = "客户类型:0业主 1租户 2住户 3开发商") @RequestParam("ownerProperty") String ownerProperty,
            @ApiParam(value = "查询类型,0:租户->业主->开发商,1:业主->租户") @RequestParam(value = "type", required = false) String type);

    @RequestMapping(value = "/customer/list-customer-search", method = RequestMethod.GET)
    public RestResult<List<CustomerVo>> listCustomerForSearch(@RequestParam("customerName") String customerName, @ApiParam("0非开发商客户 1开发商 2所有客户") @RequestParam("isDeveloper") Integer isDeveloper,
                                                              @ApiParam("需要屏蔽的客户ID") @RequestParam(name = "ownerId", required = false) Long ownerId);

    @RequestMapping(value = "/owner-rest/info", method = RequestMethod.GET)
    public RestResult<OwnerHouseBaseInfo> getHouseInfo(@RequestParam("houseId") Long houseId);

    @RequestMapping(value = "/owner-rest/house/house-view", method = RequestMethod.GET)
    public RestResult<HouseViewVo> detailHouseView(@RequestParam("houseId") Long houseId);

    @RequestMapping(value = "/owner-rest/search-info", method = RequestMethod.GET)
    public RestResult<List<OwnerHouseBaseInfo>> searchHouseInfo(@RequestParam("precinctName") String precinctName,
                                                                @RequestParam("houseName") String houseName);

    @RequestMapping(value = "/customer/add-customer", method = RequestMethod.POST)
    public RestResult<Long> addCustomer(@RequestBody CustomerVo customerVo);

    @RequestMapping(value = "/owner-rest/house-info", method = RequestMethod.GET)
    public RestResult<OwnerHouseHouseInfo> getHouseHouseInfo(@ApiParam(value = "房产Id") @RequestParam("houseId") Long houseId);

    @RequestMapping(value = "/search-fullName", method = RequestMethod.GET)
    public RestResult<OwnerHouseBaseInfo> searchHouseInfoByFullName(@ApiParam(value = "租户id") @RequestParam(value = "enterpriseId", required = false) Long enterpriseId,
                                                                    @ApiParam(value = "组织id") @RequestParam(value = "organizationId", required = false) Long organizationId,
                                                                    @ApiParam(value = "房产全称") @RequestParam(value = "fullName") String fullName);


}
