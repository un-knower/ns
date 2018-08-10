package com.newsee.system.service;

import java.util.List;

import com.newsee.common.vo.LoginCommonDataVo;
import com.newsee.system.entity.NsCoreResourcebutton;

public interface INsButtonService {

    List<NsCoreResourcebutton> listButton(LoginCommonDataVo loginVo);
}
