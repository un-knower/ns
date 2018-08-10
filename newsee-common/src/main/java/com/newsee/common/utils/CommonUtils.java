package com.newsee.common.utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import javax.imageio.ImageIO;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.newsee.common.constant.Constants;
import com.newsee.common.enums.HouseDecorateStageEnum;
import com.newsee.common.enums.HouseRentStageEnum;
import com.newsee.common.enums.HouseStageEnum;
import com.newsee.common.exception.BizException;
import com.newsee.common.rest.ResultCodeEnum;
import com.newsee.common.vo.NsCoreResourcecolumnVo;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * 通用工具类
 *
 */
public  class CommonUtils {

	public static final String SPECIAL_SYMBOLS_SQL = "[~!@#$%^&*()+=|{}:;,.''<>/?~!--//-@#￥%……&]";
	
	/**
	 * 验证密码只能是6-15位数字，字母，下划线
	 * 且已字母或数字开头
	 * @return
	 */
	public static boolean checkPwd(String pwd) {
		return pwd.matches("^[a-zA-Z0-9]\\w{5,15}$");
	}

	/**
	 * 是否包含特殊符号
	 * 
	 * @param value
	 * @return
	 */
	public static Boolean isSpecialSymbols(String value) {
		return CommonUtils.isSpecialSymbols(value, null);
	}

	/**
	 * 是否包含特殊字符
	 * 
	 * @param value
	 *            字符内容
	 * @param specialSymbols
	 *            特殊字符或符号
	 * @return
	 */
	public static Boolean isSpecialSymbols(String value, String specialSymbols) {
		String regEx = null;
		if (value == null)  value = "";
		if (specialSymbols == null || "".equals(specialSymbols.trim())) {
			regEx = SPECIAL_SYMBOLS_SQL;
		} else {
			regEx = specialSymbols;
		}
		
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(value);
		return m.find();
	}
	
	/**
	 * 通过时间戳产生随机编码
	 * @return
	 */
	public static Long getRandomFileId() {
		return System.currentTimeMillis() + new Random().nextInt(1000);
	}
	
	/**
	 * 获取16位长度的唯一编码
	 * @param machineId 最大支持1-9个集群机器部署
	 * @return
	 */
	public static String getOrderIdByUUId(int machineId) {
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if(hashCodeV < 0) {//有可能是负数
            hashCodeV = - hashCodeV;
        }
        /**0 代表前面补充0,15 代表长度为15,d 代表参数为正数型 */
        return machineId + String.format("%015d", hashCodeV);
    }
	
	/**
	 * 获取UUID长度28位
	 * @return
	 */
	public static String getUUId() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
	
   /**
	 * 创建一定长度的随机数
	 * @param length
	 * @return
	 */
	public static String createRandomPWD(int length) {
        StringBuilder code = new StringBuilder();
        char[] letter = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        Random rd = new Random();
        for (int i = 0; i < length; i++) {
            code.append(letter[rd.nextInt(10)]);
        }
        return code.toString();
    }
	
	/**
	 * 创建一定长度的随机数
	 * @param length
	 * @return
	 */
	public static String createRandomPWD() {
		return CommonUtils.createRandomPWD(6);
	}
		
	/**
	 * 判断对象是否为空
	 * 或一些基本数据类型是否是默认初始值
	 * @param obj
	 * @return
	 */
	public static Boolean isObjectEmpty(Object obj) {
		if (null == obj) return Boolean.TRUE;
		Boolean bool = Boolean.FALSE;
		if (obj instanceof String) {
			bool = "".equals(((String) obj).trim());
		} else if (obj instanceof Long) {
			bool = (Long) obj == 0;
		} else if (obj instanceof Integer) {
			bool = (Integer) obj == 0;
		} else if (obj instanceof Short) {
			bool = (Short) obj == 0;
		} else if (obj instanceof Byte) {
			bool = (Byte) obj == 0;
		} else if (obj instanceof Double) {
			bool = (Double) obj == 0.0;
		} else if (obj instanceof Float) {
			bool = (Float) obj == 0F;
		} else if (obj instanceof Character) {
			bool = "".equals((Character) obj);
		}
		
		return bool;
	}
	
