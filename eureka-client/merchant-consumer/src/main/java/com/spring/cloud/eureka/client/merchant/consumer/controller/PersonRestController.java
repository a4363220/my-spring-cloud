package com.spring.cloud.eureka.client.merchant.consumer.controller;

import com.spring.cloud.eureka.client.merchant.api.domain.Person;
import com.spring.cloud.eureka.client.merchant.api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 22:50
 */
@RestController
public class PersonRestController implements PersonService {

    private final PersonService personService;

    @Autowired
    public PersonRestController(PersonService personService) {
        this.personService = personService;
    }

    @Override
    public Person add(@RequestBody Person person) {
        return personService.add(person);
    }

    @Override
    public List<Person> queryAll() {
        System.out.println("进入人查询");
        return personService.queryAll();
    }
}
