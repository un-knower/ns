package com.newsee.owner.service.impl;

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
import com.newsee.owner.dao.BillBillDetailBusinessMapper;
import com.newsee.owner.dao.BillBillUsedDetailMapper;
import com.newsee.owner.dao.BillBillUsedMapper;
import com.newsee.owner.dao.WyglChargeChargePaymentMapper;
import com.newsee.owner.dao.WyglChargeCustomerChargeDetailMapper;
import com.newsee.owner.entity.BillBillDetailBusiness;
import com.newsee.owner.entity.BillBillUsedDetail;
import com.newsee.owner.entity.BillBillUsedWithBLOBs;
import com.newsee.owner.entity.WyglChargeChargePayment;
import com.newsee.owner.entity.WyglChargeCustomerChargeDetail;
import com.newsee.owner.service.IPressureTestService;
import com.newsee.owner.vo.WyglChargeChargePaymentVO;
import com.newsee.owner.vo.WyglChargeCustomerChargeDetailVO;

/**
 * Created by niyang on 2017/8/24.
 */
@Service
public class PressureTestServiceImpl implements IPressureTestService {

    @Autowired
    private BillBillUsedMapper billbillUsedMapper;

    @Autowired
    private WyglChargeChargePaymentMapper wyglChargeChargePaymentMapper;

    @Autowired
    private WyglChargeCustomerChargeDetailMapper wyglChargeCustomerChargeDetailMapper;

    @Autowired
    private BillBillDetailBusinessMapper billBillDetailBusinessMapper;

    @Autowired
    private BillBillUsedDetailMapper billBillUsedDetailMapper;


    @Override
//    @ReadDataSource
    public PageInfo<WyglChargeCustomerChargeDetail> listPage(WyglChargeCustomerChargeDetailVO vo) {
        PageInfo<WyglChargeCustomerChargeDetail> pageInfo = null;
        if (vo != null) {
            PageHelper.startPage(vo.getPageIndex(), vo.getPageSize());
            Map<String,Object> searchParam=new HashMap<>();
            searchParam.put("CustomerID",vo.getCustomerId());
            searchParam.put("IsPaid",vo.getPaid());
            List<WyglChargeCustomerChargeDetail> list = wyglChargeCustomerChargeDetailMapper.loadList(searchParam);
            pageInfo = new PageInfo<>(list);
        }
        return pageInfo;
    }

    @Override
    //@WriteDataSource
    public Boolean payTheFees(WyglChargeChargePaymentVO vo) {
        List<WyglChargeCustomerChargeDetail> detailList=vo.getDetailList();
        List<WyglChargeChargePayment> paymentList=new ArrayList<>();
        for (WyglChargeCustomerChargeDetail detailVo:detailList) {
            WyglChargeChargePayment wyglChargeChargePayment=new WyglChargeChargePayment();
            BeanUtils.copyProperties(detailVo,wyglChargeChargePayment);
            paymentList.add(wyglChargeChargePayment);
        }
        wyglChargeChargePaymentMapper.insertList(paymentList);

        wyglChargeCustomerChargeDetailMapper.updateByCustomerId(vo.getCustomerId());


        BillBillUsedWithBLOBs billBillUsedWithBLOBs=new BillBillUsedWithBLOBs();
        billBillUsedWithBLOBs.setBillcode(UUID.randomUUID().toString());
        billbillUsedMapper.insertSelective(billBillUsedWithBLOBs);

        BillBillUsedDetail billBillUsedDetail=new BillBillUsedDetail();
        billBillUsedDetail.setRemark("test");
        billBillUsedDetailMapper.insertSelective(billBillUsedDetail);

        BillBillDetailBusiness billBillDetailBusiness=new BillBillDetailBusiness();
        billBillDetailBusiness.setTablename("Wygl_Charge_ChargePayment");
        billBillDetailBusiness.setBilldetailid(billBillUsedDetail.getId());
        billBillDetailBusiness.setBusinessid(1L);
        billBillDetailBusinessMapper.insertSelective(billBillDetailBusiness);
        return true;
    }

}
