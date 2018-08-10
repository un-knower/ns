package com.newsee.common.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.util.CellRangeAddressList;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;


/**
 * @author 胡乾亮
 * @ClassName ExcelUtil
 * @Description: Excel工具类
 * @date 2017年10月13日 上午8:58:35
 */
public class ExcelUtil {
    /**
     * @param @param  is
     * @param @param  excelFileName
     * @param @return
     * @param @throws IOException
     * @return Workbook
     * @throws
     * @Title: createWorkbook
     * @Description: 判断excel文件后缀名，生成不同的workbook
     */
    public static Workbook createWorkbook(InputStream is, String excelFileName) throws IOException {
        if (excelFileName.endsWith(".xls")) {
            return new HSSFWorkbook(is);
        } else if (excelFileName.endsWith(".xlsx")) {
            return new XSSFWorkbook(is);
        }
        return null;
    }

    /**
     * @param @param  workbook
     * @param @param  sheetIndex
     * @param @return
     * @return Sheet
     * @throws
     * @Title: getSheet
     * @Description: 根据sheet索引号获取对应的sheet
     */
    public static Sheet getSheet(Workbook workbook, int sheetIndex) {
        return workbook.getSheetAt(sheetIndex);
    }

    /**
     * @param @param  vo javaBean
     * @param @param  is 输入流
     * @param @param  excelFileName
     * @param @return
     * @return List<Object>
     * @throws
     * @Title: importDataFromExcel
     * @Description: 将sheet中的数据保存到list中，
     * 1、调用此方法时，vo的属性个数必须和excel文件每行数据的列数相同且一一对应，vo的所有属性都为String
     * 2、在action调用此方法时，需声明
     * private File excelFile;上传的文件
     * private String excelFileName;原始文件的文件名
     * 3、页面的file控件name需对应File的文件名
     */
    public static List<Object> importDataFromExcel(Object vo, InputStream is, String excelFileName) {
        List<Object> list = new ArrayList<Object>();
        try {
            //创建工作簿
            Workbook workbook = createWorkbook(is, excelFileName);
            //创建工作表sheet
            Sheet sheet = getSheet(workbook, 0);
            //获取sheet中数据的行数
            int rows = sheet.getPhysicalNumberOfRows();
            //获取表头单元格个数
            int cells = sheet.getRow(0).getPhysicalNumberOfCells();
            //利用反射，给JavaBean的属性进行赋值
            Field[] fields = vo.getClass().getDeclaredFields();
            for (int i = 1; i < rows; i++) {//第一行为标题栏，从第二行开始取数据
                Row row = sheet.getRow(i);
                int index = 0;
                while (index < cells) {
                    Cell cell = row.getCell(index);
                    if (null == cell) {
                        cell = row.createCell(index);
                    }
//                    cell.setCellType(Cell.CELL_TYPE_STRING);
                    String value = null == cell.getStringCellValue() ? "" : cell.getStringCellValue();

                    Field field = fields[index];
                    String fieldName = field.getName();
                    if (!"serialVersionUID".equals(fieldName)) {
                        String methodName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                        System.out.println(field.getType().getName());
                        Method setMethod = vo.getClass().getMethod(methodName, field.getType());
                        setMethod.invoke(vo, new Object[]{value});
                    }
                    index++;
                }
                if (isHasValues(vo)) {//判断对象属性是否有值
                    list.add(vo);
                    vo.getClass().getConstructor(new Class[]{}).newInstance(new Object[]{});//重新创建一个vo对象
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                is.close();//关闭流
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return list;

    }

    /**
     * @param @param  object
     * @param @return
     * @return boolean
     * @throws
     * @Title: isHasValues
     * @Description: 判断一个对象所有属性是否有值，如果一个属性有值(分空)，则返回true
     */
    public static boolean isHasValues(Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        boolean flag = false;
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
            Method getMethod;
            try {
                getMethod = object.getClass().getMethod(methodName);
                Object obj = getMethod.invoke(object);
                if (null != obj && !("".equals(obj))) {
                    flag = true;
                    break;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        return flag;

    }

    public static void main(String[] args) {
        String[] header = {"*项目|color:RED", "*房产简称|color:RED", "收费对象类型", "收费对象姓名", "证件类型", "证件号码", "*收费科目|color:RED", "*单价|color:RED", "*计费开始日期|color:RED",
                "*计费结束日期|color:RED", "*应收日期|color:RED", "*应收金额|color:RED"};
        for (String s : header) {
            String[] split = s.split("\\|");
            System.out.println("0:" + split[0]);
            if (split.length > 1) {
                String property = split[1];
                if (property.startsWith("color")) {
                    String[] split1 = property.split(":");
                    System.out.println(split1);
//                    switch (split1[1]){
//                        case "RED":
//                            HSSFColor.RED.index;
//                            default: HSSFColor.WHITE.index;
//                    }
                }
            }
        }
    }

    /**
     * 导出Excel
     *
     * @param list    数据
     * @param headers 表头，格式:{"名称1|color:RED","名称2","名称3|color:GREEN"}
     * @param title
     * @param os
     * @param <T>
     */
    public static <T> void exportDataToExcel(List<T> list, String[] headers, String title, OutputStream os) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        //生成一个表格
        HSSFSheet sheet = workbook.createSheet(title);
        //设置表格默认列宽15个字节
        sheet.setDefaultColumnWidth(15);
//        //生成一个样式
//        HSSFCellStyle style = getCellStyle(workbook);
//        //生成一个字体
//        HSSFFont font = getFont(workbook);
//        //把字体应用到当前样式
//        style.setFont(font);

        //生成表格标题
        HSSFRow row = sheet.createRow(0);
        row.setHeight((short) 300);
        HSSFCell cell = null;

        for (int i = 0; i < headers.length; i++) {
            //生成一个样式
            HSSFCellStyle style = getCellStyle(workbook);
            //生成一个字体
            HSSFFont font = getFont(workbook);
            //把字体应用到当前样式
            style.setFont(font);
            cell = row.createCell(i);
            cell.setCellStyle(style);
            String header = headers[i];
            String[] split = header.split("\\|");
            HSSFRichTextString text = new HSSFRichTextString(split[0]);
            cell.setCellValue(text);
            //设置cell.font.color样式
            if (split.length > 1) {
                String s = split[1];
                if (s.startsWith("color")) {
                    String[] colorArr = s.split(":");
                    switch (colorArr[1]) {
                        case "RED":
                            font.setColor(HSSFColor.RED.index);
                            break;
                        case "BLACK":
                            font.setColor(HSSFColor.BLACK.index);
                            break;
                        case "GREEN":
                            font.setColor(HSSFColor.GREEN.index);
                            break;
                        default:
                            font.setColor(HSSFColor.WHITE.index);
                            break;
                    }

                }
            }
        }

        //将数据放入sheet中
        for (int i = 0; i < list.size(); i++) {
            row = sheet.createRow(i + 1);
            T t = list.get(i);
            //利用反射，根据JavaBean属性的先后顺序，动态调用get方法得到属性的值
            Field[] fields = t.getClass().getDeclaredFields();
            try {
                for (int j = 0; j < fields.length; j++) {
                    cell = row.createCell(j);
                    Field field = fields[j];
                    String fieldName = field.getName();
                    String methodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
                    Method getMethod = t.getClass().getMethod(methodName, new Class[]{});
                    Object value = getMethod.invoke(t, new Object[]{});

                    if (null == value)
                        value = "";
                    cell.setCellValue(value.toString());

                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            workbook.write(os);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @param @param  workbook
     * @param @return
     * @return HSSFCellStyle
     * @throws
     * @Title: getCellStyle
     * @Description: 获取单元格格式
     */
    public static HSSFCellStyle getCellStyle(HSSFWorkbook workbook) {
        HSSFCellStyle style = workbook.createCellStyle();
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index);
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
        style.setBorderTop(HSSFCellStyle.BORDER_THIN);
        style.setLeftBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setRightBorderColor(HSSFCellStyle.BORDER_THIN);
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER);

        return style;
    }

    /**
     * @param @param  workbook
     * @param @return
     * @return HSSFFont
     * @throws
     * @Title: getFont
     * @Description: 生成字体样式
     */
    public static HSSFFont getFont(HSSFWorkbook workbook) {
        HSSFFont font = workbook.createFont();
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short) 12);
        font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        return font;
    }

    public static boolean isIE(HttpServletRequest request) {
        return request.getHeader("USER-AGENT").toLowerCase().indexOf("msie") > 0 ? true : false;
    }

    public static InputStream createInputStream(MultipartFile file, HttpServletRequest request) {
        String bathPath = request.getServletContext().getRealPath("upload/");
        System.out.println("bath路径：" + bathPath);
        File uploadDir = new File(bathPath);
        //创建一个目录 （它的路径名由当前 File 对象指定，包括任一必须的父路径。）  
        if (!uploadDir.exists()) uploadDir.mkdirs();
        //新建一个文件  
        File tempFile = new File(bathPath + new Date().getTime() + ".xlsx");
        //初始化输入流  
        InputStream is = null;
        try {
            //将上传的文件写入新建的文件中  
            file.transferTo(tempFile);
            //根据新建的文件实例化输入流  
            is = new FileInputStream(tempFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return is;
    }

    /**
     * 设置某些列的值只能输入预制的数据,显示下拉框.
     *
     * @param sheet    要设置的sheet.
     * @param textList 下拉框显示的内容
     * @param firstRow 开始行
     * @param endRow   结束行
     * @param firstCol 开始列
     * @param endCol   结束列
     * @return 设置好的sheet.
     */
    public static HSSFSheet setHSSFValidation(HSSFSheet sheet, String[] textList, int firstRow, int endRow, int firstCol, int endCol) {
        // 加载下拉列表内容
        DVConstraint constraint = DVConstraint.createExplicitListConstraint(textList);
        // 设置数据有效性加载在哪个单元格上,四个参数分别是：起始行、终止行、起始列、终止列
        CellRangeAddressList regions = new CellRangeAddressList(firstRow, endRow, firstCol, endCol);
        // 数据有效性对象
        HSSFDataValidation data_validation_list = new HSSFDataValidation(regions, constraint);
        sheet.addValidationData(data_validation_list);
        return sheet;
    }

}
