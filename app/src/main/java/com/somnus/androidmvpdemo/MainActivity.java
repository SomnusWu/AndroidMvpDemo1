package com.somnus.androidmvpdemo;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.dd.processbutton.iml.ActionProcessButton;
import com.somnus.androidmvpdemo.bean.User;
import com.somnus.androidmvpdemo.presenter.UserLoginPreSenter;
import com.somnus.androidmvpdemo.utils.FormValidation;
import com.somnus.androidmvpdemo.utils.TextWatcher;
import com.somnus.androidmvpdemo.view.IUserLoginView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IUserLoginView {


    @Bind(R.id.textInputLayout_phone)
    TextInputLayout textInputLayoutPhone;
    @Bind(R.id.textInputLayout_password)
    TextInputLayout textInputLayoutPassword;
    @Bind(R.id.button_sign)
    ActionProcessButton buttonSign;

    FloatingActionButton fab;


    UserLoginPreSenter mUserLoginPreSenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);



        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        mUserLoginPreSenter = new UserLoginPreSenter(this);
        initTextInputLayout();
    }

    private void initTextInputLayout() {

//        textInputLayoutPhone.setHint("UserName");
//        textInputLayoutPassword.setHint("PassWord");

        textInputLayoutPhone.getEditText().addTextChangedListener(new TextWatcher(textInputLayoutPhone) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 10)
                    if (FormValidation.isMobile(getEditString())) {
                        textInputLayoutPhone.setErrorEnabled(false);
                    } else {
                        setEditTextError(textInputLayoutPhone, R.string.msg_error_phone);
                    }
            }
        });
        textInputLayoutPassword.getEditText().addTextChangedListener(new TextWatcher(textInputLayoutPassword) {
            @Override
            public void afterTextChanged(Editable s) {
                if (getEditString().length() > 5){
                    if (FormValidation.isSimplePassword(getEditString())) {
                        textInputLayoutPassword.setErrorEnabled(false);
                    } else {
                        setEditTextError(textInputLayoutPassword, R.string.msg_error_password);
                    }
                }
            }
        });
    }

    private void setEditTextError(TextInputLayout layout, int msgId) {
        layout.setErrorEnabled(true);
        layout.setError(getString(msgId));
    }


    /**
     * 登录 按钮 click
     *
     * @param view
     */
    public void login(View view) {
        String phone = textInputLayoutPhone.getEditText().getText().toString();
        String password = textInputLayoutPassword.getEditText().getText().toString();
        if (!valid(phone, password))
            return;
        mUserLoginPreSenter.userLogin();

        hideKeyboard();
    }


    private void hideKeyboard() {
        View view = getCurrentFocus();
        if (view != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(view.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }
    public boolean valid(String phone, String password) {
//        if (!FormValidation.isMobile(phone)) {
//            WidgetUtils.requestFocus(textInputLayoutPhone.getEditText());
//            setEditTextError(textInputLayoutPhone, R.string.msg_error_phone);
//            return true;
//        }
//        if (!FormValidation.isSimplePassword(password)) {
//            WidgetUtils.requestFocus(textInputLayoutPassword.getEditText());
//            setEditTextError(textInputLayoutPassword, R.string.msg_error_password);
//            return true;
//        }
        if (!TextUtils.isEmpty(phone))
            return true;
        if (!TextUtils.isEmpty(password))
            return true;

        return false;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public String getUserName() {
        return textInputLayoutPhone.getEditText().getText().toString();
    }

    @Override
    public String getPassWord() {
        return textInputLayoutPassword.getEditText().getText().toString();
    }

    @Override
    public void showLoading() {
        buttonSign.setEnabled(false);
        buttonSign.setProgress(50);
    }

    @Override
    public void hideLoading() {
//        buttonSign.setProgress(100);
    }

    @Override
    public void showFailedError() {
        buttonSign.setProgress(-1);
        showMsgInBottom("登录失败");
    }

    @Override
    public void toMainActivity(User user) {
        Log.d("tag", user.toString());
        buttonSign.setProgress(100);
        showMsgInBottom("登录成功");
    }

    public void showMsgInBottom(String msg) {
        buttonSign.setEnabled(true);
        Snackbar.make(fab, msg, Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}
