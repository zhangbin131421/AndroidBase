package com.carrot.base.androidbase;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.os.Handler;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.carrot.base.androidbase.client.UserClient;
import com.carrot.base.androidbase.client.VersionUpgradeClient;
import com.carrot.base.androidbase.error.SSErrorHandler;
import com.carrot.base.androidbase.preferences.UserPrefs_;
import com.carrot.base.androidbase.vo.result.LoginResult;
import com.carrot.base.androidbase.vo.result.UserResult;
import com.carrot.base.androidbase.vo.result.VersionUpgradeResult;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Bean;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;
import org.androidannotations.annotations.sharedpreferences.Pref;
import org.androidannotations.rest.spring.annotations.RestService;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
    @RestService
    VersionUpgradeClient versionUpgradeClient;
    @AfterViews
    void initView(){
        getVersion();
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
    @Background
    public void getVersion() {
        VersionUpgradeResult versionUpgradeResult = versionUpgradeClient.getVersionUpgradeResult();
        showUpgrade(versionUpgradeResult);
    }

    /*
           * 获取当前程序的版本号
           */
    private String getVersionName() {
        PackageInfo packInfo = null;
        try {
            packInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
            return packInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "unknown";
    }

    @UiThread
    public void showUpgrade(VersionUpgradeResult versionUpgradeResult) {
        m_newVerName="外勤通"+versionUpgradeResult.getVersionNum()+".apk";
        int i = getVersionName().compareTo(versionUpgradeResult.getVersionNum());
        if (i < 0) {
            doNewVersionUpdate(versionUpgradeResult.getVersionNum());
        }
    }
    Handler m_mainHandler;
    ProgressDialog m_progressDlg;
    String m_newVerName; //最新版的版本名
    /**
     * 提示更新新版本
     */
    private void doNewVersionUpdate(String verName) {
        final String url="http://120.77.100.58:8082/api/VersionCheck/Download/?VersionNum="+verName;
        m_mainHandler = new Handler();
        m_progressDlg =  new ProgressDialog(this);
        m_progressDlg.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        // 设置ProgressDialog 的进度条是否不明确 false 就是不设置为不明确
        m_progressDlg.setIndeterminate(false);

        String str = "发现新版本：" + verName + " ,是否更新？";
        Dialog dialog = new AlertDialog.Builder(this).setTitle("软件更新").setMessage(str)
                // 设置内容
                .setPositiveButton("更新",// 设置确定按钮
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog,
                                                int which) {
                                m_progressDlg.setTitle("正在下载");
                                m_progressDlg.setMessage("请稍候...");
                                downFile(url);  //开始下载
                            }
                        })
                .setNegativeButton("暂不更新",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,
                                                int whichButton) {
                                // 点击"取消"按钮之后退出程序
//                                finish();
                                dialog.dismiss();
                            }
                        }).create();// 创建
        // 显示对话框
        dialog.show();
    }

    private void downFile(final String url) {
        m_progressDlg.show();
        new Thread() {
            public void run() {
                HttpClient client = new DefaultHttpClient();
                HttpGet get = new HttpGet(url);
                HttpResponse response;
                try {
                    response = client.execute(get);
                    HttpEntity entity = response.getEntity();
                    long length = entity.getContentLength();

                    m_progressDlg.setMax((int) length);//设置进度条的最大值

                    InputStream is = entity.getContent();
                    FileOutputStream fileOutputStream = null;
                    if (is != null) {
                        File file = new File(
                                Environment.getExternalStorageDirectory(),
                                m_newVerName);
                        fileOutputStream = new FileOutputStream(file);
                        byte[] buf = new byte[1024];
                        int ch = -1;
                        int count = 0;
                        while ((ch = is.read(buf)) != -1) {
                            fileOutputStream.write(buf, 0, ch);
                            count += ch;
                            if (length > 0) {
                                m_progressDlg.setProgress(count);
                            }
                        }
                    }
                    fileOutputStream.flush();
                    if (fileOutputStream != null) {
                        fileOutputStream.close();
                    }
                    down();  //告诉HANDER已经下载完成了，可以安装了
                } catch (ClientProtocolException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    /**
     * 告诉HANDER已经下载完成了，可以安装了
     */
    private void down() {
        m_mainHandler.post(new Runnable() {
            public void run() {
                m_progressDlg.cancel();
                update();
            }
        });
    }

    /**
     * 安装程序
     */
    void update() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(new File(Environment
                        .getExternalStorageDirectory(), m_newVerName)),
                "application/vnd.android.package-archive");
        startActivity(intent);
    }
}
