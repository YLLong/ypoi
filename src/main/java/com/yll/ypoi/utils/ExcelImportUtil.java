package com.yll.ypoi.utils;

import com.yll.ypoi.annotation.Excel;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: ExcelImportUtil
 * @description: 文档导入工具类
 * @author: akira
 * @date: Created in 2018/11/15 21:09
 * @modify by: akira
 * @version: V1.0
 */
@Component
@Slf4j
public class ExcelImportUtil {

    /**
     * @title: getFilds
     * @description: 获取类中有自定义注解的属性
     * @author: akira
     * @date: Created in 2018/11/17 15:11
     * @param clazz
     * @throws:
     * @return: java.util.List<java.lang.reflect.Field>
     */
    public List<Field> getFilds(Class<?> clazz) {
        List<Field> fieldList = new ArrayList<>();
        // 获取类的所有属性
        Field[] declaredFields = clazz.getDeclaredFields();
        // 出掉没有 excel 注解的属性
        for (int i = 0; i < declaredFields.length; i++) {
            Field declaredField = declaredFields[i];
            if (declaredField.isAnnotationPresent(Excel.class)) {
                fieldList.add(declaredField);
            }
        }
        return fieldList;
    }

    /**
     * @title: packData
     * @description: 封装入库的单个实体
     * @author: akira
     * @date: Created in 2018/11/15 21:17
     * @param clazz 对象
     * @param row excel 行
     * @throws:
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */
    public Map<String, Object> packData(Class<?> clazz, Row row) {
        Map<String, Object> dataMap = new HashMap<>();
        List<Field> filds = getFilds(clazz);
        for (int i = 0; i < filds.size(); i++) {
            Field field =  filds.get(i);
            dataMap.put(field.getName(), getValue(field, row.getCell(i)));
        }
        return dataMap;
    }

    /**
     * @title: getValue
     * @description: 获取属性同类型值
     * @author: akira
     * @date: Created in 2018/11/15 22:05
     * @param field
     * @param cell
     * @throws:
     * @return: T
     */
    public <T> T getValue(Field field, Cell cell) {
        String cellValue = "";
        switch (cell.getCellType()) {
            case NUMERIC:
                if (org.apache.poi.ss.usermodel.DateUtil.isCellDateFormatted(cell)) {
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    cellValue = sdf.format(org.apache.poi.ss.usermodel.DateUtil.getJavaDate(cell.getNumericCellValue())).toString();
                } else {
                    DataFormatter dataFormatter = new DataFormatter();
                    cellValue = dataFormatter.formatCellValue(cell);
                }
                break;
            case BLANK:
                cellValue = "";
                break;
            case STRING:
                cellValue = cell.getStringCellValue();
                break;
            case BOOLEAN:
                cellValue += cell.getBooleanCellValue();
                break;
            case FORMULA:
                cellValue += cell.getCellFormula();
                break;
            case ERROR:
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";

        }
        if (field.getType() == Integer.class) {
            return (T) Integer.valueOf(cellValue);
        }
        if (field.getType() == Boolean.class) {
            return (T) Boolean.valueOf(cellValue);
        }
        if (field.getType() == Long.class) {
            return (T) Long.valueOf(cellValue);
        }
        if (field.getType() == Double.class) {
            return (T) Double.valueOf(cellValue);
        }
        if (field.getType() == Float.class) {
            return (T) Float.valueOf(cellValue);
        }
        if (field.getType() == Byte.class) {
            return (T) Byte.valueOf(cellValue);
        }
        return (T) cellValue;
    }

}
