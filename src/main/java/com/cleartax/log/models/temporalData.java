package com.cleartax.log.models;

import lombok.Value;

@Value
public class temporalData {
    REST_METHOD rm;
    String url;
    Integer minTime;
    Integer maxTime;
    Double avgTime;
}
