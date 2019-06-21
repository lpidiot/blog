package com.idiot.blog.entity;

import com.idiot.blog.common.CommonEntity;

import javax.persistence.Entity;

/**
 * @ClassName:CommonUser
 * @Description:TODO
 * @Version:1.0
 **/
@Entity
public class TbUser extends CommonEntity {
    private String username;
    private String name;
    private String password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
}
