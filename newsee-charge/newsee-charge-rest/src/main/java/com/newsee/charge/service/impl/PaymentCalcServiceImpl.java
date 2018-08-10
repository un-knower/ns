package com.newsee.charge.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.newsee.charge.dao.*;
import com.newsee.charge.enmus.HouseStandrdAduitStatus;
import com.newsee.charge.entity.*;
import com.newsee.charge.service.IGoodsTaxRateService;
import com.newsee.charge.service.IPaymentCalcService;
import com.newsee.charge.service.remote.IOwnerRemoteService;
import com.newsee.charge.service.remote.ISystemRemoteService;
import com.newsee.charge.service.remote.IWebSocketRemoteService;
import com.newsee.charge.vo.HouseStandardAddVo;
import com.newsee.charge.vo.PaymentCalcExcelVo;
import com.newsee.charge.vo.PaymentCalcVo;
import com.newsee.charge.vo.PaymentCalcVoImportExcel;
import com.newsee.common.constant.Constants;
import com.newsee.common.exception.BizException;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.DateUtils;
import com.newsee.common.utils.ExcelUploadUtil;
import com.newsee.common.utils.ExcelUtil;
import com.newsee.common.vo.SearchVo;
import com.newsee.database.annotation.ReadDataSource;
import com.newsee.database.annotation.WriteDataSource;
import com.newsee.database.util.DataSourceContextHolder;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.entity.OwnerHouseHouseInfo;
import com.newsee.owner.vo.CustomerVo;

import org.apache.commons.collections.CollectionUtils;
import org.quartz.CronExpression;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Period;
import java.time.ZoneId;
import java.util.*;
import java.util.stream.Collectors;

import static com.google.common.collect.Lists.newArrayList;
import static com.google.common.collect.Lists.reverse;
import static org.apache.commons.lang.StringUtils.endsWith;
import static org.apache.commons.lang.StringUtils.isNotBlank;
import static org.apache.commons.lang.time.DateUtils.addDays;
import static org.apache.commons.lang.time.DateUtils.addMonths;

@Service
public class PaymentCalcServiceImpl implements IPaymentCalcService {

    @Autowired
    private ChargeCustomerChargeCalcTaskMapper chargeCustomerChargeCalcTaskMapper;
    @Autowired
    private ChargeCustomerChargeCalcTaskChargeItemMapper chargeCustomerchargecalctaskChargeitemMapper;
    @Autowired
    private ChargeCustomerChargeCalcTaskHouseMapper chargeCustomerchargecalctaskHouseMapper;
    @Autowired
    private ChargeCalcPlanQuartzMapper chargeCalcPlanQuartzMapper;
    @Autowired
    private ChargeHouseChargeStandardMapper chargeHouseChargeStandardMapper;
    @Autowired
    private ChargeChargeItemMapper chargeChargeItemMapper;
    @Autowired
    private ChargeChargeStandardMapper chargeChargeStandardMapper;
    @Autowired
    private ChargeCustomerChargeDetailMapper chargeCustomerChargeDetailMapper;
    @Autowired
    private ChargeHouseChargeStandardCustomerMapper chargeHouseChargeStandardCustomerMapper;
    @Autowired
    private ChargeChargeStandardLadderMapper chargeChargeStandardLadderMapper;
    @Autowired
    private ISystemRemoteService iSystemRemoteService;
    @Autowired
    private IOwnerRemoteService iOwnerRemoteService;
    @Autowired
    private ChargeCalcLogMapper chargeCalcLogMapper;
    @Autowired
    private IGoodsTaxRateService goodsTaxRateService;
    @Autowired
    private IWebSocketRemoteService webSocketRemoteService;


    private static final Date MAX_DATE = new Date(99999999999999999L);// Sat Nov
    // 07
    // 17:46:39
    // CST
    // 3170843

