package com.somnus.androidmvpdemo.model;

/**
 * @date： 2016/9/26.
 * @FileName: com.somnus.androidmvpdemo.model.IUserModel.java
 * @author: Somnus
 * @Description:
 */
public interface IUserModel {
    static final String TAG = "IUserModel";

    public void login(String userName,String userPassWord,onUserLoginInterface userLoginInterface);
}
