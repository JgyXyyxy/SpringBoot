package com.sdu.bean;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;

/**
 * Created by J on 2017/3/4.
 */
@Entity
@Table(name = "manager_user")
public class ManagerUser {

    @Id
    @NotEmpty(message = "用户名不可为空")
    private String username;

    @NotEmpty(message = "密码不可为空")
    @Length(min = 6,max = 18,message = "密码长度为6到18位")
    private String password;

    public ManagerUser() {
    }

    public ManagerUser(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
