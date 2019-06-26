package com.mooc.house.api.service;

import com.mooc.house.api.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    public String getUserName(Long id) {
        return userDao.getUserName(id);
    }
}
