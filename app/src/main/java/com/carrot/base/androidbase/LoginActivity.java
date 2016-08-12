package com.carrot.base.androidbase;

import android.app.Activity;
import android.widget.Button;
import android.widget.EditText;

import com.carrot.base.androidbase.preferences.MyPrefs_;

import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {


    @Pref
    MyPrefs_ myPrefs;

    @ViewById(R.id.et_login_username)
    EditText etUsername;

    @ViewById(R.id.et_login_password)
    EditText etPassword;

    @ViewById(R.id.btn_login_login)
    Button btnLogin;

    @Click(R.id.btn_login_login)
    void doLogin(){
        System.out.println("do login.");

        this.loginSuccess();
    }

    void loginSuccess(){
        myPrefs.edit().currentUsername().put(etUsername.getText().toString()).apply();

        this.finish();
    }

}
