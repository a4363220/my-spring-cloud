package com.spring.cloud;

import com.spring.cloud.gateway.ZookeeperLoadBalance;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/23 16:33
 */
@SpringBootApplication
@EnableDiscoveryClient
@ServletComponentScan(basePackages = "com.spring.cloud.servlet")// spring默认不会加载servlet组件
@EnableScheduling
public class GateWayStart {

    public static void main(String[] args) {
        SpringApplication.run(GateWayStart.class, args);
    }

    @Bean
    public ZookeeperLoadBalance zookeeperLoadBalancer(DiscoveryClient discoveryClient) {
        return new ZookeeperLoadBalance(discoveryClient);
    }

}
