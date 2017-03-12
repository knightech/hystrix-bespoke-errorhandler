package com.baeldung.spring.cloud.hystrix.rest.consumer;

import org.springframework.http.HttpStatus;

/**
 * Created by pete on 11/03/2017.
 */
public class RestUtil {

    public static boolean isError(HttpStatus status) {
        HttpStatus.Series series = status.series();
        return (HttpStatus.Series.CLIENT_ERROR.equals(series)
                || HttpStatus.Series.SERVER_ERROR.equals(series));
    }
}
