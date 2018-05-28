package me.hackathon.root.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.model.user.User;
import me.hackathon.root.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void insertUser(User user) {
        userRepository.insertUser(user);
    }

    public User getUserById(long id) {
        return userRepository.selectUserById(id);
    }

    public int updateUserById(User user) {
        return userRepository.updateUserById(user);
    }

    public int deleteUserById(long id) {
        return userRepository.deleteUserById(id);
    }
}
