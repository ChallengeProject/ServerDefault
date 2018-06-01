package me.hackathon.root.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.hackathon.root.model.board.Board;
import me.hackathon.root.model.response.BoardDocumentView;
import me.hackathon.root.model.response.BoardMainView;
import me.hackathon.root.model.response.BoardResultView;
import me.hackathon.root.model.response.BoardView;
import me.hackathon.root.model.supoort.ExceptionCode;
import me.hackathon.root.model.supoort.ResultContainer;
import me.hackathon.root.model.user.UserBoard;
import me.hackathon.root.repository.user.UserBoardService;
import me.hackathon.root.service.board.BoardService;
import me.hackathon.root.support.Constant;

@RestController
@RequestMapping(Constant.BOARD_URI)
public class BoardController {

    @Autowired
    private BoardService boardService;
    @Autowired
    private UserBoardService userBoardService;

    @GetMapping("/main")
    public ResultContainer<BoardMainView> getBoardMainView() {
        return new ResultContainer<>(boardService.getBoardMainView());
    }

    @GetMapping("/{boardId}")
    public ResultContainer<Board> getBoardById(@PathVariable("boardId") Integer boardId) {
        return new ResultContainer<>(boardService.getBoardById(boardId));
    }

    @GetMapping("/{userId}")
    public ResultContainer<BoardView> getBoardByUserId(@PathVariable("userId") Integer userId) {
        return new ResultContainer<>(boardService.getBoardView(userId));
    }

    @PostMapping("/write")
    public ResultContainer<Void> writeBoard(@RequestBody Board board) {
        Date date = new Date();
        Long test = date.getTime();
        boardService.insertBoard(board);
        return new ResultContainer<>();
    }

    @PostMapping("/join")
    public ResultContainer<Void> joinBoard(@RequestBody UserBoard userBoard) {

        userBoardService.insertUserBoard(userBoard);
        return new ResultContainer<>();
    }
    @PostMapping("/document/{userId}/{boardId}")
    public ResultContainer<BoardDocumentView> getBoardDocumentView(@PathVariable("userId") int userId, @PathVariable("boardId") int boardId) {
        return new ResultContainer<>(userBoardService.getBoardDocumentView(userId, boardId));
    }

    @PostMapping("/result/{userId}/{boardId}")
    public ResultContainer<BoardResultView> getBoardResultView(@PathVariable("userId") int userId, @PathVariable("boardId") int boardId) {
        return new ResultContainer<>(userBoardService.getBoardResultView(userId, boardId));
    }

    @PostMapping("/end")
    public ResultContainer<Void> endBoard(@RequestBody Integer boardId) {
        ResultContainer<Void> resultContainer = new ResultContainer<>();
        if (boardService.updateBoardStatusToEnd(boardId) != 1) {
            resultContainer.setCode(ExceptionCode.INTERNAL_SERVER_ERROR);
        }
        return resultContainer;
    }

}
