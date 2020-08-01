package com.spring.cloud.zk;

import com.spring.cloud.zk.client.EnableRestClient;
import com.spring.cloud.zk.client.SayingService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/20 20:28
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling // 激活任务调度
@EnableFeignClients// 激活客户端 可在这里自定名称，那么在接口中就可以不写死
@EnableRestClient(clients = SayingService.class) // 声明式指定 自定义实现
public class ZkClientStart {

    public static void main(String[] args) {
        SpringApplication.run(ZkClientStart.class, args);
    }
}
