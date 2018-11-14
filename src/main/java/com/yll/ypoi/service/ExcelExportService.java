package com.yll.ypoi.service;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * @className: ExcelExportService
 * @description: 文档导出服务接口
 * @author: akira
 * @date: Created in 2018/11/14 22:41
 * @modify by: akira
 * @version: V1.0
 */
public interface ExcelExportService {

    /**
     * @title: createSheet
     * @description: 创建sheet
     * @author: akira
     * @date: Created in 2018/11/14 23:22
     * @param workbook excel文档对象
     * @param objects 表头对象list
     * @throws:
     * @return: java.util.List<org.apache.poi.hssf.usermodel.HSSFSheet>
     */
    public List<HSSFSheet> createSheet(HSSFWorkbook workbook, List<Object> objects);

    /**
     * @title: createWorkbook
     * @description: 创建excel文档
     * @author: akira
     * @date: Created in 2018/11/14 23:26
     * @param objects
     * @throws:
     * @return: org.apache.poi.hssf.usermodel.HSSFWorkbook
     */
    public HSSFWorkbook createWorkbook(List<Object> objects);

}
