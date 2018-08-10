/*
 * Copyright(c) 2017 杭州新视窗信息技术有限公司 All rights reserved.
 * 【收费宝】项目代码
 */
package com.newsee.charge.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.newsee.charge.entity.ChargeChargeItem;
import com.newsee.common.vo.SearchVo;
import com.newsee.charge.entity.ChargeGoodsTaxRate;
import com.newsee.charge.vo.GoodsTaxRateVo;
import com.newsee.owner.entity.OwnerHouseBaseInfo;

public interface IGoodsTaxRateService {

    /**
     * 获取税率列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    PageInfo<ChargeGoodsTaxRate> listPage(List<OwnerHouseBaseInfo> houseBaseInfoList, SearchVo searchVo);

    /**
     * 获取税率详情
     *
     * @param id 主键id
     * @return
     */
    GoodsTaxRateVo detail(Long id);

    /**
     * 编辑税率详情
     *
     * @return boolean 编辑成功与否
     */
    boolean edit(List<GoodsTaxRateVo> vos);

    /**
     * 新增税率
     *
     * @return boolean 新增成功与否
     */
    boolean add(GoodsTaxRateVo vo);

    /**
     * 根据主键删除税率
     *
     * @param id 主键id
     * @return
     */
    boolean delete(Long id);

    /**
     * 根据主键批量删除税率
     *
     * @param ids
     * @return
     */
    boolean deleteBatch(List<Long> ids);

    /**
     * 将组织中的收费科目新增和更新到表chargeGoodsTaxRate中
     *
     * @param item 收费科目
     * @param organizationId   组织id
     * @param precinctId       项目id
     */
    List<OwnerHouseBaseInfo> initGoodsTaxRate(ChargeChargeItem item, Long organizationId, Long precinctId);

    /**
     * 删除税率
     *
     * @param enterpriseId 企业id
     * @param chargeItemId 收费科目id
     * @param houseId      项目id
     * @return
     */
    boolean delete(Long enterpriseId, Long chargeItemId, Long houseId);

    /**
     * 修改税率的项目名称，当修改项目名称时，税率中的项目名称也需要跟着变动
     *
     * @param enterpriseId 企业id
     * @param houseId      项目id
     * @param houseName    项目名称
     * @return
     */
    boolean updatePrecinctName(Long enterpriseId, Long houseId, String houseName);

    /**
     * 查询税率是否设置过
     *
     * @param enterpriseId 企业id
     * @param chargeItemId 收费科目id
     * @param houseId      项目id
     * @return
     */
    ChargeGoodsTaxRate getChargeGoodsTaxRate(Long enterpriseId, Long chargeItemId, Long houseId);
}
