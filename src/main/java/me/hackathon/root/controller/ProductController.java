package me.hackathon.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.hackathon.root.model.comment.Comment;
import me.hackathon.root.model.product.Product;
import me.hackathon.root.model.response.ProductMainView;
import me.hackathon.root.model.response.ProductView;
import me.hackathon.root.model.supoort.ResultContainer;
import me.hackathon.root.model.user.UserOrder;
import me.hackathon.root.service.product.ProductService;
import me.hackathon.root.support.Constant;

@RestController
@RequestMapping(Constant.PRODUCT_URI)
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/main")
    public ResultContainer<ProductMainView> getProductMainView() {
        return new ResultContainer<>(productService.getProductMainView());
    }

    @GetMapping("/{productId}")
    public ResultContainer<ProductView> getProduct(@PathVariable("productId") Integer productId) {
        return new ResultContainer<>(productService.getProductView(productId));
    }

    @PostMapping("/comment")
    public ResultContainer<Void> insertComment(@RequestBody Comment comment) {
        productService.insertComment(comment);
        return new ResultContainer<>();
    }

    @PostMapping("/buy")
    public ResultContainer<Void> buyProduct(@RequestBody UserOrder userOrder) {
        productService.buyProduct(userOrder);

        return new ResultContainer<>();
    }

    @PostMapping("/write")
    public ResultContainer<Void> writeProduct(@RequestBody Product product) {
        productService.insertProduct(product);
        return new ResultContainer<>();
    }


}
