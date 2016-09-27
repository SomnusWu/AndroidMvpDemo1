package com.somnus.androidmvpdemo.model;

import com.somnus.androidmvpdemo.bean.User;
import com.somnus.androidmvpdemo.utils.Constants;

/**
 * @date： 2016/9/26.
 * @FileName: com.somnus.androidmvpdemo.model.UserModel.java
 * @author: Somnus
 * @Description:
 */

public class UserModel implements IUserModel {

    @Override
    public void login(final String userName, final String userPassWord, final onUserLoginInterface userLoginInterface) {
        /**
         * 模拟登陆操作
         */

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //模拟登录
                if (Constants.USER_NAME.equalsIgnoreCase(userName) && Constants.USER_PASWORD.equals(userPassWord)) {
                    User user = new User();
                    user.setUsername(userName);
                    user.setPassword(userPassWord);
                    userLoginInterface.loginSuccess(user);
                } else {
                    userLoginInterface.loginFailed();
                }

            }
        }).start();
    }
}
