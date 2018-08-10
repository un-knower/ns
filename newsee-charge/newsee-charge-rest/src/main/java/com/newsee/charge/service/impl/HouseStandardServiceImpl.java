package com.newsee.charge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.charge.dao.*;
import com.newsee.charge.enmus.HouseStandrdAduitStatus;
import com.newsee.charge.entity.*;
import com.newsee.charge.service.IHouseStandardService;
import com.newsee.charge.service.remote.IOwnerRemoteService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.AppStandardVo;
import com.newsee.charge.vo.ChargeStaticData;
import com.newsee.charge.vo.HouseStandardAddVo;
import com.newsee.charge.vo.HouseStandardVo;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.DateUtils;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.entity.OwnerHouseHouseInfo;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.owner.vo.HouseViewVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
public class HouseStandardServiceImpl implements IHouseStandardService {

    @Autowired
    private ChargeHouseChargeStandardMapper chargeHousechargestandardMapper;

    @Autowired
    private ChargeHouseChargeStandardCustomerMapper chargeHousechargestandardcustomerMapper;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    private IOwnerRemoteService ownerRemoteService;

    @Autowired
    private ChargeChargeItemMapper chargeChargeItemMapper;

    @Autowired
    private ChargeChargeStandardMapper chargeChargeStandardMapper;

    @Autowired
    private ChargeChargeStandardLadderMapper chargeChargeStandardLadderMapper;

