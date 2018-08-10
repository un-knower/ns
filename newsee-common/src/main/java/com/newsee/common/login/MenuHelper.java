package com.newsee.common.login;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.apache.commons.collections.CollectionUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.utils.SpringBeanUtils;
import com.newsee.redis.util.RedisUtil;

public class MenuHelper {

	/**
	 * 根据menu的英文名称获取相应的funcId，
	 * 业务日志记录用
	 * @param menuenName
	 * @return
	 */
	public static String getFuncIdByMenuEnName(String menuenName){
		Long userId = LoginDataHelper.getUserId();
		Long organizationId = LoginDataHelper.getCompanyLevelOrgId();
		RedisUtil redisUtil = SpringBeanUtils.getBean(RedisUtil.class);
		String funcId = "";
		String menuRedisKey = RedisKeysConstants.REDIS_LOGININFO_MENU_PREFIX + organizationId + "_" + userId;
		if(!Objects.isNull(redisUtil.getObjectValue(menuRedisKey))){
			@SuppressWarnings("unchecked")
			List<Map<String, Object>> menuMap = JSON.parseObject(JSON.toJSONString(redisUtil.getObjectValue(menuRedisKey)),List.class);
			if(!CollectionUtils.isEmpty(menuMap)){
				for(Map<String, Object> map : menuMap){
					//子菜单为空，循环下一个一级菜单
					if(Objects.isNull( map.get("childMenus"))){
						continue;
					}
					//获取二级菜单
					@SuppressWarnings("unchecked")
					List<JSONObject> children = (List<JSONObject>) map.get("childMenus");
					//循环二级菜单，根据子菜单的英文名称获取响应的funcid
					if(!CollectionUtils.isEmpty(children)){
						for(JSONObject jsonObject : children){
							if(jsonObject.get("menuMenusubname").equals(menuenName)){
								funcId = jsonObject.get("funcId").toString();
								break;
							}
						}
					}
				}
			}
		}
		return funcId;
	}
}
