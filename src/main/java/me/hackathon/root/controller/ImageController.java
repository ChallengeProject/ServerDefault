package me.hackathon.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import me.hackathon.root.service.AmazonS3Service;
import me.hackathon.root.service.product.ProductService;
import me.hackathon.root.service.user.UserService;
import me.hackathon.root.support.Constant;

@RestController
@RequestMapping(Constant.API_URI)
public class ImageController {
    @Autowired
    private UserService userService;

    @Autowired
    private AmazonS3Service amazonS3Service;

    @Autowired
    private ProductService productService;

    @PostMapping("/user/profile/{userId}")
    public void uploadUserProfile(@PathVariable("userId") int userId, @RequestPart MultipartFile multipartFile) {
        String profileUrl = amazonS3Service.uploadImage(multipartFile);
        userService.uploadUserProfile(profileUrl, userId);
    }
}
