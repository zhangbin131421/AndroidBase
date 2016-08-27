package com.carrot.base.androidbase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.SystemClock;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carrot.base.androidbase.client.UserClient;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.vo.result.LoginResult;
import com.carrot.base.androidbase.vo.result.UserResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {


    @RestService
    UserClient userClient;

    @Pref
    UserPrefs_ userPrefs;

    @ViewById(R.id.et_login_username)
    EditText etUsername;

    @ViewById(R.id.et_login_password)
    EditText etPassword;

    @ViewById(R.id.btn_login_login)
    Button btnLogin;

    ProgressDialog progress;

    @AfterViews
    void initView(){
        progress = new ProgressDialog(this);
    }

    @Click(R.id.btn_login_login)
    @Background
    void doLogin(){

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(username.equals("") || password.equals("")){
            alert("请输入用户名密码");
            return;
        }

//        loginSuccessTest();

        LoginResult loginResult = userClient.login(username, password);


        if(!loginResult.isSuccess()){
            alert("用户名密码不正确");
            return;
        }

        showLoading();

        UserResult userResult = userClient.getUserById(loginResult.getId());

        loginSuccess(userResult);

    }

    @UiThread
    void loginSuccess(UserResult userResult){

        if(progress != null){
            progress.dismiss();
        }
        userPrefs.edit().name().put(userResult.getName())
                .id().put(userResult.getId())
                .phone().put(userResult.getPhone())
                .isValid().put(userResult.getIsValid())
                .message().put(userResult.getMessage())
                .code().put(userResult.getCode())
                .role().put(userResult.getRole())
                .createTime().put(userResult.getCreateTime())
                .updateTime().put(userResult.getUpdateTime())
                .apply();

        this.finish();
    }

    @UiThread
    void loginSuccessTest(){

        if(progress != null){
            progress.dismiss();
        }
        userPrefs.edit().name().put("张三")
                .id().put(1)
//                .level().put(userResult.getLevel())
//                .phone().put(userResult.getPhone())
//                .isValid().put(userResult.getIsValid())
//                .message().put(userResult.getMessage())
//                .code().put(userResult.getCode())
                .apply();

        this.finish();
    }

    @UiThread
    void showLoading(){

        progress.setTitle("Loading");
        progress.show();

    }
    @UiThread
    void alert(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
