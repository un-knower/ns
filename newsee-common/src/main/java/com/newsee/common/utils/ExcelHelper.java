package com.newsee.common.utils;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.util.CollectionUtils;

import com.newsee.common.entity.ExcelColMode;
import com.newsee.common.entity.ExcelExportRule;
import com.newsee.common.entity.ExcelFontFormat;
import com.newsee.common.entity.ExcelHeadCell;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.vo.NsCoreResourcecolumnVo;

import jxl.Workbook;
import jxl.format.Colour;
import jxl.format.UnderlineStyle;
import jxl.format.VerticalAlignment;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.JxlWriteException;
import jxl.write.biff.RowsExceededException;


/**
 * 
 *
 * 
 */
public class ExcelHelper {

	private static Log log = LogFactory.getLog(ExcelHelper.class);

	/**
	 * 实际需要展现的数据,支持DTO和Map
	 */
	private List<Object> rowDatas;

	private Set<Object> writed;

	/**
	 * 取数据及数据展现相关
	 */
	private List<ExcelColMode> colModes;

	/**
	 * 行头(横向排列),如果有父行头则按父行头的顺序，没有父行头的按List顺序排列
	 */
	private List<List<ExcelHeadCell>> headCols;

	/**
	 * 数据背景颜色区分,0:不区分,1:按行奇偶区分,2:按列奇偶区分
	 */
	private int distinguishable;

	/**
	 * 缓存展现内容的sheet页
	 */
	private WritableSheet sheet;

	/**
	 * 缓存单元格格式
	 */
	private Map<ExcelFontFormat, WritableCellFormat> mappedFormat;

	/**
	 * id字段名称,用于树形结构
	 */
	private String idName;

	/**
	 * 父id字段名称，用于树形结构
	 */
	private String pidName;

	private static String ONE_BLANK = " ";

	private int curDataRowIndex;
	private int curExcelRowIndex;

	public InputStream writeExcel(List<Object> rowDatas, ExcelExportRule rule,Integer[][] mergeCells) throws IOException, WriteException,
			SecurityException, IllegalArgumentException, NoSuchMethodException, IllegalAccessException,
			InvocationTargetException {
		if (rule != null) {
			this.rowDatas = rowDatas;
			this.colModes = rule.getColModes();
			this.headCols = rule.getHeadCols();
			this.distinguishable = rule.getDistinguishable();
			if (validate()) {
				ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
				WritableWorkbook workbook = Workbook.createWorkbook(outputStream);
				String sheetName = rule.getSheetName();
				if (StringUtils.isBlank(sheetName)) {
					sheetName = "sheet0";
				}
				sheet = workbook.createSheet(sheetName, 0);
				
				// 设置列宽
				for (int i = 0; i < colModes.size(); i++) {
					ExcelColMode colMode = colModes.get(i);
					if (colMode.getWidth() != null){
						sheet.setColumnView(i, colMode.getWidth());
					}else {
						//默认列宽列头长度+4
						sheet.setColumnView(i, this.headCols.get(0).get(i).getContent().getBytes().length+4);
					}
						
				}
				writeHeads();
				// 树形结构
				if ("1".equals(rule.getHierarchical())) {
					this.idName = rule.getIdName();
					this.pidName = rule.getPidName();
					writeTreeBody();
				}
				// 非树形结构
				else {
					writeBody();
				}
				if(mergeCells!=null){
					for(Integer[] mergeCell1:mergeCells){
						sheet.mergeCells(mergeCell1[0], mergeCell1[1], mergeCell1[2], mergeCell1[3]);
					}
				}
				workbook.write();
				workbook.close();
				return new ByteArrayInputStream(outputStream.toByteArray());
			}
		} else {
			log.error("ExcelExportRule为空，无法导出excel");
		}
		return null;
	}

	private boolean validate() {
		if (colModes == null || colModes.size() == 0) {
			log.error("读取数据的规则ExcelExportRule.colModes为空");
			return false;
		}
		return true;
	}

