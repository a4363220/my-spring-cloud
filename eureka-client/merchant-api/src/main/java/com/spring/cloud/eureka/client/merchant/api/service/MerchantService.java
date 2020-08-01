package com.spring.cloud.eureka.client.merchant.api.service;

import com.spring.cloud.eureka.client.merchant.api.domain.Merchant;

import java.util.List;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 15:27
 */
public interface MerchantService {

    // 添加商户
    Merchant addMerchant(Merchant merchant);

    // 查询商户
    List<Merchant>  queryMerchantAll();
}
