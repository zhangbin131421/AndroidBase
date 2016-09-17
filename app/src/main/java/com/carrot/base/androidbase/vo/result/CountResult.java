package com.carrot.base.androidbase.vo.result;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import org.parceler.Parcel;

/**
 * Created by victor on 9/17/16.
 */
@Parcel
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class CountResult {

    public static final String Count = "Count";
    @JsonProperty(value=Count)
    public int count;
}
