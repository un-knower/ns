package com.newsee.devplatform.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.newsee.common.utils.StringUtils;
import com.newsee.devplatform.constants.DevPlatformContants;
import com.newsee.devplatform.dao.JeCoreFuncinfoMapper;
import com.newsee.devplatform.dao.JeCoreMenuMapper;
import com.newsee.devplatform.dao.JeCoreResourcebuttonMapper;
import com.newsee.devplatform.dao.JeCoreResourcecolumnMapper;
import com.newsee.devplatform.dao.JeCoreResourcefieldMapper;
import com.newsee.devplatform.dao.SyncRecordMapper;
import com.newsee.devplatform.entity.JeCoreFuncinfo;
import com.newsee.devplatform.entity.JeCoreMenu;
import com.newsee.devplatform.entity.JeCoreResourcebutton;
import com.newsee.devplatform.entity.JeCoreResourcecolumn;
import com.newsee.devplatform.entity.JeCoreResourcefield;
import com.newsee.devplatform.entity.SyncRecord;
import com.newsee.devplatform.service.IJepfSyncService;
import com.newsee.devplatform.service.remote.ISystemJepfRemoteService;
import com.newsee.devplatform.vo.JepfSyncOrgVo;
import com.newsee.devplatform.vo.JepfSyncVo;
import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.entity.NsCoreMenu;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.entity.NsCoreResourcecolumn;
import com.newsee.system.entity.NsCoreResourcefield;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.vo.JepfVo;
import com.newsee.system.vo.NsSystemSuperAdmin;

import common.Logger;

@Service
public class JepfSyncServiceImpl implements IJepfSyncService{
	
	private static final Logger logger = Logger.getLogger(JepfSyncServiceImpl.class);
	
	@Autowired
	private SyncRecordMapper syncRecordMapper;
	
	@Autowired
	private JeCoreFuncinfoMapper jeCoreFuncinfoMapper;
	
	@Autowired
	private JeCoreMenuMapper jeCoreMenuMapper;
	
	@Autowired
	private JeCoreResourcebuttonMapper jeCoreResourcebuttonMapper;
	
	@Autowired
	private JeCoreResourcecolumnMapper jCoreResourcecolumnMapper;
	
	@Autowired
	private JeCoreResourcefieldMapper jeCoreResourcefieldMapper;
	
	@Autowired
	private ISystemJepfRemoteService systemJepfRemoteService;
	
	//@Autowired
	//private RedisUtil redisUtil;
	
