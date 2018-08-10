<#assign controllerNameLower = controllerName?uncap_first>
/*==========================================================================================================================*/
import qs from 'querystring';
import Cookies from 'js-cookie';
import fetch from '@/utils/fetch/fetch'
import { dataFilter } from '@/utils/fetch/fetchDataType'
/*==========================================================================================================================*/

/**
 * 表数据-获取,表格数据获取
 */
export function gridDataFetch(query) {
	dataFilter(query);
	return fetch({
		url: '/${subpackage}/${controllerNameLower}/list-${controllerNameLower}',
		method: 'post',
		data: query
	})
}

/**
 * 表格数据详情
 */
export function gridDataDetail(query){
	dataFilter(query);
	return fetch({
		url: '/${subpackage}/${controllerNameLower}/detail-${controllerNameLower}',
		method: 'get',
		params: query
	})
}

/**
 * 表格表单数据保存
 */
export function gridDataSubmit(query){
	let url = '';
	if(query.${pageName}Id) {
		url = '/${subpackage}/${controllerNameLower}/edit-${controllerNameLower}';
	} else {
		url = '/${subpackage}/${controllerNameLower}/add-${controllerNameLower}';
	}
	dataFilter(query);
	return fetch({
		url: url,
		method: 'post',
		data: query
	})
}

/**
 * 表格表单数据删除
 */
export function gridDataDelete(query) { //表数据-删除
	dataFilter(query);
	return fetch({
		url: '/${subpackage}/${controllerNameLower}/delete-${controllerNameLower}',
		method: 'get',
		params: query
	})
}

/**
 * 表格表单数据删除
 */
export function gridDataDeleteBatch(query) { //表数据-删除
	dataFilter(query);
	return fetch({
		url: '/${subpackage}/${controllerNameLower}/delete-${controllerNameLower}-batch',
		method: 'get',
		params: query
	})
}

