<#include "/java_copyright.include"> 
<#assign pageNameLower = pageName?uncap_first>
package ${basepackage}.${subpackage}.service;

import java.util.List;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import ${basepackage}.${subpackage}.vo.${controllerName}Vo;

public interface I${controllerName}Service {

	/**
	 * 获取${pageCnName}列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	PageInfo<${controllerName}> listPage(SearchVo searchVo);
	
	/**
	 * 获取${pageCnName}详情
	 * @param id 主键id
	 * @return
	 */
	${controllerName}Vo detail(Long id);
	
	/**
	 * 编辑${pageCnName}详情
	 * @return boolean 编辑成功与否
	 */
	boolean edit(${controllerName}Vo vo);
	
	/**
	 * 新增${pageCnName}
	 * @return boolean 新增成功与否
	 */
	boolean add(${controllerName}Vo vo);
	
	/**
	 * 根据主键删除${pageCnName}
	 * @param id 主键id
	 * @return
	 */
	boolean delete(Long id);
	
	/**
	 * 根据主键批量删除${pageCnName}
	 * @param ids
	 * @return
	 */
	boolean deleteBatch(List<Long> ids);

}
