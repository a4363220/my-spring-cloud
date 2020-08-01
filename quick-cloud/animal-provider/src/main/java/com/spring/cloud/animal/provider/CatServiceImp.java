package com.spring.cloud.animal.provider;

import com.spring.cloud.animal.entity.Cat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * 描述：
 *     猫服务实现,使用feign方式、可以不实现接口,保持相同的api路径即可
 * @author 小谷
 * @Date 2020/1/16 17:26
 */
@RestController
public class CatServiceImp {

    // 实现查询所有猫的实现
    @GetMapping("/cat/queryAllCat")
    public List<Cat> queryAllCat(){
        System.out.println("进入服务提供者、查询所有猫、通过feign方式");
        Cat cat = new Cat();
        cat.setCatName("波斯猫");
        List<Cat> list = new ArrayList<>();
        list.add(cat);
        return list;
    }
}
