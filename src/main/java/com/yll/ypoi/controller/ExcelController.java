package com.yll.ypoi.controller;

import com.yll.ypoi.pojo.Teacher;
import com.yll.ypoi.service.ExcelExportService;
import com.yll.ypoi.utils.ExcelImportUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @className: ExcelController
 * @description: 文档导出导入处理模块
 * @author: akira
 * @date: Created in 2018/11/14 23:37
 * @modify by: akira
 * @version: V1.0
 */
@RestController
@Slf4j
public class ExcelController {

    @Autowired
    ExcelExportService excelExportService;
    @Autowired
    ExcelImportUtil excelImportUtil;

    @GetMapping("/download")
    public void exportExcelTmp(HttpServletResponse response) {
        ServletOutputStream servletOutputStream = null;
        try {
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.addHeader("charset", "utf-8");
            response.addHeader("Pragma", "no-cache");
            String encodeName = URLEncoder.encode("测试.xls", StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename=\"" + encodeName + "\"; filename*=utf-8''" + encodeName);
            List<Object> objects = new ArrayList<>();
            Teacher teacher = new Teacher();
            objects.add(teacher);
            HSSFWorkbook workbook = excelExportService.createWorkbook(objects);
            workbook.write(response.getOutputStream());
            servletOutputStream = response.getOutputStream();
            response.flushBuffer();
        } catch (Exception e) {
            log.error("下载模板失败", e);
            e.printStackTrace();
        } finally {
            try {
                if (servletOutputStream != null) {
                    servletOutputStream.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @GetMapping("/importExcel")
    public String importExcelTmp(MultipartFile file) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook(new POIFSFileSystem(file.getInputStream()));
        // 获取sheet数
        int numberOfSheets = workbook.getNumberOfSheets();
        for (int i = 0; i < numberOfSheets; i++) {
            List<Map<String, Object>> dataIn = new ArrayList<>();
            HSSFSheet sheetAt = workbook.getSheetAt(i);
            // 获取sheet的行数
            int physicalNumberOfRows = sheetAt.getPhysicalNumberOfRows();
            Teacher teacher;
            for (int j = 0; j < physicalNumberOfRows; j++) {
                if (j == 0) {
                    // 第一行是表头
                    continue;
                }
                // 获取每一行
                HSSFRow row = sheetAt.getRow(j);
                teacher = new Teacher();
                Field[] fields = teacher.getClass().getFields();
                Map<String, Object> objectMap = excelImportUtil.packData(fields, row);
                dataIn.add(objectMap);
            }
            System.out.println("保存数据到数据库=======" + dataIn);
        }
        return "SUCCESS";
    }

}
