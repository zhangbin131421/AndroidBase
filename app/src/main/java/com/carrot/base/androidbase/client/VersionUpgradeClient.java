package com.carrot.base.androidbase.client;

import com.carrot.base.androidbase.vo.result.VersionUpgradeResult;

import org.androidannotations.rest.spring.annotations.Accept;
import org.androidannotations.rest.spring.annotations.Get;
import org.androidannotations.rest.spring.annotations.Rest;
import org.androidannotations.rest.spring.api.MediaType;
import org.androidannotations.rest.spring.api.RestClientErrorHandling;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.GsonHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

/**
 * Created by victor on 8/21/16.
 */
@Rest(rootUrl = "http://120.77.100.58:8082/api/VersionCheck/GetLatestVersionNum", converters = {MappingJackson2HttpMessageConverter.class, GsonHttpMessageConverter.class, StringHttpMessageConverter.class})
public interface VersionUpgradeClient extends RestClientErrorHandling {

    @Get("")
    @Accept(MediaType.APPLICATION_JSON)
    VersionUpgradeResult getVersionUpgradeResult();
}
