package com.newsee.custom.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.apache.commons.collections.CollectionUtils;

import com.newsee.common.entity.SelectEntity;
import com.newsee.system.vo.NsCoreDictionaryVo;
import com.newsee.system.vo.NsCoreDictionaryitemVo;

public class BussinessUtils {
    
    /**
     * 将数据字典类实体list转换成下拉实体list
     * @param dictionaryList
     * @return
     */
    public static List<SelectEntity> getSelectedEntity(NsCoreDictionaryVo dictionaryVo){
        List<SelectEntity> selectList = new ArrayList<SelectEntity>();
        if(Objects.isNull(dictionaryVo)){
        	return selectList;
        }
        List<NsCoreDictionaryitemVo> dictionaryitemVoList = dictionaryVo.getDictionaryitemVos();
        if(!CollectionUtils.isEmpty(dictionaryitemVoList)){
            for(NsCoreDictionaryitemVo dictionary : dictionaryitemVoList){
                SelectEntity select = new SelectEntity();
                select.setLabel(dictionary.getDictionaryitemItemname());
                select.setValue(dictionary.getDictionaryitemItemcode());
                selectList.add(select);
            }
        }
        return selectList;
    }
}
