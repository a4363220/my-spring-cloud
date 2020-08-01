package com.spring.cloud.zk.client;

import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.ImportResource;

import java.lang.annotation.*;

/**
 * 描述：
 * 不用填写项目名称
 *
 * @author 小谷
 * @Date 2020/3/22 19:35
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(RestClientsRegistrar.class)
public @interface RestClient {

    /**
     * rest 服务应用名称
     */
    String name();

}
