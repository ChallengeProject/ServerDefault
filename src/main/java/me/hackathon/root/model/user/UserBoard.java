package me.hackathon.root.model.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserBoard {
    private Integer id;
    private Integer userId;
    private Integer boardId;
}
