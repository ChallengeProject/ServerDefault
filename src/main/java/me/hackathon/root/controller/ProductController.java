package me.hackathon.root.controller;

import java.io.IOException;

import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import me.hackathon.root.model.comment.Comment;
import me.hackathon.root.model.product.Product;
import me.hackathon.root.model.response.product.ProductMainView;
import me.hackathon.root.model.response.product.ProductView;
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
    public ResultContainer<ProductView> getProduct(@NotNull @PathVariable("productId") int productId) {
        return new ResultContainer<>(productService.getProductView(productId));
    }

    @PostMapping("/comment")
    public ResultContainer<Void> insertComment(@RequestBody Comment comment) {
        productService.insertComment(comment);
        return new ResultContainer<>();
    }

    @PostMapping("/buy")
    public ResultContainer<Void> buyProduct(@RequestBody UserOrder userOrder) throws IOException {
        productService.buyProduct(userOrder);

        return new ResultContainer<>();
    }

    @PostMapping(value = {"/write", "/thumbnail"})
    public ResultContainer<Void> writeProduct(@RequestBody Product product)
            {
        productService.insertProduct(product);
        return new ResultContainer<>();
    }

    @PostMapping("/thumbnail/{productId}")
    public void uploadProductThumbnail(@PathVariable("productId") int productId, @RequestPart MultipartFile multipartFile) {
        productService.uploadThumbnail(multipartFile, productId);
    }

//    @PostMapping("/thumbnail")
//    public void updateProductThumbnail(@RequestBody Product product) {
//        productService.updateProductThumbnail(product);
//    }


}
