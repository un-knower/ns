package com.newsee.soss.dao;

import java.util.List;

import com.newsee.soss.entity.NsSossProductOrderRecord;

public interface NsSossProductOrderRecordMapper {
	/**添加新的订单*/
	int insert(NsSossProductOrderRecord record);
	/**根据订单ID更新订单记录*/
	int updateByOrderID(NsSossProductOrderRecord record);
	/**查询订单记录表*/
	List<NsSossProductOrderRecord> selectOrderRecords(List<Long> orderProductIdList);
	/**根据订单ID查询订单信息*/
	NsSossProductOrderRecord selectOrderRecordByOrderID(Long id);
}
