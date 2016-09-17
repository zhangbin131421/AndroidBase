package com.carrot.base.androidbase.client;

import com.carrot.base.androidbase.vo.result.CountResult;
import com.carrot.base.androidbase.vo.result.ResolveRecordResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Headers;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.RequiresHeader;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.androidannotations.rest.spring.api.RestClientHeaders;
import org.apache.http.HttpHeaders;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Created by victor on 8/28/16.
 */
@Rest(rootUrl = "http://120.55.101.6:8889/api/ResolveRecord",
        converters = {MappingJackson2HttpMessageConverter.class,GsonHttpMessageConverter.class,
                StringHttpMessageConverter.class,FormHttpMessageConverter.class,
                ByteArrayHttpMessageConverter.class})
public interface ResolveRecordClient extends RestClientHeaders {

    @Get("/GetByID/?ID={id}")
    @Accept(MediaType.APPLICATION_JSON)
    ResolveRecordResult getById(@Path int id);

    @Get("/GetByUserID/?UserID={userId}")
    @Accept(MediaType.APPLICATION_JSON)
    List<ResolveRecordResult> getByUserId(@Path int userId);


    @Get("/GetByUserIDAndHandled/?UserID={userId}&IsHandled={isHandled}")
    @Accept(MediaType.APPLICATION_JSON)
    List<TaskBaseVo> getByUserId(@Path int userId, @Path int isHandled);


    @Post("/AddNew")
    @Accept(MediaType.APPLICATION_JSON)
    void add(@Body ResolveRecordResult equipmentCheckResult);


    @Post("/Update")
    @Headers({
            @Header(name = HttpHeaders.CONTENT_TYPE, value = "multipart/form-data"),
            @Header(name = "keep-alive", value = "360")})
    void update(@Body MultiValueMap<String, Object> data);


    @Post("/Delete/?ID={id}")
    @Accept(MediaType.APPLICATION_JSON)
    void delete(@Path int id);


    //
    @Get("/GetNewTaskNum/?UserID={userId}")
    @Accept(MediaType.APPLICATION_JSON)
    CountResult getUnFinishedByUserId(@Path int userId);
}
