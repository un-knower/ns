package com.newsee.owner.utils;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;

public class ExcelUtils {
	//HSSFWorkbook对象
	private HSSFWorkbook wb;
	private HSSFSheet sheet;
	private HSSFCellStyle cellStyle;
	
	private int maxColumnSize;
	
	public ExcelUtils() {
		//创建HSSFWorkbook对象
		 wb = new HSSFWorkbook();
	}
	
	/**
	 * 单元格表头样式
	 */
	public HSSFCellStyle getHeaderStyle() {
		//单元格样式
		HSSFCellStyle cellStyle=wb.createCellStyle();
		//设置字体
		HSSFFont  fontStyle=wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short)11);
		fontStyle.setColor(HSSFColor.BLACK.index);
		fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		fontStyle.setItalic(false);
		cellStyle.setFont(fontStyle);
		// 设置单元格的横向和纵向对齐方式，具体参数就不列了，参考HSSFCellStyle
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);		 
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return cellStyle;
	}
	
	/**
	 * 设置单元格样式
	 */
	public HSSFCellStyle getStyle() {
		//单元格样式
		HSSFCellStyle cellStyle=wb.createCellStyle();
		//设置字体
		HSSFFont  fontStyle=wb.createFont();
		fontStyle.setFontName("宋体");
		fontStyle.setFontHeightInPoints((short)11);
		fontStyle.setColor(HSSFColor.BLACK.index);
		fontStyle.setItalic(false);
		cellStyle.setFont(fontStyle);
		// 设置单元格的横向和纵向对齐方式，具体参数就不列了，参考HSSFCellStyle
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);		 
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		return cellStyle;
	}
	
	/**
	 * 创建Excel对象
	 * @param sheetName 
	 * @param cellStyle 样式
	 * @return
	 */
	public ExcelUtils createExcel(String sheetName, HSSFCellStyle cellStyle) {
		//创建HSSFSheet对象
		 sheet = wb.createSheet(sheetName);
		//设置缺省列高
		sheet.setDefaultRowHeightInPoints(15); 
		//设置缺省列宽
		sheet.setDefaultColumnWidth(15);
		if (cellStyle == null) {
			this.cellStyle = getStyle();
		} else {
			this.cellStyle = cellStyle;
		}
		return new ExcelUtils();
	}
	
	/**
	 * 创建表头
	 * @param headerList
	 */
	public void createHeader(List<List<ExcelCell>> headerList) {
		if (headerList == null || headerList.size() == 0) {
			throw new NullPointerException("Excel 表头为NULL");
		}
		HSSFRow row = null; //在sheet里创建一行，参数为行索引(excel的行)，可以是0～65535之间的任何一个
		int totalRow = sheet.getPhysicalNumberOfRows();
		for (int i = totalRow; i < totalRow+headerList.size(); i++) {
			//创建HSSFRow对象
			row = sheet.createRow(i);
			//添加样式
			row.setRowStyle(cellStyle);
			if (row.getPhysicalNumberOfCells() > maxColumnSize) {
				maxColumnSize = row.getPhysicalNumberOfCells();
			}
			//得到列
			this.col_operation(headerList.get(i-totalRow), row);
		}
	}
	
	/**
	 * 添加数据
	 * @param headerList
	 */
	public void addData(List<List<ExcelCell>> headerList, HSSFCellStyle style) {
		this.cellStyle = style;
		this.createHeader(headerList);
	}
	
	/**
	 * 列操作
	 * @param colList
	 * @param row
	 */
	private void col_operation(List<ExcelCell> colList, HSSFRow row) { 
		HSSFCell cell = null;
		ExcelCell cellParam = null;
		for (int i = 0; i < colList.size(); i++) {
			cellParam = colList.get(i);
			//创建列
			if (cellParam.getColIndex() == null) {
				cell = row.createCell(i);
			} else {
				cell = row.createCell(cellParam.getColIndex());
			}
			//设置单元格样式
			cell.setCellStyle(cellStyle);
			//设置单元格内容
			if (cellParam.getValueStr() != null) {
				cell.setCellValue(cellParam.getValueStr());
			} else if (cellParam.getValueDou() != null) {
				cell.setCellValue(cellParam.getValueDou());
			} else if (cellParam.getValueDate() != null) {
				cell.setCellValue(cellParam.getValueDate());
			} else if (cellParam.isValueBool() != null) {
				cell.setCellValue(cellParam.isValueBool());
			}
			//合并单元格CellRangeAddress构造参数依次表示起始行，截至行，起始列， 截至列
			if (cellParam.isMergeCell()) {
				sheet.addMergedRegion(new CellRangeAddress(cellParam.getFirstRow(), cellParam.getLastRow(), cellParam.getFirstCol(), cellParam.getLastCol()));
			}
		}
	}

	public HSSFWorkbook getWb() {
		return wb;
	}

	public HSSFSheet getSheet() {
		return sheet;
	}

	public int getMaxColumnSize() {
		return maxColumnSize;
	}
	
}
