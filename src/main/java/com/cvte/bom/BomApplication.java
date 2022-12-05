package com.cvte.bom;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author: jonil
 * @Date: 2022/12/05
 * @Description: springboot启动类
 */
@SpringBootApplication
@MapperScan("com.cvte.bom.mapper")
public class BomApplication {

    public static void main(String[] args) {
        SpringApplication.run(BomApplication.class, args);
    }

}
