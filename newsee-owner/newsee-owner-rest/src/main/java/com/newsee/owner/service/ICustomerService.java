package com.newsee.owner.service;

import java.util.List;
import java.util.Map;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.owner.entity.OwnerCustomerBaseInfo;
import com.newsee.owner.entity.OwnerCustomerResult;
import com.newsee.owner.vo.CustomerFamilyVo;
import com.newsee.owner.vo.CustomerVo;
import com.newsee.owner.vo.FamilyInfoVo;
import com.newsee.owner.vo.OwnerBankAccountVo;
import com.newsee.system.vo.NsCoreDictionaryVo;

public interface ICustomerService {

	/**
	 * 
	 * @Title: listPage
	 * @Description: 获取客户列表
	 * @param @param searchVo
	 * @param @return 设定文件
	 * @return PageInfo<OwnerCustomerResult> 返回类型
	 * @throws Exception
	 * @throws @author wangrenjie
	 */
	public PageInfo<OwnerCustomerResult> listPage(SearchVo searchVo, Map<String, Object> columnMap, boolean pageFlag) throws Exception;

	public OwnerCustomerResult getTotal(SearchVo searchVo, Map<String, Object> columnMap) throws Exception;

	/**
	 * 
	 * @Title: add
	 * @Description: 新增客户
	 * @param @param customerVo
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws @author wangrenjie
	 */
	public Long add(CustomerVo customerVo, Map<String, NsCoreDictionaryVo> dicMap);

	/**
	 * 
	 * @Title: edit
	 * @Description: 编辑客户
	 * @param @param customerVo
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws @author wangrenjie
	 */
	public int edit(CustomerVo customerVo, Map<String, NsCoreDictionaryVo> dicMap) throws Exception;

	public int delete(List<CustomerVo> customerVoList) throws Exception;

	/**
	 * 
	 * @Title: detail
	 * @Description: 查看客户详情
	 * @param @param ownerId
	 * @param @return 设定文件
	 * @return CustomerVo 返回类型
	 * @throws @author wangrenjie
	 */
	public CustomerVo detail(Long ownerId);

	public Map<String, Object> deleteAllCustomerBySearch(SearchVo searchVo, Long userId, String userName) throws Exception;

	public Map<String, Object> deleteCustomerBatch(List<Long> customerIdList, Long userId, String userName);

	public List<CustomerVo> listCustomerForSearch(String customerName, Long organizationId, Integer isDeveloper, Long ownerId);

	/**
	 * 
	 * @Title: addFamily
	 * @Description: 保存客户家庭关系
	 * @param @param customerFamilyVo
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws @author wangrenjie
	 */
	public int addFamily(CustomerFamilyVo customerFamilyVo, Map<String, NsCoreDictionaryVo> dicMap) throws Exception;

	/**
	 * 
	 * @Title: deleteFamily
	 * @Description: 删除家庭成员
	 * @param @param ownerId 客户ID
	 * @param @param relationOwnerId 客户关联成员ID
	 * @param @param userId
	 * @param @return 设定文件
	 * @return int 返回类型
	 * @throws @author wangrenjie
	 */
	public int deleteFamily(Long ownerId, Long relationOwnerId, Long userId, String userName) throws Exception;

	/**
	 * 
	 * @Title: listFamilyByOwnerId
	 * @Description: 获取客户家庭成员
	 * @param @param ownerId
	 * @param @return 设定文件
	 * @return List<FamilyInfoVo> 返回类型
	 * @throws @author wangrenjie
	 */
	public List<FamilyInfoVo> listFamilyByOwnerId(Long ownerId);

	/**
	 * 
	 * @Title: addBank
	 * @Description: 保存客户银行账户
	 * @param ownerBankAccountVo
	 * @param userId
	 * @return int 返回类型
	 * @date 2017年11月13日 上午9:52:29
	 * @author wangrenjie
	 */
	public int addBank(OwnerBankAccountVo ownerBankAccountVo, Long userId, String userName);

	public OwnerBankAccountVo detailSingleBank(Long ownerBankAccountId);

	/**
	 * 
	 * @Title: listBankByOwnerId
	 * @Description: 获取客户银行账户
	 * @param ownerId
	 * @return List<OwnerBankAccountVo> 返回类型
	 * @date 2017年11月13日 上午10:16:13
	 * @author wangrenjie
	 */
	public List<OwnerBankAccountVo> listBankByOwnerId(Long ownerId);

	public int deleteBank(Long ownerId, Long ownerBankAccountId, Long userId, String userName);

	public List<CustomerVo> listAllCustomer(Long houseId);

	/**
	 * 根据项目Id和类型查询用户对象，如果没有租户则查询业主，如果没有业主则查询开发商，没有开发商返回空
	 * @param houseId
	 * @param ownerProperty 要查询的客户类型：0业主 1租户 2住户 3开发商
	 */
	public CustomerVo getCustomerByHouseIdAndProperty(Long houseId, String ownerProperty,String type);

	/**
	 * 搜索房产下面开发商信息
	 * @param houseId
	 * @param name
	 * @param enterpriseId
	 * @param orgId
	 * @return 用户列表
	 */
	public List<CustomerVo> searchDevelopersCustomer(Long houseId, String name, Long enterpriseId, Long orgId);

	/**
	 * 根据条件搜索用户
	 * @param houseId
	 * @param ownerProperty
	 * @param name
	 * @param ownerType
	 * @return
	 */
	List<OwnerCustomerBaseInfo> searchCustomer(Long houseId, String ownerProperty, String name, String ownerType);
}
