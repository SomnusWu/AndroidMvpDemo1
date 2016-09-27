package com.somnus.androidmvpdemo.view;

import com.somnus.androidmvpdemo.bean.User;

/**
 * @date： 2016/9/26.
 * @FileName: com.somnus.androidmvpdemo.view.IUserLoginView.java
 * @author: Somnus
 * @Description:
 */
public interface IUserLoginView {
    static final String TAG = "IUserLoginView";

    String getUserName();

    String getPassWord();

    void showLoading();

    void hideLoading();

    void showFailedError();

    void toMainActivity(User user);

}
