package com.carrot.base.androidbase.error;

import android.util.Log;

import org.androidannotations.annotations.EBean;
import org.androidannotations.rest.spring.api.RestErrorHandler;
import org.springframework.core.NestedRuntimeException;

/**
 * Created by victor on 9/9/16.
 */
@EBean
public class SSErrorHandler implements RestErrorHandler {
    @Override
    public void onRestClientExceptionThrown(NestedRuntimeException e) {
        Log.i("sslog", "rest client exception: " + e.getMessage());
    }
}
