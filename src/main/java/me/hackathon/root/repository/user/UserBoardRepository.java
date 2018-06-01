package me.hackathon.root.repository.user;

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
}
