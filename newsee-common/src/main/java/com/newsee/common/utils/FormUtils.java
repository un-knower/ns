package com.newsee.common.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.newsee.common.constant.BizListRequestConstants;
import com.newsee.common.constant.Constants;
import com.newsee.common.constant.FormConstants;
import com.newsee.common.entity.FilterEntity;
import com.newsee.common.vo.SearchVo;
import com.newsee.common.vo.NsCoreResourcefieldVo;
import net.sf.cglib.beans.BeanMap;

/**
 * 操作反射类
 * @author xiaosisi add on 2017/10/30
 *
 */
public class FormUtils {

//	/**
//	 * 处理前端传过来的的表单，将每个表单的值赋值到相应的class属性中
//	 * 主要用户处理前端表单的值装换成vo对象
//	 * @param fields 表单属性
//	 * @param clazz vo的class
//	 * @return
//	 */
//	public static Object fieldsToVo(List<SystemResourceFieldVo> fields, Class<?> clazz){
//		if(CollectionUtils.isEmpty(fields)){
//			return null;
//		}
//		Object obj = null;
//		try{
//			//创建class对象，往里面设置属性值
//			obj = clazz.newInstance();
//			for (SystemResourceFieldVo field : fields) {
//				String key = field.getResourcefieldNameEn();
//				//获取不到key，进入下一个属性的处理
//				if(StringUtils.isBlank(key)){
//					continue;
//				}
//				//获取当前属性的数据类型
//				String type = clazz.getDeclaredField(key).getGenericType().toString();
//				Object value = getValueByType(type, field.getValue());
//				PropertyDescriptor descriptor = new PropertyDescriptor(key, clazz);
//				if (!Objects.isNull(descriptor)) {
//					Method method = descriptor.getWriteMethod();
//					method.invoke(obj, value);
//				}else{
//					continue;
//				}
//			}
//		}catch(Exception e){
//			return null;
//		}
//		return obj;
//	}
//	
//	/**
//	 * 处理前端传过来的的表单，将每个表单的值赋值到相应的class属性中
//	 * 主要用户处理前端表单的值装换成vo对象
//	 * @param fields 表单属性
//	 * @param object vo包含属性值的对象，即从后台查询出来的对象
//	 * @return
//	 * @throws IllegalAccessException 
//	 * @throws IllegalArgumentException 
//	 * @throws IntrospectionException 
//	 * @throws InvocationTargetException 
//	 */
//	public static List<SystemResourceFieldVo> voToFields(List<SystemResourceFieldVo> fields, Object object) throws IllegalArgumentException, IllegalAccessException, IntrospectionException, InvocationTargetException{
//		if(CollectionUtils.isEmpty(fields)){
//			return null;
//		}
//		if(Objects.isNull(object)){
//			return null;
//		}
//		Class<?> clazz = object.getClass();
//		//获取class中的所有属性集合
//		Field[] clazzFields = clazz.getDeclaredFields();
//		//将List<SystemResourceFieldVo>整合成Map，方便循环
//		Map<String, SystemResourceFieldVo> voMap = new HashMap<String, SystemResourceFieldVo>();
//		fields.forEach(field -> {
//			voMap.put(field.getResourcefieldNameEn(), field);
//		});
//		
//		//循环vo对象，获取属性值，并将值写入到voMap中对应的值中
//		for(int i= 0; i < clazzFields.length; i++){
//			//获取当前field的value值，设置到voMap中对应的SystemResourceFieldVo的value中
//			Field field = clazzFields[i];
//			field.setAccessible(true);
//			
//			SystemResourceFieldVo vo = voMap.get(field.getName());
//			if(!Objects.isNull(vo)){
//				PropertyDescriptor descriptor = new PropertyDescriptor(field.getName(), clazz);
//				if (!Objects.isNull(descriptor)) {
//					Method method = descriptor.getReadMethod();
//					String value = method.invoke(object).toString();
//					vo.setValue(value);
//				}
//			}
//		}
//		return fields;
//	}
	
	
	
	
	/**
	 * 将json字符串形式的form表单装换成相应的对象
	 * @param resultData
	 * @return
	 */
	public static List<NsCoreResourcefieldVo> getFormFields(Map<String, Object> map){
		List<NsCoreResourcefieldVo> formFields  = new ArrayList<NsCoreResourcefieldVo>();
		if(!Objects.isNull(map.get(FormConstants.FORM_FIELDS))){
			formFields = JSON.parseArray((String)map.get(FormConstants.FORM_FIELDS), NsCoreResourcefieldVo.class);
		}
		return formFields;
	}
	
