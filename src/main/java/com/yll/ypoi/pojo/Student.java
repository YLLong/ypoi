package com.yll.ypoi.pojo;

import com.yll.ypoi.annotation.Excel;
import com.yll.ypoi.annotation.Sheet;
import lombok.Data;

import java.io.Serializable;

/**
 * @className: Student
 * @description: 学生实体类
 * @author: yys1778
 * @date: Created in 2018/11/14 15:43
 * @modify by: yys1778$
 * @version: V1.0
 */
@Data
@Sheet(name = "学生表")
public class Student implements Serializable {

    @Excel(name = "id")
    private Integer id;

    @Excel(name = "学生姓名")
    private String stuName;

    @Excel(name = "学号")
    private String stuId;

    @Excel(name = "学生年龄")
    private String stuAge;

}
