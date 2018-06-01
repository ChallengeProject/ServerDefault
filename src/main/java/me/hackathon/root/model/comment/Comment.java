package me.hackathon.root.model.comment;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {
    private Integer id;
    private Integer userId;
    private Long createdTime;
    private String content;
    private CommentGrade grade;
    private Integer productId;
}
