package com.carrot.base.androidbase.error;

import android.util.Log;

import org.androidannotations.annotations.EBean;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;

/**
 * Created by victor on 9/9/16.
 */
@EBean
public class SSResponseErrorHandler implements ResponseErrorHandler {
    @Override
    public boolean hasError(ClientHttpResponse response) throws IOException {
        Log.i("sslog", "response has error");
        return false;
    }

    @Override
    public void handleError(ClientHttpResponse response) throws IOException {
        Log.i("sslog", "response handler error");
    }
}
