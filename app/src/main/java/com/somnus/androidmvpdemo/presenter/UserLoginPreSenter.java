package com.somnus.androidmvpdemo.presenter;

import android.os.Handler;

import com.somnus.androidmvpdemo.bean.User;
import com.somnus.androidmvpdemo.model.IUserModel;
import com.somnus.androidmvpdemo.model.UserModel;
import com.somnus.androidmvpdemo.model.onUserLoginInterface;
import com.somnus.androidmvpdemo.view.IUserLoginView;

/**
 * @date： 2016/9/26.
 * @FileName: com.somnus.androidmvpdemo.presenter.UserLoginPreSenter.java
 * @author: Somnus
 * @Description:
 */

public class UserLoginPreSenter {

    private IUserModel mIUserModel;
    private IUserLoginView mIUserLoginView;
    private Handler mHandler = new Handler();


    public UserLoginPreSenter(IUserLoginView IUserLoginView) {
        mIUserLoginView = IUserLoginView;
        mIUserModel = new UserModel();
    }

    public void userLogin() {
        mIUserLoginView.showLoading();
        mIUserModel.login(mIUserLoginView.getUserName(), mIUserLoginView.getPassWord(), new onUserLoginInterface() {
            @Override
            public void loginSuccess(final User user) {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        //模拟登陆成功
                        mIUserLoginView.hideLoading();
                        mIUserLoginView.toMainActivity(user);
                    }
                });

            }

            @Override
            public void loginFailed() {
                //需要在UI线程执行
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mIUserLoginView.showFailedError();
                        mIUserLoginView.hideLoading();
                    }
                });

            }
        });
    }


}
