package com.spring.cloud.eureka.client.merchant.provider.service;

import com.spring.cloud.eureka.client.merchant.api.domain.Person;
import com.spring.cloud.eureka.client.merchant.api.service.PersonService;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *    不一定非要实现接口，不实现的写法与service的写法一致
 * @author 小谷
 * @Date 2020/1/12 22:45
 */
@RestController
public class PersonServiceImp {

    @PostMapping(value = "/person/add")
    public Person add(@RequestBody Person person) {
        System.out.println("进入添加人提供者："+person);
        return person;
    }

    @GetMapping(value = "/person/queryAll")
    public List<Person> queryAll() {
        System.out.println("进入查询人提供者：");
        Person person = new Person();
        person.setPersonName("杨过");
        List<Person> list = new ArrayList<>();
        list.add(person);
        return list;
    }
}
