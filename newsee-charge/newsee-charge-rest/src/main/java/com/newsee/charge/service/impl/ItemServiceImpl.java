package com.newsee.charge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.charge.controller.ItemController;
import com.newsee.charge.dao.*;
import com.newsee.charge.entity.*;
import com.newsee.charge.service.IGoodsTaxRateService;
import com.newsee.charge.service.IItemService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.vo.ItemTypeVo;
import com.newsee.charge.vo.ItemVo;
import com.newsee.common.entity.NsSossEnterprise;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ItemServiceImpl implements IItemService {

    @Autowired
    private ChargeChargeItemMapper chargeChargeitemMapper;

    @Autowired
    private ChargeChargeItemTypeMapper chargeChargeitemtypeMapper;

    @Autowired
    private ChargeChargeItemGoodsTaxMapper chargeChargeItemGoodsTaxMapper;

    @Autowired
    private ChargeGoodsTaxRateMapper chargeGoodsTaxRateMapper;

    @Autowired
    private ISystemRemoteService systemRemoteService;

    @Autowired
    private ChargeGoodsTaxMapper chargeGoodsTaxMapper;

    @Autowired
    private IGoodsTaxRateService goodsTaxRateService;


    private static final Logger logger = LoggerFactory.getLogger(ItemServiceImpl.class);

    /**
     * 获取收费科目列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    @ReadDataSource
    public PageInfo<ChargeChargeItem> listPage(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeChargeItem> list = chargeChargeitemMapper.listPage(searchVo);
        //上级科目名称  费用类型等select的值
        for (ChargeChargeItem chargeChargeItem : list) {
            ChargeChargeItem chargeChargeitemtemp = chargeChargeitemMapper.selectById(chargeChargeItem.getParentId());
            if (!Objects.isNull(chargeChargeitemtemp)) {
                chargeChargeItem.setHighLevelSubjectName(chargeChargeItem.getParentId() != -1L ? chargeChargeitemtemp.getChargeItemName() : "");
            }
        }
        PageInfo<ChargeChargeItem> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @ReadDataSource
    public List<ChargeChargeItem> listPageALL(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        List<ChargeChargeItem> list = chargeChargeitemMapper.listPage(searchVo);

        return list;
    }

    public String getDictionaryItemName(String dictionaryDictype, String dictionaryDdcode, String code) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        map.put("dictionaryDictype", dictionaryDictype);
        map.put("dictionaryDdcode", dictionaryDdcode);
        map.put("code", code);
        RestResult<String> result = systemRemoteService.findDictionaryName(map);
        return result.getResultData();
    }

    /**
     * 获取收费科目详情
     *
     * @param id 主键id
     * @return
     */
    @ReadDataSource
    public ItemVo detail(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        ItemVo vo = new ItemVo();
        ChargeChargeItem chargeChargeitem = chargeChargeitemMapper.selectById(id);
        //如果查询出了数据，将数据拷贝到vo中
        if (!Objects.isNull(chargeChargeitem)) {
            BeanUtils.copyProperties(chargeChargeitem, vo);
        }
        //获取相关税目编码
        if (chargeChargeitem.getGoodsTaxId() != null) {
            ChargeGoodsTax chargeGoodsTax = chargeGoodsTaxMapper.selectById(chargeChargeitem.getGoodsTaxId());
            if (chargeGoodsTax != null) {
                vo.setGoodsTaxId(chargeGoodsTax.getId());
                vo.setGoodsTaxCode(chargeGoodsTax.getGoodsTaxNo());
                vo.setGoodsTaxName(chargeGoodsTax.getGoodsTaxName());
            }
        }
//        List<ChargeChargeItemGoodsTax> taxList = chargeChargeItemGoodsTaxMapper.selectByItemId(id);
//        vo.setTaxList(taxList);
        return vo;
    }

    /**
     * 编辑收费科目详情
     *
     * @return boolean 编辑成功与否
     */
    @WriteDataSource
    public boolean edit(ItemVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        logger.info("===↓↓↓↓↓===vo.getUpdateTime()vo.getUpdateTime()vo.getUpdateTime()vo.getUpdateTime()===↓↓↓↓↓===" + vo.getUpdateTime());
        logger.info("===↓↓↓↓↓===vo.getUpdateTime()vo.getUpdateTime()vo.getUpdateTime()vo.getUpdateTime()===↓↓↓↓↓===" + vo.getUpdateTime());
        logger.info("===↓↓↓↓↓===vo.getUpdateTime()vo.getUpdateTime()vo.getUpdateTime()vo.getUpdateTime()===↓↓↓↓↓===" + vo.getUpdateTime());
        logger.info("===↓↓↓↓↓===vo.getUpdateTime()vo.getUpdateTime()vo.getUpdateTime()vo.getUpdateTime()===↓↓↓↓↓===" + vo.getUpdateTime());
        ChargeChargeItem chargeChargeitem = new ChargeChargeItem();
        BeanUtils.copyProperties(vo, chargeChargeitem);
        int countchargeChargeitem = chargeChargeitemMapper.updateById(chargeChargeitem);
        if (countchargeChargeitem == 0) {
            return false;
        }
		/*ChargeChargeItemType chargeChargeitemtype = new ChargeChargeItemType();
		BeanUtils.copyProperties(vo, chargeChargeitemtype);
		int countchargeChargeitemtype = chargeChargeitemtypeMapper.updateById(chargeChargeitemtype);
		if(countchargeChargeitemtype == 0 ){
			return false;
		}*/
        //删除相关的税目编码后重新插入
		/*chargeChargeItemGoodsTaxMapper.deleteByItemId(chargeChargeitemtype.getId());
		if(!CollectionUtils.isEmpty(vo.getTaxList())){
			chargeChargeItemGoodsTaxMapper.insertBatch(vo.getTaxList());
		}*/
        return true;
    }

    /**
     * 新增收费科目
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    public boolean add(ItemVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeChargeItem chargeChargeitem = new ChargeChargeItem();
        BeanUtils.copyProperties(vo, chargeChargeitem);
        int countchargeChargeitem = chargeChargeitemMapper.insert(chargeChargeitem);
        if (countchargeChargeitem == 0) {
            return false;
        }
        if (!CollectionUtils.isEmpty(vo.getTaxList())) {
            chargeChargeItemGoodsTaxMapper.insertBatch(vo.getTaxList());
        }
        Long orgId = LoginDataHelper.getOrgId();
        //新增税率
        goodsTaxRateService.initGoodsTaxRate(chargeChargeitem,orgId, 0L);
        return true;
    }

    /**
     * 根据主键删除收费科目
     *
     * @param id 主键id
     * @return
     */
    @WriteDataSource
    public boolean delete(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        int countchargeChargeitem = chargeChargeitemMapper.deleteById(id);
        if (countchargeChargeitem == 0) {
            return false;
        }
        //删除相关税目编码
        chargeChargeItemGoodsTaxMapper.deleteByItemId(id);
        //删除相关税率
        Map<String, Object> map = Maps.newHashMap();
        map.put("chargeItemId", id);
        map.put("enterpriseId", LoginDataHelper.getEnterpriseId());
        chargeGoodsTaxRateMapper.deleteByParam(map);
        return true;
    }

    /**
     * 根据主键批量删除收费科目
     *
     * @param ids
     * @return
     */
    @WriteDataSource
    @Override
    public Integer deleteBatch(List<Long> ids) {
        if (Objects.isNull(ids)) {
            return 0;
        }
        int countchargeChargeitem = chargeChargeitemMapper.deleteBatch(ids);
        /*chargeChargeItemGoodsTaxMapper.deleteByItemIdBatch(ids);*/
        //删除相关税率
        ids.forEach(id -> {
            //删除相关税率
            Map<String, Object> map = Maps.newHashMap();
            map.put("chargeItemId", id);
            map.put("enterpriseId", LoginDataHelper.getEnterpriseId());
            chargeGoodsTaxRateMapper.deleteByParam(map);
        });
        return countchargeChargeitem;
    }

    /**
     * 新增收费科目
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    public boolean addItemType(ItemTypeVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeChargeItemType itemType = new ChargeChargeItemType();
        BeanUtils.copyProperties(vo, itemType);
        int countchargeChargeitemType = chargeChargeitemtypeMapper.insert(itemType);
        if (countchargeChargeitemType == 0) {
            return false;
        }
        return true;
    }

    /**
     * 新增收费科目分类
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    public boolean editItemType(ItemTypeVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeChargeItemType itemType = new ChargeChargeItemType();
        BeanUtils.copyProperties(vo, itemType);
        int countchargeChargeitemType = chargeChargeitemtypeMapper.updateById(itemType);
        if (countchargeChargeitemType == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据主键删除收费科目
     *
     * @param id 主键id
     * @return
     */
    @WriteDataSource
    public boolean deleteItemType(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        //检查该收费科目类型下是否有收费科目
		/*List<ChargeChargeItem> list = chargeChargeitemMapper.selectByTypeId(id);
		if(!CollectionUtils.isEmpty(list)){
			return false;
		}*/
        int countchargeChargeitemtype = chargeChargeitemtypeMapper.deleteById(id);
        if (countchargeChargeitemtype == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据主键删除收费科目
     *
     * @return
     */
    @ReadDataSource
    public List<ChargeChargeItemType> listItemTypeTree(Long parentId) {
        List<ChargeChargeItemType> list = new ArrayList<ChargeChargeItemType>();
        if (Objects.isNull(parentId)) {
            return list;
        }
        list = chargeChargeitemtypeMapper.listItemTypeTree(parentId);
        return list;

    }

    /**
     * 检查itemcode是否存在
     *
     * @param chargeItemCode itemCode
     * @param id             主键id
     * @param enterpriseId   企业id
     * @return
     */
    @ReadDataSource
    public boolean checkItemCodeIsExist(String chargeItemCode, Long id, Long enterpriseId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("chargeItemCode", chargeItemCode);
        map.put("id", id);
        map.put("enterpriseId", enterpriseId);
        List<ChargeChargeItem> list = chargeChargeitemMapper.selectByCode(map);
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;
    }

    /**
     * 检查itemtypecode是否存在
     *
     * @param chargeItemTypeCode
     * @param id
     * @param enterpriseId
     * @return
     */
    @ReadDataSource
    public boolean checkItemTypeCodeIsExist(String chargeItemTypeCode, Long id, Long enterpriseId) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("chargeItemTypeCode", chargeItemTypeCode);
        map.put("id", id);
        map.put("enterpriseId", enterpriseId);
        List<ChargeChargeItemType> list = chargeChargeitemtypeMapper.selectByCode(map);
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        return true;
    }

    /**
     * 根据收费科目类型id获取收费科目列表
     *
     * @param id
     * @return
     */
    @ReadDataSource
    public List<ChargeChargeItem> listItemTree(Long id, Long enterPriseId) {
        List<ChargeChargeItem> list = new ArrayList<ChargeChargeItem>();
        HashMap<String, Object> param = new HashMap<>();
        param.put("id", id);
        param.put("enterpriseId", enterPriseId);
        list = chargeChargeitemMapper.selectByTypeId(param);
        if (!CollectionUtils.isEmpty(list)) {
            for (ChargeChargeItem chargeChargeItem : list) {
                HashMap<String, Object> unitMap = new HashMap<String, Object>();
                unitMap.put("dictionaryDictype", "charge");
                unitMap.put("dictionaryDdcode", "Unit");
                unitMap.put("code", chargeChargeItem.getUnit());
                RestResult<String> result = systemRemoteService.findDictionaryName(unitMap);
                String unitName = Objects.isNull(result) ? "" : result.getResultData();
                chargeChargeItem.setUnitName(unitName);
            }
        }
        return list;
    }

    @Override
    @ReadDataSource
    public ChargeChargeItem findById(Long Id) {
        ChargeChargeItem chargeChargeitem = chargeChargeitemMapper.selectById(Id);
        return chargeChargeitem;
    }

    @Override
    @ReadDataSource
    public List<ChargeChargeItem> selectByCodeOrName(Map<String, Object> map) {
        List<ChargeChargeItem> list = chargeChargeitemMapper.selectByCodeOrName(map);
        return list;
    }

    @Override
    @ReadDataSource
    public Map<String, Object> listItemTreeForm(Long enterpriseId, Long id) {
        Map<String, Object> param = Maps.newHashMap();
        param.put("id", id);
        param.put("enterpriseId", enterpriseId);
        List<ChargeChargeItem> chargeChargeItems = chargeChargeitemMapper.selectItemTreeForm(param);
        if (!CollectionUtils.isEmpty(chargeChargeItems)) {
            chargeChargeItems.forEach(item -> {
                param.put("id", item.getId());
                List<ChargeChargeItem> childItemList = chargeChargeitemMapper.selectItemTreeForm(param);
                if (!CollectionUtils.isEmpty(childItemList)) {
                    item.setIsHasChild(true);
                } else {
                    item.setIsHasChild(false);
                }
            });
        }
        Map<String, Object> result = Maps.newHashMap();
        if (id == 0) {
            result.put("level", 2);
            List<ChargeChargeItem> itemList = new ArrayList<>();
            ChargeChargeItem rootItem = new ChargeChargeItem();
            String companyName = "";
            NsSossEnterprise enterprise = LoginDataHelper.getNsPlatformEnterprise();
            if (enterprise != null) {
                companyName = enterprise.getName();
            }
            rootItem.setId(-1L);
            rootItem.setChargeItemName(companyName);
            rootItem.setChargeItemTypeId(-1L);
            rootItem.setChargeItemShortName(companyName);
            rootItem.setIsHasChild((!Objects.isNull(chargeChargeItems) && chargeChargeItems.size() > 0) ? Boolean.TRUE : Boolean.FALSE);
            rootItem.setChildList(chargeChargeItems);
            itemList.add(rootItem);
            result.put("itemList", itemList);
        } else {
            result.put("level", 1);
            result.put("itemList", chargeChargeItems);
        }
        return result;
    }

    @Override
    @ReadDataSource
    public List<ChargeChargeItem> findChildSubject(Long id) {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        List<ChargeChargeItem> items = chargeChargeitemMapper.selectByTypeId(hashMap);
        return items;
    }

    @Override
    @ReadDataSource
    public List<Long> findChildALL(Long id) {
        List<Long> ids = new ArrayList<Long>();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("id", id);
        List<ChargeChargeItem> items = chargeChargeitemMapper.findPath(hashMap);
        if (!Objects.isNull(items) && items.size() > 0) {
            for (ChargeChargeItem chargeChargeItem : items) {
                ids.add(chargeChargeItem.getId());
            }
        }
        ids.add(id);
        return ids;
    }

    @Override
    public Integer clearTaxRate(List<Long> ids) {
        if (Objects.isNull(ids)) {
            return 0;
        }
        int countchargeChargeitem = chargeChargeitemMapper.clearTaxRate(ids);
        /*chargeChargeItemGoodsTaxMapper.deleteByItemIdBatch(ids);*/
        //删除相关税率
        ids.forEach(id -> {
            //删除相关税率
            Map<String, Object> map = Maps.newHashMap();
            map.put("chargeItemId", id);
            map.put("enterpriseId", LoginDataHelper.getEnterpriseId());
            chargeGoodsTaxRateMapper.deleteByParam(map);
        });
        return countchargeChargeitem;
    }
}