	/**
	 * 将voMap中再clazz中不存在的属性过滤出来，
	 * 放入到map中并返回
	 * @param voMap
	 * @param clazz
	 * @return
	 */
	public static Map<String, Object> filterBeanMap(Map<String, Object> voMap, Map<String, Field> fieldMap){
		Map<String, Object> map = Maps.newHashMap();
		if(Objects.isNull(voMap) || Objects.isNull(fieldMap)){
			return map;
		}
		voMap.keySet().forEach(key -> {
			if(!fieldMap.containsKey(key)){
				map.put(key, voMap.get(key));
			}
		});
		//删掉autoForm固有属性
		map.remove(BizListRequestConstants.AUTO_FORM_PROPERTY_NAME);
		return map;
	}
	
	/**
	 * 获取clazz中的field集合，用来判断在map中是否存在该属性
	 * @param clazz
	 * @param fieldName
	 * @return
	 */
	public static Map<String, Field> getClassField(Class<?> clazz){
		List<Field> fields = Arrays.asList(clazz.getDeclaredFields());
		Map<String, Field> map = fields.stream().collect(Collectors.toMap(Field::getName, field -> field));
		return map;
	}
	
	/**
	 * 将bean转换成map
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> Map<String, Object> beanToMap(T bean){
		Map<String, Object> map = Maps.newHashMap();
		if(!Objects.isNull(bean)){
			BeanMap beanMap = BeanMap.create(bean);
			beanMap.keySet().forEach(om -> map.put(om.toString(), beanMap.get(om)));
		}
		//删掉autoForm固有属性
		map.remove(BizListRequestConstants.AUTO_FORM_PROPERTY_NAME);
		return map;
	}
	
	/**
	 * 将map转换成bean
	 * @param map
	 * @param bean
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T> T mapToBean(Map<String, Object> map, T bean){
		String mapJson = JSON.toJSONString(map);
		bean = JSON.parseObject(mapJson, (Class<T>)bean.getClass());
		return bean;
	}
	
	
	 public static Object mapToObject(Map<String, Object> map, Class<?> beanClass) throws Exception {    
        if (map == null)  
            return null;    
        Object obj = beanClass.newInstance();  
        Field[] fields = obj.getClass().getDeclaredFields();   
        for (Field field : fields) {    
            int mod = field.getModifiers();    
            if(Modifier.isStatic(mod) || Modifier.isFinal(mod)){    
                continue;    
            }    
  
            field.setAccessible(true);    
            field.set(obj, map.get(field.getName()));   
        }   
        return obj;    
    }    
	
	/**
	 * 根据数据类型将string转换成相应的类型
	 * @param type 数据类型
	 * @param value string值
	 * @return
	 */
	public static Object getValueByType(String type, String value){
		if(StringUtils.isBlank(type)){
			return null;
		}
		switch(type){
			case "class java.lang.String":{
				return value;
			}
			case "class java.lang.Integer":{
				if(!StringUtils.isBlank(value)){
					return Integer.parseInt(value);
				}else{
					return null;
				}
			}
			case "class java.lang.Long":{
				if(!StringUtils.isBlank(value)){
					return Long.valueOf(value);
				}else{
					return null;
				}
			}
			case "class java.lang.Boolean":{
				if(!StringUtils.isBlank(value)){
					return StringUtils.parseBoolean(value);
				}else{
					return null;
				}
			}
			case "class java.util.Date":{
				if(!StringUtils.isBlank(value)){
					return DateUtils.strToDateByLength(value);
				}else{
					return null;
				}
			}
			default: return null;
		}
	}
	
