package com.newsee.owner.utils;

import java.util.Date;

public class ExcelCell {
	private int firstRow = 0;
	private int lastRow = 0;
	private int firstCol = 0;
	private int lastCol = 0;
	private Integer colIndex;
	private boolean isMergeCell;
	private String valueStr;
	private Boolean valueBool;
	private Date valueDate;
	private Double valueDou;
	
	/**
	 * colValue 列的值
	 * @param colValue
	 */
	public ExcelCell(Object colValue) {
		getValue(colValue);
	}
	
	public ExcelCell(Object colValue,  int colIndex) {
		this.colIndex = colIndex;
		getValue(colValue);
	}

	/**
	 * 单元格元素
	 * @param colValue 列的值
	 * @param firstRow  合并单元格的起始行
	 * @param lastRow
	 * @param firstCol  合并单元格的起始列
	 * @param lastCol
	 */
	public ExcelCell(Object colValue, int colIndex, boolean isMergeCell, int firstRow, int lastRow, int firstCol, int lastCol) {
		this.isMergeCell = isMergeCell;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCol = firstCol;
		this.lastCol = lastCol;
		this.colIndex = colIndex;
		getValue(colValue);
	}
	
	public ExcelCell(Object colValue, boolean isMergeCell, int firstRow, int lastRow, int firstCol, int lastCol) {
		this.isMergeCell = isMergeCell;
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCol = firstCol;
		this.lastCol = lastCol;
		getValue(colValue);
	}
	
	public ExcelCell(Object colValue, int colIndex, int firstRow, int lastRow, int firstCol, int lastCol) {
		this.firstRow = firstRow;
		this.lastRow = lastRow;
		this.firstCol = firstCol;
		this.lastCol = lastCol;
		this.colIndex = colIndex;
		getValue(colValue);
	}
	
	private void getValue(Object value) {
		if (value == null) 
			return;
		if (value instanceof String) {
			valueStr = (String) value;
		} else if (value instanceof Boolean) {
			valueBool = (boolean) value; 
		} else if (value instanceof Number) {
			valueDou = Double.parseDouble(String.valueOf(value));
		} else if (value instanceof Date) {
			valueDate = (Date) value; 
		} else {
			valueStr = String.valueOf(value);
		}
	}
	
	public Integer getColIndex() {
		return colIndex;
	}

	public String getValueStr() {
		return valueStr;
	}

	public void setValueStr(String valueStr) {
		this.valueStr = valueStr;
	}

	public Boolean isValueBool() {
		return valueBool;
	}

	public void setValueBool(Boolean valueBool) {
		this.valueBool = valueBool;
	}

	public Date getValueDate() {
		return valueDate;
	}

	public void setValueDate(Date valueDate) {
		this.valueDate = valueDate;
	}

	public Double getValueDou() {
		return valueDou;
	}

	public void setValueDou(Double valueDou) {
		this.valueDou = valueDou;
	}

	public boolean isMergeCell() {
		return isMergeCell;
	}
	public void setMergeCell(boolean isMergeCell) {
		this.isMergeCell = isMergeCell;
	}
	public int getFirstRow() {
		return firstRow;
	}
	public void setFirstRow(int firstRow) {
		this.firstRow = firstRow;
	}
	public int getLastRow() {
		return lastRow;
	}
	public void setLastRow(int lastRow) {
		this.lastRow = lastRow;
	}
	public int getFirstCol() {
		return firstCol;
	}
	public void setFirstCol(int firstCol) {
		this.firstCol = firstCol;
	}
	public int getLastCol() {
		return lastCol;
	}
	public void setLastCol(int lastCol) {
		this.lastCol = lastCol;
	}
	
}
