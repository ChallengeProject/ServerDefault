package me.hackathon.root.model.response;

import lombok.Builder;
import lombok.Data;
import me.hackathon.root.model.board.Board;
import me.hackathon.root.model.user.User;

@Data
@Builder
public class BoardResultView {
    private User user;
    private Board board;
}
