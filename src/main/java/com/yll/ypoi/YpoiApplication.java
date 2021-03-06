package com.yll.ypoi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @className: YpoiApplication
 * @description: //TODO
 * @author: yys1778
 * @date: Created in 2018/11/14 15:43
 * @modify by: yys1778
 * @version: V1.0
 */
@SpringBootApplication
@MapperScan("com.yll.ypoi.mapper")
public class YpoiApplication {

    public static void main(String[] args) {
        SpringApplication.run(YpoiApplication.class, args);
    }

}
