package com.newsee.charge.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.common.rest.RestResult;
import com.newsee.system.vo.NsSystemOrganizationVo;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.parsing.FailFastProblemReporter;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.charge.dao.ChargeChargePaymentMethodMapper;
import com.newsee.charge.entity.ChargeChargePaymentMethod;
import com.newsee.charge.service.IPaymethodService;
import com.newsee.charge.vo.PaymethodVo;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;

@Service

public class PaymethodServiceImpl implements IPaymethodService {

    @Autowired
    private ChargeChargePaymentMethodMapper chargeChargepaymentmethodMapper;
    @Autowired
    private ISystemRemoteService iSystemRemoteService;

    /**
     * 获取支付方式列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    @ReadDataSource
    public PageInfo<ChargeChargePaymentMethod> listPage(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        Long organizationId = searchVo.getOrganizationId();
        List<NsSystemOrganizationVo> organizationVoList = iSystemRemoteService.listAllChildNode(organizationId, null).getResultData();
        if (!CollectionUtils.isEmpty(organizationVoList)){
            List<Long> organizationIdList = organizationVoList.stream().map(x -> x.getOrganizationId()).collect(Collectors.toList());
            searchVo.setOrganizationIdList(organizationIdList);
        }
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeChargePaymentMethod> list = chargeChargepaymentmethodMapper.listPage(searchVo);
        PageInfo<ChargeChargePaymentMethod> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    /**
     * 获取支付方式详情
     *
     * @param id 主键id
     * @return
     */
    @ReadDataSource
    public PaymethodVo detail(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        PaymethodVo vo = new PaymethodVo();
        ChargeChargePaymentMethod chargeChargepaymentmethod = chargeChargepaymentmethodMapper.selectById(id);
        // 如果查询出了数据，将数据拷贝到vo中
        if (!Objects.isNull(chargeChargepaymentmethod)) {
            BeanUtils.copyProperties(chargeChargepaymentmethod, vo);
        }
        return vo;
    }

    /**
     * 编辑支付方式详情
     *
     * @return boolean 编辑成功与否
     */
    @WriteDataSource
    public boolean edit(PaymethodVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeChargePaymentMethod chargeChargepaymentmethod = new ChargeChargePaymentMethod();
        BeanUtils.copyProperties(vo, chargeChargepaymentmethod);
        int countchargeChargepaymentmethod = chargeChargepaymentmethodMapper.updateById(chargeChargepaymentmethod);
        if (countchargeChargepaymentmethod == 0) {
            return false;
        }
        return true;
    }

    /**
     * 新增支付方式
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    public boolean add(PaymethodVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeChargePaymentMethod chargeChargepaymentmethod = new ChargeChargePaymentMethod();
        BeanUtils.copyProperties(vo, chargeChargepaymentmethod);
        int countchargeChargepaymentmethod = chargeChargepaymentmethodMapper.insert(chargeChargepaymentmethod);
        if (countchargeChargepaymentmethod == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据主键删除支付方式
     *
     * @param id 主键id
     * @return
     */
    @WriteDataSource
    public boolean delete(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        int countchargeChargepaymentmethod = chargeChargepaymentmethodMapper.deleteById(id);
        if (countchargeChargepaymentmethod == 0) {
            return false;
        }
        return true;
    }

    /**
     * 删除所有满足当前查询条件数据
     *
     * @param searchVo
     * @return
     */
    @WriteDataSource
    @Override
    public boolean deleteAll(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeChargePaymentMethod> list = chargeChargepaymentmethodMapper.listPage(searchVo);
        List<Long> ids = list.stream().map(x -> x.getId()).collect(Collectors.toList());
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        int num = chargeChargepaymentmethodMapper.deleteBatch(ids);
        if (num > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据主键批量删除支付方式
     *
     * @param ids
     * @return
     */
    @Override
    public boolean deleteBatch(List<Long> ids) {
        if (CollectionUtils.isEmpty(ids)) {
            return false;
        }
        int num = chargeChargepaymentmethodMapper.deleteBatch(ids);
        if (num > 0) {
            return true;
        }
        return false;
    }

    /**
     * 检查企业内是否有相同的支付方式code
     *
     * @param enterpriseId
     * @param id
     * @param methodCode
     * @return
     */
    @ReadDataSource
    public boolean checkCodeIsExists(Long enterpriseId, Long id, String methodCode) {
        //编辑的时候没有改变code则不做校验
        if (id != null) {
            ChargeChargePaymentMethod paymentMethod = chargeChargepaymentmethodMapper.selectById(id);
            if (methodCode.equals(paymentMethod.getMethodCode())) {
                return false;
            }
        }
        //开始校验是否有相同的
        Map<String, Object> map = Maps.newHashMap();
        map.put("enterpriseId", enterpriseId);
        map.put("methodCode", methodCode);
        List<ChargeChargePaymentMethod> list = chargeChargepaymentmethodMapper.selectByCode(map);
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;
    }

    /**
     * 启用或停用
     */
    @WriteDataSource
    @Override
    public boolean enablePaymethod(Long id) {
        ChargeChargePaymentMethod chargeChargePaymentMethod = chargeChargepaymentmethodMapper.selectById(id);
        if (chargeChargePaymentMethod != null) {
            ChargeChargePaymentMethod method = new ChargeChargePaymentMethod();
            method.setId(id);
            if ("0".equals(chargeChargePaymentMethod.getIsWork())) {
                method.setIsWork("1");
            } else {
                method.setIsWork("0");
            }
            int num = chargeChargepaymentmethodMapper.updateById(method);
            return num > 0;
        }
        return false;
    }
}
