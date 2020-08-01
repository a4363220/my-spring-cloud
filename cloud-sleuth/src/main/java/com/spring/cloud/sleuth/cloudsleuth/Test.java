package com.spring.cloud.sleuth.cloudsleuth;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/15 21:57
 */
@RestController
public class Test {


    private final Logger log = LoggerFactory.getLogger(Test.class);

    @GetMapping("test")
    public String test(){
        log.info("测试");
        return "测试";
    }
}
