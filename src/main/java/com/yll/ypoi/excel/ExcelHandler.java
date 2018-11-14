package com.yll.ypoi.excel;

import com.yll.ypoi.annotation.Excel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @className: ExcelHandler
 * @description: excel处理类
 * @author: yys1778
 * @date: Created in 2018/11/14 17:00
 * @modify by: yys1778
 * @version: V1.0
 */
@Component
@Slf4j
public class ExcelHandler {

    /**
     * @title: getExcelHeader
     * @description: 根据传入的注解对象获取该对象注解中的name
     * @author: yys1778
     * @date: Created in 2018/11/14 17:02
     * @param obj 具有excel 注解类型的类
     * @throws:
     * @return: java.util.Map<java.lang.String,java.lang.String>
     */
    public Map<String, String> getExcelHeader(Object obj) {
        // 存储excel头 field：name值
        Map<String, String> headerMap = new HashMap<>();
        // 获取该对象所有属性
        Field[] fields = obj.getClass().getDeclaredFields();
        for (int i = 0; i < fields.length; i++) {
            Field field = fields[i];
            if (field.isAnnotationPresent(Excel.class)) {
                Excel excel = field.getAnnotation(Excel.class);
                headerMap.put(field.getName(), excel.name());
            }
        }
        System.out.println(headerMap);
        log.info("生成的头" + headerMap);
        return headerMap;
    }

}
