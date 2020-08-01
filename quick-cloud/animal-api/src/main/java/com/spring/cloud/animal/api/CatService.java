package com.spring.cloud.animal.api;

import com.spring.cloud.animal.entity.Cat;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * 描述：
 *     猫服务接口,通过feign
 * @author 小谷
 * @Date 2020/1/16 17:19
 */
@FeignClient(value="animal-provider")// 服务提供方应用名称
public interface CatService {

    // 查询所有猫
    @GetMapping(value="/cat/queryAllCat")
    List<Cat>  queryAllCat();
}
