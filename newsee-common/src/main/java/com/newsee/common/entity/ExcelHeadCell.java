package com.newsee.common.entity;

import org.apache.commons.lang3.builder.CompareToBuilder;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ExcelHeadCell implements Comparable<ExcelHeadCell> {

	/**
	 * 列合并
	 */
	private int colSpan;
	
	/**
	 * 行合并
	 */
	private int rowSpan;

	/**
	 * 展现字符内容
	 */
	private String content;

	/**
	 * 父列的序列号
	 */
	private int fatherIndex;

	/**
	 * 字体格式等
	 */
	private ExcelFontFormat fontFormat;

	private Integer height;

	/**
	 * 最基础的单元格,没有行合并和列合并
	 * 
	 * @param content
	 */
	public ExcelHeadCell(String content) {
		this.colSpan = 1;
		this.content = content;
	}

	public ExcelHeadCell(String content, Integer height) {
		this.colSpan = 1;
		this.content = content;
		this.height = height;
	}

	public ExcelHeadCell(String content, int fatherIndex, ExcelFontFormat fontFormat) {
		this.colSpan = 1;
		this.content = content;
		this.fatherIndex = fatherIndex;
		this.fontFormat = fontFormat;
	}

	public int getColSpan() {
		return colSpan;
	}

	public void setColSpan(int colSpan) {
		this.colSpan = colSpan;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ExcelFontFormat getFontFormat() {
		return fontFormat;
	}

	public void setFontFormat(ExcelFontFormat fontFormat) {
		this.fontFormat = fontFormat;
	}

	public int getFatherIndex() {
		return fatherIndex;
	}

	public void setFatherIndex(int fatherIndex) {
		this.fatherIndex = fatherIndex;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public int getRowSpan() {
		return rowSpan;
	}

	public void setRowSpan(int rowSpan) {
		this.rowSpan = rowSpan;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public int compareTo(ExcelHeadCell o) {
		return CompareToBuilder.reflectionCompare(this, o);
	}
	
}
