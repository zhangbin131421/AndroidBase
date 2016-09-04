package com.carrot.base.androidbase.client;

import com.carrot.base.androidbase.vo.result.CoreMeterTestResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.apache.http.HttpHeaders;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Created by victor on 8/28/16.
 */
@Rest(rootUrl = "http://120.55.101.6:8889/api/CoreMeterTest", converters = {MappingJackson2HttpMessageConverter.class,GsonHttpMessageConverter.class,StringHttpMessageConverter.class, FormHttpMessageConverter.class})
public interface CoreMeterTestClient extends RestClientHeaders {

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

//    http://120.55.101.6:8889/api/CoreMeterTest/AddNew
    @Post("/AddNew")
    @Accept(MediaType.APPLICATION_JSON)
    void add(@Body CoreMeterTestResult coreMeterTestResult);


//    http://120.55.101.6:8889/api/CoreMeterTest/Update
    @Post("/Update")
    @RequiresHeader(HttpHeaders.CONTENT_TYPE)
//    @Accept(MediaType.MULTIPART_FORM_DATA)
    void update(@Body MultiValueMap<String, Object> data);


//    http://120.55.101.6:8889/api/CoreMeterTest/Delete/?ID=1
    @Post("/Delete/?ID={id}")
    @Accept(MediaType.APPLICATION_JSON)
    void delete(@Path int id);
}
