package com.spring.cloud.eureka.client.merchant.api.domain;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 15:26
 */
public class Merchant {

   private Integer id;

   private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Merchant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
