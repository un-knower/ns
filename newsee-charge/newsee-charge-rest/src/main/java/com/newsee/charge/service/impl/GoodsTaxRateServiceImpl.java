package com.newsee.charge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.charge.dao.ChargeChargeItemMapper;
import com.newsee.charge.dao.ChargeGoodsTaxMapper;
import com.newsee.charge.dao.ChargeGoodsTaxRateMapper;
import com.newsee.charge.entity.ChargeChargeItem;
import com.newsee.charge.entity.ChargeGoodsTax;
import com.newsee.charge.entity.ChargeGoodsTaxRate;
import com.newsee.charge.service.IGoodsTaxRateService;
import com.newsee.charge.service.remote.IOwnerRemoteService;
import com.newsee.charge.vo.GoodsTaxRateVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;

@Service
public class GoodsTaxRateServiceImpl implements IGoodsTaxRateService {

    @Autowired
    private ChargeGoodsTaxRateMapper chargeGoodsTaxRateMapper;
    @Autowired
    private ChargeChargeItemMapper chargeChargeItemMapper;
    @Autowired
    private ChargeGoodsTaxMapper chargeGoodsTaxMapper;
    @Autowired
    private IOwnerRemoteService iOwnerRemoteService;


    /**
     * 获取税率列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    @ReadDataSource
    public PageInfo<ChargeGoodsTaxRate> listPage(List<OwnerHouseBaseInfo> houseBaseInfoList, SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        List<Long> houseIdList = new ArrayList<>();
        Long precinctId = Long.valueOf(searchVo.getOtherConditions().get("houseId").toString());
        if (!CollectionUtils.isEmpty(houseBaseInfoList)) {
            List<Long> houseIds = houseBaseInfoList.stream().map(house -> house.getHouseId()).collect(Collectors.toList());
            houseIdList.addAll(houseIds);
        }
        houseIdList.add(precinctId);
        searchVo.setHouseIds(houseIdList);
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeGoodsTaxRate> list = chargeGoodsTaxRateMapper.listPage(searchVo);
        PageInfo<ChargeGoodsTaxRate> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 将组织中的收费科目新增和更新到表chargeGoodsTaxRate中
     *
     * @param organizationId 组织id
     * @param precinctId     项目id
     */
    @WriteDataSource
    public List<OwnerHouseBaseInfo> initGoodsTaxRate(ChargeChargeItem item, Long organizationId, Long precinctId) {
        Map<String, Object> map = Maps.newHashMap();
        Long enterpriseId = item.getEnterpriseId();
        map.put("enterpriseId", enterpriseId);
        //查询房产树下的所有项目子节点
        List<OwnerHouseBaseInfo> childHouseList = iOwnerRemoteService.listAllChildNode(precinctId, newArrayList("2")).getResultData();
        Date now = new Date();
        //如果表ChargeGoodsTaxRate中不存在该收费科目，则插入到该表中
        if (!CollectionUtils.isEmpty(childHouseList)) {
            List<ChargeGoodsTaxRate> chargeGoodsTaxRates = new ArrayList<>();
            childHouseList.forEach(house -> {
                ChargeGoodsTaxRate rateExist = getChargeGoodsTaxRate(enterpriseId, item.getId(), house.getHouseId());
                if (Objects.isNull(rateExist)) {
                    ChargeGoodsTaxRate rate = new ChargeGoodsTaxRate();
                    rate.setEnterpriseId(enterpriseId);
                    rate.setOrganizationId(organizationId);
                    rate.setPrecinctId(house.getHouseId());
                    rate.setPrecinctName(house.getHouseName());
                    rate.setChargeItemId(item.getId());
                    rate.setChargeItemName(item.getChargeItemName());
                    String goodsTaxCode = item.getGoodsTaxCode();
                    rate.setGoodsTaxCode(goodsTaxCode == null ? "" : goodsTaxCode);
                    setGoodsTaxInfo(enterpriseId, rate, goodsTaxCode);
                    rate.setCreateUserId(item.getCreateUserId());
                    rate.setCreateUserName(item.getCreateUserName());
                    rate.setCreateTime(now);
                    rate.setUpdateUserId(item.getUpdateUserId());
                    rate.setUpdateUserName(item.getUpdateUserName());
                    rate.setUpdateTime(now);
                    rate.setRemark("");
                    chargeGoodsTaxRates.add(rate);
                }
            });
            if (!CollectionUtils.isEmpty(chargeGoodsTaxRates)) {
                chargeGoodsTaxRateMapper.insertBatch(chargeGoodsTaxRates);
            }
        }
        return childHouseList;
    }

