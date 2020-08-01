package com.spring.cloud.animal.consumer;

import com.spring.cloud.animal.entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：
 *    狗消费者控制层
 * @author 小谷
 * @Date 2020/1/16 16:43
 */
@RestController
public class DogController {


    @Autowired
    DogRestProxy dogRestProxy;

    // 查询所有狗
    @GetMapping("/dog/queryAllDog")
    public List<Dog>  qeuryAllDog(){
        System.out.println("进入动物提供者、查询所有狗 rest 控制层");
        return dogRestProxy.queryAllDog();
    }
}