    /**
     * 获取房产收费标准列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    @ReadDataSource
    public PageInfo<ChargeHouseChargeStandard> listPage(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        List<OwnerHouseBaseInfo> houseBaseInfos = new ArrayList<>();
        if (!Objects.isNull(searchVo.getHouseId())) {
            List<String> type = new ArrayList<String>(Arrays.asList("6", "7", "8", "9"));
            //查询项目下所有的房产
            List<Long> houseIdList = new ArrayList<>();
            houseBaseInfos = ownerRemoteService.listAllChildNode(searchVo.getHouseId(), type).getResultData();
            OwnerHouseBaseInfo currentHouseBaseInfo = ownerRemoteService.getHouseInfo(searchVo.getHouseId()).getResultData();
            if (currentHouseBaseInfo != null) houseBaseInfos.add(currentHouseBaseInfo);
            if (!Objects.isNull(houseBaseInfos)) {
                List<Long> houseIds = houseBaseInfos.stream().map(t -> t.getHouseId()).collect(Collectors.toList());
                houseIdList.addAll(houseIds);
                houseIdList.add(searchVo.getHouseId());
            }
            searchVo.setHouseIds(houseIdList);
        }
        List<ChargeChargeStandard> chargeChargeStandards = chargeChargeStandardMapper.listPageAll(searchVo);
        List<Long> standards = new ArrayList<Long>();
        if (!Objects.isNull(chargeChargeStandards)) {
            for (ChargeChargeStandard chargeChargeStandard : chargeChargeStandards) {
                standards.add(chargeChargeStandard.getId());
            }
        }
        searchVo.setIds(standards.size() > 0 ? standards : Arrays.asList(-1L));
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeHouseChargeStandard> list = chargeHousechargestandardMapper.listPage(searchVo);
        //查询客户信息
        if (!Objects.isNull(list)) {
            for (ChargeHouseChargeStandard chargeHouseChargeStandard : list) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("enterpriseId", chargeHouseChargeStandard.getEnterpriseId());
                map.put("organizationId", chargeHouseChargeStandard.getOrganizationId());
                map.put("houseChargeId", chargeHouseChargeStandard.getId());
                map.put("houseId", chargeHouseChargeStandard.getHouseId());
                RestResult<OwnerHouseBaseInfo> restResult = ownerRemoteService.getHouseInfo(chargeHouseChargeStandard.getPreinctId());
                if (!Objects.isNull(restResult) && !Objects.isNull(restResult.getResultData())) {
                    OwnerHouseBaseInfo houseBaseInfo = restResult.getResultData();
                    chargeHouseChargeStandard.setPreinctName(houseBaseInfo.getHouseName());
                }
                List<ChargeHouseChargeStandardCustomer> chargeHouseChargeStandardCustomers = chargeHousechargestandardcustomerMapper.selectByHouseChargeId(map);
                String customers = "";//收费客户
                String chargeCustomer = chargeHouseChargeStandard.getCustorName();//客户名称
                if (!Objects.isNull(chargeHouseChargeStandardCustomers)) {
                    for (ChargeHouseChargeStandardCustomer chargeHouseChargeStandardCustomer : chargeHouseChargeStandardCustomers) {
//                        if (chargeHouseChargeStandardCustomer.getOwnerId().equals(chargeHouseChargeStandard.getChargeId())) {
//                            chargeCustomer = chargeHouseChargeStandardCustomer.getOwnerName();
//                        }
                        customers += ("".equals(customers) ? chargeHouseChargeStandardCustomer.getOwnerName() : ("," + chargeHouseChargeStandardCustomer.getOwnerName()));
                    }
                }
                chargeHouseChargeStandard.setChargeCustor(customers);
                chargeHouseChargeStandard.setCustorName(chargeCustomer);
                for (OwnerHouseBaseInfo houseBaseInfo : houseBaseInfos) {
                    if (houseBaseInfo.getHouseId().equals(chargeHouseChargeStandard.getHouseId())) {
                        OwnerHouseHouseInfo ownerHouseHouseInfo = ownerRemoteService.getHouseHouseInfo(houseBaseInfo.getHouseId()).getResultData();
                        if (!Objects.isNull(ownerHouseHouseInfo)) {
                            chargeHouseChargeStandard.setHouseName(ownerHouseHouseInfo.getRoomShortName());
                        }
                    }
                }

                //设置收费标准名称
                ChargeChargeStandard standard = chargeChargeStandardMapper.selectById(chargeHouseChargeStandard.getStandardId());
                if (!Objects.isNull(standard)) {
                    chargeHouseChargeStandard.setStandardName(standard.getStandardName());
                }
            }
        }
        PageInfo<ChargeHouseChargeStandard> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @ReadDataSource
    public List<ChargeHouseChargeStandard> listPageALL(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        if (!Objects.isNull(searchVo.getHouseId())) {
            List<String> type = new ArrayList<String>(Arrays.asList("6", "7", "8", "9"));
            //查询项目下所有的房产
            //type.add("6");
            List<OwnerHouseBaseInfo> houseBaseInfos = ownerRemoteService.listAllChildNode(searchVo.getHouseId(), type).getResultData();
            List<Long> houseIds = houseBaseInfos.stream().map(t -> t.getHouseId()).collect(Collectors.toList());
            searchVo.setHouseIds(houseIds);
        }
        List<ChargeHouseChargeStandard> list = chargeHousechargestandardMapper.listPage(searchVo);
        //查询客户信息
        if (!Objects.isNull(list)) {
            for (ChargeHouseChargeStandard chargeHouseChargeStandard : list) {
                Map<String, Object> map = Maps.newHashMap();
                map.put("enterpriseId", chargeHouseChargeStandard.getEnterpriseId());
                map.put("organizationId", chargeHouseChargeStandard.getOrganizationId());
                map.put("houseChargeId", chargeHouseChargeStandard.getId());
                map.put("houseId", chargeHouseChargeStandard.getHouseId());
                List<ChargeHouseChargeStandardCustomer> chargeHouseChargeStandardCustomers = chargeHousechargestandardcustomerMapper.selectByHouseChargeId(map);
                String customers = "";
                if (!Objects.isNull(chargeHouseChargeStandardCustomers)) {
                    for (ChargeHouseChargeStandardCustomer chargeHouseChargeStandardCustomer : chargeHouseChargeStandardCustomers) {
                        customers += ("".equals(customers) ? chargeHouseChargeStandardCustomer.getOwnerName() : ("," + chargeHouseChargeStandardCustomer.getOwnerName()));
                    }
                }
                chargeHouseChargeStandard.setChargeCustor(customers);
            }
        }
        return list;
    }

    /**
     * 获取房产收费标准详情
     */
    @ReadDataSource
    public Map<String, Object> detail(Map<String, Object> param) {
        Map<String, Object> result = Maps.newHashMap();
        if (Objects.isNull(param)) {
            return null;
        }
        List<ChargeHouseChargeStandardCustomer> chargeHousechargestandardcustomer = chargeHousechargestandardcustomerMapper.selectByHouseChargeId(param);
        result.put("customers", chargeHousechargestandardcustomer);

        List<CustomerVo> customers = ownerRemoteService.listAllCustomer((Long) param.get("houseId")).getResultData();
        Map<String, Object> relativeCustomerMap = new HashMap<>();
        List<Map<String, Object>> relativeCustomerList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(customers)) {
            for (CustomerVo customer : customers) {
                Map<String, Object> map = new HashMap<>();
                map.put("label", customer.getOwnerName());
                map.put("value", customer.getOwnerId());
                relativeCustomerList.add(map);
            }
        }
        relativeCustomerMap.put("options", relativeCustomerList);
        Map<String, Object> selectValue = new HashMap();
        selectValue.put(ChargeStaticData.VALUE_TYPE, "");
        selectValue.put(ChargeStaticData.UNIT_TYPE, "");
        relativeCustomerMap.put(ChargeStaticData.PICKED_TYPE, selectValue);
//        result.put("relativeCustomers", relativeCustomerMap);
        for (ChargeHouseChargeStandardCustomer chargeHouseChargeStandardCustomer : chargeHousechargestandardcustomer) {
            chargeHouseChargeStandardCustomer.setRelativeCustomerMap(relativeCustomerMap);
        }
        return result;
    }

    /**
     * 编辑房产收费标准详情
     *
     * @return boolean 编辑成功与否
     */
    @WriteDataSource
    public boolean edit(List<ChargeHouseChargeStandardCustomer> vos) {
        if (CollectionUtils.isEmpty(vos)) {
            return false;
        }//houseChargeId
        chargeHousechargestandardcustomerMapper.deleteByHouseChargeId(vos.get(0).getHouseChargeId());
        vos.forEach(vo -> {
            ChargeHouseChargeStandardCustomer customer = new ChargeHouseChargeStandardCustomer();
            BeanUtils.copyProperties(vo, customer);
            chargeHousechargestandardcustomerMapper.insert(customer);
        });
        return true;
    }


    @WriteDataSource
    public boolean editHouse(List<ChargeHouseChargeStandard> vos) {
        if (CollectionUtils.isEmpty(vos)) {
            return false;
        }
        vos.forEach(vo -> {
            ChargeHouseChargeStandard chargeHouseChargeStandard = new ChargeHouseChargeStandard();
            BeanUtils.copyProperties(vo, chargeHouseChargeStandard);
            chargeHousechargestandardMapper.updateById(chargeHouseChargeStandard);
        });
        return true;
    }

    /**
     * 新增房产收费标准
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    public boolean add(List<HouseStandardAddVo> vo, Map<String, Object> map) {

        //根据

        for (HouseStandardAddVo houseStandardAddVo1 : vo) {
            Long precitId = houseStandardAddVo1.getHouseId();
            HouseStandardAddVo houseStandardAddVo = new HouseStandardAddVo();
            BeanUtils.copyProperties(houseStandardAddVo1, houseStandardAddVo);
            //该项目下所有的房间  6.房产 7.车库 8.车位 9.公共区域'
            //查询项目下所有的房产
            List<OwnerHouseBaseInfo> houseListEntities = ownerRemoteService.listAllChildNode(houseStandardAddVo.getHouseId(), Arrays.asList("6", "7", "8", "9")).getResultData();
            for (OwnerHouseBaseInfo item : houseListEntities) {
                houseStandardAddVo.setHouseId(item.getHouseId());
                houseStandardAddVo.setPreinctId(precitId);
                houseStandardAddVo.setHouseFullName(item.getHouseFullName());
                ChargeHouseChargeStandard houseStandards = chargeHousechargestandardMapper.findHouseStandard(houseStandardAddVo);
                //设置收费客户信息
                ChargeHouseChargeStandardCustomer customer = new ChargeHouseChargeStandardCustomer();
                customer.setEnterpriseId(houseStandardAddVo.getEnterpriseId());
                customer.setOrganizationId(houseStandardAddVo.getOrganizationId());
                customer.setCreateUserId(houseStandardAddVo.getCreateUserId());
                customer.setCreateTime(houseStandardAddVo1.getCreateTime());
                customer.setCreateUserId(houseStandardAddVo1.getCreateUserId());
                customer.setCreateUserName(houseStandardAddVo1.getCreateUserName());
                customer.setUpdateTime(houseStandardAddVo1.getUpdateTime());
                customer.setUpdateUserId(houseStandardAddVo1.getUpdateUserId());
                customer.setUpdateUserName(houseStandardAddVo1.getUpdateUserName());
                RestResult<CustomerVo> result = null;
                if (map.get(ChargeStaticData.IS_COVER_TYPE) != null && (Boolean) map.get(ChargeStaticData.IS_COVER_TYPE)) {
                    //删除该房间的收费标准
                    chargeHousechargestandardMapper.deleteById(Objects.isNull(houseStandards) ? -1 : houseStandards.getId());
                    //删除该房间的收费客户
                    chargeHousechargestandardcustomerMapper.deleteByHouseChargeId(Objects.isNull(houseStandards) ? -1 : houseStandards.getId());

                    if ("1".equals(houseStandardAddVo.getChargeCustor()) || "2".equals(houseStandardAddVo.getChargeCustor())) {
                        //查询该房间的收费客户
                        result = ownerRemoteService.getCustomer(item.getHouseId(), "1".equals(houseStandardAddVo.getChargeCustor()) ? "0" : "1", "0");
                        if (!Objects.isNull(result) && !Objects.isNull(result.getResultData())) {
                            CustomerVo customerVo = result.getResultData();
                            customer.setOwnerId(customerVo.getOwnerId());
                            customer.setOwnerName(customerVo.getOwnerName());
                            houseStandardAddVo.setChargeId(customerVo.getOwnerId());
                        } else {
                            continue;
                        }
                    } else {
                        //填写开发商的ID
                        //RestResult<List<CustomerVo>>  restResult = ownerRemoteService.searchDevelopersCustomer(item.getHouseId(),String.valueOf(map.get("developer")));
                        customer.setOwnerId(Long.parseLong(String.valueOf(map.get(ChargeStaticData.DEVELOPER_TYPE))));
                        customer.setOwnerName("");
                        houseStandardAddVo.setChargeId(Long.parseLong(String.valueOf(map.get(ChargeStaticData.DEVELOPER_TYPE))));
                    }
                    //客户名称：业户->租户
                    RestResult<CustomerVo> customerName = ownerRemoteService.getCustomer(item.getHouseId(), "1".equals(houseStandardAddVo.getChargeCustor()) ? "0" : "1", "1");
                    if (customerName != null && customerName.getResultData() != null) {
                        houseStandardAddVo.setCustorName(customerName.getResultData().getOwnerName());
                    }
                    ChargeHouseChargeStandard chargeHouseChargeStandard = new ChargeHouseChargeStandard();
                    BeanUtils.copyProperties(houseStandardAddVo, chargeHouseChargeStandard);
                    chargeHouseChargeStandard.setPrice(chargeHouseChargeStandard.getUnit());
                    chargeHousechargestandardMapper.insert(chargeHouseChargeStandard);

                    customer.setHouseChargeId(chargeHouseChargeStandard.getId());
                    customer.setHouseId(chargeHouseChargeStandard.getHouseId());
                    //查询到与房产相关人员就插入
                    if ("0".equals(houseStandardAddVo.getChargeCustor()) || (result != null && !Objects.isNull(result.getResultData()))) {
                        chargeHousechargestandardcustomerMapper.insert(customer);
                    }

                } else {
                    if (Objects.isNull(houseStandards)) {
                        if ("1".equals(houseStandardAddVo.getChargeCustor()) || "2".equals(houseStandardAddVo.getChargeCustor())) {
                            //查询该房间的收费客户
                            result = ownerRemoteService.getCustomer(item.getHouseId(), "1".equals(houseStandardAddVo.getChargeCustor()) ? "0" : "1", "0");
                            if (!Objects.isNull(result) && !Objects.isNull(result.getResultData())) {
                                CustomerVo customerVo = result.getResultData();
                                customer.setOwnerId(customerVo.getOwnerId());
                                customer.setOwnerName(customerVo.getOwnerName());
                                houseStandardAddVo.setChargeId(customerVo.getOwnerId());
                            } else {
                                continue;
                            }
                        } else {
                            //查询开发商的id
                            customer.setOwnerId(Long.parseLong(String.valueOf(map.get(ChargeStaticData.DEVELOPER_TYPE))));
                            customer.setOwnerName("");
                            houseStandardAddVo.setChargeId(Long.parseLong(String.valueOf(map.get(ChargeStaticData.DEVELOPER_TYPE))));
                        }
                        //客户名称：业户->租户
                        RestResult<CustomerVo> customerName = ownerRemoteService.getCustomer(item.getHouseId(), "1".equals(houseStandardAddVo.getChargeCustor()) ? "0" : "1", "1");
                        if (customerName != null && customerName.getResultData() != null) {
                            houseStandardAddVo.setCustorName(customerName.getResultData().getOwnerName());
                        }
                        ChargeHouseChargeStandard chargeHouseChargeStandard = new ChargeHouseChargeStandard();
                        BeanUtils.copyProperties(houseStandardAddVo, chargeHouseChargeStandard);
                        chargeHouseChargeStandard.setPrice(chargeHouseChargeStandard.getUnit());
                        chargeHousechargestandardMapper.insert(chargeHouseChargeStandard);

                        customer.setHouseChargeId(chargeHouseChargeStandard.getId());
                        customer.setHouseId(chargeHouseChargeStandard.getHouseId());
                        if ("0".equals(houseStandardAddVo.getChargeCustor()) || (result != null && !Objects.isNull(result.getResultData()))) {
                            chargeHousechargestandardcustomerMapper.insert(customer);
                        }
                    }
                }


            }
        }
        return true;
    }


    @ReadDataSource
    public Integer checkHouseStandardData(HouseStandardAddVo vo) {
        if (Objects.isNull(vo) ||
                Objects.isNull(vo.getHouseId()) ||
                Objects.isNull(vo.getOrganizationId())) {
            BizException.isNull(vo, "参数错误");
        }
        List<HouseStandardAddVo> addVos = new ArrayList<HouseStandardAddVo>();

        //根据当前项目Id查询已经设置了的收费标准的收费科目
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("preinctId", vo.getHouseId());
        param.put("enterpriseId", vo.getEnterpriseId());
        param.put("organizationId", vo.getOrganizationId());
        List<ChargeChargeStandard> chargeChargeStandards = chargeChargeStandardMapper.findChargeItemNameForPericint(param);
        return Objects.isNull(chargeChargeStandards) ? 0 : chargeChargeStandards.size();
    }

    /**
     * 初始化新增房产收费标准页面数据
     *
     * @return boolean 新增成功与否
     */
    @Override
    @ReadDataSource
    public List<HouseStandardAddVo> initHouseStandardData(HouseStandardAddVo vo) {
        if (Objects.isNull(vo) ||
                Objects.isNull(vo.getHouseId()) ||
                Objects.isNull(vo.getOrganizationId())) {
            BizException.isNull2(null, "参数错误");
        }
        Date chargeStartTime = new Date();//计费开始时间
        //查询项目情况,计费开始时间(chargeStartTime)默认为收房日期，如果没有则为当前日期
        HouseViewVo houseViewVo = ownerRemoteService.detailHouseView(vo.getHouseId()).getResultData();
        if (houseViewVo != null && !CollectionUtils.isEmpty(houseViewVo.getTakeStageVoList())) {
            if (isNotBlank(houseViewVo.getTakeStageVoList().get(0).getOperateDate())) {
                //收房日期
                Date operateDate = DateUtils.strToDate(houseViewVo.getTakeStageVoList().get(0).getOperateDate().substring(0, DateUtils.YYYYMMDD_CROSS.length()), DateUtils.YYYYMMDD_CROSS);
                chargeStartTime = operateDate;
            }
        }
        List<HouseStandardAddVo> addVos = new ArrayList<HouseStandardAddVo>();
        //根据当前项目Id查询已经设置了的收费标准的收费科目
        HashMap<String, Object> param = Maps.newHashMap();
        param.put("preinctId", vo.getHouseId());
        param.put("enterpriseId", vo.getEnterpriseId());
        param.put("organizationId", vo.getOrganizationId());
        List<ChargeChargeStandard> chargeChargeStandards = chargeChargeStandardMapper.findChargeItemNameForPericint(param);
        if (CollectionUtils.isEmpty(chargeChargeStandards)) {
            BizException.isNull(chargeChargeStandards, "该房产所属的公司或者子公司还未设置收费标准,请在收费标准页面设置收费标准。");
        }
        //根据收费科目  项目查询收费标准
        for (ChargeChargeStandard chargeChargeStandard : chargeChargeStandards) {
            HouseStandardAddVo standardvo = new HouseStandardAddVo();
            standardvo.setChargeStartTime(chargeStartTime);
            //standardvo.setChargeEndTime();
            standardvo.setChargeCustor("2");
            standardvo.setOrganizationId(vo.getOrganizationId());
            HashMap<String, Object> preMap = new HashMap<String, Object>();
            param.put("chargeItemId", chargeChargeStandard.getChargeItemId());
            //查询该科目设置了哪些收费标准
            List<ChargeChargeStandard> standards = chargeChargeStandardMapper.findChargestandardNameForPericint(param);
            //查询该科目的设置单位值
            ChargeChargeItem chargeChargeItem = chargeChargeItemMapper.selectById(chargeChargeStandard.getChargeItemId());
            String unit = "";
            if (!Objects.isNull(chargeChargeItem) && !Objects.isNull(chargeChargeItem.getUnit())) {
                //查询单位具体值
                HashMap<String, Object> unitMap = new HashMap<String, Object>();
                unitMap.put("dictionaryDictype", "charge");
                unitMap.put("dictionaryDdcode", "Unit");
                unitMap.put("code", chargeChargeItem.getUnit());
                RestResult<String> result = systemRemoteService.findDictionaryName(unitMap);
                unit = Objects.isNull(result) ? "" : result.getResultData();
            }
            BeanUtils.copyProperties(chargeChargeStandard, standardvo);
            HashMap<String, Object> selectValue = new HashMap();
            selectValue.put(ChargeStaticData.VALUE_TYPE, "");
            selectValue.put(ChargeStaticData.UNIT_TYPE, "");
            preMap.put(ChargeStaticData.PICKED_TYPE, selectValue);
            List list = new ArrayList();
            for (ChargeChargeStandard e : standards) {
                HashMap<String, Object> options = Maps.newHashMap();
                options.put(ChargeStaticData.LABEL_TYPE, e.getStandardName());
                options.put(ChargeStaticData.VALUE_TYPE, e.getId());
                HashMap<String, Object> para = Maps.newHashMap();
                para.put("id", e.getId());
                para.put("type", "standard");
                List<ChargeChargeStandardLadder> chargeChargeStandardLadders = chargeChargeStandardLadderMapper.selectById(para);
                options.put(ChargeStaticData.UNIT_TYPE, !Objects.isNull(chargeChargeStandardLadders) && chargeChargeStandardLadders.size() > 0 ? (chargeChargeStandardLadders.get(0).getLadderPrice() + unit) : (0 + unit));
                list.add(options);
            }
            preMap.put(ChargeStaticData.OPTION_TYPE, list);
            standardvo.setUnit(unit);
            standardvo.setPreinctNames(preMap);
            standardvo.setHouseId(vo.getHouseId());
            addCustomerTypes(standardvo);

            addVos.add(standardvo);
        }
        return addVos;
    }

    private void addCustomerTypes(HouseStandardAddVo standardvo) {
        //添加一个死的数据给前端
        List<Map<String, Object>> customerTypeList = new ArrayList<>();
        Map<String, Object> developer = new HashMap<>();
        developer.put("label", "开发商");
        developer.put("value", 0);
        customerTypeList.add(developer);
        Map<String, Object> owner = new HashMap<>();
        owner.put("label", "业主");
        owner.put("value", 1);
        customerTypeList.add(owner);
        Map<String, Object> tenant = new HashMap<>();
        tenant.put("label", "租户");
        tenant.put("value", 2);
        customerTypeList.add(tenant);
        Map<String, Object> customerTypes = Maps.newHashMap();
        customerTypes.put(ChargeStaticData.OPTION_TYPE, customerTypeList);
        Map<String, Object> selectValue = new HashMap();
        selectValue.put(ChargeStaticData.VALUE_TYPE, "");
        selectValue.put(ChargeStaticData.UNIT_TYPE, "");
        customerTypes.put(ChargeStaticData.PICKED_TYPE, selectValue);

        standardvo.setCustomerTypes(customerTypes);
    }

    /**
     * 根据主键删除房产收费标准
     *
     * @param id 主键id
     * @return
     */
    @WriteDataSource
    public boolean delete(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        chargeHousechargestandardMapper.deleteById(id);
        chargeHousechargestandardcustomerMapper.deleteByHouseChargeId(id);
        return true;
    }

    /**
     * 根据主键批量删除房产收费标准
     *
     * @param ids
     * @return
     */
    @WriteDataSource
    public String deleteBatch(List<Long> ids) {
        if (Objects.isNull(ids)) {
            return "选择0条";
        }
        int i = chargeHousechargestandardMapper.deleteBatch(ids);
        chargeHousechargestandardcustomerMapper.deleteBatchByHouseId(ids);
        int size = ids.size();
        return "选择" + size + "条," + "成功" + i + "条," + "失败" + (size - i) + "条。";
    }

    /**
     * 审核房产收费标准,单条审核和批量审核时调用本接口（审核通过或者不通过调用此接口）
     *
     * @param vo
     * @return
     */
    @WriteDataSource
    public boolean auditHouseStandard(HouseStandardVo vo) {
        if (Objects.isNull(vo) || CollectionUtils.isEmpty(vo.getIds())) {
            return false;
        }
        ChargeHouseChargeStandard standard = new ChargeHouseChargeStandard();
        standard.setUpdateUserId(vo.getUpdateUserId());
        standard.setUpdateUserName(vo.getUpdateUserName());
        standard.setUpdateTime(vo.getUpdateTime());
        //审核通过
        if (vo.getIsPass()) {
            standard.setAuditStatus(HouseStandrdAduitStatus.PASSED.getValue());
            //审核不通过
        } else {
            standard.setAuditStatus(HouseStandrdAduitStatus.NOTPASS.getValue());
            standard.setNotPassReason(vo.getNotPassReason());
        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("standrd", standard);
        map.put("ids", vo.getIds());
        int count = chargeHousechargestandardMapper.updateAuditStatus(map);
        if (count != vo.getIds().size()) {
            return false;
        }
        return true;
    }

    /**
     * 反审核房产收费标准
     *
     * @param vo
     * @return
     */
    @WriteDataSource
    public boolean auditBackHouseStandard(HouseStandardVo vo) {
        if (Objects.isNull(vo) || CollectionUtils.isEmpty(vo.getIds())) {
            return false;
        }
        //清空不通过理由，将审核状态修改成未审核
        ChargeHouseChargeStandard standard = new ChargeHouseChargeStandard();
        standard.setUpdateUserId(vo.getUpdateUserId());
        standard.setUpdateUserName(vo.getUpdateUserName());
        standard.setUpdateTime(vo.getUpdateTime());
        standard.setAuditStatus(HouseStandrdAduitStatus.NOTAUDIT.getValue());
        standard.setNotPassReason("");
        Map<String, Object> map = Maps.newHashMap();
        map.put("standrd", standard);
        map.put("ids", vo.getIds());
        int count = chargeHousechargestandardMapper.updateAuditStatus(map);
        if (count != vo.getIds().size()) {
            return false;
        }
        return true;
    }


    /**
     * 根据列表检索条件审核通过或者不通过所有的房产收费标准
     *
     * @param searchVo 检索条件
     * @return
     */
    @WriteDataSource
    public boolean auditAllHouseStandrd(SearchVo searchVo) {
        if (Objects.isNull(searchVo) || Objects.isNull(searchVo.getOtherConditions())) {
            return false;
        }
        Map<String, Object> otherConditions = searchVo.getOtherConditions();
        Boolean isPass = (Boolean) otherConditions.get("isPass");
        if (Objects.isNull(isPass)) {
            return false;
        }
        //审核通过的情况
        if (isPass) {
            otherConditions.put("auditStatus", HouseStandrdAduitStatus.PASSED.getValue());
            //审核不通过的情况
        } else {
            otherConditions.put("auditStatus", HouseStandrdAduitStatus.NOTPASS.getValue());
        }
        return chargeHousechargestandardMapper.auditAllHouseStandrd(searchVo) > 0;
    }

    /**
     * 根据列表检索条件反审核过所有的房产收费标准
     *
     * @param searchVo 检索条件
     * @return
     */
    @WriteDataSource
    public boolean auditBackAllHouseStandrd(SearchVo searchVo) {
        if (Objects.isNull(searchVo) || Objects.isNull(searchVo.getOtherConditions())) {
            return false;
        }
        Map<String, Object> otherConditions = searchVo.getOtherConditions();
        //审核通过的情况
        otherConditions.put("auditStatus", HouseStandrdAduitStatus.NOTAUDIT.getValue());
        return chargeHousechargestandardMapper.auditBackAllHouseStandrd(searchVo) > 0;
    }

    @Override
    public List<AppStandardVo> listAppStandard(Long enterpriseId, Long orgId, Long houseId, String houseName, Integer typeId) {
        if (houseId == null) {
            RestResult<OwnerHouseBaseInfo> ownerHouseBaseInfoRestResult = ownerRemoteService.searchHouseInfoByFullName(enterpriseId, orgId, houseName);
            if (ownerHouseBaseInfoRestResult.getResultData() != null) {
                houseId = ownerHouseBaseInfoRestResult.getResultData().getHouseId();
            }
        }
        BizException.isNull(houseId, "房产信息");
        Map<String, Object> map = Maps.newHashMap();
        map.put("enterpriseId", enterpriseId);
        map.put("orgId", orgId);
        map.put("houseId", houseId);
        map.put("typeId", typeId);
        List<ChargeHouseChargeStandard> chargeChargeStandards = chargeHousechargestandardMapper.listAppHouseStandard(map);
        List<AppStandardVo> appStandardVoList = new ArrayList<>();
        for (ChargeHouseChargeStandard chargeHouseChargeStandard : chargeChargeStandards) {
            AppStandardVo appStandardVo = new AppStandardVo();
            appStandardVo.setChargeItemID(chargeHouseChargeStandard.getChargeItemId());
            appStandardVo.setChargeItemName(chargeHouseChargeStandard.getChargeItemName());
            appStandardVo.setChargeID(chargeHouseChargeStandard.getChargeId());
            appStandardVo.setChargeName(chargeHouseChargeStandard.getChargeItemName());
            Map<String, Object> param = Maps.newHashMap();
            param.put("enterpriseId", chargeHouseChargeStandard.getEnterpriseId());
            param.put("organizationId", chargeHouseChargeStandard.getOrganizationId());
            param.put("houseChargeId", chargeHouseChargeStandard.getId());
            param.put("houseId", chargeHouseChargeStandard.getHouseId());
            List<ChargeHouseChargeStandardCustomer> customerList = chargeHousechargestandardcustomerMapper.selectByHouseChargeId(param);
            if (!CollectionUtils.isEmpty(customerList)) {
                appStandardVo.setCustomerID(customerList.get(0).getOwnerId());
            }
            appStandardVo.setHouseID(chargeHouseChargeStandard.getHouseId());
            appStandardVoList.add(appStandardVo);
        }
        return appStandardVoList;
    }

}
