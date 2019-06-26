package com.mooc.house.api.controller;

import com.mooc.house.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApiUserController {
    @Autowired
    private UserService userService;

    @RequestMapping("test/getusername")
    public String getUserName(Long id) {
        return userService.getUserName(id);
    }
}
