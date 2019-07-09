package com.mooc.house.userservice1.mapper;


import com.mooc.house.userservice1.common.PageParams;
import com.mooc.house.userservice1.model.Agency;
import com.mooc.house.userservice1.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface AgencyMapper {

  List<Agency> select(Agency agency);
  
  int insert(Agency agency);
  
  List<User> selectAgent(@Param("user") User user, @Param("pageParams") PageParams pageParams);
  
  Long selectAgentCount(@Param("user") User user);
}
