package com.company.project.utils;

import com.company.project.annotation.ExcelField;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * 导出excel工具类
 */
public class ExportExcelUtil {

    /**
     *
     * @param response
     * @param fileName excel名称
     * @param colNames 列名
     * @param dataBody 数据体
     * @param <T> 映射实体对象
     */
    public static <T> void export(HttpServletResponse response, String fileName, String[] colNames, List<T> dataBody) throws Exception{

        if (colNames == null || colNames != null && colNames.length <= 0) {
            return;
        }
        Long startTime = System.currentTimeMillis();
        //处理输出流
        OutputStream os = response.getOutputStream();// 取得输出流
        response.reset();// 清空输出流
        response.setHeader("Content-disposition", "attachment; " +
                "filename=" + new String(fileName.getBytes("GB2312"), "ISO8859-1"));// 设定输出文件头
        response.setContentType("application/msexcel");// 定义输出类型

        Workbook workbook = new SXSSFWorkbook();
        Sheet sheet = workbook.createSheet();
        //处理标题
        Row rowHeader = sheet.createRow(0);
        Cell cellHeader = null;
        for (int i = 0; i < colNames.length; i++) {
            cellHeader = rowHeader.createCell(i);
            cellHeader.setCellValue(colNames[i]);
            sheet.setColumnWidth(i, colNames[i].length()*2*256);//设置列宽（单位1/256）
        }

        //处理数据体
        if (!CollectionUtils.isEmpty(dataBody)) {
            Row rowBody = null;
            Cell cellBody = null;
            Field[] fields = dataBody.get(0).getClass().getDeclaredFields();
            for (int i = 0; i < dataBody.size(); i++) {
                rowBody = sheet.createRow(i+1);
                Class clazz = dataBody.get(i).getClass();
                for (int j = 0; j < rowHeader.getLastCellNum(); j++) {//从excel第一列开始赋值
                    cellBody = rowBody.createCell(j);
                    String colName = rowHeader.getCell(j).getStringCellValue();
                    for (int k = 0; k < fields.length; k++) {//根据注解查找对应的值
                        ExcelField excelField = fields[k].getAnnotation(ExcelField.class);
                        if (excelField != null && excelField.name().equals(colName)) {
                            String filedName = fields[k].getName();
                            String methodName = "get" + filedName.substring(0,1).toUpperCase() + filedName.substring(1);
                            Method method =  clazz.getDeclaredMethod(methodName);
                            Object methodReturnValue = method.invoke(dataBody.get(i));
                            setCellValue(fields[k].getType(), methodReturnValue, cellBody);
                            break;
                        }
                    }
                }
            }
        }
        //写出
        workbook.write(os);
        System.out.println("导出数据写进文件耗时：【" + (System.currentTimeMillis() - startTime) + "】毫秒");
        workbook.close();
    }

    private static void setCellValue(Class<?> typeClass, Object value, Cell cell) {
        if (typeClass == BigDecimal.class || typeClass == Double.class || typeClass == Float.class) {
            cell.setCellValue(value == null ? 0 : Double.valueOf(
                    new BigDecimal(value.toString()).setScale(3, BigDecimal.ROUND_HALF_UP).toString())
            );
        } else if (typeClass == Integer.class || typeClass == Long.class) {
            cell.setCellValue(value == null ? 0 : Long.valueOf(value.toString()));
        } else if (typeClass == Date.class) {
            if (value == null) {
                cell.setCellValue("--");
                return;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            cell.setCellValue(sdf.format(value));
        } else {
            if (value == null) {
                cell.setCellValue("--");
                return;
            }
            cell.setCellValue(value.toString());
        }
    }

    /**
     * 根据类型转义对象
     * @param typeClass
     * @param value
     * @return
     */
    private static Object getClassTypeValue(Class<?> typeClass, Object value){
        if (typeClass == BigDecimal.class) {//BigDecimal
            if(null == value){
                return new BigDecimal(0);
            }
            return new BigDecimal(value.toString());
        } else if (typeClass == Date.class) {//Date
            if(null == value){
                return null;
            }
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(value);
        } else if (typeClass == Long.class) {//Long
            if(null == value || "".equals(value)){
                return null;
            }
            return Long.valueOf(value.toString());
        } else if (typeClass == Float.class) {//Float
            if(null == value || "".equals(value)){
                return null;
            }
            return Float.valueOf(value.toString());
        } else if (typeClass == Double.class) {//Double
            if(null == value || "".equals(value)){
                return null;
            }
            return Double.valueOf(value.toString());
        } else if (typeClass == Integer.class) {//Integer
            if(null == value || "".equals(value)){
                return null;
            }
            return Integer.valueOf(value.toString());
        } else if (typeClass == Boolean.class) {//Boolean
            if (null == value){
                return null;
            }
            return Boolean.valueOf(value.toString());
        }

        else {
            return typeClass.cast(value);
        }
    }
}
