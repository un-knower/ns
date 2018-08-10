package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.FormUtils;
import com.newsee.common.vo.SearchVo;
import com.newsee.system.dao.NsCoreDictionaryMapper;
import com.newsee.system.dao.NsCoreDictionarygroupMapper;
import com.newsee.system.dao.NsCoreDictionaryitemMapper;
import com.newsee.system.dao.NsSystemOrganizationMapper;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsCoreDictionarygroup;
import com.newsee.system.entity.NsCoreDictionaryitem;
import com.newsee.system.entity.NsSystemOrganization;
import com.newsee.system.service.INsDictionaryItemService;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

@Service
public class NsDictionaryItemServiceImpl implements INsDictionaryItemService {
    
    @Autowired
    NsCoreDictionaryitemMapper dictionaryitemMapper;
    @Autowired
    NsCoreDictionaryMapper dictionaryMapper;
    @Autowired
    NsSystemOrganizationMapper organizationMapper;
    @Autowired
    NsCoreDictionarygroupMapper dictionarygroupMapper;
    
    @Override
    public Boolean add(NsCoreDictionaryitemVo dictionaryitemVo) {
        boolean result = false;
        NsCoreDictionaryitem dictionaryitem = new NsCoreDictionaryitem();
        BeanUtils.copyProperties(dictionaryitemVo, dictionaryitem);
        String jeCoreDictionaryitemId = UUID.randomUUID().toString();
        dictionaryitem.setJeCoreDictionaryitemId(jeCoreDictionaryitemId);
        dictionaryitem.setDictionaryitemNodeinfotype("custom");
        NsCoreDictionaryitem onlyName = dictionaryitemMapper.checkOnlyName(dictionaryitem);
        if (onlyName != null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "字典项名称重复");
        }
        
