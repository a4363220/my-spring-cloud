package com.spring.cloud.animal.provider;

import com.spring.cloud.animal.api.DogService;
import com.spring.cloud.animal.entity.Dog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：
 *    服务提供者控制层
 * @author 小谷
 * @Date 2020/1/16 16:55
 */
@RestController
public class DogController {

    @Autowired
    DogService dogService;

    // 查询所有狗
    @GetMapping("/dog/queryAllDog")
    public List<Dog> qeuryAllDog(){
        return dogService.queryAllDog();
    }
}
