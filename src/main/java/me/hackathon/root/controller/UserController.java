package me.hackathon.root.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
