package com.hnie.forum.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by Administrator on 2016/10/30.
 */
public class User implements Serializable {

    private Integer id;
    private String name;
    private String head;
    private String mail;
    private String birthday;
    private String sex;
    private String phonenum;

    private String password;
    private String isAdmin;

    private Integer postsNum;//发表帖子数

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(String isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Integer getPostsNum() {
        return postsNum;
    }

    public void setPostsNum(Integer postsNum) {
        this.postsNum = postsNum;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", head='" + head + '\'' +
                ", password='" + password + '\'' +
                ", mail='" + mail + '\'' +
                ", birthday='" + birthday + '\'' +
                ", sex='" + sex + '\'' +
                ", phonenum='" + phonenum + '\'' +
                ", isAdmin='" + isAdmin + '\'' +
                '}';
    }
}
