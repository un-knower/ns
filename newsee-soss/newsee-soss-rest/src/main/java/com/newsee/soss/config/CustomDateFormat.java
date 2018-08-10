/**
 * 
 */
package com.newsee.soss.config;

import java.text.FieldPosition;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.newsee.common.utils.StringUtils;

import com.fasterxml.jackson.databind.util.StdDateFormat;

/**
 * @ClassName: CustomDateFormat
 * @Description:用于解析yyyy-MM-dd HH:mm:ss时间格式
 * @author 肖斯斯
 * @date 2018年01月23日 下午4:28:57
 */
public class CustomDateFormat extends StdDateFormat{

	private static final long serialVersionUID = -3201781773655300201L;

	public static final CustomDateFormat instance = new CustomDateFormat();
	
	@Override
	public Date parse(String dateStr, ParsePosition pos) {
		SimpleDateFormat sdf  = null;
		if(StringUtils.isBlank(dateStr)){
			return null;
		}
		if (dateStr.length() == 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 16) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 19) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 23) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return sdf.parse(dateStr, pos);
		}
		return super.parse(dateStr, pos);
	}
	
	@Override
	public Date parse(String dateStr) {
		ParsePosition pos = new ParsePosition(0);
		SimpleDateFormat sdf  = null;
		if(StringUtils.isBlank(dateStr)){
			return null;
		}
		if (dateStr.length() == 10) {
			sdf = new SimpleDateFormat("yyyy-MM-dd");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 16) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 19) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			return sdf.parse(dateStr, pos);
		}
		if (dateStr.length() == 23) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
			return sdf.parse(dateStr, pos);
		}
		return super.parse(dateStr, pos);
	}
	
	public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date, toAppendTo, fieldPosition);
	}

	public CustomDateFormat clone() {
		return new CustomDateFormat();
	}
}
