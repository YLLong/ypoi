package com.yll.ypoi.utils;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFDataFormatter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
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
     * @title: packData
     * @description: 封装入库的单个实体
     * @author: akira
     * @date: Created in 2018/11/15 21:17
     * @param fields 对象属性数组
     * @param row    excel 行
     * @throws:
     * @return: java.util.Map<java.lang.String, java.lang.Object>
     */

    public Map<String, Object> packData(Field[] fields, Row row) {
        if (null == fields || fields.length < 0) {
            log.error("对象属性不存在");
            return null;
        }
        Map<String, Object> dataMap = new HashMap<>();
        for (int i = 0; i < fields.length; i++) {
            dataMap.put(fields[i].getName(), getValue(fields[i], row.getCell(i)));
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
        HSSFDataFormatter hssfDataFormatter = new HSSFDataFormatter();
        String cellValue = hssfDataFormatter.formatCellValue(cell);
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
