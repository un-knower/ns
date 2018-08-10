package com.newsee.system.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.constant.RedisKeysConstants;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.StringUtils;
import com.newsee.redis.util.RedisUtil;
import com.newsee.system.entity.NsSystemArea;
import com.newsee.system.service.INsSystemAreaService;
import com.newsee.system.vo.NsSystemAreaVo;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@ResponseBody
@RequestMapping("/area")
@Api(tags = {"com.newsee.system.controller.NsAreaController"}, description = "省/市/区选择  REST API")
public class NsAreaController {
	
	@Autowired
	private INsSystemAreaService systemAreaService;
	
	@Autowired
	private RedisUtil redisUtil;
   
    /**
     * 
     * @Title: detailMenu 
     * @Description: 省/市/区选择
     * @param menuId
     * @return RestResult    返回类型 
     * @throws Exception 
     * @throws 
     * @author sw
      */
	 @ApiOperation(value = "获取省/市/区, areaLevel, areaCode 非必填参数，都为空，查询所有数据")    
     @RequestMapping(value = "/area-funcinfo", method = RequestMethod.GET)
     public RestResult<List<NsSystemAreaVo>> areaFuncinfo(@ApiParam(value = "区域级别Level") @RequestParam(name="areaLevel", required=false) String areaLevel
    		 , @ApiParam(value = "区域编码，根据区域编码，获取其下的子区域") @RequestParam(name="areaCode", required=false) String areaCode) throws Exception{
         RestResult<List<NsSystemAreaVo>> restResult = null;
         if (!StringUtils.isBlank(areaLevel) || !StringUtils.isBlank(areaCode)) {
        	 NsSystemArea area = new NsSystemArea();
        	 area.setParentAreaCode(areaCode);
        	 area.setAreaLevel(areaLevel);
        	 List<NsSystemArea> list = systemAreaService.findAreaInfo(area);
        	 if (!CollectionUtils.isEmpty(list)) {
        		 NsSystemAreaVo areaVo = null;
        		 List<NsSystemAreaVo> tempVos = new ArrayList<>(list.size());
        		 for (NsSystemArea sub : list) {
        			areaVo = new NsSystemAreaVo();
 					areaVo.setLabel(sub.getAreaName());
 					areaVo.setValue(sub.getAreaCode());
 					tempVos.add(areaVo);
				 }
        		 restResult = new RestResult<List<NsSystemAreaVo>>(tempVos);
        	 }
        	 
         } else {
        	 List<NsSystemAreaVo> list = null;
        	 Object redisData = redisUtil.getObjectValue(RedisKeysConstants.REDIS_CACHE_PROVICE_CITY_CONTRY_PREFIX);
        	 if (null == redisData) {
        		 list = systemAreaService.findAllArea();         
        		 if (!CollectionUtils.isEmpty(list)) {
        			 redisUtil.setObjectValue(RedisKeysConstants.REDIS_CACHE_PROVICE_CITY_CONTRY_PREFIX, list);
        		 }
        		 restResult = new RestResult<List<NsSystemAreaVo>>(list);
        	 } else {
        		 @SuppressWarnings("unchecked")
        		 List<NsSystemAreaVo> temp = (List<NsSystemAreaVo>) redisData; 
        		 restResult = new RestResult<List<NsSystemAreaVo>>(temp);
        	 }
         }
         
         return restResult;
     }
     
     @ApiOperation(value = "根据areaCode获取区域信息")    
     @RequestMapping(value = "/getArea", method = RequestMethod.GET)
     public NsSystemArea getArea(@ApiParam(value = "区域编码，根据区域编码，获取区域信息") @RequestParam(name="areaCode") String areaCode) throws Exception{
         NsSystemArea area = new NsSystemArea();
         area.setAreaCode(areaCode);
         List<NsSystemArea> list = systemAreaService.findAreaInfo(area);
         area = null;
         if (!CollectionUtils.isEmpty(list)) {
        	 area = list.get(0);
         }
         
         return area;
     }
     
     @ApiOperation(value = "根据areaCode集合获取区域信息") 
     @RequestMapping(value = "/getAreas", method = RequestMethod.GET)
     public List<NsSystemArea> getAreaList(@RequestParam(name="areaCodeList") String areaCodeList) {
    	 List<NsSystemArea> list = null;
    	 if (!StringUtils.isBlank(areaCodeList)) {
    		 try {
				list = systemAreaService.findAreaInfo(Arrays.asList(areaCodeList.split(",")));
			} catch (Exception e) {
				new BizException(ResultCodeEnum.FAILURE.CODE, e.getMessage());
			}
    	 }
    	 
    	 return list;
     }
     
}
