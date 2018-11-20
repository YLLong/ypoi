package com.yll.ypoi.service;

import org.apache.poi.ss.usermodel.Sheet;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @className: ExcelImportService
 * @description: excel文档导入服务接口
 * @author: akira
 * @date: Created in 2018/11/20 22:11
 * @modify by: akira
 * @version: V1.0
 */
public interface ExcelImportService {

    /**
     * @title: getDataList
     * @description: 解析sheet的数据
     * @author: akira
     * @date: Created in 2018/11/20 23:41
     * @param sheet
     * @param clazz
     * @throws:
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String,Object>> getDataListSheet(Sheet sheet, Class<?> clazz);

    /**
     * @title: getDataListSheets
     * @description: 文档有多个sheet的情况 数据解析
     * @author: akira
     * @date: Created in 2018/11/20 23:34
     * @param in 文件输入流
     * @param clazzs 解析对象
     * @throws:
     * @return: java.util.Map<java.lang.String,java.util.List<?>>
     */
    Map<String, List<?>> getDataListInputStream(InputStream in, List<Class<?>> clazzs) throws IOException;

    /**
     * @title: getDataListInputStream
     * @description: 文档只有一个sheet的情况
     * @author: akira
     * @date: Created in 2018/11/20 23:45
     * @param in
     * @param clazz
     * @throws:
     * @return: java.util.List<java.util.Map<java.lang.String,java.lang.Object>>
     */
    List<Map<String,Object>> getDataListInputStream(InputStream in, Class<?> clazz) throws IOException;

}
