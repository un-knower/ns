/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.charge.entity.ChargeChargePaymentMethod;
import com.newsee.charge.vo.PaymethodVo;

public interface IPaymethodService {

    /**
     * 获取支付方式列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    PageInfo<ChargeChargePaymentMethod> listPage(SearchVo searchVo);

    /**
     * 获取支付方式详情
     *
     * @param id 主键id
     * @return
     */
    PaymethodVo detail(Long id);

    /**
     * 编辑支付方式详情
     *
     * @return boolean 编辑成功与否
     */
    boolean edit(PaymethodVo vo);

    /**
     * 新增支付方式
     *
     * @return boolean 新增成功与否
     */
    boolean add(PaymethodVo vo);

    /**
     * 根据主键删除支付方式
     *
     * @param id 主键id
     * @return
     */
    boolean delete(Long id);


    /**
     * 删除所有满足当前查询条件数据
     *
     * @param searchVo
     * @return
     */
    boolean deleteAll(SearchVo searchVo);

    /**
     * 根据主键批量删除支付方式
     *
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Long> ids);


    /**
     * 检查企业内是否有相同的支付方式code
     *
     * @param enterpriseId
     * @param id
     * @param methodCode
     * @return
     */
    boolean checkCodeIsExists(Long enterpriseId, Long id, String methodCode);

    /**
     * 停用或启用
     *
     * @param id
     * @return
     */
    boolean enablePaymethod(Long id);

}
