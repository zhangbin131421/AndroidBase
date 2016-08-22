package com.carrot.base.androidbase.client;

import android.util.Log;

import com.carrot.base.androidbase.constant.HTTPConstant;
import com.carrot.base.androidbase.vo.result.LoginResult;
import com.carrot.base.androidbase.vo.result.UserResult;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by victor on 8/21/16.
 */
@Rest(converters = {MappingJackson2HttpMessageConverter.class,GsonHttpMessageConverter.class,StringHttpMessageConverter.class})
public interface UserClient {
    @Get("/GetUserById/?ID={id}")
    UserResult getUserById(@Path int id);

    @Get("http://120.55.101.6:8089/api/User/Login/?LoginName={username}&Password={password}")
    @Accept(MediaType.APPLICATION_JSON)
    LoginResult login(@Path String username, @Path String password);


}
