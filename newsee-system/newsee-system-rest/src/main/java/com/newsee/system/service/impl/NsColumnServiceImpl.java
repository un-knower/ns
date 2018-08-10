package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newsee.system.dao.NsCoreResourcecolumnMapper;
import com.newsee.system.entity.NsCoreResourcecolumn;
import com.newsee.system.service.INsColumnService;
import com.newsee.common.vo.NsCoreResourcecolumnVo;

@Service
public class NsColumnServiceImpl implements INsColumnService {
    
    @Autowired
    NsCoreResourcecolumnMapper columnMapper;

    @Override
    public List<NsCoreResourcecolumnVo> list(Map<String, Object> map) {
       List<NsCoreResourcecolumn> columns = columnMapper.selectByMap(map);
       List<NsCoreResourcecolumnVo> columnVos = new ArrayList<>();
       for (NsCoreResourcecolumn column : columns) {
           NsCoreResourcecolumnVo columnVo = new NsCoreResourcecolumnVo();
           BeanUtils.copyProperties(column, columnVo);
           columnVos.add(columnVo);
       }
        return columnVos;
    }

    @Override
    public List<NsCoreResourcecolumnVo> listForRemote(NsCoreResourcecolumnVo nsCoreResourcecolumnVo) {
       List<NsCoreResourcecolumn> columns = columnMapper.selectByVo(nsCoreResourcecolumnVo);
       List<NsCoreResourcecolumnVo> columnVos = new ArrayList<>();
       for (NsCoreResourcecolumn column : columns) {
           NsCoreResourcecolumnVo columnVo = new NsCoreResourcecolumnVo();
           BeanUtils.copyProperties(column, columnVo);
           columnVos.add(columnVo);
       }
        return columnVos;
    }

    
}
