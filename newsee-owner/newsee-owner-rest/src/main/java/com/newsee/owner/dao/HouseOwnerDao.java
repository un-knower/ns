package com.newsee.owner.dao;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.newsee.owner.entity.HouseOwner;
import com.newsee.owner.vo.HouseOwnerVo;
import com.newsee.owner.vo.OwnerSerchConditionVo;


@Repository
public interface HouseOwnerDao {
    int deleteByPrimaryKey(Long id);

    int deleteBatchByIds(List<Long> ids);

    int insert(HouseOwner record);

    int insertSelective(HouseOwner record);

    HouseOwnerVo loadById(Long id);

    int updateByPrimaryKeySelective(HouseOwner record);

    int updateByPrimaryKey(HouseOwner record);

    int updateBatchByIds(HouseOwnerVo record);

    List<HouseOwnerVo> loadList(OwnerSerchConditionVo vo);

    List<HouseOwnerVo> loadByIdList(@Param("idList")List<Long> idList);
}
