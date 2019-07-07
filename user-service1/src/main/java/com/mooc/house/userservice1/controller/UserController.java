package com.mooc.house.userservice1.controller;


import com.mooc.house.userservice1.common.RestResponse;
import com.mooc.house.userservice1.model.User;
import com.mooc.house.userservice1.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
        return RestResponse.success(user);
    }

    @RequestMapping("/getList")
    public RestResponse<List<User>> getUserList(@RequestBody User user) {
        List<User> users = userService.getUserByQuery(user);
        return RestResponse.success(users);
    }

    @RequestMapping("add")
    public RestResponse add(@RequestBody User user) {
        userService.addAccount(user, user.getEnableUrl());
        return RestResponse.success();
    }

    @RequestMapping("enable")
    public RestResponse<User> enable(String key) {
        userService.enable(key);
        return RestResponse.success();
    }
    @RequestMapping("auth")
    public RestResponse<User> auth(@RequestBody User user){
        User finalUser = userService.auth(user.getEmail(),user.getPasswd());
        return RestResponse.success(finalUser);
    }
    @RequestMapping("get")
    public RestResponse<User> getUser(String token){
        User findUser=userService.getLoginedUserByToken(token);
        return RestResponse.success(findUser);
    }

    @RequestMapping("logout")
    public RestResponse<Object> logout(String token){
        userService.invalidate(token);
        return RestResponse.success();
    }
}
