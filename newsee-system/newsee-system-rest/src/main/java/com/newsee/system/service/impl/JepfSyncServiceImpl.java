package com.newsee.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.dao.NsCoreFuncinfoMapper;
import com.newsee.system.dao.NsCoreMenuMapper;
import com.newsee.system.dao.NsCoreResourcebuttonMapper;
import com.newsee.system.dao.NsCoreResourcecolumnMapper;
import com.newsee.system.dao.NsCoreResourcefieldMapper;
import com.newsee.system.dao.NsSystemOrganizationMapper;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.entity.NsCoreMenu;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.entity.NsCoreResourcecolumn;
import com.newsee.system.entity.NsCoreResourcefield;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.service.IJepfSyncService;
import com.newsee.system.vo.JepfVo;

@Service
public class JepfSyncServiceImpl implements IJepfSyncService{
	
	private static final Logger logger = LoggerFactory.getLogger(JepfSyncServiceImpl.class);
	
	@Autowired
	private NsCoreMenuMapper nsCoreMenuMapper;
	
	@Autowired
	private NsCoreFuncinfoMapper nsCoreFuncinfoMapper;
	
	@Autowired 
	private NsCoreResourcebuttonMapper nsCoreResourcebuttonMapper;
	
	@Autowired
	private NsCoreResourcecolumnMapper nsCoreResourcecolumnMapper;
	
	@Autowired
	private NsCoreResourcefieldMapper nsCoreResourcefieldMapper;
	
	@Autowired
	private NsSystemOrganizationMapper nsSystemOrganizationMapper;
	
	@Autowired
	private RedisUtil redisUtil;
	
	
	/**
	 * 从JEPF同步menu数据
	 * @param menus
	 * @return
	 */
	public Boolean syncMenu(List<NsCoreMenu> menus){
		if(CollectionUtils.isEmpty(menus)){
			return false;
		}
		//查看menu是否存在，存在则更新，不存在则插入
		int count = 0;
		for(NsCoreMenu menu :menus){
			NsCoreMenu menuInDb = nsCoreMenuMapper.selectByMenuIdAndOrgId(menu);
			int countTmp = 0;
			if(Objects.isNull(menuInDb)){
				countTmp = nsCoreMenuMapper.insert(menu);
			}else{
				countTmp = nsCoreMenuMapper.updateByMenuIdAndOrgId(menu);
			}
			count = count + countTmp; 
		}
		return count == menus.size();
	}
	
	/**
	 * 从JEPF中同步funcinfo数据
	 * @param funcinfos
	 * @return
	 */
	public Boolean syncFuncinfo(List<NsCoreFuncinfo> funcinfos){
		if(CollectionUtils.isEmpty(funcinfos)){
			return false;
		}
		int count = 0;
		for(NsCoreFuncinfo funcInfo : funcinfos){
		    Map<String, Object> paramMap = new HashMap<>();
	        paramMap.put("enterpriseId", funcInfo.getEnterpriseId());
	        paramMap.put("organizationId", funcInfo.getOrganizationId());
	        paramMap.put("jeCoreFuncinfoId", funcInfo.getJeCoreFuncinfoId());
			NsCoreFuncinfo funcInfoInDb = nsCoreFuncinfoMapper.selectByOrgIdAndFuncId(paramMap);
			int countTmp = 0;
			if(Objects.isNull(funcInfoInDb)){
				countTmp = nsCoreFuncinfoMapper.insert(funcInfo);
			}else{
				countTmp = nsCoreFuncinfoMapper.updateByOrgIdAndFuncId(funcInfo);
			}
			count = count + countTmp;
		}
		return count == funcinfos.size();
	}
	
	/**
	 * 从JEPF中同步button数据
	 * @param buttons
	 * @return
	 */
	public Boolean syncResourceButton(List<NsCoreResourcebutton> buttons){
		if(CollectionUtils.isEmpty(buttons)){
			return false;
		}
		int count = 0;
		//根据企业id和组织id删除所有的button
		nsCoreResourcebuttonMapper.deleteByOrgId(buttons.get(0));
		count = nsCoreResourcebuttonMapper.insertBatch(buttons);
//		for(NsCoreResourcebutton button : buttons){
//			NsCoreResourcebutton buttonInDb = nsCoreResourcebuttonMapper.selectByOrgIdAndButtonId(button);
//			int countTmp = 0;
//			if(Objects.isNull(buttonInDb)){
//				countTmp = nsCoreResourcebuttonMapper.insert(button);
//			}else{
//				countTmp = nsCoreResourcebuttonMapper.updateByOrgIdAndButtonId(button);
//			}
//			count = count + countTmp;
//		}
		return count == buttons.size();
	}
	
