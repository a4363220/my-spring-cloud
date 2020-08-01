package com.spring.cloud.animal.api;

import com.spring.cloud.animal.entity.Dog;

import java.util.List;

/**
 * 描述：
 *     狗服务接口,使用restTemplate
 * @author 小谷
 * @Date 2020/1/16 16:38
 */
public interface DogService {

    // 查询所有狗
    List<Dog> queryAllDog();
}
