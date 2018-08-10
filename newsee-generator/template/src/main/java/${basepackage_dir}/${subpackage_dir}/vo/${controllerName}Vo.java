<#include "/macro.include"/>
<#include "/java_copyright.include">
package ${basepackage}.${subpackage}.vo;

import java.io.Serializable;
import java.util.Date;
import io.swagger.annotations.ApiModelProperty;

/**
 * ${controllerName}Vo
 * @version 1.0
 * @author
 */
public class ${controllerName}Vo implements Serializable {
	
	private static final long serialVersionUID = 1L;

	<#list voColumns as column>
	/** ${column.columnAlias} */
	@ApiModelProperty(value = "${column.columnAlias}")
	private ${column.simpleJavaType} ${column.columnNameLower};
	
	</#list>
		
	<#list voColumns as column>
	public void set${column.columnName}(${column.simpleJavaType} ${column.columnNameLower}) {
		this.${column.columnNameLower} = ${column.columnNameLower};
	}
	
	public ${column.simpleJavaType} get${column.columnName}() {
		return ${column.columnNameLower};
	}
	
	</#list>
}
<#macro generateJavaColumns>
	<#list voColumns as column>
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