package me.hackathon.root.model.response;

import java.util.List;

import lombok.Builder;
import lombok.Data;
import me.hackathon.root.model.comment.Comment;
import me.hackathon.root.model.product.Product;
import me.hackathon.root.model.user.User;

@Data
@Builder
public class ProductView {
    private Product product;
    private User user;
    private List<Comment> comments;
}
