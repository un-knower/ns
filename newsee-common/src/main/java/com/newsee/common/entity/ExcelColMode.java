package com.newsee.common.entity;

import com.newsee.common.inter.ExcelColModelFormatterInter;

public class ExcelColMode {

	/**
	 * Map中的key或者dto中实现get方法的字段名
	 */
	private String name;

	/** 列宽 */
	private Integer width;

	/**
	 * 字体格式,可以设置字体大小，字体颜色，字体加粗
	 */
	private ExcelFontFormat fontFormat;

	/**
	 * 内容格式化
	 */
	private ExcelColModelFormatterInter contentFormatter;

	public ExcelColMode(String name) {
		this.name = name;
	}
	
	public ExcelColMode(String name,ExcelFontFormat fontFormat) {
		this.name = name;
		this.fontFormat = fontFormat;
	}
	
	public ExcelColMode(String name,ExcelFontFormat fontFormat, Integer width) {
		this.name = name;
		this.fontFormat = fontFormat;
		this.width = width;
	}

	public ExcelColMode(String name, Integer width) {
		this.name = name;
		this.width = width;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ExcelFontFormat getFontFormat() {
		return fontFormat;
	}

	public void setFontFormat(ExcelFontFormat fontFormat) {
		this.fontFormat = fontFormat;
	}

	public ExcelColModelFormatterInter getContentFormatter() {
		return contentFormatter;
	}

	public void setContentFormatter(ExcelColModelFormatterInter contentFormatter) {
		this.contentFormatter = contentFormatter;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

}
