package com.newsee.charge.service.impl;

import java.util.*;

import com.google.common.collect.Maps;
import com.newsee.charge.vo.AppStandardVo;
import com.newsee.common.exception.BizException;
import com.newsee.owner.entity.OwnerHouseHouseInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.charge.dao.ChargeChargeStandardLadderMapper;
import com.newsee.charge.dao.ChargeChargeStandardMapper;
import com.newsee.charge.entity.ChargeChargeStandard;
import com.newsee.charge.entity.ChargeChargeStandardLadder;
import com.newsee.charge.service.IItemService;
import com.newsee.charge.service.IStandardService;
import com.newsee.charge.service.remote.IOwnerRemoteService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.ItemVo;
import com.newsee.charge.vo.StandardVo;
import com.newsee.common.rest.RestResult;
import com.newsee.common.utils.DateUtils;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.entity.OwnerHouseBaseInfo;

@Service
public class StandardServiceImpl implements IStandardService {

    @Autowired
    private ChargeChargeStandardMapper chargeChargestandardMapper;
    @Autowired
    private ChargeChargeStandardLadderMapper chargeChargestandardladderMapper;

    @Autowired
    private IOwnerRemoteService iOwnerRemoteService;

    @Autowired
    private ISystemRemoteService systemRemoteService;
    @Autowired
    private IItemService itemServiceImpl;
    @Autowired
    private IStandardService standardServiceImpl;

    /**
     * 获取收费标准列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    @ReadDataSource
    public PageInfo<ChargeChargeStandard> listPage(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        if (!Objects.isNull(searchVo.getId())) {
            List<Long> ids = itemServiceImpl.findChildALL(searchVo.getId());
            searchVo.setIds(ids);
        }
        if (!Objects.isNull(searchVo.getHouseId())) {
            List<String> type = new ArrayList<String>();
            //查询区域下所有的项目
            type.add("2");
            List<OwnerHouseBaseInfo> houses = iOwnerRemoteService.listAllChildNode(searchVo.getHouseId(), type).getResultData();
            List<Long> houseIds = new ArrayList<Long>();
            if (!CollectionUtils.isEmpty(houses)) {
                houses.forEach(item -> {
                    houseIds.add(item.getHouseId());
                });
            }
            houseIds.add(searchVo.getHouseId());
            searchVo.setHouseIds(houseIds);
        }
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeChargeStandard> list = chargeChargestandardMapper.listPage(searchVo);
        for (ChargeChargeStandard chargeChargeStandard : list) {
            ItemVo itemVo = itemServiceImpl.detail(chargeChargeStandard.getChargeItemId());
            StandardVo vo = standardServiceImpl.detail(chargeChargeStandard.getId());
            List<ChargeChargeStandardLadder> standardLadderList = vo.getStandardLadderList();
            for (ChargeChargeStandardLadder chargeChargeStandardLadder : standardLadderList) {
                chargeChargeStandard.setPrice(chargeChargeStandardLadder.getLadderPrice());
            }
            HashMap<String, Object> unitMap = new HashMap<String, Object>();
            unitMap.put("dictionaryDictype", "charge");
            unitMap.put("dictionaryDdcode", "Unit");
            unitMap.put("code", itemVo.getUnit());
            RestResult<String> result = systemRemoteService.findDictionaryName(unitMap);
            if (!Objects.isNull(result) && !Objects.isNull(result.getResultData())) {
                chargeChargeStandard.setUnit(result.getResultData());
            }
        }
        PageInfo<ChargeChargeStandard> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }


    @Override
    @ReadDataSource
    public List<ChargeChargeStandard> listPageALL(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        if (Objects.isNull(searchVo)) {
            return null;
        }
        if (!Objects.isNull(searchVo.getId())) {
            List<Long> ids = itemServiceImpl.findChildALL(searchVo.getId());
            searchVo.setIds(ids);
        }
        if (!Objects.isNull(searchVo.getHouseId())) {
            List<String> type = new ArrayList<String>();
            //查询区域下所有的项目
            type.add("2");
            List<OwnerHouseBaseInfo> houses = iOwnerRemoteService.listAllChildNode(searchVo.getHouseId(), type).getResultData();
            List<Long> houseIds = new ArrayList<Long>();
            if (!CollectionUtils.isEmpty(houses)) {
                houses.forEach(item -> {
                    houseIds.add(item.getHouseId());
                });
            }
            houseIds.add(searchVo.getHouseId());
            searchVo.setHouseIds(houseIds);
        }
        List<ChargeChargeStandard> list = chargeChargestandardMapper.listPage(searchVo);
        for (ChargeChargeStandard chargeChargeStandard : list) {
            ItemVo itemVo = itemServiceImpl.detail(chargeChargeStandard.getChargeItemId());
            StandardVo vo = standardServiceImpl.detail(chargeChargeStandard.getId());
            List<ChargeChargeStandardLadder> standardLadderList = vo.getStandardLadderList();
            for (ChargeChargeStandardLadder chargeChargeStandardLadder : standardLadderList) {
                chargeChargeStandard.setPrice(chargeChargeStandardLadder.getLadderPrice());
            }
            HashMap<String, Object> unitMap = new HashMap<String, Object>();
            unitMap.put("dictionaryDictype", "charge");
            unitMap.put("dictionaryDdcode", "Unit");
            unitMap.put("code", itemVo.getUnit());
            RestResult<String> result = systemRemoteService.findDictionaryName(unitMap);
            if (!Objects.isNull(result) && !Objects.isNull(result.getResultData())) {
                chargeChargeStandard.setUnit(result.getResultData());
            }
        }
        return list;
    }

    /**
     * 获取收费标准详情
     *
     * @param id 主键id
     * @return
     */
    @ReadDataSource
    public StandardVo detail(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        StandardVo vo = new StandardVo();
        ChargeChargeStandard chargeChargestandard = chargeChargestandardMapper.selectById(id);
        List<String> date = new ArrayList<String>();
        date.add(Objects.isNull(chargeChargestandard.getExecuteDate()) ? "" : DateUtils.dateToSpecialStr(chargeChargestandard.getExecuteDate()));
        date.add(Objects.isNull(chargeChargestandard.getCancelDate()) ? "" : DateUtils.dateToSpecialStr(chargeChargestandard.getCancelDate()));
        chargeChargestandard.setEffectiveDate(date);
        //如果查询出了数据，将数据拷贝到vo中
        if (!Objects.isNull(chargeChargestandard)) {
            BeanUtils.copyProperties(chargeChargestandard, vo);
        }
        HashMap<String, Object> param = new HashMap<String, Object>();
        param.put("id", id);
        param.put("type", "standard");
        List<ChargeChargeStandardLadder> standardLadderList = chargeChargestandardladderMapper.selectById(param);
        if (!Objects.isNull(standardLadderList)) {
            vo.setStandardLadderList(standardLadderList);
        }
        param.put("type", "delay");
        List<ChargeChargeStandardLadder> delayList = chargeChargestandardladderMapper.selectById(param);
        if (!Objects.isNull(delayList)) {
            vo.setDelayList(delayList);
        }

        ItemVo itemVo = itemServiceImpl.detail(vo.getChargeItemId());
        HashMap<String, Object> unitMap = new HashMap<String, Object>();
        unitMap.put("dictionaryDictype", "charge");
        unitMap.put("dictionaryDdcode", "Unit");
        unitMap.put("code", itemVo.getUnit());
        RestResult<String> result = systemRemoteService.findDictionaryName(unitMap);
        if (!Objects.isNull(result) && !Objects.isNull(result.getResultData())) {
            vo.setUnit(result.getResultData());
            vo.setUnitName(result.getResultData());
        }
        return vo;
    }


