package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.system.dao.NsCoreDictionaryMapper;
import com.newsee.system.dao.NsCoreDictionaryitemMapper;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.entity.NsCoreDictionaryitem;
import com.newsee.system.service.INsDictionaryService;
import com.newsee.system.vo.DictionaryDdcodeVo;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

@Service
public class NsDictionaryServiceImpl implements INsDictionaryService {
    
    @Autowired
    NsCoreDictionaryMapper dictionaryMapper;
    @Autowired
    NsCoreDictionaryitemMapper dictionaryitemMapper;

    @Override
    public Boolean add(NsCoreDictionaryVo dictionaryVo) {
        boolean result = false;
        NsCoreDictionary dictionary = new NsCoreDictionary();
        BeanUtils.copyProperties(dictionaryVo, dictionary);
        String jeCoreDictionaryId = UUID.randomUUID().toString();
        dictionary.setJeCoreDictionaryId(jeCoreDictionaryId);
        dictionary.setDictionaryDictype("custom");
        dictionary.setSyModifyuser(dictionaryVo.getHandlerId().toString());
        dictionary.setSyCreateuser(dictionaryVo.getHandlerId().toString());
        NsCoreDictionary onlyCode = dictionaryMapper.checkOnlyByDictionaryDdcode(dictionary);
        NsCoreDictionary onlyName = dictionaryMapper.checkOnlyByDictionaryDdname(dictionary);
        if (onlyCode != null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "字典编码重复");
        }
        if (onlyName != null) {
            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "同一分类下字典名称不能重复");
        }
        dictionaryMapper.insert(dictionary);
        result = true;
        return result;
    }

    @Override
    public Boolean edit(NsCoreDictionaryVo dictionaryVo) {
        boolean result = false;
        if ("system".equals(dictionaryVo.getDictionaryDictype())) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "系统默认的不可以编辑");
            result = false;
        }else{
            NsCoreDictionary dictionary = new NsCoreDictionary();
            BeanUtils.copyProperties(dictionaryVo, dictionary);
            dictionary.setSyModifyuser(dictionaryVo.getHandlerId().toString());
            NsCoreDictionary only = dictionaryMapper.checkOnlyByDictionaryDdcode(dictionary);
            if (only != null) {
                BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, "字典编码重复");
            }
            dictionaryMapper.updateByDictionaryId(dictionary);
            result = true;
        }
        return result;
    }

    @Override
    public Boolean delete(String dictionaryId) {
        boolean result = false;
        NsCoreDictionary dictionary = dictionaryMapper.selectByDictionaryId(dictionaryId);
        //系统默认的不可以删除
        if ("system".equals(dictionary.getDictionaryDictype())) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "系统默认的不可以删除");
            result = false;
        }else{
            //有数据字典项的不可以删除
            Map<String, Object> map = new HashMap<>();
            map.put("organizationId", dictionary.getOrganizationId());
            map.put("dictionaryId", dictionaryId);
            List<NsCoreDictionaryitem> dictionaryitems = dictionaryitemMapper.selectByDictionaryId(map);
            if (!CollectionUtils.isEmpty(dictionaryitems)) {
                BizException.fail(ResultCodeEnum.SERVER_ERROR, "该字典下有字典项的不可以删除");
                result = false;
            }
            dictionaryMapper.deleteByDictionaryId(dictionaryId);
            //dictionaryitemMapper.deleteByDictionaryId(dictionaryId);
            result = true;
        }
        return result;
    }

    @Override
    public List<NsCoreDictionaryVo> list(Long dictionarygroupId, Long organizationId) {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("organizationId", organizationId);
        map1.put("dicGroupId", dictionarygroupId);
        List<NsCoreDictionary> dictionaries = dictionaryMapper.selectByDictionaryGroupId(map1);
        List<NsCoreDictionaryVo> dictionaryVos = new ArrayList<>();
        for (NsCoreDictionary dictionary : dictionaries) {
            NsCoreDictionaryVo dictionaryVo = new NsCoreDictionaryVo();
            BeanUtils.copyProperties(dictionary, dictionaryVo);
            dictionaryVos.add(dictionaryVo);
        }
        return dictionaryVos;
    }

    @Override
    public NsCoreDictionaryVo detail(String dictionaryId) {
        NsCoreDictionary dictionary = dictionaryMapper.selectByDictionaryId(dictionaryId);
        NsCoreDictionaryVo dictionaryVo = new NsCoreDictionaryVo();
        BeanUtils.copyProperties(dictionary, dictionaryVo);
        return dictionaryVo;
    }
    
    @Override
    public NsCoreDictionaryVo getNsCoreDictionaryVo(NsCoreDictionary dictionary) {
        NsCoreDictionary dictionaryInfo = dictionaryMapper.selectByDictionaryDdcode(dictionary);
        NsCoreDictionaryVo result = new NsCoreDictionaryVo();
        if(Objects.isNull(dictionaryInfo)){
            return result;
        }
        Map<String, Object> map1 = new HashMap<>();
        map1.put("organizationId", dictionary.getOrganizationId());
        map1.put("dictionaryId", dictionaryInfo.getJeCoreDictionaryId());
        List<NsCoreDictionaryitem> dictionaryitems = dictionaryitemMapper.selectByDictionaryId(map1);
        List<NsCoreDictionaryitemVo> dictionaryitemVos = new ArrayList<>();
        for (NsCoreDictionaryitem dictionaryitem : dictionaryitems) {
            NsCoreDictionaryitemVo dictionaryitemVo = new NsCoreDictionaryitemVo();
            BeanUtils.copyProperties(dictionaryitem, dictionaryitemVo);
            dictionaryitemVos.add(dictionaryitemVo);
        }
        BeanUtils.copyProperties(dictionaryInfo, result);
        result.setDictionaryitemVos(dictionaryitemVos);
        return result;
    }

    @Override
    public NsCoreDictionaryVo getNsCoreDictionaryVoForSearch(NsCoreDictionary dictionary) {
        NsCoreDictionary dictionaryInfo = dictionaryMapper.selectByDictionaryDdcode(dictionary);
        NsCoreDictionaryVo result = new NsCoreDictionaryVo();
        if(Objects.isNull(dictionaryInfo)){
            return result;
        }
        Map<String, Object> map = new HashMap<>();
        map.put("organizationId", dictionary.getOrganizationId());
        map.put("dictionaryId", dictionaryInfo.getJeCoreDictionaryId());
        map.put("dicName", dictionary.getDictionaryDdname());
        List<NsCoreDictionaryitem> dictionaryitems = dictionaryitemMapper.selectByDictionaryIdForSearch(map);
        List<NsCoreDictionaryitemVo> dictionaryitemVos = new ArrayList<>();
        for (NsCoreDictionaryitem dictionaryitem : dictionaryitems) {
            NsCoreDictionaryitemVo dictionaryitemVo = new NsCoreDictionaryitemVo();
            BeanUtils.copyProperties(dictionaryitem, dictionaryitemVo);
            dictionaryitemVos.add(dictionaryitemVo);
        }
        BeanUtils.copyProperties(dictionaryInfo, result);
        result.setDictionaryitemVos(dictionaryitemVos);
        return result;
    }

    @Override
    public Map<String, List<NsCoreDictionaryVo>> getNsCoreDictionaryVo(DictionaryDdcodeVo dictionaryDdcodeVo) {
//        NsCoreDictionary dictionaryInfo = dictionaryMapper.selectByDictionaryDdcode(dictionary);

        List<NsCoreDictionaryVo> nsCoreDictionaryVos = new ArrayList<>();
        List<NsCoreDictionary> dictionariyList = dictionaryMapper.listByDictionaryDdcode(dictionaryDdcodeVo);
        if (!CollectionUtils.isEmpty(dictionariyList)) {
            Map<String, Object> map1 = new HashMap<>();
            map1.put("organizationId", dictionaryDdcodeVo.getOrganizationId());
            map1.put("dictionaryList", dictionariyList);
            List<NsCoreDictionaryitem> dictionaryitems = dictionaryitemMapper.listByDictionaryId(map1);
            for (NsCoreDictionary dictionary : dictionariyList) {
                NsCoreDictionaryVo nsCoreDictionaryVo = new NsCoreDictionaryVo();
                BeanUtils.copyProperties(dictionary, nsCoreDictionaryVo);
                List<NsCoreDictionaryitemVo> dictionaryitemVos = new ArrayList<>();
                for (NsCoreDictionaryitem dictionaryitem : dictionaryitems) {
                    if (dictionary.getJeCoreDictionaryId().equals(dictionaryitem.getDictionaryitemDictionaryId())) {
                        NsCoreDictionaryitemVo dictionaryitemVo = new NsCoreDictionaryitemVo();
                        BeanUtils.copyProperties(dictionaryitem, dictionaryitemVo);
                        dictionaryitemVos.add(dictionaryitemVo);
                    }
                }
                nsCoreDictionaryVo.setDictionaryitemVos(dictionaryitemVos);
                nsCoreDictionaryVos.add(nsCoreDictionaryVo);
            }
        }
        Map<String, List<NsCoreDictionaryVo>> map = nsCoreDictionaryVos.stream().collect(Collectors.groupingBy(NsCoreDictionaryVo::getDictionaryDdcode));
        return map;
    }

	@Override
	public NsCoreDictionaryitem findById(Map<String, Object> map) {
		NsCoreDictionary nsCoreDictionary = dictionaryMapper.findById(map);
		HashMap<String, Object> mapParam = new HashMap<>();
		mapParam.put("dictionaryitemDictionaryId", nsCoreDictionary.getJeCoreDictionaryId());
		mapParam.put("dictionaryitemItemcode", map.get("code"));
		
		NsCoreDictionaryitem dictionaryitem = dictionaryitemMapper.findName(mapParam);
		return dictionaryitem;
	}

}
