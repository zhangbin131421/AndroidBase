package com.carrot.base.androidbase.client;

import com.carrot.base.androidbase.vo.result.AreaInformationResult;
import com.carrot.base.androidbase.vo.result.CrossTestResult;
import com.carrot.base.androidbase.vo.result.TaskBaseVo;

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
import org.androidannotations.rest.spring.api.RestClientHeaders;
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
@Rest(rootUrl = "http://120.77.100.58:8082/api/AreaInformation",
        converters = {MappingJackson2HttpMessageConverter.class,
                StringHttpMessageConverter.class,FormHttpMessageConverter.class,
                ByteArrayHttpMessageConverter.class})
public interface AreaInformationClient extends RestClientErrorHandling {

    @Get("/GetUserById/?UserID={userId}")
    @Accept(MediaType.APPLICATION_JSON)
    AreaInformationResult[] getByUserId(@Path int userId);

    @Get("/GetByID/?ID={id}")
    @Accept(MediaType.APPLICATION_JSON)
    AreaInformationResult[] getById(@Path int id);

    @Get("/GetAll")
    @Accept(MediaType.APPLICATION_JSON)
    AreaInformationResult[] getAll();


}
