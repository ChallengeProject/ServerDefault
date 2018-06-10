package me.hackathon.root.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.hackathon.root.model.board.Board;
import me.hackathon.root.model.response.board.BoardMainView;
import me.hackathon.root.model.supoort.ResultContainer;
import me.hackathon.root.model.user.UserBoard;
import me.hackathon.root.service.user.UserBoardService;
import me.hackathon.root.support.Constant;

@RestController
@RequestMapping(Constant.USER_BOARD_URI)
public class UserBoardController {
    @Autowired
    private UserBoardService userBoardService;

    @GetMapping("/{userId}")
    public ResultContainer<BoardMainView> getUserBoards(@PathVariable("userId") int userId) {
        return new ResultContainer<BoardMainView>(BoardMainView.builder().boards(userBoardService.getJoinBoardByUserId(userId)).build());
    }
}
