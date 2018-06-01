package me.hackathon.root.repository.user;

import org.apache.ibatis.annotations.Mapper;

import me.hackathon.root.model.user.UserBoard;

@Mapper
public interface UserBoardMapper {
    void insertUserBoard(UserBoard userBoard);
}
