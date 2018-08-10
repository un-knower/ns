package com.newsee.system.service;

import java.util.List;

import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.system.vo.NsCoreMenuVo;

public interface INsMenuService {

    List<NsCoreMenuVo> listMenu(LoginCommonDataVo loginVo);
    
    List<NsCoreMenuVo> listMenuButton(LoginCommonDataVo loginVo);
    
    List<NsCoreMenuVo> listAllMenuButton(LoginCommonDataVo loginVo);
}
