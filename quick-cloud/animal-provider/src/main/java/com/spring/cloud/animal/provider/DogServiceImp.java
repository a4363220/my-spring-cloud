package com.spring.cloud.animal.provider;

import com.spring.cloud.animal.api.DogService;
import com.spring.cloud.animal.entity.Dog;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *
 *    狗实现类
 * @author 小谷
 * @Date 2020/1/16 16:41
 */
@Service
public class DogServiceImp implements DogService {

    // 实现查询所有狗
    @Override
    public List<Dog> queryAllDog() {
        System.out.println("进入动物提供者、查询所有狗 业务实现层");
        Dog dog = new Dog();
        dog.setDogName("金毛");
        List<Dog> list = new ArrayList<>();
        list.add(dog);
        return list;
    }
}
