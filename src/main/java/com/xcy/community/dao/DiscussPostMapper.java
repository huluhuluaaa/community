package com.xcy.community.dao;

import com.xcy.community.entity.DiscussPost;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface DiscussPostMapper {

    //userId是0则查询所有用户的帖子，若不为0则查该Id对应用户发布的所有帖子，在mapper.xml中使用<if>来实现
    List<DiscussPost> selectDiscussPosts(int userId, int offset, int limit);

    //@Param注解用于给参数取别名
    //若方法只有一个参数，并且在动态SQL语句<if>中使用，则必须加此注解
    int selectDiscussPostRows(@Param("userId") int userId);
}
