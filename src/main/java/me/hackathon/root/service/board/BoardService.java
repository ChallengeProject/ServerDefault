package me.hackathon.root.service.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.model.board.Board;
import me.hackathon.root.model.response.BoardMainView;
import me.hackathon.root.model.response.BoardView;
import me.hackathon.root.repository.board.BoardRepository;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;


    public BoardMainView getBoardMainView() {
        return BoardMainView.builder()
                .boards(boardRepository.selectBoardsAll())
                .build();
    }

    public BoardView getBoardView(int userId) {
        return BoardView.builder()
                .boards(boardRepository.selectBoardsByUserId(userId))
                .build();

    }

    public void insertBoard(Board board) {
        boardRepository.insertBoard(board);
    }

    public Board getBoardDetailById(int boardId) {
        return boardRepository.selectBoardById(boardId);
    }

    public int updateBoardStatusToEnd(int boardId) {
        return boardRepository.updateBoardStatusToEnd(boardId);
    }
}
