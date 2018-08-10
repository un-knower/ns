package com.newsee.owner.controller;

import java.io.BufferedOutputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.newsee.common.enums.HouseDecorateStageEnum;
import com.newsee.common.enums.HouseOperateTypeEnum;
import com.newsee.common.enums.HouseRentStageEnum;
import com.newsee.common.enums.HouseStageEnum;
import com.newsee.common.enums.HouseTypeEnum;
import com.newsee.common.login.LoginDataHelper;
import com.newsee.common.rest.RestResult;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.utils.CommonUtils;
import com.newsee.common.utils.DateUtils;
import com.newsee.owner.entity.HouseListEntity;
import com.newsee.owner.entity.OwnerHouseBaseInfo;
import com.newsee.owner.service.IReportFormService;
import com.newsee.owner.service.remote.ISystemRemoteService;
import com.newsee.owner.utils.ExcelCell;
import com.newsee.owner.utils.ExcelUtils;
import com.newsee.owner.utils.OwnerUtils;
import com.newsee.owner.vo.ReportFormVo;
import com.newsee.owner.vo.ReportFormVo.ConstantType;
import com.newsee.owner.vo.ReportFormVo.ReportRes;
import com.newsee.system.entity.NsCoreDictionary;
import com.newsee.system.vo.NsCoreDictionaryVo;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

/**
 * 报表逻辑层
 *
 */
@RestController
@RequestMapping(value = "/report")
@ResponseBody
public class ReportFormController {
	@Autowired
	private IReportFormService reportFormService;
	@Autowired
	private ISystemRemoteService systemRemoteService;

