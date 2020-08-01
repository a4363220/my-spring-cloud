package com.spring.cloud.eureka.client.merchant.consumer.proxy;

import com.spring.cloud.eureka.client.merchant.api.domain.Merchant;
import com.spring.cloud.eureka.client.merchant.api.service.MerchantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 17:29
 */
@Service
public class MerchantServiceProxy implements MerchantService {

    // 提供者应用节点
    private static final String PROVIDER = "http://merchant-service-provider";

    /**
     * 通过restapi代理到服务器提供者
     * */
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Merchant addMerchant(Merchant merchant) {
        System.out.println("代理新增："+merchant);
        return restTemplate.postForObject(PROVIDER+"/merchant/add",merchant,Merchant.class);
    }

    @Override
    public List<Merchant> queryMerchantAll() {
        System.out.println("代理查询");
        return restTemplate.getForObject(PROVIDER+"/merchant/queryAll",List.class);
    }
}
