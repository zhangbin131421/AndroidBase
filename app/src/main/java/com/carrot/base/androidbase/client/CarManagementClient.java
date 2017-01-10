package com.carrot.base.androidbase.client;

import com.carrot.base.androidbase.vo.result.CarManagementResult;
import com.carrot.base.androidbase.vo.result.CarResult;
import com.carrot.base.androidbase.vo.result.CountResult;
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
@Rest(rootUrl = "http://120.77.100.58:8082/api/CarManagement",
        converters = {MappingJackson2HttpMessageConverter.class,
                StringHttpMessageConverter.class,FormHttpMessageConverter.class,
                ByteArrayHttpMessageConverter.class})
public interface CarManagementClient extends RestClientErrorHandling {

    @Get("/GetByID/?ID={id}")
    @Accept(MediaType.APPLICATION_JSON)
    CarManagementResult getById(@Path int id);

    @Get("/GetAll")
    @Accept(MediaType.APPLICATION_JSON)
    List<CarManagementResult> getAll();


    @Get("/GetByUserID/?UserID={userId}")
    @Accept(MediaType.APPLICATION_JSON)
    List<CarManagementResult> getByUserId(@Path int userId);


    @Post("/AddNew")
    @Accept(MediaType.APPLICATION_JSON)
    UpdateResult add(@Body CarManagementResult carManagementResult);


    @Post("/Delete/?ID={id}")
    @Accept(MediaType.APPLICATION_JSON)
    void delete(@Path int id);



    @Get("/GetAllCarInfo")
    @Accept(MediaType.APPLICATION_JSON)
    List<CarResult> getAllCar();
}
