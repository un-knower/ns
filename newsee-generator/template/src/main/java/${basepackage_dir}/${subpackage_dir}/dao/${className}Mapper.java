<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first>
<#assign shortName = table.shortName>
package ${basepackage}.${subpackage}.dao;

import java.util.List;
import java.util.Map;
import ${basepackage}.${subpackage}.entity.${className};
import com.newsee.common.vo.SearchVo;

public interface ${className}Mapper {
  
    ${className} selectById(Long id);
    
    int insert(${className} ${classNameLower});
    
    int insertBatch(${r'List<'}${className}${r'>'} ${classNameLower}List);
    
    int updateById(${className} ${classNameLower});
    
    int deleteById(Long id);
    
    int deleteBatch(List<Long> ids);
    
    ${r'List<'}${className}${r'>'} listPage(SearchVo vo);
    
}