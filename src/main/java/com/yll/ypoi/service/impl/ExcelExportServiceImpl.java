package com.yll.ypoi.service.impl;

import com.yll.ypoi.service.ExcelExportService;
import com.yll.ypoi.utils.ExcelExportUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @className: ExcelExportServiceImpl
 * @description: excel操作服务实现类
 * @author: akira
 * @date: Created in 2018/11/14 22:49
 * @modify by: akira
 * @version: V1.0
 */
@Service
public class ExcelExportServiceImpl implements ExcelExportService {

    @Autowired
    ExcelExportUtil excelExportUtil;

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
    @Override
    public List<HSSFSheet> createSheet(HSSFWorkbook workbook, List<Object> objects) {
        // sheet list
        List<HSSFSheet> sheets = new ArrayList<>();
        for (Object obj : objects) {
            HSSFSheet sheet = workbook.createSheet();
            // 获取表头
            List<String> excelHeader = excelExportUtil.getExcelHeader(obj);
            // 创建第一行（表头）
            HSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < excelHeader.size(); i++) {
                String title =  excelHeader.get(i);
                // 第一行 单元格
                HSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(title);
            }
            sheets.add(sheet);
        }
        return sheets;
    }

    /**
     * @param objects
     * @title: createWorkbook
     * @description: 创建excel文档
     * @author: akira
     * @date: Created in 2018/11/14 23:26
     * @throws:
     * @return: org.apache.poi.hssf.usermodel.HSSFWorkbook
     */
    @Override
    public HSSFWorkbook createWorkbook(List<Object> objects) {
        HSSFWorkbook workbook = new HSSFWorkbook();
        createSheet(workbook, objects);
        return workbook;
    }

}
