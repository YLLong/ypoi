package com.yll.ypoi.pojo;

import com.yll.ypoi.annotation.Excel;
import com.yll.ypoi.annotation.Sheet;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: Teacher
 * @description: 老师实体类
 * @author: yys1778
 * @date: Created in 2018/11/15 9:26
 * @modify by: yys1778
 * @version: V1.0
 */
@Data
@Sheet(name = "教师表")
public class Teacher implements Serializable {

    private Integer id;

    @Excel(name = "老师姓名")
    private String teaName;

    @Excel(name = "工号")
    private String teaId;

    @Excel(name = "老师年龄")
    private Integer teaAge;

}
