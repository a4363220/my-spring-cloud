package com.spring.cloud.animal.consumer;

import com.spring.cloud.animal.api.DogService;
import com.spring.cloud.animal.entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 描述：
 *     狗消费者使用restTemplate代理的方式进行处理
 * @author 小谷
 * @Date 2020/1/16 16:48
 */
@Service
public class DogRestProxy implements DogService {

    // 提供服务者提供应用节点
    private static final String provider = "http://animal-provider";

    // 注入restTemplate
    @Autowired
    private RestTemplate restTemplate;


    // 实现查询狗
    @Override
    public List<Dog> queryAllDog() {
        System.out.println("进入消费者、查询所有狗 rest代理层 ");
        return restTemplate.getForObject(provider+"/dog/queryAllDog",List.class);
    }
}
