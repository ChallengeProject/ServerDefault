package me.hackathon.root.service.product;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.model.comment.Comment;
import me.hackathon.root.model.product.Product;
import me.hackathon.root.model.response.ProductMainView;
import me.hackathon.root.model.response.ProductView;
import me.hackathon.root.model.user.UserOrder;
import me.hackathon.root.repository.product.ProductRepository;
import me.hackathon.root.service.comment.CommentService;
import me.hackathon.root.service.user.UserOrderService;
import me.hackathon.root.service.user.UserService;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private UserOrderService userOrderService;

    public ProductMainView getProductMainView() {
        return new ProductMainView(productRepository.selectProductListAll());
    }


    public Product getProductByProductId(Integer productId) {
        Product product = productRepository.selectProductByProductId(productId);

        return product;
    }


    public ProductView getProductView(Integer productId) {
        Product product = productRepository.selectProductByProductId(productId);

        return ProductView.builder()
                                 .comments(commentService.getCommentsByProductId(product.getId()))
                                 .product(product)
                                 .user(userService.getUserById(product.getUserId()))
                                 .build();
    }

    public void insertProduct(Product product) {
        productRepository.insertProduct(product);
    }

    public void insertComment(Comment comment) {
        commentService.insertComment(comment);
    }

    public void buyProduct(UserOrder userOrder) {
        userOrderService.insertUserOrder(userOrder);
    }

    public List<Product> getProductListByIds(Collection<Integer> ids) {
        return productRepository.selectProductListByIds(ids);
    }

    public List<Product> getProductListByUserId(int userId) {
        return productRepository.selectProductListByUserId(userId);
    }
}
