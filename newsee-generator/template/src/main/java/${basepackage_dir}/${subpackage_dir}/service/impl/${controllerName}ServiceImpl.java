<#assign pageNameUpper = pageName?capitalize>
<#assign controllerNameLower = controllerName?uncap_first>
package ${basepackage}.${subpackage}.service.impl;

import java.util.List;
import java.util.Objects;
import java.util.Map;
import org.springframework.stereotype.Service;
import org.springframework.beans.BeanUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import org.springframework.beans.factory.annotation.Autowired;
import ${basepackage}.${subpackage}.service.I${controllerName}Service;
<#list tablesName as tableString>
import ${basepackage}.${subpackage}.entity.${tableString?cap_first};
import ${basepackage}.${subpackage}.dao.${tableString?cap_first}Mapper;
</#list>

@Service
public class ${controllerName}ServiceImpl implements I${controllerName}Service {
	
	<#list tablesName as tableString>
    @Autowired
    private ${tableString?cap_first}Mapper ${tableString?uncap_first}Mapper;
    </#list>
    
	/**
	 * 获取${pageCnName}列表信息
	 * @param searchVo 检索条件
	 * @return
	 */
	public PageInfo<${controllerName}> listPage(SearchVo searchVo){
		if (Objects.isNull(searchVo)) {
			return null;
		}
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        <#list tablesName as mapper>
         <#if mapper_index == 0>
        List<${controllerName}> list = ${mapper?uncap_first}Mapper.listPage(searchVo);
         </#if>
        </#list>
        PageInfo<${controllerName}> pageInfo = new PageInfo<>(list);
        return pageInfo;
	}
	
	/**
	 * 获取${pageCnName}详情
	 * @param id 主键id
	 * @return
	 */
	public ${controllerName}Vo detail(Long id){
		if(Objects.isNull(id)) {
			return null;
		}
		${controllerName}Vo vo = new ${controllerName}Vo();
		<#list tablesName as tableString>
		${tableString?cap_first} ${tableString?uncap_first} = ${tableString?uncap_first}Mapper.selectById(id);
		//如果查询出了数据，将数据拷贝到vo中
		if(!Objects.isNull(${tableString?uncap_first})){
			BeanUtils.copyProperties(${tableString?uncap_first}, vo);
		}
	    </#list>
	    return vo;
	}
	
	/**
	 * 编辑${pageCnName}详情
	 * @return boolean 编辑成功与否
	 */
	public boolean edit(${controllerName}Vo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		<#list tablesName as tableString>
		${tableString?cap_first} ${tableString?uncap_first} = new ${tableString?cap_first}();
		BeanUtils.copyProperties(vo, ${tableString?uncap_first});
		int count${tableString} = ${tableString?uncap_first}Mapper.updateById(${tableString?uncap_first});
		if(count${tableString} == 0 ){
			return false;
		}
	    </#list>
	    return true;
	}
	
	/**
	 * 新增${pageCnName}
	 * @return boolean 新增成功与否
	 */
	public boolean add(${controllerName}Vo vo){
		if(Objects.isNull(vo)) {
			return false;
		}
		<#list tablesName as tableString>
		${tableString?cap_first} ${tableString?uncap_first} = new ${tableString?cap_first}();
		BeanUtils.copyProperties(vo, ${tableString?uncap_first});
		int count${tableString} = ${tableString?uncap_first}Mapper.insert(${tableString?uncap_first});
		if(count${tableString} == 0 ){
			return false;
		}
	    </#list>
	    return true;
	}
	
	/**
	 * 根据主键删除${pageCnName}
	 * @param id 主键id
	 * @return
	 */
	public boolean delete(Long id){
		if(Objects.isNull(id)) {
			return false;
		}
		<#list tablesName as tableString>
		int count${tableString} = ${tableString?uncap_first}Mapper.deleteById(id);
		if(count${tableString} == 0 ){
			return false;
		}
	    </#list>
	    return true;
	}
	
	/**
	 * 根据主键批量删除${pageCnName}
	 * @param ids
	 * @return
	 */
	public boolean deleteBatch(List<Long> ids){
		if(Objects.isNull(ids)) {
			return false;
		}
		<#list tablesName as tableString>
		int count${tableString} = ${tableString?uncap_first}Mapper.deleteBatch(ids);
		if(count${tableString} == 0 ){
			return false;
		}
	    </#list>
	    return true;
	}
}
