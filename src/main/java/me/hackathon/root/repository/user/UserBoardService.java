package me.hackathon.root.repository.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.model.response.BoardDocumentView;
import me.hackathon.root.model.response.BoardResultView;
import me.hackathon.root.model.user.UserBoard;
import me.hackathon.root.service.board.BoardService;
import me.hackathon.root.service.user.UserService;

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
                                .user(userService.getUserById(userId))
                                .board(boardService.getBoardDetailById(boardId))
                                .build();
    }

    public BoardResultView getBoardResultView(int userId, int boardId) {
        return BoardResultView.builder()
                              .user(userService.getUserById(userId))
                              .board(boardService.getBoardDetailById(boardId))
                              .build();
    }
}
