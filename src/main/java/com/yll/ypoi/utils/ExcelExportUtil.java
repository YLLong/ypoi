package com.yll.ypoi.utils;

import com.yll.ypoi.annotation.Excel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

/**
 * @className: ExcelExportUtil
 * @description: 表格导出模板工具
 * @author: akira
 * @date: Created in 2018/11/14 22:02
 * @modify by: akira
 * @version: V1.0
 */
@Component
@Slf4j
public class ExcelExportUtil {

    /**
     * @title: getExcelHeader
     * @description: 获取表头
     * @author: akira
     * @date: Created in 2018/11/14 22:52
     * @param obj 属性具有自定义注解的实体对象
     * @throws:
     * @return: java.util.List<java.lang.String>
     */
    public List<String> getExcelHeader(Object obj) {
        // 存储excel头
        List<String> headerList = new ArrayList<>();
        // 获取该对象所有属性
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(Excel.class)) {
                Excel excel = field.getAnnotation(Excel.class);
                headerList.add(excel.name());
            }
        }
        System.out.println(headerList);
        log.info("生成的头" + headerList);
        return headerList;
    }

}
