package com.mooc.house.comment.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.mooc.house.comment.model.Blog;
import com.mooc.house.comment.model.LimitOffset;

@Mapper
public interface BlogMapper {
  
   List<Blog> selectBlog(@Param("blog") Blog blog, @Param("pageParams")LimitOffset limitOffset);
  
   Long selectBlogCount(Blog query);

}
