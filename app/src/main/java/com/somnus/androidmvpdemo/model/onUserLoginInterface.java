package com.somnus.androidmvpdemo.model;

import com.somnus.androidmvpdemo.bean.User;

/**
 * @date： 2016/9/26.
 * @FileName: com.somnus.androidmvpdemo.model.onUserLoginInterface.java
 * @author: Somnus
 * @Description:
 */
public interface onUserLoginInterface {
    static final String TAG = "onUserLoginInterface";

    void loginSuccess(User user);//登陆成功
    void loginFailed();//登陆失败

}
