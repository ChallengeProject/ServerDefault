package me.hackathon.root.repository.board;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import me.hackathon.root.model.board.Board;

@Mapper
public interface BoardMapper {

    List<Board> selectBoardsAll();

    void insertBoard(Board board);

    Board selectBoardById(@Param("boardId") int boardId);

    List<Board> selectBoardsByUserId(@Param("userId") int userId);

    int updateBoardStatusToEnd(@Param("boardId") int boardId);
}
