package com.newsee.system.service;

import java.util.List;

import com.newsee.system.entity.NsCoreFuncinfo;
import com.newsee.system.entity.NsCoreMenu;
import com.newsee.system.entity.NsCoreResourcebutton;
import com.newsee.system.entity.NsCoreResourcecolumn;
import com.newsee.system.entity.NsCoreResourcefield;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.vo.JepfVo;

/**
 * JEPF同步接口 ，将JEPF中的数据同步到newsee-system中
 * @author xiaoss add on 2017/11/28
 *
 */
public interface IJepfSyncService {

	/**
	 * 从JEPF同步menu数据
	 * @param menus
	 * @return
	 */
	Boolean syncMenu(List<NsCoreMenu> menus);
	
	/**
	 * 从JEPF中同步funcinfo数据
	 * @param funcinfos
	 * @return
	 */
	Boolean syncFuncinfo(List<NsCoreFuncinfo> funcinfos);
	
	/**
	 * 从JEPF中同步button数据
	 * @param buttons
	 * @return
	 */
	Boolean syncResourceButton(List<NsCoreResourcebutton> buttons);
	
	/**
	 * 从JEPF中同步列头数据
	 * @param colums
	 * @return
	 */
	Boolean syncResourceCloumn(List<NsCoreResourcecolumn> colums);
	
	/**
	 * 从JEPF中同步表单数据
	 * @param fields
	 * @return
	 */
	Boolean syncResourceField(List<NsCoreResourcefield> fields);
	
	/**
	 * 从JEPF中同步表单数据
	 * @param fields
	 * @return
	 */
	Boolean syncAll(JepfVo jepfVo);
	
	/**
	 * 获取所有子公司以上级别的公司
	 * @return
	 */
	List<NsSystemOrganization> getAllCompanyLevelOrg(String orgName);
	
}
