package com.spring.cloud.eureka.client.merchant.provider.service;

import com.spring.cloud.eureka.client.merchant.api.domain.Merchant;
import com.spring.cloud.eureka.client.merchant.api.service.MerchantService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 15:40
 */
@Service
public class MerchantServiceImp implements MerchantService {


    @Override
    public Merchant addMerchant(Merchant merchant) {
        System.out.println("进入添加服务");
        return merchant;
    }

    @Override
    public List<Merchant> queryMerchantAll() {
        System.out.println("进入查询服务");
        Merchant merchant = new Merchant();
        merchant.setId(1);
        merchant.setName("你好");
        List<Merchant> list = new ArrayList<>();
        list.add(merchant);
        return list;
    }
}
