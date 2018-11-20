package com.yll.ypoi.service.impl;

import com.yll.ypoi.service.ExcelImportService;
import com.yll.ypoi.utils.ExcelImportUtil;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @className: ExcelImportServiceImpl
 * @description: excel文档导入服务实现类
 * @author: akira
 * @date: Created in 2018/11/20 22:12
 * @modify by: akira
 * @version: V1.0
 */
@Service
public class ExcelImportServiceImpl implements ExcelImportService {

    @Autowired
    ExcelImportUtil excelImportUtil;

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
    @Override
    public List<Map<String, Object>> getDataListSheet(Sheet sheet, Class<?> clazz) {
        // sheet的行数
        int numberOfRows = sheet.getPhysicalNumberOfRows();
        List<Map<String, Object>> dataIn = new ArrayList<>();
        for (int i = 0; i < numberOfRows; i++) {
            if (i== 0) {
                continue;
            }
            // 获取每一个行
            Row row = sheet.getRow(i);
            Map<String, Object> objectMap = excelImportUtil.packData(clazz, row);
            dataIn.add(objectMap);
        }
        return dataIn;
    }

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
    @Override
    public Map<String, List<?>> getDataListInputStream(InputStream in, List<Class<?>> clazzs) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(in));
        Map<String, List<?>> map=new HashMap<>();
        // 获取sheet数
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            List<Map<String, Object>> dataIn = new ArrayList<>();
            //获取每个sheet
            HSSFSheet sheetAt = workbook.getSheetAt(i);
            // 返回每个sheet数据
            List<Map<String, Object>> dataListSheet = getDataListSheet(sheetAt, clazzs.get(i));
            // 获取sheet对应的类名
            String className = clazzs.get(i).getName();
            map.put(className,dataListSheet);
        }
        return map;
    }

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
    @Override
    public List<Map<String, Object>> getDataListInputStream(InputStream in, Class<?> clazz) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(in));
        // 获取第一个sheet
        HSSFSheet sheetAt = workbook.getSheetAt(0);
        return getDataListSheet(sheetAt, clazz);
    }
}