	/**
	 * 同步原始数据
	 * @return
	 */
	public void syncOriginal(JepfSyncVo syncVo, SyncRecord record){
		Long startTime = System.currentTimeMillis();
		List<JepfSyncOrgVo> orgs = getSyncOrg(syncVo);
		JepfVo vo = new JepfVo();
		for(JepfSyncOrgVo org : orgs){
			//获取最近一次的同步记录
			//record.setEnterpriseId(org.getEnterpriseId());
			//record.setOrganizationId(org.getOrganizationId());
			//SyncRecord syncRecord = syncRecordMapper.selectLatestByOrg(record);
			//获取收费宝所有的菜单项目
			/*logger.info("=======↓↓↓↓↓=======开始获取jepf中的配置信息=======↓↓↓↓↓=======");*/
			Long startSelectTime = System.currentTimeMillis();
			//判断是否是运营系统注册
			List<JeCoreMenu> menuList = null;
			if(DevPlatformContants.NS_SOOS_NAME.equals(syncVo.getMenuName())){
				menuList = getMenuByMenuName(DevPlatformContants.NS_SOOS_NAME);
			}else{
				menuList = getMenuByMenuName(DevPlatformContants.NS_PAY_NAME);
			}
			if(CollectionUtils.isEmpty(menuList)){
				return;
			}
			List<JeCoreFuncinfo> funcInfoList = getFuncinfoByFuncodes(menuList);
			if(CollectionUtils.isEmpty(funcInfoList)){
				return;
			}
			List<JeCoreResourcebutton> buttons = null;
			List<JeCoreResourcecolumn> columns = null;
			List<JeCoreResourcefield> fields = null;
			Date latestSyncDate = null;
			//if(!Objects.isNull(syncRecord)){
				//latestSyncDate = syncRecord.getSyncTime();
				//latestSyncDate = null;
			//}
			//全量同步
			if(!CollectionUtils.isEmpty(funcInfoList)){
				buttons = getResourceButtonByFuncIds(funcInfoList, latestSyncDate);
				columns = getResourcrcolumnByFuncIds(funcInfoList, latestSyncDate);
				fields = getResourcrfieldByFuncIds(funcInfoList, latestSyncDate);
				//调用system的远程服务，插入到newsee-system库中
			}
			Long costSelect = System.currentTimeMillis()-startSelectTime;
			/*logger.info("=======↑↑↑↑↑=======获取jepf中的配置信息完成，menu数量:" +menuList.size() +"，func数量:"+funcInfoList.size() +
				"，button数量:"+buttons.size()+"，column数量:"+columns.size()+"，field数量:"+fields.size()+"耗时:"+costSelect+"ms=======↑↑↑↑↑=======");
			//处理成system需要的数据格式
			logger.info("=======↓↓↓↓↓=======正在组装jepf中的配置信息=======↓↓↓↓↓=======");*/
			startSelectTime = System.currentTimeMillis();
			List<NsCoreMenu> nsMenuList = new ArrayList<NsCoreMenu>();
			List<NsCoreFuncinfo> nsFuncInfo = new ArrayList<NsCoreFuncinfo>();
			List<NsCoreResourcebutton> nsbuttons = new ArrayList<NsCoreResourcebutton>();
			List<NsCoreResourcecolumn> nsColumns = new ArrayList<NsCoreResourcecolumn>();
			List<NsCoreResourcefield> nsFields = new ArrayList<NsCoreResourcefield>();
			menuList.forEach(menu -> {
				NsCoreMenu nsMenu = new NsCoreMenu();
				BeanUtils.copyProperties(menu, nsMenu);
				//默认启用该menu
				nsMenu.setMenuEnabled("1");
				nsMenu.setEnterpriseId(org.getEnterpriseId());
				nsMenu.setOrganizationId(org.getOrganizationId());
				nsMenuList.add(nsMenu);
			});
			//systemJepfRemoteService.syncMenus(nsMenuList);
			funcInfoList.forEach(func -> {
				NsCoreFuncinfo nsFuncinfo = new NsCoreFuncinfo();
				BeanUtils.copyProperties(func, nsFuncinfo);
				nsFuncinfo.setEnterpriseId(org.getEnterpriseId());
				nsFuncinfo.setOrganizationId(org.getOrganizationId());
				nsFuncInfo.add(nsFuncinfo);
			});
			//systemJepfRemoteService.syncFuncinfos(nsFuncInfo);
			if(!CollectionUtils.isEmpty(buttons)){
				buttons.forEach(button -> {
					NsCoreResourcebutton nsButton = new NsCoreResourcebutton();
					BeanUtils.copyProperties(button, nsButton);
					nsButton.setEnterpriseId(org.getEnterpriseId());
					nsButton.setOrganizationId(org.getOrganizationId());
					nsbuttons.add(nsButton);
				});
				//systemJepfRemoteService.syncButtons(nsbuttons);
			}
			if(!CollectionUtils.isEmpty(columns)){
				columns.forEach(column -> {
					NsCoreResourcecolumn nsColumn = new NsCoreResourcecolumn();
					BeanUtils.copyProperties(column, nsColumn);
					nsColumn.setResourcecolumnCode(StringUtils.underlineToHump(column.getResourcecolumnCode()));
					nsColumn.setResourcecolumnXtype(changeCloumnXtype(column.getResourcecolumnXtype()));
					nsColumn.setEnterpriseId(org.getEnterpriseId());
					nsColumn.setOrganizationId(org.getOrganizationId());
					nsColumns.add(nsColumn);
				});
				//systemJepfRemoteService.syncColumns(nsColumns);
			}
			if(!CollectionUtils.isEmpty(fields)){
				fields.forEach(field -> {
				    if (!field.getResourcefieldName().startsWith("展示字段")) {
				        NsCoreResourcefield nsField = new NsCoreResourcefield();
	                    BeanUtils.copyProperties(field, nsField);
	                    nsField.setResourcefieldCode(StringUtils.underlineToHump(field.getResourcefieldCode()));
	                    nsField.setResourcefieldGroupname(StringUtils.underlineToHump(field.getResourcefieldGroupname()));
	                    nsField.setEnterpriseId(org.getEnterpriseId());
	                    nsField.setOrganizationId(org.getOrganizationId());
	                    nsField.setResourcefieldInterpreter(StringUtils.isBlank(field.getResourcefieldInterpreter())?"0":field.getResourcefieldInterpreter());
	                    nsField.setResourcefieldXtype(changeFiledXtype(field.getResourcefieldXtype()));
	                    nsFields.add(nsField);
                    }
				});
				//systemJepfRemoteService.syncFields(nsFields);
			}
			vo.setMenus(nsMenuList);
			vo.setFuncinfos(nsFuncInfo);
			vo.setColumns(nsColumns);
			vo.setFields(nsFields);
			vo.setButtons(nsbuttons);
			Long costMake = System.currentTimeMillis()-startSelectTime;
			/*logger.info("=======↑↑↑↑↑=======组装jepf配置信息完成，耗时:" + costMake + "ms=======↑↑↑↑↑=======");
			logger.info("=======↓↓↓↓↓=======开始将jepf信息信息同步到system库中=======↓↓↓↓↓=======");*/
			startSelectTime = System.currentTimeMillis();
			systemJepfRemoteService.syncAll(vo);
			Long syncCost = System.currentTimeMillis() - startSelectTime;
			/*logger.info("=======↑↑↑↑↑=======完成jepf信息信息同步到system库中，耗时："+ syncCost +"ms=======↑↑↑↑↑=======");*/
			//同步后删除缓存中的key
			//redisUtil.deleteByPrefix(RedisKeysConstants.REDIS_FUNCTION_FIELDS_PREFIX + "_" + org.getEnterpriseId().toString() + "_" + org.getOrganizationId().toString());
			//redisUtil.deleteByPrefix(RedisKeysConstants.REDIS_FUNCTION_INFO_PREFIX + "_" + org.getEnterpriseId().toString() + "_" + org.getOrganizationId().toString());
			//redisUtil.deleteByPrefix(RedisKeysConstants.REDIS_COLUMN_PREFIX + "_" + org.getEnterpriseId().toString() + "_" + org.getOrganizationId().toString());
			//redisUtil.deleteByPrefix(RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + org.getOrganizationId().toString());
			//插入同步记录
			syncRecordMapper.insert(record);
			
			//同步完成之后创建或更新该企业客户的超级管理员
	        NsSystemSuperAdmin superAdmin = new NsSystemSuperAdmin();
	        Long userId = 1L;//userId为该企业的负责人由SoSS平台传过来
	        superAdmin.setUserid(userId);
	        superAdmin.setEnterpriseId(org.getEnterpriseId());
	        superAdmin.setOrganizationId(org.getOrganizationId());
	        if(DevPlatformContants.NS_SOOS_NAME.equals(syncVo.getMenuName())){
	        	 superAdmin.setRoleType("soss");
	        	 superAdmin.setUserid(syncVo.getUserId());
	        }
	        systemJepfRemoteService.createSuperAdmin(superAdmin);
		}
		Long sumCost = System.currentTimeMillis()-startTime;
		/*logger.info("↑↑↑↑↑=======end==========完成同步jepf中的信息，总耗时:"+ sumCost + "ms=============end============↑↑↑↑↑");*/
	}
	