	@ApiOperation(value = "导出报表为EXCEL")
	@RequestMapping(value = "/project-house/excel", method = RequestMethod.GET)
	public RestResult<?> excelHouseInfoReport(
			@ApiParam(name = "startTime") @RequestParam(name = "startTime", required = false) String startTime,
			@ApiParam(name = "endTime") @RequestParam(name = "endTime") String endTime,
			@ApiParam(name = "houseIdJson") @RequestParam(name = "houseIdJson") String houseIdJson,
			@ApiParam(name = "reportType") @RequestParam(name = "reportType") String reportType,
			@ApiParam(name = "houseType") @RequestParam(name = "houseType", required = false) String houseType,
			@ApiParam(name = "stage") @RequestParam(name = "stage", required = false) String stage,
			HttpServletResponse response) throws Exception {
		if (StringUtils.isEmpty(houseIdJson)) {
			return RestResult.PARAMS_MISSING;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		ReportFormVo vo = new ReportFormVo();
		if (!StringUtils.isEmpty(startTime)) {
			vo.setStartTime(sdf.parse(startTime));
		}
		vo.setEndTime(sdf.parse(endTime));
		vo.setHouseIdJson(houseIdJson);
		vo.setReportType(reportType);
		vo.setHouseType(houseType);
		vo.setStage(stage);
		RestResult<List<ReportRes>> restResult = this.projectHouseInfoReport(vo);
		if (!ResultCodeEnum.SUCCESS.CODE.equals(restResult.getResultCode())) {
			return restResult;
		}
		List<ReportRes> rowDatas = restResult.getResultData();
		ExcelUtils excelUtil = new ExcelUtils();
		String sheetName = "";
		if (ConstantType.REPORT_OWNER_HOUSETYPE.getTitle().equals(reportType)) {
			sheetName = "项目房产信息汇总表";
			this.exportHouseInfoExcel(excelUtil, sheetName, rowDatas);
		} else if (ConstantType.REPORT_OWNER_FREEHOUSE.getTitle().equals(reportType)) {

		} else if (ConstantType.REPORT_OWNER_CHANGEINFO.getTitle().equals(reportType)) {
			sheetName = "项目房态变动信息汇总表";
			this.exportHouseChangeInfoExcel(excelUtil, sheetName, rowDatas);
		}

		String excelName = sheetName + DateUtils.getUserDate(DateUtils.YYYYMMDD_CROSS);
		String realFilename = URLEncoder.encode(excelName == null ? "excel" : excelName, "UTF-8");
		// 清空response
		response.reset();
		// 设置response的Header
		response.addHeader("Content-Disposition", "attachment;filename=" + realFilename + ".xls");
		response.setContentType("application/vnd.ms-excel;charset=UTF-8");
		OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
		excelUtil.getWb().write(toClient);
		toClient.flush();
		toClient.close();

		return new RestResult<>();
	}

	/**
	 * 导出房产汇总表-房产类型
	 * 
	 * @param excelUtil
	 * @param sheetName
	 * @param rowDatas
	 */
	private void exportHouseInfoExcel(ExcelUtils excelUtil, String sheetName, List<ReportRes> rowDatas) {
		excelUtil.createExcel(sheetName, excelUtil.getHeaderStyle());
		List<List<ExcelCell>> headerList = new ArrayList<>();
		ExcelCell cell = null;
		// 第一行
		List<ExcelCell> rowList = new ArrayList<ExcelCell>();
		cell = new ExcelCell(ConstantType.项目名称.getTitle(), 0, true, 0, 2, 0, 0);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.所属公司.getTitle(), 1, true, 0, 2, 1, 1);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.房产类型.getTitle(), 2, true, 0, 2, 2, 3);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.数量.getTitle(), 4, true, 0, 2, 4, 4);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.计费面积.getTitle(), 5, true, 0, 2, 5, 5);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.建筑面积.getTitle(), 6, true, 0, 2, 6, 6);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.公摊面积.getTitle(), 7, true, 0, 2, 7, 7);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.公摊面积.getTitle(), 8, true, 0, 2, 8, 8);
		rowList.add(cell);
		cell = new ExcelCell("出售信息", 9, true, 0, 0, 9, 12);
		rowList.add(cell);
		cell = new ExcelCell("收房信息", 13, true, 0, 0, 13, 16);
		rowList.add(cell);
		cell = new ExcelCell("入住信息", 17, true, 0, 0, 17, 20);
		rowList.add(cell);
		cell = new ExcelCell("装修信息", 21, true, 0, 0, 21, 26);
		rowList.add(cell);
		cell = new ExcelCell("出租", 27, true, 0, 1, 27, 28);
		rowList.add(cell);
		cell = new ExcelCell("停用", 29, true, 0, 1, 29, 30);
		rowList.add(cell);
		headerList.add(rowList);
		// 第二行
		rowList = new ArrayList<ExcelCell>();
		cell = new ExcelCell(ConstantType.已售.getTitle(), 9, true, 1, 1, 9, 10);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.空置.getTitle(), 11, true, 1, 1, 11, 12);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.待收房.getTitle(), 13, true, 1, 1, 13, 14);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.已收房.getTitle(), 15, true, 1, 1, 15, 16);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.入住.getTitle(), 17, true, 1, 1, 17, 18);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.空关.getTitle(), 19, true, 1, 1, 19, 20);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.装修中.getTitle(), 21, true, 1, 1, 21, 22);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.已装修.getTitle(), 23, true, 1, 1, 23, 24);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.未装修.getTitle(), 25, true, 1, 1, 25, 26);
		rowList.add(cell);
		headerList.add(rowList);
		// 第三行
		rowList = new ArrayList<ExcelCell>();
		for (int i = 9; i < 30; i += 2) {
			cell = new ExcelCell(ConstantType.数量.getTitle(), i);
			rowList.add(cell);
			cell = new ExcelCell(ConstantType.计费面积.getTitle(), i + 1);
			rowList.add(cell);
		}
		headerList.add(rowList);
		// 创建表头
		excelUtil.createHeader(headerList);
		// 填充数据
		rowList.clear();
		List<List<ExcelCell>> dataList = new ArrayList<>();
		ReportRes temp = null;
		for (ReportRes res : rowDatas) {
			// 列数据
			rowList = new ArrayList<ExcelCell>();
			rowList.add(new ExcelCell(res.getHouseName()));
			rowList.add(new ExcelCell(res.getCompanyName()));
			rowList.add(new ExcelCell(res.getHouseTypeName()));
			rowList.add(new ExcelCell(res.getHouseSubTypeName()));
			rowList.add(new ExcelCell(res.getCount()));
			rowList.add(new ExcelCell(res.getChargingArea()));
			rowList.add(new ExcelCell(res.getBuildingArea()));
			rowList.add(new ExcelCell(res.getPoolArea()));
			rowList.add(new ExcelCell(res.getInsidingArea()));
			temp = res.getSaledInfo(); // 已售
			this.cellData(temp, rowList);
			temp = res.getSaleNotInfo(); // 空置
			this.cellData(temp, rowList);
			temp = res.getSoufangWaitInfo();
			this.cellData(temp, rowList);
			temp = res.getSoufangedInfo();
			this.cellData(temp, rowList);
			temp = res.getRuzhuInfo();
			this.cellData(temp, rowList);
			temp = res.getRuzhuNotInfo();
			this.cellData(temp, rowList);
			temp = res.getDecoratingInfo();
			this.cellData(temp, rowList);
			temp = res.getDecoratedInfo();
			this.cellData(temp, rowList);
			temp = res.getDecorateNotInfo();
			this.cellData(temp, rowList);
			temp = res.getRentInfo();
			this.cellData(temp, rowList);
			temp = res.getStopInfo();
			this.cellData(temp, rowList);
			// 行数据
			dataList.add(rowList);
		}
		// 添加数据
		excelUtil.addData(dataList, excelUtil.getStyle());
	}

	/**
	 * 导出报表数据处理
	 * 
	 * @param temp
	 * @param rowList
	 */
	private void cellData(ReportRes temp, List<ExcelCell> rowList) {
		if (temp != null) {// 已售
			rowList.add(new ExcelCell(temp.getCount()));
			rowList.add(new ExcelCell(temp.getChargingArea()));
		} else {
			rowList.add(new ExcelCell(0));
			rowList.add(new ExcelCell(0));
		}
	}

	private void cellDataHouseChange(ReportRes temp, List<ExcelCell> rowList) {
		if (temp != null) {
			rowList.add(new ExcelCell(temp.getCount()));
			rowList.add(new ExcelCell(temp.getChargingArea()));
			rowList.add(new ExcelCell(new StringBuilder().append(temp.getCounRatio() * 100).append("%").toString()));
			rowList.add(new ExcelCell(
					new StringBuilder().append(temp.getChargingAreaRatio() * 100).append("%").toString()));
		} else {
			rowList.add(new ExcelCell(0));
			rowList.add(new ExcelCell(0));
			rowList.add(new ExcelCell(""));
			rowList.add(new ExcelCell(""));
		}
	}

	/**
	 * 导出excel房产变动汇总表
	 */
	private void exportHouseChangeInfoExcel(ExcelUtils excelUtil, String sheetName, List<ReportRes> rowDatas) {
		excelUtil.createExcel(sheetName, excelUtil.getHeaderStyle());
		List<List<ExcelCell>> headerList = new ArrayList<>();
		ExcelCell cell = null;
		// 第一行
		List<ExcelCell> rowList = new ArrayList<ExcelCell>();
		cell = new ExcelCell(ConstantType.所属公司.getTitle(), 0, true, 0, 1, 0, 0);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.项目名称.getTitle(), 1, true, 0, 1, 1, 1);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.房产类型.getTitle(), 2, true, 0, 1, 2, 2);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.房态类型.getTitle(), 3, true, 0, 1, 3, 3);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.期初.getTitle(), 4, true, 0, 0, 4, 7);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.本期.getTitle(), 8, true, 0, 0, 8, 11);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.期末.getTitle(), 12, true, 0, 0, 12, 15);
		rowList.add(cell);
		cell = new ExcelCell(ConstantType.累计.getTitle(), 16, true, 0, 0, 16, 19);
		rowList.add(cell);
		headerList.add(rowList);
		// 第二行
		rowList = new ArrayList<ExcelCell>();
		for (int i = 4; i < 20; i += 4) {
			cell = new ExcelCell(ConstantType.数量.getTitle(), i);
			rowList.add(cell);
			cell = new ExcelCell(ConstantType.计费面积.getTitle(), i + 1);
			rowList.add(cell);
			cell = new ExcelCell(ConstantType.数量比率.getTitle(), i + 2);
			rowList.add(cell);
			cell = new ExcelCell(ConstantType.面积比率.getTitle(), i + 3);
			rowList.add(cell);
		}
		headerList.add(rowList);
		// 创建表头
		excelUtil.createHeader(headerList);
		// 填充数据
		rowList.clear();
		List<List<ExcelCell>> dataList = new ArrayList<>();
		ReportRes temp = null;
		for (ReportRes res : rowDatas) {
			// 列数据
			rowList = new ArrayList<ExcelCell>();
			rowList.add(new ExcelCell(res.getCompanyName()));
			rowList.add(new ExcelCell(res.getHouseName()));
			rowList.add(new ExcelCell(res.getHouseTypeName()));
			rowList.add(new ExcelCell(res.getHouseSubTypeName()));
			temp = res.getBeforeInfo();
			this.cellDataHouseChange(temp, rowList);
			temp = res.getBenInfo();
			this.cellDataHouseChange(temp, rowList);
			temp = res.getAfterInfo();
			this.cellDataHouseChange(temp, rowList);
			temp = res.getNowInfo();
			this.cellDataHouseChange(temp, rowList);
			// 行数据
			dataList.add(rowList);
		}
		// 添加数据
		excelUtil.addData(dataList, excelUtil.getStyle());
	}

	/**
	 * @ApiParam(name="项目ID字符集，以“，”隔开") @RequestParam(required=false) String
	 *                                  houseIdJson
	 * @ApiParam(name="查询的开始时间") @RequestParam(required = false) Date startTime
	 * @ApiParam(name="查询的截止时间") @RequestParam(required = false) Date endTime
	 * @ApiParam(name="房产类型") @RequestParam(required = false) String houseType
	 * @ApiParam(name="房态类型") @RequestParam(required = false) String stage
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	@ApiOperation(value = "项目房产信息汇总报表")
	@RequestMapping(value = "/project-house", method = RequestMethod.POST)
	public RestResult<List<ReportRes>> projectHouseInfoReport(@RequestBody ReportFormVo vo) throws Exception {

		String houseIdJson = vo.getHouseIdJson();
		if (StringUtils.isEmpty(houseIdJson) || vo.getEndTime() == null) {
			return RestResult.PARAMS_MISSING;
		}
		vo.setEnterpriseId(LoginDataHelper.getEnterpriseId());
		vo.setHouseId(Long.valueOf(houseIdJson));
		// String[] tempStrs = houseIdJson.split(",");
		// List<Long> houseList = new ArrayList<Long>(tempStrs.length);
		// for (String temp : tempStrs) {
		// houseList.add(Long.parseLong(temp));
		// }

		// 获取登录用户数据权限
		// vo.setHouseIdList(houseList);
		// 获取查询的项目
		Map<String, Object> map = reportFormService.findBaseHouseInfo(vo);
		List<OwnerHouseBaseInfo> baseList = (List<OwnerHouseBaseInfo>) map.get("precinct");
		if (CollectionUtils.isEmpty(baseList)) {
			return new RestResult<>(ResultCodeEnum.DATA_NOT_EXIST.CODE, ResultCodeEnum.DATA_NOT_EXIST.DESC);
		}
		List<ReportRes> resReportFormList = null;
		if (ConstantType.REPORT_OWNER_HOUSETYPE.getTitle().equals(vo.getReportType())) {
			resReportFormList = this.dealwithHouseInfo(map, vo);
		} else if (ConstantType.REPORT_OWNER_FREEHOUSE.getTitle().equals(vo.getReportType())) {
			resReportFormList = this.houseFreeInfoReport(map, vo);
		} else if (ConstantType.REPORT_OWNER_CHANGEINFO.getTitle().equals(vo.getReportType())) {
			resReportFormList = this.houseChangeInfoReport(map, vo);
		}

		// 结果集
		return new RestResult<>(resReportFormList);
	}

	/**
	 * 项目房态变动信息汇总
	 * 
	 * @param baseList
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<ReportRes> houseChangeInfoReport(Map<String, Object> map, ReportFormVo vo) throws Exception {
		// 结果集
		List<ReportRes> resReportFormList = new ArrayList<ReportRes>();
		List<OwnerHouseBaseInfo> precinctList = (List<OwnerHouseBaseInfo>) map.get("precinct");

		List<HouseListEntity> houseList = (List<HouseListEntity>) map.get("house");
		Map<Long, List<HouseListEntity>> houseMap = houseList.stream()
				.collect(Collectors.groupingBy(HouseListEntity::getPrecinctId));
		// 项目名称，查询时间（起-止），房产类型，房产形态
		List<HouseListEntity> subBaseList = null;
		for (OwnerHouseBaseInfo precinct : precinctList) {
			String companyName = this.getOrganization(precinct.getOrganizationId());
			String houseName = precinct.getHouseName();
			Long houseId = precinct.getHouseId();

			List<HouseListEntity> tempList = houseMap.get(precinct.getHouseId());
			if (!CollectionUtils.isEmpty(tempList)) {
				Map<String, List<HouseListEntity>> houseTypeMap = tempList.stream()
						.collect(Collectors.groupingBy(HouseListEntity::getHouseType));
				List<ReportRes> tempReportList = null;
				if (vo.getHouseType().equals(HouseTypeEnum.ROOM.getValue())) {
					// 房间
					subBaseList = houseTypeMap.get(HouseTypeEnum.ROOM.getValue());
					resReportFormList = this.houseChageStatisticsInfo(subBaseList, vo, companyName, houseName, houseId,
							HouseTypeEnum.ROOM);
				} else if (vo.getHouseType().equals(HouseTypeEnum.CARPORT.getValue())) {
					// 车位
					subBaseList = houseTypeMap.get(HouseTypeEnum.CARPORT.getValue());
					resReportFormList = this.houseChageStatisticsInfo(subBaseList, vo, companyName, houseName, houseId,
							HouseTypeEnum.CARPORT);
				} else if (vo.getHouseType().equals(HouseTypeEnum.PUBLICAREA.getValue())) {
					// 公共区域
					subBaseList = houseTypeMap.get(HouseTypeEnum.PUBLICAREA.getValue());
					resReportFormList = this.houseChageStatisticsInfo(subBaseList, vo, companyName, houseName, houseId,
							HouseTypeEnum.PUBLICAREA);
				} else {
					// 房间
					subBaseList = houseTypeMap.get(HouseTypeEnum.ROOM.getValue());
					resReportFormList = this.houseChageStatisticsInfo(subBaseList, vo, companyName, houseName, houseId,
							HouseTypeEnum.ROOM);
					// 车位
					subBaseList = houseTypeMap.get(HouseTypeEnum.CARPORT.getValue());
					tempReportList = this.houseChageStatisticsInfo(subBaseList, vo, companyName, houseName, houseId,
							HouseTypeEnum.CARPORT);
					if (!CollectionUtils.isEmpty(tempReportList)) {
						resReportFormList = Stream.of(resReportFormList, tempReportList).flatMap(data -> data.stream())
								.collect(Collectors.toList());
					}
					// 公共区域
					subBaseList = houseTypeMap.get(HouseTypeEnum.PUBLICAREA.getValue());
					tempReportList = this.houseChageStatisticsInfo(subBaseList, vo, companyName, houseName, houseId,
							HouseTypeEnum.PUBLICAREA);
					if (!CollectionUtils.isEmpty(tempReportList)) {
						resReportFormList = Stream.of(resReportFormList, tempReportList).flatMap(data -> data.stream())
								.collect(Collectors.toList());
					}
				}
			}

		}

		return resReportFormList;
	}

	/**
	 * 项目汇总-收费项目
	 * 
	 * @param baseList
	 * @param vo
	 * @return
	 */
	private List<ReportRes> houseFreeInfoReport(Map<String, Object> map, ReportFormVo vo) {
		// 结果集
		List<ReportRes> resReportFormList = new ArrayList<ReportRes>();

		return resReportFormList;
	}

	/**
	 * 项目汇总-房产类型
	 * 
	 * @param baseList
	 * @param vo
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	private List<ReportRes> dealwithHouseInfo(Map<String, Object> map, ReportFormVo vo) throws Exception {
		// 结果集
		List<ReportRes> resReportFormList = new ArrayList<ReportRes>();
		// 获取所有的子房产
		List<HouseListEntity> subBaseList = null;
		Map<String, List<HouseListEntity>> houseTypeMap = null;
		//
		List<OwnerHouseBaseInfo> precinctList = (List<OwnerHouseBaseInfo>) map.get("precinct");

		List<HouseListEntity> houseList = (List<HouseListEntity>) map.get("house");

		NsCoreDictionaryVo roomTypeDic = getDictionary("roomTypeId", LoginDataHelper.getOrgId());
		NsCoreDictionaryVo carportTypeDic = getDictionary("carportTypeId", LoginDataHelper.getOrgId());
		Map<Long, List<HouseListEntity>> houseMap = houseList.stream()
				.collect(Collectors.groupingBy(HouseListEntity::getPrecinctId));
		for (OwnerHouseBaseInfo precinct : precinctList) {
			String companyName = this.getOrganization(precinct.getOrganizationId());
			List<HouseListEntity> tempList = houseMap.get(precinct.getHouseId());
			if (!CollectionUtils.isEmpty(tempList)) {
				houseTypeMap = tempList.stream().collect(Collectors.groupingBy(HouseListEntity::getHouseType));
				// 房产
				subBaseList = houseTypeMap.get(HouseTypeEnum.ROOM.getValue());
				List<ReportRes> tempReportList = this.statisticsInfo(subBaseList, vo, companyName,
						precinct.getHouseName(), precinct.getHouseId(), HouseTypeEnum.ROOM, roomTypeDic);
				resReportFormList = Stream.of(resReportFormList, tempReportList).flatMap(data -> data.stream())
						.collect(Collectors.toList());
				// 车位
				subBaseList = houseTypeMap.get(HouseTypeEnum.CARPORT.getValue());
				tempReportList = this.statisticsInfo(subBaseList, vo, companyName, precinct.getHouseName(),
						precinct.getHouseId(), HouseTypeEnum.ROOM, carportTypeDic);
				if (!CollectionUtils.isEmpty(tempReportList)) {
					resReportFormList = Stream.of(resReportFormList, tempReportList).flatMap(data -> data.stream())
							.collect(Collectors.toList());
				}
				// 公共区域
				subBaseList = houseTypeMap.get(HouseTypeEnum.PUBLICAREA.getValue());
				tempReportList = this.statisticsInfo(subBaseList, vo, companyName, precinct.getHouseName(),
						precinct.getHouseId(), HouseTypeEnum.ROOM, null);
				if (!CollectionUtils.isEmpty(tempReportList)) {
					resReportFormList = Stream.of(resReportFormList, tempReportList).flatMap(data -> data.stream())
							.collect(Collectors.toList());
				}
			}
		}
		// for (HouseListEntity baseInfo : houseList) {
		// String companyName =
		// this.getOrganization(baseInfo.getOrganizationId());
		// String houseName = baseInfo.getHouseName();
		// Long houseId = baseInfo.getHouseId();

		// if (CollectionUtils.isEmpty(subBaseList)) {
		// subBaseList = this.getSubHouseInfo(houseList);
		// } else { //合并List
		// subBaseList = Stream.of(subBaseList,
		// this.getSubHouseInfo(houseList)).flatMap(data ->
		// data.stream()).collect(Collectors.toList());
		// }
		// if (CollectionUtils.isEmpty(subBaseList)) { //无子房产
		// subBaseList = houseList;
		// }

		// }
		return resReportFormList;
	}

	/**
	 * 统计
	 * 
	 * @param tempBaseList
	 * @param companyName
	 * @param houseName
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	private Map<String, ReportRes> statisticsSubInfo(List<HouseListEntity> resultBaseList, String companyName,
			String houseName, long houseId, HouseTypeEnum houseTypeEnum, NsCoreDictionaryVo dictionaryVo)
			throws Exception {
		Map<String, ReportRes> reportMap = null;
		if (!CollectionUtils.isEmpty(resultBaseList)) {
			ReportFormVo reportForm = new ReportFormVo();
			ReportRes report = null;
			List<Long> resultHouseIdList = null;
			List<HouseListEntity> tempResList = null;
			Map<String, List<HouseListEntity>> houseTypeMap = resultBaseList.stream()
					.collect(Collectors.groupingBy(HouseListEntity::getRoomTypeId));
			reportMap = new HashMap<>(houseTypeMap.size());
			for (Iterator<Entry<String, List<HouseListEntity>>> iterator = houseTypeMap.entrySet().iterator(); iterator
					.hasNext();) {
				Entry<String, List<HouseListEntity>> entry = iterator.next();
				BigDecimal sumCharge = BigDecimal.ZERO;
				BigDecimal sumBuild = BigDecimal.ZERO;
				BigDecimal sumInside = BigDecimal.ZERO;
				BigDecimal sumPool = BigDecimal.ZERO;
				report = reportForm.new ReportRes();
				tempResList = entry.getValue();
				resultHouseIdList = new ArrayList<Long>(tempResList.size());
				for (HouseListEntity house : tempResList) {
					sumCharge = sumCharge.add(CommonUtils.long2Decimal(house.getChargingArea()));
					sumBuild = sumBuild.add(CommonUtils.long2Decimal(house.getBuildingArea()));
					sumInside = sumInside.add(CommonUtils.long2Decimal(house.getInsideArea()));
					sumPool = sumPool.add(CommonUtils.long2Decimal(house.getPoolArea()));
					resultHouseIdList.add(house.getHouseId());
				}

				report.setPrecinctId(houseId);
				report.setCompanyName(companyName);
				report.setPrecinctName(houseName);
				report.setCount((long) tempResList.size());
				report.setHouseType(houseTypeEnum.getValue());
				report.setHouseTypeName(houseTypeEnum.getTitle());
				report.setHouseSubType(entry.getKey());
				report.setHouseSubTypeName(OwnerUtils.getDicName(dictionaryVo, entry.getKey()));
				report.setChargingArea(sumCharge);
				report.setBuildingArea(sumBuild);
				report.setInsidingArea(sumInside);
				report.setPoolArea(sumPool);
				report.setHouseIdList(resultHouseIdList);
				reportMap.put(entry.getKey(), report);
			}
		}

		return reportMap;
	}

	/**
	 * 分类
	 * 
	 * @param houseTypeCountMap
	 * @param houseTypeMap
	 * @param index
	 */
	private void partingStatisticsInfo(Map<String, ReportRes> houseTypeCountMap, Map<String, ReportRes> houseTypeMap,
			int index) {
		if (!CollectionUtils.isEmpty(houseTypeCountMap)) {
			ReportRes obj1 = null;
			ReportRes obj2 = null;
			for (Iterator<Entry<String, ReportRes>> iterator = houseTypeCountMap.entrySet().iterator(); iterator
					.hasNext();) {
				Entry<String, ReportRes> entry = iterator.next();
				if (!houseTypeMap.containsKey(entry.getKey())) {
					obj2 = entry.getValue();
					obj2.setHouseIdList(null);
					houseTypeMap.put(entry.getKey(), obj2);
				} else {
					obj1 = houseTypeMap.get(entry.getKey());
					obj2 = entry.getValue();
					obj1.setCount(obj2.getCount() + obj1.getCount());
					obj1.setChargingArea(obj2.getChargingArea().add(obj1.getChargingArea()));
					obj1.setBuildingArea(obj2.getBuildingArea().add(obj1.getBuildingArea()));
					obj1.setPoolArea(obj2.getPoolArea().add(obj1.getPoolArea()));
					obj1.setInsidingArea(obj2.getInsidingArea().add(obj1.getInsidingArea()));
					switch (index) {
					case 1: // 空置
						obj1.setSaleNotInfo(obj2);
						break;
					case 2: // 已售
						obj1.setSaledInfo(obj2);
						break;
					case 3: // 未领
						obj1.setSoufangWaitInfo(obj2);
						break;
					case 4: // 收房
						obj1.setSoufangedInfo(obj2);
						break;
					case 5: // 入住
						obj1.setRuzhuInfo(obj2);
						break;
					case 6: // 空关
						obj1.setRuzhuNotInfo(obj2);
						break;
					case 7: // 装修中
						obj1.setDecoratingInfo(obj2);
						break;
					case 8: // 已装修
						obj1.setDecoratedInfo(obj2);
						break;
					case 9: // 未装修
						obj1.setDecorateNotInfo(obj2);
						break;
					case 10: // 已出租
						obj1.setRentInfo(obj2);
						break;
					case 11: // 停用
						obj1.setStopInfo(obj2);
						break;
					default:
						break;
					}
				}
			}

		}
	}

	/**
	 * 分类统计
	 * 
	 * @param baseList
	 * @param vo
	 * @param companyName
	 * @param houseName
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	private List<ReportRes> statisticsInfo(List<HouseListEntity> baseList, ReportFormVo vo, String companyName,
			String houseName, long houseId, HouseTypeEnum houseTypeEnum, NsCoreDictionaryVo dictionaryVo)
			throws Exception {
		if (CollectionUtils.isEmpty(baseList)) {
			return null;
		}
		List<Long> houseIdList = baseList.stream().map(base -> base.getHouseId()).collect(Collectors.toList());
		ReportFormVo formVo = new ReportFormVo();
		Map<String, ReportRes> houseTypeMap = new HashMap<String, ReportRes>();
		// 空置
		formVo = new ReportFormVo();
		formVo.setHandleTime(vo.getEndTime());
		formVo.setOrganizationId(vo.getOrganizationId());
		formVo.setHouseOperateType(HouseOperateTypeEnum.SHOU_LOU.getValue());
		List<HouseListEntity> houseEmptyList = reportFormService.findHouseResultInfo(houseIdList, formVo);
		Map<String, ReportRes> houseTypeCountMap = this.statisticsSubInfo(houseEmptyList, companyName, houseName,
				houseId, houseTypeEnum, dictionaryVo);
		this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 1);
		// 已售
		formVo = new ReportFormVo();
		formVo.setHandleTime(vo.getEndTime());
		formVo.setOrganizationId(vo.getOrganizationId());
		formVo.setStageGt(HouseStageEnum.KONG_ZHI.getValue());
		List<HouseListEntity> houseSaledList = reportFormService.findHouseResultInfo(houseIdList, formVo);
		houseTypeCountMap = this.statisticsSubInfo(houseSaledList, companyName, houseName, houseId, houseTypeEnum,
				dictionaryVo);
		this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 2);
		// 未领
		// 在已售的数据中筛选
		List<HouseListEntity> tempHouseList = null;
		if (!CollectionUtils.isEmpty(houseSaledList)) {
			tempHouseList = houseSaledList.stream()
					.filter(house -> HouseStageEnum.WEI_LING.getValue().equals(house.getHouseStage()))
					.collect(Collectors.toList());
			houseTypeCountMap = this.statisticsSubInfo(tempHouseList, companyName, houseName, houseId, houseTypeEnum,
					dictionaryVo);
			this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 3);
		}
		// 收房
		List<HouseListEntity> obtainHouseList = null;
		if (!CollectionUtils.isEmpty(houseSaledList)) {
			obtainHouseList = houseSaledList.stream()
					.filter(house -> !HouseStageEnum.WEI_LING.getValue().equals(house.getHouseStage()))
					.collect(Collectors.toList());
			houseTypeCountMap = this.statisticsSubInfo(obtainHouseList, companyName, houseName, houseId, houseTypeEnum,
					dictionaryVo);
			this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 4);
		}
		// 入住
		// 在收房的数据中筛选
		if (!CollectionUtils.isEmpty(obtainHouseList)) {
			tempHouseList = obtainHouseList.stream()
					.filter(house -> HouseStageEnum.RU_ZHU.getValue().equals(house.getHouseStage()))
					.collect(Collectors.toList());
			houseTypeCountMap = this.statisticsSubInfo(tempHouseList, companyName, houseName, houseId, houseTypeEnum,
					dictionaryVo);
			this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 5);
		}
		// 空关
		if (!CollectionUtils.isEmpty(obtainHouseList)) {
			tempHouseList = obtainHouseList.stream()
					.filter(house -> !HouseStageEnum.RU_ZHU.getValue().equals(house.getHouseStage()))
					.collect(Collectors.toList());
			houseTypeCountMap = this.statisticsSubInfo(tempHouseList, companyName, houseName, houseId, houseTypeEnum,
					dictionaryVo);
			this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 6);
		}

		// 装修
		formVo = new ReportFormVo();
		formVo.setOrganizationId(vo.getOrganizationId());
		formVo.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue());
		formVo.setEndTime(vo.getEndTime());
		List<HouseListEntity> houseDecorateList = reportFormService.findHouseResultInfo(houseIdList, formVo);
		// 装修中
		if (!CollectionUtils.isEmpty(houseDecorateList)) {
			tempHouseList = houseDecorateList.stream().filter(
					house -> HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue().equals(house.getDecorateStage()))
					.collect(Collectors.toList());
			houseTypeCountMap = this.statisticsSubInfo(tempHouseList, companyName, houseName, houseId, houseTypeEnum,
					dictionaryVo);
			this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 7);
			houseDecorateList.removeAll(tempHouseList);
		}
		// 已装修
		if (!CollectionUtils.isEmpty(houseDecorateList)) {
			tempHouseList = houseDecorateList.stream().filter(
					house -> HouseDecorateStageEnum.DECORATE_STAGE_DONE.getValue().equals(house.getDecorateStage()))
					.collect(Collectors.toList());
			houseTypeCountMap = this.statisticsSubInfo(tempHouseList, companyName, houseName, houseId, houseTypeEnum,
					dictionaryVo);
			this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 8);
			houseDecorateList.removeAll(tempHouseList);
		}
		// 未装修
		if (!CollectionUtils.isEmpty(houseDecorateList)) {
			houseTypeCountMap = this.statisticsSubInfo(houseDecorateList, companyName, houseName, houseId,
					houseTypeEnum, dictionaryVo);
			this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 9);
		}

		// 已出租
		formVo = new ReportFormVo();
		formVo.setOrganizationId(vo.getOrganizationId());
		formVo.setRentStage(HouseRentStageEnum.RENT_STAGE_IN.getValue());
		formVo.setStartTime(vo.getEndTime());
		List<HouseListEntity> houseRentList = reportFormService.findHouseResultInfo(houseIdList, formVo);
		if (!CollectionUtils.isEmpty(houseRentList)) {
			houseRentList = houseRentList.stream()
					.filter(house -> HouseRentStageEnum.RENT_STAGE_IN.getValue().equals(house.getRentStage()))
					.collect(Collectors.toList());
			houseTypeCountMap = this.statisticsSubInfo(houseRentList, companyName, houseName, houseId, houseTypeEnum,
					dictionaryVo);
			this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 10);
		}

		// 停用
		formVo = new ReportFormVo();
		formVo.setOrganizationId(vo.getOrganizationId());
		formVo.setIsBlockUp(1);
		formVo.setBlockUpTime(vo.getEndTime());
		List<HouseListEntity> houseBlockList = reportFormService.findHouseResultInfo(houseIdList, formVo);
		houseTypeCountMap = this.statisticsSubInfo(houseBlockList, companyName, houseName, houseId, houseTypeEnum,
				dictionaryVo);
		this.partingStatisticsInfo(houseTypeCountMap, houseTypeMap, 11);

		List<ReportRes> list = new ArrayList<ReportRes>();
		for (Iterator<Entry<String, ReportRes>> iterator = houseTypeMap.entrySet().iterator(); iterator.hasNext();) {
			Entry<String, ReportRes> entry = iterator.next();
			list.add(entry.getValue());
		}

		return list;
	}

	/**
	 * 统计房态变更统计
	 * 
	 * @param tempResList
	 * @param companyName
	 * @param houseName
	 * @param houseId
	 * @return
	 */
	private ReportRes statisticsHouseChageSubInfo(List<HouseListEntity> tempResList) {
		ReportFormVo formVo = new ReportFormVo();
		ReportRes report = formVo.new ReportRes();
		BigDecimal sumCharge = BigDecimal.ZERO;
		BigDecimal sumBuild = BigDecimal.ZERO;
		BigDecimal sumInside = BigDecimal.ZERO;
		BigDecimal sumPool = BigDecimal.ZERO;
		long count = 0;
		if (!CollectionUtils.isEmpty(tempResList)) {
			List<Long> resultHouseIdList = new ArrayList<Long>(tempResList.size());
			for (HouseListEntity house : tempResList) {
				sumCharge = sumCharge.add(CommonUtils.long2Decimal(house.getChargingArea()));
				sumBuild = sumBuild.add(CommonUtils.long2Decimal(house.getBuildingArea()));
				sumInside = sumInside.add(CommonUtils.long2Decimal(house.getInsideArea()));
				sumPool = sumPool.add(CommonUtils.long2Decimal(house.getPoolArea()));
				count++;
				resultHouseIdList.add(house.getHouseId());
			}
			report.setHouseIdList(resultHouseIdList);
		}
		report.setCount(count);
		report.setChargingArea(sumCharge);
		report.setBuildingArea(sumBuild);
		report.setInsidingArea(sumInside);
		report.setPoolArea(sumPool);
		report.setCounRatio(0.0f);
		report.setChargingAreaRatio(0.0f);
		return report;
	}

	/**
	 * 房态变更，前，中，末，累计时期(出售，入住)
	 * 
	 * @param houseIdList
	 * @param vo
	 * @param companyName
	 * @param houseName
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	private List<ReportRes> houseChageStatisticsInfo(List<HouseListEntity> baseList, ReportFormVo vo,
			String companyName, String houseName, Long houseId, HouseTypeEnum houseTypeEnum) throws Exception {
		if (CollectionUtils.isEmpty(baseList)) {
			return null;
		}
		List<ReportRes> list = null;
		ReportFormVo formVo = new ReportFormVo();
		ReportRes report = null;
		List<Long> houseIdList = baseList.stream().map(base -> base.getHouseId()).collect(Collectors.toList());
		// =======期初=========
		Map<ConstantType, ReportRes> mapBefore = this.statisticsHouseChangeInfo(houseIdList, vo, vo.getStartTime());
		// =======期末=========
		Map<ConstantType, ReportRes> mapAfter = this.statisticsHouseChangeInfo(houseIdList, vo, vo.getEndTime());
		// =======本期=========
		// 期末数据-期初数据
		Map<ConstantType, ReportRes> mapBen = null;
		if (!CollectionUtils.isEmpty(mapAfter) && !CollectionUtils.isEmpty(mapBefore)) {
			mapBen = new HashMap<>(mapAfter.size());
			ReportRes before = null;
			ReportRes after = null;
			for (Iterator<Entry<ConstantType, ReportRes>> iterator = mapAfter.entrySet().iterator(); iterator
					.hasNext();) {
				Entry<ConstantType, ReportRes> entry = iterator.next();
				after = entry.getValue();
				before = mapBefore.get(entry.getKey());
				report = formVo.new ReportRes();
				if (after != null) {
					report.setCount(after.getCount());
					report.setChargingArea(after.getChargingArea());
					report.setBuildingArea(after.getBuildingArea());
					report.setInsidingArea(after.getInsidingArea());
					report.setPoolArea(after.getPoolArea());
					if (before != null) {
						if (after.getCount() > 0)
							report.setCount(after.getCount() - before.getCount());
						if (after.getChargingArea().compareTo(BigDecimal.ZERO) > 0)
							report.setChargingArea(after.getChargingArea().subtract(before.getChargingArea()));
						if (after.getBuildingArea().compareTo(BigDecimal.ZERO) > 0)
							report.setBuildingArea(after.getBuildingArea().subtract(before.getBuildingArea()));
						if (after.getInsidingArea().compareTo(BigDecimal.ZERO) > 0)
							report.setInsidingArea(after.getInsidingArea().subtract(before.getInsidingArea()));
						if (after.getPoolArea().compareTo(BigDecimal.ZERO) > 0)
							report.setPoolArea(after.getPoolArea().subtract(before.getPoolArea()));
						if (!CollectionUtils.isEmpty(after.getHouseIdList())
								&& !CollectionUtils.isEmpty(before.getHouseIdList())) {
							after.getHouseIdList().removeAll(before.getHouseIdList());
						}
					}
					report.setHouseIdList(after.getHouseIdList());
					report.setHouseSubTypeName(after.getHouseSubTypeName());
					report.setCounRatio(0.0f);
					report.setChargingAreaRatio(0.0f);
					mapBen.put(entry.getKey(), report);
				}
			}
		}
		// 处理本期数据比率
		this.statisticsRatioCount(mapBen.get(ConstantType.空置), mapBen.get(ConstantType.已售));
		this.statisticsRatioCount(mapBen.get(ConstantType.待收房), mapBen.get(ConstantType.已收房));
		this.statisticsRatioCount(mapBen.get(ConstantType.入住), mapBen.get(ConstantType.空关));
		this.statisticsRatioCount(mapBen.get(ConstantType.装修中), mapBen.get(ConstantType.已装修),
				mapBen.get(ConstantType.未装修));
		this.statisticsRatioCount(mapBen.get(ConstantType.出租), mapBen.get(ConstantType.未租));
		// =======累计=========
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
		Map<ConstantType, ReportRes> mapNow = this.statisticsHouseChangeInfo(houseIdList, vo,
				df.parse(df.format(new Date())));
		// 整合以上各时期数据
		if (!CollectionUtils.isEmpty(mapBefore)) {
			ReportRes temp = null;
			list = new ArrayList<>();
			for (Iterator<Entry<ConstantType, ReportRes>> iterator = mapBefore.entrySet().iterator(); iterator
					.hasNext();) {
				Entry<ConstantType, ReportRes> entry = iterator.next();
				report = formVo.new ReportRes();
				if (report != null) {
					report.setCompanyName(companyName);
					report.setHouseId(houseId);
					report.setHouseName(houseName);
					report.setHouseType(houseTypeEnum.getValue());
					report.setHouseTypeName(houseTypeEnum.getTitle());
					report.setHouseSubType(entry.getKey().getValue());
					report.setHouseSubTypeName(entry.getKey().getTitle());
					// 期初
					temp = entry.getValue();
					if (temp != null) {
						report.setCount(temp.getCount());
						report.setChargingArea(temp.getChargingArea());
					} else {
						report.setCount(0L);
						report.setChargingArea(BigDecimal.ZERO);
					}
					report.setBeforeInfo(temp);
					// 本期
					temp = mapBen.get(entry.getKey());
					if (temp != null) {
						report.setCount(report.getCount() + (temp == null ? 0 : temp.getCount()));
						report.setChargingArea(report.getChargingArea().add(temp.getChargingArea()));
						report.setBenInfo(temp);
					}
					// 期末
					temp = mapAfter.get(entry.getKey());
					if (temp != null) {
						report.setCount(report.getCount() + (temp == null ? 0 : temp.getCount()));
						report.setChargingArea(report.getChargingArea().add(temp.getChargingArea()));
						report.setAfterInfo(temp);
					}
					// 累计
					temp = mapNow.get(entry.getKey());
					if (temp != null) {
						report.setCount(report.getCount() + (temp == null ? 0 : temp.getCount()));
						report.setChargingArea(report.getChargingArea().add(temp.getChargingArea()));
						report.setNowInfo(temp);
					}

					list.add(report);
				}
			}
		}

		return list;
	}

	/**
	 * 房态变更求和，与比率统计
	 * 
	 * @param params
	 */
	private void statisticsRatioCount(ReportRes... params) {
		// 提取类型
		long count = 0;
		BigDecimal changeArea = BigDecimal.ZERO;
		for (ReportRes res : params) { // 统计总和
			if (res != null) {
				count += res.getCount();
				changeArea = res.getChargingArea().add(changeArea);
			}
		}
		for (ReportRes res : params) {// 计算比率
			if (res == null)
				continue;
			if (count == 0) {
				res.setCounRatio(0f);
			} else {
				res.setCounRatio(new BigDecimal(res.getCount())
						.divide(new BigDecimal(count), 2, BigDecimal.ROUND_HALF_UP).floatValue());
			}
			if (changeArea.compareTo(BigDecimal.ZERO) == 0) {
				res.setChargingAreaRatio(0f);
			} else {
				res.setChargingAreaRatio(
						res.getChargingArea().divide(changeArea, 2, BigDecimal.ROUND_HALF_UP).floatValue());
			}
		}
	}

	/**
	 * 房态变更统计
	 * 
	 * @param baseList
	 * @param vo
	 * @param companyName
	 * @param houseName
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	private Map<ConstantType, ReportRes> statisticsHouseChangeInfo(List<Long> houseIdList, ReportFormVo vo, Date time)
			throws Exception {
		if (CollectionUtils.isEmpty(houseIdList)) {
			return null;
		}
		Map<ConstantType, ReportRes> map = new HashMap<>();
		// 房态类型
		String stage = vo.getStage();
		if (!StringUtils.isEmpty(stage)) {
			List<String> houses = Arrays.asList(ConstantType.空置.getValue(), ConstantType.已售.getValue(),
					ConstantType.待收房.getValue(), ConstantType.已收房.getValue(), ConstantType.入住.getValue(),
					ConstantType.空关.getValue());
			if (houses.contains(stage)) {
				this.houseChangeHouseOperation(vo, map, houseIdList, time);
			}
			List<String> decorates = Arrays.asList(ConstantType.装修中.getValue(), ConstantType.已装修.getValue(),
					ConstantType.未装修.getValue());
			if (decorates.contains(stage)) {
				vo.setStage(null);
				vo.setDecorateStage(stage);
				this.houseChangeDecorateOperation(vo, map, houseIdList, time);
			}
			List<String> rents = Arrays.asList(ConstantType.出租.getValue(), ConstantType.未租.getValue());
			if (rents.contains(stage)) {
				vo.setStage(null);
				vo.setRentStage(stage);
				this.houseChangeRentOperation(vo, map, houseIdList, time);
			}
		} else {
			this.houseChangeHouseOperation(vo, map, houseIdList, time);
			this.houseChangeDecorateOperation(vo, map, houseIdList, time);
			this.houseChangeRentOperation(vo, map, houseIdList, time);
		}

		return map;
	}

	// 空置，已售，待收房，已收房，入住，空关
	private void houseChangeHouseOperation(ReportFormVo vo, Map<ConstantType, ReportRes> map, List<Long> houseIdList,
			Date time) throws Exception {
		// 空置
		String stage = vo.getStage();
		ReportFormVo formVo = new ReportFormVo();
		formVo.setHandleTime(time);
		formVo.setOrganizationId(vo.getOrganizationId());
		if (!StringUtils.isEmpty(stage)) {
			if (ConstantType.空置.getValue().equals(stage)) {
				formVo.setHouseOperateType(HouseOperateTypeEnum.SHOU_LOU.getValue());
				List<HouseListEntity> houseEmptyList = reportFormService.findHouseResultInfo(houseIdList, formVo);
				// 数据整理，计算
				ReportRes tempReport1 = this.statisticsHouseChageSubInfo(houseEmptyList);
				tempReport1.setHouseSubTypeName(ConstantType.空置.getTitle());
				map.put(ConstantType.空置, tempReport1);
			} else {
				List<HouseListEntity> tempHouseList = null;
				ReportRes tempReport = null;
				formVo = new ReportFormVo();
				formVo.setHandleTime(time);
				formVo.setOrganizationId(vo.getOrganizationId());
				formVo.setStageGt(HouseStageEnum.KONG_ZHI.getValue());
				List<HouseListEntity> houseSaledList = reportFormService.findHouseResultInfo(houseIdList, formVo);
				if (ConstantType.已售.getValue().equals(stage)) {
					tempReport = this.statisticsHouseChageSubInfo(houseSaledList);
					tempReport.setHouseSubTypeName(ConstantType.已售.getTitle());
					map.put(ConstantType.已售, tempReport);
				} else if (ConstantType.待收房.getValue().equals(stage)) {
					if (!CollectionUtils.isEmpty(houseSaledList)) {
						tempHouseList = houseSaledList.stream()
								.filter(house -> HouseStageEnum.WEI_LING.getValue().equals(house.getHouseStage()))
								.collect(Collectors.toList());
						tempReport = this.statisticsHouseChageSubInfo(tempHouseList);
						tempReport.setHouseSubTypeName(ConstantType.待收房.getTitle());
					}
					map.put(ConstantType.待收房, tempReport);
				} else if (ConstantType.已收房.getValue().equals(stage)) {
					if (!CollectionUtils.isEmpty(houseSaledList)) {
						tempHouseList = houseSaledList.stream()
								.filter(house -> !HouseStageEnum.WEI_LING.getValue().equals(house.getHouseStage()))
								.collect(Collectors.toList());
						tempReport = this.statisticsHouseChageSubInfo(tempHouseList);
						tempReport.setHouseSubTypeName(ConstantType.已收房.getTitle());
					}
					map.put(ConstantType.已收房, tempReport);
				} else if (ConstantType.入住.getValue().equals(stage)) {
					if (!CollectionUtils.isEmpty(houseSaledList)) {
						tempHouseList = houseSaledList.stream()
								.filter(house -> HouseStageEnum.RU_ZHU.getValue().equals(house.getHouseStage()))
								.collect(Collectors.toList());
						tempReport = this.statisticsHouseChageSubInfo(tempHouseList);
						tempReport.setHouseSubTypeName(ConstantType.入住.getTitle());
					}
					map.put(ConstantType.入住, tempReport);
				} else if (ConstantType.空关.getValue().equals(stage)) {
					if (!CollectionUtils.isEmpty(houseSaledList)) {
						tempHouseList = houseSaledList.stream()
								.filter(house -> HouseStageEnum.KONG_GUAN.getValue().equals(house.getHouseStage()))
								.collect(Collectors.toList());
						tempReport = this.statisticsHouseChageSubInfo(tempHouseList);
						tempReport.setHouseSubTypeName(ConstantType.空关.getTitle());
					}
					map.put(ConstantType.空关, tempReport);
				}
			}
		} else {
			formVo.setHouseOperateType(HouseOperateTypeEnum.SHOU_LOU.getValue());
			List<HouseListEntity> houseEmptyList = reportFormService.findHouseResultInfo(houseIdList, formVo);
			// 数据整理，计算
			ReportRes tempReport1 = this.statisticsHouseChageSubInfo(houseEmptyList);
			tempReport1.setHouseSubTypeName(ConstantType.空置.getTitle());
			// 已售
			formVo = new ReportFormVo();
			formVo.setHandleTime(time);
			formVo.setOrganizationId(vo.getOrganizationId());
			formVo.setStageGt(HouseStageEnum.KONG_ZHI.getValue());
			List<HouseListEntity> houseSaledList = reportFormService.findHouseResultInfo(houseIdList, formVo);
			ReportRes tempReport2 = this.statisticsHouseChageSubInfo(houseSaledList);
			tempReport2.setHouseSubTypeName(ConstantType.已售.getTitle());
			this.statisticsRatioCount(tempReport1, tempReport2);
			map.put(ConstantType.空置, tempReport1);
			map.put(ConstantType.已售, tempReport2);
			tempReport1 = null;
			tempReport2 = null;
			// 未领
			// 在已售的数据中筛选
			List<HouseListEntity> tempHouseList = null;
			if (!CollectionUtils.isEmpty(houseSaledList)) {
				tempHouseList = houseSaledList.stream()
						.filter(house -> HouseStageEnum.WEI_LING.getValue().equals(house.getHouseStage()))
						.collect(Collectors.toList());
				tempReport1 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport1.setHouseSubTypeName(ConstantType.待收房.getTitle());
			}
			// 收房
			List<HouseListEntity> obtainHouseList = null;
			if (!CollectionUtils.isEmpty(houseSaledList)) {
				obtainHouseList = houseSaledList.stream()
						.filter(house -> !HouseStageEnum.WEI_LING.getValue().equals(house.getHouseStage()))
						.collect(Collectors.toList());
				tempReport2 = this.statisticsHouseChageSubInfo(obtainHouseList);
				tempReport2.setHouseSubTypeName(ConstantType.已收房.getTitle());
			}
			this.statisticsRatioCount(tempReport1, tempReport2);
			map.put(ConstantType.待收房, tempReport1);
			map.put(ConstantType.已收房, tempReport2);
			tempReport1 = null;
			tempReport2 = null;
			// 入住
			// 在收房的数据中筛选
			if (!CollectionUtils.isEmpty(obtainHouseList)) {
				tempHouseList = obtainHouseList.stream()
						.filter(house -> HouseStageEnum.RU_ZHU.getValue().equals(house.getHouseStage()))
						.collect(Collectors.toList());
				tempReport1 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport1.setHouseSubTypeName(ConstantType.入住.getTitle());
			}
			// 空关
			if (!CollectionUtils.isEmpty(obtainHouseList)) {
				tempHouseList = obtainHouseList.stream()
						.filter(house -> !HouseStageEnum.RU_ZHU.getValue().equals(house.getHouseStage()))
						.collect(Collectors.toList());
				tempReport2 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport2.setHouseSubTypeName(ConstantType.空关.getTitle());
			}
			this.statisticsRatioCount(tempReport1, tempReport2);
			map.put(ConstantType.入住, tempReport1);
			map.put(ConstantType.空关, tempReport2);
			tempReport1 = null;
			tempReport2 = null;
		}
	}

	// 装修
	private void houseChangeDecorateOperation(ReportFormVo vo, Map<ConstantType, ReportRes> map, List<Long> houseIdList,
			Date time) throws Exception {
		// 装修
		ReportFormVo formVo = new ReportFormVo();
		formVo.setOrganizationId(vo.getOrganizationId());
		formVo.setDecorateStage(HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue());
		formVo.setEndTime(time);
		List<HouseListEntity> houseDecorateList = reportFormService.findHouseResultInfo(houseIdList, formVo);
		List<HouseListEntity> tempHouseList;
		String decorateStage = vo.getDecorateStage();
		ReportRes tempReport1 = null;
		ReportRes tempReport2 = null;
		ReportRes tempReport3 = null;
		if (!StringUtils.isEmpty(decorateStage) && !CollectionUtils.isEmpty(houseDecorateList)) {
			if (ConstantType.装修中.getValue().equals(decorateStage)) {
				tempHouseList = houseDecorateList.stream().filter(
						house -> HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue().equals(house.getDecorateStage()))
						.collect(Collectors.toList());
				tempReport1 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport1.setHouseSubTypeName(ConstantType.装修中.getTitle());
				map.put(ConstantType.装修中, tempReport1);
			} else if (ConstantType.已装修.getValue().equals(decorateStage)) {
				tempHouseList = houseDecorateList.stream().filter(
						house -> HouseDecorateStageEnum.DECORATE_STAGE_DONE.getValue().equals(house.getDecorateStage()))
						.collect(Collectors.toList());
				tempReport2 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport2.setHouseSubTypeName(ConstantType.已装修.getTitle());
				map.put(ConstantType.已装修, tempReport2);
			} else if (ConstantType.未装修.getValue().equals(decorateStage)) {
				tempHouseList = houseDecorateList.stream().filter(
						house -> HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue().equals(house.getDecorateStage()))
						.collect(Collectors.toList());
				tempReport3 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport3.setHouseSubTypeName(ConstantType.未装修.getTitle());
				map.put(ConstantType.未装修, tempReport3);
			}
		} else {
			// 装修中
			if (!CollectionUtils.isEmpty(houseDecorateList)) {
				tempHouseList = houseDecorateList.stream().filter(
						house -> HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue().equals(house.getDecorateStage()))
						.collect(Collectors.toList());
				tempReport1 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport1.setHouseSubTypeName(ConstantType.装修中.getTitle());
			}
			// 已装修
			if (!CollectionUtils.isEmpty(houseDecorateList)) {
				tempHouseList = houseDecorateList.stream().filter(
						house -> HouseDecorateStageEnum.DECORATE_STAGE_DONE.getValue().equals(house.getDecorateStage()))
						.collect(Collectors.toList());
				tempReport2 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport2.setHouseSubTypeName(ConstantType.已装修.getTitle());
			}
			// 未装修
			if (!CollectionUtils.isEmpty(houseDecorateList)) {
				tempHouseList = houseDecorateList.stream().filter(
						house -> HouseDecorateStageEnum.DECORATE_STAGE_NONE.getValue().equals(house.getDecorateStage()))
						.collect(Collectors.toList());
				tempReport3 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport3.setHouseSubTypeName(ConstantType.未装修.getTitle());
			}
			this.statisticsRatioCount(tempReport1, tempReport2, tempReport3);
			map.put(ConstantType.装修中, tempReport1);
			map.put(ConstantType.已装修, tempReport2);
			map.put(ConstantType.未装修, tempReport3);
		}

	}

	// 出租
	private void houseChangeRentOperation(ReportFormVo vo, Map<ConstantType, ReportRes> map, List<Long> houseIdList,
			Date time) throws Exception {
		// 已出租
		ReportFormVo formVo = new ReportFormVo();
		formVo.setOrganizationId(vo.getOrganizationId());
		formVo.setRentStage(HouseRentStageEnum.RENT_STAGE_IN.getValue());
		formVo.setStartTime(time);
		List<HouseListEntity> houseRentList = reportFormService.findHouseResultInfo(houseIdList, formVo);
		List<HouseListEntity> tempHouseList = null;
		ReportRes tempReport1 = null;
		ReportRes tempReport2 = null;
		String rentStage = vo.getRentStage();
		if (!StringUtils.isEmpty(rentStage)) {
			if (ConstantType.出租.getValue().equals(rentStage) && !CollectionUtils.isEmpty(houseRentList)) {
				tempHouseList = houseRentList.stream()
						.filter(house -> HouseRentStageEnum.RENT_STAGE_IN.getValue().equals(house.getRentStage()))
						.collect(Collectors.toList());
				tempReport1 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport1.setHouseSubTypeName(ConstantType.出租.getTitle());
				map.put(ConstantType.出租, tempReport1);
			} else if (ConstantType.未租.getValue().equals(rentStage) && !CollectionUtils.isEmpty(houseRentList)) {
				tempHouseList = houseRentList.stream()
						.filter(house -> !HouseRentStageEnum.RENT_STAGE_IN.getValue().equals(house.getRentStage()))
						.collect(Collectors.toList());
				tempReport2 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport2.setHouseSubTypeName(ConstantType.未租.getTitle());
				map.put(ConstantType.未租, tempReport2);
			}
		} else {
			if (!CollectionUtils.isEmpty(houseRentList)) {
				tempHouseList = houseRentList.stream()
						.filter(house -> HouseRentStageEnum.RENT_STAGE_IN.getValue().equals(house.getRentStage()))
						.collect(Collectors.toList());
				tempReport1 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport1.setHouseSubTypeName(ConstantType.出租.getTitle());
			}
			// 未出租
			if (!CollectionUtils.isEmpty(houseRentList)) {
				tempHouseList = houseRentList.stream()
						.filter(house -> !HouseRentStageEnum.RENT_STAGE_IN.getValue().equals(house.getRentStage()))
						.collect(Collectors.toList());
				tempReport2 = this.statisticsHouseChageSubInfo(tempHouseList);
				tempReport2.setHouseSubTypeName(ConstantType.未租.getTitle());
			}
			this.statisticsRatioCount(tempReport1, tempReport2);
			map.put(ConstantType.出租, tempReport1);
			map.put(ConstantType.未租, tempReport2);
		}
	}

	/**
	 * 获取子房产（房产，车位，公共区域）
	 * 
	 * @param path
	 * @param houseId
	 * @return
	 * @throws Exception
	 */
	private List<OwnerHouseBaseInfo> getSubHouseInfo(List<OwnerHouseBaseInfo> list) throws Exception {
		// ReportFormVo vo = new ReportFormVo();
		// List<String> houseTypeList =
		// Arrays.asList(HouseTypeEnum.ROOM.getValue(),
		// HouseTypeEnum.CARPORT.getValue(), HouseTypeEnum.
		// PUBLICAREA.getValue());
		// vo.setHouseTypeList(houseTypeList);
		// vo.setPath(path+houseId);
		// vo.setOrganizationId(orgId);
		// List<OwnerHouseBaseInfo> baseList =
		// reportFormService.findBaseHouseInfo(vo);
		List<OwnerHouseBaseInfo> baseList = list.stream()
				.filter(baseInfo -> HouseTypeEnum.ROOM.getValue().equals(baseInfo.getHouseType())
						|| HouseTypeEnum.CARPORT.getValue().equals(baseInfo.getHouseType())
						|| HouseTypeEnum.PUBLICAREA.getValue().equals(baseInfo.getHouseType()))
				.collect(Collectors.toList());
		return baseList;
	}

	private String getOrganization(Long organizationId) {
		if (Objects.isNull(organizationId)) {
			return "";
		}
		String name = null;
		RestResult<Map<String, Object>> res = systemRemoteService.detailOrganization(organizationId);
		Map<String, Object> map = null;
		if (Objects.isNull(res)) {
			return "";
		}
		if (ResultCodeEnum.SUCCESS.CODE.equals(res.getResultCode())) {
			map = res.getResultData();
			LinkedHashMap<?, ?> companyMap = (LinkedHashMap<?, ?>) map.get("company");
			name = String.valueOf(companyMap.get("companyName"));
		}

		return name;
	}

	private NsCoreDictionaryVo getDictionary(String code, Long organizationId) {
		NsCoreDictionaryVo dictionaryVo = null;
		NsCoreDictionary dictionary = new NsCoreDictionary();
		dictionary.setDictionaryDdcode(code);
		dictionary.setOrganizationId(organizationId);
		RestResult<NsCoreDictionaryVo> dictionaryResult = systemRemoteService.getDictionary(dictionary);
		if (dictionaryResult != null) {
			dictionaryVo = dictionaryResult.getResultData();
		}
		return dictionaryVo;
	}
}
