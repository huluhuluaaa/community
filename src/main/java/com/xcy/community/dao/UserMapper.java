package com.xcy.community.dao;

import com.xcy.community.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    User selectById(int id);

    User selectByName(String username);

    User selectByEmail(String email);

    int insertUser(User user);

    int updateStatus(int id, int status);

    int updateHeader(int id, String headUrl);

    int updatePassword(int id, String password);

    int updateStatusTo1ByUser(User user);

    int deleteUser(int id);

    List<User> getUsersByIdRange(int begin, int end);
}
