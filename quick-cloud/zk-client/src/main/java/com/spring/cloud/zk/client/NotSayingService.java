package com.spring.cloud.zk.client;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：
 * 实现无需指定服务名称
 *
 * @author 小谷
 * @Date 2020/3/22 19:38
 */
@RestClient(name = "spring-cloud-zk-server")
public interface NotSayingService {

    @GetMapping("/say")
    String say(@RequestParam String message);
}
