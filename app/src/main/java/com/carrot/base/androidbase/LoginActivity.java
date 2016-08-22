package com.carrot.base.androidbase;

import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carrot.base.androidbase.client.UserClient;
import com.carrot.base.androidbase.preferences.MyPrefs_;
import com.carrot.base.androidbase.vo.result.LoginResult;
import com.carrot.base.androidbase.vo.result.UserResult;

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
    MyPrefs_ myPrefs;

    @ViewById(R.id.et_login_username)
    EditText etUsername;

    @ViewById(R.id.et_login_password)
    EditText etPassword;

    @ViewById(R.id.btn_login_login)
    Button btnLogin;

    @Click(R.id.btn_login_login)
    @Background
    void doLogin(){

        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();

        if(username.equals("") || password.equals("")){
            alert("请输入用户名密码");
            return;
        }


        LoginResult loginResult = userClient.login(username, password);


        if(!loginResult.isSuccess()){
            alert("用户名密码不正确");
            return;
        }




        loginSuccess();

    }

    @UiThread
    void loginSuccess(){
        myPrefs.edit().currentUsername().put(etUsername.getText().toString()).apply();

        this.finish();
    }

    @UiThread
    void alert(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}
