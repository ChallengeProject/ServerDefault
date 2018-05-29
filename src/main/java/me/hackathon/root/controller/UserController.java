package me.hackathon.root.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import me.hackathon.root.model.request.UserLoginRequest;
import me.hackathon.root.model.request.UserRequest;
import me.hackathon.root.model.supoort.ResultContainer;
import me.hackathon.root.model.user.User;
import me.hackathon.root.service.UserService;
import me.hackathon.root.support.Constant;

@RestController
@RequestMapping(Constant.USER_URI)
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/getUserById")
    public User getUserById(Long id) {
        return userService.getUserById(id);
    }

    @PostMapping("/insertUser")
    public ResultContainer<Void> insertUser(@RequestBody UserRequest user) {
        userService.insertUser(user);

        return new ResultContainer<>();
    }

    @PostMapping("/updateUserById")
    public ResultContainer<Void> updateUserById(@RequestBody UserRequest user) {
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

}
