package me.hackathon.root.repository.user;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import me.hackathon.root.model.user.UserBoard;

@Mapper
public interface UserBoardMapper {
    void insertUserBoard(UserBoard userBoard);

    List<Integer> selectUserBoardsByBoardId(@Param("boardId") int boardId);

    List<UserBoard> selectUserBoardsByUserId(@Param("userId") int userId);

}
