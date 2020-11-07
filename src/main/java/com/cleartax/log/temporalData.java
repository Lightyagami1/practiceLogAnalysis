package com.cleartax.log;

import lombok.Value;

@Value
public class temporalData {
    REST_METHOD rm;
    String url;
    Integer minTime;
    Integer maxTime;
    Double avgTime;
}
