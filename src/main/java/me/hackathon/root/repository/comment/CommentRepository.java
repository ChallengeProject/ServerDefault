package me.hackathon.root.repository.comment;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.hackathon.root.model.comment.Comment;

@Repository
public class CommentRepository {

    @Autowired
    private CommentMapper commentMapper;

    public void insertComment(Comment comment) {
        commentMapper.insertComment(comment);
    }

    public List<Comment> selectCommentsByProductId(@Param("productId") int productId) {
        return commentMapper.selectCommentsByProductId(productId);
    }
}
