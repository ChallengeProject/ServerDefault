package me.hackathon.root.repository.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.hackathon.root.model.board.Board;

@Repository
public class BoardRepository {
    @Autowired
    private BoardMapper boardMapper;

    public List<Board> selectBoardsAll() {
        return boardMapper.selectBoardsAll();
    }

    public void insertBoard(Board board) {
        boardMapper.insertBoard(board);
    }

    public Board selectBoardById(int boardId) {
        return boardMapper.selectBoardById(boardId);
    }

    public List<Board> selectBoardsByUserId(int userId) {
        return boardMapper.selectBoardsByUserId(userId);
    }

    public int updateBoardStatusToEnd(int boardId) {
        return boardMapper.updateBoardStatusToEnd(boardId);
    }
}
