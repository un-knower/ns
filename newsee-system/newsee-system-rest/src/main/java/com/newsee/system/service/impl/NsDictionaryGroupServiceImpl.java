package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.system.dao.NsCoreDictionaryMapper;
import com.newsee.system.dao.NsCoreDictionarygroupMapper;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsCoreDictionarygroup;
import com.newsee.system.service.INsDictionaryGroupService;
import com.newsee.system.vo.DictionaryTreeVo;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionarygroupVo;

@Service
public class NsDictionaryGroupServiceImpl implements INsDictionaryGroupService {
    
    @Autowired
    NsCoreDictionarygroupMapper dictionarygroupMapper;
    @Autowired
    NsCoreDictionaryMapper dictionaryMapper;

    @Override
    public Boolean add(NsCoreDictionarygroupVo dictionarygroupVo) {
        boolean result = false;
        NsCoreDictionarygroup dictionaryGroup = new NsCoreDictionarygroup();
        BeanUtils.copyProperties(dictionarygroupVo, dictionaryGroup);
        dictionaryGroup.setCreateUserId(dictionarygroupVo.getHandlerId());
        dictionaryGroup.setUpdateUserId(dictionarygroupVo.getHandlerId());
        dictionarygroupMapper.insert(dictionaryGroup);
        result = true;
        return result;
    }

    @Override
    public Boolean edit(NsCoreDictionarygroupVo dictionarygroupVo) {
        boolean result = false;
        NsCoreDictionarygroup dictionaryGroup = new NsCoreDictionarygroup();
        BeanUtils.copyProperties(dictionarygroupVo, dictionaryGroup);
        dictionaryGroup.setUpdateUserId(dictionarygroupVo.getHandlerId());
        dictionarygroupMapper.updateById(dictionaryGroup);
        result = true;
        return result;
    }

    @Override
    public Boolean delete(Long dictionarygroupId, Long organizationId) {
        boolean result = false;
        Map<String, Object> map1 = new HashMap<>();
        map1.put("organizationId", organizationId);
        map1.put("dicGroupId", dictionarygroupId);
        List<NsCoreDictionary> resultList = dictionaryMapper.selectByDictionaryGroupId(map1);
        if (!CollectionUtils.isEmpty(resultList)) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "数据字典组下有数据字典时，不可被删除");
            result = false;
        }else{
            dictionarygroupMapper.deleteSoftById(dictionarygroupId);
            result = true;
        }
        return result;
    }

    @Override
    public List<NsCoreDictionarygroupVo> list(Long organizationId) {
        List<NsCoreDictionarygroup> dictionarygroups = dictionarygroupMapper.selectByOrganizationId(organizationId);
        List<NsCoreDictionarygroupVo> dictionarygroupVos = new ArrayList<>();
        for (NsCoreDictionarygroup dictionarygroup : dictionarygroups) {
            NsCoreDictionarygroupVo dictionarygroupVo = new NsCoreDictionarygroupVo();
            BeanUtils.copyProperties(dictionarygroup, dictionarygroupVo);
            Map<String, Object> map1 = new HashMap<>();
            map1.put("organizationId", organizationId);
            map1.put("dicGroupId", dictionarygroupVo.getDictionaryGroupId());
            List<NsCoreDictionary> dictionaries = dictionaryMapper.selectByDictionaryGroupId(map1);
            List<NsCoreDictionaryVo> dictionaryVos =  new ArrayList<>();
            dictionaries.forEach(dictionary ->{
                NsCoreDictionaryVo dictionaryVo = new NsCoreDictionaryVo();
                BeanUtils.copyProperties(dictionary, dictionaryVo);
                dictionaryVos.add(dictionaryVo);
            });
            dictionarygroupVo.setDictionaryVos(dictionaryVos);
            dictionarygroupVos.add(dictionarygroupVo);
        }
        return dictionarygroupVos;
    }
    
    @Override
    public DictionaryTreeVo listTree(Long organizationId) {
        DictionaryTreeVo rootNode = new DictionaryTreeVo();
        rootNode.setEnterpriseId(0L);
        rootNode.setOrganizationId(0L);
        rootNode.setNodeValue("0");
        rootNode.setNodeName("字典类型");
        rootNode.setNodeType("root");
        //字典组
        List<NsCoreDictionarygroup> dictionarygroups = dictionarygroupMapper.selectByOrganizationId(organizationId);
        List<DictionaryTreeVo> dictionaryTreeVos = new ArrayList<>();
        dictionarygroups.forEach(dicGroup->{
            DictionaryTreeVo dictionaryTreeVo = new DictionaryTreeVo();
            dictionaryTreeVo.setOrganizationId(dicGroup.getOrganizationId());
            dictionaryTreeVo.setNodeName(dicGroup.getDictionaryGroupName());
            dictionaryTreeVo.setNodeValue(dicGroup.getDictionaryGroupId().toString());
            dictionaryTreeVo.setNodeType("dicGroup");
            
            //字典
            Map<String, Object> map1 = new HashMap<>();
            map1.put("organizationId", organizationId);
            map1.put("dicGroupId", dicGroup.getDictionaryGroupId());
            List<NsCoreDictionary> dictionaries = dictionaryMapper.selectByDictionaryGroupId(map1);
            if (!CollectionUtils.isEmpty(dictionaries)) {
                List<DictionaryTreeVo> dicTreeVos = new ArrayList<>();
                dictionaries.forEach(dic ->{
                    DictionaryTreeVo dicTreeVo = new DictionaryTreeVo();
                    dicTreeVo.setOrganizationId(dic.getOrganizationId());
                    dicTreeVo.setNodeName(dic.getDictionaryDdname());
                    dicTreeVo.setNodeValue(dic.getJeCoreDictionaryId());
                    dicTreeVo.setNodeType("dic");
                    dicTreeVo.setChildren(new ArrayList<>());
                    dicTreeVos.add(dicTreeVo);
                });
                dictionaryTreeVo.setChildren(dicTreeVos);
            }else {
                dictionaryTreeVo.setChildren(new ArrayList<>());
            }
            
            dictionaryTreeVos.add(dictionaryTreeVo);
        
        });
        rootNode.setChildren(dictionaryTreeVos);
        return rootNode;
    }

    @Override
    public NsCoreDictionarygroupVo detail(Long dictionarygroupId) {
        NsCoreDictionarygroup dictionarygroup = dictionarygroupMapper.selectById(dictionarygroupId);
        NsCoreDictionarygroupVo dictionarygroupVo = new NsCoreDictionarygroupVo();
        BeanUtils.copyProperties(dictionarygroup, dictionarygroupVo);
        return dictionarygroupVo;
    }

}
