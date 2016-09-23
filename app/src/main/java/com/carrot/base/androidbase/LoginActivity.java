package com.carrot.base.androidbase;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Build;
import android.os.SystemClock;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carrot.base.androidbase.client.UserClient;
import com.carrot.base.androidbase.error.SSErrorHandler;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.vo.result.LoginResult;
import com.carrot.base.androidbase.vo.result.UserResult;
import com.wingjay.blurimageviewlib.BlurImageView;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;

import cn.jpush.android.api.JPushInterface;

/**
 * Created by victor on 8/12/16.
 */
@EActivity(R.layout.activity_login)
public class LoginActivity extends Activity {

    @Bean
    SSErrorHandler ssErrorHandler;

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
//
//    @ViewById(R.id.bg)
//    BlurImageView bgImage;

    ProgressDialog progress;

    @AfterViews
    void initView(){
        progress = new ProgressDialog(this);
        userClient.setRestErrorHandler(ssErrorHandler);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            //透明状态栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //透明导航栏
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
//        bgImage.setBlurFactor(0);

//        BlurImageView fullBlurImageView = (BlurImageView) this.getba
//        fullBlurImageView.setFullImageByUrl(blurImageUrl, normalImageUrl);
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
        JPushInterface.init(this);
        userResult.deviceID = JPushInterface.getRegistrationID(this);
        Log.i("sslog", "device id : " + userResult.deviceID);

        userPrefs.edit().name().put(userResult.name)
                .id().put(userResult.id)
                .phone().put(userResult.phone)
                .isValid().put(userResult.isValid)
                .message().put(userResult.message)
                .code().put(userResult.code)
                .role().put(userResult.role)
                .createTime().put(userResult.createTime)
                .updateTime().put(userResult.updateTime)
                .deviceID().put(userResult.deviceID)
                .dept().put(userResult.dept)
                .role().put(userResult.role)
                .classSort().put(userResult.classSort)
                .apply();

        updateUser(userResult);
        this.finish();
    }

    @Background
    void updateUser(UserResult userResult){

        userClient.updateUser(userResult);
    }

//    @UiThread
//    void loginSuccessTest(){
//
//        if(progress != null){
//            progress.dismiss();
//        }
//        userPrefs.edit().name().put("张三")
//                .id().put(1)
////                .level().put(userResult.getLevel())
////                .phone().put(userResult.getPhone())
////                .isValid().put(userResult.getIsValid())
////                .message().put(userResult.getMessage())
////                .code().put(userResult.getCode())
//                .apply();
//
//        this.finish();
//    }

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
