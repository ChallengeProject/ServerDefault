package me.hackathon.root.repository.comment;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import me.hackathon.root.model.comment.Comment;

@Mapper
public interface CommentMapper {

    void insertComment(Comment comment);

    List<Comment> selectCommentsByProductId(@Param("productId") int productId);
}
