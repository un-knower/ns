<#include "/macro.include"/>
<#include "/java_copyright.include">
<#assign className = table.className>   
<#assign classNameLower = className?uncap_first> 
package ${basepackage}.${subpackage}.entity;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * ${table.tableAlias}
 * @version 1.0
 * @author
 */
public class ${className} implements Serializable {
	
	private static final long serialVersionUID = 1L;

	<#list table.columns as column>
	/** ${column.columnAlias} */
	@ApiModelProperty(value = "${column.columnAlias}")
	private ${column.simpleJavaType} ${column.columnNameLower};
	
	</#list>
		
	<#list table.columns as column>
	public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	public ${column.simpleJavaType} get${column.columnName}() {
		return ${column.columnNameLower};
	}
	
	</#list>
}
<#macro generateJavaColumns>
	<#list table.columns as column>
    <@generateBycondition column.sqlName>
	public void set${column.columnName}(${column.simpleJavaType} value) {
		this.${column.columnNameLower} = value;
	}
	
	public ${column.simpleJavaType} get${column.columnName}() {
		return this.${column.columnNameLower};
	}
	
	</@generateBycondition>
	</#list>
</#macro>