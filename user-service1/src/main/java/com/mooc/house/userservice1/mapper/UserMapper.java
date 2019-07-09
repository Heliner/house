package com.mooc.house.userservice1.mapper;

import java.util.List;

import com.mooc.house.userservice1.model.User;
import org.apache.ibatis.annotations.Mapper;



@Mapper
public interface UserMapper {
  
  User selectById(Long id);
  
  List<User> select(User user);
  
  int update(User user);
  
  int insert(User account);
  
  int delete(String email);

  User selectByEmail(String email);
}
