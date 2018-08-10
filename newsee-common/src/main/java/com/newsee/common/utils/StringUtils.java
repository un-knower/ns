package com.newsee.common.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.newsee.common.constant.Constants;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 字符串工具类
 *
 */
public class StringUtils {
	private static final HanyuPinyinOutputFormat defaultPinyinFormat = new HanyuPinyinOutputFormat();
	static {

		// UPPERCASE：大写 (ZHONG)
		// LOWERCASE：小写 (zhong)
		defaultPinyinFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);

		// WITHOUT_TONE：无音标 (zhong)
		// WITH_TONE_NUMBER：1-4数字表示英标 (zhong4)
		// WITH_TONE_MARK：直接用音标符（必须WITH_U_UNICODE否则异常） (zhòng)
		defaultPinyinFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);

		// WITH_V：用v表示ü (nv)
		// WITH_U_AND_COLON：用"u:"表示ü (nu:)
		// WITH_U_UNICODE：直接用ü (nü)
		defaultPinyinFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
	}	
	
	/**
	 * 首字母小写
	 */
	public static String lowerCaseFirstLetter(String str) {
		if (str == null || str.length() == 0)
			return str;
		String firstLetter = str.substring(0, 1).toLowerCase();
		String end = str.substring(1, str.length());
		return firstLetter + end;
	}

	/**
	 * List<Long>转换为List<String>
	 * 
	 * @param longList
	 * @return
	 */
	public static List<String> longListToStringList(List<Long> longList) {
		List<String> stringList = new ArrayList<String>();
		if (longList == null || longList.isEmpty()) {
			return stringList;
		}
		for (Long num : longList) {
			stringList.add(num.toString());
		}
		return stringList;
	}
	
	/**
	 * List<Long>转换为String（以,隔开）
	 * 
	 * @param longList
	 * @return
	 */
	public static String longListToString(List<Long> longList) {
		StringBuffer strb = new StringBuffer();
		if (longList == null || longList.isEmpty()) {
			return strb.toString();
		}
		for(int i=0;i<longList.size();i++){
			if(longList.get(i)!=null){
				strb.append(longList.get(i));
				if(i!=longList.size()-1){
					strb.append(",");
				}
			}
		}
		return strb.toString();
	}
	
	/**
	 * List<String>转换为String（以,隔开）
	 * 
	 * @param strList
	 * @return
	 */
	public static String strListToString(List<String> strList) {
		StringBuffer strb = new StringBuffer();
		if (strList == null || strList.isEmpty()) {
			return strb.toString();
		}
		for(int i=0;i<strList.size();i++){
			if(strList.get(i)!=null){
				strb.append(strList.get(i));
				if(i!=strList.size()-1){
					strb.append(",");
				}
			}
		}
		return strb.toString();
	}
	
	/**
	 * List<String>转换为List<Long>
	 * 
	 * @param StringList
	 * @return
	 */
	public static List<Long> StringListTolongList(List<String> StringList) {
		List<Long> longList = new ArrayList<Long>();
		if (StringList == null || longList.isEmpty()) {
			return longList;
		}
		for (String str : StringList) {
			longList.add(Long.valueOf(str));
		}
		return longList;
	}

	/**
	 * 获取字符串中的值，以","分割
	 * 
	 * @param ids
	 * @return
	 */
	public static List<String> getStrList(String ids) {
		if (ids == null || ids.isEmpty() || ids.replace("{","").replace("}","").trim()==null || ids.replace("{","").replace("}","").trim().isEmpty()) {
			return new ArrayList<String>();
		}
		List<String> strList = new ArrayList<String>();
		ids = ids.replace("{","").replace("}","").trim();
		String[] str = ids.split(",");
		for (String str_ : str) {
			strList.add(str_);
		}
		return strList;
	}

	/**
	 * 获取字符串中的整形，以","分割
	 * 
	 * @param ids
	 * @return
	 */
	public static List<Long> getLongList(String ids) {
		if (ids == null || ids.isEmpty()) {
			return new ArrayList<Long>();
		}
		List<Long> longList = new ArrayList<Long>();
		String[] str = ids.split(",");
		for (String str_ : str) {
			longList.add(Long.parseLong(str_));
		}
		return longList;
	}
	
	/**
	 * 获取字符串中的int类型数据，以","分割
	 * 
	 * @param ids
	 * @return
	 */
	public static List<Integer> getIntegerList(String ids) {
		if (ids == null || ids.isEmpty()) {
			return new ArrayList<Integer>();
		}
		List<Integer> integerList = new ArrayList<Integer>();
		String[] str = ids.split(",");
		for (String str_ : str) {
			integerList.add(Integer.valueOf(str_));
		}
		return integerList;
	}

	/**
	 * 判断字符串是否为空 null -- true "" -- true " " -- true
	 * 
	 * @param str
	 * @return
	 */
	public static boolean isBlank(String str) {
		if (str == null) {
			return true;
		}
		str = str.trim();
		if ("".equals(str) || str.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * 判断多个字符串中是否存在未空的字符串
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isAnyBlank(String... strings) {
		for (String str : strings) {
			if (isBlank(str)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 判断多个字符串中是否都是为空的字符串
	 * 
	 * @param strings
	 * @return
	 */
	public static boolean isAllBlank(String... strings) {
		for (String str : strings) {
			if (!isBlank(str)) {
				return false;
			}
		}
		return true;
	}

	public static String getPinYin(String str) {
		if (str == null)
			return null;
		StringBuilder stringBuilder = new StringBuilder();
		char charArr[] = str.toCharArray();
		for (char c : charArr) {
			try {
				String[] pinyin =  PinyinHelper.toHanyuPinyinStringArray(c, defaultPinyinFormat);
				if(pinyin.length>0){
					stringBuilder.append(pinyin[0]);
				}else{
					stringBuilder.append(c);
				}
			} catch (Exception e) {
//				throw new BaseException(e);
			}
		}
		return stringBuilder.toString();
	}
	
	public static String getPinYinFirstLetter(String str) {
		if(str==null)
			return null;
		StringBuilder stringBuilder = new StringBuilder();
		char charArr[] = str.toCharArray();
		for (char c : charArr) {
			try {
				String[] pinyin = PinyinHelper.toHanyuPinyinStringArray(c, defaultPinyinFormat);
				if(pinyin.length>0){
					stringBuilder.append(pinyin[0].substring(0,1).toUpperCase());
				}else{
					stringBuilder.append(c);
				}
			} catch (Exception e) {
//				throw new BaseException(e);
			}
		}
		return stringBuilder.toString();
	}
	
	/**
	 * 从URL中获取IP地址
	 * @param url
	 * @return
	 */
	public static String getIpFromUrl(String url) {
		if (url != null && url.length() > 0) {
			if (url.contains("http:"))
				url = url.replace("http://", "");
			else if (url.contains("https:"))
				url = url.replace("https://", "");
			if (url.contains(":")) {
				url = url.substring(0, url.indexOf(":"));
			} else if (url.contains("/")) {
				url = url.substring(0, url.indexOf("/"));
			}
		}
		return url;
	}
	
	/**
	 * 处理json串中的特殊字符
	 * @param source 源字符串
	 * @return
	 */
	public static String replaceSpecialForJson(String source){
		if(isBlank(source)){
			return "";
		}
        return source.replaceAll("\r", "\\r").
        		replaceAll("\n", "\\n").
        		replaceAll("'", "\"").
        		replaceAll("\"","\\\"").
        		replaceAll("\\{","|").
        		replaceAll("\\}","|");
	}
	
	/**
	 * 检查String数组中是否有和target数组中一样的String字符串
	 * 并返回在数组中的index
	 * @param array
	 * @param target
	 * @return -1,target不存在于数组中，其他，返回在数组中的index
	 * @author xiaosisi add on 2017/08/25
	 */
	public static int indexOf(String[] array, String target){
		int index = -1;
		if(array == null || array.length == 0){
			return index;
		}
		if(isBlank(target)){
			return index;
		}
		for(int i =0; i<array.length; i++){
			if(target.equals(array[i])){
				return i;
			}
		}
		return index;
	}
	
	/**
	 * 将string转换成Boolean
	 * 【1】或者全部转换成小写为【true】，其他情况为false
	 * @param value
	 * @return
	 * @author xiaosisi add on 2017/08/25
	 */
	public static Boolean parseBoolean(String value){
		if(isBlank(value)){
			return false;
		}
		if("1".equals(value) || "true".equals(value.toLowerCase())){
			return true;
		}
		return false;
	}
	
	/**
	 * 将String装换成integer
	 * @param value
	 * @return Integer
	 * @author xiaosisi add on 2017/08/25
	 */
	public static Integer parseInteger(String value){
		if(isBlank(value)){
			return null;
		}
		return Integer.parseInt(value);
	}
	
	/**
	 * 将string字符串按照特定的字符分割成list
	 * @param value
	 * @param splitChar
	 * @return
	 */
	public static List<String> parseList(String value, String splitChar){
		List<String> list = new ArrayList<String>();
		if(!isBlank(value) && !isBlank(splitChar)){
			if(value.indexOf(splitChar)>=0){
				list.addAll(Arrays.asList(value.split(splitChar)));
			}else{
				list.add(value);
			}
		}
		return list;
	}
	
	/**
	 * 如果value为空或者null，放回空格，表单显示时前台需要
	 * 使用该功能
	 * @param value
	 * @return
	 */
	public static String addBlank(String value){
		if(isBlank(value)){
			return " ";
		}
		return value;
	}
	
	 /**
     * 驼峰转下划线格式
     * @param word
     * @return
     */
    public static String humpToUnderLine(String word){
    	if(isBlank(word)){
    		return "";
    	}
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (Character.isUpperCase(c)) {
                 sb.append("_");
                 sb.append(Character.toLowerCase(c));
            }else {
                 sb.append(c);
            }
        }
        return sb.toString();
    }
    
    /**
     * 将下划线格式转换成驼峰格式的数据
     * @param word
     * @return
     */
    public static String underlineToHump(String word){
    	if(isBlank(word)){
    		return "";
    	}
    	word = lowerCaseFirstLetter(word);
    	//如果没有下划线则不转驼峰格式
    	if(word.indexOf("_") <= -1){
    		return word;
    	}
    	//如果有下划线则转驼峰格式
    	boolean isUnderLineAfter = false;
    	StringBuffer sb = new StringBuffer();
    	for (int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '_') {
            	isUnderLineAfter = true;
            	continue;
            }else {
            	if(isUnderLineAfter){
            		sb.append(Character.toUpperCase(c));
            		isUnderLineAfter = false;
            	}else{
            		sb.append(Character.toLowerCase(c));
            	}
            }
        }
    	return sb.toString();
    }
    
    /**
     * 使用分隔符将字符串分割开
     * @param source 源字符串
     * @param delimiter 分割符
     * @return
     */
    
    public static String[] delimitedListToStringArray(String str, String delimiter) {
        return delimitedListToStringArray(str, delimiter, null);
    }
    
    public static String[] delimitedListToStringArray(String str, String delimiter, String charsToDelete) {
        if (str == null) {
            return new String[0];
        }
        if (delimiter == null) {
            return new String[] {str};
        }

        List<String> result = new ArrayList<String>();
        if ("".equals(delimiter)) {
            for (int i = 0; i < str.length(); i++) {
                result.add(deleteAny(str.substring(i, i + 1), charsToDelete));
            }
        }
        else {
            int pos = 0;
            int delPos;
            while ((delPos = str.indexOf(delimiter, pos)) != -1) {
                result.add(deleteAny(str.substring(pos, delPos), charsToDelete));
                pos = delPos + delimiter.length();
            }
            if (str.length() > 0 && pos <= str.length()) {
                // Add rest of String, but not in case of empty input.
                result.add(deleteAny(str.substring(pos), charsToDelete));
            }
        }
        return toStringArray(result);
    }
    
    public static String deleteAny(String inString, String charsToDelete) {
        if (!hasLength(inString) || !hasLength(charsToDelete)) {
            return inString;
        }

        StringBuilder sb = new StringBuilder(inString.length());
        for (int i = 0; i < inString.length(); i++) {
            char c = inString.charAt(i);
            if (charsToDelete.indexOf(c) == -1) {
                sb.append(c);
            }
        }
        return sb.toString();
    }
    
    public static boolean hasLength(String str) {
        return hasLength((CharSequence) str);
    }
    
    public static boolean hasLength(CharSequence str) {
        return (str != null && str.length() > 0);
    }
    public static String[] toStringArray(Collection<String> collection) {
        if (collection == null) {
            return null;
        }

        return collection.toArray(new String[collection.size()]);
    }
    /**
	 * Replace all occurrences of a substring within a string with
	 * another string.
	 * @param inString {@code String} to examine
	 * @param oldPattern {@code String} to replace
	 * @param newPattern {@code String} to insert
	 * @return a {@code String} with the replacements
	 */
	public static String replace(String inString, String oldPattern, String newPattern) {
		if (!isBlank(inString) || !isBlank(oldPattern) || newPattern == null) {
			return inString;
		}
		int index = inString.indexOf(oldPattern);
		if (index == -1) {
			// no occurrence -> can return input as-is
			return inString;
		}

		int capacity = inString.length();
		if (newPattern.length() > oldPattern.length()) {
			capacity += 16;
		}
		StringBuilder sb = new StringBuilder(capacity);

		int pos = 0;  // our position in the old string
		int patLen = oldPattern.length();
		while (index >= 0) {
			sb.append(inString.substring(pos, index));
			sb.append(newPattern);
			pos = index + patLen;
			index = inString.indexOf(oldPattern, pos);
		}

		// append any characters to the right of a match
		sb.append(inString.substring(pos));
		return sb.toString();
	}
	
	/**
	 * 如果字符串为null，转换成空字符串，其他情况原样返回
	 * @param source
	 * @return
	 */
	public static String nullToBlank(String source){
		if(isBlank(source)){
			return "";
		}
		return source;
	}
	
	/**
	 * 比较两个json字符串异同，
	 * 并将不相同的部分返回
	 * @param before
	 * @param after
	 */
	public static Map<String, Object> compareJson(String before, String after){
		Map<String, Object> result = Maps.newHashMap();
		if(isBlank(before) || isBlank(after)){
			return result;
		}
		Map<String, Object> beforeMap = JSON.parseObject(before, Map.class);
		Map<String, Object> afterMap = JSON.parseObject(after, Map.class);
		if(Objects.isNull(beforeMap) || Objects.isNull(afterMap)){
			return result;
		}
		
		return result;
	}
	/**
     * 将Path转换为List<Long>
     *
     * @param path
     * @return
     */
    public static List<Long> handlerPath2List(String path) {
        String[] pathArray = StringUtils.delimitedListToStringArray(path, Constants.SEPARATOR_PATH);
        List<String> pathStrList = Arrays.asList(pathArray);
        List<Long> pathList = new ArrayList<>();
        for (String pathStr : pathStrList) {
            if (!StringUtils.isBlank(pathStr)) {
                pathList.add(Long.parseLong(pathStr));
            }
        }
        return pathList;
    }
}