	/**
	 * 从JEPF中同步列头数据
	 * @param colums
	 * @return
	 */
	public Boolean syncResourceCloumn(List<NsCoreResourcecolumn> columns){
		if(CollectionUtils.isEmpty(columns)){
			return false;
		}
		int count = 0;
		//根据企业id和组织id删除所有的cloumn
		nsCoreResourcecolumnMapper.deleteByOrgId(columns.get(0));
		count = nsCoreResourcecolumnMapper.insertBatch(columns);
//		for(NsCoreResourcecolumn column : columns){
//			NsCoreResourcecolumn columnInDb = nsCoreResourcecolumnMapper.selectByOrgIdAndColumnId(column);
//			int countTmp = 0;
//			if(Objects.isNull(columnInDb)){
//				countTmp = nsCoreResourcecolumnMapper.insert(column);
//			}else{
//				countTmp = nsCoreResourcecolumnMapper.updateByOrgIdAndColumnId(column);
//			}
//			count = count + countTmp;
//		}
		return count == columns.size();
	}
	
	/**
	 * 从JEPF中同步表单数据
	 * @param fields
	 * @return
	 */
	public Boolean syncResourceField(List<NsCoreResourcefield> fields){
		if(CollectionUtils.isEmpty(fields)){
			return false;
		}
		int count = 0;
		//根据企业id和组织id删除field
		nsCoreResourcefieldMapper.deleteByOrgId(fields.get(0));
		count = nsCoreResourcefieldMapper.insertBatch(fields);
//		for(NsCoreResourcefield field : fields){
//			NsCoreResourcefield fieldInDb = nsCoreResourcefieldMapper.selectByOrgIdAndFieldId(field);
//			int countTmp = 0;
//			if(Objects.isNull(fieldInDb)){
//				countTmp = nsCoreResourcefieldMapper.insert(field);
//			}else{
//				countTmp = nsCoreResourcefieldMapper.updateByOrgIdAndFieldId(field);
//			}
//			count = count + countTmp;
//		}
		return count == fields.size();
	}
	