    private void setGoodsTaxInfo(Long enterpriseId, ChargeGoodsTaxRate rate, String goodsTaxCode) {
        if (goodsTaxCode != null) {
            Map<String, Object> taxMap = Maps.newHashMap();
            taxMap.put("enterpriseId", enterpriseId);
            taxMap.put("goodsTaxNo", goodsTaxCode);
            List<ChargeGoodsTax> chargeGoodsTaxes = chargeGoodsTaxMapper.selectByCode(taxMap);
            if (!CollectionUtils.isEmpty(chargeGoodsTaxes)) {
                ChargeGoodsTax chargeGoodsTax = chargeGoodsTaxes.get(0);
                rate.setGoodsTaxId(chargeGoodsTax.getId());
                rate.setGoodsTaxName(chargeGoodsTax.getGoodsTaxName());
            }
        }
    }

    @Override
    public boolean delete(Long enterpriseId, Long chargeItemId, Long houseId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("enterpriseId", enterpriseId);
        map.put("chargeItemId", chargeItemId);
        map.put("houseId", houseId);
        int num = chargeGoodsTaxRateMapper.deleteByParam(map);
        if (num > 0) {
            return true;
        }
        return false;
    }

    @Override
    public boolean updatePrecinctName(Long enterpriseId, Long houseId, String houseName) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("enterpriseId", enterpriseId);
        map.put("houseId", houseId);
        map.put("houseName", houseName);
        int num = chargeGoodsTaxRateMapper.updatePrecinctName(map);
        if (num > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据科目id和房产id查询税率
     *
     * @param enterpriseId
     * @param chargeItemId
     * @param houseId
     * @return
     */
    public ChargeGoodsTaxRate getChargeGoodsTaxRate(Long enterpriseId, Long chargeItemId, Long houseId) {
        Map<String, Object> mapRate = Maps.newHashMap();
        mapRate.put("enterpriseId", enterpriseId);
        mapRate.put("chargeItemId", chargeItemId);
        mapRate.put("precinctId", houseId);
        //判断税率是否设置过
        return chargeGoodsTaxRateMapper.selectByOrganizationIdAndItemId(mapRate);
    }

    /**
     * 获取税率详情
     *
     * @param id 主键id
     * @return
     */
    @ReadDataSource
    public GoodsTaxRateVo detail(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        GoodsTaxRateVo vo = new GoodsTaxRateVo();
        ChargeGoodsTaxRate chargeGoodstaxrate = chargeGoodsTaxRateMapper.selectById(id);
        //如果查询出了数据，将数据拷贝到vo中
        if (!Objects.isNull(chargeGoodstaxrate)) {
            BeanUtils.copyProperties(chargeGoodstaxrate, vo);
        }
        return vo;
    }

    /**
     * 编辑税率详情
     *
     * @return boolean 编辑成功与否
     */
    @WriteDataSource
    public boolean edit(List<GoodsTaxRateVo> vos) {
        if (CollectionUtils.isEmpty(vos)) {
            return false;
        }
        vos.forEach(vo -> {
            ChargeGoodsTaxRate chargeGoodstaxrate = new ChargeGoodsTaxRate();
            BeanUtils.copyProperties(vo, chargeGoodstaxrate);
            if (chargeGoodstaxrate.getRemark() == null) {
                chargeGoodstaxrate.setRemark("");
            }
            chargeGoodsTaxRateMapper.updateById(chargeGoodstaxrate);
        });
        return true;
    }

    /**
     * 新增税率
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    public boolean add(GoodsTaxRateVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeGoodsTaxRate chargeGoodstaxrate = new ChargeGoodsTaxRate();
        BeanUtils.copyProperties(vo, chargeGoodstaxrate);
        int countchargeGoodstaxrate = chargeGoodsTaxRateMapper.insert(chargeGoodstaxrate);
        if (countchargeGoodstaxrate == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据主键删除税率
     *
     * @param id 主键id
     * @return
     */
    @WriteDataSource
    public boolean delete(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        int countchargeGoodstaxrate = chargeGoodsTaxRateMapper.deleteById(id);
        if (countchargeGoodstaxrate == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据主键批量删除税率
     *
     * @param ids
     * @return
     */
    @WriteDataSource
    public boolean deleteBatch(List<Long> ids) {
        if (Objects.isNull(ids)) {
            return false;
        }
        int countchargeGoodstaxrate = chargeGoodsTaxRateMapper.deleteBatch(ids);
        if (countchargeGoodstaxrate == 0) {
            return false;
        }
        return true;
    }
}
