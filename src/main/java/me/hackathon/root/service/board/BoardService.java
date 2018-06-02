package me.hackathon.root.service.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import me.hackathon.root.model.board.Board;
import me.hackathon.root.model.board.BoardStatus;
import me.hackathon.root.model.response.board.BoardMainView;
import me.hackathon.root.model.response.board.BoardRequest;
import me.hackathon.root.model.response.board.BoardView;
import me.hackathon.root.model.user.User;
import me.hackathon.root.repository.board.BoardRepository;
import me.hackathon.root.service.BlockChainService;
import me.hackathon.root.service.user.UserBoardService;
import me.hackathon.root.service.user.UserService;
import me.hackathon.root.service.user.UserUpgradeGradeService;

@Service
public class BoardService {
    @Autowired
    private BoardRepository boardRepository;
    @Autowired
    private UserBoardService userBoardService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserUpgradeGradeService userUpgradeGradeService;
    @Autowired
    private BlockChainService blockChainService;


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
        board.setStatus(BoardStatus.ENROLLING);
        boardRepository.insertBoard(board);
    }

    public Board getBoardDetailById(int boardId) {
        return boardRepository.selectBoardById(boardId);
    }

    @Transactional
    public int updateBoardStatusToEnd(BoardRequest boardRequest) {
        Board board = boardRepository.selectBoardById(boardRequest.getBoardId());

        if(!board.getUserId().equals(boardRequest.getUserId())) {
            return 0;
        }

        //참가한 userId가져오기
        // userBoard에서 boardId를 통해서 userIds를 가져오기
        List<Integer> userIds = userBoardService.getUserBoardsByBoardId(boardRequest.getBoardId());
        for(int userId : userIds) {
            User user = userService.getUserAndCoinById(userId);
            //봉사자에게 코인 지급
            blockChainService.sendCoin(user.getEmail(),board.getCoin());
        }

        userUpgradeGradeService.upgradeUserGrade(boardRequest.getUserId());
        return boardRepository.updateBoardStatusToEnd(boardRequest.getBoardId());
    }
}