	/**
	 * 从JEPF中同步表单数据，先删除再插入
	 * @param fields
	 * @return
	 */
	@Transactional 
	public Boolean syncAll(JepfVo jepfVo){
		logger.info("=======↓↓↓↓↓=======开始将jepf数据插入到system库中=======↓↓↓↓↓=======");
		Long startTime = System.currentTimeMillis();
		Long costTime = System.currentTimeMillis();
		if(CollectionUtils.isEmpty(jepfVo.getMenus()) || CollectionUtils.isEmpty(jepfVo.getFuncinfos())){
			return false;
		}
		//同步menu
		Long enterpriseId = jepfVo.getMenus().get(0).getEnterpriseId();
		Long organizationId = jepfVo.getMenus().get(0).getOrganizationId();
		/*logger.info("=======准备操作从system库中的数据=======");
		logger.info("=======enterpriseId:" + enterpriseId.toString() + "|organizationId:"+organizationId.toString()+"=======");
		logger.info("=======↓↓↓↓↓=======正在同步menu数据，1.根据organzationId和enterpriseId删除已经存在的menu信息=======↓↓↓↓↓=======");*/
		Long menuStartTime = System.currentTimeMillis();
		int delMenuSize = nsCoreMenuMapper.deleteByOrgId(jepfVo.getMenus().get(0));
		int insMenuSize = nsCoreMenuMapper.insertBatch(jepfVo.getMenus());
		costTime = System.currentTimeMillis() - menuStartTime;
		/*logger.info("=======↑↑↑↑↑=======menu数据同步成功，删除了"+delMenuSize+"条，插入了"+ insMenuSize +"条，耗时:"+ costTime +"=======↑↑↑↑=======");
		//同步funcinfo
		logger.info("=======↓↓↓↓↓=======正在同步func数据，1.根据organzationId和enterpriseId删除已经存在的func信息=======↓↓↓↓↓=======");*/
		Long funcStartTime = System.currentTimeMillis();
		int delFuncSize = nsCoreFuncinfoMapper.deleteByOrgId(jepfVo.getFuncinfos().get(0));
		int insFuncSize = nsCoreFuncinfoMapper.insertBatch(jepfVo.getFuncinfos());
		costTime = System.currentTimeMillis() - funcStartTime;
		/*logger.info("=======↑↑↑↑↑=======func数据同步成功，删除了"+delFuncSize+"条，插入了"+ insFuncSize +"条，耗时:"+costTime+"ms=======↑↑↑↑=======");*/
		//同步button
		if(!CollectionUtils.isEmpty(jepfVo.getButtons())){
			Long butonStartTime = System.currentTimeMillis();
			/*logger.info("=======↓↓↓↓↓=======正在同步button数据，1.根据organzationId和enterpriseId删除已经存在的button信息=======↓↓↓↓↓=======");*/
			int delButtonSize = nsCoreResourcebuttonMapper.deleteByOrgId(jepfVo.getButtons().get(0));
			int insButtonSize = nsCoreResourcebuttonMapper.insertBatch(jepfVo.getButtons());
			costTime = System.currentTimeMillis() - butonStartTime;
			/*logger.info("=======↑↑↑↑↑=======buttonc数据同步成功，删除了"+delButtonSize+"条，插入了"+ insButtonSize +"条，耗时:"+costTime+"ms=======↑↑↑↑=======");*/
		}
		//同步column
		if(!CollectionUtils.isEmpty(jepfVo.getColumns())){
			Long columnStartTime = System.currentTimeMillis();
			/*logger.info("=======↓↓↓↓↓=======正在同步column数据，1.根据organzationId和enterpriseId删除已经存在的column信息=======↓↓↓↓↓=======");*/
			int delColumnSize = nsCoreResourcecolumnMapper.deleteByOrgId(jepfVo.getColumns().get(0));
			int insColumnSize = nsCoreResourcecolumnMapper.insertBatch(jepfVo.getColumns());
			costTime = System.currentTimeMillis() - columnStartTime;
			logger.info("=======↑↑↑↑↑=======column数据同步成功，删除了"+delColumnSize+"条，插入了"+ insColumnSize +"条，耗时:"+costTime+"ms=======↑↑↑↑=======");
		}
		//同步field
		if(!CollectionUtils.isEmpty(jepfVo.getFields())){
			Long fieldStartTime = System.currentTimeMillis();
			logger.info("=======↓↓↓↓↓=======正在同步field数据，1.根据organzationId和enterpriseId删除已经存在的field信息=======↓↓↓↓↓=======");
			int delFieldSize = nsCoreResourcefieldMapper.deleteByOrgId(jepfVo.getFields().get(0));
			int insFieldSize = nsCoreResourcefieldMapper.insertBatch(jepfVo.getFields());
			costTime = System.currentTimeMillis() - fieldStartTime;
			logger.info("=======↑↑↑↑↑=======field数据同步成功，删除了"+delFieldSize+"条，插入了"+ insFieldSize +"条，耗时:" + costTime + "=======↑↑↑↑=======");
		}
		//同步后删除缓存中的key
		logger.info("=======↓↓↓↓↓=======正在清除redis中的缓存信息=======↓↓↓↓↓=======");
		Long redisClearTime = System.currentTimeMillis();
		redisUtil.deleteByPrefix(RedisKeysConstants.REDIS_FUNCTION_FIELDS_PREFIX + "_" + enterpriseId.toString() + "_" + organizationId.toString());
		redisUtil.deleteByPrefix(RedisKeysConstants.REDIS_FUNCTION_INFO_PREFIX + "_" + enterpriseId.toString() + "_" + organizationId.toString());
		redisUtil.deleteByPrefix(RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + enterpriseId.toString() + "_" + organizationId.toString());
		redisUtil.deleteByPrefix(RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + enterpriseId.toString());
		costTime = System.currentTimeMillis() - redisClearTime;
		logger.info("=======↑↑↑↑↑=======redis缓存信息清除成功,耗时:"+costTime+"ms=======↑↑↑↑=======");
		costTime = System.currentTimeMillis() - startTime;
		logger.info("=======↑↑↑↑↑=======将jepf数据同步到system库中完成,耗时:"+costTime+"ms=======↑↑↑↑=======");
		return true;
	}
	
	/**
	 * 获取所有子公司以上级别的公司
	 * @return
	 */
	public List<NsSystemOrganization> getAllCompanyLevelOrg(String orgName){
		Map<String, String> map = Maps.newHashMap();
		map.put("orgName", orgName);
		return nsSystemOrganizationMapper.selectAllCompanyLevelOrg(map);
	}
	

}
