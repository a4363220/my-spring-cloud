package com.spring.zk.server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/20 20:34
 */
@RestController
public class ClientController {

    @Value("${spring.application.name}")// 当前应用名称
    private String currentServiceName;


    @GetMapping("/say")
    public String say(@RequestParam("message") String message) {
        System.out.println("ServerController接收到消息 - say：" + message);
        return "Hello," + message;
    }
}
