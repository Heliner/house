package com.mooc.house.api.dao;

import com.mooc.house.api.config.GenericRest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Repository;

@Repository
public class UserDao {
    @Autowired
    private GenericRest rest;

    public String getUserName(Long id) {
        String url = "http://user/getusername?id=" + id;
        return rest.get(url, new ParameterizedTypeReference<String>() {
        }).getBody();
    }
}
