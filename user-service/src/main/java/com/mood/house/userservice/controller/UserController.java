package com.mood.house.userservice.controller;

import com.mood.house.userservice.common.RestResponse;
import com.mood.house.userservice.model.User;
import com.mood.house.userservice.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.mood.house.userservice.common.RestResponse.success;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;
    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    //查询
    @RequestMapping("/getById")
    public RestResponse<User> getUserById(Long id) {
       User user = userService.getUserById(id);
        return  RestResponse.success(user);
    }

    @RequestMapping("/getList")
    public RestResponse<List<User>> getUserList(@RequestBody User user){
        List<User> users = userService.getUserByQuery(user);
        return RestResponse.success(users);
    }
}
