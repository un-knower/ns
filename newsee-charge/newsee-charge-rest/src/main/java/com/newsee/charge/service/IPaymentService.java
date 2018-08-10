/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.charge.entity.ChargeCustomerChargeDetailLog;
import com.newsee.common.vo.SearchVo;
import com.newsee.charge.entity.ChargeCustomerChargeDetail;
import com.newsee.charge.vo.PaymentVo;

public interface IPaymentService {

    /**
     * 获取应收款列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    PageInfo<ChargeCustomerChargeDetail> listPage(SearchVo searchVo);

    /**
     * 获取应收款详情
     *
     * @param id 主键id
     * @return
     */
    PaymentVo detail(Long id);

    /**
     * 编辑应收款详情
     *
     * @return boolean 编辑成功与否
     */
    boolean edit(PaymentVo vo);

    /**
     * 新增应收款
     *
     * @return boolean 新增成功与否
     */
    boolean add(PaymentVo vo);

    /**
     * 根据主键删除应收款
     *
     * @param id 主键id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据主键批量删除应收款
     *
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Long> ids);

    /**
     * 应收款管理审核、反审核
     *
     * @param map
     * @return
     */
    Integer checkChargeDetail(Map<String, Object> map);

    /**
     * 应收款减免
     *
     * @param type 减免分类
     * @param chargeDetailList
     * @return
     */
    Integer discountChargeDetail(String type,List<ChargeCustomerChargeDetail> chargeDetailList);

    /**
     * 查询日志记录
     *
     * @param map
     * @return
     */
    PageInfo<ChargeCustomerChargeDetailLog> listPaymentLog(Map<String, Object> map);

    /**
     * 批量调整应收款
     * @param list
     * @return
     */
    Boolean editBatch(List<PaymentVo> list);

    /**
     * 合计行
     * @param searchVo
     * @param columnMap
     */
    ChargeCustomerChargeDetail getTotal(SearchVo searchVo, Map<String,Object> columnMap) throws Exception;
}
