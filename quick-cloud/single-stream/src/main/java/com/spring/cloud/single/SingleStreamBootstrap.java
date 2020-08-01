package com.spring.cloud.single;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 描述：
 *    单独整合kafka
 *    标准api
 *    spirngboot
 *    springcloud
 *    集成方式
 * @author 小谷
 * @Date 2020/1/16 17:57
 */
@SpringBootApplication
public class SingleStreamBootstrap {

    public static void main(String[] args) {
        SpringApplication.run(SingleStreamBootstrap.class,args);
    }
}
