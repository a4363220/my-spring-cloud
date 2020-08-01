package com.spring.cloud.eureka.client.merchant.api.domain;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/12 22:44
 */
public class Person {

    private String personName;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    @Override
    public String toString() {
        return "Person{" +
                "personName='" + personName + '\'' +
                '}';
    }
}
