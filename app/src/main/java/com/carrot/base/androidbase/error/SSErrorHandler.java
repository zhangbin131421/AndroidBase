package com.carrot.base.androidbase.error;

import android.content.Context;
import android.util.Log;

import org.androidannotations.annotations.EBean;
import org.androidannotations.annotations.RootContext;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.rest.spring.api.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by victor on 9/9/16.
 */
@EBean
public class SSErrorHandler implements RestErrorHandler {

    @RootContext
    Context context;

    @Override
    @UiThread
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        new SweetAlertDialog(context, SweetAlertDialog.ERROR_TYPE)
                .setTitleText("糟糕")
                .setContentText("网络连接失败，请重试。")
                .show();
    }
}
