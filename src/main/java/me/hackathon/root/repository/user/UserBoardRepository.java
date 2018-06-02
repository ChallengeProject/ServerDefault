package me.hackathon.root.repository.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.hackathon.root.model.user.UserBoard;

@Repository
public class UserBoardRepository {
    @Autowired
    private UserBoardMapper userBoardMapper;

    public void insertUserBoard(UserBoard userBoard) {
        userBoardMapper.insertUserBoard(userBoard);
    }

    public List<Integer> selectUserBoardsByBoardId(int boardId) {
        return userBoardMapper.selectUserBoardsByBoardId(boardId);
    }

    public List<UserBoard> selectUserBoardsByUserId(int userId) {
        return userBoardMapper.selectUserBoardsByUserId(userId);
    }

}