	private void writeHeads() throws JxlWriteException, WriteException {
		curExcelRowIndex = 0;
		if (headCols != null && !headCols.isEmpty()) {
			int s = headCols.size();
			if (s > 1) {
				caculaterHeadColSpans();
			}
			for (int i = 0; i < s; i++) {
				int tempColIndex = 0;
				List<ExcelHeadCell> headRowCols = headCols.get(i);
				for (int j = 0; j < headRowCols.size(); j++) {
					ExcelHeadCell headCol = headRowCols.get(j);
					writeHeadCell(headCol, tempColIndex);
					tempColIndex += headCol.getColSpan();
				}
				curExcelRowIndex++;
			}
		}
	}

	// 计算标题需要列数
	private void caculaterHeadColSpans() {
		int s = headCols.size();
		for (int i = s - 1; i > 0; i--) {
			List<ExcelHeadCell> subCols = headCols.get(i);
			Collections.sort(subCols);
			List<ExcelHeadCell> supCols = headCols.get(i - 1);
			int[] fatherColSpans = new int[supCols.size()];
			for (ExcelHeadCell subCol : subCols) {
				int fi = subCol.getFatherIndex();
				fatherColSpans[fi] += subCol.getColSpan();
			}
			for (int j = 0; j < supCols.size(); j++) {
				ExcelHeadCell supCol = supCols.get(j);
				if (fatherColSpans[j] > 0) {
					supCol.setColSpan(fatherColSpans[j]);
				}
			}
		}
	}

	private void writeHeadCell(ExcelHeadCell headCol, int colIndex) throws JxlWriteException, WriteException {
		ExcelFontFormat eff = headCol.getFontFormat();
		String content = headCol.getContent();
		int colspan = headCol.getColSpan();
		if (headCol.getHeight() != null)
			sheet.setRowView(curExcelRowIndex, headCol.getHeight(), false);
		writeCell(content, eff, colIndex, colspan);
	}

	private void writeCell(String content, ExcelFontFormat eff, int colIndex, int colspan) throws JxlWriteException,
			WriteException {
		if (eff != null) {
			WritableCellFormat wcf = getCellFormat(eff);
			sheet.addCell(new Label(colIndex, curExcelRowIndex, content, wcf));
		} else {
			sheet.addCell(new Label(colIndex, curExcelRowIndex, content));
		}
		if (colspan > 1) {
			sheet.mergeCells(colIndex, curExcelRowIndex, colIndex + colspan - 1, curExcelRowIndex);
		}
	}

	/**
	 * 从缓存中取格式化的字体,没有则新建并缓存,生成EXCELL完成后需要清除缓存的字体
	 * 
	 * @param eff
	 * @return
	 * @throws WriteException
	 */
	private WritableCellFormat getCellFormat(ExcelFontFormat eff) throws WriteException {
		WritableCellFormat wcf = null;
		if (mappedFormat == null) {
			mappedFormat = new HashMap<ExcelFontFormat, WritableCellFormat>();
		} else {
			wcf = mappedFormat.get(eff);
		}
		if (wcf == null) {
			FontName fn = eff.convertFontName();
			WritableFont wf = new WritableFont(fn, eff.convertFontSize(), eff.isBold() ? WritableFont.BOLD
					: WritableFont.NO_BOLD, eff.isItalic(), UnderlineStyle.NO_UNDERLINE, eff.getColor());
			wcf = new WritableCellFormat(wf);
			wcf.setBackground(eff.getBackgroundColor());
			wcf.setAlignment(eff.convertFlow());
			wcf.setBorder(jxl.format.Border.ALL, jxl.format.BorderLineStyle.THIN, jxl.format.Colour.BLACK);
			if (eff.getVerticalAlign() == 0)
				wcf.setVerticalAlignment(VerticalAlignment.TOP);
			else if (eff.getVerticalAlign() == 1)
				wcf.setVerticalAlignment(VerticalAlignment.CENTRE);
			else if (eff.getVerticalAlign() == 2)
				wcf.setVerticalAlignment(VerticalAlignment.BOTTOM);
			mappedFormat.put(eff, wcf);
		}
		return wcf;
	}

