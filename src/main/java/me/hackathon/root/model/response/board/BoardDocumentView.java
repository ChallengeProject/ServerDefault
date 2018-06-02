package me.hackathon.root.model.response.board;

import lombok.Builder;
import lombok.Data;
import me.hackathon.root.model.board.Board;
import me.hackathon.root.model.user.User;

@Data
@Builder
public class BoardDocumentView {
    private User user;
    private Board board;
}
