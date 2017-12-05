package com.ken.litepaltest.javabean;

import org.litepal.crud.DataSupport;

/**
 * Created by Administrator on 2017/12/5.
 */

public class Human extends DataSupport {
    private int id;
    private String name;
    private String sex;
    private int age;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
    public Human(String name,String sex,int age){
        this.name = name;
        this.sex = sex;
        this.age = age;
    }
    public Human(){

    }
}