        NsCoreDictionaryitem onlyCode = dictionaryitemMapper.checkOnlyCode(dictionaryitem);
        if (onlyCode != null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "字典项值重复");
        }

        dictionaryitemMapper.insert(dictionaryitem);
        result = true;
        return result;
    }

    @Override
    public Boolean edit(NsCoreDictionaryitemVo dictionaryitemVo) {
        boolean result = false;
        if ("system".equals(dictionaryitemVo.getDictionaryitemNodeinfotype())) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "系统默认的不可编辑");
            result = false;
        }else{
            NsCoreDictionaryitem dictionaryitem = new NsCoreDictionaryitem();
            BeanUtils.copyProperties(dictionaryitemVo, dictionaryitem);
            NsCoreDictionaryitem onlyName = dictionaryitemMapper.checkOnlyName(dictionaryitem);
            if (onlyName != null) {
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "字典项名称重复");
            }
            
            NsCoreDictionaryitem onlyCode = dictionaryitemMapper.checkOnlyCode(dictionaryitem);
            if (onlyCode != null) {
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "字典项值重复");
            }
            dictionaryitemMapper.updateByDictionaryitemId(dictionaryitem);
            result = true;
        }
        
        return result;
    }

    @Override
    public Boolean delete(String dictionaryitemId) {
        boolean result = false;
        NsCoreDictionaryitem dicitem = dictionaryitemMapper.selectByDictionaryitemId(dictionaryitemId);
        if ("system".equals(dicitem.getDictionaryitemNodeinfotype())) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "系统默认的不可删除");
            result = false;
        }else{
            dictionaryitemMapper.deleteByDictionaryitemId(dictionaryitemId);
            result = true; 
        }
        return result;
    }

    @Override
    public List<NsCoreDictionaryitemVo> list(String dictionaryId,Long organizationId) {
        Map<String, Object> map = new HashMap<>();
        map.put("organizationId", organizationId);
        map.put("dictionaryId", dictionaryId);
        List<NsCoreDictionaryitem> dictionaryitems = dictionaryitemMapper.selectByDictionaryId(map);
        List<NsCoreDictionaryitemVo> dictionaryitemVos = new ArrayList<>();
        for (NsCoreDictionaryitem dictionaryitem: dictionaryitems) {
            NsCoreDictionaryitemVo dictionaryitemVo = new NsCoreDictionaryitemVo();
            BeanUtils.copyProperties(dictionaryitem, dictionaryitemVo);
            dictionaryitemVos.add(dictionaryitemVo);
        }
        return dictionaryitemVos;
    }
    
    @Override
    public PageInfo<NsCoreDictionaryitemVo> listPage(SearchVo searchVo,String dictionaryitemDictionaryId, String dictionaryGroupId) {
       /*List<NsCoreDictionaryitemVo> list = new ArrayList<>();*/

       Map<String, Object> searchVoMap = FormUtils.beanToMap(searchVo);
       searchVoMap.put("dictionaryitemDictionaryId", dictionaryitemDictionaryId);
       searchVoMap.put("dictionaryGroupId", dictionaryGroupId);
       if (CommonUtils.isObjectEmpty(dictionaryitemDictionaryId)) {
           searchVoMap.put("groupFlag", true);
       }
       PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
       List<NsCoreDictionaryitemVo> dictionaryitems= dictionaryitemMapper.listResultBySearch(searchVoMap);
  /*     PageInfo<NsCoreDictionaryitem> tempPage = new PageInfo<>(dictionaryitems);

       for (NsCoreDictionaryitem dictionaryitem : dictionaryitems) {
            NsCoreDictionaryitemVo dictionaryitemVo = new NsCoreDictionaryitemVo();
            BeanUtils.copyProperties(dictionaryitem, dictionaryitemVo);
            list.add(dictionaryitemVo);
       }*/
       PageInfo<NsCoreDictionaryitemVo> pageInfo = new PageInfo<>(dictionaryitems);
      /* BeanUtils.copyProperties(tempPage, pageInfo);*/
      /* pageInfo.setList(dictionaryitems);*/
       return pageInfo;
    }
    
    @Override
    public List<NsCoreDictionaryitemVo> listPageSelect(String dictionaryitemDictionaryId, String dictionaryGroupId) {
       Map<String, Object> searchVoMap =Maps.newHashMap();
       searchVoMap.put("dictionaryitemDictionaryId", dictionaryitemDictionaryId);
       searchVoMap.put("dictionaryGroupId", dictionaryGroupId);
       List<NsCoreDictionaryitemVo> dictionaryitems= dictionaryitemMapper.listResultBySearchSelect(searchVoMap);
       return dictionaryitems;
    }

    @Override
    public NsCoreDictionaryitemVo detail(String dictionaryitemId) {
        NsCoreDictionaryitemVo dictionaryitemVo = new NsCoreDictionaryitemVo();
        NsCoreDictionaryitem dictionaryitem = dictionaryitemMapper.selectByDictionaryitemId(dictionaryitemId);
        BeanUtils.copyProperties(dictionaryitem, dictionaryitemVo);
        //所属字典
        NsCoreDictionary dictionary = dictionaryMapper.selectByDictionaryId(dictionaryitemVo.getDictionaryitemDictionaryId());
        //字典组
        Long dicGroupId = dictionary.getDictionaryGroupId();
        NsCoreDictionarygroup dicGroup = dictionarygroupMapper.selectById(dicGroupId);
        
        dictionaryitemVo.setDictionaryitemDictionaryName(dicGroup.getDictionaryGroupName()+"-"+dictionary.getDictionaryDdname());
        if ("system".equals(dictionaryitem.getDictionaryitemNodeinfotype())) {
            dictionaryitemVo.setOrganizationName("系统内置类型");
        }else {
            NsSystemOrganization organization = organizationMapper.selectById(dictionaryitem.getOrganizationId());
            dictionaryitemVo.setOrganizationName(organization.getOrganizationName());
        }
        return dictionaryitemVo;
    }


}
