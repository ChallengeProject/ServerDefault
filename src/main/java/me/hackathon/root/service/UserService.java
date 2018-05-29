package me.hackathon.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.exceptions.UserAlreadyExistsException;
import me.hackathon.root.model.request.UserLoginRequest;
import me.hackathon.root.model.request.UserRequest;
import me.hackathon.root.model.user.User;
import me.hackathon.root.repository.UserRepository;
import me.hackathon.root.support.security.SecurityUtils;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void insertUser(UserRequest userRequest) {
        // user 중복 검사
        if (findUserByEmail(userRequest.getEmail()) == true) {
            throw new UserAlreadyExistsException();
        }
        userRepository.insertUser(buildUser(userRequest));
    }

    public User getUserById(long id) {
        return userRepository.selectUserById(id);
    }

    public int updateUserById(UserRequest userRequest) {
        return userRepository.updateUserById(buildUser(userRequest));
    }

    public int deleteUserById(long id) {
        return userRepository.deleteUserById(id);
    }

    public User login(UserLoginRequest userLoginRequest) {
        userLoginRequest.setPassword(SecurityUtils.encryptMD5AndSHA256(userLoginRequest.getPassword()));
        return userRepository.login(userLoginRequest);
    }

    public boolean findUserByEmail(String email) {
        return userRepository.selectUserByEmail(email);
    }

    public User buildUser(UserRequest userRequest) {
        User user = new User();
        user.setEmail(userRequest.getEmail());
        user.setPassword(SecurityUtils.encryptMD5AndSHA256(userRequest.getPassword()));

        return user;
    }
}