	/**
	 * 获取需要同步的组织
	 * @param syncVo
	 * @return
	 */
	private List<JepfSyncOrgVo> getSyncOrg(JepfSyncVo syncVo){
		List<JepfSyncOrgVo>  orgs = new ArrayList<JepfSyncOrgVo>();
		if(syncVo.getSyncType() == DevPlatformContants.VO_SYNC_TYPE_SOME){
			orgs = syncVo.getOrgList();
		}
		
		if(syncVo.getSyncType() == DevPlatformContants.VO_SYNC_TYPE_ALL){
			List<NsSystemOrganization> list = systemJepfRemoteService.getAllCompanyOrg(syncVo.getOrgName()).getResultData();
			if(!CollectionUtils.isEmpty(list)){
				for(NsSystemOrganization org : list){
					JepfSyncOrgVo orgVo = new JepfSyncOrgVo();
					BeanUtils.copyProperties(org, orgVo);
					orgs.add(orgVo);
				}
			}
			//去掉同步原始数据
//			JepfSyncOrgVo orgVoTmp = new JepfSyncOrgVo();
//			orgVoTmp.setEnterpriseId(0L);
//			orgVoTmp.setOrganizationId(0L);
//			orgs.add(orgVoTmp);
		}
		return orgs;
	}
	
