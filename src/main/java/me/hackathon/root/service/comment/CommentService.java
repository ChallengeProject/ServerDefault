package me.hackathon.root.service.comment;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.model.comment.Comment;
import me.hackathon.root.repository.comment.CommentRepository;

@Service
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    public void insertComment(Comment comment) {
        commentRepository.insertComment(comment);
    }

    public List<Comment> getCommentsByProductId(int productId) {
        return commentRepository.selectCommentsByProductId(productId);
    }

}
