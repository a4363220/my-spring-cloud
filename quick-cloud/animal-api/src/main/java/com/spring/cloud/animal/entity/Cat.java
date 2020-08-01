package com.spring.cloud.animal.entity;

/**
 * 描述：
 *     猫传参实体类
 * @author 小谷
 * @Date 2020/1/16 17:19
 */
public class Cat {

    private String catName;

    public Cat() {
    }

    public Cat(String catName) {
        this.catName = catName;
    }

    public String getCatName() {
        return catName;
    }

    public void setCatName(String catName) {
        this.catName = catName;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "catName='" + catName + '\'' +
                '}';
    }
}