	/**
	 * 根据id获取funcinfo
	 * 获取功能是使用
	 * @param funId
	 * @return
	 */
	public JeCoreFuncinfo getFuncinfoById(String funcId){
		if(StringUtils.isBlank(funcId)){
			return null;
		}
		return jeCoreFuncinfoMapper.selectByFuncId(funcId);
	}
	
	/**
	 * 根据menuName获取根menu
	 * 进而根据根menu的menuid获取整个项目的munu
	 * @param menuName
	 * @return
	 */
	private List<JeCoreMenu> getMenuByMenuName(String menuName){
		logger.info("↓↓↓↓↓=======start==========正在根据名字获取所有的菜单=============start============↓↓↓↓↓");
		if(StringUtils.isBlank(menuName)){
			return null;
		}
		JeCoreMenu rootMenu= jeCoreMenuMapper.selectByMenuName(menuName);
		String path = rootMenu.getSyPath();
		//如果是运营
		if(DevPlatformContants.NS_SOOS_NAME.equals(menuName)){
		  //适配 selectByMenuPath sql
		  path = path.substring(1);
		}
		if(StringUtils.isBlank(path)){
			return null;
		}
		List<JeCoreMenu> menus = jeCoreMenuMapper.selectByMenuPath(path);
		logger.info("↑↑↑↑↑=======end==========正在根据名字获取所有的菜单======"+menus.size()+"=======end============↑↑↑↑↑");
		return menus;
	}
	
	/**
	 * 根据菜单获取相关的功能info
	 * @param menus
	 * @param latestSyncTime
	 * @return
	 */
	private List<JeCoreFuncinfo> getFuncinfoByFuncodes(List<JeCoreMenu> menus){
		logger.info("↓↓↓↓↓=======start==========正在根据菜单获取功能=============start============↓↓↓↓↓");
		List<JeCoreFuncinfo> funcList = new ArrayList<JeCoreFuncinfo>();
		if(CollectionUtils.isEmpty(menus)){
			return funcList;
		}
		//整合meun中的funcCode
		List<String> funcCodes = new ArrayList<String>();
		menus.forEach(menu -> funcCodes.add(menu.getMenuNodeinfo()));
		List<JeCoreFuncinfo>  funcs = jeCoreFuncinfoMapper.selectByFuncCodes(funcCodes);
		logger.info("↑↑↑↑↑=======end==========正在根据菜单获取功能======"+funcs.size()+"=======end============↑↑↑↑↑");
		return funcs;
	}
	
	/**
	 * 根据funcinfo获取所有的按钮
	 * @param funcInfos
	 * @param latestSyncTime
	 * @return
	 */
	private List<JeCoreResourcebutton> getResourceButtonByFuncIds(List<JeCoreFuncinfo> funcInfos, Date latestSyncTime){
		logger.info("↓↓↓↓↓=======start==========正在根据功能获取相关按钮信息=============start============↓↓↓↓↓");
		List<JeCoreResourcebutton> buttons = new ArrayList<JeCoreResourcebutton>();
		if(CollectionUtils.isEmpty(funcInfos)){
			return buttons;
		}
		List<String> funcIds = new ArrayList<String>();
		funcInfos.forEach(funcInfo -> funcIds.add(funcInfo.getJeCoreFuncinfoId()));
		Map<String, Object> map = Maps.newHashMap();
		map.put("funcIds", funcIds);
		map.put("latestSyncTime", latestSyncTime);
		buttons = jeCoreResourcebuttonMapper.selectByFuncIds(map);
		logger.info("↑↑↑↑↑=======end==========正在根据功能获取相关按钮信息======"+buttons.size()+"=======end============↑↑↑↑↑");
		return buttons;
	}
	
	/**
	 * 根据funcids获取相关的表头
	 * @param funcInfos
	 * @param latestSyncTime
	 * @return
	 */
	public List<JeCoreResourcecolumn> getResourcrcolumnByFuncIds(List<JeCoreFuncinfo> funcInfos, Date latestSyncTime){
		logger.info("↓↓↓↓↓=======start==========正在根据功能获取相关表头信息=============start============↓↓↓↓↓");
		List<JeCoreResourcecolumn>  columns = new ArrayList<JeCoreResourcecolumn>();
		if(CollectionUtils.isEmpty(funcInfos)){
			return columns;
		}
		List<String> funcIds = new ArrayList<String>();
		funcInfos.forEach(funcInfo -> funcIds.add(funcInfo.getJeCoreFuncinfoId()));
		Map<String, Object> map = Maps.newHashMap();
		map.put("funcIds", funcIds);
		map.put("latestSyncTime", latestSyncTime);
		columns = jCoreResourcecolumnMapper.selectByFuncIds(map);
		logger.info("↑↑↑↑↑=======end==========正在根据功能获取相关表头信息======"+columns.size()+"=======end============↑↑↑↑↑");
		return columns;
	}
	
