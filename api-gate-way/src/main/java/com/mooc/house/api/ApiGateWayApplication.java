package com.mooc.house.api;

import com.mooc.house.api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@Controller
public class ApiGateWayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGateWayApplication.class, args);
    }

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("/userInstance")
    @ResponseBody
    public List<ServiceInstance> getRegister(){
        return discoveryClient.getInstances("user");
    }

    @Autowired
    private UserService userService;

    @RequestMapping("/getusername")
    public String getUserName(Long id) {
        return userService.getUserName(id);
    }
}
