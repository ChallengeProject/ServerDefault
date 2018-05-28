package me.hackathon.root.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import me.hackathon.root.model.user.User;

@Mapper
public interface UserMapper {

    void insertUser(User user);

    User selectUserById(@Param("id")long id);

    int updateUserById(User user);

    int deleteUserById(@Param("id")long id);
}