    @ReadDataSource
    public Boolean checkName(StandardVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeChargeStandard chargeChargeStandard = new ChargeChargeStandard();
        BeanUtils.copyProperties(vo, chargeChargeStandard);
        Integer counts = chargeChargestandardMapper.checkName(chargeChargeStandard);
        if (counts != null && counts > 0) {
            return false;
        }
        return true;
    }

    /**
     * 编辑收费标准详情
     *
     * @return boolean 编辑成功与否
     */
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean edit(StandardVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        if (!CollectionUtils.isEmpty(vo.getEffectiveDate())) {
            vo.setExecuteDate(DateUtils.strToDate(vo.getEffectiveDate().get(0)));
            vo.setCancelDate(DateUtils.strToDate(vo.getEffectiveDate().get(1)));
        }
        ChargeChargeStandard chargeChargestandard = new ChargeChargeStandard();
        BeanUtils.copyProperties(vo, chargeChargestandard);
        int countchargeChargestandard = chargeChargestandardMapper.updateById(chargeChargestandard);
        //int tt = 1 / 0;
        if (countchargeChargestandard == 0) {
            return false;
        }
        chargeChargestandardladderMapper.deleteByStandardId(vo.getId());
        if (!CollectionUtils.isEmpty(vo.getStandardLadderList())) {
            for (ChargeChargeStandardLadder chargeChargeStandardLadder : vo.getStandardLadderList()) {
                chargeChargeStandardLadder.setStandardId(chargeChargestandard.getId());
                chargeChargeStandardLadder.setEnterpriseId(chargeChargestandard.getEnterpriseId());
                chargeChargeStandardLadder.setOrganizationId(chargeChargestandard.getOrganizationId());
            }
            vo.getStandardLadderList().forEach(item -> {
                chargeChargestandardladderMapper.insert(item);
            });
        }
        if (!CollectionUtils.isEmpty(vo.getDelayList())) {
            for (ChargeChargeStandardLadder chargeChargeStandardLadder : vo.getDelayList()) {
                chargeChargeStandardLadder.setStandardId(chargeChargestandard.getId());
                chargeChargeStandardLadder.setEnterpriseId(chargeChargestandard.getEnterpriseId());
                chargeChargeStandardLadder.setOrganizationId(chargeChargestandard.getOrganizationId());
            }
            vo.getDelayList().forEach(item -> {
                chargeChargestandardladderMapper.insert(item);
            });
        }
        return true;
    }

