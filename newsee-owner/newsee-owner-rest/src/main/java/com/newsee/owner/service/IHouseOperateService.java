package com.newsee.owner.service;


import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.newsee.common.vo.SearchVo;
import com.newsee.owner.vo.HouseListVo;
import com.newsee.owner.vo.HouseOperateSalesVo;
import com.newsee.system.vo.NsCoreDictionaryVo;

public interface IHouseOperateService {
    
    public long addSalse(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap, boolean doTake) throws Exception;
    
    public long addTake(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap, boolean hasSales) throws Exception;
    
    public long addCheckIn(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception;
    
    public long addEmpty(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception;
    
    public long addMoveOut(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception;
    
    public long addRent(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap) throws Exception;
    
    public long addSublet(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap) throws Exception;
    
    public long addRentOut(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName, Map<String, NsCoreDictionaryVo> dicMap) throws Exception;
    
    public long addDecorate(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception;
    
    /**
     * 
    * @Title: editHouseStage 
    * @Description: 修改房态
    * @param houseOperateTypeEnum
    * @param houseId
    * @param userId
    * @return int    返回类型 
    * @date  2017年11月20日 下午5:20:31
    * @author wangrenjie
     */
    public int editHouseStage(HouseOperateSalesVo houseOperateSalesVo, Long userId, String userName) throws Exception;
    
    /**
     * 
    * @Title: detailHouseStage 
    * @Description: 房态操作详情 
    * @param houseId
    * @param detailId
    * @return HouseOperateSalesVo    返回类型 
    * @date  2017年11月24日 下午3:07:24
    * @author wangrenjie
     */
    public HouseOperateSalesVo detailHouseStage(Long houseId, Long detailId, String houseOperateType, Byte isEdit) throws Exception;
    
    /**
     * 
    * @Title: listPage 
    * @Description: 房态操作列表
    * @param searchVo
    * @param houseOperateType
    * @return PageInfo<HouseListEntity>    返回类型 
    * @date  2017年11月24日 下午3:07:30
    * @author wangrenjie
     */
    public PageInfo<HouseListVo> listPage(SearchVo searchVo, String houseOperateType, Map<String, Object> columnMap, boolean pageFlag) throws Exception;
    
    public HouseListVo getTotal(SearchVo searchConditionVo, String houseOperateType, Map<String, Object> columnMap) throws Exception;

    /**
     * 
    * @Title: getDeveloperByHousePath 
    * @Description: 获取房产开发商 
    * @param houseId
    * @return Long    返回类型 
    * @date  2017年11月17日 下午4:25:24
    * @author wangrenjie
     */
    public Long getDeveloperByHousePath(Long houseId) throws Exception;
    
    /**
     * 
    * @Title: checkStageOperate 
    * @Description: 判断房产当前状态下是否可以进行当前操作
    * @param houseId
    * @return boolean    返回类型 
    * @date  2017年11月24日 下午3:09:04
    * @author wangrenjie
     */
    public Map<String, Object> checkStageOperate(Long detailId, Long houseId, String houseOperateType) throws Exception;
    
    public boolean revokeStageDetail(Long houseId, Long detailId, Long userId, String userName) throws Exception;
    
    public int editDecorateStageTask();
}
