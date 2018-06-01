package me.hackathon.root.service.user;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import me.hackathon.root.exceptions.UserAlreadyExistsException;
import me.hackathon.root.model.request.UserLoginRequest;
import me.hackathon.root.model.user.User;
import me.hackathon.root.model.user.UserGrade;
import me.hackathon.root.model.user.UserStatus;
import me.hackathon.root.repository.user.UserRepository;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static final String DEFAULT_PROFILE_URL =
            "https://hackathon.image.s3.ap-northeast-2.amazonaws.com/b6c4c36d28d3b08e3bd4fcfb1a457602c0eed63f0194b0f22934d37e3f2df776?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Date=20180601T141937Z&X-Amz-SignedHeaders=host&X-Amz-Expires=604800&X-Amz-Credential=AKIAI7VBNUOJGTJ47BJQ%2F20180601%2Fap-northeast-2%2Fs3%2Faws4_request&X-Amz-Signature=4fbeddfa8f7894681e892400b726d7abe6f5e47ab906229d3e91ab44c02866c5";

    public void insertUser(User user) {
        // user 중복 검사
        if (findUserByEmail(user.getEmail()) == true) {
            throw new UserAlreadyExistsException();
        }
        userRepository.insertUser(buildUser(user));
    }

    public User getUserById(long id) {
        return userRepository.selectUserById(id);
    }

    public int updateUserById(User userRequest) {
        return userRepository.updateUserById(buildUser(userRequest));
    }

    public int deleteUserById(long id) {
        return userRepository.deleteUserById(id);
    }

    public User login(UserLoginRequest userLoginRequest) {
//        userLoginRequest.setPassword(SecurityUtils.encryptMD5AndSHA256(userLoginRequest.getPassword()));
        return userRepository.login(userLoginRequest);
    }

    public boolean findUserByEmail(String email) {
        return userRepository.selectUserByEmail(email);
    }

    public List<User> getUserListByIds(Collection<Integer> ids) {
        return userRepository.selectUserListByIds(ids);
    }

    public User buildUser(User userRequest) {
        return User.builder()
                   .grade(UserGrade.SILVER)
                   .email(userRequest.getEmail())
                   .password(userRequest.getPassword())
                   .phoneNumber(userRequest.getPhoneNumber())
                   .name(userRequest.getName())
                   .address(userRequest.getAddress())
                   .status(UserStatus.NORMAL)
                   .birth(userRequest.getBirth())
                   .coin(0)
                   .vltTime(0)
                   .profileUrl(DEFAULT_PROFILE_URL)
                   .build();
    }
}
