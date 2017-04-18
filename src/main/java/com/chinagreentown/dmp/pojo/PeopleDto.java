package com.chinagreentown.dmp.pojo;

/**
 * Created by admin on 2017/4/8.
 */

import com.fasterxml.jackson.databind.annotation.JsonSerialize;


@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class PeopleDto {
    private String phone;
    private String age;

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "PeopleDto{" +
                "phone='" + phone + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}