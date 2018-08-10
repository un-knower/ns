package com.newsee.common.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import jxl.format.Alignment;
import jxl.format.Colour;
import jxl.write.WritableFont;
import jxl.write.WritableFont.FontName;

public class ExcelFontFormat {

	private int font = 0; // 字体 0:宋体,1:楷体,2:黑体，3：仿宋体，4:隶书
	private Colour color = Colour.BLACK; // 字体颜色
	private boolean bold = false; // 是否加粗
	private int flow = 0; // 文字浮动方向,0:靠左(默认),1:居中,2:靠右,
	private int fontSize = 0; // 文字大小,0:正常,-2,-1,0,1,2,3,4依次加大,最大到4
	private Colour backgroundColor = Colour.WHITE; // 单元格填充色
	private boolean italic;// 是否斜体
	private int verticalAlign = 1; // 文字上下对齐 0:上 1：中 2：下

	public int getFont() {
		return font;
	}

	public void setFont(int font) {
		this.font = font;
	}

	public Colour getColor() {
		return color;
	}

	public void setColor(Colour color) {
		this.color = color;
	}

	public Colour getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Colour backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public boolean isBold() {
		return bold;
	}

	public void setBold(boolean bold) {
		this.bold = bold;
	}

	public int getFontSize() {
		return fontSize;
	}

	public void setFontSize(int fontSize) {
		this.fontSize = fontSize;
	}

	public int getFlow() {
		return flow;
	}

	public void setFlow(int flow) {
		this.flow = flow;
	}

	public Alignment convertFlow() {
		return convertFlow(flow);
	}

	public static Alignment convertFlow(int flow) {
		Alignment al = null;
		switch (flow) {
		case 0:
			al = Alignment.LEFT;
			break;
		case 1:
			al = Alignment.CENTRE;
			break;
		case 2:
			al = Alignment.RIGHT;
			break;
		default:
			al = Alignment.LEFT;
		}
		return al;
	}

	public FontName convertFontName() {
		return convertFontName(font);
	}

	public static FontName convertFontName(int font) {
		FontName fn = null;
		switch (font) {
		case 0:
			fn = WritableFont.createFont("SimSun");
			break;
		case 1:
			fn = WritableFont.createFont("KaiTi");
			break;
		case 2:
			fn = WritableFont.createFont("SimHei");
			break;
		case 3:
			fn = WritableFont.createFont("FangSong");
			break;
		case 4:
			fn = WritableFont.createFont("LiSu");
			break;
		default:
			fn = WritableFont.createFont("STSong");
		}
		return fn;
	}

	public int convertFontSize() {
		return convertFontSize(fontSize);
	}

	public static int convertFontSize(int fontSize) {
		return 12 + fontSize * 2;
	}

	@Override
	public boolean equals(Object obj) {
		return EqualsBuilder.reflectionEquals(this, obj);
	}

	@Override
	public int hashCode() {
		return HashCodeBuilder.reflectionHashCode(this);
	}

	public boolean isItalic() {
		return italic;
	}

	public void setItalic(boolean italic) {
		this.italic = italic;
	}

	public int getVerticalAlign() {
		return verticalAlign;
	}

	public void setVerticalAlign(int verticalAlign) {
		this.verticalAlign = verticalAlign;
	}
}
