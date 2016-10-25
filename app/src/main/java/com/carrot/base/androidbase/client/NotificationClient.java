package com.carrot.base.androidbase.client;

import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.CountResult;
import com.carrot.base.androidbase.vo.result.NotificationResult;
import com.carrot.base.androidbase.vo.result.UpdateResult;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Body;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Header;
import org.androidannotations.rest.spring.annotations.Headers;
import org.androidannotations.rest.spring.annotations.Path;
import org.androidannotations.rest.spring.annotations.Post;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.apache.http.HttpHeaders;
import org.springframework.http.converter.ByteArrayHttpMessageConverter;
import org.springframework.http.converter.FormHttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.util.MultiValueMap;

import java.util.List;

/**
 * Created by victor on 8/28/16.
 */
@Rest(rootUrl = "http://119.90.140.156:8087/api/Notification",
        converters = {MappingJackson2HttpMessageConverter.class,
                StringHttpMessageConverter.class,FormHttpMessageConverter.class,
                ByteArrayHttpMessageConverter.class})
public interface NotificationClient extends RestClientErrorHandling {

    @Get("/GetByID/?ID={id}")
    @Accept(MediaType.APPLICATION_JSON)
    NotificationResult getById(@Path int id);

    @Get("/Get")
    @Accept(MediaType.APPLICATION_JSON)
    List<NotificationResult> get();



    //
    @Get("/GetNewTaskNum/?UserID={userId}")
    @Accept(MediaType.APPLICATION_JSON)
    CountResult getUnFinishedByUserId(@Path int userId);
}
