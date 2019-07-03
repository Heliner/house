package com.mooc.house.userservice1.mapper;

import com.mooc.house.userservice1.model.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    User selectById(Long id);

    List<User> select(User user);

    void update(User user);

    void insert1(User account);

    void delete(String email);
}
