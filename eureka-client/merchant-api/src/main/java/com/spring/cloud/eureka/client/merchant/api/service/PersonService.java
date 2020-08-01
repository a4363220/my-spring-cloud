package com.spring.cloud.eureka.client.merchant.api.service;

import com.spring.cloud.eureka.client.merchant.api.domain.Person;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

/**
 * 描述：
 * 使用feign的方式构建
 *
 * @author 小谷
 * @Date 2020/1/12 19:53
 */
@FeignClient(value = "merchant-service-provider")//服务提供方应用的名称
public interface PersonService {

    @PostMapping(value = "/person/add")
    Person add(Person person);

    @GetMapping(value = "/person/queryAll")
    List<Person> queryAll();
}