	/**
	 * 初始化类的基本数据类型
	 * @param clazz
	 * @param obj
	 * @return
	 */
    public  static <T> T getObjInstance(Class<?> clazz, T obj) {
        if (clazz == null || obj == null) {
            throw new NullPointerException("参数为空");
        }
        obj = initObject(clazz, obj);
        return obj;
    }

    /**
	 * 初始化类的基本数据类型
	 * @param clazz
	 * @param obj
	 * @return
	 */
    public static <T> T getObjInstance(String clazzPath, T obj) {
        if ((clazzPath == null || "".equals(clazzPath.trim())) || obj == null) {
            throw new NullPointerException("参数为空");
        }
        Class<?> clazz;
        try {
            clazz = Class.forName(clazzPath);
            obj = initObject(clazz, obj);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return obj;
    }

    private static <T> T initObject(Class<?> clazz, T obj) {
        try {
            // 取得本类的全部属性
            Field[] fields = clazz.getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                // 权限修饰符
                Field field = fields[i];
                // 属性类型
                Class<?> type = field.getType();
                field.setAccessible(true);
                int index = fields[i].getModifiers();
                String permissions = Modifier.toString(index);                
                if(permissions.equals("private")) {
                    StringBuffer sbuffer = new StringBuffer("set").append(Character.toUpperCase(field.getName().charAt(0)));
                    if (field.getName().length() > 1)  sbuffer.append( field.getName().substring(1) );
                    String strMethod = sbuffer.toString();
                    Method method = clazz.getMethod(strMethod, type);
                    dealWithParam(field, obj, type.getSimpleName(), method);
                }
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (SecurityException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } 
        
        return obj;
    }

    private static <T> void dealWithParam(Field field, T obj, String fieldName, Method method) 
    		throws IllegalArgumentException, IllegalAccessException, InvocationTargetException {
        if (field.get(obj) == null) {
            switch (fieldName) {
            case "String":
                //field.set(obj, "直接操作属性");
                method.invoke(obj, "");
                break;
            case "Integer":
                method.invoke(obj, 0);
                break;
            case "Long":
                method.invoke(obj, 0L);
                break;
            case "Short":
                method.invoke(obj, 0);
                break;
            case "Character":
                method.invoke(obj, "");
                break;
            case "Float":
                method.invoke(obj, 0f);
                break;
            case "Double":
                method.invoke(obj, 0);
                break;
            case "Boolean":
                method.invoke(obj, false);
                break;
            case "Byte":
                method.invoke(obj, 0);
                break;
            default:
                break;
            }
        }
    }
    
    /**
	 * 判断字符串是否为null或空字符串
	 * @param target 目标字符串
	 * @return boolean 判断结果
	 * @author xiaosisi on 2017/3/7
	 */
	public static boolean isNullOrBlank(String target){
		if(target == null || target.trim().length() == 0){
			return true;
		}
		return false;
		
	}
	/**
	 * 
	* @Title: distinguish 
	* @Description: 区分新旧json串不同
	* @param @param oldStr
	* @param @param newStr
	* @param @return    设定文件 
	* @return List<String>    返回不同之处
	* @throws 
	* @author wangrenjie
	 */
	public static List<String> distinguish(String oldStr, String newStr){
	    List<String> list = new ArrayList<>();
	    if (CommonUtils.isNullOrBlank(oldStr) || CommonUtils.isNullOrBlank(newStr)) {
            return null;
        }
        Map<String, Object> map1 = JSONObject.parseObject(oldStr);
        Map<String, Object> map2 = JSONObject.parseObject(newStr);
       
        for (Iterator<Entry<String, Object>> iterator = map1.entrySet().iterator(); iterator.hasNext();) {
            Entry<String, Object> entry = (Entry<String, Object>) iterator.next();
            if (map2.containsKey(entry.getKey())) {
                if (!map2.get(entry.getKey()).equals(entry.getValue())) {
                    list.add("旧数据("+entry.getKey()+":"+entry.getValue()+")--->新数据("+entry.getKey()+":"+map2.get(entry.getKey())+")");
                }
            }
            if (!map2.containsKey(entry.getKey())) {
                list.add("旧数据("+entry.getKey()+":"+entry.getValue()+")--->新数据("+entry.getKey()+":\"\")");
            }
        }	  
        return list;
	}
	
	/**
	 * 
	 * @Title: isNumber 
	 * @Description: 判断是否为整数
	 * @param @param str
	 * @param @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 * @author wangrenjie
	 */
	public static boolean isNumber(String str){
        Pattern pattern = Pattern.compile("[-+]{0,1}\\d+"); 
        Matcher isNum = pattern.matcher(str);
        if( !isNum.matches() ){
            return false; 
        } 
        return true; 	    
	}
	
	/**
	 * 
	 * @Title: isDecimal 
	 * @Description: 判断是否为小数 
	 * @param @param str
	 * @param @return    设定文件 
	 * @return boolean    返回类型 
	 * @throws 
	 *  @author wangrenjie
	 */
	public static boolean isDecimal(String str){ 
	    Pattern pattern = Pattern.compile("[-+]{0,1}\\d+\\.\\d*|[-+]{0,1}\\d*\\.\\d+"); 
	    Matcher isNum = pattern.matcher(str);
	    if( !isNum.matches() ){
	        return false; 
	    } 
	    return true; 
	}
	
	
    //  删除ArrayList中重复元素 
     public static <T> void removeDuplicate(List<T> list)  {
        HashSet<T> h = new HashSet<>(list);
        list.clear();
        list.addAll(h);
    }
    /**
     * 
    * @Title: toPinyin 
    * @Description: 汉语转拼音
    * @param @param chineseLanguage
    * @param @return    设定文件 
    * @return String    返回类型 
    * @throws 
    * @author wangrenjie
     */
    public static String toPinyin(String chineseLanguage){
        String hanyupinyin = "";
        if (StringUtils.isNotBlank(chineseLanguage)) {
            char[] cl_chars = chineseLanguage.trim().toCharArray();
            HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
            defaultFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 输出拼音全部小写
            defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不带声调
            defaultFormat.setVCharType(HanyuPinyinVCharType.WITH_V) ; 
            try {
                for (int i=0; i<cl_chars.length; i++){
                    if (String.valueOf(cl_chars[i]).matches("[\u4e00-\u9fa5]+")){// 如果字符是中文,则将中文转为汉语拼音
                            hanyupinyin += PinyinHelper.toHanyuPinyinStringArray(cl_chars[i], defaultFormat)[0];
                    } else {// 如果字符不是中文,则不转换
                        hanyupinyin += cl_chars[i];
                    }
                }
            } catch (BadHanyuPinyinOutputFormatCombination e) {
                BizException.fail(ResultCodeEnum.PARAMS_ERROR, "字符不能转成汉语拼音");
            }            
        }
        return hanyupinyin;
    }
    
    /** 
     * 取出拼音中第一个字母
     * @param chineseLanguage 
     * @return 
     */  
    public static String converterToFirstSpell(String chineseLanguage) {  
        String pinyinName = "";  
        if (chineseLanguage == null || chineseLanguage.length() == 0) {
        	return pinyinName;
        }
        char[] nameChar = chineseLanguage.toCharArray();  
        HanyuPinyinOutputFormat defaulFormat = new HanyuPinyinOutputFormat();  
        defaulFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);  
        defaulFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);  
        defaulFormat.setVCharType(HanyuPinyinVCharType.WITH_V);  
        for (int i = 0; i < nameChar.length; i++) {  
            if (nameChar[i] > 128) {
                try {
                    pinyinName += PinyinHelper.toHanyuPinyinStringArray(nameChar[i], defaulFormat)[0].charAt(0);    
                } catch (BadHanyuPinyinOutputFormatCombination ex) {
                    ex.printStackTrace();  
                }  
            } else {  
                pinyinName += nameChar[i];  
            }  
        }  
        return pinyinName;  
    }  
    