	/**
	 * 根据funcids获取相关的表单
	 * @param funcInfos
	 * @param latestSyncTime
	 * @return
	 */
	public List<JeCoreResourcefield> getResourcrfieldByFuncIds(List<JeCoreFuncinfo> funcInfos, Date latestSyncTime){
		logger.info("↓↓↓↓↓=======start==========正在根据功能获取相关表单信息=============start============↓↓↓↓↓");
		List<JeCoreResourcefield>  fields = new ArrayList<JeCoreResourcefield>();
		if(CollectionUtils.isEmpty(funcInfos)){
			return fields;
		}
		List<String> funcIds = new ArrayList<String>();
		funcInfos.forEach(funcInfo -> funcIds.add(funcInfo.getJeCoreFuncinfoId()));
		Map<String, Object> map = Maps.newHashMap();
		map.put("funcIds", funcIds);
		map.put("latestSyncTime", latestSyncTime);
		fields = jeCoreResourcefieldMapper.selectByFuncIds(map);
		logger.info("↑↑↑↑↑=======end==========正在根据功能获取相关表单信息======"+fields.size()+"=======end============↑↑↑↑↑");
		return fields;
	}
	
	/**
	 * 将jepf中的xtype装换成saas中的xType
	 * @param sourceXtype
	 * @return
	 */
	private String changeFiledXtype(String sourceXtype){
		if(StringUtils.isBlank(sourceXtype)){
			return "";
		}
		switch (sourceXtype){
			//文本框
			case "textfield":
				return "input";
			//数值框
			case "numberfield":
				return "input";
			//单选框
			case "rgroup":
				return "radio";
			//复选框
			case "cgroup":
				return "checkbox";
			//下拉框
			case "cbbfield":
				return "select";
			//文本域
			case "textarea":
				return "input";
			//html编辑器
			case "ckeditor":
				return "ckeditor";
			//编号
			case "textcode":
				return "textcode";
			//附件
			case "uxfilefield":
				return "uxfilefield";
			//多附件
			case "uxfilesfield":
				return "uxfilesfield";
			//日期
			case "datefield":
				return "datepicker";
			//日期时间
			case "datetimefield":
				return "timepicker";
			//日期月份
			case "datemonthfield":
				return "monthpicker";
			//日期期间
			case "rangedatefield":
				return "datepicker";
			//树形选择
			case "treessfield":
				return "treessfield";
			//树形选择大
			case "treessareafield":
				return "treessareafield";
			//查询选择
			case "gridssfield":
				return "gridssfield";
			//查询选择大
			case "gridssareafield":
				return "gridssareafield";
			//智能查询
			case "searchfield":
				return "searchfield";
			//人员构造器
			case "queryuserfield":
				return "queryuserfield";
			//人员构造器（大）
			case "queryuserareafield":
				return "queryuserareafield";
			//下拉框集合
			case "cbblistfield":
				return "cbblistfield";
			//多数据选项集合
			case "multiitem":
				return "cascader";
			//子功能集合
			case "childfuncfield":
				return "childfuncfield";
			//颜色选择器
			case "colorfield":
				return "upload";
			//流程图字段
			case "graphfield":
				return "graphfield";
			//展示字段
			case "displayfield":
				return "text";
			//评星（头像）
			case "starfield":
				return "avatar";
			//进度条
			case "barfield":
				return "separator";
			//子功能
			case "child":
				return "baseTable";
			//分组框
			case "fieldset":
				return "group";
			default:
				return "";
		}
	}
	
	/**
	 * 将clounm列转换成saas需要的类型
	 * @param sourceXtype
	 * @return
	 */
	private String changeCloumnXtype(String sourceXtype){
		if(StringUtils.isBlank(sourceXtype)){
			return "";
		}
		switch(sourceXtype){
			case "uxcolumn":
				return "text";
			case "rownumberer":
				return "number";
			case "actioncolumn":
				return "date";
			case "uxcheckcolumn":
				return "select";
			default:
				return "";
		}
	}
	
}
