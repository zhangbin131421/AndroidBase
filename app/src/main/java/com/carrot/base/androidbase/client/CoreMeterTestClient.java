package com.carrot.base.androidbase.client;

import com.carrot.base.androidbase.vo.result.CoreMeterTestResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.List;

/**
 * Created by victor on 8/28/16.
 */
@Rest(rootUrl = "http://120.55.101.6:8889/api/CoreMeterTest", converters = {MappingJackson2HttpMessageConverter.class,GsonHttpMessageConverter.class,StringHttpMessageConverter.class})
public interface CoreMeterTestClient {

    @Get("/GetByID/?ID={id}")
    @Accept(MediaType.APPLICATION_JSON)
    CoreMeterTestResult getById(@Path int id);

    @Get("/GetByUserID/?UserID={userId}")
    @Accept(MediaType.APPLICATION_JSON)
    List<TaskBaseVo> getByUserId(@Path int userId);

    //http://120.55.101.6:8889/api/CoreMeterTest/GetByUserIDAndHandled/?UserID=1&IsHandled=1

    @Get("/GetByUserIDAndHandled/?UserID={userId}&IsHandled={isHandled}")
    @Accept(MediaType.APPLICATION_JSON)
    List<TaskBaseVo> getByUserId(@Path int userId, @Path int isHandled);
}
