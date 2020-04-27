package com.codegym;

import java.io.*;

public class Catalog implements Serializable{
    private static int counter = 1;
    private int id = counter++;
    private String numberPhone;
    private String group;
    private String fullName;
    private String sex;
    private String address;
    private String birthday;
    private String email;

    public Catalog(String numberPhone, String group, String fullName, String sex, String address, String birthday, String email) {
        this.numberPhone = numberPhone;
        this.group = group;
        this.fullName = fullName;
        this.sex = sex;
        this.address = address;
        this.birthday = birthday;
        this.email = email;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNumberPhone() {
        return this.numberPhone;
    }

    public void setNumberPhone(String numberPhone) {
        this.numberPhone = numberPhone;
    }

    public String getGroup() {
        return this.group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getFullName() {
        return this.fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getSex() {
        return this.sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBirthday() {
        return this.birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
