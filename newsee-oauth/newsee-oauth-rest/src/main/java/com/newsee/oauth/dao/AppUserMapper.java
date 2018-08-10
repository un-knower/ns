package com.newsee.oauth.dao;

import java.util.List;
import java.util.Map;

import com.newsee.common.entity.AppUser;
import com.newsee.oauth.vo.AppUserVo;

public interface AppUserMapper {
	
    int deleteById(Long id);
    
    int deleteByUserId(Long id);
    
    int deleteSoftByUserId(Long id);

    int insert(AppUser record);

    AppUser selectById(Long id);

    int updateById(AppUser record);
    
    int updateByUserId(AppUser record);

    AppUser selectByUserName(String userName);
    
    AppUser selectPasswordByUserAccount(Map<String, Object> map);
    
    int updatePasswordByPhoneNumber(List<AppUserVo> appUsers);
    /**
     * 根据user_account,appid获取appuse信息
     * 主要给登录用（会出现一个用户属于多家公司的情况）
     * @param map
     * @return
     */
    List<AppUser> selectByUserAccount(Map<String, Object> map);
    
    List<AppUser> vailateUserAccount(Map<String, Object> map);
    
    AppUser selectByUserId(Long userId);
}