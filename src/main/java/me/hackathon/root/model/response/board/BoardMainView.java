package me.hackathon.root.model.response.board;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import me.hackathon.root.model.board.Board;

@Data
@Builder
public class BoardMainView {
    List<Board> boards;
}