	/**
	 * 获取表单项目中的简单表格项目
	 * @param vos
	 * @return
	 */
	public static NsCoreResourcefieldVo getBaseTableField(List<NsCoreResourcefieldVo> vos){
		if(CollectionUtils.isEmpty(vos)){
			return null;
		}
		for(int i = 0; i< vos.size(); i++){
			if((BizListRequestConstants.FORM_TYPE_TABLE).equals(vos.get(i).getResourcefieldXtype())){
				return vos.get(i);
			}
		}
		return null;
	}
	
	/**
	 * 
	 * @Title: creatQueryForSearch 
	 * @Description: 生成MongoDB查询条件
	 * @param @param searchVo 筛选器
	 * @param @param mainSearch 主要查询条件
	 * @param @return    设定文件 
	 * @return Query  返回为null
	 * @throws 
	 * @author wangrenjie
	 *         肖斯斯  modify on 2017/11/23 自动表单搜索条件修改
	 * 
	 */
	public static Query creatQueryForSearch(SearchVo searchVo, String mainSearch, Class<?> clazz){
	    Query query = null;
        if (!Objects.isNull(searchVo)) {
        	query = new Query();
            //列表中输入框中的搜索条件
            if (!CommonUtils.isNullOrBlank(searchVo.getMainSearch())) {
            	query.addCriteria(Criteria.where(mainSearch).regex(searchVo.getMainSearch()));
            }
            //共通搜索条件
            if (!CommonUtils.isObjectEmpty(searchVo.getEnterpriseId())) {
            	query.addCriteria(Criteria.where("enterpriseId").is(searchVo.getEnterpriseId()));
            }
            if (!CommonUtils.isObjectEmpty(searchVo.getOrganizationId())) {
            	query.addCriteria(Criteria.where("organizationId").is(searchVo.getOrganizationId()));
            }
            if (!CommonUtils.isObjectEmpty(searchVo.getDepartmentId())) {
            	query.addCriteria(Criteria.where("departmentId").is(searchVo.getDepartmentId()));
            }
            //筛选器搜索条件
            if (!CollectionUtils.isEmpty(searchVo.getFilterList())) {
            	//组合所有搜索条件
                if (!CollectionUtils.isEmpty(searchVo.getTreeConditions())) {
                    searchVo.getFilterList().addAll(searchVo.getTreeConditions());
                }
                Map<String, Field> clazzFieldMap = FormUtils.getClassField(clazz);
                //按FieldName进行流分组
                Map<String, List<FilterEntity>> map = searchVo.getFilterList().stream().collect(Collectors.groupingBy(FilterEntity::getFieldName));
                //组合筛选器中的检索条件
                for (Iterator<Entry<String, List<FilterEntity>>> iterator = map.entrySet().iterator(); iterator.hasNext();) {
                    Entry<String, List<FilterEntity>> entry = iterator.next();
                    //提取查询条件
                    Criteria criteria = null;
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        if (i == 0) {
                            criteria = getCriteria(entry.getValue().get(i), clazzFieldMap);
                        }else {
                            criteria.andOperator(getCriteria(entry.getValue().get(i), clazzFieldMap));
                        }
                    }
                    if (criteria != null) {
                    	query.addCriteria(criteria);
                    }
                }
            }
            if (!CommonUtils.isNullOrBlank(searchVo.getOrderFieldName()) && 
                    !CommonUtils.isNullOrBlank(searchVo.getOrderFieldType())) {
                String orderFieldName = "";
                if (Constants.FIELD_TYPE_TEXT.equals(searchVo.getOrderFieldType())) {
                    orderFieldName = searchVo.getOrderFieldName() + "PY";
                }else {
                    orderFieldName = searchVo.getOrderFieldName();
                }
                if (Constants.COLLECTIONS_ORDER_BY_ASC.equals(searchVo.getOrderBy())) {
                    query.with(new Sort(Direction.ASC,orderFieldName));
                }else {
                    query.with(new Sort(Direction.DESC,orderFieldName));
                }
                
            }
            if (!CommonUtils.isObjectEmpty(searchVo.getPageSize())) {
                query.skip((searchVo.getPageNum() - 1) * searchVo.getPageSize());
                query.limit(searchVo.getPageSize());
            }
            System.out.println("====query===="+ query.toString()+"=====query=====");
        }
        
