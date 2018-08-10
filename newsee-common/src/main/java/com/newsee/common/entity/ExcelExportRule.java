package com.newsee.common.entity;

import java.util.ArrayList;
import java.util.List;

public class ExcelExportRule {

	/**
	 * 封装如何从数据集取数据，数据显示格式，日期格式和数字格式在这里设置
	 */
	private List<ExcelColMode> colModes;

	/**
	 * 封装EXCEL头部内容及内容显示格式
	 */
	private List<List<ExcelHeadCell>> headCols;

	/**
	 * 数据背景颜色区分,0:不区分,1:按行奇偶区分,奇数行白色，偶数行灰色，2:按列奇偶区分 奇数列白色，偶数列灰色， <br/>
	 * <b>注意：此参数为0时，单元格设置的背景色才起作用</b>
	 */
	private int distinguishable = 0;

	/**
	 * EXCEL的sheet页名称
	 */
	private String sheetName;

	/**
	 * 是否树形结构，1：是，0：否
	 */
	private String hierarchical = "0";

	/**
	 * id字段名，当hierarchical="1"时候才起作用
	 */
	private String idName;

	/**
	 * 父id字段名，当hierarchical="1"时候才起作用
	 */
	private String pidName;

	public List<ExcelColMode> getColModes() {
		return colModes;
	}

	public void setColModes(List<ExcelColMode> colModes) {
		this.colModes = colModes;
	}

	public List<List<ExcelHeadCell>> getHeadCols() {
		return headCols;
	}

	public void setHeadCols(List<List<ExcelHeadCell>> headCols) {
		this.headCols = headCols;
	}

	public int getDistinguishable() {
		return distinguishable;
	}

	public void setDistinguishable(int distinguishable) {
		this.distinguishable = distinguishable;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getHierarchical() {
		return hierarchical;
	}

	public void setHierarchical(String hierarchical) {
		this.hierarchical = hierarchical;
	}

	public String getIdName() {
		return idName;
	}

	public void setIdName(String idName) {
		this.idName = idName;
	}

	public String getPidName() {
		return pidName;
	}

	public void setPidName(String pidName) {
		this.pidName = pidName;
	}

	public void addExcelColMode(ExcelColMode excelColMode) {
		if (colModes == null)
			colModes = new ArrayList<ExcelColMode>();
		colModes.add(excelColMode);
	}

	public void addExcelHeadCellList(List<ExcelHeadCell> list) {
		if (headCols == null)
			headCols = new ArrayList<List<ExcelHeadCell>>();
		headCols.add(list);
	}

}
