/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.charge.entity.ChargeCalcLog;
import com.newsee.charge.vo.PaymentCalcVoImportExcel;
import com.newsee.common.vo.SearchVo;
import com.newsee.charge.entity.ChargeChargeItem;
import com.newsee.charge.entity.ChargeCustomerChargeCalcTask;
import com.newsee.charge.vo.PaymentCalcVo;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface IPaymentCalcService {

    /**
     * 获取应收款计算列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    PageInfo<ChargeCustomerChargeCalcTask> listPage(SearchVo searchVo);

    PageInfo<ChargeCustomerChargeCalcTask> listPlanPage(SearchVo searchVo);

    List<ChargeCustomerChargeCalcTask> listPageAll(SearchVo searchVo);
    
    
    public void calculateCost(Long planId);

    /**
     * 获取应收款计算详情
     *
     * @param id 主键id
     * @return
     */
    PaymentCalcVo detail(Long id);


    public List<ChargeCustomerChargeCalcTask> listPageALL(SearchVo searchVo);

    public Integer checkPayment(Map<String, Object> map);

    public Integer planManager(Map<String, Object> map);

    public List<ChargeChargeItem> getChargeItemList(Long houseId);

    /**
     * 编辑应收款计算详情
     *
     * @return boolean 编辑成功与否
     */
    boolean edit(PaymentCalcVo vo);

    /**
     * 新增应收款计算
     *
     * @return boolean 新增成功与否
     */
    boolean add(PaymentCalcVo vo);

    /**
     * 根据主键删除应收款计算
     *
     * @param id 主键id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据主键批量删除应收款计算
     *
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Long> ids);

    /**
     * 批量导入任务
     *
     * @param file          Excel模板
     * @param paymentCalcVo 参数
     * @return
     */
    String importPaymentCalc(MultipartFile file, PaymentCalcVoImportExcel paymentCalcVo);

    /**
     * 下载Excel模板
     *  @param precinctId       项目id
     * @param itemId           科目id
     * @param standardId
     * @param shouldChargeDate 应收款日期
     * @param startDate        计费开始时间
     * @param endDate          计费结束时间
     * @param response
     */
    void downloadExcelTemplate(Long precinctId, Long itemId, Long standardId, Date shouldChargeDate, Date startDate, Date endDate, HttpServletResponse response);


    boolean deleteTaskDetails(Long id);

    /**
     * 日志列表
     * @param searchVo
     * @return
     */
    PageInfo<ChargeCalcLog> listPageChargeCalcLog(SearchVo searchVo);

    /**
     * 任务详情
     * @param id
     * @return
     */
    ChargeCustomerChargeCalcTask detailChargeCalcTask(Long id);

	Integer checkPaymentCalc(Map<String, Object> map);
}
