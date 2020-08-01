package com.spring.cloud.zk.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/22 18:41
 */
@FeignClient(name = "spring-cloud-zk-server")// 指定应用名称
public interface SayingService {

    @GetMapping("/say")
    String say(@RequestParam String message);
}
