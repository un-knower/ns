package com.newsee.owner.dao;

import java.util.List;
import java.util.Map;

import com.newsee.owner.entity.OwnerCustomerBankAccount;

public interface OwnerCustomerBankAccountMapper {
    int deleteByPrimaryKey(Long ownerBankAccountId);

    int insert(OwnerCustomerBankAccount record);

    int insertSelective(OwnerCustomerBankAccount record);

    OwnerCustomerBankAccount selectByPrimaryKey(Long ownerBankAccountId);

    int updateByPrimaryKeySelective(OwnerCustomerBankAccount record);

    int updateByPrimaryKey(OwnerCustomerBankAccount record);
    /**
     * 
    * @Title: listBankByOwnerId 
    * @Description: 获取客户银行账户
    * @param ownerId
    * @return List<OwnerCustomerBankAccount>    返回类型 
    * @date  2017年11月13日 上午10:18:25
    * @author wangrenjie
     */
    List<OwnerCustomerBankAccount> listBankByOwnerId(Long ownerId);
    
    int deleteBank(Map<String, Object> map);
    int deleteBankByOwnerId(Map<String, Object> map);
}