    public static int getAgeByBirth(Date birthday) {
        int age = 0;
        try {
            Calendar now = Calendar.getInstance();
            now.setTime(new Date());// 当前时间

            Calendar birth = Calendar.getInstance();
            birth.setTime(birthday);

            if (birth.after(now)) {//如果传入的时间，在当前时间的后面，返回0岁
                age = 0;
            } else {
                age = now.get(Calendar.YEAR) - birth.get(Calendar.YEAR);
                if (now.get(Calendar.DAY_OF_YEAR) > birth.get(Calendar.DAY_OF_YEAR)) {
                    age += 1;
                }
            }
            return age;
        } catch (Exception e) {//兼容性更强,异常后返回数据
           return 0;
        }
    }
    
    /**
     * 
    * @Title: getHouseStage 
    * @Description: 获取房态
    * @param stage 房态
    * @param rentStage 出租状态
    * @param decorateStage 装修状态
    * @param isBlockUp 停用状态
    * @return String    返回类型 
    * @date  2017年11月14日 上午9:07:59
    * @author wangrenjie
     */
    public static String getHouseStage(String stage, String rentStage, String decorateStage, Integer isBlockUp){
        String stageName = "";
        if (Constants.TRUE == isBlockUp.intValue())
        {
            stageName = "停用";//房产登记 停用
        }
        else if (HouseDecorateStageEnum.DECORATE_STAGE_IN.getValue().equals(decorateStage))
        {
            stageName = "装修中";//装修登记装修中
        }
        else if (HouseRentStageEnum.RENT_STAGE_IN.getValue().equals(rentStage))
        {
            stageName = "出租";//出租登记出租
        }
        else if (HouseStageEnum.RU_ZHU.getValue().equals(stage))
        {
            stageName = "入住";//入住登记入住
        }
        else if (HouseDecorateStageEnum.DECORATE_STAGE_DONE.getValue().equals(decorateStage))
        {
            stageName = "装修完未入住";//装修完未入住
        }
        else if (HouseStageEnum.KONG_GUAN.getValue().equals(stage))
        {
            stageName = "空关";//收房（入伙）登记空关 ，已收房
        }
        else if (HouseStageEnum.WEI_LING.getValue().equals(stage))
        {
            stageName = "未领"; //售楼登记已售未领
        }
        else if (HouseStageEnum.KONG_ZHI.getValue().equals(stage))
        {
            stageName = "空置"; //售楼登记 未售空置
        }
        else //其他状态都为入住
        {
            stageName = "入住";//入住
        }
        return stageName;
    }
    /**
     * 
    * @Title: createQrCode 
    * @Description: 生成包含字符串信息的二维码图片
    * @param outputStream 文件输出流路径
    * @param content 二维码携带信息
    * @param qrCodeSize 二维码图片大小
    * @param imageFormat 二维码的格式
    * @return
    * @throws WriterException
    * @throws IOException boolean    返回类型 
    * @date  2017年12月7日 下午7:22:18
    * @author wangrenjie
     */
    public static void createQrCode(OutputStream os, String content, int qrCodeSize, String imageFormat) throws WriterException, IOException{  
         //设置二维码纠错级别ＭＡＰ
         Hashtable<EncodeHintType, ErrorCorrectionLevel> hintMap = new Hashtable<EncodeHintType, ErrorCorrectionLevel>();  
         hintMap.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);  // 矫错级别  
         QRCodeWriter qrCodeWriter = new QRCodeWriter();  
         //创建比特矩阵(位矩阵)的QR码编码的字符串  
         BitMatrix byteMatrix = qrCodeWriter.encode(content, BarcodeFormat.QR_CODE, qrCodeSize, qrCodeSize, hintMap);  
         // 使BufferedImage勾画QRCode  (matrixWidth 是行二维码像素点)
         int matrixWidth = byteMatrix.getWidth();  
         BufferedImage image = new BufferedImage(matrixWidth-200, matrixWidth-200, BufferedImage.TYPE_INT_RGB);  
         image.createGraphics();  
         Graphics2D graphics = (Graphics2D) image.getGraphics();  
         graphics.setColor(Color.WHITE);  
         graphics.fillRect(0, 0, matrixWidth, matrixWidth);  
         // 使用比特矩阵画并保存图像
         graphics.setColor(Color.BLACK);  
         for (int i = 0; i < matrixWidth; i++){
             for (int j = 0; j < matrixWidth; j++){
                 if (byteMatrix.get(i, j)){
                     graphics.fillRect(i-100, j-100, 1, 1);  
                 }
             }
         }
         ImageIO.write(image, imageFormat, os);  
     }  
    /**
     * 
    * @Title: toJson 
    * @Description: 实体转JSON 
    * @param object
    * @return String    返回类型 
    * @date  2017年12月14日 上午11:02:13
    * @author wangrenjie
     */
    public static String toJson(Object object){
        if (object != null) {
            return JSON.toJSONString(object,SerializerFeature.WriteDateUseDateFormat, SerializerFeature.WriteMapNullValue, SerializerFeature.WriteNullListAsEmpty,SerializerFeature.WriteNullStringAsEmpty, SerializerFeature.DisableCircularReferenceDetect);
        }
        return "";
    }
    /**
     * 
    * @Title: clearNull 
    * @Description: 去除实体中的null
    * @param object
    * @return T    返回类型 
    * @date  2017年12月14日 下午1:51:54
    * @author wangrenjie
     */
    @SuppressWarnings("unchecked")
    public static <T>T clearNull(T object){
//        TimeZone.setDefault(TimeZone.getTimeZone("GMT+8"));  
        JSON.DEFFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
        String objectJson = toJson(object);
        if (StringUtils.isNotBlank(objectJson)) {
            object = JSONObject.parseObject(objectJson, (Class<T>)object.getClass());
        }
        return object;
    }
    /**
     * 
    * @Title: totalList 
    * @Description: 对列表数据进行合计
    * @param listJson
    * @param columnMap
    * @param clazz
    * @return
    * @throws Exception String    返回类型 
    * @date  2018年1月9日 上午10:04:49
    * @author wangrenjie
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static <T> String totalList(String listJson,List<NsCoreResourcecolumnVo> columnList, Class<T> clazz) throws Exception{
        T t = clazz.newInstance();
        String resultStr = "";
//        if (columnMap != null && !isNullOrBlank(listJson)) {
            List<Map> list = JSONArray.parseArray(listJson, Map.class);
            Field[] fields = clazz.getDeclaredFields();
            if (!CollectionUtils.isEmpty(list)) {
//                List<NsCoreResourcecolumnVo> tempList = new ArrayList<>();
//                List<NsCoreResourcecolumnVo> columnList = (List<NsCoreResourcecolumnVo>) columnMap.get("columns");
//                if (!CollectionUtils.isEmpty(columnList)) {
//                    String temp = JSONArray.toJSONString(columnList);
//                    tempList = JSONArray.parseArray(temp, NsCoreResourcecolumnVo.class);
//                    tempList = tempList.stream().filter(column->Constants.TRUE.toString().equals(column.getResourcecolumnAllowedit())).collect(Collectors.toList());
//                }
                for (NsCoreResourcecolumnVo columnVo : columnList) {
                    BigDecimal total = new BigDecimal("0.00");
                    String resourceColumnNameEn = toLowerCaseFirstOne(columnVo.getResourcecolumnNameEn());
                    for (Map<String, Object> map : list) {
                        if (map.get(resourceColumnNameEn) == null) {
                            BizException.fail(ResultCodeEnum.DATA_NOT_EXIST, resourceColumnNameEn +"字段不存在");
                            continue;
                        }
                        total = total.add(new BigDecimal(map.get(resourceColumnNameEn).toString()));
//                        total = total + Long.valueOf(map.get(columnVo.getResourcecolumnNameEn()).toString());
                    }
                    Field field = null;
                    for (int i = 0; i < fields.length; i++) {
                        if (fields[i].getName().equals(resourceColumnNameEn)) {
                            field = fields[i];
                            break;
                        }
                    }
                    if (field != null) {
                        String methodName = "set"+ resourceColumnNameEn.substring(0,1).toUpperCase()+ resourceColumnNameEn.substring(1);
                        Method setMethod = t.getClass().getMethod(methodName, field.getType());
                        Object value = null;
                        if (field.getType().equals(BigDecimal.class)) {
                            value = total;
                        } else if (field.getType().equals(Long.class)) {
                            value = total.longValue();
                        } else if (field.getType().equals(Integer.class)) {
                            value = total.intValue();
                        } else if (field.getType().equals(Short.class)) {
                            value = total.shortValue();
                        } else if (field.getType().equals(Byte.class)) {
                            value = total.byteValue();
                        } else if (field.getType().equals(Double.class)) {
                            value = total.doubleValue();
                        } else if (field.getType().equals(Float.class)) {
                            value = total.floatValue();
                        }
                        setMethod.invoke(t, new Object[]{value});   
                    }
                }
                resultStr = JSONObject.toJSONString(t);
            }
//        }
        return resultStr;
    }

    /**
     * 首字母转小写
     */
    public static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     * 首字母转大写
     */
    public static String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
    /**
     * 
    * @Title: getTotalColumn 
    * @Description: 从表头中获取需要统计的字段 
    * @param columnList
    * @return List<NsCoreResourcecolumnVo>    返回类型 
    * @date  2018年1月9日 上午10:04:21
    * @author wangrenjie
     */
    public static List<NsCoreResourcecolumnVo> getTotalColumn(List<NsCoreResourcecolumnVo> columnList){
        List<NsCoreResourcecolumnVo> tempList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(columnList)) {
            String temp = JSONArray.toJSONString(columnList);
            tempList = JSONArray.parseArray(temp, NsCoreResourcecolumnVo.class);
            tempList = tempList.stream().filter(column->Constants.TRUE.toString().equals(column.getResourcecolumnAllowedit())).collect(Collectors.toList());
        }
        return tempList;
    }
    /**
     * @param @param  decimal
     * @param @return 设定文件
     * @return Long    返回类型
     * @throws
     * @Title: decimal2Long
     * @Description: 将前端小数数值转换成long
     * @author wangrenjie
     */
    public static Long decimal2Long(BigDecimal decimal) {
        if (decimal == null) {
            return null;
        }
        return decimal.multiply(new BigDecimal(Constants.DECIMAL_TRANS_LONG)).longValue();
    }

    public static BigDecimal long2Decimal(Long longNum){
        if (isObjectEmpty(longNum)) {
            return new BigDecimal("0.00");
        }   
        BigDecimal decimal = new BigDecimal(longNum).divide(new BigDecimal(Constants.DECIMAL_TRANS_LONG));
        if (decimal != null) {
            decimal = decimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        return decimal;
    }
    
    public static BigDecimal long2DecimalHasNull(Long longNum){
        if (longNum == null) {
            return null;
        }   
        BigDecimal decimal = new BigDecimal(longNum).divide(new BigDecimal(Constants.DECIMAL_TRANS_LONG));
        if (decimal != null) {
            decimal = decimal.setScale(2,BigDecimal.ROUND_HALF_UP);
        }
        return decimal;
    }    

}
