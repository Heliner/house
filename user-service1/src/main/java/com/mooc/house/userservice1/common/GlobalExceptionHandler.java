package com.mooc.house.userservice1.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;

import static com.mooc.house.userservice1.common.Exception2CodeRepo.getCode;


@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ResponseStatus(HttpStatus.OK)
    @ExceptionHandler(value = Throwable.class)
    @ResponseBody
    public RestResponse<Object> hanlder(HttpServletRequest request, Throwable throwable) {
        LOGGER.error(throwable.getMessage(), throwable);
        Object target = throwable;

        RestCode restCode = getCode(throwable);
        RestResponse<Object> restResponse = new RestResponse<>(restCode.code, restCode.msg);
        return restResponse;
    }


}