	private void writeTreeBody() throws RowsExceededException, WriteException, SecurityException,
			IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (rowDatas != null && rowDatas.size() > 0) {
			curDataRowIndex = 1;
			Object fo = rowDatas.get(0);
			boolean isMap = fo instanceof Map;
			if (isMap) {
				writeTreeDatas4Map();
			} else {
				writeTreeDatas4Dto();
			}
		}
	}

	private void writeTreeDatas4Map() throws JxlWriteException, WriteException {
		if (writed == null) {
			writed = new HashSet<Object>();
		}
		for (Object data : rowDatas) {
			if (!writed.contains(data)) {
				Map m = (Map) data;
				Object pid = m.get(pidName);
				if (pid == null) {
					writeRow4Map(data);
					curDataRowIndex++;
					curExcelRowIndex++;
					writed.add(data);
					writeSubDatas4Map(m, 1);
				} else {
					if (pid instanceof String) {
						String ps = (String) pid;
						if (StringUtils.isBlank(ps) || (Integer.valueOf(ps) <= 0)) {
							writeRow4Map(data);
							curDataRowIndex++;
							curExcelRowIndex++;
							writed.add(data);
							writeSubDatas4Map(m, 1);
						}
					} else if (pid instanceof Integer) {
						Integer pi = (Integer) pid;
						if (pi <= 0) {
							writeRow4Map(data);
							curDataRowIndex++;
							curExcelRowIndex++;
							writed.add(data);
							writeSubDatas4Map(m, 1);
						}
					} else if (pid instanceof Long) {
						Long pl = (Long) pid;
						if (pl.compareTo(0L) <= 0) {
							writeRow4Map(data);
							curDataRowIndex++;
							curExcelRowIndex++;
							writed.add(data);
							writeSubDatas4Map(m, 1);
						}
					} else if (pid instanceof BigDecimal) {
						if (((BigDecimal) pid).compareTo(BigDecimal.ZERO) <= 0) {
							writeRow4Map(data);
							curDataRowIndex++;
							curExcelRowIndex++;
							writed.add(data);
							writeSubDatas4Map(m, 1);
						}
					}
				}
			}
		}
	}

	private void writeSubDatas4Map(Map father, int deep) throws RowsExceededException, WriteException {
		for (Object data : rowDatas) {
			if (!writed.contains(data)) {
				Map m = (Map) data;
				Object pid = m.get(pidName);
				Object fid = father.get(idName);
				if (pid != null) {
					if (pid instanceof Long) {
						Long pl = (Long) pid;
						Long fl = null;
						try {
							fl = (Long) fid;
						} catch (Exception e) {
							if (fid instanceof BigDecimal) {
								fl = ((BigDecimal) fid).longValue();
							}
						}
						if (pl.equals(fl)) {
							writeSubRow4Map(m, deep);
							curDataRowIndex++;
							curExcelRowIndex++;
							int subDeep = deep + 1;
							writed.add(data);
							writeSubDatas4Map(m, subDeep);
						}
					} else if (pid instanceof String) {
						String ps = (String) pid;
						String fs = null;
						try {
							fs = (String) fid;
						} catch (Exception e) {
							if (fid instanceof BigDecimal) {
								fs = ((BigDecimal) fid).toString();
							}
						}
						if (ps.equals(fs)) {
							writeSubRow4Map(m, deep);
							curDataRowIndex++;
							curExcelRowIndex++;
							int subDeep = deep + 1;
							writed.add(data);
							writeSubDatas4Map(m, subDeep);
						}
					} else if (pid instanceof Integer) {
						Integer pi = (Integer) pid;
						Integer fi = null;
						try {
							fi = (Integer) fid;
						} catch (Exception e) {
							if (fid instanceof BigDecimal) {
								fi = ((BigDecimal) fid).intValue();
							}
						}
						if (pi.equals(fi)) {
							writeSubRow4Map(m, deep);
							curDataRowIndex++;
							curExcelRowIndex++;
							int subDeep = deep + 1;
							writed.add(data);
							writeSubDatas4Map(m, subDeep);
						}
					}
				}
			}
		}
	}

	private void writeSubRow4Map(Map subMap, int deep) throws RowsExceededException, WriteException {
		int tempColIndex = 0;
		for (ExcelColMode mode : colModes) {
			Object o = subMap.get(mode.getName());
			String content = null;
			if (o == null)
				content = "";
			else {
				if (mode.getContentFormatter() != null)
					content = mode.getContentFormatter().format(o);
				else
					content = o.toString();
			}
			if (tempColIndex == 0) {
				int blankCount = 6 * deep;
				for (int i = 0; i < blankCount; i++) {
					content = ONE_BLANK + content;
				}
			}
			writeContent(content, mode, tempColIndex);
			tempColIndex++;
		}
	}

	private void writeTreeDatas4Dto() throws SecurityException, IllegalArgumentException, NoSuchMethodException,
			IllegalAccessException, InvocationTargetException, JxlWriteException, WriteException {
		if (writed == null) {
			writed = new HashSet<Object>();
		}
		for (Object data : rowDatas) {
			if (!writed.contains(data)) {
				Object pid = getValue(data, pidName);
				if (pid == null) {
					writeRow4Dto(data);
					curDataRowIndex++;
					curExcelRowIndex++;
					writed.add(data);
					writeSubDatas4Dto(data, 1);
				} else {
					if (pid instanceof String) {
						String ps = (String) pid;
						if (StringUtils.isBlank(ps) || (Integer.valueOf(ps)) <= 0) {
							writeRow4Dto(data);
							curDataRowIndex++;
							curExcelRowIndex++;
							writed.add(data);
							writeSubDatas4Dto(data, 1);
						}
					} else if (pid instanceof Integer) {
						Integer pi = (Integer) pid;
						if (pi <= 0) {
							writeRow4Dto(data);
							curDataRowIndex++;
							curExcelRowIndex++;
							writed.add(data);
							writeSubDatas4Dto(data, 1);
						}
					} else if (pid instanceof Long) {
						Long pl = (Long) pid;
						if (pl.compareTo(0L) <= 0) {
							writeRow4Dto(data);
							curDataRowIndex++;
							curExcelRowIndex++;
							writed.add(data);
							writeSubDatas4Dto(data, 1);
						}
					}
				}
			}
		}
	}

	private void writeSubDatas4Dto(Object father, int deep) throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException, InvocationTargetException, RowsExceededException,
			WriteException {
		for (Object data : rowDatas) {
			if (!writed.contains(data)) {
				Object pid = getValue(data, pidName);
				Object fid = getValue(father, idName);
				if (pid != null) {
					if (pid instanceof Long) {
						Long pl = (Long) pid;
						Long fl = (Long) fid;
						if (pl.equals(fl)) {
							writeSubRow4Dto(data, deep);
							curDataRowIndex++;
							curExcelRowIndex++;
							int subDeep = deep + 1;
							writed.add(data);
							writeSubDatas4Dto(data, subDeep);
						}
					} else if (pid instanceof String) {
						String ps = (String) pid;
						String fs = (String) fid;
						if (ps.equals(fs)) {
							writeSubRow4Dto(data, deep);
							curDataRowIndex++;
							curExcelRowIndex++;
							int subDeep = deep + 1;
							writed.add(data);
							writeSubDatas4Dto(data, subDeep);
						}
					} else if (pid instanceof Integer) {
						Integer pi = (Integer) pid;
						Integer fi = (Integer) fid;
						if (pi.equals(fi)) {
							writeSubRow4Dto(data, deep);
							curDataRowIndex++;
							curExcelRowIndex++;
							int subDeep = deep + 1;
							writed.add(data);
							writeSubDatas4Dto(data, subDeep);
						}
					}
				}
			}
		}
	}

	private void writeSubRow4Dto(Object subData, int deep) throws SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException, InvocationTargetException, RowsExceededException,
			WriteException {
		int tempColIndex = 0;
		for (ExcelColMode mode : colModes) {
			String field = mode.getName();
			Object o = getValue(subData, field);
			String content = null;
			if (o == null)
				content = "";
			else {
				if (mode.getContentFormatter() != null)
					content = mode.getContentFormatter().format(o);
				else
					content = o.toString();
			}
			if (tempColIndex == 0) {
				int blankCount = 6 * deep;
				for (int i = 0; i < blankCount; i++) {
					content = ONE_BLANK + content;
				}
			}
			writeContent(content, mode, tempColIndex);
		}
	}

	private void writeBody() throws RowsExceededException, WriteException, SecurityException, IllegalArgumentException,
			NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		if (rowDatas != null && rowDatas.size() > 0) {
			curDataRowIndex = 1;
			Object fo = rowDatas.get(0);
			if (fo instanceof Map) {
				writeDatas4Map();
			} else {
				writeDatas4Dto();
			}
		}
	}

	private void writeDatas4Map() throws JxlWriteException, WriteException {
		for (Object data : rowDatas) {
			writeRow4Map(data);
			curDataRowIndex++;
			curExcelRowIndex++;
		}
	}

	private void writeDatas4Dto() throws JxlWriteException, WriteException, SecurityException,
			IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		for (Object data : rowDatas) {
			writeRow4Dto(data);
			curDataRowIndex++;
			curExcelRowIndex++;
		}
	}

	private void writeRow4Map(Object data) throws JxlWriteException, WriteException {
		Map m = (Map) data;
		int tempColIndex = 0;
		for (ExcelColMode mode : colModes) {
			Object o = m.get(mode.getName());
			String content = null;
			if (o == null)
				content = "";
			else {
				if (mode.getContentFormatter() != null)
					content = mode.getContentFormatter().format(o);
				else
					content = o.toString();
			}
			writeContent(content, mode, tempColIndex);
			tempColIndex++;
		}
	}

	private void writeContent(String content, ExcelColMode mode, int colIndex) throws RowsExceededException,
			WriteException {
		ExcelFontFormat eff = mode.getFontFormat();
		if (distinguishable == 1) {
			if (eff == null) {
				eff = new ExcelFontFormat();
			}
			if (curDataRowIndex % 2 == 1) {
				eff.setBackgroundColor(Colour.WHITE);
			} else {
				eff.setBackgroundColor(Colour.GRAY_25);
			}
		} else if (distinguishable == 2) {
			if (eff == null) {
				eff = new ExcelFontFormat();
			}
			if (colIndex % 2 == 1) {
				eff.setBackgroundColor(Colour.WHITE);
			} else {
				eff.setBackgroundColor(Colour.GRAY_25);
			}
		}
		writeCell(content, eff, colIndex, 1);
	}

	private void writeRow4Dto(Object data) throws JxlWriteException, WriteException, SecurityException,
			IllegalArgumentException, NoSuchMethodException, IllegalAccessException, InvocationTargetException {
		int tempColIndex = 0;
		for (ExcelColMode mode : colModes) {
			String field = mode.getName();
			Object o = getValue(data, field);
			String content = null;
			if (o == null)
				content = "";
			else {
				if (mode.getContentFormatter() != null)
					content = mode.getContentFormatter().format(o);
				else
					content = o.toString();
			}
			writeContent(content, mode, tempColIndex);
			tempColIndex++;
		}
	}

	private Object getValue(Object data, String feild) throws SecurityException, NoSuchMethodException,
			IllegalArgumentException, IllegalAccessException, InvocationTargetException {
		if (feild.contains(".")) {
			String fileds[] = feild.split("\\.");
			Object value = null;
			String methodName = "get" + fileds[0].substring(0, 1).toUpperCase() + fileds[0].substring(1);
			try {
				Method getMethod = data.getClass().getMethod(methodName);
				value = getMethod.invoke(data);
			} catch (NoSuchMethodException e) {
				try {
					methodName = "get" + feild.substring(0, 1).toLowerCase() + feild.substring(1);
					Method getMethod = data.getClass().getMethod(methodName);
					value = getMethod.invoke(data);
				} catch (Exception e2) {
                    BizException.fail(ResultCodeEnum.SERVER_ERROR, ResultCodeEnum.SERVER_ERROR.DESC);
				}
			}

			Object value2 = null;
			String methodName2 = "get" + fileds[1].substring(0, 1).toUpperCase() + fileds[1].substring(1);
			try {
				Method getMethod = value.getClass().getMethod(methodName2);
				value2 = getMethod.invoke(value);
			} catch (NoSuchMethodException e) {
				try {
					methodName = "get" + fileds[1].substring(0, 1).toLowerCase() + fileds[1].substring(1);
					Method getMethod = data.getClass().getMethod(methodName);
					value = getMethod.invoke(value);
				} catch (Exception e2) {
					BizException.fail(ResultCodeEnum.SERVER_ERROR, ResultCodeEnum.SERVER_ERROR.DESC);
				}
			}
			return value2;
		} else {
			Object value = null;
			String methodName = "get" + feild.substring(0, 1).toUpperCase() + feild.substring(1);
			try {
				Method getMethod = data.getClass().getMethod(methodName);
				value = getMethod.invoke(data);
			} catch (NoSuchMethodException e) {
				try {
					methodName = "get" + feild.substring(0, 1).toLowerCase() + feild.substring(1);
					Method getMethod = data.getClass().getMethod(methodName);
					value = getMethod.invoke(data);
				} catch (Exception e2) {
                    BizException.fail(ResultCodeEnum.SERVER_ERROR, ResultCodeEnum.SERVER_ERROR.DESC);
				}
			}
			return value;
		}
	}

	/**
	 * 释放资源
	 */
	public void clear() {
		if (writed != null) {
			writed = null;
		}
		if (rowDatas != null) {
			rowDatas = null;
		}
		if (colModes != null) {
			colModes = null;
		}
		if (headCols != null) {
			headCols = null;
		}
		if (sheet != null) {
			sheet = null;
		}
		if (mappedFormat != null) {
			mappedFormat = null;
		}
		if (idName != null) {
			idName = null;
		}
		if (pidName != null) {
			pidName = null;
		}
	}

	private static boolean isContainStyle(String style, String s1, String s2) {
		String styleArr[] = style.split(";");
		for (String s : styleArr) {
			if (s.contains(s1) && s.contains(s2))
				return true;
		}
		return false;
	}

	private static void writeSheetData(Integer startRow[], int level, Map<String, String> map, List<String> ridAry,
			List<Map<String, String>> allData, WritableSheet sheet) throws RowsExceededException, WriteException {
		String tab = "";
		for (int i = 0; i < level - 1; i++) {
			tab += "   ";
		}
		sheet.addCell(new Label(0, startRow[0], tab + map.get("index")));
		for (int i = 0; i < ridAry.size(); i++) {
			sheet.addCell(new Label(i + 1, startRow[0], map.get("R" + ridAry.get(i))));
		}
		startRow[0]++;
		List<Map<String, String>> children = getChildren(allData, map.get("id"));
		if (children != null && children.size() > 0) {
			for (Map<String, String> child : children) {
				writeSheetData(startRow, level + 1, child, ridAry, allData, sheet);
			}
		}
	}

	private static List<Map<String, String>> getFirstLeve(List<Map<String, String>> list) {
		List<Map<String, String>> firstLevel = new ArrayList<Map<String, String>>();
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				if (map.get("parentId") == null || map.get("parentId").length() == 0)
					firstLevel.add(map);
			}
		}
		return firstLevel;
	}

	private static List<Map<String, String>> getChildren(List<Map<String, String>> list, String id) {
		List<Map<String, String>> children = new ArrayList<Map<String, String>>();
		if (list != null && list.size() > 0) {
			for (Map<String, String> map : list) {
				if (map.get("parentId") != null && map.get("parentId").toString().equals(id))
					children.add(map);
			}
		}
		return children;
	}

	private static String getIndexData(String code, String rid, Map<String, List<HashMap<String, Object>>> tabDataMap) {
		if (code == null || code.length() == 0)
			return "";
		if (tabDataMap == null || tabDataMap.size() == 0)
			return "0";
		String codeArr[] = code.split("\\.");
		if (codeArr.length != 2)
			return "";
		List<HashMap<String, Object>> listMap = tabDataMap.get(codeArr[0]);
		if (listMap == null || listMap.size() == 0)
			return "0";
		else {
			for (int i = 0; i < listMap.size(); i++) {
				HashMap<String, Object> map = listMap.get(i);
				if (map.get("P_R_ID").toString().equals(rid)) {
					Object obj = map.get(codeArr[1]);
					if (obj != null)
						return obj.toString();
					else
						return "0";
				}
			}
			return "";
		}
	}

	private static String getUnit(String code, Map<String, String> tabUnitMap) {
		String result = "";
		if (code != null) {
			String codeArr[] = code.split("\\.");
			if (codeArr.length == 2) {
				if (tabUnitMap.get(codeArr[0]) != null)
					result = tabUnitMap.get(codeArr[0]);
			}
		}
		return result;
	}
	public static <T> void exportExcel(List<T> resultList,List<NsCoreResourcecolumnVo> columnList,String sheetName, HttpServletResponse response) throws Exception{
	    List<Object> rowDatas = new ArrayList<Object>();
        if (!CollectionUtils.isEmpty(resultList) &&
                !CollectionUtils.isEmpty(columnList)) {
            for (Object object : resultList) {
                rowDatas.add(object);
            }
            //EXCEL工具类
            ExcelHelper excelHelper = new ExcelHelper();
            //列头填充
            List<ExcelHeadCell> headCells = new ArrayList<ExcelHeadCell>();
            ExcelFontFormat format = new ExcelFontFormat();
            format.setFontSize(0);
            for (NsCoreResourcecolumnVo columnVo : columnList) {
                headCells.add(new ExcelHeadCell(columnVo.getResourcecolumnName(),0,format));
            }
            List<List<ExcelHeadCell>> headCellsList = new ArrayList<List<ExcelHeadCell>>();
            headCellsList.add(headCells);
            ExcelExportRule rule = new ExcelExportRule();
            rule.setSheetName(sheetName);
            rule.setHeadCols(headCellsList);
            rule.setDistinguishable(1);
            List<ExcelColMode> colModes = new ArrayList<ExcelColMode>();
            //每列属性名
            format.setFontSize(-1);
            for (NsCoreResourcecolumnVo columnVo : columnList) {
                colModes.add(new ExcelColMode(columnVo.getResourcecolumnNameEn(),format));
            }
            rule.setColModes(colModes);
            InputStream inputStream = excelHelper.writeExcel(rowDatas, rule,null);
            String excelName = sheetName + DateUtils.getUserDate(DateUtils.YYYYMMDD_CROSS);
            
//            String realFilename = URLEncoder.encode(excelName==null?"excel":excelName, "UTF-8");
            String realFilename = new String(excelName.getBytes("UTF-8"), "ISO8859-1");
            byte b[] = new byte[512];
            // 清空response
            response.reset();
            // 设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename=" +realFilename+ ".xls");
            response.addHeader("Content-Length", "" + inputStream.available());
            OutputStream toClient = new BufferedOutputStream(
                    response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=UTF-8");
            int i = inputStream.read(b);
            while (i != -1) {
                toClient.write(b);
                i = inputStream.read(b);
            }
            toClient.flush();
            toClient.close();
            inputStream.close();
        }
	}
}
