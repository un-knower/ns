package com.newsee.charge.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.charge.dao.ChargeChargeStandardMapper;
import com.newsee.charge.dao.ChargeCustomerChargeDetailLogMapper;
import com.newsee.charge.dao.ChargeCustomerChargeDetailMapper;
import com.newsee.charge.dao.ChargeDiscountDetailMapper;
import com.newsee.charge.entity.ChargeChargeStandard;
import com.newsee.charge.entity.ChargeCustomerChargeDetail;
import com.newsee.charge.entity.ChargeCustomerChargeDetailLog;
import com.newsee.charge.entity.ChargeDiscountDetail;
import com.newsee.charge.service.IPaymentService;
import com.newsee.charge.service.remote.IOwnerRemoteService;
import com.newsee.charge.vo.PaymentVo;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.StringUtils;
import com.newsee.common.vo.NsCoreResourcecolumnVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PaymentServiceImpl implements IPaymentService {

    @Autowired
    private ChargeCustomerChargeDetailMapper chargeCustomerChargeDetailMapper;
    @Autowired
    private ChargeCustomerChargeDetailLogMapper chargeCustomerChargeDetailLogMapper;
    @Autowired
    private IOwnerRemoteService iOwnerRemoteService;
    @Autowired
    private ChargeDiscountDetailMapper chargeDiscountDetailMapper;
    @Autowired
    private ChargeChargeStandardMapper chargeChargeStandardMapper;

    /**
     * 获取应收款列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    @ReadDataSource
    public PageInfo<ChargeCustomerChargeDetail> listPage(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        List<Long> houseIdList = new ArrayList<>();
        houseIdList.add(searchVo.getHouseId());
        List<OwnerHouseBaseInfo> houseResult = iOwnerRemoteService.listAllChildNode(searchVo.getHouseId(), Arrays.asList("6", "7", "8", "9")).getResultData();
        if (!CollectionUtils.isEmpty(houseResult)) {
            List<Long> houseIds = houseResult.stream().map(house -> house.getHouseId()).collect(Collectors.toList());
            houseIdList.addAll(houseIds);
        }
        searchVo.setHouseIds(houseIdList);
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeCustomerChargeDetail> list = chargeCustomerChargeDetailMapper.listPage(searchVo);
        PageInfo<ChargeCustomerChargeDetail> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 获取应收款详情
     *
     * @param id 主键id
     * @return
     */
    @ReadDataSource
    public PaymentVo detail(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        PaymentVo vo = new PaymentVo();
        ChargeCustomerChargeDetail chargeCustomerchargedetail = chargeCustomerChargeDetailMapper.selectById(id);
        //如果查询出了数据，将数据拷贝到vo中
        if (!Objects.isNull(chargeCustomerchargedetail)) {
            BeanUtils.copyProperties(chargeCustomerchargedetail, vo);
        }
        Map<String, Object> map = Maps.newHashMap();
        map.put("preinctId", chargeCustomerchargedetail.getPreinctId());
        map.put("enterpriseId", chargeCustomerchargedetail.getEnterpriseId());
        map.put("organizationId", chargeCustomerchargedetail.getOrganizationId());
        map.put("chargeItemId", chargeCustomerchargedetail.getChargeItemId());
        List<ChargeChargeStandard> standardList = chargeChargeStandardMapper.findChargestandardNameForPericint(map);
        vo.setStandardList(standardList);
        return vo;
    }

    /**
     * 编辑应收款详情
     *
     * @return boolean 编辑成功与否
     */
    @WriteDataSource
    public boolean edit(PaymentVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        Date now = new Date();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long orgId = LoginDataHelper.getOrgId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        ChargeCustomerChargeDetail dbDetail = chargeCustomerChargeDetailMapper.selectById(vo.getId());
        ChargeCustomerChargeDetail detail = new ChargeCustomerChargeDetail();
        BeanUtils.copyProperties(vo, detail);
        detail.setActualChargeSum(vo.getActualChargeSum());
        int num = chargeCustomerChargeDetailMapper.updateById(detail);
        //新增应收款调整记录

        if (num == 0) {
            return false;
        }
        dbDetail.setCreateUserId(userId);
        dbDetail.setCreateUserName(userName);
        dbDetail.setCreateTime(now);
        dbDetail.setUpdateUserId(userId);
        dbDetail.setUpdateUserName(userName);
        dbDetail.setUpdateTime(now);
        dbDetail.setEnterpriseId(enterpriseId);
        dbDetail.setOrganizationId(orgId);
        if (detail.getCalcEndDate() != null) dbDetail.setCalcEndDate(detail.getCalcEndDate());
        if (detail.getCalcStartDate() != null) dbDetail.setCalcStartDate(detail.getCalcStartDate());
        if (detail.getShouldChargeDate() != null) dbDetail.setShouldChargeDate(detail.getShouldChargeDate());
        if (detail.getActualChargeSum() != null) dbDetail.setActualChargeSum(detail.getActualChargeSum());
        if (detail.getAmount() != null) dbDetail.setAmount(detail.getAmount());
        if (detail.getPaidChargeSum() != null) dbDetail.setPaidChargeSum(detail.getPaidChargeSum());
        //新建变更日志
        addChargeDetailLog(dbDetail, 0, dbDetail.getActualChargeSum(), vo.getDescription());
        //新增应收款调整记录
        addChargeDiscountDetail(detail, dbDetail);
        return true;
    }

    /**
     * 新增应收款调整记录
     *
     * @param newDetail
     * @param oldDetail
     */
    private void addChargeDiscountDetail(ChargeCustomerChargeDetail newDetail, ChargeCustomerChargeDetail oldDetail) {
        ChargeDiscountDetail chargeDiscountDetail = new ChargeDiscountDetail();
        chargeDiscountDetail.setEnterpriseId(oldDetail.getEnterpriseId());
        chargeDiscountDetail.setOrganizationId(oldDetail.getOrganizationId());
        chargeDiscountDetail.setChargeDetailId(oldDetail.getId());
        chargeDiscountDetail.setOldAmount(oldDetail.getAmount());
        chargeDiscountDetail.setOldShouldChargeDate(oldDetail.getShouldChargeDate());
        chargeDiscountDetail.setOldShouldChargeSum(oldDetail.getActualChargeSum());
        chargeDiscountDetail.setNewAmount(newDetail.getAmount());
        chargeDiscountDetail.setNewShouldChargeDay(newDetail.getShouldChargeDate());
        chargeDiscountDetail.setNewShouldChargeSum(newDetail.getActualChargeSum());
        chargeDiscountDetail.setCreateUserId(oldDetail.getCreateUserId());
        chargeDiscountDetail.setCreateTime(oldDetail.getCreateTime());
        chargeDiscountDetail.setCreateUserName(oldDetail.getCreateUserName());
        chargeDiscountDetailMapper.insert(chargeDiscountDetail);
    }

    /**
     * 新增应收款
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    public boolean add(PaymentVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeCustomerChargeDetail chargeCustomerchargedetail = new ChargeCustomerChargeDetail();
        BeanUtils.copyProperties(vo, chargeCustomerchargedetail);
        int num = chargeCustomerChargeDetailMapper.insert(chargeCustomerchargedetail);
        if (num == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据主键删除应收款
     *
     * @param id 主键id
     * @return
     */
    @WriteDataSource
    public boolean delete(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        String ischeck = chargeCustomerChargeDetailMapper.selectById(id).getIsCheck();
        if (!"审核通过".equals(ischeck)) {
            int countchargeCustomerchargedetail = chargeCustomerChargeDetailMapper.deleteById(id);
            if (countchargeCustomerchargedetail == 0) {
                return false;
            }
        } else {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "审核通过的数据不允许删除");
            return false;
        }
        return true;
    }

    /**
     * 根据主键批量删除应收款
     *
     * @param ids
     * @return
     */
    @WriteDataSource
    public boolean deleteBatch(List<Long> ids) {
        if (Objects.isNull(ids)) {
            return false;
        }
        List<String> list = new ArrayList<String>();
        for (Long long1 : ids) {
            String ischeck = chargeCustomerChargeDetailMapper.selectById(long1).getIsCheck();
            list.add(ischeck);
        }
        if (!list.contains("审核通过")) {
            int countchargeCustomerchargedetail = chargeCustomerChargeDetailMapper.deleteBatch(ids);
            if (countchargeCustomerchargedetail == 0) {
                return false;
            }
        } else {
            BizException.fail(ResultCodeEnum.PARAMS_ERROR, "存在审核通过的数据,删除失败！");
            return false;
        }
        return true;
    }

    @Override
    @WriteDataSource
    public Integer checkChargeDetail(Map<String, Object> map) {
        String check = (String) map.get("isCheck");
        int num = chargeCustomerChargeDetailMapper.checkChargeDetail(map);
        return num;
    }

    @Override
    @WriteDataSource
    public Integer discountChargeDetail(String type, List<ChargeCustomerChargeDetail> chargeDetailList) {
        //TODO:有欠费、缴款记录的应收款不能进行减免操作
        int sum = 0;
        if (!CollectionUtils.isEmpty(chargeDetailList)) {
            for (ChargeCustomerChargeDetail detail : chargeDetailList) {
                if (detail.getDiscount() == null && detail.getDiscountReason() == null)
                    continue;
                int num = chargeCustomerChargeDetailMapper.discountChargeDetail(detail);
                sum += num;
            }
        }
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        Long orgId = LoginDataHelper.getOrgId();
        Long userId = LoginDataHelper.getUserId();
        String userName = LoginDataHelper.getUserName();
        Date now = new Date();
        //新建变更日志
        for (ChargeCustomerChargeDetail detail : chargeDetailList) {
            ChargeCustomerChargeDetail dbDetail = chargeCustomerChargeDetailMapper.selectById(detail.getId());
            dbDetail.setEnterpriseId(enterpriseId);
            dbDetail.setOrganizationId(orgId);
            dbDetail.setCreateUserId(userId);
            dbDetail.setCreateUserName(userName);            dbDetail.setCreateTime(now);
            dbDetail.setUpdateUserName(userName);
            dbDetail.setUpdateUserId(userId);
            dbDetail.setUpdateTime(now);
            addChargeDetailLog(dbDetail, 1,  null, type);
            //新增应收款调整记录
//            addChargeDiscountDetail(detail, dbDetail);
        }
        return sum;
    }

    /**
     * 添加日志
     *
     * @param chargeDetail 应收款详情
     * @param type         类型，0:变更，1:减免
     */
    private void addChargeDetailLog(ChargeCustomerChargeDetail chargeDetail, Integer type, Double beforeActualChargeSum, String description) {
        ChargeCustomerChargeDetailLog detailLog = new ChargeCustomerChargeDetailLog();
        BeanUtils.copyProperties(chargeDetail, detailLog);
        detailLog.setChargeDetailId(chargeDetail.getId());
        detailLog.setDescription(description);
        Double actualChargeSum = chargeDetail.getActualChargeSum() == null ? 0d : chargeDetail.getActualChargeSum();
        if (type == 0) {
            detailLog.setBeforeActualChargeSum(beforeActualChargeSum);
            detailLog.setChangeChargeSum(beforeActualChargeSum - actualChargeSum);
            detailLog.setAfterActualChargeSum(actualChargeSum);
        } else if (type == 1) {
            detailLog.setBeforeDiscountSum(actualChargeSum);
            detailLog.setDiscountSum(chargeDetail.getDiscount());
            detailLog.setAfterDiscountSum(actualChargeSum - chargeDetail.getDiscount());
        }
        chargeCustomerChargeDetailLogMapper.insert(detailLog);
    }

    @Override
    @ReadDataSource
    public PageInfo<ChargeCustomerChargeDetailLog> listPaymentLog(Map<String, Object> map) {
        JSONObject voObject = JSONObject.parseObject(JSON.toJSONString(map.get("searchVo1")));
        SearchVo searchVo = JSONObject.toJavaObject(voObject, SearchVo.class);
        Long organizationId = LoginDataHelper.getOrgId();
        Long enterpriseId = LoginDataHelper.getEnterpriseId();
        searchVo.setEnterpriseId(enterpriseId);
        searchVo.setOrganizationId(organizationId);
        map.put("searchVo1", searchVo);
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeCustomerChargeDetailLog> list = chargeCustomerChargeDetailLogMapper.listPaymentLog(map);
        PageInfo<ChargeCustomerChargeDetailLog> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public Boolean editBatch(List<PaymentVo> list) {
        for (PaymentVo paymentVo : list) {
            boolean edit = edit(paymentVo);
            if (!edit) return false;
        }
        return true;
    }

    @ReadDataSource
    @Override
    public ChargeCustomerChargeDetail getTotal(SearchVo searchVo, Map<String, Object> columnMap) throws Exception {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        if (columnMap != null) {
            List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
            columnList = columnList.stream().filter(column->"number".equals(column.getResourcecolumnXtype())).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(columnList)) {
                List<Long> houseIdList = new ArrayList<>();
                houseIdList.add(searchVo.getHouseId());
                List<OwnerHouseBaseInfo> houseResult = iOwnerRemoteService.listAllChildNode(searchVo.getHouseId(), Arrays.asList("6", "7", "8", "9")).getResultData();
                if (!CollectionUtils.isEmpty(houseResult)) {
                    List<Long> houseIds = houseResult.stream().map(house -> house.getHouseId()).collect(Collectors.toList());
                    houseIdList.addAll(houseIds);
                }
                searchVo.setHouseIds(houseIdList);
                List<ChargeCustomerChargeDetail> list = chargeCustomerChargeDetailMapper.listPage(searchVo);
                String AllJson = JSONObject.toJSONString(list);
                String allTotalJson = CommonUtils.totalList(AllJson, columnList, ChargeCustomerChargeDetail.class);
                if (StringUtils.hasLength(allTotalJson)) {
                    ChargeCustomerChargeDetail detail = JSONObject.parseObject(allTotalJson, ChargeCustomerChargeDetail.class);
                    return detail;
                }
            }
        }
        return null;
    }
}
