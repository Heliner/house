package com.mood.house.userservice.controller;

import com.mood.house.userservice.common.RestResponse;
import com.mood.house.userservice.exception.IllegalParamsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
public class UserController {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @RequestMapping("/getusername")
    public RestResponse<String> getusername(Long id) {
        LOGGER.info("incoming request");
       if(null == id)
           throw new IllegalParamsException(IllegalParamsException.Type.WRONG_PAGE_NUM,"错误的分页");
        return RestResponse.success("test-username");
    }

}
