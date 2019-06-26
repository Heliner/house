package com.mood.house.userservice.controller;

import com.mood.house.userservice.common.RestResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    public RestResponse<String> getusername(Long id) {
        LOGGER.info("incoming request");
        return RestResponse.success("test-username");
    }
}