    /**
     * 新增收费标准
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    public boolean add(StandardVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }

        if (!CollectionUtils.isEmpty(vo.getEffectiveDate())) {
            vo.setExecuteDate(DateUtils.strToSpecialDate(vo.getEffectiveDate().get(0)));
            vo.setCancelDate(DateUtils.strToSpecialDate(vo.getEffectiveDate().get(1)));
        }
        ChargeChargeStandard chargeChargestandard = new ChargeChargeStandard();
        BeanUtils.copyProperties(vo, chargeChargestandard);
        int countchargeChargestandard = chargeChargestandardMapper.insert(chargeChargestandard);
        if (countchargeChargestandard == 0) {
            return false;
        }
        if (!CollectionUtils.isEmpty(vo.getStandardLadderList())) {
            for (ChargeChargeStandardLadder chargeChargeStandardLadder : vo.getStandardLadderList()) {
                chargeChargeStandardLadder.setStandardId(chargeChargestandard.getId());
                chargeChargeStandardLadder.setEnterpriseId(chargeChargestandard.getEnterpriseId());
                chargeChargeStandardLadder.setOrganizationId(chargeChargestandard.getOrganizationId());
            }
            vo.getStandardLadderList().forEach(item -> {
                chargeChargestandardladderMapper.insert(item);
            });
        }
        if (!CollectionUtils.isEmpty(vo.getDelayList())) {
            for (ChargeChargeStandardLadder chargeChargeStandardLadder : vo.getDelayList()) {
                chargeChargeStandardLadder.setStandardId(chargeChargestandard.getId());
                chargeChargeStandardLadder.setEnterpriseId(chargeChargestandard.getEnterpriseId());
                chargeChargeStandardLadder.setOrganizationId(chargeChargestandard.getOrganizationId());
            }
            vo.getDelayList().forEach(item -> {
                chargeChargestandardladderMapper.insert(item);
            });
        }
        return true;
    }

    /**
     * 根据主键删除收费标准
     *
     * @param id 主键id
     * @return
     */
    @WriteDataSource
    public boolean delete(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        int countchargeChargestandard = chargeChargestandardMapper.deleteById(id);
        if (countchargeChargestandard == 0) {
            return false;
        }
        chargeChargestandardladderMapper.deleteByStandardId(id);
        return true;
    }

    /**
     * 根据主键批量删除收费标准
     *
     * @param ids
     * @return
     */
    @WriteDataSource
    public int deleteBatch(List<Long> ids) {
        if (Objects.isNull(ids)) {
            return 0;
        }
        int countchargeChargestandard = chargeChargestandardMapper.deleteBatch(ids);
        int countchargeChargestandardladder = chargeChargestandardladderMapper.deleteByStandardIdBatch(ids);
        return countchargeChargestandard;
    }

    @Override
    @ReadDataSource
    public List<ChargeChargeStandard> listStandardForm(SearchVo searchVo) {
        if (!Objects.isNull(searchVo.getId()) && searchVo.getId() > 0L) {
            List<Long> ids = itemServiceImpl.findChildALL(searchVo.getId());
            searchVo.setIds(ids);
        }
        if (!Objects.isNull(searchVo.getHouseId())) {
            List<String> type = new ArrayList<String>();
            //查询区域下所有的项目
            type.add("2");
            List<OwnerHouseBaseInfo> houses = iOwnerRemoteService.listAllChildNode(searchVo.getHouseId(), type).getResultData();
            List<Long> houseIds = new ArrayList<Long>();
            if (!CollectionUtils.isEmpty(houses)) {
                houses.forEach(item -> {
                    houseIds.add(item.getHouseId());
                });
            }
            houseIds.add(searchVo.getHouseId());
            searchVo.setHouseIds(houseIds);
        }
        List<ChargeChargeStandard> list = chargeChargestandardMapper.listPage(searchVo);
        return list;
    }

}
