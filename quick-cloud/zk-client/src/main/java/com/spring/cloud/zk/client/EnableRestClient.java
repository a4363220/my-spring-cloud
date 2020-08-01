package com.spring.cloud.zk.client;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/3/22 19:56
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Import(RestClientsRegistrar.class)
public @interface EnableRestClient {

    /**
     * 指定RestClient接口
     * */
    Class<?>[] clients() default {};
}
