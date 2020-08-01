package com.spring.cloud.animal.entity;

/**
 * 描述：
 *
 * @author 小谷
 * @Date 2020/1/16 16:39
 */
public class Dog {

    // 狗名称
    private String dogName;

    public Dog() {
    }

    public Dog(String dogName) {
        this.dogName = dogName;
    }

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    @Override
    public String toString() {
        return "Dog{" +
                "dogName='" + dogName + '\'' +
                '}';
    }
}
