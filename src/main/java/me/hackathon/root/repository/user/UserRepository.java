package me.hackathon.root.repository.user;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import me.hackathon.root.model.request.UserLoginRequest;
import me.hackathon.root.model.user.User;

@Repository
public class UserRepository {
    @Autowired
    private UserMapper userMapper;

    public void insertUser(User user) {
        userMapper.insertUser(user);
    }

    public User selectUserById(long id) {
        return userMapper.selectUserById(id);
    }

    public int updateUserById(User user) {
        return userMapper.updateUserById(user);
    }

    public int deleteUserById(long id) {
        return userMapper.deleteUserById(id);
    }

    public boolean selectUserByEmail(String email) {
        return userMapper.selectUserByEmail(email) > 0;
    }

    public User login(UserLoginRequest userLoginRequest) {
        return userMapper.login(userLoginRequest);
    }

    public List<User> selectUserListByIds(Collection<Integer> ids) {
        return userMapper.selectUserListByIds(ids);
    }
}
