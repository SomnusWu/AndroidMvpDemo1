package com.somnus.androidmvpdemo.bean;

/**
 * @date： 2016/4/27.
 * @FileName: com.somnus.mvp.androidmvpdemo.bean.User.java
 * @author: Somnus
 * @Description: user的一个实体类
 */
public class User {

    private String username;
    private String password;

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

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