        return query;
	}
	
	/**
	 * 组合筛选器中的检索条件
	 * 供mongodb使用
	 * @param filterEntity
	 * @return
	 */
	private static Criteria getCriteria(FilterEntity filterEntity, Map<String, Field> clazzFieldMap){
		String fieldName = filterEntity.getFieldName();
		if(Objects.isNull(clazzFieldMap.get(fieldName))){
			fieldName = BizListRequestConstants.AUTO_FORM_PROPERTY_NAME + "." + fieldName;
		}
	    Criteria criteria = new Criteria(fieldName);
	    //为空
        if (Constants.COMPARISON_NULL.equals(filterEntity.getComparison())) {
            criteria.is(null).orOperator(criteria.is(""));
            return criteria;
        }
        //不为空
        if (Constants.COMPARISON_NOT_NULL.equals(filterEntity.getComparison())) {
            criteria.ne(null).orOperator(criteria.ne(""));
            return criteria;
        }
        //LIKE
        if (Constants.COMPARISON_LIKE.equals(filterEntity.getComparison())) {
            criteria.regex(filterEntity.getFieldValue());
            return criteria;
        }
        //NOT LIKE
        if (Constants.COMPARISON_NOT_LIKE.equals(filterEntity.getComparison())) {
            criteria.not().regex(filterEntity.getFieldValue());
            return criteria;
        }
        //大于
        if (Constants.COMPARISON_GREATER_THAN.equals(filterEntity.getComparison())) {
            criteria.gt(filterEntity.getFieldValue());
            return criteria;
        }
        //小于
        if (Constants.COMPARISON_LESS_THAN.equals(filterEntity.getComparison())) {
            criteria.lt(filterEntity.getFieldValue());
            return criteria;
        }
        //大于等于
        if (Constants.COMPARISON_GREATER_EQUAL_THAN.equals(filterEntity.getComparison())) {
            criteria.gte(filterEntity.getFieldValue());
            return criteria;
        }
        //小于等于
        if (Constants.COMPARISON_LESS_EQUAL_THAN.equals(filterEntity.getComparison())) {
            criteria.lte(filterEntity.getFieldValue());
            return criteria;
        }
        //等于
        if (Constants.COMPARISON_EQUAL.equals(filterEntity.getComparison())){
            if (CommonUtils.isNumber(filterEntity.getFieldValue())) {
                criteria.is(Long.valueOf(filterEntity.getFieldValue()));
            }else if (CommonUtils.isDecimal(filterEntity.getFieldValue())) {
                criteria.is(new BigDecimal(filterEntity.getFieldValue()));
            }else {
                criteria.is(filterEntity.getFieldValue());
            }
            return criteria;
        }
        //不等于
        if (Constants.COMPARISON_NOT_EQUAL.equals(filterEntity.getComparison())) {
            if (CommonUtils.isNumber(filterEntity.getFieldValue())) {
                criteria.ne(Long.valueOf(filterEntity.getFieldValue()));
            }else if (CommonUtils.isDecimal(filterEntity.getFieldValue())) {
                criteria.ne(new BigDecimal(filterEntity.getFieldValue()));
            }else {
                criteria.ne(filterEntity.getFieldValue());
            }
            return criteria;
        }
        return null;
	}
	
}
