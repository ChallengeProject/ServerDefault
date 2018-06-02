package me.hackathon.root.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.model.response.board.BoardDocumentView;
import me.hackathon.root.model.response.board.BoardResultView;
import me.hackathon.root.model.user.UserBoard;
import me.hackathon.root.repository.user.UserBoardRepository;
import me.hackathon.root.service.board.BoardService;

@Service
public class UserBoardService {
    @Autowired
    private UserBoardRepository userBoardRepository;
    @Autowired
    private UserService userService;
    @Autowired
    private BoardService boardService;

    public void insertUserBoard(UserBoard userBoard) {
        userBoardRepository.insertUserBoard(userBoard);
    }

    public BoardDocumentView getBoardDocumentView(int userId, int boardId) {

        return BoardDocumentView.builder()
                                .user(userService.getUserAndCoinById(userId))
                                .board(boardService.getBoardDetailById(boardId))
                                .build();
    }

    public BoardResultView getBoardResultView(int userId, int boardId) {
        return BoardResultView.builder()
                              .user(userService.getUserAndCoinById(userId))
                              .board(boardService.getBoardDetailById(boardId))
                              .build();
    }

    public List<Integer> getUserBoardsByBoardId(int boardId) {
        return userBoardRepository.selectUserBoardsByBoardId(boardId);
    }

    public List<UserBoard> getUserBoardsByUserId(int userId) {
        return userBoardRepository.selectUserBoardsByUserId(userId);
    }
}