    /**
     * 计算一个任务的费用
     *
     * @param planId
     */
    @WriteDataSource
    public void calculateCost(Long planId) {

        // 查询该任务的相关设置信息
        ChargeCustomerChargeCalcTask task = chargeCustomerChargeCalcTaskMapper.selectById(planId);
        // 查询出该任务下所有参与这次计费的房间信息
        List<ChargeCustomerChargeCalcTaskHouse> houses = chargeCustomerchargecalctaskHouseMapper.selectByTaskId(planId);
        // 查询出该任务下所有的计费科目
        List<ChargeCustomerChargeCalcTaskChargeItem> items = chargeCustomerchargecalctaskChargeitemMapper
                .selectByTaskId(planId);
        ChargeChargeItem chargeChargeItem = null;
        //查询该计划执行的次数最大值
        Integer counts = chargeCustomerChargeDetailMapper.findPlanExceuteNumbers(planId);
        //设置本次计算次数
        task.setSequence(1);
        for (ChargeCustomerChargeCalcTaskHouse house : houses) {
            try {
                for (ChargeCustomerChargeCalcTaskChargeItem item : items) {
                    try {
                        /** 查询出该房子对该科目设置的收费标准信息 */
                        chargeChargeItem = chargeChargeItemMapper.selectById(item.getChargeItem());
                        item.setChargeItemName(chargeChargeItem.getChargeItemName());
                        HouseStandardAddVo houseStandardAddVo = new HouseStandardAddVo();
                        houseStandardAddVo.setChargeItemId(item.getChargeItem());
                        houseStandardAddVo.setHouseId(house.getHouseId());
                        houseStandardAddVo.setEnterpriseId(house.getEnterpriseId());
                        houseStandardAddVo.setOrganizationId(house.getOrganizationId());
                        ChargeHouseChargeStandard houseChargeStandard = chargeHouseChargeStandardMapper.findHouseStandard(houseStandardAddVo);
                        ChargeChargeStandard standard = null;
                        /** 查询出该房子对该科目设置的收费标准信息 */
                        if (Objects.isNull(houseChargeStandard)) {
                            // 该房子对该科目未设置房产收费标准，不参与计算
                            String desc = item.getChargeItemName() + "-" + "生成失败。原因：收费标准未设置";
                            addChargeCalcLog(task.getSequence(), desc, 0D, task.getId(), 1, task.getEnterpriseId(),
                                    task.getOrganizationId());
                            continue;
                        } else {
                            // 查询出该收费标准的详细信息
                            standard = chargeChargeStandardMapper.selectById(houseChargeStandard.getStandardId());
                        }
                        // 收费周期
                        int period = Integer.valueOf(standard.getChargeType());
                        // 获取时间区间
                        Date[] interDate = getInterDate(task, house, period, item, houseChargeStandard, standard);
                        if (Objects.isNull(interDate)) {
                            // 插入日志 已经超出了房间收费标准里的区间
                            String desc = item.getChargeItemName() + "-" + "生成失败。原因：超出房间收费标准中的区间";
                            addChargeCalcLog(task.getSequence(), desc, 0D, task.getId(), 1, task.getEnterpriseId(),
                                    task.getOrganizationId());
                            continue;
                        }

                        // 按照1计算到每月 0自然月 进行拆分
                        if ("0".equals(standard.getChargeSplitType())) {
                            // 进行自然月拆分
                            Period p = Period.between(
                                    interDate[0].toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                    interDate[1].toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                            Date calcStartDate = null;
                            Date calcEndDate = null;
                            for (int month = 1; month <= p.toTotalMonths() + 1; month++) {
                                try {
                                    if (month == 1) {
                                        calcStartDate = interDate[0];
                                        calcEndDate = DateUtils.getLastDateByYmdType(calcStartDate, "MONTH");
                                    }
                                    // 插入详情
                                    addChargeCustomerChargeDetail(task, house.getHouseId(), standard, item,
                                            houseChargeStandard, calcStartDate, calcEndDate);
                                    task.setTaskStatus("费用已生成");
                                    // 计费开始时间月初，计费结束时间月末,最后计费结束时间就是最后的时间
                                    calcStartDate = addDays(calcEndDate, 1);
                                    calcEndDate = DateUtils.getLastDateByYmdType(calcStartDate, "MONTH");
                                    if (calcEndDate.compareTo(interDate[1]) >= 0) {
                                        calcEndDate = interDate[1];
                                    }
                                } catch (Exception e) {
                                    String desc = "任务名称：" + task.getTaskName() + ",项目名称：" + task.getPrecinctName() + "房间id：" + house.getHouseId() + "收费科目id：" + item.getChargeItem() + item.getChargeItemName() + "-" + "生成失败。" + (e.getMessage().length() > 2000 ? e.getMessage().substring(0, 2000) : e.getMessage());
                                    addChargeCalcLog(task.getSequence(), desc, 0D, task.getId(), 1, task.getEnterpriseId(), task.getOrganizationId());
                                }
                            }
                        } else if ("1".equals(standard.getChargeSplitType())) {
                            // 按照月份拆分
                            Period p = Period.between(
                                    interDate[0].toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                    interDate[1].toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                            Date calcStartDate = null;
                            Date calcEndDate = null;
                            // 按照间隔多少个月进行拆分数据
                            for (int month = 1; month <= p.toTotalMonths(); month++) {
                                try {
                                    // 进行月拆分
                                    if (month == 1) {
                                        calcStartDate = interDate[0];
                                        calcEndDate = addMonths(calcStartDate, 1);
                                    }
                                    // 插入详情到
                                    addChargeCustomerChargeDetail(task, house.getHouseId(), standard, item,
                                            houseChargeStandard, calcStartDate, calcEndDate);
                                    task.setTaskStatus("费用已生成");
                                    // 计费开始时间月初，计费结束时间月末,最后计费结束时间就是最后的时间
                                    calcStartDate = addDays(calcEndDate, 1);
                                    calcEndDate = addMonths(calcStartDate, 1);
                                    if (calcEndDate.compareTo(interDate[1]) >= 0) {
                                        calcEndDate = interDate[1];
                                    }
                                } catch (Exception e) {
                                    String desc = "任务名称：" + task.getTaskName() + ",项目名称：" + task.getPrecinctName() + "房间id：" + house.getHouseId() + "收费科目id：" + item.getChargeItem() + item.getChargeItemName() + "-" + "生成失败。" + (e.getMessage().length() > 2000 ? e.getMessage().substring(0, 2000) : e.getMessage());
                                    addChargeCalcLog(task.getSequence(), desc, 0D, task.getId(), 1, task.getEnterpriseId(), task.getOrganizationId());

                                }
                            }
                        } else {
                            // 按照周期拆分
                            Period p = Period.between(
                                    interDate[0].toInstant().atZone(ZoneId.systemDefault()).toLocalDate(),
                                    interDate[1].toInstant().atZone(ZoneId.systemDefault()).toLocalDate());

                            Date calcStartDate = null;
                            Date calcEndDate = null;
                            long periodNum = p.toTotalMonths() / period;
                            // 按照间隔多少个月进行拆分数据
                            for (int month = 1; month <= periodNum; month++) {
                                try {
                                    // 进行月拆分
                                    if (month == 1) {
                                        calcStartDate = interDate[0];
                                        calcEndDate = addMonths(calcStartDate, period);
                                    }
                                    // 插入详情到
                                    addChargeCustomerChargeDetail(task, house.getHouseId(), standard, item,
                                            houseChargeStandard, calcStartDate, calcEndDate);
                                    task.setTaskStatus("费用已生成");
                                    // 计费开始时间月初，计费结束时间月末,最后计费结束时间就是最后的时间
                                    calcStartDate = addDays(calcEndDate, 1);
                                    calcEndDate = addMonths(calcStartDate, period);
                                    if (calcEndDate.compareTo(interDate[1]) >= 0) {
                                        calcEndDate = interDate[1];
                                    }
                                } catch (Exception e) {
                                    String desc = "任务名称：" + task.getTaskName() + ",项目名称：" + task.getPrecinctName() + "房间id：" + house.getHouseId() + "收费科目id：" + item.getChargeItem() + item.getChargeItemName() + "-" + "生成失败。" + (e.getMessage().length() > 2000 ? e.getMessage().substring(0, 2000) : e.getMessage());
                                    addChargeCalcLog(task.getSequence(), desc, 0D, task.getId(), 1, task.getEnterpriseId(), task.getOrganizationId());

                                }
                            }

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        addChargeCalcLog(task.getSequence(), e.getMessage().length() > 2000 ? e.getMessage().substring(0, 2000) : e.getMessage(), 0D, task.getId(), 1, task.getEnterpriseId(),
                                task.getOrganizationId());
                    }
                }
            } catch (Exception e) {
                addChargeCalcLog(task.getSequence(), e.getMessage(), 0D, task.getId(), 1, task.getEnterpriseId(), task.getOrganizationId());
            }
        }

    }

    /**
     * 获取计费区间
     *
     * @param task                应收款计算任务
     * @param house               应收款计算任务-房产
     * @param item                应收款计算任务-收费科目
     * @param period              周期
     * @param houseChargeStandard 收费标准
     * @return 计费区间
     */
    private Date[] getInterDate(ChargeCustomerChargeCalcTask task, ChargeCustomerChargeCalcTaskHouse house, int period,
                                ChargeCustomerChargeCalcTaskChargeItem item, ChargeHouseChargeStandard houseChargeStandard, ChargeChargeStandard standard) {
        // 时间区间
        Date[] interDate = new Date[2];
        Date start1, end1, start2, end2;
        end2 = Objects.isNull(houseChargeStandard.getChargeEndTime()) ? MAX_DATE : houseChargeStandard.getChargeEndTime();

        if (HouseStandrdAduitStatus.TASK_AUTO.getTitle().equals(task.getTaskType())) {//自动计划算费
            // 得到房间收费标准中设置的计费开始时间结束时间
            interDate[0] = houseChargeStandard.getChargeStartTime();
            interDate[1] = end2;
            //“本月、次月”这个时间段进行比较，只算本月和次月，如果房产收费开始时间和房产收费结束时间没有交集，那么算费失败
            interDate = compareCurrentMonth(task, interDate);
        } else {//手动算费
            if (HouseStandrdAduitStatus.CycleTypeAppoint.getTitle().equals(task.getChargeCycleType())) {// 任务中设置的是指定周期
                // 得到任务设置中的指定开始时间和结束时间
                start1 = task.getChargeCycleStartDate();
                end1 = task.getChargeCycleEndDate();
                // 得到房间收费标准中设置的计费开始时间结束时间
                start2 = houseChargeStandard.getChargeStartTime();
                interDate = getTimeInterval(start1, end1, start2, end2);

                if (!Objects.isNull(interDate)) {
                    Date startDate = null;
                    for (startDate = interDate[0]; addMonths(interDate[0], period).compareTo(interDate[1]) <= 0; startDate = addDays(addMonths(interDate[0], period), 1))
                        ;
                    interDate[1] = addDays(startDate, -1);
                }
            } else if (HouseStandrdAduitStatus.CycleTypeContinue.getTitle().equals(task.getChargeCycleType())) {//延续
                //手动算费-延续
                // 查询出该房间该收费项目的最后算费时间
                ChargeCustomerChargeDetail detail = new ChargeCustomerChargeDetail();
                detail.setHouseId(house.getHouseId());
                detail.setChargeItemId(item.getChargeItem());
                ChargeCustomerChargeDetail last = chargeCustomerChargeDetailMapper.selectByHouseAndItem(detail);
                // 非第一次计算费用
                if (!Objects.isNull(last)) {
                    interDate[0] = addDays(last.getCalcEndDate(), 1);
                } else {
                    // 如果没有算过费用，则收费开始时间为房间收费标准中设置的计费开始时间
                    interDate[0] = houseChargeStandard.getChargeStartTime();
                }
                // 根据收费标准中设置的收费周期算计费结束时间
                interDate[1] = addDays(addMonths(interDate[0], period), -1);

                // 根据计费开始时间 和计费结束时间算 计费时间区间
                interDate = getTimeInterval(interDate[0], interDate[1], houseChargeStandard.getChargeStartTime(), end2);
            }
        }
        //判断该收费标准的生效区间是否合法
        if (!Objects.isNull(interDate)) {
            Date lineDate = Objects.isNull(standard.getCancelDate()) ? MAX_DATE : standard.getCancelDate();
            return lineDate.compareTo(interDate[1]) >= 0 ? interDate : null;
        }

        return interDate;
    }

    /**
     * 求出的时间区间和“本月/次月”这个时间区间进行比较，
     * interDate = 房产收费设置中的计费开始时间和计费结束时间
     * interDate和“本月”、“次月”求交集
     *
     * @param task
     */
    private Date[] compareCurrentMonth(ChargeCustomerChargeCalcTask task, Date[] interDate) {
        Date currentMonthStartDate, currentMonthEndDate;

        if ("本月".equals(task.getChargeStartMonth())) {
            Date now = new Date();
            currentMonthStartDate = DateUtils.getStartDateByYMD(now, "MONTH");
            currentMonthEndDate = DateUtils.getLastDateByYmdType(now, "MONTH");

        } else if ("次月".equals(task.getChargeStartMonth())) {
            //次月
            Date nextMonth = addMonths(new Date(), 1);
            currentMonthStartDate = DateUtils.getStartDateByYMD(nextMonth, "MONTH");
            currentMonthEndDate = DateUtils.getLastDateByYmdType(nextMonth, "MONTH");
        } else {
            return interDate;
        }
        Date[] timeInterval = getTimeInterval(currentMonthStartDate, currentMonthEndDate, interDate[0], interDate[1]);
        return timeInterval;

    }


    /**
     * 添加应收款费用详情
     *
     * @param task                 应收款计算任务
     * @param houseId              应收款计算任务-房产
     * @param chargeChargeStandard 收费标准
     * @param taskChargeItem       应收款计算任务-科目
     * @param houseChargeStandard  房间收费标准
     * @param calcStartDate        计费开始时间
     * @param calcEndDate          计费结束时间
     * @throws Exception
     */
    private void addChargeCustomerChargeDetail(ChargeCustomerChargeCalcTask task, Long houseId,
                                               ChargeChargeStandard chargeChargeStandard, ChargeCustomerChargeCalcTaskChargeItem taskChargeItem,
                                               ChargeHouseChargeStandard houseChargeStandard, Date calcStartDate, Date calcEndDate) throws Exception {
        // 查询收费对象
        Map<String, Object> map = Maps.newHashMap();
        map.put("enterpriseId", houseChargeStandard.getEnterpriseId());
        map.put("organizationId", houseChargeStandard.getOrganizationId());
        map.put("houseChargeId", houseChargeStandard.getId());
        map.put("houseId", houseChargeStandard.getHouseId());
        List<ChargeHouseChargeStandardCustomer> standardCustomerList = chargeHouseChargeStandardCustomerMapper
                .selectByHouseChargeId(map);

        // 查询收费标准中的单价
        HashMap<String, Object> para = Maps.newHashMap();
        para.put("id", chargeChargeStandard.getId());
        para.put("type", "standard");
        List<ChargeChargeStandardLadder> chargeChargeStandardLadders = chargeChargeStandardLadderMapper.selectById(para);
        //获取房产信息
        OwnerHouseHouseInfo houseHouseInfo = iOwnerRemoteService.getHouseHouseInfo(houseId).getResultData();
//        HouseViewVo houseViewVo = iOwnerRemoteService.detailHouseView(houseId).getResultData();
        //计算总金额
        Double totalAmount = calculateTotalAmount(chargeChargeStandard, houseHouseInfo);
        //按照自然月拆分的可能不足一个月
        if ("0".equals(chargeChargeStandard.getChargeSplitType())) {
            //该区间不是月初或者月末 按照天数算
            Date monthLastDate = DateUtils.getLastDateByYmdType(calcEndDate, "MONTH");
            Date monthStartDate = DateUtils.getStartDateByYMD(calcStartDate, "MONTH");
            if (calcEndDate.compareTo(monthLastDate) != 0 || calcStartDate.compareTo(monthStartDate) != 0) {
                Period p = Period.between(calcStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), calcEndDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
                int totalDay = p.getDays() + 1;
                int currentDay = Period.between(monthStartDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate(), monthLastDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate()).getDays() + 1;
                totalAmount = totalAmount * totalDay / currentDay;
            }
        }
        if (!CollectionUtils.isEmpty(standardCustomerList)) {
            for (ChargeHouseChargeStandardCustomer standardCustomer : standardCustomerList) {
                try {
                    ChargeCustomerChargeDetail detail = new ChargeCustomerChargeDetail();
                    detail.setEnterpriseId(task.getEnterpriseId());
                    detail.setOrganizationId(task.getOrganizationId());
                    detail.setTaskId(task.getId());
                    detail.setHouseId(houseId);
                    detail.setChargeItemName(taskChargeItem.getChargeItemName());
                    detail.setHouseName(houseHouseInfo.getRoomShortName());
                    detail.setPreinctId(task.getPrecinctId());
                    detail.setPreinctName(task.getPrecinctName());
                    detail.setOwnerId(standardCustomer.getOwnerId());
                    detail.setOwnerName(standardCustomer.getOwnerName());
                    detail.setPaidOwnerType(standardCustomer.getOwnerType());
                    detail.setChargeItemId(taskChargeItem.getChargeItem());
                    detail.setChargeItemName(taskChargeItem.getChargeItemName());
                    detail.setChargeId(houseChargeStandard.getStandardId());
                    detail.setChargeName(houseChargeStandard.getStandardName());
                    detail.setSequence(task.getSequence());
                    if (!CollectionUtils.isEmpty(chargeChargeStandardLadders)) {
                        ChargeChargeItem chargeChargeItem = chargeChargeItemMapper.selectById(taskChargeItem.getChargeItem());
                        // 查询单位具体值
                        HashMap<String, Object> unitMap = new HashMap<String, Object>();
                        unitMap.put("dictionaryDictype", "charge");
                        unitMap.put("dictionaryDdcode", "Unit");
                        unitMap.put("code", chargeChargeItem.getUnit());
                        RestResult<String> result = iSystemRemoteService.findDictionaryName(unitMap);
                        String unit = Objects.isNull(result) ? "" : result.getResultData();
                        ChargeChargeStandardLadder chargeChargeStandardLadder = chargeChargeStandardLadders.get(0);
                        detail.setPrice(Double.parseDouble(chargeChargeStandardLadder.getLadderPrice()));
                        detail.setChargeItemPrice(chargeChargeStandardLadder.getLadderPrice() + unit);
                    }
                    // 查询房产信息

                    if (houseHouseInfo != null) {
                        detail.setAmount(CommonUtils.long2DecimalHasNull(houseHouseInfo.getChargingArea()).doubleValue());
                    }
                    detail.setCalcStartDate(calcStartDate);
                    detail.setCalcEndDate(calcEndDate);
                    // 应收款日期
                    if ("1".equals(chargeChargeStandard.getChargeMonthType())) {
                        detail.setShouldChargeDate(
                                addMonths(calcStartDate, Integer.parseInt(chargeChargeStandard.getChargeMonth())));
                    } else {
                        detail.setShouldChargeDate(
                                addMonths(calcEndDate, Integer.parseInt(chargeChargeStandard.getChargeMonth())));
                    }
                    // 0:月末|1:月初|2:指定日期|3:计费开始日期后
                    if ("0".equals(chargeChargeStandard.getChargeDayType())) {
                        detail.setShouldChargeDate(
                                DateUtils.getLastDateByYmdType(detail.getShouldChargeDate(), "MONTH"));
                    } else if ("1".equals(chargeChargeStandard.getChargeDayType())) {
                        detail.setShouldChargeDate(DateUtils.getStartDateByYMD(detail.getShouldChargeDate(), "MONTH"));
                    } else if ("2".equals(chargeChargeStandard.getChargeDayType())) {
                        Calendar calendar = Calendar.getInstance();
                        calendar.setTime(DateUtils.getLastDateByYmdType(detail.getShouldChargeDate(), "MONTH"));
                        int year = calendar.get(Calendar.YEAR);
                        int month = calendar.get(Calendar.MONTH);
                        int lastDay = calendar.get(Calendar.DAY_OF_MONTH);
                        //这里可能出现大于实际的月份最大值，如day=30,2月份只有28天
                        int day = Integer.parseInt(chargeChargeStandard.getChargeDay());
                        if (lastDay < day) {
                            calendar.set(year, month, lastDay);
                        } else {
                            calendar.set(year, month, day);
                        }
                        detail.setShouldChargeDate(calendar.getTime());
                    } else {
                        detail.setShouldChargeDate(
                                addDays(calcEndDate, Integer.parseInt(chargeChargeStandard.getChargeDay())));
                    }
                    double actualChargeSum = totalAmount * (Double.parseDouble(standardCustomer.getBearRatio()) / 100);//占有的百分比
                    detail.setActualChargeSum(actualChargeSum);//实际应收
                    detail.setChargeSum(totalAmount);//应收金额
                    detail.setPaidChargeSum(0D);
                    detail.setIsCheck("未审核");//未审核
                    setTax(task, taskChargeItem, detail, actualChargeSum);

                    chargeCustomerChargeDetailMapper.insert(detail);
                    // 插入成功日志
                    String desc = taskChargeItem.getChargeItemName() + "-" + "生成成功";
                    addChargeCalcLog(task.getSequence(), desc, actualChargeSum, task.getId(), 0, task.getEnterpriseId(),
                            task.getOrganizationId());
                } catch (Exception exception) {
                    // 插入失败日志
                    String desc = "任务名称：" + task.getTaskName() + ",项目名称：" + task.getPrecinctName() + "房间id：" + houseId + "收费科目id：" + taskChargeItem.getChargeItem() + taskChargeItem.getChargeItemName() + "-" + "生成失败。" + (exception.getMessage().length() > 2000 ? exception.getMessage().substring(0, 2000) : exception.getMessage());
                    addChargeCalcLog(task.getSequence(), desc, 0D, task.getId(), 1, task.getEnterpriseId(), task.getOrganizationId());
                }
            }

        } else {
            String desc = "任务名称：" + task.getTaskName() + ",项目名称：" + task.getPrecinctName() + "房间id：" + houseId + "收费科目id：" + taskChargeItem.getChargeItem() + taskChargeItem.getChargeItemName() + "-" + "生成失败。原因：收费对象未设置";
            addChargeCalcLog(task.getSequence(), desc, 0D, task.getId(), 1, task.getEnterpriseId(), task.getOrganizationId());
        }

    }

    /**
     * 设置税额
     *
     * @param task
     * @param taskChargeItem
     * @param detail
     * @param actualChargeSum
     */
    private void setTax(ChargeCustomerChargeCalcTask task, ChargeCustomerChargeCalcTaskChargeItem taskChargeItem, ChargeCustomerChargeDetail detail, double actualChargeSum) {
        //设置税额，查询项目的税率
        ChargeGoodsTaxRate chargeGoodsTaxRate = goodsTaxRateService.getChargeGoodsTaxRate(task.getEnterpriseId(), taskChargeItem.getChargeItem(), task.getPrecinctId());
        if (chargeGoodsTaxRate != null) {
            //税额=应收金额/(1+项目的税率)*项目的税率
            Double taxRate = chargeGoodsTaxRate.getTaxRate() == null ? 0d : chargeGoodsTaxRate.getTaxRate() / 100;
            double tax = actualChargeSum / (1 + taxRate) * taxRate;
            detail.setTax(tax);
        } else {
            detail.setTax(0d);
        }
    }

    /**
     * 计算总金额
     *
     * @param chargeChargeStandard
     * @return
     * @throws Exception
     */
    private Double calculateTotalAmount(ChargeChargeStandard chargeChargeStandard, OwnerHouseHouseInfo houseHouseInfo) throws Exception {
        Double totalAmount = 0d;
        String expressions = chargeChargeStandard.getExpressions();
        if (isNotBlank(expressions)) {
            expressions = expressions.substring(expressions.indexOf("=") + 1);
            expressions = expressions.replace("收费标准_单价", "price");
            expressions = expressions.replace("房产_计费面积", "chargeArea");
            expressions = expressions.replace("房间_所处楼层", "floor");
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
//            engine.put("price", Double.parseDouble(chargeChargeStandard.getPrice()));
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", chargeChargeStandard.getId());
            map.put("type", "standard");
            List<ChargeChargeStandardLadder> chargeChargeStandardLadders = chargeChargeStandardLadderMapper.selectById(map);
            if (!CollectionUtils.isEmpty(chargeChargeStandardLadders)) {
                engine.put("price", chargeChargeStandardLadders.get(0).getLadderPrice());
            } else {
                throw new Exception("单价未设置！");
            }
            engine.put("chargeArea", CommonUtils.long2DecimalHasNull(houseHouseInfo.getChargingArea()).doubleValue());
            engine.put("floor", houseHouseInfo.getFloor());

            try {
                totalAmount = (Double) engine.eval(expressions);
            } catch (Exception e) {
                throw new Exception("面积公式设置不合理！");
            }
            //四舍五入new BigDecimal
            totalAmount = detailAmount(chargeChargeStandard, totalAmount);
        }
        return totalAmount;
    }


    /**
     * \ 两个时间的交集 wangjun
     *
     * @param start1
     * @param end1
     * @param start2
     * @param end2
     * @return
     */
    public Date[] getTimeInterval(Date start1, Date end1, Date start2, Date end2) {
        Date[] intervalDate = new Date[2];
        Comparator<Date> comparator = Comparator.naturalOrder();

        if (comparator.compare(start1, end2) >= 0 || comparator.compare(start2, end1) >= 0) {
            // 不存在交集
            return null;
        }
        intervalDate[0] = comparator.compare(start1, start2) > 0 ? start1 : start2;

        intervalDate[1] = comparator.compare(end1, end2) > 0 ? end2 : end1;

        return intervalDate;
    }

    /**
     * 获取应收款计算列表信息
     *
     * @param searchVo 检索条件
     * @return
     */
    @ReadDataSource
    public PageInfo<ChargeCustomerChargeCalcTask> listPage(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
      /*  List<OwnerHouseBaseInfo> houseBaseInfos = new ArrayList<>();
        if (!Objects.isNull(searchVo.getHouseId())) {
            List<String> type = new ArrayList<String>(Arrays.asList("6", "7", "8", "9"));
            //查询项目下所有的房产
            List<Long> houseIdList = new ArrayList<>();
            houseBaseInfos = ownerRemoteService.listAllChildNode(searchVo.getHouseId(), type).getResultData();
            if (!Objects.isNull(houseBaseInfos)) {
                List<Long> houseIds = houseBaseInfos.stream().map(t -> t.getHouseId()).collect(Collectors.toList());
                houseIdList.addAll(houseIds);
                houseIds.add(searchVo.getHouseId());
                searchVo.setHouseIds(houseIds);
            }
        }*/
        List<ChargeCustomerChargeCalcTask> list = chargeCustomerChargeCalcTaskMapper.listPage(searchVo);
        for (ChargeCustomerChargeCalcTask chargeCustomerChargeCalcTask : list) {
            chargeCustomerChargeCalcTask.setIs_exist(
                    0 != chargeCustomerChargeDetailMapper.selectByTaskId(chargeCustomerChargeCalcTask.getId()).size() ? "1" : "0");
        }
        PageInfo<ChargeCustomerChargeCalcTask> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @ReadDataSource
    public PageInfo<ChargeCustomerChargeCalcTask> listPlanPage(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeCustomerChargeCalcTask> list = chargeCustomerChargeCalcTaskMapper.listAllPlan(searchVo);
        List<Long> listId = new ArrayList<Long>();
        for (ChargeCustomerChargeCalcTask chargeCustomerChargeCalcTask : list) {
            chargeCustomerChargeCalcTask.setIs_work(
                    chargeCalcPlanQuartzMapper.selectByPlanId(chargeCustomerChargeCalcTask.getId()).getIsWork());
            listId.add(chargeCustomerChargeCalcTask.getId());
            for (Long id : listId) {
                String[] corn = chargeCalcPlanQuartzMapper.selectByPlanId(id).getPlanCorn().split(" ");
                int count = 0;
                for (int i = corn.length - 1; i >= 0; i--) {
                    if ("*".equals(corn[i])) {
                        count++;
                    }
                    if (corn[i].length() == 1 && corn[i].matches("[0-9]{1,}") == true) {
                        corn[i] = "0" + corn[i];
                    }
                }
                boolean flag = false;
                if (!"?".equals(corn[corn.length - 2]) && !"*".equals(corn[corn.length - 2])) {
                    switch (corn[corn.length - 2]) {
                        case "Mon":
                            chargeCustomerChargeCalcTask.setInterval("每周一" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        case "Tue":
                            chargeCustomerChargeCalcTask.setInterval("每周二" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        case "Wed":
                            chargeCustomerChargeCalcTask.setInterval("每周三" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        case "Thu":
                            chargeCustomerChargeCalcTask.setInterval("每周四" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        case "Fri":
                            chargeCustomerChargeCalcTask.setInterval("每周五" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        case "Sta":
                            chargeCustomerChargeCalcTask.setInterval("每周六" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        case "Sun":
                            chargeCustomerChargeCalcTask.setInterval("每周日" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        default:
                            flag = true;
                    }
                    if (flag) {
                        chargeCustomerChargeCalcTask.setInterval("错误的执行频率！");
                    }
                } else {
                    switch (count) {
                        case 3:
                            chargeCustomerChargeCalcTask.setInterval("每天" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        case 2:
                            chargeCustomerChargeCalcTask.setInterval("每月" + corn[3] + "号" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        case 1:
                            chargeCustomerChargeCalcTask.setInterval("每年" + corn[4] + "月" + corn[3] + "号" + " " + corn[2] + ":" + corn[1] + ":" + corn[0]);
                            break;
                        default:
                            flag = true;
                    }
                    if (flag) {
                        chargeCustomerChargeCalcTask.setInterval("错误的执行频率！");
                    }
                }
            }
        }

        PageInfo<ChargeCustomerChargeCalcTask> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @ReadDataSource
    public List<ChargeCustomerChargeCalcTask> listPageALL(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        List<ChargeCustomerChargeCalcTask> list = chargeCustomerChargeCalcTaskMapper.listPage(searchVo);
        return list;
    }

    @ReadDataSource
    @Override
    public List<ChargeCustomerChargeCalcTask> listPageAll(SearchVo searchVo) {
        if (Objects.isNull(searchVo)) {
            return null;
        }
        List<ChargeCustomerChargeCalcTask> list = chargeCustomerChargeCalcTaskMapper.listPage(searchVo);
        return list;
    }

    @ReadDataSource
    @Override
    public boolean deleteTaskDetails(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        ChargeCustomerChargeCalcTask task = chargeCustomerChargeCalcTaskMapper.selectById(id);
        if (!"未审核".equals(task.getIsCheck())) {
            BizException.fail(ResultCodeEnum.FAILURE, "已审核的数据不允许清除");
            return false;
        }
        int chargeCustomerChargeDetail = chargeCustomerChargeDetailMapper.deleteById(id);
        if (chargeCustomerChargeDetail == 0) {
            return false;
        }
        return true;
    }

    /**
     * 获取应收款计算详情
     *
     * @param id 主键id
     * @return
     */
    @ReadDataSource
    public PaymentCalcVo detail(Long id) {
        if (Objects.isNull(id)) {
            return null;
        }
        PaymentCalcVo vo = new PaymentCalcVo();
        ChargeCustomerChargeCalcTask chargeCustomerchargecalctask = chargeCustomerChargeCalcTaskMapper.selectById(id);
        // 如果查询出了数据，将数据拷贝到vo中
        if (!Objects.isNull(chargeCustomerchargecalctask)) {
            BeanUtils.copyProperties(chargeCustomerchargecalctask, vo);
        }
        vo.setStatus(chargeCalcPlanQuartzMapper.selectByPlanId(id).getIsWork());
        String[] corn = chargeCalcPlanQuartzMapper.selectByPlanId(id).getPlanCorn().split(" ");
        int count = 0;
        for (int i = corn.length - 1; i >= 0; i--) {
            if ("*".equals(corn[i])) {
                count++;
            }
            if (corn[i].length() == 1 && corn[i].matches("[0-9]{1,}") == true) {
                corn[i] = "0" + corn[i];
            }
        }
        boolean flag = false;
        if (!"?".equals(corn[corn.length - 2]) && !"*".equals(corn[corn.length - 2])) {
            vo.setDateType("每周");
            vo.setmSenconds(corn[2] + ":" + corn[1] + ":" + corn[0]);
            switch (corn[corn.length - 2]) {
                case "Mon":
                    vo.setWeeks("1");
                    break;
                case "Tue":
                    vo.setWeeks("2");
                    break;
                case "Wed":
                    vo.setWeeks("3");
                    break;
                case "Thu":
                    vo.setWeeks("4");
                    break;
                case "Fri":
                    vo.setWeeks("5");
                    break;
                case "Sta":
                    vo.setWeeks("6");
                    break;
                case "Sun":
                    vo.setWeeks("7");
                    break;
                default:
                    flag = true;
            }
            if (flag) {
                BizException.fail(ResultCodeEnum.SERVER_ERROR, "执行频率有误，请重新设置！");
            }
        } else {
            switch (count) {
                case 3:
                    vo.setDateType("每日");
                    vo.setmSenconds(corn[2] + ":" + corn[1] + ":" + corn[0]);
                    break;
                case 2:
                    vo.setDateType("每月");
                    vo.setMdays(corn[3]);
                    vo.setmSenconds(corn[2] + ":" + corn[1] + ":" + corn[0]);
                    break;
                case 1:
                    vo.setDateType("每年");
                    vo.setMdays(corn[4] + "-" + corn[3]);
                    vo.setmSenconds(corn[2] + ":" + corn[1] + ":" + corn[0]);
                    break;
                default:
                    flag = true;
            }
            if (flag) {
                BizException.fail(ResultCodeEnum.SERVER_ERROR, "执行频率有误，请重新设置！");
            }
        }
        List<ChargeCustomerChargeCalcTaskChargeItem> chargeCustomerchargecalctaskChargeitem = chargeCustomerchargecalctaskChargeitemMapper
                .selectByTaskId(id);
        // 如果查询出了数据，将数据拷贝到vo中
        if (!Objects.isNull(chargeCustomerchargecalctaskChargeitem)) {
            vo.setItems(chargeCustomerchargecalctaskChargeitem);
        }
        List<ChargeCustomerChargeCalcTaskHouse> chargeCustomerchargecalctaskHouse = chargeCustomerchargecalctaskHouseMapper
                .selectByTaskId(id);
        // 如果查询出了数据，将数据拷贝到vo中
        if (!Objects.isNull(chargeCustomerchargecalctaskHouse)) {
            vo.setHouses(chargeCustomerchargecalctaskHouse);
        }
        return vo;
    }

    @Override
    @ReadDataSource
    public List<ChargeChargeItem> getChargeItemList(Long houseId) {
        HouseStandardAddVo houseStandardAddVo = new HouseStandardAddVo();
        houseStandardAddVo.setPreinctId(houseId);
        List<ChargeChargeItem> items = null;
        // 查询该项目下的所有设置了的房产标准
        List<ChargeHouseChargeStandard> housestandards = chargeHouseChargeStandardMapper.findHouseStandardByPrecientId(houseStandardAddVo);
        if (!Objects.isNull(housestandards)) {
            List<Long> itemIds = housestandards.stream().map(e -> e.getChargeItemId()).collect(Collectors.toList());
            HashMap<String, Object> map = Maps.newHashMap();
            if (!CollectionUtils.isEmpty(itemIds)) {
                map.put("itemIds", itemIds);
                // 查询所有科目
                items = chargeChargeItemMapper.findPath(map);
            } else {
                List<ChargeChargeStandard> standards = chargeChargeStandardMapper.selectByPrecintId(houseId);
                itemIds = standards.stream().map(e -> e.getChargeItemId()).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(itemIds)) {
                    map.put("itemIds", itemIds);
                    // 查询所有科目
                    items = chargeChargeItemMapper.findPath(map);
                }
            }
        }
        return items;
    }


    /**
     * 编辑应收款计算详情
     *
     * @return boolean 编辑成功与否
     */
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean edit(PaymentCalcVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }
        ChargeCustomerChargeCalcTask chargeCustomerchargecalctask = new ChargeCustomerChargeCalcTask();
        BeanUtils.copyProperties(vo, chargeCustomerchargecalctask);
        List<ChargeCustomerChargeCalcTask> tasks = chargeCustomerChargeCalcTaskMapper.selectByIdAndName(chargeCustomerchargecalctask);
        if (!Objects.isNull(tasks) && !CollectionUtils.isEmpty(tasks)) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该项目下已经存在该任务名称！");
        }

        ChargeCalcPlanQuartz chargeCalcPlanQuartz = new ChargeCalcPlanQuartz();
        if (HouseStandrdAduitStatus.TASK_AUTO.getTitle().equals(vo.getTaskType())) {
            chargeCalcPlanQuartz.setPlanCorn(generrateCron(vo));
        } else {
            String dateFormat = "ss mm HH dd MM ? yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            String formatTimeStr = null;
            if (vo.getWorkDate() != null) {
                formatTimeStr = sdf.format(vo.getWorkDate());
            } else {
                BizException.fail(ResultCodeEnum.SERVER_ERROR, "请填写生效时间！");
            }
            chargeCalcPlanQuartz.setPlanCorn(formatTimeStr);
        }
        chargeCalcPlanQuartz.setPlanId(vo.getId());
        chargeCalcPlanQuartz.setEnterpriseId(vo.getEnterpriseId());
        chargeCalcPlanQuartz.setOrganizationId(vo.getOrganizationId());
        chargeCalcPlanQuartz.setIsWork(vo.getStatus());// 0:禁用 1：启用
        chargeCalcPlanQuartz.setJobName("com.newsee.charge.task.ChargeCalcJob");
        chargeCalcPlanQuartz.setDbDataSource(DataSourceContextHolder.getDataSource());
        chargeCalcPlanQuartzMapper.updateById(chargeCalcPlanQuartz);


        chargeCustomerchargecalctask.setHousesSum(Objects.isNull(vo.getHouses()) ? 0 : vo.getHouses().size());
        int countchargeCustomerchargecalctask = chargeCustomerChargeCalcTaskMapper
                .updateById(chargeCustomerchargecalctask);
        if (countchargeCustomerchargecalctask == 0) {
            return false;
        }

        List<ChargeCustomerChargeCalcTaskChargeItem> chargeCustomerchargecalctaskChargeitem = vo.getItems();
        List<Long> ids = Arrays.asList(vo.getId());
        if (Objects.isNull(ids) && ids.size() > 0) {
            chargeCustomerchargecalctaskChargeitemMapper.deleteBatch(ids);
        }
        if (!Objects.isNull(chargeCustomerchargecalctaskChargeitem)) {
            chargeCustomerchargecalctaskChargeitemMapper.insertBatch(chargeCustomerchargecalctaskChargeitem);
        }

        List<ChargeCustomerChargeCalcTaskHouse> chargeCustomerchargecalctaskHouse = vo.getHouses();
        if (Objects.isNull(ids) && ids.size() > 0) {
            chargeCustomerchargecalctaskChargeitemMapper.deleteBatch(ids);
        }
        if (!Objects.isNull(chargeCustomerchargecalctaskHouse)) {
            chargeCustomerchargecalctaskHouseMapper.insertBatch(chargeCustomerchargecalctaskHouse);
        }
        return true;
    }

    /**
     * @param quartz
     * @param seconds
     */
    public void secondsCronDe(List<String> quartz, String seconds) {
        //16:0:0
        String s[] = seconds.split("\\:");
        for (int i = s.length - 1; i >= 0; i--) {
            if (s[i].startsWith("0")) s[i] = s[i].substring(1);
            quartz.remove(s.length - 1 - i);
            quartz.add(s.length - 1 - i, s[i] + " ");
        }
    }

    /**
     * @param vo
     * @return
     */
    public String generrateCron(PaymentCalcVo vo) {
        /** 日 周 月 年 0 1 2 3 */
        List<String> quartz = new ArrayList<>(Arrays.asList("* ", "* ", "* ", "* ", "* ", "? ", "*"));
        boolean flag = false;
        if (Objects.isNull(vo.getmSenconds())) flag = true;
        switch (vo.getDateType()) {
            case "每日":
                secondsCronDe(quartz, vo.getmSenconds());
                break;
            case "每月":
                secondsCronDe(quartz, vo.getmSenconds());
                quartz.remove(3);
                if (Objects.isNull(vo.getMonthDay())) flag = true;
                quartz.add(3, vo.getMonthDay() + " ");
                break;
            case "每周":
                secondsCronDe(quartz, vo.getmSenconds());
                quartz.remove(5);
                if (Objects.isNull(vo.getWeeks())) flag = true;
                quartz.add(5, vo.getWeeks() + " ");
                quartz.remove(3);
                quartz.add(3, "? ");
                break;
            case "每年":
                secondsCronDe(quartz, vo.getmSenconds());
                // 几月几号
                if (Objects.isNull(vo.getMdays())) flag = true;
                String[] mdays = vo.getMdays().split("\\-");
                for (int i = 1; i >= 0; i--) {
                    if (mdays[i].startsWith("0"))
                        mdays[i] = mdays[i].substring(1);
                    quartz.remove(4 - i);
                    quartz.add(4 - i, mdays[i] + " ");
                }
                break;
            default:
                flag = true;
        }
        if (flag) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "执行频率请填写完整！");
        }
        String crons = "";
        for (String string : quartz) {
            crons += string;
        }
        return crons;
    }

    /**
     * @param standard
     * @param amount
     * @return
     */
    public Double detailAmount(ChargeChargeStandard standard, Double amount) {
        //0:分|1:角|2:元
        DecimalFormat df = null;

        StringBuffer format = new StringBuffer("#.");

        Integer type = Integer.valueOf(standard.getChargeRoundType());
        //得出格式串
        for (int i = type; i < 3; i++, format.append("0")) ;

        df = new DecimalFormat(String.valueOf(format));
        //得到元角分的金额
        amount = Double.valueOf(df.format(new BigDecimal(amount)));
        //0:四舍五入|1:只舍不入|2:只入不舍|3:不做处理
        switch (standard.getMantissa()) {
            case "0":
                amount = new BigDecimal(amount).setScale(2 - type, BigDecimal.ROUND_HALF_UP).doubleValue();
                break;
            case "2":
                amount = Double.parseDouble(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).toString().substring(0, new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).toString().indexOf(".") + 3 - type)) + Math.pow(10, type - 2);
                break;
            default:
                System.out.println(new BigDecimal(amount).toString().substring(0, new BigDecimal(amount).toString().indexOf(".") + 3 - type));
                amount = Double.parseDouble(new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).toString().substring(0, new BigDecimal(amount).setScale(2, BigDecimal.ROUND_HALF_UP).toString().indexOf(".") + 3 - type));
                break;
        }
        return amount;
    }

    /**
     * 新增应收款计算
     *
     * @return boolean 新增成功与否
     */
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean add(PaymentCalcVo vo) {
        if (Objects.isNull(vo)) {
            return false;
        }

        ChargeCustomerChargeCalcTask chargeCustomerchargecalctask = new ChargeCustomerChargeCalcTask();
        BeanUtils.copyProperties(vo, chargeCustomerchargecalctask);

        List<ChargeCustomerChargeCalcTask> tasks = chargeCustomerChargeCalcTaskMapper.selectByIdAndName(chargeCustomerchargecalctask);
        if (!Objects.isNull(tasks) && !CollectionUtils.isEmpty(tasks)) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该项目下已经存在该任务名称！");
        }
        chargeCustomerchargecalctask.setHousesSum(Objects.isNull(vo.getHouses()) ? 0 : vo.getHouses().size());
        int countchargeCustomerchargecalctask = chargeCustomerChargeCalcTaskMapper.insert(chargeCustomerchargecalctask);
        if (countchargeCustomerchargecalctask == 0) {
            return false;
        }
        // 添加到自动任务当中去
        ChargeCalcPlanQuartz chargeCalcPlanQuartz = new ChargeCalcPlanQuartz();
        if (HouseStandrdAduitStatus.TASK_AUTO.getTitle().equals(chargeCustomerchargecalctask.getTaskType())) {
            chargeCalcPlanQuartz.setPlanCorn(generrateCron(vo));
        } else {
            String dateFormat = "ss mm HH dd MM ? yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
            String formatTimeStr = null;
            if (vo.getWorkDate() != null) {
                formatTimeStr = sdf.format(vo.getWorkDate());
            }
            chargeCalcPlanQuartz.setPlanCorn(formatTimeStr);
        }
        chargeCalcPlanQuartz.setPlanId(chargeCustomerchargecalctask.getId());
        chargeCalcPlanQuartz.setEnterpriseId(vo.getEnterpriseId());
        chargeCalcPlanQuartz.setOrganizationId(vo.getOrganizationId());
        chargeCalcPlanQuartz.setIsWork(vo.getStatus());// 0:禁用 1：启用
        chargeCalcPlanQuartz.setJobName("com.newsee.charge.task.ChargeCalcJob");
        chargeCalcPlanQuartz.setDbDataSource(DataSourceContextHolder.getDataSource());
        chargeCalcPlanQuartzMapper.insert(chargeCalcPlanQuartz);

        List<ChargeCustomerChargeCalcTaskChargeItem> items = vo.getItems();
        // 设置任务id 插入科目信息
        if (!CollectionUtils.isEmpty(items)) {
            items.forEach(e -> {
                e.setTaskId(chargeCustomerchargecalctask.getId());
                e.setEnterpriseId(vo.getEnterpriseId());
                e.setOrganizationId(vo.getOrganizationId());
            });
            int countchargeCustomerchargecalctaskChargeitem = chargeCustomerchargecalctaskChargeitemMapper
                    .insertBatch(items);
            if (countchargeCustomerchargecalctaskChargeitem != items.size()) {
                return false;
            }
        }
        // 插入收费房产信息
        List<ChargeCustomerChargeCalcTaskHouse> houses = vo.getHouses();
        if (!Objects.isNull(houses)) {
            houses.forEach(e -> {
                e.setTaskId(chargeCustomerchargecalctask.getId());
                e.setEnterpriseId(vo.getEnterpriseId());
                e.setOrganizationId(vo.getOrganizationId());
            });
            int countchargeCustomerchargecalctaskHouse = chargeCustomerchargecalctaskHouseMapper.insertBatch(houses);

            if (countchargeCustomerchargecalctaskHouse != houses.size()) {
                return false;
            }
        }

        return true;
    }

    /**
     * 根据主键删除应收款计算
     *
     * @param id 主键id
     * @return
     */
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW)
    public boolean delete(Long id) {
        if (Objects.isNull(id)) {
            return false;
        }
        // 停用自动任务
        ChargeCalcPlanQuartz chargeCalcPlanQuartz = new ChargeCalcPlanQuartz();
        chargeCalcPlanQuartz.setIsWork(HouseStandrdAduitStatus.DISABLED.getTitle());
        chargeCalcPlanQuartz.setPlanId(id);
        chargeCalcPlanQuartzMapper.updateById(chargeCalcPlanQuartz);

        int countchargeCustomerchargecalctask = chargeCustomerChargeCalcTaskMapper.deleteById(id);
        if (countchargeCustomerchargecalctask == 0) {
            return false;
        }
        int countchargeCustomerchargecalctaskChargeitem = chargeCustomerchargecalctaskChargeitemMapper.deleteById(id);
        if (countchargeCustomerchargecalctaskChargeitem == 0) {
            return false;
        }
        int countchargeCustomerchargecalctaskHouse = chargeCustomerchargecalctaskHouseMapper.deleteById(id);
        if (countchargeCustomerchargecalctaskHouse == 0) {
            return false;
        }
        return true;
    }

    /**
     * 根据主键批量删除应收款计算
     *
     * @param ids
     * @return
     */
    @WriteDataSource
    public boolean deleteBatch(List<Long> ids) {
        if (Objects.isNull(ids)) {
            return false;
        }

        ids.forEach(e -> {
            // 停用自动任务
            ChargeCalcPlanQuartz chargeCalcPlanQuartz = new ChargeCalcPlanQuartz();
            chargeCalcPlanQuartz.setIsWork(HouseStandrdAduitStatus.DISABLED.getTitle());
            chargeCalcPlanQuartz.setPlanId(e);
            chargeCalcPlanQuartzMapper.updateById(chargeCalcPlanQuartz);
        });

        int countchargeCustomerchargecalctask = chargeCustomerChargeCalcTaskMapper.deleteBatch(ids);
        if (countchargeCustomerchargecalctask == 0) {
            return false;
        }
        int countchargeCustomerchargecalctaskChargeitem = chargeCustomerchargecalctaskChargeitemMapper.deleteBatch(ids);
        if (countchargeCustomerchargecalctaskChargeitem == 0) {
            return false;
        }
        int countchargeCustomerchargecalctaskHouse = chargeCustomerchargecalctaskHouseMapper.deleteBatch(ids);
        if (countchargeCustomerchargecalctaskHouse == 0) {
            return false;
        }
        return true;
    }

    @WriteDataSource
    public Integer checkPaymentCalc(Map<String, Object> map) {
        Integer result = chargeCustomerChargeCalcTaskMapper.checkPaymentCalc(map);
        return result;
    }

    @WriteDataSource
    public Integer checkPayment(Map<String, Object> map) {
        Integer result = chargeCustomerChargeDetailMapper.checkPayment(map);
        return result;
    }

    @WriteDataSource
    public Integer planManager(Map<String, Object> map) {
        Integer result = chargeCalcPlanQuartzMapper.planManager(map);
        return result;
    }

    @Override
    @WriteDataSource
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public String importPaymentCalc(MultipartFile file, PaymentCalcVoImportExcel paymentCalcVo) {
        StringBuffer resultMessage = new StringBuffer();
        // 创建任务
        ChargeCustomerChargeCalcTask task = new ChargeCustomerChargeCalcTask();
        BeanUtils.copyProperties(paymentCalcVo, task);
        task.setTaskType(paymentCalcVo.getTaskType());
        if (isNotBlank(paymentCalcVo.getShouldChargeAccountEnd())) {
            task.setShouldChargeAccountEnd(DateUtils.strToDate(paymentCalcVo.getShouldChargeAccountEnd(), DateUtils.YYYYMMDD_CROSS_HHMMSS));
        }
        if (isNotBlank(paymentCalcVo.getShouldChargeAccountStart())) {
            task.setShouldChargeAccountEnd(DateUtils.strToDate(paymentCalcVo.getShouldChargeAccountStart(), DateUtils.YYYYMMDD_CROSS_HHMMSS));
        }
        if (isNotBlank(paymentCalcVo.getWorkDate())) {
            task.setShouldChargeAccountEnd(DateUtils.strToDate(paymentCalcVo.getWorkDate(), DateUtils.YYYYMMDD_CROSS_HHMMSS));
        }
        List<ChargeCustomerChargeCalcTask> tasks = chargeCustomerChargeCalcTaskMapper.selectByIdAndName(task);
        if (!Objects.isNull(tasks) && !CollectionUtils.isEmpty(tasks)) {
            BizException.fail(ResultCodeEnum.SERVER_ERROR, "该项目下已经存在该任务名称！");
        }
        task.setPlanId(0L);
        task.setHousesSum(0);
        chargeCustomerChargeCalcTaskMapper.insert(task);
        if (file != null) {
            List<ChargeCustomerChargeCalcTaskChargeItem> taskChargeItemList = new ArrayList<>();
            List<ChargeCustomerChargeCalcTaskHouse> taskHouseList = new ArrayList<>();
            // 解析Excel
            FileInputStream is = null;
            try {
                if (file.getInputStream() instanceof FileInputStream) {
                    is = (FileInputStream) file.getInputStream();
                    ArrayList<ArrayList<Object>> arrayLists = ExcelUploadUtil.readExcel(file.getOriginalFilename(), is);
                    if (!CollectionUtils.isEmpty(arrayLists)) {
                        arrayLists.remove(0);
                        int progress = 0;//导入多少条
                        int len = arrayLists.size();//总条数
                        for (int i = 0; i < arrayLists.size(); i++) {
                            try {
                                progress++;
                                // 检查Excel的值
                                Map<String, Object> resultMap = checkExcelValue(arrayLists.get(i));
                                if (-1 == (Byte) resultMap.get("resultCode")) {
                                    // 插入一条日志
                                    addChargeCalcLog(-1, "第" + (i + 1) + "条导入失败:" + resultMap.get("resultMessage"), 0D, 0L, 1,
                                            task.getEnterpriseId(), task.getOrganizationId());
                                    continue;
                                } else {
                                    Long houseId = (Long) resultMap.get("houseId");// 项目id
                                    String houseName = (String) resultMap.get("houseName");// 项目名称
                                    String chargeName = (String) resultMap.get("chargeName");// 收费对象名称
                                    Integer chargeType = (Integer) resultMap.get("chargeType");// 收费对象类型
                                    Long chargeItemId = (Long) resultMap.get("chargeItemId");// 收费科目id
                                    String chargeItemName = (String) resultMap.get("chargeItemName");// 收费科目名称
                                    Double price = (Double) resultMap.get("price");// 单价
                                    Date startDate = (Date) resultMap.get("startDate");// 开始时间
                                    Date endDate = (Date) resultMap.get("endDate");// 结束时间
                                    Date shouldChargeDate = (Date) resultMap.get("shouldChargeDate");// 应收款时间
                                    Double shouldChargePrice = (Double) resultMap.get("shouldChargePrice");// 应收款金额
                                    Long chargeId = (Long) resultMap.get("chargeId");

                                    // 添加应收款费用详情
                                    ChargeCustomerChargeDetail detail = new ChargeCustomerChargeDetail();
                                    detail.setEnterpriseId(task.getEnterpriseId());
                                    detail.setOrganizationId(task.getOrganizationId());
                                    detail.setTaskId(task.getId());
                                    detail.setPreinctId(houseId);
                                    detail.setPreinctName(houseName);
                                    detail.setOwnerId(1L);
                                    detail.setOwnerName(chargeName);
                                    detail.setPaidOwnerType(chargeType);
                                    detail.setChargeItemId(chargeItemId);
                                    detail.setChargeItemName(chargeItemName);
                                    // 查询出该房子对该科目设置的收费标准信息
                                    HouseStandardAddVo houseStandardAddVo = new HouseStandardAddVo();
                                    houseStandardAddVo.setChargeItemId(chargeItemId);
                                    houseStandardAddVo.setHouseId(houseId);
                                    houseStandardAddVo.setEnterpriseId(task.getEnterpriseId());
                                    houseStandardAddVo.setOrganizationId(task.getOrganizationId());
                                    ChargeHouseChargeStandard houseChargeStandard = chargeHouseChargeStandardMapper.findHouseStandard(houseStandardAddVo);
                                    if (houseChargeStandard != null) {
                                        detail.setChargeId(houseChargeStandard.getStandardId());
                                        detail.setChargeName(houseChargeStandard.getStandardName());
                                    }
                                    detail.setPrice(price);
                                    ChargeChargeItem chargeChargeItem = chargeChargeItemMapper.selectById(chargeItemId);
                                    // 查询单位具体值
                                    HashMap<String, Object> unitMap = new HashMap<String, Object>();
                                    unitMap.put("dictionaryDictype", "charge");
                                    unitMap.put("dictionaryDdcode", "Unit");
                                    unitMap.put("code", chargeChargeItem.getUnit());
                                    RestResult<String> result = iSystemRemoteService.findDictionaryName(unitMap);
                                    String unit = Objects.isNull(result) ? "" : result.getResultData();
                                    detail.setChargeItemPrice(price + unit);
                                    detail.setHouseId(houseId);
                                    detail.setAmount(shouldChargePrice / price);
                                    detail.setCalcStartDate(startDate);
                                    detail.setCalcEndDate(endDate);
                                    detail.setShouldChargeDate(shouldChargeDate);
                                    detail.setActualChargeSum(shouldChargePrice);
                                    detail.setChargeSum(shouldChargePrice);
                                    detail.setPaidChargeSum(0D);
                                    detail.setCreateTime(task.getCreateTime());
                                    detail.setCreateUserId(task.getCreateUserId());
                                    detail.setCreateUserName(task.getCreateUserName());
                                    detail.setUpdateTime(task.getUpdateTime());
                                    detail.setUpdateUserId(task.getUpdateUserId());
                                    detail.setUpdateUserName(task.getUpdateUserName());
                                    chargeCustomerChargeDetailMapper.insert(detail);
                                    // 插入一条导入日志
                                    addChargeCalcLog(-1, houseName + "," + chargeItemName + "导入成功。", shouldChargePrice,
                                            task.getId(), 0, task.getEnterpriseId(), task.getOrganizationId());
                                    //创建应收款计算计划-收费科目
                                    ChargeCustomerChargeCalcTaskChargeItem taskChargeItem = new ChargeCustomerChargeCalcTaskChargeItem();
                                    taskChargeItem.setTaskId(task.getId());
                                    taskChargeItem.setChargeItem(chargeItemId);
                                    taskChargeItem.setChargeItemName(chargeItemName);
                                    taskChargeItem.setEnterpriseId(task.getEnterpriseId());
                                    taskChargeItem.setOrganizationId(task.getOrganizationId());
                                    taskChargeItemList.add(taskChargeItem);
                                    //创建应收款计算计划-房间
                                    ChargeCustomerChargeCalcTaskHouse taskHouse = new ChargeCustomerChargeCalcTaskHouse();
                                    taskHouse.setEnterpriseId(task.getEnterpriseId());
                                    taskHouse.setOrganizationId(task.getOrganizationId());
                                    taskHouse.setTaskId(task.getId());
                                    taskHouse.setHouseId(houseId);
                                    taskHouseList.add(taskHouse);
                                }
                            } catch (Exception e) {
                                // 插入错误日志
                                e.printStackTrace();
                                addChargeCalcLog(-1, "第" + (i + 1) + "条导入错误。", 0D, 0L, 1, task.getEnterpriseId(),
                                        task.getOrganizationId());
                                continue;
                            }
                            webSocketRemoteService.returnProgress(paymentCalcVo.getCreateUserName(), progress + "S" + len);
                        }
                        resultMessage.append("总共").append(len).append("条，").append("成功").append(progress).append("条");
                    } else {
                        BizException.fail(ResultCodeEnum.PARAMS_ERROR, "Excel模板数据为空。");
                    }
                }
            } catch (IOException e) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "模板解析失败。");
            } finally {
                if (is != null) {
                    try {
                        is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            if (!CollectionUtils.isEmpty(taskChargeItemList)) {
                chargeCustomerchargecalctaskChargeitemMapper.insertBatch(taskChargeItemList);
            }
            if (!CollectionUtils.isEmpty(taskHouseList)) {
                chargeCustomerchargecalctaskHouseMapper.insertBatch(taskHouseList);
            }
            return resultMessage.length() > 0 ? resultMessage.toString() : "数据导入失败";
        }
        return "保存成功";
    }

    @Override
    @ReadDataSource
    public void downloadExcelTemplate(Long precinctId, Long itemId, Long standardId, Date shouldChargeDate, Date startDate, Date endDate,
                                      HttpServletResponse response) {
        List<OwnerHouseBaseInfo> houseBaseInfoList = iOwnerRemoteService
                .listAllChildNode(precinctId, newArrayList("6", "7", "8", "9")).getResultData();
        String[] header = {"*项目|color:RED", "*房产简称|color:RED", "收费对象类型", "收费对象姓名", "证件类型", "证件号码", "*收费科目|color:RED", "*单价|color:RED", "*计费开始日期|color:RED",
                "*计费结束日期|color:RED", "*应收日期|color:RED", "*应收金额|color:RED"};
        ChargeChargeItem chargeChargeItem = null;
        if (itemId != null) {
            chargeChargeItem = chargeChargeItemMapper.selectById(itemId);
        }
        ChargeChargeStandard standard = null;
        if (standardId != null) {

        }
        List<PaymentCalcExcelVo> excelDataList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(houseBaseInfoList)) {
            for (OwnerHouseBaseInfo houseBaseInfo : houseBaseInfoList) {
                PaymentCalcExcelVo excelVo = new PaymentCalcExcelVo();
                OwnerHouseBaseInfo precinct = iOwnerRemoteService.getHouseInfo(houseBaseInfo.getPrecinctId()).getResultData();
                if (precinct != null) {
                    excelVo.setPrecinctName(precinct.getHouseName());
                }
                excelVo.setHouesName(houseBaseInfo.getHouseName());
                excelVo.setChargeType(null);
                excelVo.setChargeName(null);
                excelVo.setIdCard(null);
                excelVo.setIdCardType(null);
                if (chargeChargeItem != null) {
                    excelVo.setChargeName(chargeChargeItem.getChargeItemName());
                }
                excelVo.setPrice(null);
                excelVo.setStartDate(startDate);
                excelVo.setEndDate(endDate);
                excelVo.setShouldChargeDate(shouldChargeDate);
                excelVo.setShouldChargePrice(null);
                excelDataList.add(excelVo);
            }

        }
        try {
            ServletOutputStream outputStream = response.getOutputStream();
            ExcelUtil.exportDataToExcel(excelDataList, header, "导出", outputStream);
            outputStream.close();
        } catch (IOException e) {
            BizException.fail(ResultCodeEnum.FAILURE, "导出Excel失败");
        }
    }

    @Override
    @ReadDataSource
    public PageInfo<ChargeCalcLog> listPageChargeCalcLog(SearchVo searchVo) {
        PageHelper.startPage(searchVo.getPageNum(), searchVo.getPageSize());
        List<ChargeCalcLog> chargeCalcLogs = chargeCalcLogMapper.listPage(searchVo);
        PageInfo<ChargeCalcLog> pageInfo = new PageInfo<>(chargeCalcLogs);
        return pageInfo;
    }

    @Override
    @ReadDataSource
    public ChargeCustomerChargeCalcTask detailChargeCalcTask(Long id) {
        ChargeCustomerChargeCalcTask task = chargeCustomerChargeCalcTaskMapper.selectById(id);
        return task;
    }

    /**
     * 检查Excel的数据是否合格
     *
     * @param list
     */
    private Map<String, Object> checkExcelValue(List<Object> list) {
        Map<String, Object> map = Maps.newHashMap();
        map.put("resultCode", Constants.RESULT_SUCCESS);
        if (!CollectionUtils.isEmpty(list)) {
            if (list.size() < 12) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "模板格式不正确");
                return map;
            }
            // 项目名称
            String precinctName = (String) list.get(0);
            if (CommonUtils.isObjectEmpty(precinctName)) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "项目名称不能为空");
                return map;
            }
            // 房产名称
            if (CommonUtils.isObjectEmpty(list.get(1))) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "房产简称不能为空");
                return map;
            }
            String houseName = (String) list.get(1);
            List<OwnerHouseBaseInfo> houseBaseInfoList = iOwnerRemoteService.searchHouseInfo(precinctName, houseName)
                    .getResultData();
            if (!CollectionUtils.isEmpty(houseBaseInfoList)) {
                map.put("precinctName", precinctName);
                map.put("houseName", houseName);
                map.put("houseId", houseBaseInfoList.get(0).getHouseId());
            } else {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "不存在改房产信息");
                return map;
            }
            // 收费对象类型
            String chargeTypeStr = (String) list.get(2);
            if (chargeTypeStr != null
                    && (!"业主".equals(chargeTypeStr) && !"租户".equals(chargeTypeStr) && !"开发商".equals(chargeTypeStr))) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "收费对象类型不正确");
                return map;
            } else {
                int i = "业主".equals(chargeTypeStr) ? 0 : ("租户".equals(chargeTypeStr) ? 1 : 2);
                map.put("chargeType", i);
            }
            // 收费对象姓名
            String chargeName = list.get(3) == null ? "" : (String) list.get(3);
            map.put("chargeName", chargeName);
            // 证件类型
            String idType = list.get(4) == null ? "" : (String) list.get(4);
            map.put("idType", idType);
            // 证件号
            String idCard = list.get(5) == null ? "" : (String) list.get(5);
            map.put("idCard", idCard);
            // 检查收费对象id
            checkChargeId(map);

            // 收费科目
            if (list.get(6) == null) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "收费科目不能为空");
                return map;
            } else {
                String chargeItem = (String) list.get(6);
                Map<String, Object> chargeItemMap = Maps.newHashMap();
                Long enterpriseId = LoginDataHelper.getEnterpriseId();
                chargeItemMap.put("enterpriseId", enterpriseId);
                chargeItemMap.put("chargeItemCode", chargeItem);
                List<ChargeChargeItem> chargeChargeItems = chargeChargeItemMapper.selectByCodeOrName(chargeItemMap);
                if (CollectionUtils.isEmpty(chargeChargeItems)) {
                    map.put("resultCode", Constants.RESULT_ERROR);
                    map.put("resultMessage", "收费科目不存在");
                    return map;
                } else {
                    map.put("chargeItemId", chargeChargeItems.get(0).getId());
                    map.put("chargeItemName", chargeChargeItems.get(0).getChargeItemName());
                }
            }
            // 单价
            String pattern = "^[0-9]+([.]{1}[0-9]+){0,1}$";
            if (list.get(7) == null) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "单价不能为空");
                return map;
            } else {
                String price = (String) list.get(7);
                if (!price.matches(pattern)) {
                    map.put("resultCode", Constants.RESULT_ERROR);
                    map.put("resultMessage", "单价不正确");
                    return map;
                } else {
                    map.put("price", Double.parseDouble(price));
                }
            }
            // 计费开始日期
            if (CommonUtils.isObjectEmpty(list.get(8))) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "计费开始日期不能为空");
                return map;
            } else {
                Date startDate = DateUtils.strToDate((String) list.get(8));
                map.put("startDate", startDate);
            }
            // 计费结束日期
            if (CommonUtils.isObjectEmpty(list.get(9))) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "计费结束日期不能为空");
                return map;
            } else {
                Date endDate = DateUtils.strToDate((String) list.get(9));
                map.put("endDate", endDate);
            }
            // 应收日期
            if (CommonUtils.isObjectEmpty(list.get(10))) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "应收日期不能为空");
                return map;
            } else {
                Date shouldChargeDate = DateUtils.strToDate((String) list.get(10));
                map.put("shouldChargeDate", shouldChargeDate);
            }
            if (CommonUtils.isObjectEmpty(list.get(11))) {
                map.put("resultCode", Constants.RESULT_ERROR);
                map.put("resultMessage", "应收金额不能为空");
                return map;
            } else {
                String shouldChargePrice = (String) list.get(11);
                if (!shouldChargePrice.matches(pattern)) {
                    map.put("resultCode", Constants.RESULT_ERROR);
                    map.put("resultMessage", "应收金额不正确");
                    return map;
                } else {
                    map.put("shouldChargePrice", Double.parseDouble(shouldChargePrice));
                }
            }

        } else {
            map.put("resultCode", Constants.RESULT_ERROR);
            map.put("resultMessage", "Excel数据格式不对");
        }
        return map;
    }

    /**
     * 检查收费对象信息
     */
    private void checkChargeId(Map<String, Object> map) {
        // 根据对象姓名，证件类型，证件号查询收费对象
        String chargeName = (String) map.get("chargeName");
        String idCard = (String) map.get("idCard");
        String idType = (String) map.get("idType");
        if (!"".equals(chargeName) && !"".equals(idCard)) {
            RestResult<List<CustomerVo>> customerForSearch = iOwnerRemoteService.listCustomerForSearch(chargeName, 2, null);
            if (customerForSearch != null && !CollectionUtils.isEmpty(customerForSearch.getResultData())) {
                List<CustomerVo> customerVoList = customerForSearch.getResultData();
                List<CustomerVo> chargeList = customerVoList.stream()
                        .filter(customerVo -> idCard.equals(customerVo.getCertificate())).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(chargeList)) {
                    map.put("chargeId", chargeList.get(0).getOwnerId());
                }
            }
            if (map.get("chargeId") == null) {
                // 将收费对象添加到用户中去
                CustomerVo customerVo = new CustomerVo();
                customerVo.setCertificate(idCard);
                switch (idType) {
                    case "身份证":
                        customerVo.setCertificateType("10");
                        break;
                    case "护照":
                        customerVo.setCertificateType("11");
                        break;
                    case "军官证":
                        customerVo.setCertificateType("12");
                        break;
                    case "统一社会信用代码":
                        customerVo.setCertificateType("13");
                        break;
                    default:
                        customerVo.setCertificateType("10");
                        break;
                }
                customerVo.setOwnerName(chargeName);
                Long ownerId = iOwnerRemoteService.addCustomer(customerVo).getResultData();
                map.put("chargeId", ownerId);
            }
        } else {
            map.put("resultCode", Constants.RESULT_ERROR);
            map.put("resultMessage", "收费对象为空");
        }
    }

    /**
     * 插入详细的方法
     *
     * @param description  描述
     * @param amount       生成金额
     * @param taskId       任务id
     * @param isSuccess    0:成功，1：失败
     * @param enterpriseId 租户id
     */
    private void addChargeCalcLog(Integer numbers, String description, Double amount, Long taskId, Integer isSuccess, Long enterpriseId,
                                  Long orgId) {
        Date now = new Date();
        ChargeCalcLog log = new ChargeCalcLog();
        log.setAmount(amount);
        log.setDescription(description);
        log.setTaskId(taskId);
        log.setIsSuccess(isSuccess);
        log.setCreateTime(now);
        log.setCreateUserId(null);
        log.setCreateUserName("");
        log.setEnterpriseId(enterpriseId);
        log.setOrganizationId(orgId);
        log.setIsDelete(0);
        chargeCalcLogMapper.insert(log);
    }
}
