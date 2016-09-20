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
public class SSErrorWithoutDialogHandler implements RestErrorHandler {

    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        Log.e("sserror", e.getMessage());
    }
}
