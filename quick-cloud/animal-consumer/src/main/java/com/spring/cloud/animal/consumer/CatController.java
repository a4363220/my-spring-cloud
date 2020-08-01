package com.spring.cloud.animal.consumer;

import com.spring.cloud.animal.api.CatService;
import com.spring.cloud.animal.entity.Cat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 描述：
 *    猫消费者控制层 使用feign方式
 * @author 小谷
 * @Date 2020/1/16 17:31
 */
@RestController
public class CatController implements CatService{

    private final CatService catService;

    @Autowired
    public CatController(CatService catService) {
        this.catService = catService;
    }


    // 查询所有猫
    @Override
    public List<Cat> queryAllCat() {
        System.out.println("进入消费者、查询所有猫、通过feign方式");
        return catService.queryAllCat();
    }
}
