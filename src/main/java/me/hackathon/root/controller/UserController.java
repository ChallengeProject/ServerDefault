package me.hackathon.root.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import me.hackathon.root.model.request.UserLoginRequest;
import me.hackathon.root.model.supoort.ResultContainer;
import me.hackathon.root.model.user.User;
import me.hackathon.root.service.AmazonS3Service;
import me.hackathon.root.service.user.UserService;
import me.hackathon.root.support.Constant;

@RestController
@RequestMapping(Constant.USER_URI)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private AmazonS3Service amazonS3Service;

    @GetMapping("/myPage/{userId}")
    public ResultContainer<User> getUserById(@PathVariable("userId") int userId) {
        return new ResultContainer<>(userService.getUserById(userId));
    }


    @PostMapping("/join")
    public ResultContainer<Void> insertUser(@RequestBody User user) {
        userService.insertUser(user);

        return new ResultContainer<>();
    }

    @PostMapping("/updateUserById")
    public ResultContainer<Void> updateUserById(@RequestBody User user) {
        userService.updateUserById(user);

        return new ResultContainer<>();
    }

    @PostMapping("/deleteUserById")
    public ResultContainer<Void> deleteUserById(@Valid @RequestBody long userId) {
        userService.deleteUserById(userId);

        return new ResultContainer<>();
    }

    @PostMapping("/login")
    public ResultContainer<User> login(@RequestBody UserLoginRequest userLoginRequest) {
        return new ResultContainer<>(userService.login(userLoginRequest));
    }

    @PostMapping("/upload")
    public ResultContainer<String> upload(@RequestPart MultipartFile multipartFile) {

        return new ResultContainer<>(amazonS3Service.uploadImage(multipartFile));
    }
}
