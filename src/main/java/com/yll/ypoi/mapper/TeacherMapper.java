package com.yll.ypoi.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @className: TeacherMapper
 * @description: 教师类入库接口
 * @author: akira
 * @date: Created in 2018/11/17 17:26
 * @modify by: akira
 * @version: V1.0
 */
public interface TeacherMapper {

    Integer insertExcelData(@Param("mapList") List<Map<String, Object>> mapList);

}
