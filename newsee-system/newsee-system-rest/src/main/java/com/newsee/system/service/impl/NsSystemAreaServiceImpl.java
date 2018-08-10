package com.newsee.system.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newsee.system.dao.NsSystemAreaMapper;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.service.INsSystemAreaService;
import com.newsee.system.vo.NsSystemAreaVo;

@Service
public class NsSystemAreaServiceImpl implements INsSystemAreaService {
	
	@Autowired
	private NsSystemAreaMapper systemAreaMapper;

	@Override
	public List<NsSystemArea> findAreaInfo(NsSystemArea area) throws Exception{
		if (null == area) 
			throw new NullPointerException("NsSystemArea 对象为 null");		
		Map<String, Object> map = new HashMap<String, Object>(8);
		map.put("id", area.getId());
		map.put("parentAreaCode", area.getParentAreaCode());
		map.put("areaLevel", area.getAreaLevel());
		map.put("areaCode", area.getAreaCode());
		List<NsSystemArea> list = systemAreaMapper.selectAreaInfo(map);
		return list;
	}

	@Override
	public List<NsSystemAreaVo> findAllArea() throws Exception{
		//查询数据
		List<NsSystemArea> list = systemAreaMapper.selectAllArea();
		
		//处理数据
		List<NsSystemAreaVo> provinceVos = null;
		if (!CollectionUtils.isEmpty(list)) {
			//省份数据
			List<NsSystemArea> provinces = list.stream().filter(area -> "1".equals(area.getAreaLevel())).collect(Collectors.toList());
			//市数据
			List<NsSystemArea> citys = list.stream().filter(area -> "2".equals(area.getAreaLevel())).collect(Collectors.toList());
			//区/县数据
			List<NsSystemArea> countys = list.stream().filter(area -> "3".equals(area.getAreaLevel())).collect(Collectors.toList());
			//街道/镇数据
			List<NsSystemArea> streets = list.stream().filter(area -> "4".equals(area.getAreaLevel())).collect(Collectors.toList());
			
			NsSystemAreaVo areaVo = null;
			//省份
			if (!CollectionUtils.isEmpty(provinces)) {
				provinceVos = new ArrayList<>(provinces.size());
				for (NsSystemArea area : provinces) {
					areaVo = new NsSystemAreaVo();
					areaVo.setLabel(area.getAreaName());
					areaVo.setValue(area.getAreaCode());					 
					provinceVos.add(areaVo);
				}
			}
			
			//城市
			if (!CollectionUtils.isEmpty(citys)) {
				this.dealData(provinceVos, citys);
			}
			
			//区县
			if (!CollectionUtils.isEmpty(countys)) {
				for (NsSystemAreaVo provice : provinceVos) {
					this.dealData(provice.getChildren(), countys);
				}
			}
		 
			//街道
			if (!CollectionUtils.isEmpty(streets)) {
				List<NsSystemAreaVo> subCitys = null;
				for (NsSystemAreaVo provice : provinceVos) {
					subCitys = provice.getChildren();
					for (NsSystemAreaVo subCity : subCitys) {
						this.dealData(subCity.getChildren(), streets);
					}
				}
			}
			
		}
		
		return provinceVos;
	}
	
	/**
	 * 处理区域数据
	 * @param datas
	 * @param subDatas
	 */
	private void dealData(List<NsSystemAreaVo> datas, List<NsSystemArea> subDatas) {
		NsSystemAreaVo areaVo = null;
		List<NsSystemAreaVo> tempVos = null;
		for (NsSystemAreaVo data : datas) {
			tempVos = new ArrayList<>(64);
			for (NsSystemArea sub : subDatas) {
				if (data.getValue().equals(sub.getParentAreaCode())) {
					areaVo = new NsSystemAreaVo();
					areaVo.setLabel(sub.getAreaName());
					areaVo.setValue(sub.getAreaCode());
					tempVos.add(areaVo);							
				}				
			}
			data.setChildren(tempVos);
		}
	}

	@Override
	public List<NsSystemArea> findAreaInfo(List<String> areaCodeList) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>(8);
		map.put("areaCodeList", areaCodeList);
		List<NsSystemArea> list = systemAreaMapper.selectAreaInfo(map);
		return list;
	}
	

}
